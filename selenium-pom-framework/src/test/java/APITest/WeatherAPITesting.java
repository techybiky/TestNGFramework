package APITest;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class WeatherAPITesting {

	@Test
	public void TestCall() {

		String apiKey = "6b534e5ea8923237f55bec307f680dc9";
		baseURI = "https://api.openweathermap.org";
		
		Response response = given()
		.queryParam("lat", 33.44)
		.queryParam("lon", 94.00)
		.queryParam("exclude", "hourly")
		.queryParam("appid", apiKey)
		.get("/data/3.0/onecall")
		.then().statusCode(401)
		.extract().response();
		
		Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
		
		

	}

}
