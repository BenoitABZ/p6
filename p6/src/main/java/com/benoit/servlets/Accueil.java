package com.benoit.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.benoit.entities.Adherent;
import com.benoit.forms.ConnexionForm;


@WebServlet( "/Accueil" )
public class Accueil extends HttpServlet {


	private static final long serialVersionUID = 1L;
	private static final String VUE_ACCUEIL    = "/index.jsp";
   

    public Accueil() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher( VUE_ACCUEIL ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	   
		doGet(request, response);
	}
}
