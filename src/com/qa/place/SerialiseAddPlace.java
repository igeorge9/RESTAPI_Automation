package com.qa.place;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.util.ArrayList;
import java.util.List;

import com.qa.pojo.AddPlace;
import com.qa.pojo.Location;

public class SerialiseAddPlace {

	public static void main(String[] args) {

		RestAssured.baseURI="https://rahulshettyacademy.com";
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
		
		Response response=given().log().all().queryParams("key","qaclick123").body(addPlace).contentType("application/json")
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).extract().response();
		
//		String responseString=response.asString();
//		System.out.println(responseString);
	}

}
