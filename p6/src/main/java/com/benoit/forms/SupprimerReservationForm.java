package com.benoit.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.benoit.dao.classDao.DaoException;
import com.benoit.dao.interfaceDao.CommentaireDao;
import com.benoit.dao.interfaceDao.ReservationDao;
import com.benoit.dao.interfaceDao.TopoDao;
import com.benoit.entities.Adherent;
import com.benoit.entities.Reservation;
import com.benoit.entities.Topo;

public class SupprimerReservationForm {
	
	private static final String CHAMP_RESERVATION  = "reservation";

	private static final String CHAMP_ADHERENT     = "adherent";
	
	 private String              resultat;
	    
	    private Map<String, String> erreurs         = new HashMap<String, String>();
	    
	    public String getResultat() {
			return resultat;
		}

		public Map<String, String> getErreurs() {
			return erreurs;
		}

		
		
		private ReservationDao reservationDao = null;
		
		public SupprimerReservationForm (ReservationDao reservationDao) {
			
		 this.reservationDao = reservationDao;
		 
		}
		 
		 public void supprimer (HttpServletRequest request, HttpSession session) {
		 
		Reservation reservation = (Reservation) request.getAttribute(CHAMP_RESERVATION); 
		
		Adherent adherent = (Adherent)session.getAttribute(CHAMP_ADHERENT);
		 
		 try {
				
				if (erreurs.isEmpty()) {
					
					reservationDao.supprimer(reservation);
					
					Reservation reservationSuppr = null;
					
					adherent.setReservation(reservationSuppr);
					
					resultat = "reservation supprimé"; }
				
					else {
						
					resultat = "erreur(s)?";
					
				}
				
				}catch(DaoException e) {
					
					  setErreur( "imprévu", "Erreur imprévue lors de la suppression" );
					  
			          resultat = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			          
			            e.printStackTrace();	
					}
				
			     
					
				}
				
	      private void setErreur( String champ, String message ) {
	        erreurs.put( champ, message );
	    }
		

}



