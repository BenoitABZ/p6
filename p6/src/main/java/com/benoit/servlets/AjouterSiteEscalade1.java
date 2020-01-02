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

@WebServlet( "/AjouterSiteEscalade1" )
public class AjouterSiteEscalade1 extends HttpServlet {
	private static final long serialVersionUID      = 1L;
	private static final String ATT_SITE            = "siteEscalade";
	private static final String ATT_FORM            = "form";
	private static final String VUE_AJOUTER_SITE_2  = "/p6/AjouterSiteEscalade2";
	private static final String VUE_AJOUTER_SITE_1  = "/p6/restreint/AjouterSiteEscalade1.jsp";
	private static final String VUE_AJOUTER_SITE_0  = "/restreint/AjouterSiteEscalade1.jsp";
    SiteEscaladeDao siteEscaladeDao = null;
 
    public AjouterSiteEscalade1() {
        super();
    
    }
    
    public void init() {
    	DaoSession daoSession = new DaoSession();
    	this.siteEscaladeDao = daoSession.getSiteEscaladeDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect(VUE_AJOUTER_SITE_1);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		AjouterSiteEscaladeForm form = new AjouterSiteEscaladeForm(siteEscaladeDao);
		
		HttpSession session = request.getSession();
		
		 request.setCharacterEncoding("UTF-8");
		
		SiteEscalade siteEscalade = form.setSecteurNdAdherentNdLieuForSite(request, session);
		
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_SITE, siteEscalade);
		
		if(form.getErreurs().isEmpty()) {
			
			response.sendRedirect( VUE_AJOUTER_SITE_2);
			
		} else {
			
			this.getServletContext().getRequestDispatcher( VUE_AJOUTER_SITE_0 ).forward( request, response );
		}		
		
	}

}
