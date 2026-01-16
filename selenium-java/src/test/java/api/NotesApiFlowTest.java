package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

public class NotesApiFlowTest {

    private static final String BASE_URL = System.getenv().getOrDefault("API_URL",
            "https://practice.expandtesting.com/notes/api");

    private static final String REGISTER_PATH = "/users/register";
    private static final String LOGIN_PATH = "/users/login";
    private static final String NOTES_PATH = "/notes";

    private static Map<String, Object> registeredUser;
    private static String authToken;
    private static String createdNoteId;

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(priority = 1)
    public void shouldRegisterNewUserSuccessfully() {
        long suffix = System.currentTimeMillis();

        registeredUser = Map.of(
                "name", "testuser-" + suffix,
                "email", "testuser-" + suffix + "@example.com",
                "password", "Test@1234");

        given()
                .contentType(ContentType.JSON)
                .body(registeredUser)
                .when()
                .post(REGISTER_PATH)
                .then()
                .statusCode(201)
                .body("success", equalTo(true))
                .body("status", equalTo(201))
                .body("message", equalTo("User account created successfully"))
                .body("data", notNullValue())
                .body("data.id", notNullValue())
                .body("data.id", instanceOf(String.class))
                .body("data.name", equalTo(registeredUser.get("name")))
                .body("data.email", equalTo(registeredUser.get("email")));
    }

    @Test(priority = 2)
    public void shouldLoginWithRegisteredUserSuccessfully() {
        Map<String, Object> loginBody = Map.of(
                "email", registeredUser.get("email"),
                "password", registeredUser.get("password"));

        Response res = given()
                .contentType(ContentType.JSON)
                .body(loginBody)
                .when()
                .post(LOGIN_PATH)
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("status", equalTo(200))
                .body("message", equalTo("Login successful"))
                .body("data", notNullValue())
                .body("data.id", instanceOf(String.class))
                .body("data.name", equalTo(registeredUser.get("name")))
                .body("data.email", equalTo(registeredUser.get("email")))
                .body("data.token", instanceOf(String.class))
                .extract().response();

        authToken = res.path("data.token");
    }

    @Test(priority = 3)
    public void shouldCreateHomeNoteUsingAuthToken() {
        Map<String, Object> notePayload = Map.of(
                "title", "Home Note Title",
                "description", "Home API note description",
                "category", "Home");

        Response res = given()
                .contentType(ContentType.JSON)
                .header("x-auth-token", authToken)
                .body(notePayload)
                .when()
                .post(NOTES_PATH)
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Note successfully created"))
                .body("data", notNullValue())
                .body("data.id", instanceOf(String.class))
                .body("data.title", equalTo(notePayload.get("title")))
                .body("data.description", equalTo(notePayload.get("description")))
                .body("data.category", equalTo(notePayload.get("category")))
                .body("data.completed", equalTo(false))
                .body("data.created_at", instanceOf(String.class))
                .body("data.updated_at", instanceOf(String.class))
                .body("data.user_id", instanceOf(String.class))
                .extract().response();

        createdNoteId = res.path("data.id");
    }

    @Test(priority = 4)
    public void shouldCreateWorkNoteUsingAuthToken() {
        Map<String, Object> notePayload = Map.of(
                "title", "Work Note Title",
                "description", "Work API note description",
                "category", "Work");

        given()
                .contentType(ContentType.JSON)
                .header("x-auth-token", authToken)
                .body(notePayload)
                .when()
                .post(NOTES_PATH)
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Note successfully created"))
                .body("data", notNullValue())
                .body("data.id", instanceOf(String.class))
                .body("data.title", equalTo(notePayload.get("title")))
                .body("data.description", equalTo(notePayload.get("description")))
                .body("data.category", equalTo(notePayload.get("category")))
                .body("data.completed", equalTo(false))
                .body("data.created_at", instanceOf(String.class))
                .body("data.updated_at", instanceOf(String.class))
                .body("data.user_id", instanceOf(String.class));
    }

