package com.apitesting;

import static io.restassured.RestAssured.given;

import java.util.List;

import io.restassured.path.json.JsonPath;
import pojo.Api;
import pojo.GetCourse;

//Using Cleient credentials OAuth - Authorization(Granting access) through Client credentials grant
//These are the details which are required for getting the access(access_token) 
//- through which we can be able to get the data(it could be private data or public)
public class OAuthTest {

	public static void main(String[] args) {

		//For 'Client Credentials grant' - 'client_id' and 'client_secret' are required.
		//Here, we also need to provide the 'grant_type' i.e., 'client_credentials'.
		//We should follow the API Contract
		String response  = given()
		.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type", "client_credentials")
		.formParam("scope", "trust")
		.when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
		.asString(); //We can use then() if we want to assert the status code and validate the response
		//If we are not doing that, no need to specify. So, we haven't specified here
		
		System.out.println(response); 
		
		JsonPath js = new JsonPath(response); 
		//In RestAssured, JsonPath is used to extract and validate specific parts of a JSON response. 
		//It simplifies traversing JSON structures by providing methods to query and fetch values using paths. 
		//For example, it allows retrieving specific keys, arrays, or nested objects in an API response efficiently.
		//Here, we are extracting the value(a String) of 'access_token' from the response
		
		String accessToken = js.getString("access_token");
		
		//Getting the object from 'GetCourse' class.
		GetCourse gc = given().queryParam("access_token", accessToken)
		.when().log().all()
		//.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();
		//As we already provided the QueryParameters in given(), in get(), we can provide onlly the endpoint without parameters
		.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class); //in 'pojo' package

		System.out.println(gc.getExpertise());
		System.out.println(gc.getInstructor());
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getServices());

		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		List<Api> apiCourses = gc.getCourses().getApi();
		for(int i=0; i<apiCourses.size();i++)
		{
			if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUi Webservices testing"))
			{
				System.out.println(apiCourses.get(i).getPrice());
			}
		}
	}
}
