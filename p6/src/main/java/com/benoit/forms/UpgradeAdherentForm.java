package com.benoit.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.benoit.dao.classDao.DaoException;
import com.benoit.dao.interfaceDao.AdherentDao;
import com.benoit.entities.Adherent;


public class UpgradeAdherentForm {
	private static final String CHAMP_ADHERENT         = "adherent";

    
    
    private String              resultat;
    
    private Map<String, String> erreurs         = new HashMap<String, String>();
    
    public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	
	
	private AdherentDao adherentDao = null;
	
	public UpgradeAdherentForm (AdherentDao adherentDao) {
		
	 this.adherentDao=adherentDao;
	}
	
	
	public Adherent upgradeAdherent(Adherent adherent) {
	
		//Adherent adherent = (Adherent) request.getAttribute(CHAMP_ADHERENT);
	    
		Boolean membre = adherent.getMembre();
		
		membre = true;
		
		
		
		try {
			
			if (erreurs.isEmpty()) {
				
				adherent.setMembre(membre);
				
				System.out.println(adherent.getMembre());
				
				adherentDao.maj(adherent);
				
				resultat = "ok"; }
			
				else {
					
				resultat = "erreur(s)?";
				
			}
			
			}catch(DaoException e) {
				
				  setErreur( "imprévu", "Erreur imprévue" );
				  
		          resultat = "Échec : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
		          
		            e.printStackTrace();	
				}
			
		      return adherent;
				
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
