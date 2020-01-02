package com.benoit.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.benoit.dao.daoSession.DaoSession;
import com.benoit.dao.interfaceDao.TopoDao;
import com.benoit.entities.Topo;
import com.benoit.forms.SupprimerTopoForm;
import com.benoit.forms.TrouverTopoForm;

/**
 * Servlet implementation class SupprimerTopo
 */
@WebServlet( "/SupprimerTopo" )
public class SupprimerTopo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_ID                 = "id";
	private static final String ATT_TOPO               = "topo";
	private static final String VUE_TOPOS_ADHERENT     = "/p6/ListerTopoAdherent";
	private static final String ATT_FORM_S             = "formS";
	private static final String ATT_FORM_T             = "formT";
	TopoDao topoDao = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerTopo() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	DaoSession daoSession = new DaoSession();
    	this.topoDao = daoSession.getTopoDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter(ATT_ID);
		
		TrouverTopoForm formT = new TrouverTopoForm(topoDao);
		
		Topo topo = formT.trouver(id);
			
		HttpSession session = request.getSession();
		
		request.setAttribute(ATT_TOPO, topo);
		
		SupprimerTopoForm formS = new SupprimerTopoForm(topoDao);
		
		formS.supprimer(request, session);
		
		request.setAttribute(ATT_FORM_S, formS);
		
		request.setAttribute(ATT_FORM_T, formT);
		
		this.getServletContext().getRequestDispatcher( VUE_TOPOS_ADHERENT ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
