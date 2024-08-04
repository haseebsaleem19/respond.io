package testCases;

import config.ApplicationConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class workspace {

    private static final String url = ApplicationConfigReader.getProtocol() +
            ApplicationConfigReader.getBaseUrl() + ApplicationConfigReader.getApiPath()+
            ApplicationConfigReader.getOrgId()+ ApplicationConfigReader.getCreateWorkSpace();

    private static final String loginUrl = ApplicationConfigReader.getProtocol() +
            ApplicationConfigReader.getBaseUrl() + ApplicationConfigReader.getPath();


    public String loginToken()
    {
        Response response = given()
                .contentType(ApplicationConfigReader.getContentType())
                .formParam("email", ApplicationConfigReader.getEmail())
                .formParam("password", ApplicationConfigReader.getPassword())
                .when()
                .post(loginUrl)
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", equalTo("Login successful"))
                .body("data.idToken", notNullValue())
                .log().all()
                .extract().response();
        // Extract the idToken
        String idToken = response.jsonPath().getString("data.idToken");
        return idToken;
    }
    String token = "Bearer "+loginToken();
    @Test
    public void testAddWorkSpace() {

        int result[] =  getWorkSpace();
        if (result[0] >= 1)
        {
            deleteWorkspace(result[1]);
        }

        RestAssured.given()

                .contentType(ApplicationConfigReader.getContentType())
                .header("Authorization", token)
                .header("orgid", ApplicationConfigReader.getOrgId())
                .formParam("name", ApplicationConfigReader.getName())
                .formParam("timezone", ApplicationConfigReader.getTimezone())
                .when()
                .post(url)
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", equalTo("Workspace added Successfully."))
                .body("data.bundle.onboarding.isOnboardingDismissed", equalTo(false))
                .body("data.bundle.onboarding.primaryChannels", hasSize(0))
                .body("data.isAway", equalTo(false))
                .body("data.isEnabled", equalTo(true))
                .body("data.usersInactiveAfter", equalTo(300))
                .body("data.name", equalTo("haseebsaleem"))
                .body("data.status", equalTo(true))
                .body("data.lang", equalTo("en"))
                .body("data.orgId", equalTo(239021))
                .body("data.timezone", equalTo("Asia/Karachi"))
                .body("data.updated_at", notNullValue())
                .body("data.created_at", notNullValue())
                .log().all()
                .extract()
                .response();
    }


    // @Test
    public int[] getWorkSpace() {
        String requestBody = "{\"search\":\"\",\"pagination\":{\"page\":1,\"itemsPerPage\":25,\"sortBy\":[\"name\"],\"descending\":[false],\"rowsPerPage\":25}}";

        // Make the POST request
        Response response = given()
                .header("Authorization", token)
                .header("content-type", "application/json")
                .header("orgid", "239021")
                .body(requestBody)
                .when()
                .post("https://app.respond.io/api/organization/239021/spaces")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .response();

        JsonPath jsonPath = response.jsonPath();
        int totalCount = jsonPath.getInt("data.totalCount");
        List<Map<String, Object>> items = jsonPath.getList("data.items");

        int[] result = new int[2];
        result[0] = totalCount;

        // Check if items list is empty
        if (items.isEmpty()) {
            result[1] = -1; // Default value when no items are present
        } else {
            int id = jsonPath.getInt("data.items[0].id");
            result[1] = id;
        }
        return result;
    }


    public void deleteWorkspace(int id) {
        String url = "https://app.respond.io/api/organization/"+ApplicationConfigReader.getOrgId()+"/spaces/"+id;
        // Make the POST request
        Response response = given()
                .header("authorization", token)
                .header("content-type", "application/json")
                .header("orgid", ApplicationConfigReader.getOrgId())
                .when()
                .delete(url)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

}