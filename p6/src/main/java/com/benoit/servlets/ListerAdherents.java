package com.benoit.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.benoit.dao.daoSession.DaoSession;
import com.benoit.dao.interfaceDao.AdherentDao;
import com.benoit.entities.Adherent;
import com.benoit.forms.ListerAdherentForm;


/**
 * Servlet implementation class ListerAdherent
 */
@WebServlet( "/ListerAdherents" )
public class ListerAdherents extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_LIST             = "listeAdherents";
	private static final String ATT_FORM             = "form";
	private static final String VUE_ADHERENT         = "/restreint/ListerAdherents.jsp";
	private static final String ATT_MAP              = "mapAdherents";
	AdherentDao adherentDao = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListerAdherents() {
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
	        ListerAdherentForm form = new ListerAdherentForm(adherentDao);
	       
	       /* Traitement de la requête et récupération du bean en résultant */
	        List<Adherent> adherents = form.listerAdherents(request);
	        
	   	    Map<Long, Adherent> mapAdherents = new HashMap<Long, Adherent>();
	        for ( Adherent adherent : adherents) {
	        mapAdherents.put( adherent.getId(), adherent);
	     }

	    	request.setAttribute( ATT_FORM, form );
	    	
	    	request.setAttribute( ATT_MAP, mapAdherents );
	    	
	    	request.setAttribute( ATT_LIST, adherents);
	    	
		    this.getServletContext().getRequestDispatcher(VUE_ADHERENT).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
