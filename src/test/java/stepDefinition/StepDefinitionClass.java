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
import resources.PayLoadDataForTestBuild;
import resources.Utils;

public class StepDefinitionClass extends Utils {
	RequestSpecification reqspec;
	PayLoadDataForTestBuild addPlacePayload = new PayLoadDataForTestBuild()	;
	Response res;
	//AddPlacePojo inputPlace = new AddPlacePojo();
	@Given("Add Place PayLoad")
	public void add_Place_PayLoad() throws IOException {
		reqspec = given().spec(requestSpec()).body(addPlacePayload.getPlacePayload());

	}

	@When("user calls {string} with Post Http Request")
	public void user_calls_with_Post_Http_Request(String string) {
		res = reqspec.when().post("/maps/api/place/add/json").then()
				.spec(responseSpec()).extract().response();

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
