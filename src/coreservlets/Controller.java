package coreservlets;

import java.util.ArrayList;

/**
 * 
 * @author Samuel Orgill 15118305
 * @version 5 24/3/2016
 *
 */

/**  
 * Controller class for testing the programs ability to return and insert data.
 *
 */

public class Controller {
	
	public static void main(String[] args) {
		
		courseDAO dao = new courseDAO();
	

		/*
		 *  Inserts course into the database
		 *  Intialises variables, passes variables to a Course object,
		 *  then inserts object into the database via the courseDAO
		 */
		
		int courseID = 5550;
	    String courseName = "French History";
	    String ftOrPt = "FT";
	    int courseCredits = 360;
	    int courseDuration = 36;
	    int tutorID = 11110000;
	    String courseTutor = "Piere Durand";
		
		Course c = new Course(courseID, courseName, ftOrPt, courseCredits,
        		courseDuration, tutorID, courseTutor);
		
		c.setCourseID(courseID);
		c.setCourseName(courseName);
		c.setFullOrPartTime(ftOrPt);
		c.setCourseCredits(courseCredits);
		c.setCourseDuration(courseDuration);
		c.setTutorID(tutorID);
		c.setCourseTutor(courseTutor);
		
		courseDAO.insertCourse(c);
		
		System.out.println(c);
		
		
		
		 /* 
		  * Gets all courses from the database
		  * Populates an ArrayList from the courseDAO method,
		  * Prints the arraylist to the console
		  * 
		  */
		 
		
		ArrayList<Course> sList  = new ArrayList<Course>();
				
		sList =	dao.getAllCourses();
	
		for(int i = 0; i < sList.size(); i++ ){
		
		String list = sList.get(i).toString();	
		System.out.println(list);
		}
	
	
	/*
	 * Gets searched for course
	 * Passes arbitrary course name ("Chemistry") to the DAO,
	 * which searches the database and returns any course named
	 * "Chemistry" into the nameList ArrayList and then prints
	 * to the console.
	 */

	ArrayList<Course> nameList  = new ArrayList<Course>();
	
	nameList =	dao.getCourse("Chemistry");
	

	for(int i = 0; i < nameList.size(); i++ ){
	
	String list = nameList.get(i).toString();	
	System.out.println(list);
	}
	
}
	
}
