package com.benoit.forms;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.benoit.dao.classDao.DaoException;
import com.benoit.dao.daoSession.DaoSession;
import com.benoit.dao.interfaceDao.ReservationDao;
import com.benoit.dao.interfaceDao.TopoDao;
import com.benoit.entities.Adherent;
import com.benoit.entities.Reservation;
import com.benoit.entities.Topo;

public class MajTopoForm {
	
	    private static final String CHAMP_DISPONIBILITE   = "disponibilite";
	    private static final String CHAMP_ADHERENT        = "adherent";
	    private static final String CHAMP_DATE_PARUTION   = "dateParution";
	    private static final String CHAMP_NOM             = "nom";
	    private static final String CHAMP_TOPO            = "topo";
	    private static final String CHAMP_DESCRIPTION     = "description";
	    
	    private String              resultat;
	    
	    private Map<String, String> erreurs         = new HashMap<String, String>();
	    
	    public String getResultat() {
			return resultat;
		}

		public Map<String, String> getErreurs() {
			return erreurs;
		}

		
		
		private TopoDao topoDao = null; ReservationDao reservationDao = null;
		
		public MajTopoForm (TopoDao topoDao, ReservationDao reservationDao) {
			
		 this.topoDao=topoDao;
		 this.reservationDao = reservationDao;
		
		}
		
		
		public Topo majTopo(HttpServletRequest request, HttpSession session) {
			
			String date = getChamp(request, CHAMP_DATE_PARUTION);			
			String nom = getChamp(request, CHAMP_NOM);			
			String disponibilite = getChamp(request, CHAMP_DISPONIBILITE);
			String description = getChamp(request, CHAMP_DESCRIPTION);
			Topo topo = (Topo) session.getAttribute(CHAMP_TOPO);			
			Adherent adherent = (Adherent) session.getAttribute(CHAMP_ADHERENT);
			
			topo.setAdherent(adherent);
		    traiterDateParution(date, topo);
		    traiterNom(nom, topo);
		    traiterDisponibilite(disponibilite, topo);
		    traiterDescription(description, topo);
			
			try {
				
				if (erreurs.isEmpty()) {
					
					topoDao.maj(topo);
					
					resultat = "topo ajouté avec succés"; }
				
					else {
						
					resultat = "erreur(s)?";
					
				}
				
				}catch(DaoException e) {
					
					  setErreur( "imprévu", "Erreur imprévue lors de l'ajout." );
					  
			          resultat = "Échec de l'ajout : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			          
			            e.printStackTrace();	
					}
				
			      return topo;
					
				}
		
		private void traiterNom( String nom, Topo topo) {
	        try {
	            controler( nom,2);
	        } catch ( Exception  e ) {
	            setErreur( CHAMP_NOM, e.getMessage() );
	            
	        }
	        topo.setNom( nom );
		}
		
		@SuppressWarnings("rawtypes")
		private void traiterDisponibilite( String disponibilite, Topo topo) {
	        try {
	            
	            
	        } catch ( Exception  e ) {
	            setErreur( CHAMP_DISPONIBILITE, e.getMessage() );
	        }
	        
	        String dispoTopo = topo.getDisponibilite(); 
	        
	        topo.setDisponibilite( disponibilite );
	        
	        if (dispoTopo != disponibilite) {
	        	
	        	if (dispoTopo.equals("disponible")) {
	        			
	        		
	        		Set<Reservation> reservations = topo.getReservations();
	        		
	        		Iterator it = reservations.iterator();
	        				
	        				while(it.hasNext()) {
	        					
	        					Reservation reservation = (Reservation) it.next();
	        					
	        					reservation.setStatutReservation("Refusée");
	        					
	        					reservationDao.maj(reservation);
	        					
	        					reservations.remove(reservation);
	        					
	        					reservations.add(reservation);
	        					
	        					
	        				}
	        				
	        				topo.setReservations(reservations);
	        				
	        	}else if (dispoTopo.equals("indisponible")) {
	        		
	                Set<Reservation> reservations = topo.getReservations();
	        		
	        		Iterator it = reservations.iterator();
	        				
	        				while(it.hasNext()) {
	        					
	        					Reservation reservation = (Reservation) it.next();
	        					
	        					if (reservation.getStatutReservation().equals("Acceptée")) {
	        						
	        						
	        						
	        						reservations.remove(reservation);
	        				         
	        						
	        						reservationDao.supprimer(reservation);
	        						
	        						
	        						
	        						
	        					}
	      	        						        					
	        				}
	        				topo.setReservations(reservations);
	        	}
	   	        			        		
	        }
	        
	        
		}
	        
		private void traiterDateParution( String date, Topo topo) {
			
			LocalDate dateParution = null;
			
	        try {
	         
	            
	            dateParution = LocalDate.parse(date);
	            topo.setDateParution(dateParution);
	            
	        } catch ( Exception  e ) {
	            setErreur( CHAMP_DATE_PARUTION, e.getMessage() );
	        }
	        
	    }
		
		private void traiterDescription( String description, Topo topo) {
	        try {
	        	 controler(description, 20 );
	             topo.setDescription( description );
	            
	        } catch ( Exception  e ) {
	            setErreur( CHAMP_DESCRIPTION, e.getMessage() );
	        }
	      
		}
		
		private void controler(String parametre, int nbreCa) throws Exception {
			
			if (parametre == null || parametre.length() == 0) {
				
				throw new Exception ("Veuillez renseigner ce champ.");
		}
			else if (parametre.length()<nbreCa) {
				
				throw new Exception ("Ce champ doit contenir au moins " + nbreCa + " caractère(s).");
			}
		}
		
	
				
	      private void setErreur( String champ, String message ) {
	        erreurs.put( champ, message );
	    }
	    
		  private String getChamp (HttpServletRequest request, String parameter) {
				
			 String valeur = request.getParameter(parameter);
			
			 if(valeur == null) return null;
			
			 else {
				 
				 return valeur;
			 
			 }
			
			
			
			
				
			}

}
