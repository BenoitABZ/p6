package com.benoit.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.benoit.dao.daoSession.DaoSession;
import com.benoit.dao.interfaceDao.AdherentDao;
import com.benoit.entities.Adherent;
import com.benoit.forms.InscriptionForm;

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	private static final String VUE_SUCCES = "/p6/Connexion";
	private static final String VUE_FORM   = "/Inscription.jsp";	
	AdherentDao adherentDao = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inscription() {
        super();
        // TODO Auto-generated constructor stub
    }
     
    public void init() {
    	DaoSession daoSession = new DaoSession();
    	this.adherentDao = daoSession.getAdherentDao();
    }
    
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    InscriptionForm form = new InscriptionForm(adherentDao);
		
	    request.setCharacterEncoding("UTF-8");
	    
		Adherent adherent = form.inscrire(request);
		request.setAttribute("adherent", adherent);
		request.setAttribute("resultat",form.getResultat());
		request.setAttribute("erreurs", form.getErreurs());
		
		if (form.getErreurs().isEmpty()) {
			
			//List <Contact> contacts = form.recuperer();
			
			//request.setAttribute("contacts", contacts);
			
			response.sendRedirect(VUE_SUCCES);
			
			/*this.getServletContext().getRequestDispatcher(VUE_SUCCES).forward(request, response); */
		}
		
		else {
			
			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
	}

	}
	}


