package com.benoit.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.benoit.dao.classDao.DaoException;
import com.benoit.dao.interfaceDao.ReservationDao;
import com.benoit.dao.interfaceDao.TopoDao;
import com.benoit.entities.Reservation;
import com.benoit.entities.Topo;


public class ListerReservationsToposForm {
	
	private static final String CHAMP_TOPO  = "topo";
	
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
	
	public ListerReservationsToposForm (ReservationDao reservationDao, TopoDao topoDao) {
		
	 this.reservationDao=reservationDao;
	 this.topoDao = topoDao;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unused" })
	public List<Topo> listerReservationsTopos(HttpServletRequest request, HttpSession session) {
		
		
		List<Reservation> reservations=null;
		
		List<Topo> reservationsTopos = new ArrayList<Topo>();
		
		ListerToposAdherentForm form = new ListerToposAdherentForm(topoDao);
		
		try {
			
			
				List<Topo> topos = form.listerToposAdherent(request, session);
				
				Iterator iter = topos.iterator();
				
				while(iter.hasNext()) {
				
			    Topo topo = (Topo) iter.next();
				
				reservations = reservationDao.trouver(topo);
				if(reservations != null) {
				Iterator it = reservations.iterator();
						
						while (it.hasNext()) {
							
							
							Reservation reservation = (Reservation) it.next();
							
							if (reservation.getStatutReservation().equals("En attente d'acceptation")){
								
								reservationsTopos.add(topo);
								
								break;
						}
								
				
				}
						
				}
				}
				
				if (reservationsTopos.size()>0) {
					
					resultat = "Liste des demandes de reservation par topo"; 
					
				}else {
					
			     resultat = "Vous n'avez aucune demande réservation"; 
					
				}
			}catch(DaoException e) {
				
				  setErreur( "imprévu", "Erreur imprévue" );
				  
		          resultat = "Échec: une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
		          
		            e.printStackTrace();	
				}
		
		 return reservationsTopos;
		}
		     
				
			
      private void setErreur( String champ, String message ) {
          erreurs.put( champ, message );
      }
       		

}
