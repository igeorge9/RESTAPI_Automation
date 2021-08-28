package com.qa.oauth.tests;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import com.qa.pojo.Api;
import com.qa.pojo.GetCourses;
import com.qa.pojo.WebAutomation;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class OAuthTest {

	public static void main(String[] args) throws InterruptedException {
		
		/*
		 * Request for Code 
		*/
		
		String url="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWjXcdY7rS1yXjqEK1MITBw577TYEvfseOIBR1NxIJblMIB1I98u888oSIUSwXT3kw&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
		String partialCode=url.split("code=")[1];
		String finalCode=partialCode.split("&scope")[0];
		System.out.println("Extracted Code is "+finalCode);
		
		/*
		 * Request for access token
		 */
		
		String accessTokenResponse=given().log().all().urlEncodingEnabled(false)
		.queryParams("code",finalCode)
		.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization_code")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("state", "verifyfjdss")
		.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
        .when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
		System.out.println(accessTokenResponse);
		JsonPath js= new JsonPath(accessTokenResponse);
		String accessToken=js.getString("access_token");
		System.out.println("Extracted accessToken "+accessToken);
		
		/*
		 * Final Request body Response
		 */
		
		
		 String response=given() .queryParams("access_token", accessToken)
		  .when().get("https://rahulshettyacademy.com/getCourse.php").asString();
		  System.out.println(response);
		 
			/*
			 * String r2= given().contentType("application/json").
			 * queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON)
			 * .when() .get("https://rahulshettyacademy.com/getCourse.php") .asString();
			 * System.out.println(r2);
			 */
	
		GetCourses getCourse=given().contentType("application/json")
				.queryParam("access_token",accessToken)
		.expect()
//		.contentType(ContentType.JSON)
		.defaultParser(Parser.JSON)
		.when()
		.get("https://rahulshettyacademy.com/getCourse.php").as(GetCourses.class);
		
		/*
		 * To print the course title and price
		 */
		
		String[] Courses= {"Selenium Webdriver Java","Cypress","Protractor"};
		System.out.println("Services inside courses are "+getCourse.getServices());
		System.out.println("Instructor is "+getCourse.getInstructor());
		
		System.out.println("Couse name is "+getCourse.getCourses().getApi().get(1).getCourseTitle());
		List<Api> apiCourses=getCourse.getCourses().getApi();
		for(int i=0;i<apiCourses.size();i++)
		{
			if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
			{
				System.out.println("The price of SoapUI Webservices testing is "+apiCourses.get(i).getPrice());
			}
		}
		/*
		 * To get the course title of every course under WebAutomation 
		 */
		
		List<WebAutomation> webAutomationCourses=getCourse.getCourses().getWebAutomation();
		for(int i=0;i<webAutomationCourses.size();i++)
		{
			System.out.println(webAutomationCourses.get(i).getCourseTitle());
		}
		
		/*
		 * Above process using another method
		 */
		
		List<String> list=Arrays.asList(Courses);
		
		ArrayList<String> al= new ArrayList();
		for(int i=0;i<webAutomationCourses.size();i++)
		{
			al.add(webAutomationCourses.get(i).getCourseTitle());
		}
		Assert.assertTrue(al.equals(list));
		
	}
}
