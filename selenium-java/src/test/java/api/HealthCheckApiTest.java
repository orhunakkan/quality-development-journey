package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class HealthCheckApiTest {

  private static final String BASE_URL = System.getenv().getOrDefault("API_URL",
      "https://practice.expandtesting.com/notes/api");

  private static final String HEALTH_CHECK_PATH = "/health-check";

  @BeforeClass
  public static void setup() {
    RestAssured.baseURI = BASE_URL;
  }

  @Test
  public void shouldReturnSuccessfulHealthCheckResponse() {
    given()
        .contentType(ContentType.JSON)
        .when()
        .get(HEALTH_CHECK_PATH)
        .then()
        .statusCode(200)
        .body("success", equalTo(true))
        .body("message", equalTo("Notes API is Running"));
  }
}
