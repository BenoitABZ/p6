package com.benoit.forms;



import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import com.benoit.dao.classDao.DaoException;
import com.benoit.dao.interfaceDao.ReservationDao;
import com.benoit.dao.interfaceDao.TopoDao;
import com.benoit.entities.Reservation;
import com.benoit.entities.Topo;



public class AccepterReservationForm {
	

 
    private static final String CHAMP_RESERVATION = "reservation";
    
    Set<Reservation> reservations = null;
    
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
	
	public AccepterReservationForm (ReservationDao reservationDao, TopoDao topoDao) {
		
	 this.reservationDao=reservationDao;
	 
	 this.topoDao=topoDao;
	}
	
	@SuppressWarnings("rawtypes")
	public Reservation accepter(HttpServletRequest request) {
	
		
		String ok = "Acceptée";
		
		String notOk = "Refusée";
		
		Reservation reservationOk = (Reservation) request.getAttribute(CHAMP_RESERVATION);
		
		reservationOk.setStatutReservation(ok);
		
		Topo topo = reservationOk.getTopo();
		
		Reservation reservation = null;
		
		reservations= topo.getReservations();
		
		Iterator it = reservations.iterator();
		
		while(it.hasNext()) {
			
			reservation = (Reservation) it.next();
			
			if (!reservation.getStatutReservation().equals(ok)) reservation.setStatutReservation(notOk);		
			
		}
		
	
		topo.setDisponibilite("indisponible");
		topo.setReservations(reservations);
		
		
		try {
			
			if (erreurs.isEmpty()) {
				
				reservationDao.maj(reservation);
				
				topoDao.maj(topo);
				
				resultat = "Reservation acceptée"; }
			
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
		


