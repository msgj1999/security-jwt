package com.example.demo.controller;

import lombok.extern.log4j.Log4j2;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Log4j2
@RestController
public class DemoController {

    @GetMapping("/")
    public String hello() {
        log.info("Teste log");
        return "Teste";
    }

    @GetMapping("/endpoint1")
    public String endpoint1(Authentication authentication) {
        log.info("Acesso ao endpoint1 por: {}", authentication.getName());
        return buildResponse(authentication);
    }

    @GetMapping("/endpoint2")
    public String endpoint2(Authentication authentication) {
        log.info("Acesso ao endpoint2 por: {}", authentication.getName());
        return buildResponse(authentication);
    }

    @GetMapping("/endpoint3")
    public String endpoint3(Authentication authentication) {
        log.info("Acesso ao endpoint3 por: {}", authentication.getName());
        return buildResponse(authentication);
    }

    private String buildResponse(Authentication authentication) {
        String username = authentication.getName();
        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));

        return "Usuário:" + username + "\nPermissões:" + roles;
    }
}
