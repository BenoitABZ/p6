package com.benoit.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.benoit.dao.classDao.DaoException;
import com.benoit.dao.interfaceDao.SiteEscaladeDao;
import com.benoit.entities.SiteEscalade;

public class LabelSiteEscaladeForm {
	
private static final String CHAMP_SITE_ESCALADE = "siteEscalade";
 
    private String              resultat;
    
    private Map<String, String> erreurs         = new HashMap<String, String>();
    
    public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	
	
	private SiteEscaladeDao siteEscaladeDao = null;
	
	public LabelSiteEscaladeForm (SiteEscaladeDao siteEscaladeDao) {
		
	 this.siteEscaladeDao=siteEscaladeDao;
	}
	
	
	public SiteEscalade labelliserSiteEscalade(HttpServletRequest request, HttpSession session) {
	
		SiteEscalade siteEscalade = (SiteEscalade) session.getAttribute(CHAMP_SITE_ESCALADE);
		
		String label = "Officiel Les Amis De L'escalade";
	    
		siteEscalade.setLabel(label);
		
		
		
		try {
			
			if (erreurs.isEmpty()) {
				
				siteEscaladeDao.maj(siteEscalade);
				
				resultat = "Officiel Les Amis De L'escalade"; }
			
				else {
					
				resultat = "erreur(s)?";
				
			}
			
			}catch(DaoException e) {
				
				  setErreur( "imprévu", "Erreur imprévue" );
				  
		          resultat = "Échec : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
		          
		            e.printStackTrace();	
				}
			
		      return siteEscalade;
				
			}
			
      private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }
    
	  private String getChamp (HttpServletRequest request, String parameter) {
			
		 String valeur = request.getParameter(parameter);
		
		 if(valeur == null) return null;
		
		 else {
			 
			 return valeur;
		 
		 }
		
		
		
		
			
		}

}
