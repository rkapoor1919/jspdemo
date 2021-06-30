package com.B1.FHIRWebApp;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditPatientServlet
 */
@WebServlet("/EditPatientServlet")
public class EditPatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EncodeResourceData encode =new EncodeResourceData();
	String data="";
	String id="";
    /**
     * Default constructor. 
     */
    public EditPatientServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// step 2: add students to request object
		request.setAttribute("patient", data);
		request.setAttribute("id", id);
		// step 3: get request dispatcher
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("editResource.jsp");	
		
		// step 4: now forward to JSP
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 boolean active=Boolean.parseBoolean(request.getParameter("active"));  
			PatientClass patient=new PatientClass(
					request.getParameter("id"),
					request.getParameter("idUse"),
					request.getParameter("idSystem"),
					request.getParameter("idValue"),
					active,
					request.getParameter("fname"),
					request.getParameter("gname"),
					request.getParameter("gender"),
					request.getParameter("address"),
					request.getParameter("city"),
					request.getParameter("state"),
					request.getParameter("postal"),
					request.getParameter("country"),
					request.getParameter("email"),
					request.getParameter("birthDate"),
					request.getParameter("phone"),
					request.getParameter("generalPractitioner")
					);
			System.out.println("ssssssssssssss"+request.getParameter("gender"));
			System.out.println("ssssssssssssss"+request.getParameter("idUse"));
			data=encode.EncodePatientData(patient);
			id=request.getParameter("id");
			doGet(request, response);
	}

}
