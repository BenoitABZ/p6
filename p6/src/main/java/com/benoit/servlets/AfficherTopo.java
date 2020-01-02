package com.benoit.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.benoit.dao.daoSession.DaoSession;
import com.benoit.dao.interfaceDao.AdherentDao;
import com.benoit.dao.interfaceDao.SiteEscaladeDao;
import com.benoit.dao.interfaceDao.TopoDao;
import com.benoit.entities.Adherent;
import com.benoit.entities.SiteEscalade;
import com.benoit.entities.Topo;
import com.benoit.forms.ConvertDateFrForm;
import com.benoit.forms.TrouverAdherentForm;
import com.benoit.forms.TrouverSiteEscaladeForm;
import com.benoit.forms.TrouverTopoForm;

/**
 * Servlet implementation class AfficherTopo
 */
@WebServlet( "/AfficherTopo" )
public class AfficherTopo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_ID                   = "idTopo";
	private static final String ATT_FORM                 = "form";
	private static final String ATT_TOPO                 = "topo";
	private static final String VUE_SITE_ESCALADE        = "/restreint/AfficherTopo.jsp";
	private static final String ATT_ADHERENT             = "adherent";	
	private static final String ATT_DATE                 = "dateTopo";
	
	
	TopoDao topoDao = null;
	
	AdherentDao adherentDao = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfficherTopo() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	DaoSession daoSession = new DaoSession();
    	this.topoDao = daoSession.getTopoDao();
    	this.adherentDao = daoSession.getAdherentDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String id = request.getParameter(ATT_ID);
		
		TrouverTopoForm form = new TrouverTopoForm(topoDao);
		
		
		
		Topo topo = form.trouver(id);
		
	 	LocalDate date = topo.getDateParution();
    	
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-uuuu");
        
        String dateTopo = date.format(formatters);
		
		HttpSession session = request.getSession();
		
		Adherent adherent = (Adherent)session.getAttribute(ATT_ADHERENT);
		
		Long idAdherent = adherent.getId();
		
		
		
		request.setAttribute("idAdherent", idAdherent);
		
		TrouverAdherentForm formA = new TrouverAdherentForm(adherentDao);
		
		adherent = formA.trouver(request);
		
		session.setAttribute(ATT_ADHERENT, adherent);
			
		request.setAttribute(ATT_FORM, form);
  	
		request.setAttribute(ATT_DATE, dateTopo);
		
		request.setAttribute(ATT_TOPO, topo);
		
		session.setAttribute(ATT_TOPO, topo);
		
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
