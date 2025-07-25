package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public void cadastrar(Usuario usuario) {
    	log.info("Recebendo cadastro de novo usuário: {}", usuario.getUsername());
    	
        String senhaCriptografada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(senhaCriptografada);
        
        usuarioRepository.cadastrar(usuario);
    }

    public List<Usuario> listarTodos() {
    	log.info("Listando todos os usuários");
        return usuarioRepository.listarTodos();
    }
    
    public Usuario buscarPorUsername(String username) {
        log.info("Buscando usuário com username: {}", username);
        return usuarioRepository.buscarPorUsername(username);
    }

    public void atualizarStatus(Long id, boolean ativo) {
    	log.info("Atualizando status do usuário ID {} para {}", id, ativo);
        usuarioRepository.atualizarStatus(id, ativo);
    }
}
