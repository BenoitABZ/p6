package com.benoit.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.benoit.dao.classDao.DaoException;
import com.benoit.dao.interfaceDao.AdherentDao;
import com.benoit.entities.Adherent;


public class TrouverAdherentForm {

    private static final String CHAMP_ID             = "idAdherent";
    
    
    private String              resultat;
    
    private Map<String, String> erreurs         = new HashMap<String, String>();
    
    public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	
	
	private AdherentDao adherentDao = null;
	
	public TrouverAdherentForm (AdherentDao adherentDao) {
		
	 this.adherentDao=adherentDao;
	}
	
	
	public Adherent trouver(HttpServletRequest request) {
		
		Long idLong = null;
		
		String id = getChamp(request, CHAMP_ID);
		
		if (id == null) {
			
		idLong = (Long) request.getAttribute(CHAMP_ID);
		}
	
		
		Adherent adherent = null;
		
		try {
			
			if (erreurs.isEmpty()) {
				
				if (id!=null) {
					
			    adherent = adherentDao.trouver(id);
				} else if (idLong != null) {
					
			    adherent= adherentDao.trouver(idLong);
				}
				
				
				resultat = adherent.getPrenom(); }
			
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
