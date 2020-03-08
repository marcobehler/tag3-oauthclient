package com.example.clientserverservlet;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

@RestController
public class MyController {

    private final WebClient webClient;

    public MyController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/")
    public String hello(HttpServletRequest request) {
        return "hallo";
    }

    @GetMapping("/invoices")
    public String explicit(Model model) {
        String body = this.webClient
                .get()
                .attributes(clientRegistrationId("marcobehler"))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return body;
    }
}
