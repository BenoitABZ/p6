package com.benoit.dao.classDao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.query.Query;


import com.benoit.dao.daoSession.DaoSession;
import com.benoit.dao.interfaceDao.ReservationDao;
import com.benoit.entities.Adherent;
import com.benoit.entities.Reservation;
import com.benoit.entities.Topo;

public class ReservationDaoImpl extends SuperClassDao implements ReservationDao {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Reservation> trouver(Topo topo) throws DaoException{
		  
		   Session session = null;
		   
		   try { 
		
		    session = DaoSession.newSession();
		  
				
			
			Long id = topo.getId();
			
			Query query = session.createQuery("SELECT r From Reservation r JOIN r.topo t WHERE t.id = :id");
			   
		    query.setParameter("id", id);
		    
			List<Reservation> reservations= (List<Reservation>) query.list();
		      
		    return reservations;
		    
             }catch(Exception e) {
		    	
		    	throw new DaoException (e);
		    
		    }
			
		    finally {
		    	
		    session.close();
		    
		    }
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Reservation> trouverAccept(String statut, Adherent adherent) throws DaoException{
		  
		   Session session = null; 
		   
		   try { 
		
		    session = DaoSession.newSession();
		  
			statut = "Acceptée";
			
			Long id = adherent.getId();
			
			Query query = session.createQuery("SELECT r From Reservation r JOIN r.topo as t JOIN t.adherent as a WHERE r.statutReservation = :statut AND a.id = :id");
			   
		    query.setParameter("statut", statut);
		    query.setParameter("id", id);
		    
			List<Reservation> reservations= (List<Reservation>) query.list();
		      
		    return reservations;
		    
             }catch(Exception e) {
		    	
		    	throw new DaoException (e);
		    
		    }
			
		    finally {
		    	
		    session.close();
		    
		    }
	}

	@SuppressWarnings("rawtypes")
	public Reservation trouver(Adherent adherent) throws DaoException{
		
	
		    Session session = null;
		    
		    try {
		
		    session = DaoSession.newSession();    
			 
				
			Long id = adherent.getId();
			
			
			Query query = session.createQuery("Select r From Reservation r JOIN r.adherent as a where a.id = :id");
			
			query.setParameter("id", id);
		      
		   
		      
			Reservation reservation = (Reservation) query.uniqueResult();
		      
		    return reservation;
		    
			}catch(Exception e) {
			    	
			  throw new DaoException (e);
			    
			    }
				
		    finally {
		    	if (session!= null) 
		    session.close();
		    
		    }
		 
	  }

	@SuppressWarnings("rawtypes")
	public Reservation trouver(String idString) throws DaoException {
		
	    Session session = null;
	    
	    try {
	
	    session = DaoSession.newSession();    
	    
	    Long id = Long.parseLong(idString);
		
		Query query = session.createQuery("Select r From Reservation r where r.id = :id");
		
		query.setParameter("id", id);
	        
	      
		Reservation reservation = (Reservation) query.uniqueResult();
	      
	    return reservation;
	    
		}catch(Exception e) {
		    	
		  throw new DaoException (e);
		    
		    }
			
	    finally {
	    	
	    session.close();
	    
	    }
	}	
	}


