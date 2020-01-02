package com.benoit.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.benoit.dao.daoSession.DaoSession;
import com.benoit.dao.interfaceDao.CommentaireDao;
import com.benoit.dao.interfaceDao.ReservationDao;
import com.benoit.dao.interfaceDao.TopoDao;
import com.benoit.entities.Commentaire;
import com.benoit.entities.Reservation;
import com.benoit.forms.AccepterReservationForm;
import com.benoit.forms.TrouverCommentaireForm;
import com.benoit.forms.TrouverReservationForm;


@WebServlet("/AccepterReservation" )
public class AccepterReservation extends HttpServlet {
	private static final long serialVersionUID            = 1L;
	private static final String ATT_ID                    = "idReservation";
	private static final String ATT_RESERVATION           = "reservation";
	private static final String ATT_FORM_T                = "formT";
	private static final String ATT_FORM_A                = "formA";
	private static final String VUE_CONFIRMATION_ACCEPTEE = "/ListerReservationsAcceptees";

	
	ReservationDao reservationDao = null;
	TopoDao topoDao = null;

    public AccepterReservation() {
        super();
   
    }
    
    public void init() {
    	DaoSession daoSession = new DaoSession();
    	this.reservationDao = daoSession.getReservationDao();
    	this.topoDao = daoSession.getTopoDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String id = request.getParameter(ATT_ID);
		
		TrouverReservationForm formT = new TrouverReservationForm(reservationDao);
		
		Reservation reservation = formT.trouverId(id);
		
		request.setAttribute(ATT_RESERVATION, reservation);
		
        AccepterReservationForm formA = new AccepterReservationForm(reservationDao, topoDao);
        		
        reservation = formA.accepter(request);	
        
        request.setAttribute(ATT_RESERVATION, reservation);
        request.setAttribute(ATT_FORM_T, formT);
        request.setAttribute(ATT_FORM_A, formA);
		
		this.getServletContext().getRequestDispatcher( VUE_CONFIRMATION_ACCEPTEE).forward( request, response );
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
