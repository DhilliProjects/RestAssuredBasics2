package authorize;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.Api;
import pojo.GetCourse;
import pojo.WebAutomation;
//here, we doing Deserialization - Converting Request/Response payload(which is in Json/Xml format) into Java object - Using Json, we are doing Java things like creating object

//Using Cleient credentials OAuth - Authorization(Granting access) through Client credentials grant
//These are the details which are required for getting the access(access_token) 
//- through which we can be able to get the data(it could be private data or public)
public class OAuthDeserializeTest {

	public static void main(String[] args) {
		//Note, that URL is a combination of URI and Resource. When you provide the URI globally, no need to use it in post() again. We can just post the Resource directly
		RestAssured.baseURI = "https://rahulshettyacademy.com"; //providing the URI globally
		
		//For 'Client Credentials grant' - 'client_id' and 'client_secret' are required.
		//Here, we also need to provide the 'grant_type' i.e., 'client_credentials'.
		//We should follow the API Contract
		String response  = given()
		.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type", "client_credentials")
		.formParam("scope", "trust")
		.when().log().all().post("oauthapi/oauth2/resourceOwner/token")
		.asString(); //We can use then() if we want to assert the status code and validate the response
		//If we are not doing that, no need to specify. So, we haven't specified here
		
		System.out.println(response); 
		
		JsonPath js = new JsonPath(response); 
		//In RestAssured, JsonPath is used to extract and validate specific parts of a JSON response. 
		//It simplifies traversing JSON structures by providing methods to query and fetch values using paths. 
		//For example, it allows retrieving specific keys, arrays, or nested objects in an API response efficiently.
		//Here, we are extracting the value(a String) of 'access_token' from the response
		
		String accessToken = js.getString("access_token"); //here, we got the access token from the response
		
		//Getting the object from 'GetCourse' class.
		GetCourse gc = given().queryParam("access_token", accessToken) //by providing the access token as Params, we will be getting the further details of the courses.
		.when().log().all()
		//.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();
		//As we already provided the QueryParameters in given(), in get(), we can provide onlly the endpoint without parameters
		.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class); //in 'pojo' package

		//Simple jsons' formats
		System.out.println(gc.getExpertise());
		System.out.println(gc.getInstructor());
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getServices());

		//Now, Dynamic json
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle()); //getting the course name at index 1 of Api in Courses i.e., SoapUi Webservices testing
		
		List<Api> apiCourses = gc.getCourses().getApi(); //List of courses in Api
		for(int i=0; i<apiCourses.size();i++)
		{
			if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUi Webservices testing")) //validating the course title with 'if' condition
			{
				System.out.println(apiCourses.get(i).getPrice()); //and printing the price of the relevant course
			}
		}
		
		//Now, let's try to get the course names of WebAutomation
		ArrayList<String> arr = new ArrayList<String>(); //Creating an Arraylist
		
		List<WebAutomation> web = gc.getCourses().getWebAutomation();
		for(int i=0; i<web.size(); i++)
		{
			arr.add(web.get(i).getCourseTitle()); //additing each course title of WebAutomation in the List array i.e., 'arr'
		}
		
		String[] courseTitles = {"Selenium Webdriver Java", "Cypress", "Protractor"}; //Array of string having course titles
		List<String> expectedList = Arrays.asList(courseTitles); //Converting the 'courseTitle' array into ArrayList so that we can compare two Arraylists easily
		
		//Now, we need to validate whether this converted Arraylist and the ArrayList having all courseTiles, which are added in for loop
		Assert.assertTrue(arr.equals(expectedList)); //Validating the ArrayList of expectedListS
		//If two variables are Strings, we can use Assert.assertEquals(expected, actual). But, these are not ArrayLists.
		//So, we using Assert.assertTrue()
		//And as this is just validation and we are not printing anything, we won't get any output in conole for this. If it fails, then only this validation gives error
		
	}
}
