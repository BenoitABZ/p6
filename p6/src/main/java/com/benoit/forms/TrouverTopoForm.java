package com.benoit.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import com.benoit.dao.classDao.DaoException;
import com.benoit.dao.interfaceDao.TopoDao;
import com.benoit.entities.Topo;
public class TrouverTopoForm {
	

	private String              resultat;
    
    private Map<String, String> erreurs         = new HashMap<String, String>();
    
    public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	private TopoDao topoDao = null;
	
	public TrouverTopoForm (TopoDao topoDao) {
		
	 this.topoDao =topoDao ;
	}
	
	
	public Topo trouver(String id) {
		
        Topo topo = null;
		
		try {
								
				topo = topoDao.trouver(id);
				
				if(topo != null) {
				
				resultat = "Votre topo"; 
				
				}else {
					
			     resultat = "Topo introuvable"; 
					
				}
				
			
			}catch(DaoException e) {
				
				  setErreur( "imprévu", "Erreur imprévue" );
				  
		          resultat = "Échec: une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
		          
		            e.printStackTrace();	
				}
			
		      return topo;
				
			}
      private void setErreur( String champ, String message ) {
          erreurs.put( champ, message );
      }

}
