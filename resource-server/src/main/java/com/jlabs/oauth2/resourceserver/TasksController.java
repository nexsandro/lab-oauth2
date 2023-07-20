package com.jlabs.oauth2.resourceserver;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TasksController {

    @GetMapping("/tasks")
    @ResponseBody
    public String tasks(@AuthenticationPrincipal Jwt jwt) {
        return "<h1>Tasks para usuário %s</h1> de claims %s: ".formatted(jwt.getSubject(), jwt.getClaims());
    }
}
