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

/**
 * Servlet implementation class AjouterSiteEscalade4
 */
@WebServlet( "/AjouterSiteEscalade4" )
public class AjouterSiteEscalade4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_SITE            = "siteEscalade";
	private static final String ATT_FORM            = "form";
	private static final String VUE_AJOUTER_SITE_5  = "/p6/AjouterSiteEscalade5";
	private static final String VUE_AJOUTER_SITE_4  = "/restreint/AjouterSiteEscalade4.jsp";;
    SiteEscaladeDao siteEscaladeDao = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterSiteEscalade4() {
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
		response.sendRedirect(request.getContextPath()+VUE_AJOUTER_SITE_4);
		
        System.out.println("doget");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AjouterSiteEscaladeForm form = new AjouterSiteEscaladeForm(siteEscaladeDao);
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		SiteEscalade siteEscalade = form.setDescriptionBeforeFinal(request, session);
		
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_SITE, siteEscalade);
		session.setAttribute(ATT_SITE, siteEscalade);
		
		if(form.getErreurs().isEmpty()) {
			
			response.sendRedirect( VUE_AJOUTER_SITE_5);
		} else {
			
	    this.getServletContext().getRequestDispatcher( VUE_AJOUTER_SITE_4 ).forward( request, response );
		}
		
		System.out.println("dopost");
		
	}
	}


