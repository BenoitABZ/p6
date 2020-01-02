package com.benoit.servlets;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.benoit.dao.daoSession.DaoSession;
import com.benoit.dao.interfaceDao.AdherentDao;
import com.benoit.dao.interfaceDao.ReservationDao;
import com.benoit.entities.Adherent;
import com.benoit.entities.Reservation;
import com.benoit.forms.ConvertDateFrForm;
import com.benoit.forms.TrouverReservationForm;


@WebServlet( "/AfficherReservationAdherent" )
public class AfficherReservationAdherent extends HttpServlet {
	private static final long serialVersionUID   = 1L;
	private static final String VUE_RESERVATION  = "/restreint/AfficherReservationAdherent.jsp";
    private static final String ATT_RESERVATION  = "reservation";
	private static final String ATT_FORM         = "form";
	private static final String ATT_DATE         = "dateReservation";
    ReservationDao reservationDao = null;
       
 
    public AfficherReservationAdherent() {
        super();
        
    }
    
    public void init() {
    	DaoSession daoSession = new DaoSession();
    	this.reservationDao = daoSession.getReservationDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		TrouverReservationForm form = new TrouverReservationForm(reservationDao);
		
		HttpSession session = request.getSession();
		
		Reservation reservation = form.trouver(request, session);
		
		  ConvertDateFrForm formC = new ConvertDateFrForm(); 
	        
	        String dateReservation = formC.convertFRReservation(reservation);
		
		request.setAttribute(ATT_RESERVATION, reservation);
		
		request.setAttribute(ATT_DATE, dateReservation);
		
		request.setAttribute( ATT_FORM, form );
		
		this.getServletContext().getRequestDispatcher( VUE_RESERVATION ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
