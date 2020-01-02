package com.benoit.dao.interfaceDao;

import java.util.List;

import com.benoit.dao.classDao.DaoException;
import com.benoit.entities.Adherent;
import com.benoit.entities.Commentaire;
import com.benoit.entities.Reservation;
import com.benoit.entities.SiteEscalade;

public interface CommentaireDao extends SuperInterfaceDao {
	
	public List<Commentaire> trouver(SiteEscalade siteEscalade) throws DaoException;
	
	Commentaire trouver(String id) throws DaoException;

}
