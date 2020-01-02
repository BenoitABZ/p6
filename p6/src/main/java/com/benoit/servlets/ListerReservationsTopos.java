package com.benoit.servlets;

import java.io.IOException;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.benoit.dao.daoSession.DaoSession;
import com.benoit.dao.interfaceDao.ReservationDao;
import com.benoit.dao.interfaceDao.TopoDao;
import com.benoit.entities.Commentaire;
import com.benoit.entities.Reservation;
import com.benoit.entities.Topo;
import com.benoit.forms.ListerReservationsToposForm;

@WebServlet( "/ListerReservationsTopos" )
public class ListerReservationsTopos extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	private static final String ATT_LIST_TOPO           = "listeTopo";
	private static final String ATT_FORM                = "form";
	private static final String VUE_RESERVATIONS_TOPOS  = "/restreint/ListerReservationsTopos.jsp";
	private static final String ATT_MAP_TOPOS           = "mapTopos";
	private static final String ATT_MAP_RESERVATIONS    = "mapReservations";
	TopoDao topoDao = null;
	ReservationDao reservationDao = null;

    public ListerReservationsTopos() {
        super();
     
    }
    
    public void init() {
        DaoSession daoSession = new DaoSession();
        this.topoDao = daoSession.getTopoDao();
        this.reservationDao = daoSession.getReservationDao();
        }

	@SuppressWarnings("rawtypes")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
        ListerReservationsToposForm form = new  ListerReservationsToposForm (reservationDao, topoDao);
        
        HttpSession session = request.getSession();

        List<Topo> topos = form.listerReservationsTopos(request, session);
        
        Map<Long, Topo> mapTopos = new HashMap<Long, Topo>();
        
        Map<Long, Reservation> mapReservations = new HashMap<Long, Reservation>();
        for (  Topo  topo : topos) {
        mapTopos.put( topo.getId(),topo );
             Set<Reservation> reservations = topo.getReservations();
             
             Iterator it = reservations.iterator();
            		 
            		 while (it.hasNext()) {
            			 
            			 Reservation reservation = (Reservation) it.next();
            			 
            			 mapReservations.put(reservation.getId(), reservation);
            		 }          		 
            	
        }	
                  
    	request.setAttribute( ATT_FORM, form );
    	
    	request.setAttribute( ATT_LIST_TOPO, topos );
    	
    	request.setAttribute( ATT_MAP_TOPOS, mapTopos );
    	
    	request.setAttribute( ATT_MAP_RESERVATIONS, mapReservations );
    	
    	
    	
	    this.getServletContext().getRequestDispatcher(VUE_RESERVATIONS_TOPOS ).forward( request, response );
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
