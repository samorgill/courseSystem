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
 * Servlet for getting a user specified course from the database
 *
 */

@WebServlet("/getCourse")
public class getCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
	public getCourse(){
		
	}
    
    private boolean isEmpty(String value) {
        return((value == null) || (value.trim().equals("")));
      }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 /*
		 * Creates instance of the courseDAO, populates an ArrayList
		 * with the results of the courseDAO.getCourse class
		 * sets the courseList attribute and passes the ArrayList
		 */
		
		response.setHeader("Cache-Control", "no-cache");
	    response.setHeader("Pragma", "no-cache");
		
		
		String courseName = request.getParameter("coursename");			
		ArrayList<Course> courses = courseDAO.getCourse(courseName);		
		request.setAttribute("courseList", courses);
    	
		//Using GSON to convert the arraylist into a JSON attribute 
    	Gson gson = new Gson();
    	String json = gson.toJson(courses);
    	request.setAttribute("json", json);
		
    	//For loop to convert the courses into a text attribute
     	for(int i = 0; i < courses.size(); i++){
    	String text = courses.get(i).toString();
    	request.setAttribute("text", text);}
    	
     	/* Gets the format specified by the user then sends the
    	 * results the corresponding formatting JSP
    	 */
		String format = request.getParameter("format");		
		String outputPage;		
		if ("xml".equals(format)) {
          response.setContentType("text/xml");
          outputPage = "/WEB-INF/results/course-xml.jsp";
        } else if ("json".equals(format)) {       	
          response.setContentType("application/json");
          outputPage = "/WEB-INF/results/course-json.jsp";
        } else if ("text".equals(format)){
          response.setContentType("text/plain");
          outputPage = "/WEB-INF/results/course-string.jsp";
        } else {
        	response.setContentType("application/json");
	        outputPage = "/WEB-INF/results/course-json.jsp";
        }
    	RequestDispatcher dispatcher =
    		      request.getRequestDispatcher(outputPage);
    		    dispatcher.include(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
