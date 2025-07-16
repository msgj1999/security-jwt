package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class DemoController {

    @GetMapping("/")
    public String hello() {
        return "Teste";
    }

    @GetMapping("/endpoint1")
    public String endpoint1(Authentication authentication) {
        return buildResponse(authentication);
    }

    @GetMapping("/endpoint2")
    public String endpoint2(Authentication authentication) {
        return buildResponse(authentication);
    }

    @GetMapping("/endpoint3")
    public String endpoint3(Authentication authentication) {
        return buildResponse(authentication);
    }

    private String buildResponse(Authentication authentication) {
        String username = authentication.getName();
        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));

        return "Usuário:" + username + "\n"
                + "Permissões" + roles;
    }
}
