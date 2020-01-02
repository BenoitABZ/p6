package com.benoit.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.benoit.dao.daoSession.DaoSession;
import com.benoit.dao.interfaceDao.SiteEscaladeDao;
import com.benoit.entities.SiteEscalade;
import com.benoit.forms.AjouterSiteEscaladeForm;

@WebServlet( "/AjouterSiteEscalade6" )
public class AjouterSiteEscalade6 extends HttpServlet {
	private static final long serialVersionUID      = 1L;
	private static final String ATT_SITE            = "siteEscalade";
	private static final String ATT_FORM            = "form";
	private static final String VUE_LIST            = "/p6/ListerSitesEscalade";
	private static final String VUE_AJOUTER_SITE_6  = "/WEB-INF/AjouterSite6.jsp";
    SiteEscaladeDao siteEscaladeDao = null;
       
 
    public AjouterSiteEscalade6() {
        super();
    
    }
    
    public void init() {
    	DaoSession daoSession = new DaoSession();
    	this.siteEscaladeDao = daoSession.getSiteEscaladeDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher( VUE_AJOUTER_SITE_6 ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
AjouterSiteEscaladeForm form = new AjouterSiteEscaladeForm(siteEscaladeDao);
		
		HttpSession session = request.getSession();
		
		SiteEscalade siteEscalade = form.creerSite(request, session);
		
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_SITE, siteEscalade);
		
		if(form.getErreurs().isEmpty()) {
			
			response.sendRedirect(VUE_LIST);
		} else {
			
	    this.getServletContext().getRequestDispatcher(VUE_AJOUTER_SITE_6).forward( request, response );
		}
	}

}
