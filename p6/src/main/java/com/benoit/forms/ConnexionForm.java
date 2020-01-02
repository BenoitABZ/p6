package com.benoit.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.benoit.dao.classDao.DaoException;
import com.benoit.dao.interfaceDao.AdherentDao;
import com.benoit.entities.Adherent;

public class ConnexionForm {

	    private static final String CHAMP_EMAIL          = "mail";
	    private static final String CHAMP_PASS           = "motDePasse";
	    private static final String ALGO_CHIFFREMENT       = "SHA-256";
	    

	    private String              resultat;
	    private Map<String, String> erreurs      = new HashMap<String, String>();

	    public String getResultat() {
	        return resultat;
	    }

	    public Map<String, String> getErreurs() {
	        return erreurs;
	    }
	    
		private AdherentDao adherentDao = null;
		
		public ConnexionForm (AdherentDao adherentDao) {
			
		 this.adherentDao = adherentDao;
		}

	    public Adherent connecterAdherent( HttpServletRequest request ) {
	        /* Récupération des champs du formulaire */
	    	
	        String mail = getChamp( request, CHAMP_EMAIL );
	        String motDePasse = getChamp( request, CHAMP_PASS );

	        Adherent adherent = null;
	        
	        traiterMail(mail);
	        traiterMotDePasse(motDePasse);
	            
	     
	        
	    	try {
	    		
	    		if (erreurs.isEmpty()) {
	    			
	    			adherent = adherentDao.trouverMail(mail, motDePasse, ALGO_CHIFFREMENT);
	    				    			
	    			resultat = "authentification reussie"; }
	    		
	    			else {
	    				
	    			resultat = "erreur(s)?";
	    			
	    		}
	    		
	    		}catch(DaoException e) {
	    			
	    			  setErreur( "erreur", e.getMessage());
	    			  
	    	          resultat = "Échec de la connexion : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
	    	          
	    	            e.printStackTrace();	
	    			}
	    		
	    		return adherent;
	    			
	    		}

	  
        private void traiterMail( String mail) {
            try {
                
            	if (!mail.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
                    throw new Exception( "Merci de saisir une adresse mail valide." );
                }
            	
         
            	controler( mail,2);
            
            	
            	
            } catch ( Exception  e ) {
                setErreur( CHAMP_EMAIL, e.getMessage() );
                
            }
            
        }
        
        private void traiterMotDePasse( String mdp) {
            try {
                controler( mdp,8);
                     
            } catch ( Exception  e ) {
                setErreur( CHAMP_PASS, e.getMessage() );
                
            }
            
        }
        
    	private void controler(String parametre, int nbreCa) throws Exception {
    		
    		if (parametre == null || parametre.length() == 0) {
    			
    			throw new Exception ("Veuillez renseigner le champ" + parametre + ".");
    	}
    		else if (parametre.length()<nbreCa) {
    			
    			throw new Exception ("Le champ " + parametre + " doit contenir au moins " + nbreCa + " caractère(s).");
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


