package authorize;

// we need to manually make it 'static' - to work with RestAssured
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
//Mainly, here we are practicing RequestSpecification and ResponseSpecification - RequestSpecBuilder and ResponseBuilder
public class SpecBuilderTest {

	//We are creating a Json format using Java objects
	public static void main(String[] args) {

		RestAssured.baseURI ="https://rahulshettyacademy.com";
		
		AddPlace ap = new AddPlace(); //Creating object for 'AddPlace' class to call the methods inside in it
		ap.setAccuracy(50);
		ap.setAddress("G5, Sklm");
		ap.setLanguage("Hindi");
		ap.setPhone_number("9876543411");
		ap.setWebsite("https://dhilliprojects.com");
		ap.setName("C Home");
		
		//For creating a Nested json
		List<String> myList = new ArrayList<String>(); //Created an ArrayList object to add String elements
		//and hence, we provided the variable type as List<String> in the 'AddPlace' class
		myList.add("park");
		myList.add("shopping");
		ap.setTypes(myList);
		
		//Another Nested Json
		Location l = new Location(); //Creating object for 'Location' class to call the methods in it
		l.setLat(-33.2522); //setting the values
		l.setLng(34.5627);
		
		ap.setLocation(l); 
		
		
		
		RequestSpecification request = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		
		RequestSpecification reqspec = given().spec(request).body(ap);

		//we can try another way too. Let's try it for ResponseSpecification
		ResponseSpecBuilder builder = new ResponseSpecBuilder();
		builder.expectStatusCode(200).expectContentType(ContentType.JSON);
		ResponseSpecification responseSpec = builder.build();
		
		Response res = reqspec.when().post("maps/api/place/add/json")
		.then().spec(responseSpec).extract().response();
		
		String responseString = res.asString();
		System.out.println(responseString);
		
	}

}
