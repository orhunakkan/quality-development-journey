import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NotesApiFlowTest {

  private static final String BASE_URL =
      System.getenv().getOrDefault("API_URL", "https://practice.expandtesting.com/notes/api");

  private static final String REGISTER_PATH = "/users/register";
  private static final String LOGIN_PATH = "/users/login";
  private static final String NOTES_PATH = "/notes";

  private static Map<String, Object> registeredUser;
  private static String authToken;
  private static String createdNoteId;

  @BeforeAll
  static void setup() {
    RestAssured.baseURI = BASE_URL;
  }

  @Test
  @Order(1)
  void shouldRegisterNewUserSuccessfully() {
    long suffix = System.currentTimeMillis();

    registeredUser = Map.of(
        "name", "testuser-" + suffix,
        "email", "testuser-" + suffix + "@example.com",
        "password", "Test@1234"
    );

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

  @Test
  @Order(2)
  void shouldLoginWithRegisteredUserSuccessfully() {
    Map<String, Object> loginBody = Map.of(
        "email", registeredUser.get("email"),
        "password", registeredUser.get("password")
    );

    Response res =
        given()
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

  @Test
  @Order(3)
  void shouldCreateHomeNoteUsingAuthToken() {
    Map<String, Object> notePayload = Map.of(
        "title", "Home Note Title",
        "description", "Home API note description",
        "category", "Home"
    );

    Response res =
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
            .body("data.user_id", instanceOf(String.class))
            .extract().response();

    createdNoteId = res.path("data.id");
  }

  @Test
  @Order(4)
  void shouldCreateWorkNoteUsingAuthToken() {
    Map<String, Object> notePayload = Map.of(
        "title", "Work Note Title",
        "description", "Work API note description",
        "category", "Work"
    );

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

  @Test
  @Order(5)
  void shouldRetrieveNoteByIdWithAuthToken() {
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

  @Test
  @Order(6)
  void shouldUpdateNoteByIdWithAuthToken() {
    Map<String, Object> updatePayload = Map.of(
        "title", "Updated Home Note Title",
        "description", "Updated Home API note description",
        "completed", true,
        "category", "Personal"
    );

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

  @Test
  @Order(7)
  void shouldRetrieveAllNotesWithAuthToken() {
    Response res =
        given()
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

    // Find notes by category (similar to your JS .find())
    List<Map<String, Object>> notes = res.path("data");

    Map<String, Object> personalNote = notes.stream()
        .filter(n -> "Personal".equals(n.get("category")))
        .findFirst()
        .orElseThrow(() -> new AssertionError("Personal note not found"));

    Assertions.assertNotNull(personalNote.get("id"));
    Assertions.assertEquals("Updated Home Note Title", personalNote.get("title"));
    Assertions.assertEquals("Updated Home API note description", personalNote.get("description"));
    Assertions.assertEquals(true, personalNote.get("completed"));
    Assertions.assertTrue(personalNote.get("created_at") instanceof String);
    Assertions.assertTrue(personalNote.get("updated_at") instanceof String);
    Assertions.assertTrue(personalNote.get("user_id") instanceof String);

    Map<String, Object> workNote = notes.stream()
        .filter(n -> "Work".equals(n.get("category")))
        .findFirst()
        .orElseThrow(() -> new AssertionError("Work note not found"));

    Assertions.assertNotNull(workNote.get("id"));
    Assertions.assertEquals("Work Note Title", workNote.get("title"));
    Assertions.assertEquals("Work API note description", workNote.get("description"));
    Assertions.assertEquals(false, workNote.get("completed"));
    Assertions.assertTrue(workNote.get("created_at") instanceof String);
    Assertions.assertTrue(workNote.get("updated_at") instanceof String);
    Assertions.assertTrue(workNote.get("user_id") instanceof String);
  }

  @Test
  @Order(8)
  void shouldDeleteNoteByIdWithAuthToken() {
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
