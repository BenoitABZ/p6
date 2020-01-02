package com.benoit.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.benoit.dao.daoSession.DaoSession;
import com.benoit.dao.interfaceDao.SiteEscaladeDao;
import com.benoit.dao.interfaceDao.TopoDao;
import com.benoit.entities.Commentaire;
import com.benoit.entities.SiteEscalade;
import com.benoit.entities.Topo;
import com.benoit.forms.TrouverSiteEscaladeForm;
import com.benoit.forms.TrouverTopoForm;

/**
 * Servlet implementation class AfficherSiteEscalade
 */
@WebServlet( "/AfficherSiteEscalade" )
public class AfficherSiteEscalade extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_ID                   = "idSiteEscalade";
	private static final String ATT_FORM                 = "form";
	private static final String ATT_SITE_ESCALADE        = "siteEscalade";
	private static final String VUE_SITE_ESCALADE        = "/AfficherSiteEscalade.jsp";
	private static final String LIST_COMMENTAIRE         = "commentaires";
	
	SiteEscaladeDao siteEscaladeDao = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfficherSiteEscalade() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	DaoSession daoSession = new DaoSession();
    	this.siteEscaladeDao = daoSession.getSiteEscaladeDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String id = request.getParameter(ATT_ID);
		
		TrouverSiteEscaladeForm form = new TrouverSiteEscaladeForm(siteEscaladeDao);
		
		SiteEscalade siteEscalade = form.trouver(id);
		
	    Set<Commentaire> commentaires = siteEscalade.getCommentaires();
		
		HttpSession session = request.getSession();
			
		request.setAttribute(ATT_FORM, form);
		
		request.setAttribute(LIST_COMMENTAIRE, commentaires);
		
		session.setAttribute(ATT_SITE_ESCALADE, siteEscalade);
		
		this.getServletContext().getRequestDispatcher(VUE_SITE_ESCALADE).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
