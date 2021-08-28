package com.resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification requestSpecBuilder;
	public static RequestSpecification RequestSpec() throws IOException {
		
		if(requestSpecBuilder== null)
		{
		PrintStream log= new PrintStream(new FileOutputStream("logs.txt"));
		 requestSpecBuilder= new RequestSpecBuilder()
				.setContentType(ContentType.JSON)
				.setBaseUri(globalVariables("baseURI"))
				.addQueryParam("key","qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
                .build();
		return requestSpecBuilder;
		}
		return requestSpecBuilder;
	}
	
	public static String globalVariables(String key) throws IOException
	{
		Properties prop= new Properties();
		FileInputStream fis= new FileInputStream("C:\\Users\\i\\Irene's _Workspace\\RESTAPI_Testing\\src\\com\\resources\\prop.properties");
		prop.load(fis);
		String url=prop.getProperty(key);
		return url;
	}
	
	public String getJsonPath(Response response,String key) {
		
		String responseString=response.asString();
		JsonPath js= new JsonPath(responseString);
		return js.get(key).toString();
	}

}
