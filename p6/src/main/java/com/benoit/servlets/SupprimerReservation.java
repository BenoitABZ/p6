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
import com.benoit.entities.Adherent;
import com.benoit.entities.Reservation;
import com.benoit.entities.Topo;
import com.benoit.forms.SupprimerReservationForm;
import com.benoit.forms.SupprimerTopoForm;
import com.benoit.forms.TrouverReservationForm;
import com.benoit.forms.TrouverTopoForm;

@WebServlet( "/SupprimerReservation" )
public class SupprimerReservation extends HttpServlet {
	private static final long serialVersionUID         = 1L;
	private static final String ATT_RESERVATION        = "reservation";
	private static final String VUE_RESERVATION        = "/p6/AfficherReservationAdherent";
	private static final String ATT_FORM_S             = "formS";
	private static final String ATT_FORM_T             = "formT";
	ReservationDao reservationDao = null;

    public SupprimerReservation() {
        super();
  
    }
    
    public void init() {
    	DaoSession daoSession = new DaoSession();
    	this.reservationDao = daoSession.getReservationDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		TrouverReservationForm formT = new TrouverReservationForm(reservationDao);
		
		Reservation reservation = formT.trouver(request, session);
		
		request.setAttribute(ATT_RESERVATION, reservation);
		
		SupprimerReservationForm formS = new SupprimerReservationForm(reservationDao);
		
		formS.supprimer(request, session);
		
		request.setAttribute(ATT_FORM_S, formS);
		
		request.setAttribute(ATT_FORM_T, formT);
		
		response.sendRedirect(VUE_RESERVATION);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
