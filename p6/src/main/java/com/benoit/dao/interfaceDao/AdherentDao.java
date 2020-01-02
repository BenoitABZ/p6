package com.benoit.dao.interfaceDao;


import java.util.List;

import com.benoit.dao.classDao.DaoException;
import com.benoit.entities.Adherent;

public interface AdherentDao extends SuperInterfaceDao{
	
	Adherent trouver(String mail, String motDePasse) throws DaoException;
	
	List<Adherent> listerAdherent() throws DaoException;
	
	Adherent trouver(Long idLong) throws DaoException;
	
	Adherent trouver(String id) throws DaoException;
	
	Adherent trouverMail(String mail, String mdp, String chiffrement) throws DaoException;
	
	Adherent trouverMailTest(String mail) throws DaoException;
}
