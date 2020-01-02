package com.benoit.dao.interfaceDao;


import java.util.List;

import com.benoit.dao.classDao.DaoException;
import com.benoit.entities.Adherent;
import com.benoit.entities.Reservation;
import com.benoit.entities.Topo;

public interface ReservationDao extends SuperInterfaceDao {
	
	List<Reservation> trouver(Topo topo) throws DaoException;
	
	Reservation trouver(Adherent adherent) throws DaoException;
	
	Reservation trouver(String id) throws DaoException;
	
	List<Reservation> trouverAccept(String statut, Adherent adherent) throws DaoException;

}
