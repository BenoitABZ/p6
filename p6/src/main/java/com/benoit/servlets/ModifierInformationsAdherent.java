package com.benoit.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.benoit.dao.daoSession.DaoSession;
import com.benoit.dao.interfaceDao.AdherentDao;
import com.benoit.entities.Adherent;
import com.benoit.entities.Adresse;
import com.benoit.forms.MajAdherentForm;

/**
 * Servlet implementation class ModifierAdherent
 */
@WebServlet( "/ModifierInformationsAdherent" )
public class ModifierInformationsAdherent extends HttpServlet {
	private static final long serialVersionUID      = 1L;	
	private static final String VUE_SUCCES          = "/p6/Connexion";
	private static final String VUE_FORM1            = "/p6/restreint/ModifierInformationsAdherent.jsp";
	private static final String VUE_FORM2            = "/restreint/ModifierInformationsAdherent.jsp";
	AdherentDao adherentDao = null;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierInformationsAdherent() {
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
		response.sendRedirect(VUE_FORM1);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    MajAdherentForm form = new MajAdherentForm(adherentDao);
		
	    HttpSession session = request.getSession();
	    
		Adherent adherent = form.majAdherent(request, session);
		
		Adresse adresse=adherent.getAdresse();
		
		System.out.println(adresse.getLibelleVoie());
		request.setAttribute("adherent", adherent);
		request.setAttribute("form",form);
		
		
		if (form.getErreurs().isEmpty()) {
			
			//List <Contact> contacts = form.recuperer();
			
			//request.setAttribute("contacts", contacts);
			
			response.sendRedirect(VUE_SUCCES);
			
			/*this.getServletContext().getRequestDispatcher(VUE_SUCCES).forward(request, response); */
		}
		
		else {
			
			this.getServletContext().getRequestDispatcher(VUE_FORM2).forward(request, response);
	}

	}
	}


