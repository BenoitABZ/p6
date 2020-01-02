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
import com.benoit.dao.interfaceDao.SiteEscaladeDao;
import com.benoit.entities.Adherent;
import com.benoit.entities.SiteEscalade;
import com.benoit.forms.LabelSiteEscaladeForm;
import com.benoit.forms.TrouverAdherentForm;
import com.benoit.forms.UpgradeAdherentForm;

/**
 * Servlet implementation class LabelSiteEscalade
 */
@WebServlet( "/LabelSiteEscalade" )
public class LabelSiteEscalade extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_SITE_ESCALADE        = "siteEscalade";
	private static final String VUE_SITE_ESCALADE        = "/AfficherSiteEscalade.jsp";
	private static final String ATT_FORM                 = "form";
	SiteEscaladeDao siteEscaladeDao = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LabelSiteEscalade() {
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
		LabelSiteEscaladeForm form = new LabelSiteEscaladeForm (siteEscaladeDao);
		
		HttpSession session = request.getSession();
		
		SiteEscalade siteEscalade = form.labelliserSiteEscalade(request, session);
		
		session.setAttribute(ATT_SITE_ESCALADE, siteEscalade);
        request.setAttribute(ATT_FORM, form);
        
		
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
