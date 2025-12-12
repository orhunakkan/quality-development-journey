package com.example.demo.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

public class NotesHealthCheckTest {
    private static WebClient webClient;
    private static Gson gson;

    @BeforeAll
    public static void setup() {
        String apiUrl = System.getProperty("apiUrl", "https://practice.expandtesting.com/notes/api");

        webClient = WebClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();

        gson = new Gson();
    }

    @Test
    public void shouldReturnSuccessfulHealthCheckResponse() {
        String response = webClient.get()
                .uri("/health-check")
                .retrieve()
                .onStatus(status -> status.value() != 200, clientResponse -> clientResponse.bodyToMono(String.class)
                        .flatMap(body -> Mono.error(new RuntimeException("Health check failed: " + body))))
                .bodyToMono(String.class)
                .block();

        assertNotNull(response);
        JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
        assertTrue(jsonResponse.get("success").getAsBoolean());
        assertEquals("Notes API is Running", jsonResponse.get("message").getAsString());
    }
}
