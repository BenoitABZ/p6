package com.benoit.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.benoit.dao.daoSession.DaoSession;
import com.benoit.dao.interfaceDao.ReservationDao;
import com.benoit.dao.interfaceDao.TopoDao;
import com.benoit.entities.Topo;
import com.benoit.forms.MajTopoForm;
import com.benoit.forms.TrouverTopoForm;

/**
 * Servlet implementation class InformationsTopo
 */
@WebServlet( "/ModifierTopo" )
public class ModifierTopo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_ID               = "idTopo";
	private static final String VUE_TOPO_INFO        = "/restreint/AfficherTopoAdherent.jsp";
	private static final String ATT_TOPO             = "topo";
	private static final String VUE_TOPO_LISTE       = "/p6/ListerTopos";
	private static final String ATT_FORM             = "form";
	TopoDao topoDao = null;
	ReservationDao reservationDao = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierTopo() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	DaoSession daoSession = new DaoSession();
    	this.topoDao = daoSession.getTopoDao();
    	this.reservationDao=daoSession.getReservationDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter(ATT_ID);
		
		TrouverTopoForm form = new TrouverTopoForm(topoDao);
		
		Topo topo = form.trouver(id);
			
		HttpSession session = request.getSession();
		
		session.setAttribute(ATT_TOPO, topo);
		request.setAttribute(ATT_TOPO, topo);
		
		
		
		this.getServletContext().getRequestDispatcher( VUE_TOPO_INFO ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MajTopoForm form = new MajTopoForm(topoDao, reservationDao);
		
		HttpSession session = request.getSession();
		
		Topo topo = form.majTopo(request, session);
		
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_TOPO, topo);
		
		 if ( form.getErreurs().isEmpty() ) {
			 
			 response.sendRedirect(VUE_TOPO_LISTE);
			 
		 }else {
			
			 this.getServletContext().getRequestDispatcher( VUE_TOPO_INFO ).forward( request, response );
			 
		 }
		
		
	}

}
