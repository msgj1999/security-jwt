package com.example.demo.repository;

import com.example.demo.model.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class UsuarioRepository {

    private final DataSource dataSource;

    public void cadastrar(Usuario usuario) {
        String sql = "INSERT INTO usuario (username, password, status) VALUES (?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());
            stmt.setBoolean(3, usuario.isStatus());
            stmt.executeUpdate();

            log.info("Usuário cadastrado: {}", usuario.getUsername());
        } catch (SQLException e) {
            log.error("Erro ao cadastrar usuário", e);
            throw new RuntimeException(e);
        }
    }

    public List<Usuario> listarTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT id, username, password, status FROM usuario";
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getLong("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setStatus(rs.getBoolean("status"));
                lista.add(u);
            }
        } catch (SQLException e) {
            log.error("Erro ao listar usuários", e);
        }
        return lista;
    }
    
    public Usuario buscarPorUsername(String username) {
        String sql = "SELECT id, username, password, status FROM usuario WHERE username = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setId(rs.getLong("id"));
                    u.setUsername(rs.getString("username"));
                    u.setPassword(rs.getString("password"));
                    u.setStatus(rs.getBoolean("status"));
                    return u;
                }
            }
        } catch (SQLException e) {
            log.error("Erro ao buscar usuário por username", e);
            throw new RuntimeException(e);
        }
        return null; 
    }

    public void atualizarStatus(Long id, boolean ativo) {
        String sql = "UPDATE usuario SET status = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, ativo);
            stmt.setLong(2, id);
            stmt.executeUpdate();

            log.info("Usuário {} foi {}", id, ativo ? "ativado" : "desativado");
        } catch (SQLException e) {
            log.error("Erro ao ativar/desativar usuário", e);
            throw new RuntimeException(e);
        }
    }
}

