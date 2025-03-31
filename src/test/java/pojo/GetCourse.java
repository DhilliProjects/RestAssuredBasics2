package pojo;


//This is the main class of Courses, which is the main class for WebAutomation, Api, and Mobile.
public class GetCourse {

	private String url;
	private String services;
	private String expertise;
	//As we are getting the 'course' variable from the 'Courses' class, we should use the class-name instead of String
	//In the same way, at the respected methods as well
	private Courses courses; //for courses, we are having nested json. So, we need to make sub classes for this and.
	//Sub class is Courses, and inside Courses, sub classes are WebAutomation, Api, and Mobile.
	private String instructor;
	private String linkedIn;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public Courses getCourses() {
		return courses;
	}
	public void setCourses(Courses courses) {
		this.courses = courses;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	
}
