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

@WebServlet( "/AjouterSiteEscalade3" )
public class AjouterSiteEscalade3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_SITE            = "siteEscalade";
	private static final String ATT_FORM            = "form";
	private static final String VUE_AJOUTER_SITE_4  = "/p6/AjouterSiteEscalade4";
	private static final String VUE_AJOUTER_SITE_3  = "/restreint/AjouterSiteEscalade3.jsp";
    SiteEscaladeDao siteEscaladeDao = null;

    public AjouterSiteEscalade3() {
        super();
       
    }
     
    public void init() {
    	DaoSession daoSession = new DaoSession();
    	this.siteEscaladeDao = daoSession.getSiteEscaladeDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.sendRedirect(request.getContextPath() + VUE_AJOUTER_SITE_3);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AjouterSiteEscaladeForm form = new AjouterSiteEscaladeForm(siteEscaladeDao);
		
		HttpSession session = request.getSession();
		
		SiteEscalade siteEscalade = form.setAllForSite(request, session);
		
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_SITE, siteEscalade);
		session.setAttribute(ATT_SITE, siteEscalade);
		
		if(form.getErreurs().isEmpty()) {
			
			response.sendRedirect( VUE_AJOUTER_SITE_4);
		} else {
			
			this.getServletContext().getRequestDispatcher( VUE_AJOUTER_SITE_3 ).forward( request, response );
		}		
		
	}
	}


