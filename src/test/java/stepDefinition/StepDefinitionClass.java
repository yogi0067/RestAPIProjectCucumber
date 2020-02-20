package stepDefinition;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlacePojo;
import pojo.Location;

public class StepDefinitionClass {
	RequestSpecification reqspec;
	ResponseSpecification resspec;
	Response res;
	AddPlacePojo inputPlace = new AddPlacePojo();
	@Given("Add Place PayLoad")
	public void add_Place_PayLoad() {
		
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		inputPlace.setAccuracy(50);
		inputPlace.setAddress("29, side layout, cohen 09");
		inputPlace.setLanguage("French-IN");
		inputPlace.setName("Frontline house");
		inputPlace.setLocation(location);
		ArrayList<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		inputPlace.setTypes(types);
		inputPlace.setWebsite("http://google.com");
		inputPlace.setPhone_number("(+91) 983 893 3937");

		reqspec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON)
				.addQueryParam("key", "qaclick123").build();
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectBody("status", equalTo("OK")).build();

		

	}

	@When("user calls {string} with Post Http Request")
	public void user_calls_with_Post_Http_Request(String string) {
		res = given().spec(reqspec).body(inputPlace).when().post("/maps/api/place/add/json").then()
				.spec(resspec).extract().response();

	}

	@Then("The API Call got success with Status code {int}")
	public void the_API_Call_got_success_with_Status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		Assert.assertEquals(res.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		String key1=res.jsonPath().get(key);
		Assert.assertEquals(key1,value);
		

	}
}
