package com.jlabs.oauth2.client;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class IndexController {

    private WebClient webClient;

    public IndexController(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("http://127.0.0.1:9090")
                .build();
    }

    @GetMapping("/index")
    public String index(
            @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
            @AuthenticationPrincipal OidcUser oidcUser) {
        return "index.html";
    }

    @GetMapping("/tasks")
    public ResponseEntity<String> tasks(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client) {
        return webClient
                .get()
                .uri("tasks")
                .header("Authorization", "Bearer %s".formatted(
                        client.getAccessToken().getTokenValue()
                ))
                .retrieve()
                .toEntity(String.class)
                .block();

    }
}
