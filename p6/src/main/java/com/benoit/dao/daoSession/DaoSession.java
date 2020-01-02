package com.benoit.dao.daoSession;

import org.hibernate.*;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.benoit.dao.classDao.AdherentDaoImpl;
import com.benoit.dao.classDao.CommentaireDaoImpl;
import com.benoit.dao.classDao.ReservationDaoImpl;
import com.benoit.dao.classDao.SiteEscaladeDaoImpl;
import com.benoit.dao.classDao.TopoDaoImpl;
import com.benoit.dao.interfaceDao.AdherentDao;
import com.benoit.dao.interfaceDao.CommentaireDao;
import com.benoit.dao.interfaceDao.ReservationDao;
import com.benoit.dao.interfaceDao.SiteEscaladeDao;
import com.benoit.dao.interfaceDao.TopoDao;
import com.benoit.entities.Adherent;
import com.benoit.entities.Adresse;
import com.benoit.entities.Commentaire;
import com.benoit.entities.Reservation;
import com.benoit.entities.Secteur;
import com.benoit.entities.SiteEscalade;
import com.benoit.entities.Topo;
import com.benoit.entities.Voie;



public class DaoSession {
	
	
	public static SessionFactory buildSessionFactory() {
		
		return new Configuration().addAnnotatedClass(SiteEscalade.class).addAnnotatedClass(Secteur.class).addAnnotatedClass(Voie.class).addAnnotatedClass(Adherent.class).addAnnotatedClass(Commentaire.class).addAnnotatedClass(Reservation.class).addAnnotatedClass(Topo.class).addAnnotatedClass(Adresse.class).configure().buildSessionFactory();
		
	}
	
	public static Session newSession() {
		
		Session session = buildSessionFactory().openSession(); 
		    
		    return session;
	}
	
	public static Session getSession() {
		
		Session session = buildSessionFactory().getCurrentSession(); 
		    
		    return session;
	}
		   
	  public AdherentDao getAdherentDao() {
	        return new AdherentDaoImpl();
	    }
	  
	  public TopoDao getTopoDao() {
	        return new TopoDaoImpl();
	    }
	  
	  public SiteEscaladeDao getSiteEscaladeDao() {
	        return new SiteEscaladeDaoImpl();
	    }
	  
	  public ReservationDao getReservationDao() {
	        return new ReservationDaoImpl();
	    }
	  
	  public CommentaireDao getCommentaireDao() {
	        return new CommentaireDaoImpl();
	    }
	  
	  
}
