package testCases;

import config.ApplicationConfigReader;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class LoginTest {

    private static final String url = ApplicationConfigReader.getProtocol() +
            ApplicationConfigReader.getBaseUrl() + ApplicationConfigReader.getPath();

    /**
     * Tests a valid login scenario.
     * Verifies that the response status is 200 and the response body contains
     * the expected status, message, and non-null idToken.
     */
    @Test
    public void testValidLogin() {
        // Set the base URI
        Response response = given()
                .contentType(ApplicationConfigReader.getContentType())
                .formParam("email", ApplicationConfigReader.getEmail())
                .formParam("password", ApplicationConfigReader.getPassword())
                .when()
                .post(url)
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", equalTo("Login successful"))
                .body("data.idToken", notNullValue())
                .log().all()
                .extract().response();
    }

    /**
     * Tests a login attempt with an invalid email format.
     * Verifies that the response status is 400 and the response body contains
     * the expected status and message indicating a validation error.
     */
    @Test
    public void testInvalidEmailFormat() {
        RestAssured.given()
                .contentType(ApplicationConfigReader.getContentType())
                .formParam("email", "invalid-email-format")
                .formParam("password", ApplicationConfigReader.getPassword())
                .when()
                .post(url)
                .then()
                .statusCode(400)
                .body("status", equalTo("ValidationError"))
                .body("message", equalTo("Email is not valid"))
                .log().all();
    }

    /**
     * Tests a login attempt with an incorrect password.
     * Verifies that the response status is 401 and the response body contains
     * the expected status and message indicating incorrect username or password.
     */
    @Test
    public void testIncorrectPassword() {
        RestAssured.given()
                .contentType(ApplicationConfigReader.getContentType())
                .formParam("email", ApplicationConfigReader.getEmail())
                .formParam("password", "wrongpassword")
                .when()
                .post(url)
                .then()
                .statusCode(401)
                .body("status", equalTo("NotAuthorizedException"))
                .body("message", equalTo("Incorrect username or password."))
                .log().all();
    }

    /**
     * Tests a login attempt with SQL injection payloads.
     * Verifies that the response status is 400 and the response body contains
     * the expected status and message indicating a validation error.
     */
    @Test
    public void testSQLInjection() {
        RestAssured.given()
                .contentType(ApplicationConfigReader.getContentType())
                .formParam("email", "' OR '1'='1")
                .formParam("password", "' OR '1'='1")
                .when()
                .post(url)
                .then()
                .statusCode(400) // Expecting unauthorized response
                .body("status", equalTo("ValidationError"))
                .body("message", equalTo("Email is not valid"))
                .log().all();
    }
}
