package com.benoit.forms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.benoit.dao.classDao.DaoException;
import com.benoit.dao.interfaceDao.SiteEscaladeDao;
import com.benoit.entities.SiteEscalade;

public class FiltrerSitesEscaladeForm {
	
	private static final String CHAMP_SECTEUR_MIN        = "secteurMin";
    private static final String CHAMP_SECTEUR_MAX        = "secteurMax";
    private static final String CHAMP_COTATION_MIN       = "cotationMin";
    private static final String CHAMP_COTATION_MAX       = "cotationMax";
    private static final String CHAMP_CRITERE_TRIE_SITE  = "critereTrieSite";
	private static final String CHAMP_DEPARTEMENT        = "departement";
	private static final String CHAMP_COMMUNE            = "commune";
	private static final String CHAMP_NOM                = "nom";

	

	
    private String              resultat;
    
    private Map<String, String> erreurs         = new HashMap<String, String>();
    
    public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	
	
	private SiteEscaladeDao siteEscaladeDao = null;
	
	public FiltrerSitesEscaladeForm (SiteEscaladeDao siteEscaladeDao) {
		
	 this.siteEscaladeDao=siteEscaladeDao;
	}
	
	
	public Integer convertInteger(String parameter) {
		
		if (parameter != null) {
			
			Integer param = Integer.parseInt(parameter);
			
			return param;
		} else {
			
			return null;
		}
		
		
	}
	
	public Set<SiteEscalade> filtrer(HttpServletRequest request) {
		
		
		
		Integer secteurMin     = convertInteger(getChamp (request, CHAMP_SECTEUR_MIN));
		Integer secteurMax     = convertInteger(getChamp (request, CHAMP_SECTEUR_MAX));
		Integer cotationMin    = convertInteger(getChamp (request, CHAMP_COTATION_MIN));
		Integer cotationMax    = convertInteger(getChamp (request, CHAMP_COTATION_MAX));
		String departement     = getChamp (request, CHAMP_DEPARTEMENT);
		String commune         = getChamp (request, CHAMP_COMMUNE);
		String nom             = getChamp (request, CHAMP_NOM);
		String critereTrieSite = getChamp (request, CHAMP_CRITERE_TRIE_SITE);
		
		
		Set<SiteEscalade> sitesEscalade=null;
		
		try {
			
			
				
				sitesEscalade =  siteEscaladeDao.rechercher(secteurMin, secteurMax, cotationMin, cotationMax, departement, critereTrieSite);
				
				 for (SiteEscalade s: sitesEscalade) {
				    	
				    	System.out.println(s.getNom());
				    }
				
				if(sitesEscalade.size() > 0 ) {
				
				resultat = "Liste des sites"; 
				
				}else {
					
			    resultat = "Aucun site d'escalade ne correspond aux critères de recherche"; 
					
				}
				
			
			}catch(DaoException e) {
				
				  setErreur( "imprévu", "Erreur imprévue" );
				  
		          resultat = "Échec: une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
		          
		            e.printStackTrace();	
				}
			
		      return sitesEscalade;
				
			}

	
    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
     private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

     private String getChamp (HttpServletRequest request, String parameter) {
	
	 String valeur = request.getParameter(parameter);
	
	 if(valeur == null || valeur.equals("")) return null;
	
	 else {
		 
		 return valeur;
	 
	 }
  }
       		

}
