package com.benoit.dao.classDao;

import org.hibernate.Session;




import org.hibernate.Transaction;


import com.benoit.dao.daoSession.DaoSession;
import com.benoit.dao.interfaceDao.SuperInterfaceDao;


public class SuperClassDao implements SuperInterfaceDao {
	

	public void creer(Object object) {
		
	    Session session = DaoSession.newSession();
        
		Transaction tx = null; 
		    
		try { 
		      tx = session.beginTransaction(); 
		     
		      session.save(object);
		      
		      session.flush() ;
		      
		      tx.commit();
		      
		    } catch (Exception e) {
		    	
		      if (tx != null) {
		      tx.rollback();
		      
		      throw new DaoException ("probleme de creation");
		      
		      
		      }
		      
		    } finally { 
		    	
		    	session.close();	    	
	 
	}

	}



	public void maj(Object object) {
		
	    Session session = DaoSession.newSession();
        
		Transaction tx = null; 
		    
		try { 
		      tx = session.beginTransaction(); 
		     
		      session.saveOrUpdate(object);
		      
		      session.flush() ;
		      
		      tx.commit();
		      
		    } catch (Exception e) {
		    	
		      if (tx != null) {
		    
		      tx.rollback();
		      }
		      
		    } finally { 
		    	
    session.close();
    
	}
		
	}
	


	public void supprimer(Object object) {
		
	        Session session = DaoSession.newSession();
        
			Transaction tx = null; 
			    
			try { 
			      tx = session.beginTransaction(); 
			     
			      session.delete(object);
			      
			      session.flush();
			      
			      tx.commit();
			      
			    } catch (Exception e) {
			    	
			      if (tx != null) {
			      tx.rollback();
			      }
			      
			    } finally { 
			    	
	session.close();
		
}
			
}
	
}

