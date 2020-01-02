package com.benoit.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.benoit.dao.classDao.DaoException;
import com.benoit.dao.interfaceDao.ReservationDao;
import com.benoit.entities.Adherent;
import com.benoit.entities.Reservation;


public class TrouverReservationForm {
	
	private static final String CHAMP_ADHERENT = "adherent";

	private String              resultat;
    
    private Map<String, String> erreurs         = new HashMap<String, String>();
    
    public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	
	
	private ReservationDao reservationDao = null;
	
	public TrouverReservationForm (ReservationDao reservationDao) {
		
	 this.reservationDao =reservationDao ;
	}
	
	
	public Reservation trouver(HttpServletRequest request, HttpSession session) {
		
		Adherent adherent = (Adherent) session.getAttribute(CHAMP_ADHERENT);
		
        Reservation reservation = null;
		
		try {
			
			
				
				reservation = reservationDao.trouver(adherent);
				
				if(reservation != null) {
				
				resultat = "Votre reservation"; 
				
				}else {
					
			     resultat = "Vous n'avez pas de réservation "; 
					
				}
				
			
			}catch(DaoException e) {
				
				  setErreur( "imprévu", "Erreur imprévue" );
				  
		          resultat = "Échec: une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
		          
		            e.printStackTrace();	
				}
			
		      return reservation;
				
			}
	
        public  List<Reservation> trouverAccept(HttpServletRequest request, HttpSession session) {
		
		Adherent adherent = (Adherent) session.getAttribute(CHAMP_ADHERENT);
		
        List<Reservation> reservations = null;
        
        String statut = "Acceptée";
		
		try {		
				
				reservations = reservationDao.trouverAccept(statut, adherent);
				
				if(reservations.size()>0) {
				
				resultat = "Vos reservations acceptées"; 
				
				}else {
					
			    resultat = "Vous n'avez accepté aucune réservation"; 
					
				}
				
			
			}catch(DaoException e) {
				
				  setErreur( "imprévu", "Erreur imprévue" );
				  
		          resultat = "Échec: une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
		          
		            e.printStackTrace();	
				}
			
		      return reservations;
				
			}
	
	public Reservation trouverId(String id) {
	
		
        Reservation reservation = null;
		
		try {
			
			
				
				reservation = reservationDao.trouver(id);
				
				if(reservation != null) {
				
				resultat = "Votre reservation"; 
				
				}else {
					
			     resultat = "Pas de reservation"; 
					
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
