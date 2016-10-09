package coreservlets;

/**
 * 
 * @author Samuel Orgill 15118305
 * @version 5 24/3/2016
 *
 */

/**
 * Java bean class for creating a Course object
 */

public class Course {
	
	private String courseName, courseTutor, fullOrPartTime;
	private int courseID, courseDuration, courseCredits, tutorID;
	
	/**
	 * 
	 * @param courseID
	 * @param courseName
	 * @param fullOrPartTime
	 * @param courseCredits
	 * @param courseDuration
	 * @param tutorID
	 * @param courseTutor
	 */
	
	public Course (int courseID, String courseName, String fullOrPartTime, int courseCredits, 
			int courseDuration, int tutorID, String courseTutor){
		
		setCourseID(courseID);
		setCourseName(courseName);
		setFullOrPartTime(fullOrPartTime);
		setCourseCredits(courseCredits);
		setCourseDuration(courseDuration);
		setTutorID(tutorID);
		setCourseTutor(courseTutor);
			
	}

	// Getters and setters for getting attribute values and setting them.
	
	public int  getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getFullOrPartTime() {
		return fullOrPartTime;
	}

	public void setFullOrPartTime(String fullOrPartTime) {
		this.fullOrPartTime = fullOrPartTime;
	}

	public int getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(int courseDuration) {
		this.courseDuration = courseDuration;
	}

	public String getCourseTutor() {
		return courseTutor;
	}

	public void setCourseTutor(String courseTutor) {
		this.courseTutor = courseTutor;
	}

	public int getTutorID() {
		return tutorID;
	}

	public void setTutorID(int tutorID) {
		this.tutorID = tutorID;
	}

	public int getCourseCredits() {
		return courseCredits;
	}

	public void setCourseCredits(int courseCredits) {
		this.courseCredits = courseCredits;
	}

	// Returns a String representation of the object
	
	 public String toString(){
	        String output;
	        output = courseID + " "+ courseName + " " + fullOrPartTime + " "+ courseCredits + " " 
	        + courseDuration + " " + tutorID + " " +  courseTutor;
	        return output;
	    }

	

}
