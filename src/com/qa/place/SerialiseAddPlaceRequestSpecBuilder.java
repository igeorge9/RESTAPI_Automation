package com.qa.place;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import com.qa.pojo.AddPlace;
import com.qa.pojo.Location;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SerialiseAddPlaceRequestSpecBuilder {

	public static void main(String[] args) {
//		RestAssured.baseURI="https://rahulshettyacademy.com";
		AddPlace addPlace=new AddPlace();
		addPlace.setAccuracy(50);
		addPlace.setAddress("Ezhakkaranad North");
		addPlace.setLanguage("English-EN");
		addPlace.setName("Home In Kerala");
		addPlace.setPhone_number("(+91) 983 893 3937");
		addPlace.setWebsite("http://google.com");
		
		
		List<String> al= new ArrayList<String>();
		al.add("shop");
		al.add("show park");
		addPlace.setTypes(al);
		
		Location location= new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		
		addPlace.setLocation(location);
		
		/*
		 * Response
		 * response=given().log().all().queryParams("key","qaclick123").body(addPlace).
		 * contentType("application/json") .when().post("maps/api/place/add/json")
		 * .then().log().all().assertThat().statusCode(200).extract().response();
		 */
		
		RequestSpecification requestSpecBuilder= new RequestSpecBuilder()
				.setContentType(ContentType.JSON)
				.setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key","qaclick123")
                .build();
		
		Response response=given().spec(requestSpecBuilder).body(addPlace)
				.when().post("maps/api/place/add/json")
				.then().assertThat().statusCode(200).extract().response();
	}

}
