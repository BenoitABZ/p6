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


@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	private static final String VUE_SUCCES = "/p6/Connexion";
	private static final String VUE_FORM   = "/Inscription.jsp";	
	AdherentDao adherentDao = null;

    public Inscription() {
        super();
       
    }
     
    public void init() {
    	DaoSession daoSession = new DaoSession();
    	this.adherentDao = daoSession.getAdherentDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	    InscriptionForm form = new InscriptionForm(adherentDao);
		
	    request.setCharacterEncoding("UTF-8");
	    
		Adherent adherent = form.inscrire(request);
		request.setAttribute("adherent", adherent);
		request.setAttribute("resultat",form.getResultat());
		request.setAttribute("erreurs", form.getErreurs());
		
		if (form.getErreurs().isEmpty()) {

			response.sendRedirect(VUE_SUCCES);

		}
		
		else {
			
			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
	}

	}
	}


