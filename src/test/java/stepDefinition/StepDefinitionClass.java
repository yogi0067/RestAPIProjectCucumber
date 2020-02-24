package stepDefinition;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileNotFoundException;
import java.io.IOException;
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
import resources.APIResources;
import resources.PayLoadDataForTestBuild;
import resources.Utils;

public class StepDefinitionClass extends Utils {
	RequestSpecification reqspec;
	PayLoadDataForTestBuild addPlacePayload = new PayLoadDataForTestBuild();
	Response res;

	// AddPlacePojo inputPlace = new AddPlacePojo();
	@Given("Add Place PayLoad {string},{string},{string}")
	public void add_Place_PayLoad(String name, String language, String address) throws IOException {
		reqspec = given().spec(requestSpec()).body(addPlacePayload.getPlacePayload(name, language, address));

	}

	@When("user calls {string} with {string} Http Request")
	public void user_calls_with_Http_Request(String resource, String httpmethod) {
		APIResources apiresource = APIResources.valueOf(resource);
		if (httpmethod.equalsIgnoreCase("POST"))
			res = reqspec.when().post(apiresource.getResource()).then().spec(responseSpec()).extract().response();
		else if (httpmethod.equalsIgnoreCase("GET"))
			res = reqspec.when().get(apiresource.getResource());
	}

	@Then("The API Call got success with Status code {int}")
	public void the_API_Call_got_success_with_Status_code(Integer int1) {
		res = res.then().spec(responseSpec()).extract().response();
		Assert.assertEquals(res.getStatusCode(), 200);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		String key1 = res.jsonPath().get(key);
		Assert.assertEquals(key1, value);
	}
	@Then("Verify the place_id of maps that were created using and return same name {string} using {string}")
	public void verify_the_place_id_of_maps_that_were_created_using_and_return_same_name_using(String expectedName, String resource) throws IOException {
	    
		String place_id = res.jsonPath().get("place_id");
		reqspec= given().spec(requestSpec()).queryParam("place_id", place_id);
		user_calls_with_Http_Request(resource, "GET");
		String actualName= res.then().spec(responseSpec()).extract().response().jsonPath().get("name");
		Assert.assertEquals(expectedName, actualName);
	}


}
