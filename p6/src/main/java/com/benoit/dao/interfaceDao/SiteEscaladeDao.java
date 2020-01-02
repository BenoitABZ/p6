package com.benoit.dao.interfaceDao;


import java.util.Set;

import com.benoit.dao.classDao.DaoException;
import com.benoit.entities.SiteEscalade;


public interface SiteEscaladeDao extends SuperInterfaceDao{
	
	SiteEscalade trouver(String id) throws DaoException;
	
	Set<SiteEscalade> lister();
	
	Set<SiteEscalade> rechercher(Integer secteurMin, Integer secteurMax, Integer cotationMin, Integer cotationMax, String lieu, String critereTrieSite);
	
	SiteEscalade trouverNom(String nom) throws DaoException;


}
	
