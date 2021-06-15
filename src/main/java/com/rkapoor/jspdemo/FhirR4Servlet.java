package com.rkapoor.jspdemo;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.rkapoor.jspdemo.PatientDataUtil;
/**
 * Servlet implementation class FhirR4Servlet
 */
@WebServlet("/FhirR4Servlet")
public class FhirR4Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PatientDataUtil obj =new PatientDataUtil();
	List<PatientClass> patients;
    /**
     * Default constructor. 
     */
    public FhirR4Servlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		String Patient=obj.getName();
//		String Dob=obj.getBirthDate();
//		String Address=obj.getAddress();
//		String Email=obj.getEmail();
//		System.out.println(Patient+" "+Dob);
//		request.setAttribute("Patient", Patient);
//		request.setAttribute("Dob", Dob);
//		request.setAttribute("Address", Address);
//		request.setAttribute("Email", Email);
		

		// step 2: add students to request object
		request.setAttribute("patient_list", patients);
		// step 3: get request dispatcher
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("viewPatientData.jsp");	
		
		// step 4: now forward to JSP
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// step 1: get the patient data from helper class (model)
		
		String feedback = request.getParameter("feedback");
		patients = obj.ParsePatientData(feedback);
		
		
		doGet(request, response);
	}

}
