package coreservlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * 
 * @author Samuel Orgill 15118305
 * @version 5 24/3/2016
 * 
 * Servlet for getting inserting a course into the database
 *
 */

@WebServlet("/insertCourse")
public class insertCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
				
		try{
			

		response.setHeader("Cache-Control", "no-cache");
	    response.setHeader("Pragma", "no-cache");
	    
		//Instance of the DAO	
		courseDAO dao = new courseDAO();
		
		//Get's course parameters and sets variables 
		int courseID = Integer.parseInt(request.getParameter("courseid"));
	    String courseName = request.getParameter("coursename");
	    String ftOrPt = request.getParameter("ftorpt");
	    int courseCredits = Integer.parseInt(request.getParameter("coursecredits"));
	    int courseDuration = Integer.parseInt(request.getParameter("courseduration"));
	    int tutorID = Integer.parseInt(request.getParameter("tutorid"));
	    String courseTutor = request.getParameter("coursetutor");;
		
		//Creates a Course object and populates with above varaibles
		Course c = new Course(courseID, courseName, ftOrPt, courseCredits,
        		courseDuration, tutorID, courseTutor);
		
		//Set's Course object attributes with variables
		c.setCourseID(courseID);
		c.setCourseName(courseName);
		c.setFullOrPartTime(ftOrPt);
		c.setCourseCredits(courseCredits);
		c.setCourseDuration(courseDuration);
		c.setTutorID(tutorID);
		c.setCourseTutor(courseTutor);
		
		//Add's course object to the database via the courseDAO's insertCourse class
		dao.insertCourse(c);
		
		//Sets attribute and add's course object
		request.setAttribute("c", c);
    	
		//Creating a gson representation of the course object
    	Gson gson = new Gson();
    	String json = gson.toJson(c);
    	request.setAttribute("json", json);
    	
    	//Creating a text representation of the course object
    	String text = c.toString();
    	request.setAttribute("text", text);
    	
    	//Get's the format specified by the user and then sends
    	//the results to the corresponding JSP.
    	String format = request.getParameter("format");
        String outputPage;
        if ("xml".equals(format)) {
            response.setContentType("text/xml");
            outputPage = "/WEB-INF/results/insertXML.jsp";
          } else if ("json".equals(format)) {
          	
            response.setContentType("application/json");
            outputPage = "/WEB-INF/results/course-json.jsp";
          } else if ("text".equals(format)){
            response.setContentType("text/plain");
            outputPage = "/WEB-INF/results/insertText.jsp";
          } else {
          	response.setContentType("application/json");
  	        outputPage = "/WEB-INF/results/course-json.jsp";
          }
    	RequestDispatcher dispatcher =
    		      request.getRequestDispatcher(outputPage);
    		    dispatcher.include(request, response);
		
    		    System.out.println(c);
    		    System.out.println(c);
		
		}catch (Exception e){
			response.getWriter().append("Error inserting course");
		}
		} 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
