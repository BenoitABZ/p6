package com.benoit.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.benoit.dao.classDao.DaoException;
import com.benoit.dao.interfaceDao.SiteEscaladeDao;
import com.benoit.entities.SiteEscalade;

public class TrouverSiteEscaladeForm {
	
private String              resultat;
    
    private Map<String, String> erreurs         = new HashMap<String, String>();
    
    public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	private SiteEscaladeDao siteEscaladeDao = null;
	
	public TrouverSiteEscaladeForm (SiteEscaladeDao siteEscaladeDao) {
		
	 this.siteEscaladeDao  =siteEscaladeDao ;
	}
	
	
	public SiteEscalade trouver(String id) {
		
		SiteEscalade siteEscalade = null;
		
		try {
					
			    siteEscalade = siteEscaladeDao.trouver(id);
				
				if(siteEscalade != null) {
				
				resultat = "Site d'escalade"; 
				
				}else {
					
			     resultat = "Site d'escalade introuvable"; 
					
				}
				
			
			}catch(DaoException e) {
				
				  setErreur( "imprévu", "Erreur imprévue" );
				  
		          resultat = "Échec: une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
		          
		            e.printStackTrace();	
				   }
			
		      return siteEscalade;
				
			 }
      private void setErreur( String champ, String message ) {
          erreurs.put( champ, message );
      }

}
