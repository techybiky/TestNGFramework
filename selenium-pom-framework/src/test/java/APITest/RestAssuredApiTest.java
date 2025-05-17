package APITest;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredApiTest {

	@Test
	public void ApiTest() {

		String json = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

		given().when().get("https://reqres.in/api/users/2").then().statusCode(200);

		// logs details for
		given().log().all().when().get("/users").then().log().body();

		// Path Param
		given().pathParam("id", 2).get("/users/{id}");

		// Query Param
		given().queryParam("page", 2).get("/users");

		// Post
		given().header("Content-Type", "application/json").body(json).post("/users").then().statusCode(201).log();

		Response res = given().get("https://reqres.in/api/users/2");
		res.jsonPath().equals("user");

	}

	@Test
	public void Pagination() {
		
		String baseURI = "https://api.github.com";
        String owner = "octocat";
        String repo = "Hello-World";
        int perPage = 5;
        

        for (int page = 1; page <= 3; page++) {  // You can loop more or until no data returned
        	
        	String fullUrl = RestAssured.baseURI + "/repos/" + owner + "/" + repo + "/issues"
                    + "?per_page=" + perPage + "&page=" + page;

            System.out.println("\nCalling URL: " + fullUrl);
            Response response = RestAssured
                    .given()
                        .baseUri(baseURI)
                        .pathParam("owner", owner)
                        .pathParam("repo", repo)
                        .queryParam("per_page", perPage)
                        .queryParam("page", page)
                    .when()
                        .get("/repos/{owner}/{repo}/issues")
                    .then()
                        .statusCode(200)
                        .extract().response();

            List<String> titles = response.jsonPath().getList("title");

            System.out.println("Page " + page + " - Total Issues Fetched: " + titles.size());
            for (String title : titles) {
                System.out.println("Issue: " + title);
            }

            // Stop if no more data
            if (titles.isEmpty()) {
                System.out.println("No more issues found. Stopping.");
                break;
            }
        }
	}
}

