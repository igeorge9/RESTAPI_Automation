package com.qa.place;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.resources.TestDataBuild;
import com.resources.Utils;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

public class SerialiseAddPlaceRequestnResponseSpecBuilder extends Utils {

//	public static void main(String[] args) throws IOException {
	public static void  SerialiseAddPlaceRequestnResponseSpecBuilder(String name) {
		
		//		RestAssured.baseURI="https://rahulshettyacademy.com";
		TestDataBuild testDataBuild= new TestDataBuild();
		
		/*
		 * Response
		 * response=given().log().all().queryParams("key","qaclick123").bodyhttp://marketplace.eclipse.org/marketplace-client-intro?mpc_install=2427135(addPlace).
		 * contentType("application/json") .when().post("maps/api/place/add/json")
		 * .then().log().all().assertThat().statusCode(200).extract().response();
		 */
		
		
		ResponseSpecification responseSpecBuilder=new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		/*
		Response response=given().log().all().spec(RequestSpec()).body(testDataBuild.AddPlacePayload(name, language, address))
				.when().post("maps/api/place/add/json")
				.then().log().all().spec(responseSpecBuilder)
				.extract().response();
		System.out.println(response);
		*/
	}
}
