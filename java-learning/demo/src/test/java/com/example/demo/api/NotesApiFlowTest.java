package com.example.demo.api;

import com.example.demo.models.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NotesApiFlowTest {
    private static WebClient webClient;
    private static Gson gson;
    private static User registeredUser;
    private static String authToken;
    private static String createdNoteId;

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
    @Order(1)
    public void shouldRegisterNewUserSuccessfully() {
        long suffix = System.currentTimeMillis();
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "testuser-" + suffix);
        requestBody.put("email", "testuser-" + suffix + "@example.com");
        requestBody.put("password", "Test@1234");

        registeredUser = new User(
                requestBody.get("name"),
                requestBody.get("email"),
                requestBody.get("password"));

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
        assertEquals("User account created successfully", jsonResponse.get("message").getAsString());
        assertEquals(201, jsonResponse.get("status").getAsInt());

        JsonObject data = jsonResponse.getAsJsonObject("data");
        assertNotNull(data.get("id").getAsString());
        assertEquals(registeredUser.getName(), data.get("name").getAsString());
        assertEquals(registeredUser.getEmail(), data.get("email").getAsString());
    }

    @Test
    @Order(2)
    public void shouldLoginWithRegisteredUser() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", registeredUser.getEmail());
        requestBody.put("password", registeredUser.getPassword());

        String response = webClient.post()
                .uri("/users/login")
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(status -> status.value() != 200, clientResponse -> clientResponse.bodyToMono(String.class)
                        .flatMap(body -> Mono.error(new RuntimeException("Login failed: " + body))))
                .bodyToMono(String.class)
                .block();

        assertNotNull(response);
        JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
        assertTrue(jsonResponse.get("success").getAsBoolean());
        assertEquals("Login successful", jsonResponse.get("message").getAsString());
        assertEquals(200, jsonResponse.get("status").getAsInt());

        JsonObject data = jsonResponse.getAsJsonObject("data");
        authToken = data.get("token").getAsString();
        assertNotNull(authToken);
        assertFalse(authToken.isEmpty());
        assertEquals(registeredUser.getName(), data.get("name").getAsString());
        assertEquals(registeredUser.getEmail(), data.get("email").getAsString());
    }

    @Test
    @Order(3)
    public void shouldCreateFirstNoteWithAuthToken() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("title", "Test Note 1");
        requestBody.put("description", "This is the first test note");
        requestBody.put("category", "Home");

        String response = webClient.post()
                .uri("/notes")
                .header("x-auth-token", authToken)
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(status -> status.value() != 200, clientResponse -> clientResponse.bodyToMono(String.class)
                        .flatMap(body -> Mono.error(new RuntimeException("Create note failed: " + body))))
                .bodyToMono(String.class)
                .block();

        assertNotNull(response);
        JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
        assertTrue(jsonResponse.get("success").getAsBoolean());
        assertEquals("Note successfully created", jsonResponse.get("message").getAsString());
        assertEquals(200, jsonResponse.get("status").getAsInt());

        JsonObject data = jsonResponse.getAsJsonObject("data");
        createdNoteId = data.get("id").getAsString();
        assertNotNull(createdNoteId);
        assertEquals("Test Note 1", data.get("title").getAsString());
        assertEquals("This is the first test note", data.get("description").getAsString());
        assertEquals("Home", data.get("category").getAsString());
        assertFalse(data.get("completed").getAsBoolean());
    }

    @Test
    @Order(4)
    public void shouldCreateSecondNoteWithAuthToken() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("title", "Test Note 2");
        requestBody.put("description", "This is the second test note");
        requestBody.put("category", "Work");

        String response = webClient.post()
                .uri("/notes")
                .header("x-auth-token", authToken)
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(status -> status.value() != 200, clientResponse -> clientResponse.bodyToMono(String.class)
                        .flatMap(body -> Mono.error(new RuntimeException("Create note failed: " + body))))
                .bodyToMono(String.class)
                .block();

        assertNotNull(response);
        JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
        assertTrue(jsonResponse.get("success").getAsBoolean());
        assertEquals("Note successfully created", jsonResponse.get("message").getAsString());

        JsonObject data = jsonResponse.getAsJsonObject("data");
        assertNotNull(data.get("id").getAsString());
        assertEquals("Test Note 2", data.get("title").getAsString());
        assertEquals("Work", data.get("category").getAsString());
    }

    @Test
    @Order(5)
    public void shouldRetrieveNoteByIdWithAuthToken() {
        String response = webClient.get()
                .uri("/notes/" + createdNoteId)
                .header("x-auth-token", authToken)
                .retrieve()
                .onStatus(status -> status.value() != 200, clientResponse -> clientResponse.bodyToMono(String.class)
                        .flatMap(body -> Mono.error(new RuntimeException("Get note failed: " + body))))
                .bodyToMono(String.class)
                .block();

        assertNotNull(response);
        JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
        assertTrue(jsonResponse.get("success").getAsBoolean());
        assertEquals("Note successfully retrieved", jsonResponse.get("message").getAsString());
        assertEquals(200, jsonResponse.get("status").getAsInt());

        JsonObject data = jsonResponse.getAsJsonObject("data");
        assertEquals(createdNoteId, data.get("id").getAsString());
        assertEquals("Test Note 1", data.get("title").getAsString());
        assertEquals("This is the first test note", data.get("description").getAsString());
        assertEquals("Home", data.get("category").getAsString());
    }

    @Test
    @Order(6)
    public void shouldUpdateNoteByIdWithAuthToken() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", "Updated Test Note 1");
        requestBody.put("description", "This note has been updated");
        requestBody.put("completed", true);
        requestBody.put("category", "Personal");

        String response = webClient.put()
                .uri("/notes/" + createdNoteId)
                .header("x-auth-token", authToken)
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(status -> status.value() != 200, clientResponse -> clientResponse.bodyToMono(String.class)
                        .flatMap(body -> Mono.error(new RuntimeException("Update note failed: " + body))))
                .bodyToMono(String.class)
                .block();

        assertNotNull(response);
        JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
        assertTrue(jsonResponse.get("success").getAsBoolean());
        assertEquals("Note successfully Updated", jsonResponse.get("message").getAsString());
        assertEquals(200, jsonResponse.get("status").getAsInt());

        JsonObject data = jsonResponse.getAsJsonObject("data");
        assertEquals(createdNoteId, data.get("id").getAsString());
        assertEquals("Updated Test Note 1", data.get("title").getAsString());
        assertEquals("This note has been updated", data.get("description").getAsString());
        assertEquals("Personal", data.get("category").getAsString());
        assertTrue(data.get("completed").getAsBoolean());
    }

    @Test
    @Order(7)
    public void shouldRetrieveAllNotesWithAuthToken() {
        String response = webClient.get()
                .uri("/notes")
                .header("x-auth-token", authToken)
                .retrieve()
                .onStatus(status -> status.value() != 200, clientResponse -> clientResponse.bodyToMono(String.class)
                        .flatMap(body -> Mono.error(new RuntimeException("Get all notes failed: " + body))))
                .bodyToMono(String.class)
                .block();

        assertNotNull(response);
        JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
        assertTrue(jsonResponse.get("success").getAsBoolean());
        assertEquals("Notes successfully retrieved", jsonResponse.get("message").getAsString());
        assertEquals(200, jsonResponse.get("status").getAsInt());

        JsonArray data = jsonResponse.getAsJsonArray("data");
        assertNotNull(data);
        assertTrue(data.size() >= 2, "Should have at least 2 notes");

        // Verify one of our notes exists in the list
        boolean foundOurNote = false;
        for (int i = 0; i < data.size(); i++) {
            JsonObject note = data.get(i).getAsJsonObject();
            if (note.get("id").getAsString().equals(createdNoteId)) {
                foundOurNote = true;
                assertEquals("Updated Test Note 1", note.get("title").getAsString());
                break;
            }
        }
        assertTrue(foundOurNote, "Should find our updated note in the list");
    }

    @Test
    @Order(8)
    public void shouldDeleteNoteByIdWithAuthToken() {
        String response = webClient.delete()
                .uri("/notes/" + createdNoteId)
                .header("x-auth-token", authToken)
                .retrieve()
                .onStatus(status -> status.value() != 200, clientResponse -> clientResponse.bodyToMono(String.class)
                        .flatMap(body -> Mono.error(new RuntimeException("Delete note failed: " + body))))
                .bodyToMono(String.class)
                .block();

        assertNotNull(response);
        JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
        assertTrue(jsonResponse.get("success").getAsBoolean());
        assertEquals("Note successfully deleted", jsonResponse.get("message").getAsString());
        assertEquals(200, jsonResponse.get("status").getAsInt());
    }
}
