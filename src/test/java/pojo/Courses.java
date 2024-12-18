package pojo;

import java.util.List;

//Considering this class as a subclass for GetCourse
//means - as it is not simple json(it is a nested json) and there are ocurses inside this, to get into it, we created this .
//and again, for this 'Courses' class, we are creating some more classes to get into webAutomation, api, mobile.
//Refer to the classes 'WebAutomation', 'Api', 'Mobile'
public class Courses {

	//As we are getting the 'webAutomation' variable from the 'WebAutomation' class, we should use the class-name instead of String
	private List<WebAutomation> webAutomation;
	//and inside, it is an array of objects (with courseTitle and price). 
	//So, we are using 'List' (as List of elements).
	private List<Api> api;
	private List<Mobile> mobile;
	
	
	public List<WebAutomation> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<WebAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public List<Api> getApi() {
		return api;
	}
	public void setApi(List<Api> api) {
		this.api = api;
	}
	public List<Mobile> getMobile() {
		return mobile;
	}
	public void setMobile(List<Mobile> mobile) {
		this.mobile = mobile;
	}
	
	
}
