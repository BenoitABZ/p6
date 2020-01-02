package com.benoit.dao.interfaceDao;

import java.util.List;

import com.benoit.dao.classDao.DaoException;

public interface SuperInterfaceDao {
	
	void creer(Object object) throws DaoException;
	
	void maj(Object object);
	
	void supprimer(Object object);

}
