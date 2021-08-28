package com.resources;

import java.util.ArrayList;
import java.util.List;

import com.qa.pojo.AddPlace;
import com.qa.pojo.Location;

public class TestDataBuild {
	
	public AddPlace AddPlacePayload(String name, String language,String address) {

	AddPlace addPlace=new AddPlace();
	addPlace.setAccuracy(50);
	addPlace.setAddress(address);
	addPlace.setLanguage(language);
	addPlace.setName(name);
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
	
	return addPlace;
	}
	
	public String deletePlacePayload(String placeId)
	{
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}
}
