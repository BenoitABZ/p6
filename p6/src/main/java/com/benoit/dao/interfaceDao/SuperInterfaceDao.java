package com.benoit.dao.interfaceDao;

import com.benoit.dao.classDao.DaoException;

public interface SuperInterfaceDao {
	
	void creer(Object object) throws DaoException;
	
	void maj(Object object);
	
	void supprimer(Object object);

}
