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
import com.benoit.dao.interfaceDao.SiteEscaladeDao;
import com.benoit.dao.interfaceDao.TopoDao;
import com.benoit.entities.Reservation;
import com.benoit.entities.SiteEscalade;
import com.benoit.entities.Topo;
import com.benoit.forms.DemanderReservationForm;
import com.benoit.forms.TrouverSiteEscaladeForm;
import com.benoit.forms.TrouverTopoForm;

@WebServlet( "/DemanderReservation" )
public class DemanderReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_ID                          = "idTopo";
	private static final String VUE_CONFIRMATION_RESERVATION    = "/AfficherReservationAdherent";
	private static final String ATT_TOPO                        = "topo";
	private static final String ATT_FORM_T                      = "formT";
	private static final String ATT_FORM_R                      = "formR";
	private static final String ATT_RESERVATION                 = "reservation";
	TopoDao topoDao = null;
	ReservationDao reservationDao = null;

    public DemanderReservation() {
        super();
  
    }
    
    public void init() {
    	DaoSession daoSession = new DaoSession();
    	this.topoDao = daoSession.getTopoDao();
    	this.reservationDao = daoSession.getReservationDao();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
        String id = request.getParameter(ATT_ID);
		
		TrouverTopoForm formT = new TrouverTopoForm(topoDao);
		
		Topo topo = formT.trouver(id);
		
		request.setAttribute(ATT_TOPO, topo);
		
		DemanderReservationForm formR = new DemanderReservationForm(reservationDao, topoDao);
		
		HttpSession session = request.getSession();
		
		Reservation reservation = formR.reserver(request, session); 
			
		request.setAttribute(ATT_FORM_T, formT);
		request.setAttribute(ATT_FORM_R, formR);
		request.setAttribute(ATT_RESERVATION, reservation);
		
		this.getServletContext().getRequestDispatcher(  VUE_CONFIRMATION_RESERVATION  ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
