package com.benoit.forms;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.benoit.dao.classDao.DaoException;
import com.benoit.dao.interfaceDao.SiteEscaladeDao;
import com.benoit.entities.Adherent;
import com.benoit.entities.Secteur;
import com.benoit.entities.SiteEscalade;
import com.benoit.entities.Voie;

public class MajSiteEscaladeForm {
	
        private static final String CHAMP_DEPARTEMENT    = "departement";
	    private static final String CHAMP_COMMUNE        = "commune";
	    private static final String CHAMP_DESCRIPTION    = "description";
	    private static final String CHAMP_ADHERENT       = "adherent";
	    private static final String CHAMP_SITE_ESCALADE  = "siteEscalade";
	    private static final String CHAMP_NOMBRE_SECTEUR = "nombreSecteur";
	    
	    
	    private String              resultat;
	    
	    private Map<String, String> erreurs         = new HashMap<String, String>();
	    
	    public String getResultat() {
			return resultat;
		}

		public Map<String, String> getErreurs() {
			return erreurs;
		}

		Set<Secteur> secteurs = null;
		
		private SiteEscaladeDao siteEscaladeDao = null;
		
		public MajSiteEscaladeForm (SiteEscaladeDao siteEscaladeDao) {
			
		 this.siteEscaladeDao=siteEscaladeDao;
		 
		 
		}
		
		
		public SiteEscalade creer(HttpServletRequest request, HttpSession session) {
			
			
			
			SiteEscalade siteEscalade = (SiteEscalade) session.getAttribute(CHAMP_SITE_ESCALADE);
			
	try {
				
				if (erreurs.isEmpty()) {
					
					siteEscaladeDao.maj(siteEscalade);
					
					resultat = "Site ajouté avec succés"; }
				
					else {
						
					resultat = "erreur(s)?";
					
				}
				
				}catch(DaoException e) {
					
					  setErreur( "imprévu", "Erreur imprévue lors de l'ajout." );
					  
			          resultat = "Échec de l'ajout : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			          
			            e.printStackTrace();	
					}
				
						
			return siteEscalade;
			
		}
		
		
		public SiteEscalade setDescriptionBeforeFinal(HttpServletRequest request, HttpSession session) {
			
			
			
			String description = getChamp(request, CHAMP_DESCRIPTION);
			
			SiteEscalade siteEscalade = (SiteEscalade) session.getAttribute(CHAMP_SITE_ESCALADE);
			
			siteEscalade.setDescription(description);	
			
			return siteEscalade;
			
		}
		
			public SiteEscalade setSecteurNdAdherentNdLieuForSite(HttpServletRequest request, HttpSession session) {
				
				String departement = getChamp(request, CHAMP_DEPARTEMENT);
				
				String commune = getChamp(request, CHAMP_COMMUNE);
				
				String nombreSecteur = getChamp(request, CHAMP_NOMBRE_SECTEUR);
				
				Adherent adherent = (Adherent) session.getAttribute(CHAMP_ADHERENT); 
				
				int nombreS = Integer.parseInt(nombreSecteur);
				
				SiteEscalade siteEscalade = (SiteEscalade) session.getAttribute(CHAMP_SITE_ESCALADE);
				
				Set<Secteur> secteurs = siteEscalade.getSecteurs();
				
   			
				for(int i=0; i <= nombreS; i++){
					
					Secteur secteur = new Secteur();
					
					secteur.setSiteEscalade(siteEscalade);
					
					secteurs.add(secteur);
				}
						
				
				siteEscalade.setSecteurs(secteurs);
				
				siteEscalade.setAdherent(adherent);
				
				siteEscalade.setDepartement(departement);
				
				siteEscalade.setCommune(commune);
				
				session.setAttribute(CHAMP_SITE_ESCALADE, siteEscalade);
				
				return siteEscalade;
			}
			
			
             @SuppressWarnings("rawtypes")
				public SiteEscalade setVoieForSite(HttpServletRequest request, HttpSession session) {
				
				SiteEscalade siteEscalade = (SiteEscalade) session.getAttribute(CHAMP_SITE_ESCALADE);
					
				secteurs = siteEscalade.getSecteurs();
				
				for (int i = 0; i <= secteurs.size();i++) {
					
				String CHAMP_NOMBRE_VOIE = "voie" + i;
					
				String nombreVoie =	request.getParameter(CHAMP_NOMBRE_VOIE);
				
				Integer nombreV = Integer.parseInt(nombreVoie);
				
				Iterator it = secteurs.iterator();
				
				while (it.hasNext()) {
				
				Secteur secteur = (Secteur) it.next();
				                 
				         for (int j = 0; j <= nombreV;i++) {
				        	 
				        	 Voie voie = new Voie();				        
				        	 
				        	 voie.setSecteur(secteur);
				        	 
				        	 Set<Voie> voies = secteur.getVoies();
				        	 
				        	 voies.add(voie);
				        	 
				        	 
				         }
				     }			      				           			         
				}
				
				
				siteEscalade.setSecteurs(secteurs);
				
				session.setAttribute(CHAMP_SITE_ESCALADE, siteEscalade);
				
				
				
				return siteEscalade;
			}
			
             @SuppressWarnings("rawtypes")
				public SiteEscalade setAllForSite (HttpServletRequest request, HttpSession session) {
 				
 				SiteEscalade siteEscalade = (SiteEscalade) session.getAttribute(CHAMP_SITE_ESCALADE);
 				
 				secteurs = siteEscalade.getSecteurs();
 								  				
 				for (int i = 0; i <= secteurs.size();i++) {
 				
 			    Iterator it = secteurs.iterator();	
 					
 			    while (it.hasNext()) {
 						
 			    Secteur secteur = (Secteur) it.next();		
   					
 			    Set<Voie> voies = secteur.getVoies();
 			    
 			    Iterator iv = voies.iterator();
 			    
 			    while (iv.hasNext()) {
 				                 
 				                for(int j=0; j<voies.size(); j++) {
 				        	    
 				        	    String CHAMP_COTATION = "cotation" + j;
 		    					
 			    				String cotation =	request.getParameter(CHAMP_COTATION);
 			    				
 			    				Integer cot = Integer.parseInt(cotation);
 			    				
 			    				String CHAMP_HAUTEUR = "hauteur" + j;
    		    					
    			    			String hauteur = request.getParameter(CHAMP_HAUTEUR);
    			    				
	                            String CHAMP_EQUIPEE = "equipee" + j;
    		    					
    			    		    String equipeeChamps = request.getParameter(CHAMP_EQUIPEE);
    			    				
    			    			String CHAMP_NOMBRE_LONGUEUR= "nombreLongueur" + j;
 		    					
 			    				String nombreLongueur = request.getParameter(CHAMP_NOMBRE_LONGUEUR);
 			    				
 			    				Voie voie = (Voie) iv.next();
 			    				
 			    				voie.setCotationVoie(cot);
 			    				
 			    				voie.setHauteur(hauteur);
 			    				
 			    				Boolean equipee = false;
 			    				
 			    				if (equipeeChamps.equals("oui")) {
 			    					
 			    					equipee = true;
 			    				}
 			    				
 			    				voie.setEquipee(equipee);
 			    				
 			    				voie.setNombreLongueur(nombreLongueur);
 				        	 
 				        	 
 				            }
 				             
 		     	    }
 				
 	        }
			
			
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
