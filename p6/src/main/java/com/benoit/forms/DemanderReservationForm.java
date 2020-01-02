package com.benoit.forms;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.benoit.dao.classDao.DaoException;
import com.benoit.dao.interfaceDao.ReservationDao;
import com.benoit.dao.interfaceDao.TopoDao;
import com.benoit.entities.Adherent;
import com.benoit.entities.Reservation;
import com.benoit.entities.Topo;

public class DemanderReservationForm {
	
	private static final String CHAMP_ADHERENT  = "adherent";
	  
    private static final String CHAMP_TOPO = "topo";
    
    
    private String              resultat;
    
    private Map<String, String> erreurs         = new HashMap<String, String>();
    
    public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	private ReservationDao reservationDao = null;
	
	private TopoDao topoDao = null;
	
	public DemanderReservationForm (ReservationDao reservationDao, TopoDao topoDao) {
		
	 this.reservationDao=reservationDao;
	 
	 this.topoDao=topoDao;
	}
	
	
	public Reservation reserver(HttpServletRequest request, HttpSession session) {
	
		
		
		Reservation reservation = new Reservation();
		
		LocalDate date = LocalDate.now();
		
		String statutReservation = "En attente d'acceptation";
		
		Topo topo = (Topo) request.getAttribute(CHAMP_TOPO);
		
		Adherent adherent = (Adherent) session.getAttribute(CHAMP_ADHERENT);
		
		Set<Reservation> reservations = topo.getReservations();
		
		Integer numReservation = topo.getReservations().size() + 1;
	     
		
		
		
		reservation.setAdherent(adherent);
		
		reservation.setTopo(topo);
		
		reservation.setDateReservation(date);
		
		reservation.setStatutReservation(statutReservation);
		
		reservation.setNumeroReservation(numReservation);
		
		
		
		reservations.add(reservation);
		
		topo.setReservations(reservations);
		
		adherent.setReservation(reservation);
		
		try {
			
			if (erreurs.isEmpty()) {
				
				reservationDao.creer(reservation);
				
				topoDao.maj(topo);
				
				resultat = "Demande de réservation reussie"; }
			
				else {
					
				resultat = "erreur(s)?";
				
			}
			
			}catch(DaoException e) {
				
				  setErreur( "imprévu", "Erreur imprévue" );
				  
		          resultat = "Échec: une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
		          
		            e.printStackTrace();	
				}
			
		      return reservation;
				
			}
		
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }		
			
	}
		
		


