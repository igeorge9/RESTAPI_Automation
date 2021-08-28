package cucumber.steps;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.testng.Assert;
import com.resources.APIResources;
import com.resources.TestDataBuild;
import com.resources.Utils;
import io.cucumber.java.en.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class stepDefinition extends Utils {

	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	Response response;
	APIResources resourceAPI;
	TestDataBuild testDataBuild = new TestDataBuild();
	static String place_id;
	 
	@Given("AddPlace Payload with {string} {string} and {string}")
	public void add_place_payload_with_and(String name, String language, String address) throws IOException {

		requestSpec = given().spec(RequestSpec()).body(testDataBuild.AddPlacePayload(name, language, address));
	}

	@When("user calls {string} with {string} HTTP request")
	public void user_calls_with_http_request(String resource, String method) throws IOException {

		resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResources());
		
		responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		
		if (method.equalsIgnoreCase("POST")) {
			response = requestSpec
					.when().post(resourceAPI.getResources());
//					.then().spec(responseSpec).extract()
//					.response();
		} 
		
		else if (method.equalsIgnoreCase("GET")) {
			response = requestSpec.spec(RequestSpec()).
					when().get(resourceAPI.getResources());
//					then().spec(responseSpec)
//					.extract().response();
		}
	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer statusCode) {

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {

		/*
		 * String responseString=response.asString(); JsonPath js= new
		 * JsonPath(responseString);
		 * Assert.assertEquals(js.get(keyValue),expectedValue);
		 */

		Assert.assertEquals(getJsonPath(response, keyValue), expectedValue);

	}
	
	@Then("verify the place details are returned using {string} with {string} HTTP Request")
	public void verify_the_place_details_are_returned_using_with_http_request(String resource, String name) throws IOException {
	  
	  place_id=getJsonPath(response, "place_id");
	  requestSpec=given().queryParam("place_id", place_id).spec(RequestSpec());
	  user_calls_with_http_request(resource,"GET");
	  Assert.assertEquals(response.getStatusCode(),200);
	  String actualName=getJsonPath(response,"name");
	  assertEquals(actualName,name);
	  System.out.println(name);
	  System.out.println(response.asString());
	  
	  }
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	 
		requestSpec=given().spec(RequestSpec()).body(testDataBuild.deletePlacePayload(place_id));
	}
	 

}
