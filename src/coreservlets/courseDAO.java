package coreservlets;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Samuel Orgill 15118305
 * @version 5 24/3/2016
 *
 */

/**
 * 
 * courseDAO is a Data Access Object with 3 classes to get all courses in the database,
 * getting a user specified course and inserting a course into the database
 *
 */

public class courseDAO {

	/*
	 *Database credentials refactored so that is only initiated once,
	 *rather than in each class.
	 */
	
	static Connection con = null;
    static PreparedStatement pst = null;
    static ResultSet rs = null;

    static String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:3306/orgills";
    static String user = "orgills";
    static String password = "Vrimbash4";
	
	/**
	 * getAllCourses returns all courses in the Database. 
	 * @return courseList
	 */
	
	public static ArrayList<Course> getAllCourses(){
		 
			ArrayList<Course> courseList = new ArrayList<Course>();


	        try {  Class.forName("com.mysql.jdbc.Driver").newInstance();
	        } catch (Exception e) {
	            System.out.println(e);
	        }

	        try {
	            
	            con = DriverManager.getConnection(url, user, password);
	            pst = con.prepareStatement("SELECT * FROM COURSE");
	            rs = pst.executeQuery();

	            while (rs.next()) {
	                
	                int courseID = rs.getInt(1);
	                String courseName = rs.getString(2);
	                String ftOrPt = rs.getString(3);
	                int courseCredits = rs.getInt(4);
	                int courseDuration = rs.getInt(5);
	                int tutorID = rs.getInt(6);
	                String courseTutor = rs.getString(7);
	                
	                Course c = new Course(courseID, courseName, ftOrPt, courseCredits,
	                		courseDuration, tutorID, courseTutor);
	          	  
	    	        courseList.add(c);
	    	        System.out.println(c.toString());
	                
	            }

	        } catch (SQLException ex) {
	                Logger lgr = Logger.getLogger(courseDAO.class.getName());
	                lgr.log(Level.SEVERE, ex.getMessage(), ex);

	        } finally {

	            try {
	                if (rs != null) {
	                    rs.close();
	                }
	                if (pst != null) {
	                    pst.close();
	                }
	                if (con != null) {
	                    con.close();
	                }

	            } catch (SQLException ex) {
	                Logger lgr = Logger.getLogger(courseDAO.class.getName());
	                lgr.log(Level.WARNING, ex.getMessage(), ex);
	            }
	        }
	        return courseList;
		 }
	
	/**
	 * getCourse returns an ArrayList of courses the user specifies
	 * @param course
	 * @return
	 */
	
	/* Recieves the course the user searches for, then searches for that course
	* in the database
	*/
	
	public static ArrayList<Course> getCourse(String course){
		ArrayList<Course> courseList = new ArrayList<Course>();
		
		course = course;

        try {  Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
                    	
        	con = DriverManager.getConnection(url, user, password);
            pst = con.prepareStatement("SELECT * FROM COURSE WHERE course_name = '" + course + "';");
            rs = pst.executeQuery();

            while (rs.next()) {
                
                int courseID = rs.getInt(1);
                String courseName = rs.getString(2);
                String ftOrPt = rs.getString(3);
                int courseCredits = rs.getInt(4);
                int courseDuration = rs.getInt(5);
                int tutorID = rs.getInt(6);
                String courseTutor = rs.getString(7);
                
                Course c = new Course(courseID, courseName, ftOrPt, courseCredits,
                		courseDuration, tutorID, courseTutor);
    	        
    	        courseList.add(c);
    	        
    	        System.out.println(c); 
                
            }

        } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(courseDAO.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(courseDAO.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
          
		return courseList;
	}
	
	/**
	 * insertCourse allows users to add a course to the database
	 * @param c
	 */
	
	/*
	 * Variables get the Course attributes then adds them to the database
	 * with a PreparedStatement 
	 */

	public static void insertCourse(Course c){
		int courseID = c.getCourseID();
        String courseName = c.getCourseName();
        String ftOrPt = c.getFullOrPartTime();
        int courseCredits = c.getCourseCredits();
        int courseDuration = c.getCourseDuration();
        int tutorID = c.getTutorID();
        String courseTutor = c.getCourseTutor();


        try {  Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            System.out.println(e);
        }

        try {

        	con = DriverManager.getConnection(url, user, password);
            
        	String insert = "INSERT INTO COURSE (course_id, course_name, FT_or_PT, course_credits, course_duration, tutor_id, course_tutor)" 
        			+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
        	
        	PreparedStatement prepState = con.prepareStatement(insert);
        	
        	prepState.setInt(1, courseID);
        	prepState.setString(2, courseName);
        	prepState.setString(3, ftOrPt);
        	prepState.setInt(4, courseCredits);
        	prepState.setInt(5, courseDuration);
        	prepState.setInt(6, tutorID);
        	prepState.setString(7, courseTutor);

        	prepState.execute();
        	
        	System.out.println(prepState);
            
        }catch (SQLException ex) {
            Logger lgr = Logger.getLogger(courseDAO.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

    } finally {

        try {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(courseDAO.class.getName());
            lgr.log(Level.WARNING, ex.getMessage(), ex);
        }      
        
	}
	}
}	 
		 

		 
		 
	

	
	

