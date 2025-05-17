package APITest;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
public class APISchimaValidation {

	
	@Test
	public void SchemaValidation() {
		RestAssured.baseURI="https://reqres.in/";
		Response re = given()
				 .when()
				 	.get("/api/users?page=2")
				 .then()
				 	.statusCode(200)
				 	.extract().response();
		 
		
		List<Integer> id = re.jsonPath().get("data.id");
		
		given()
		.queryParam("page", 2)
		.when()
		.get("/api/users")
		.then()
		.statusCode(200).log().all();
		 	
	}
}
