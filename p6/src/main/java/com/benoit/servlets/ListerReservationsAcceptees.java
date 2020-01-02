package com.benoit.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.benoit.dao.daoSession.DaoSession;
import com.benoit.dao.interfaceDao.ReservationDao;
import com.benoit.dao.interfaceDao.TopoDao;
import com.benoit.entities.Reservation;
import com.benoit.forms.TrouverReservationForm;

/**
 * Servlet implementation class ListerReservationsAcceptees
 */
@WebServlet( "/ListerReservationsAcceptees" )
    public class ListerReservationsAcceptees extends HttpServlet {
	private static final long serialVersionUID              = 1L;	
	private static final String ATT_LIST                    = "listeReservations";
	private static final String ATT_FORM                    = "form";
	private static final String VUE_RESERVATIONS_ACCEPTEES  = "/restreint/ListerReservationsAcceptees.jsp";
	TopoDao topoDao = null;
	ReservationDao reservationDao = null;
       
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListerReservationsAcceptees() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
        DaoSession daoSession = new DaoSession();
        this.topoDao = daoSession.getTopoDao();
        this.reservationDao = daoSession.getReservationDao();
        }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		    TrouverReservationForm form = new  TrouverReservationForm(reservationDao);
	        
	        HttpSession session = request.getSession();

	       /* Traitement de la requête et récupération du bean en résultant */
	        List<Reservation> reservations = form.trouverAccept(request, session);

	 
	    	request.setAttribute( ATT_FORM, form );
	    	
	    	request.setAttribute( ATT_LIST, reservations );
	    	
		    this.getServletContext().getRequestDispatcher(VUE_RESERVATIONS_ACCEPTEES ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
