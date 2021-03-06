package com.benoit.dao.classDao;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.*;
import org.hibernate.query.Query;


import com.benoit.dao.daoSession.DaoSession;
import com.benoit.dao.interfaceDao.SiteEscaladeDao;
import com.benoit.entities.Secteur;
import com.benoit.entities.SiteEscalade;
import com.benoit.entities.Voie;
import com.benoit.forms.CalculerSiteEscaladeForm;




public class SiteEscaladeDaoImpl extends SuperClassDao implements SiteEscaladeDao{
		
	@SuppressWarnings("unchecked")
	public Set<SiteEscalade> lister() throws DaoException{
		
	  Session session = DaoSession.newSession();
	  
	  Transaction tx = null; 
		try { 		
	
		 
			tx = session.beginTransaction(); 
		
		Query query = session.createQuery("Select se From SiteEscalade se ORDER BY se.nom asc");
		   
	      
		List<SiteEscalade> siteEscaladesList= (List<SiteEscalade>) query.list();
	    
		 Set<SiteEscalade> siteEscalades = new HashSet<SiteEscalade>(); 
	        for (SiteEscalade siteEscalade : siteEscaladesList) {
	        	
	        	siteEscalades.add(siteEscalade); 
	        }
	            
	        
	      session.flush() ;
	      
	      tx.commit();
		
	
	      
	    return siteEscalades;
	    
	    }
		
	    finally {
	    	
	    session.close();
	    
	    }
		
	}
	
	@SuppressWarnings("rawtypes")
	public SiteEscalade trouver(String idString) throws DaoException{
		
		  Session session = DaoSession.newSession();
		  
		  Long id = Long.parseLong(idString);
		  
			try { 		
			Query query = session.createQuery("Select se From SiteEscalade se where se.id = :id");
			
			query.setParameter("id", id);
			   
		      
			SiteEscalade se = (SiteEscalade) query.uniqueResult();
		      
		    return se;
		    
		    }
			
		    finally {
		    	
		    session.close();
		    
		    }
			
		}
	
	@SuppressWarnings("rawtypes")
	public SiteEscalade trouverNom(String nom) throws DaoException{
		
		  Session session = DaoSession.newSession();
		  
		  
		  
			try { 		
			Query query = session.createQuery("Select se From SiteEscalade se where se.nom = :nom");
			
			query.setParameter("nom", nom);
			   
		      
			SiteEscalade se = (SiteEscalade) query.uniqueResult();
		      
		    return se;
		    
		    }
			
		    finally {
		    	
		    session.close();
		    
		    }
			
		}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Set<SiteEscalade> rechercher(Integer secteurMin, Integer secteurMax, Integer cotationMin, Integer cotationMax, String departement, String critereTrieSite) throws DaoException{
			

			  Session session = DaoSession.newSession();

			  System.out.println(cotationMin);
			  
			    Map<String, Object> parametres = new HashMap<String, Object>();
			    
			    boolean premiereClause = true;
			    
			    StringBuffer requeteBuffer = new StringBuffer(" SELECT se " + " FROM SiteEscalade se " + " JOIN se.secteurs secteur " + " JOIN secteur.voies voie ");

			    if(departement!=null) {
			      requeteBuffer.append(premiereClause ? "where " : " and ");
			      requeteBuffer.append("se.departement = :departement");
			      parametres.put("departement", departement);			      
			      premiereClause = false;
			    }
			    
			    if(secteurMin!=null) {
				      requeteBuffer.append(premiereClause ? "where " : " and ");
				      requeteBuffer.append("SIZE(secteur) >= :secteurMin");
				      parametres.put("secteurMin", secteurMin);
				      premiereClause = false;
				    }
			    
			    if(secteurMax!=null) {
				      requeteBuffer.append(premiereClause ? "where " : " and ");
				      requeteBuffer.append("SIZE(secteur) <= :secteurMax");
				      parametres.put("secteurMax", secteurMax);
				      premiereClause = false;
				    }

			    if(critereTrieSite!=null) {
				      requeteBuffer.append(premiereClause ? "where " : " ");
				      requeteBuffer.append("ORDER BY :critereTrieSite asc");
				      parametres.put("critereTrieSite", critereTrieSite);
				      premiereClause = false;
				   } 
			    
			    String requeteHql = requeteBuffer.toString();
			    
			    try {

			    Query query = session.createQuery(requeteHql);
			    if(departement!=null) {
			    query.setParameter("departement", departement);
			    }
			    query.setParameter("secteurMin", secteurMin);
			    query.setParameter("secteurMax", secteurMax);
			    query.setParameter("critereTrieSite", critereTrieSite);
			    
	
			       
				List<SiteEscalade> siteEscaladesList= (List<SiteEscalade>) query.list();
			    
				 Set<SiteEscalade> se = new HashSet<SiteEscalade>(); 
			        for (SiteEscalade siteEscalade : siteEscaladesList) {
			        	
			        	se.add(siteEscalade); 
			        }
			        
			        CalculerSiteEscaladeForm calcul = new CalculerSiteEscaladeForm();
		            
	                Map<SiteEscalade, Integer>mapCotationMax = calcul.trouverCotationMaxObject(se);
		            
		            Map<SiteEscalade, Integer>mapCotationMin = calcul.trouverCotationMinObject(se);
		          
		            Set<SiteEscalade> sitesEscaladeTries = new HashSet<>();
		            
		        	Iterator<Entry<SiteEscalade, Integer>> itmax = mapCotationMax.entrySet().iterator();
		    		while (itmax.hasNext()) {
		    			Map.Entry<SiteEscalade, Integer> max = (Map.Entry<SiteEscalade, Integer>) itmax.next();

		    			
		    			Iterator<Entry<SiteEscalade, Integer>> itmin = mapCotationMin.entrySet().iterator();
			    		while (itmin.hasNext()) {
			    			Map.Entry<SiteEscalade, Integer> min = (Map.Entry<SiteEscalade, Integer>) itmin.next();

			    			
			    			if(min.getKey().equals(max.getKey())){
			    				
			    				if (min.getValue()>=cotationMin && max.getValue()<=cotationMax) {
			    					
			    					sitesEscaladeTries.add(min.getKey());
			    				}
			    				
			    			}
		    		}
		            
		    		}  
			
					return sitesEscaladeTries;
			
			      } finally {
			    	  
			        session.close();
			      }
			
	

			   
			  } 

}
