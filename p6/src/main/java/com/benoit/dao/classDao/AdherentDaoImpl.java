package com.benoit.dao.classDao;



import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.benoit.dao.daoSession.DaoSession;
import com.benoit.dao.interfaceDao.AdherentDao;
import com.benoit.entities.Adherent;

public class AdherentDaoImpl extends SuperClassDao implements AdherentDao {

	
	@SuppressWarnings("unchecked")
	public Adherent trouver(String mail, String motDePasse) throws DaoException {
			
		    try {
		    	
		        Session session = DaoSession.newSession();

				Query<Adherent> query = session.createQuery(" From Adherent a where a.mail = :mail and a.motDePasse = :motDePasse ");
			      
			    query.setParameter("mail", mail);
			    
			    query.setParameter("motDePasse", motDePasse);
			    
			      
				Adherent adherent = (Adherent) query.uniqueResult();
				
				if (adherent==null) {
					
					throw new Exception("Mail ou Mot de passe non valides");
				}
							      
			    return adherent;
		    	
		    }catch(Exception e) {
		    	
		    	throw new DaoException(e);
		    }		    
		 
	  }
	
	@SuppressWarnings("unchecked")
	public Adherent trouverMailTest(String mail) throws DaoException {
		
		
		    try {
		    	
		        Session session = DaoSession.newSession();
			
				Query<Adherent> query = session.createQuery(" From Adherent a where a.mail = :mail ");
			      
			    query.setParameter("mail", mail);
     
				Adherent adherent = (Adherent) query.uniqueResult();

			    return adherent;
		    	
		    }catch(Exception e) {
		    	
		    	throw new DaoException(e);
		    }
				 
	  }
	
	@SuppressWarnings("unchecked")
	public Adherent trouverMail(String mail, String mdp, String chiffrement) throws DaoException {
		
		
		    try {
		    	
		        Session session = DaoSession.newSession();
				
				
				Query<Adherent> query = session.createQuery(" From Adherent a where a.mail = :mail");
			      
			    query.setParameter("mail", mail);
			    		      
				Adherent adherent = (Adherent) query.uniqueResult();
				 ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
	          	    passwordEncryptor.setAlgorithm( chiffrement );
	          	    passwordEncryptor.setPlainDigest( false );
	          	  
	        
	          		if (adherent==null || !passwordEncryptor.checkPassword(mdp, adherent.getMotDePasse())) {
						
						throw new Exception("Mail ou Mot de passe non valides");
					}
					
			      
			    return adherent;
		    	
		    }catch(Exception e) {
		    	
		    	throw new DaoException(e);
		    }		    
		 
	  }

	@SuppressWarnings("unchecked")
	public List<Adherent> listerAdherent() throws DaoException {
		
		try {
	    	
	        Session session = DaoSession.newSession();
			
	        boolean boo = false;
			
			Query<Adherent> query = session.createQuery(" From Adherent a WHERE a.membre = :boo");
			
			query.setParameter("boo", boo);
		           
		    List<Adherent> adherents = (List<Adherent>) query.list();
		    
		    session.close();
		      
		    return adherents;
	    	
	    }catch(Exception e) {
	    	
	    	throw new DaoException(e);
	    }
	

	}

	@SuppressWarnings("unchecked")
	public Adherent trouver(String idString) throws DaoException {
	
	try {
	    	
	        Session session = DaoSession.newSession();
	        
	        Long id = Long.parseLong(idString);
			
			Query<Adherent> query = session.createQuery(" From Adherent a WHERE a.id = :id");
			
			query.setParameter("id", id);
		           
		   Adherent adherent = (Adherent) query.uniqueResult();
		   	   
		   session.close();
		      
		    return adherent;
	    	
	    }catch(Exception e) {
	    	
	    	throw new DaoException(e);
	    }
	
	}
	
	@SuppressWarnings("unchecked")
	public Adherent trouver(Long id) throws DaoException {
		
	try {
	    	
	        Session session = DaoSession.newSession();
			
			Query<Adherent> query = session.createQuery(" From Adherent a WHERE a.id = :id");
			
			query.setParameter("id", id);
		           
		   Adherent adherent = (Adherent) query.uniqueResult();

		   
		   session.close();
		      
		    return adherent;
	    	
	    }catch(Exception e) {
	    	
	    	throw new DaoException(e);
	    }
	
	}

	
	
}
