package com.benoit.dao.classDao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.benoit.dao.daoSession.DaoSession;
import com.benoit.dao.interfaceDao.CommentaireDao;
import com.benoit.entities.Commentaire;
import com.benoit.entities.SiteEscalade;


public class CommentaireDaoImpl extends SuperClassDao implements CommentaireDao {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	
	public List<Commentaire> trouver(SiteEscalade siteEscalade) throws DaoException {
	
		Session session =null;
		
		try { 
		
		session = DaoSession.newSession();
		 	    
			Long id = siteEscalade.getId();
			
			
			Query query = session.createQuery("Select c From Commentaire c JOIN c.siteEscalade as s where s.id = :id");
			
			query.setParameter("id", id);	      
		   
		      
			List<Commentaire> commentaires = (List<Commentaire>) query.list();
		      
		    return commentaires;
		    
		    }catch(Exception e) {
		    	
		    	throw new DaoException (e);
		    }
			
		    finally {
		    	
		    session.close();
		    
		    }
		 
	  }

	@SuppressWarnings("rawtypes")
	public Commentaire trouver(String idString) throws DaoException {
		
	Session session =null;
		
		try { 
		
		    session = DaoSession.newSession();
		 	    
			Long id = Long.parseLong(idString);
			
			Query query = session.createQuery("Select c From Commentaire c where c.id = :id");
			
			query.setParameter("id", id);	      
		   
		      
			Commentaire commentaire = (Commentaire) query.uniqueResult();
		      
		    return commentaire;
		    
		    }catch(Exception e) {
		    	
		    	throw new DaoException (e);
		    }
			
		    finally {
		    	
		    session.close();
		    
		    }
		
	}	

}