    @Test(priority = 5)
    public void shouldRetrieveNoteByIdWithAuthToken() {
        given()
                .contentType(ContentType.JSON)
                .header("x-auth-token", authToken)
                .when()
                .get(NOTES_PATH + "/" + createdNoteId)
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Note successfully retrieved"))
                .body("data", notNullValue())
                .body("data.id", equalTo(createdNoteId))
                .body("data.title", equalTo("Home Note Title"))
                .body("data.description", equalTo("Home API note description"))
                .body("data.category", equalTo("Home"))
                .body("data.completed", equalTo(false))
                .body("data.created_at", instanceOf(String.class))
                .body("data.updated_at", instanceOf(String.class))
                .body("data.user_id", instanceOf(String.class));
    }

    @Test(priority = 6)
    public void shouldUpdateNoteByIdWithAuthToken() {
        Map<String, Object> updatePayload = Map.of(
                "title", "Updated Home Note Title",
                "description", "Updated Home API note description",
                "completed", true,
                "category", "Personal");

        given()
                .contentType(ContentType.JSON)
                .header("x-auth-token", authToken)
                .body(updatePayload)
                .when()
                .put(NOTES_PATH + "/" + createdNoteId)
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Note successfully Updated"))
                .body("data", notNullValue())
                .body("data.id", equalTo(createdNoteId))
                .body("data.title", equalTo(updatePayload.get("title")))
                .body("data.description", equalTo(updatePayload.get("description")))
                .body("data.category", equalTo(updatePayload.get("category")))
                .body("data.completed", equalTo(updatePayload.get("completed")))
                .body("data.created_at", instanceOf(String.class))
                .body("data.updated_at", instanceOf(String.class))
                .body("data.user_id", instanceOf(String.class));
    }

    @Test(priority = 7)
    public void shouldRetrieveAllNotesWithAuthToken() {
        Response res = given()
                .contentType(ContentType.JSON)
                .header("x-auth-token", authToken)
                .when()
                .get(NOTES_PATH)
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Notes successfully retrieved"))
                .body("data", notNullValue())
                .body("data", instanceOf(List.class))
                .body("data.size()", equalTo(2))
                .extract().response();

        List<Map<String, Object>> notes = res.path("data");

        Map<String, Object> personalNote = notes.stream()
                .filter(n -> "Personal".equals(n.get("category")))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Personal note not found"));

        assertThat(personalNote.get("id")).isNotNull();
        assertThat(personalNote.get("title")).isEqualTo("Updated Home Note Title");
        assertThat(personalNote.get("description")).isEqualTo("Updated Home API note description");
        assertThat(personalNote.get("completed")).isEqualTo(true);
        assertThat(personalNote.get("created_at")).isInstanceOf(String.class);
        assertThat(personalNote.get("updated_at")).isInstanceOf(String.class);
        assertThat(personalNote.get("user_id")).isInstanceOf(String.class);

        Map<String, Object> workNote = notes.stream()
                .filter(n -> "Work".equals(n.get("category")))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Work note not found"));

        assertThat(workNote.get("id")).isNotNull();
        assertThat(workNote.get("title")).isEqualTo("Work Note Title");
        assertThat(workNote.get("description")).isEqualTo("Work API note description");
        assertThat(workNote.get("completed")).isEqualTo(false);
        assertThat(workNote.get("created_at")).isInstanceOf(String.class);
        assertThat(workNote.get("updated_at")).isInstanceOf(String.class);
        assertThat(workNote.get("user_id")).isInstanceOf(String.class);
    }

    @Test(priority = 8)
    public void shouldDeleteNoteByIdWithAuthToken() {
        given()
                .contentType(ContentType.JSON)
                .header("x-auth-token", authToken)
                .when()
                .delete(NOTES_PATH + "/" + createdNoteId)
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Note successfully deleted"));
    }
}
