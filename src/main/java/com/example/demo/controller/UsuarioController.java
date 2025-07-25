package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public void cadastrarUsuario(@RequestBody Usuario usuario) {
        log.info("Recebendo cadastro de novo usuário: {}", usuario.getUsername());
        usuarioService.cadastrar(usuario);
    }

    @GetMapping("/listar")
    public List<Usuario> listarUsuarios() {
        log.info("Listando todos os usuários");
        return usuarioService.listarTodos();
    }

    @PutMapping("/{id}/status")
    public void ativarDesativarUsuario(@PathVariable Long id, @RequestParam boolean ativo) {
        log.info("Atualizando status do usuário ID {} para {}", id, ativo);
        usuarioService.atualizarStatus(id, ativo);
    }
}
