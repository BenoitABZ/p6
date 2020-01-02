package com.benoit.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
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
import com.benoit.dao.interfaceDao.AdherentDao;
import com.benoit.dao.interfaceDao.TopoDao;
import com.benoit.entities.Reservation;
import com.benoit.entities.Topo;
import com.benoit.forms.ConvertDateFrForm;
import com.benoit.forms.ListerToposAdherentForm;

@WebServlet( "/ListerToposAdherent" )
public class ListerToposAdherent extends HttpServlet {
	private static final long serialVersionUID            = 1L;
	private static final String VUE_TOPO                  = "/restreint/ListerToposAdherent.jsp";
	private static final String ATT_FORM                  = "form";
	private static final String ATT_LIST_TOPO             = "topos";
	private static final String ATT_LIST_RESERVATION      = "reservations";
	private static final String ATT_MAP                   = "mapTopos";
	private static final String ATT_FORMC                 = "formC";
	private static final String ATT_LIST_DATE             = "datesParution";
	TopoDao topoDao = null;
       
    public ListerToposAdherent() {
        super();
   
    }
    
    public void init() {
    	DaoSession daoSession = new DaoSession();
    	this.topoDao = daoSession.getTopoDao();
    }

	@SuppressWarnings("rawtypes")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ListerToposAdherentForm form = new ListerToposAdherentForm(topoDao);
		
		HttpSession session = request.getSession();
		
		List<Topo> topos = form.listerToposAdherent(request,session);
		
		   ConvertDateFrForm formC = new ConvertDateFrForm(); 
           
           Set<String> datesParution = formC.convertFRTopo(topos);
		
	      Map<Long, Topo> mapTopos = new HashMap<Long, Topo>();
	      
	      	Set<Reservation> reservationsSum = new HashSet<Reservation>();
	      
          for (  Topo  topo : topos) {
          mapTopos.put( topo.getId(),topo );
          Set<Reservation> reservations = topo.getReservations();
          
          Iterator it = reservations.iterator();
         		 
         		 while (it.hasNext()) {
         			 
         			 Reservation reservation = (Reservation) it.next();
         			 
         			 String statut = reservation.getStatutReservation();
         			 
         			 if(statut.equals("En attente d'acceptation")) {
         			 
         			 reservationsSum.add(reservation);
         			   }
         		 }   
          }
          
        request.setAttribute( ATT_MAP, mapTopos );
		
		request.setAttribute(ATT_FORM, form);
		
		request.setAttribute( ATT_FORMC, formC );
    	
		request.setAttribute(ATT_LIST_DATE, datesParution);
		
		request.setAttribute(ATT_LIST_TOPO, topos);
		
		request.setAttribute(ATT_LIST_RESERVATION, reservationsSum);
		
	    this.getServletContext().getRequestDispatcher(VUE_TOPO).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
