package com.example.demo.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NotesUsersAllFlowTest {
    private static WebClient webClient;
    private static Gson gson;
    private static Map<String, String> registeredUser;
    private static String authToken;

    @BeforeAll
    public static void setup() {
        String apiUrl = System.getProperty("apiUrl", "https://practice.expandtesting.com/notes/api");

        webClient = WebClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();

        gson = new Gson();
        registeredUser = new HashMap<>();
    }

    @Test
    @Order(1)
    public void shouldRegisterNewUserSuccessfully() {
        long suffix = System.currentTimeMillis();
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "testuser-" + suffix);
        requestBody.put("email", "testuser-" + suffix + "@example.com");
        requestBody.put("password", "Test@1234");

        registeredUser.putAll(requestBody);

        String response = webClient.post()
                .uri("/users/register")
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(status -> status.value() != 201, clientResponse -> clientResponse.bodyToMono(String.class)
                        .flatMap(body -> Mono.error(new RuntimeException("Registration failed: " + body))))
                .bodyToMono(String.class)
                .block();

        assertNotNull(response);
        JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
        assertTrue(jsonResponse.get("success").getAsBoolean());
        assertEquals(201, jsonResponse.get("status").getAsInt());
        assertEquals("User account created successfully", jsonResponse.get("message").getAsString());

        JsonObject data = jsonResponse.getAsJsonObject("data");
        assertNotNull(data.get("id").getAsString());
        assertEquals(requestBody.get("name"), data.get("name").getAsString());
        assertEquals(requestBody.get("email"), data.get("email").getAsString());
    }

    @Test
    @Order(2)
    public void shouldLoginWithRegisteredUserSuccessfully() {
        Map<String, String> loginBody = new HashMap<>();
        loginBody.put("email", registeredUser.get("email"));
        loginBody.put("password", registeredUser.get("password"));

        String response = webClient.post()
                .uri("/users/login")
                .bodyValue(loginBody)
                .retrieve()
                .onStatus(status -> status.value() != 200, clientResponse -> clientResponse.bodyToMono(String.class)
                        .flatMap(body -> Mono.error(new RuntimeException("Login failed: " + body))))
                .bodyToMono(String.class)
                .block();

        assertNotNull(response);
        JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
        assertTrue(jsonResponse.get("success").getAsBoolean());
        assertEquals(200, jsonResponse.get("status").getAsInt());
        assertEquals("Login successful", jsonResponse.get("message").getAsString());

        JsonObject data = jsonResponse.getAsJsonObject("data");
        assertNotNull(data.get("id").getAsString());
        assertEquals(registeredUser.get("name"), data.get("name").getAsString());
        assertEquals(registeredUser.get("email"), data.get("email").getAsString());

        authToken = data.get("token").getAsString();
        assertNotNull(authToken);
        assertFalse(authToken.isEmpty());
    }

    @Test
    @Order(3)
    public void shouldGetUserProfileWithValidToken() {
        String response = webClient.get()
                .uri("/users/profile")
                .header("x-auth-token", authToken)
                .retrieve()
                .onStatus(status -> status.value() != 200, clientResponse -> clientResponse.bodyToMono(String.class)
                        .flatMap(body -> Mono.error(new RuntimeException("Get profile failed: " + body))))
                .bodyToMono(String.class)
                .block();

        assertNotNull(response);
        JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
        assertTrue(jsonResponse.get("success").getAsBoolean());
        assertEquals("Profile successful", jsonResponse.get("message").getAsString());

        JsonObject data = jsonResponse.getAsJsonObject("data");
        assertNotNull(data.get("id").getAsString());
        assertEquals(registeredUser.get("name"), data.get("name").getAsString());
        assertEquals(registeredUser.get("email"), data.get("email").getAsString());
    }

    @Test
    @Order(4)
    public void shouldUpdateUserProfileWithNamePhoneAndCompany() {
        Map<String, String> updatedProfile = new HashMap<>();
        updatedProfile.put("name", registeredUser.get("name") + "-updated");
        updatedProfile.put("phone", "1234567890");
        updatedProfile.put("company", "Test Company");

        String response = webClient.patch()
                .uri("/users/profile")
                .header("x-auth-token", authToken)
                .bodyValue(updatedProfile)
                .retrieve()
                .onStatus(status -> status.value() != 200, clientResponse -> clientResponse.bodyToMono(String.class)
                        .flatMap(body -> Mono.error(new RuntimeException("Update profile failed: " + body))))
                .bodyToMono(String.class)
                .block();

        assertNotNull(response);
        JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
        assertTrue(jsonResponse.get("success").getAsBoolean());
        assertEquals(200, jsonResponse.get("status").getAsInt());
        assertEquals("Profile updated successful", jsonResponse.get("message").getAsString());

        JsonObject data = jsonResponse.getAsJsonObject("data");
        assertNotNull(data.get("id").getAsString());
        assertEquals(updatedProfile.get("name"), data.get("name").getAsString());
        assertEquals(registeredUser.get("email"), data.get("email").getAsString());
        assertEquals(updatedProfile.get("phone"), data.get("phone").getAsString());
        assertEquals(updatedProfile.get("company"), data.get("company").getAsString());

        registeredUser.putAll(updatedProfile);
    }

    @Test
    @Order(5)
    public void shouldSendForgotPasswordEmailWithValidEmailAddress() {
        Map<String, String> forgotPasswordBody = new HashMap<>();
        forgotPasswordBody.put("email", registeredUser.get("email"));

        String response = webClient.post()
                .uri("/users/forgot-password")
                .bodyValue(forgotPasswordBody)
                .retrieve()
                .onStatus(status -> status.value() != 200, clientResponse -> clientResponse.bodyToMono(String.class)
                        .flatMap(body -> Mono.error(new RuntimeException("Forgot password failed: " + body))))
                .bodyToMono(String.class)
                .block();

        assertNotNull(response);
        JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
        assertTrue(jsonResponse.get("success").getAsBoolean());

        String message = jsonResponse.get("message").getAsString();
        assertNotNull(message);
        assertTrue(message.contains("Password reset link successfully sent to"));
    }

    @Test
    @Order(6)
    public void shouldLogoutWithValidAuthToken() {
        String response = webClient.delete()
                .uri("/users/logout")
                .header("x-auth-token", authToken)
                .retrieve()
                .onStatus(status -> status.value() != 200, clientResponse -> clientResponse.bodyToMono(String.class)
                        .flatMap(body -> Mono.error(new RuntimeException("Logout failed: " + body))))
                .bodyToMono(String.class)
                .block();

        assertNotNull(response);
        JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
        assertTrue(jsonResponse.get("success").getAsBoolean());
        assertEquals("User has been successfully logged out", jsonResponse.get("message").getAsString());
    }
}
