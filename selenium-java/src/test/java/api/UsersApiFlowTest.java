package api;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsersApiFlowTest {

  private static final String BASE_URL =
      System.getenv().getOrDefault("API_URL", "https://practice.expandtesting.com/notes/api");

  private static final String REGISTER_PATH = "/users/register";
  private static final String LOGIN_PATH = "/users/login";
  private static final String PROFILE_PATH = "/users/profile";
  private static final String FORGOT_PASSWORD_PATH = "/users/forgot-password";
  private static final String LOGOUT_PATH = "/users/logout";

  private static Map<String, Object> registeredUser = new HashMap<>();
  private static String authToken;

  @BeforeAll
  static void setup() {
    RestAssured.baseURI = BASE_URL;
  }

  @Test
  @Order(1)
  void shouldRegisterNewUserSuccessfully() {
    long suffix = System.currentTimeMillis();

    Map<String, Object> requestBody = Map.of(
        "name", "testuser-" + suffix,
        "email", "testuser-" + suffix + "@example.com",
        "password", "Test@1234"
    );

    registeredUser.clear();
    registeredUser.putAll(requestBody);

    given()
        .contentType(ContentType.JSON)
        .body(requestBody)
    .when()
        .post(REGISTER_PATH)
    .then()
        .statusCode(201)
        .body("success", equalTo(true))
        .body("status", equalTo(201))
        .body("message", equalTo("User account created successfully"))
        .body("data", notNullValue())
        .body("data.id", instanceOf(String.class))
        .body("data.name", equalTo(requestBody.get("name")))
        .body("data.email", equalTo(requestBody.get("email")));
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
  void shouldGetUserProfileWithValidToken() {
    given()
        .contentType(ContentType.JSON)
        .header("x-auth-token", authToken)
    .when()
        .get(PROFILE_PATH)
    .then()
        .statusCode(200)
        .body("success", equalTo(true))
        .body("message", equalTo("Profile successful"))
        .body("data", notNullValue())
        .body("data.id", instanceOf(String.class))
        .body("data.name", equalTo(registeredUser.get("name")))
        .body("data.email", equalTo(registeredUser.get("email")));
  }

  @Test
  @Order(4)
  void shouldUpdateUserProfileWithNamePhoneCompany() {
    Map<String, Object> updatedProfile = Map.of(
        "name", registeredUser.get("name") + "-updated",
        "phone", "1234567890",
        "company", "Test Company"
    );

    given()
        .contentType(ContentType.JSON)
        .header("x-auth-token", authToken)
        .body(updatedProfile)
    .when()
        .patch(PROFILE_PATH)
    .then()
        .statusCode(200)
        .body("success", equalTo(true))
        .body("status", equalTo(200))
        .body("message", equalTo("Profile updated successful"))
        .body("data", notNullValue())
        .body("data.id", instanceOf(String.class))
        .body("data.name", equalTo(updatedProfile.get("name")))
        .body("data.email", equalTo(registeredUser.get("email")))
        .body("data.phone", equalTo(updatedProfile.get("phone")))
        .body("data.company", equalTo(updatedProfile.get("company")));

    registeredUser.putAll(updatedProfile);
  }

  @Test
  @Order(5)
  void shouldSendForgotPasswordEmailWithValidEmailAddress() {
    Map<String, Object> forgotPasswordBody = Map.of(
        "email", registeredUser.get("email")
    );

    given()
        .contentType(ContentType.JSON)
        .body(forgotPasswordBody)
    .when()
        .post(FORGOT_PASSWORD_PATH)
    .then()
        .statusCode(200)
        .body("success", equalTo(true))
        .body("message", notNullValue())
        .body("message", containsString("Password reset link successfully sent to"));
  }

  @Test
  @Order(6)
  void shouldLogoutWithValidAuthToken() {
    given()
        .contentType(ContentType.JSON)
        .header("x-auth-token", authToken)
    .when()
        .delete(LOGOUT_PATH)
    .then()
        .statusCode(200)
        .body("success", equalTo(true))
        .body("message", equalTo("User has been successfully logged out"));
  }
}
