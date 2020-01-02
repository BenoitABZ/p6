package com.benoit.forms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.benoit.dao.classDao.DaoException;
import com.benoit.dao.interfaceDao.CommentaireDao;
import com.benoit.dao.interfaceDao.SiteEscaladeDao;
import com.benoit.entities.Commentaire;
import com.benoit.entities.SiteEscalade;

public class ListerSitesEscaladeForm {
	
	
    private String              resultat;
    
    private Map<String, String> erreurs         = new HashMap<String, String>();
    
    public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	
	
	private SiteEscaladeDao siteEscaladeDao = null;
	
	public ListerSitesEscaladeForm (SiteEscaladeDao siteEscaladeDao) {
		
	 this.siteEscaladeDao=siteEscaladeDao;
	}
	
	
	public Set<SiteEscalade> lister() {
		
		
		Set<SiteEscalade> sitesEscalade=null;
		
		try {
			
			
				
			    sitesEscalade = siteEscaladeDao.lister();
			    
			    for (SiteEscalade s: sitesEscalade) {
			    	
			    	System.out.println(s.getNom());
			    }
				
				if(sitesEscalade.size() > 0) {
				
				resultat = "Liste des sites d'escalade"; 
				
				}else {
					
			    resultat = "Aucun site d'escalade"; 
					
				}
				
			
			}catch(DaoException e) {
				
				  setErreur( "imprévu", "Erreur imprévue lors de l'ajout." );
				  
		          resultat = "Échec: une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
		          
		            e.printStackTrace();	
				}
			
		      return sitesEscalade;
				
			}
      private void setErreur( String champ, String message ) {
          erreurs.put( champ, message );
      }
       		
  		

}
