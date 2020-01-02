package com.benoit.forms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.benoit.dao.classDao.DaoException;
import com.benoit.dao.interfaceDao.AdherentDao;
import com.benoit.entities.Adherent;

public class ListerAdherentForm {


    private String              resultat;
    
    private Map<String, String> erreurs         = new HashMap<String, String>();
    
    public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	
	
	private AdherentDao adherentDao = null;
	
	public ListerAdherentForm (AdherentDao adherentDao) {
		
	 this.adherentDao=adherentDao;
	}
	
	
	public List<Adherent> listerAdherents(HttpServletRequest request) {
			
		List<Adherent> adherents=null;
		
		try {
			
			
				
				adherents = adherentDao.listerAdherent();
				
				if(adherents.size()>0) {
				
				resultat = "Liste des adherents"; 
				
				}else {
					
			    resultat = "Aucun adherent non Admin"; 
					
				}
				
			
			}catch(DaoException e) {
				
				  setErreur( "imprévu", "Erreur imprévue" );
				  
		          resultat = "Échec: une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
		          
		            e.printStackTrace();	
				}
			
		      return adherents;
				
			}

     private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }


}
