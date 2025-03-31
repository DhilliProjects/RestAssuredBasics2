package authorize;

// we need to manually make it 'static' - to work with RestAssured
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.AddPlace;
import pojo.Location;

//here, we doing Serialization - Converting Java object into Request/Response payload(which is in Json/Xml format)- Using Java, we are creating Json
public class SerializeTest {

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
		
		ap.setLocation(l); //adding the values set in the methods of the Location class(which is done just before ) into the method i.e., setLocation of AddPlace class
		
		Response response = given().log().all().queryParam("key", "qaclick123")
				.body(ap) // the reference variable/java object 'ap' created with 'AddPlace' class is having the entire data/Requestbody
				.when().post("maps/api/place/add/json")
				.then().assertThat().statusCode(200).extract().response();
		
		String responseString = response.asString();
		System.out.println(responseString);
		
	}

}
