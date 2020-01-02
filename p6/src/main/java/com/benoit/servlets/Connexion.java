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
import com.benoit.forms.ConnexionForm;

@WebServlet( "/Connexion" )
public class Connexion extends HttpServlet {
	private static final long  serialVersionUID = 1L;
    public static final String ATT_ADHERENT     = "adherent";
    public static final String ATT_FORM         = "form";
    public static final String VUE_CONNEXION    = "/Connexion.jsp";
    public static final String VUE_ACCUEIL      = "/p6/Accueil";
    AdherentDao adherentDao = null;

    public Connexion() {
        super();
       
    }
    
    public void init() {
    	DaoSession daoSession = new DaoSession();
    	this.adherentDao = daoSession.getAdherentDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		this.getServletContext().getRequestDispatcher( VUE_CONNEXION ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
        ConnexionForm form = new ConnexionForm(adherentDao);

        Adherent adherent = form.connecterAdherent( request );

        HttpSession session = request.getSession();

        if ( form.getErreurs().isEmpty() ) {
            session.setAttribute( ATT_ADHERENT, adherent );
          
            
            response.sendRedirect(VUE_ACCUEIL);
            
        } else {
            session.setAttribute( ATT_ADHERENT, null );

            request.setAttribute( ATT_FORM, form );

            this.getServletContext().getRequestDispatcher( VUE_CONNEXION ).forward( request, response );
        }


	}

}
