package com.benoit.dao.interfaceDao;

import java.util.List;
import com.benoit.dao.classDao.DaoException;
import com.benoit.entities.Adherent;
import com.benoit.entities.Topo;

public interface TopoDao extends SuperInterfaceDao {
	
	List<Topo> trouver (Adherent adherent) throws DaoException;
	
	Topo trouver(String id);
	
	List<Topo> rechercher(String dateParution, String nom, String disponibilité, String critereTrieTopo);


}
