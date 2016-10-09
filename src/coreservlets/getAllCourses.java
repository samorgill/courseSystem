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
import com.google.gson.JsonObject;

/**
 * 
 * @author Samuel Orgill 15118305
 * @version 5 24/3/2016
 * 
 * Servlet for getting all courses in the database
 *
 */


@WebServlet("/getAllCourses")
public class getAllCourses extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	 public getAllCourses() {
	    	
	        }

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 * 
		 */
	 @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		 /*
		 * Creates instance of the courseDAO, populates an ArrayList
		 * with the results of the courseDAO.getAllCourses class
		 * sets the courseList attribute and passes the ArrayList
		 */
		
		response.setHeader("Cache-Control", "no-cache");
	    response.setHeader("Pragma", "no-cache");
		 
		courseDAO dao = new courseDAO();
    	ArrayList<Course> courseList= new ArrayList<Course>();
    	courseList = dao.getAllCourses();
    	request.setAttribute("courseList", courseList);
    	
    	//Using GSON to convert the arraylist into a JSON attribute    	
    	Gson gson = new Gson();
    	String json = gson.toJson(courseList);
    	request.setAttribute("json", json);
    	
        //For loop to convert the courses into a text attribute
    	for(int i = 0; i < courseList.size(); i++){
    	String text = courseList.get(i).toString();
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

