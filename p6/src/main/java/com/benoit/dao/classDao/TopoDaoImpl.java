package com.benoit.dao.classDao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.benoit.dao.daoSession.DaoSession;
import com.benoit.dao.interfaceDao.TopoDao;
import com.benoit.entities.Adherent;
import com.benoit.entities.SiteEscalade;
import com.benoit.entities.Topo;

public class TopoDaoImpl extends SuperClassDao implements TopoDao {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Topo> trouver (Adherent adherent) {
		
		  Session session = DaoSession.newSession();
		  
		    
				try { 		
				Long id = adherent.getId();
				
				Query query = session.createQuery("SELECT t From Topo t JOIN t.adherent a WHERE a.id = :id");
				   
			    query.setParameter("id", id);
			    
				List<Topo> topos= (List<Topo>) query.list();
			      
			    return topos;
			    
			    }
				
			    finally {
			    	
			    session.close();
			    
			    }
	}
	
	@SuppressWarnings("unchecked")
	public List<Topo> lister() {
		
		  Session session = DaoSession.newSession();
		  
		    
			try { 		
			@SuppressWarnings("rawtypes")
			
			
			Query query = session.createQuery("Select t From Topo t");
			   
		      
			List<Topo> topos= (List<Topo>) query.list();
		      
		    return topos;
		    
		    }
			
		    finally {
		    	
		    session.close();
		    
		    }
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Topo> rechercher(String dateParution, String nom, String disponibilite, String critereTrieTopo) {
		
		  Session session = DaoSession.newSession();
		  
	
		  
		
		  Criteria criteria = session.createCriteria(Topo.class);
		  
		    if (dateParution != null && dateParution != "") {
		    	
		     StringBuffer buffer = new StringBuffer(dateParution);
				  
		     buffer.append("-01-01");
				  
		     String dateParu = buffer.toString();
				  
		     LocalDate datePar = LocalDate.parse(dateParu);  
		    	
		     criteria.add(Restrictions.ge("dateParution", datePar));
		    }
		    
		    if (nom != null && nom != "") {
		      criteria.add(Restrictions.eq("nom", nom));
		    }
		    if (disponibilite != null && disponibilite != "") {
		      criteria.add(Restrictions.eq("disponibilite", disponibilite));
		    }
		    
		    List<Topo> resultat = null;
		    
		    if (critereTrieTopo != null && critereTrieTopo != "") {
		    	
		    resultat = criteria.addOrder(Order.asc(critereTrieTopo)).list(); 
		    
		    }else {
		    	
		    resultat = criteria.list();
		    	
		    	
		    }

		    return resultat;
		  }

	public Topo trouver(String idString) {
		
		  Session session = DaoSession.newSession();
		  
		  Long id = Long.parseLong(idString); 
			try { 		
			@SuppressWarnings("rawtypes")		
			
			Query query = session.createQuery("SELECT t From Topo t WHERE t.id = :id");
			   
		    query.setParameter("id", id);
		    
			Topo topo= (Topo)query.uniqueResult();
		      
		    return topo;
		    
		    }
			
		    finally {
		    	
		    session.close();
		    
		    }
		
	} 
	}

