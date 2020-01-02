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
import com.benoit.entities.Adresse;
import com.benoit.entities.Secteur;
import com.benoit.entities.SiteEscalade;
import com.benoit.entities.Voie;

public class AjouterSiteEscaladeForm {
	
	    private static final String CHAMP_DEPARTEMENT    = "departement";
		private static final String CHAMP_COMMUNE        = "commune";
	    private static final String CHAMP_DESCRIPTION    = "description";
	    private static final String CHAMP_ADHERENT       = "adherent";
	    private static final String CHAMP_SITE_ESCALADE  = "siteEscalade";
	    private static final String CHAMP_NOMBRE_SECTEUR = "nombreSecteur";
	    private static final String CHAMP_NOM            = "nom";
	
	    
	    
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
		
		public  AjouterSiteEscaladeForm (SiteEscaladeDao siteEscaladeDao) {
			
		 this.siteEscaladeDao=siteEscaladeDao;
		 
		 
		}
		
		
		@SuppressWarnings("rawtypes")
		public SiteEscalade creerSite(HttpServletRequest request, HttpSession session) {
			
			
			
			SiteEscalade siteEscalade = (SiteEscalade) session.getAttribute(CHAMP_SITE_ESCALADE);
			
	try {
		
		if (erreurs.isEmpty()) {
					
		/*	Set<Secteur> secteurs = siteEscalade.getSecteurs();
			
			Iterator is = secteurs.iterator();
			
			while (is.hasNext()) {
				
				 Secteur secteur = (Secteur) is.next();
				
				 Set<Voie> voies = secteur.getVoies();
				 
				 Iterator iv = voies.iterator();
				 
				 while (iv.hasNext()) {
					 
					 Voie voie = (Voie) iv.next();
					 
					 				 			 
				 }
				 
				   siteEscaladeDao.creer(secteur);
			     }
			      */ 
					siteEscaladeDao.creer(siteEscalade);
					
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
			
			traiterDescription( description, siteEscalade);	
			
			return siteEscalade;
			
		}
		
	      private void traiterDescription( String description, SiteEscalade siteEscalade) {
		        try {
		        	controler( description,20 );
		        	 				
		        } catch ( Exception e ) {
		            setErreur(CHAMP_DESCRIPTION, e.getMessage() );
		        }
		        siteEscalade.setDescription(description);
		    }
		
			public SiteEscalade setSecteurNdAdherentNdLieuForSite(HttpServletRequest request, HttpSession session) {
				
				String departement = getChamp(request, CHAMP_DEPARTEMENT);
				
				String commune = getChamp(request, CHAMP_COMMUNE);
				
				String nom = getChamp(request, CHAMP_NOM);
				
				String nombreSecteur = getChamp(request, CHAMP_NOMBRE_SECTEUR);
				
				Adherent adherent = (Adherent) session.getAttribute(CHAMP_ADHERENT); 
				
			  
				
				
				
				SiteEscalade siteEscalade = null;
				
				if (session.getAttribute(CHAMP_SITE_ESCALADE) != null) {
					
				siteEscalade = (SiteEscalade) session.getAttribute(CHAMP_SITE_ESCALADE);
				} else {
					
				siteEscalade = new SiteEscalade();
				}
				
				siteEscalade.setAdherent(adherent);
				
				traiterNbreSecteur(nombreSecteur, siteEscalade);
				
				traiterNom( nom, siteEscalade);
				
 			    traiterDepartement(departement,siteEscalade);
				
 			    traiterCommune(commune, siteEscalade);
				
				session.setAttribute(CHAMP_SITE_ESCALADE, siteEscalade);
				
				return siteEscalade;
				
			}

			
		    private void traiterNbreSecteur( String nombreSecteur, SiteEscalade siteEscalade) {
		        try {
		        	controler( nombreSecteur,1 );
		        	  int nombreS = Integer.parseInt(nombreSecteur);
		  			Set<Secteur> secteurs = siteEscalade.getSecteurs();
		  				
		  				if (secteurs.size() != nombreS) {
		  				
		  				secteurs.removeAll(secteurs);
		  				
		  				for(int i=0; i < nombreS; i++){
		  					
		  					Secteur secteur = new Secteur();
		  					
		  					Integer numeroSecteur = new Integer(i);
		  					
		  					secteur.setNumeroSecteur(numeroSecteur);
		  					
		  					secteur.setSiteEscalade(siteEscalade);
		  					
		  					secteurs.add(secteur);
		  				}
		  						
		  				
		  				siteEscalade.setSecteurs(secteurs);
		  				 }
		        	 
		        } catch ( Exception e ) {
		            setErreur( CHAMP_NOMBRE_SECTEUR, e.getMessage() );
		        }
		        
		      
		   
		    }
		    
		    private void traiterDepartement( String departement, SiteEscalade siteEscalade) {
		        try {
		        	controler( departement,2 );
		        } catch ( Exception e ) {
		            setErreur( CHAMP_DEPARTEMENT, e.getMessage() );
		        }
		        siteEscalade.setDepartement(departement);
		    }
		    
		    private void traiterNom( String nom, SiteEscalade siteEscalade) {
		        try {
		            controler( nom,2 );
		            
		            SiteEscalade siteEscaladeTest = siteEscaladeDao.trouverNom(nom);
		        	
		        	if (siteEscaladeTest!= null) {
		        		
		        	throw new Exception ("Un site d'escalade possedant ce nom existe deja");
		        	
		        	}
		            
		        } catch ( Exception  e ) {
		            setErreur( CHAMP_NOM, e.getMessage() );
		        }
		        siteEscalade.setNom(nom);
			}
		      
		    private void traiterCommune( String commune, SiteEscalade siteEscalade) {
		        try {
		        	controler( commune,2 );
		        } catch ( Exception e ) {
		            setErreur( CHAMP_COMMUNE, e.getMessage() );
		        }
		        siteEscalade.setCommune(commune);
		    }

			
			
                @SuppressWarnings("rawtypes")
				public SiteEscalade setVoieForSite(HttpServletRequest request, HttpSession session) {
				
				SiteEscalade siteEscalade = (SiteEscalade) session.getAttribute(CHAMP_SITE_ESCALADE);
					
				secteurs = siteEscalade.getSecteurs();
				
	            Iterator it = secteurs.iterator();
	            
	            int i = 0;
				
				while (it.hasNext()) {
				
				i++;	
					
				Secteur secteur = (Secteur) it.next();
					
				String CHAMP_NOMBRE_VOIE = "voie" + i;
					
				String nombreVoie =	(request.getParameter(CHAMP_NOMBRE_VOIE)).trim();
				
				traiterVoie( nombreVoie, siteEscalade, secteur);
	
				}
				siteEscalade.setSecteurs(secteurs);
					
				return siteEscalade;
			}
                
                
                private void traiterVoie( String nombreVoie, SiteEscalade siteEscalade, Secteur secteur) {
    		        try {
    		        	controler( nombreVoie,1);
    		    		Integer nombreV = Integer.parseInt(nombreVoie);
    					
    					 Set<Voie> voies = secteur.getVoies();
    					 
    					 if (voies.size()!= nombreV) {
    					 
    					 voies.removeAll(voies);
    				               
    				    for (int j = 0; j < nombreV;j++) {
    					        	 
    					        	 Voie voie = new Voie();				        
    					        	 
    					        	 voie.setSecteur(secteur);
    					        	 
    					        	
    					        	 
    					        	 voies.add(voie);
    					        	 
    					        	 secteur.setVoies(voies);
    					        	 
    					        	 if (j==nombreV) {
    					        		 
    					        		 int siz = voies.size();
    					        		 
    					        		 System.out.println(siz);
    					        	 }
    					        	 
    				    }   				    
    					}
    		        } catch ( Exception e ) {
    		            setErreur( "nombreVoie", e.getMessage() );
    		        }
    		       
    		    }
    		    
			
                @SuppressWarnings("rawtypes")
				public SiteEscalade setAllForSite (HttpServletRequest request, HttpSession session) {
    				
    				SiteEscalade siteEscalade = (SiteEscalade) session.getAttribute(CHAMP_SITE_ESCALADE);
    				
    				Set<Secteur> secteurs = siteEscalade.getSecteurs();
    				
    			
    								  				
    				
    				
    			    Iterator is = secteurs.iterator();
    			    
    			    int k=0;
    			   	
    			    while (is.hasNext()) {
    			    	
    			    	k++;
    						
    			    Secteur secteur = (Secteur) is.next();		
      					
    			    Set<Voie> voies = secteur.getVoies();
    			    
    			    Iterator iv = voies.iterator();
    			    
    			    int j = 0;
    			    
    			    while (iv.hasNext()) {
    				                 
    				                j++;   
    				                int resultat = j+ k *3;
    				                
    				                System.out.println(resultat);
    				        	    
    				        	    String CHAMP_COTATION = "cotation" + resultat;
    		    					
    			    				String cotation =	request.getParameter(CHAMP_COTATION).trim();
    			    				   			    				
    			    				String CHAMP_HAUTEUR = "hauteur" + resultat;
       		    					
       			    				String hauteur = request.getParameter(CHAMP_HAUTEUR).trim();
       			    				
	                                String CHAMP_EQUIPEE = "equipee" + resultat;
       		    					
       			    				String equipeeChamps = request.getParameter(CHAMP_EQUIPEE).trim();
       			    				
       			    			    String CHAMP_NOMBRE_LONGUEUR= "longueur" + resultat;
    		    					
    			    				String nombreLongueur = request.getParameter(CHAMP_NOMBRE_LONGUEUR).trim();
    			    				
    			    				Voie voie = (Voie) iv.next();
    			    				
    			    				traiterCotation( cotation, voie);
    			    				
    			    				traiterHauteur(hauteur, voie);
    			    				
    			    				traiternombreLongueur( nombreLongueur, voie);
    			    				
    			    				Boolean equipee = false;
    			    				
    			    				if (equipeeChamps.equals("Oui")) {
    			    					
    			    					equipee = true;
    			    				}
    			    				
    			    				voie.setEquipee(equipee);
    			    				
    			    				 
    			    				 
    			    				 
    		     	    }
    				
    	        }
			
			
        
			
    				return siteEscalade;
 }
                
                private void traiterCotation( String cotation, Voie voie) {
    		        try {
    		        	controler( cotation,1 );
    		        	
    		        	Integer cot = Integer.parseInt(cotation);
	    				
	    				voie.setCotationVoie(cot);
    		        } catch ( Exception e ) {
    		            setErreur("cotation", e.getMessage() );
    		        }
    		        
    		    }
                
                private void traiterHauteur( String hauteur, Voie voie) {
    		        try {
    		        	controler( hauteur,1 );
    		        	
    		        	voie.setHauteur(hauteur);
	    				
    		        	controlerInt(hauteur);
	    				
    		        } catch ( Exception e ) {
    		            setErreur("hauteur", e.getMessage() );
    		        }
    		        
    		    }
                
                private void traiternombreLongueur( String nombreLongueur, Voie voie) {
    		        try {
    		        	controler( nombreLongueur,1 );
    		        	
    		        	voie.setNombreLongueur(nombreLongueur);
	    				
    		        	
	    				
    		        } catch ( Exception e ) {
    		            setErreur("nombreLongueur", e.getMessage() );
    		        }
    		        
    		    }
                
                
            
            	private void controler(String parametre, int nbreCa) throws Exception {
            		
            		if (parametre == null || parametre.length() == 0) {
            			
            			throw new Exception ("Veuillez renseigner ce champ.");
            	}
            		else if (parametre.length()<nbreCa) {
            			
            			throw new Exception ("Ce champ doit contenir au moins " + nbreCa + " caractère(s).");
            		}
            	}
            	
            	private void controlerInt (String parametre) throws Exception{
            		
            		 if ( !parametre.matches( "^\\d+$" ) ) {
                         throw new Exception( "Ce champ doit uniquement contenir des chiffres." );
            		 }
            		
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
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		

