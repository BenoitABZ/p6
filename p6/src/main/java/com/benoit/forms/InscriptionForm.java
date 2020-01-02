package com.benoit.forms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.benoit.dao.classDao.DaoException;
import com.benoit.dao.interfaceDao.AdherentDao;
import com.benoit.entities.Adherent;
import com.benoit.entities.Adresse;


public class InscriptionForm {
	
	private static final String CHAMP_NOM              = "nom";
    private static final String CHAMP_PRENOM           = "prenom";
    private static final String CHAMP_TELEPHONE        = "telephone";
    private static final String CHAMP_EMAIL            = "mail";
    private static final String CHAMP_LIBELLE_VOIE     = "libelleVoie";
    private static final String CHAMP_NOM_VOIE         = "nomVoie";
    private static final String CHAMP_NUMERO_VOIE      = "numeroVoie";
    private static final String CHAMP_CODE_POSTAL      = "codePostal";
    private static final String CHAMP_VILLE            = "ville";
	private static final String CHAMP_MOT_DE_PASSE     = "motDePasse";
	private static final String CHAMP_CONFIRMATION     = "ConfirmationMotDePasse";
	private static final String ALGO_CHIFFREMENT       = "SHA-256";
    
    private String              resultat;
    
    private Map<String, String> erreurs         = new HashMap<String, String>();
    
    public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	private AdherentDao adherentDao = null;
	
	public InscriptionForm (AdherentDao adherentDao) {
		
	 this.adherentDao = adherentDao;
	}
	
	
	public Adherent inscrire(HttpServletRequest request) {
		
		String nom = getChamp (request, CHAMP_NOM );
		String prenom = getChamp (request, CHAMP_PRENOM);
		String telephone = getChamp (request, CHAMP_TELEPHONE);
		String email = getChamp (request, CHAMP_EMAIL);
		String motDePasse = getChamp (request, CHAMP_MOT_DE_PASSE);
		String numeroVoie = getChamp (request, CHAMP_NUMERO_VOIE);
		String libelleVoie = getChamp (request, CHAMP_LIBELLE_VOIE);
		String nomVoie = getChamp (request, CHAMP_NOM_VOIE);
		String codePostal = getChamp (request, CHAMP_CODE_POSTAL);
		String ville = getChamp (request, CHAMP_VILLE);
		String confirmationMotDePasse = getChamp( request, CHAMP_CONFIRMATION);
		Boolean membre = false;
		
		Adresse adresse = new Adresse();
		Adherent adherent = new Adherent();
		
		adherent.setMembre(membre);
		traiterNom(nom, adherent);
		traiterTelephone(telephone, adherent);
		traiterEmail (email, adherent);
		traiterPrenom (prenom, adherent);
		traiterCodePostal(codePostal,adresse);
		traiterVille(ville, adresse);
		traiterNumeroVoie(numeroVoie,adresse);
		traiterLibelleVoie(libelleVoie,adresse);
		traiterNomVoie(nomVoie,adresse);
		traiterMotDePasse(motDePasse, confirmationMotDePasse, adherent, membre);
		
		try {
		
		if (erreurs.isEmpty()) {
			
			adherent.setAdresse(adresse);
			
			adherentDao.creer(adherent);
			
			resultat = "succes de l'inscription"; }
		
			else {
				
			resultat = "erreur(s)?";
			
		}
		
		}catch(DaoException e) {
			
			  setErreur( "imprévu", "Erreur imprévue lors de la création." );
			  
	          resultat = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
	          
	            e.printStackTrace();	
			}
		
		return adherent;
			
		}
	
	private void traiterPrenom( String prenom, Adherent adherent) {
        try {
            controler( prenom, 2);
        } catch ( Exception  e ) {
            setErreur( CHAMP_PRENOM, e.getMessage() );
            
        }
        adherent.setPrenom( prenom );
	}
	
	private void traiterNom( String nom, Adherent adherent) {
        try {
            controler( nom,2 );
        } catch ( Exception  e ) {
            setErreur( CHAMP_NOM, e.getMessage() );
        }
        adherent.setNom( nom );
	}
        
	private void traiterTelephone( String telephone, Adherent adherent) {
        try {
            controler( telephone,10 );
            controlerInt(telephone);
        } catch ( Exception  e ) {
            setErreur( CHAMP_TELEPHONE, e.getMessage() );
        }
        adherent.setTelephone( telephone );
    }

    private void traiterEmail( String mail, Adherent adherent) {
        try {
        	
        	if (!mail.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
                throw new Exception( "Merci de saisir une adresse mail valide." );
            }
        	controler( mail,2 );
        	
        	Adherent adherentTest = adherentDao.trouverMailTest(mail);
        	
        	if (adherentTest!= null) {
        		
        	throw new Exception ("Un utilisateur possedant cette adresse mail existe deja");
        	}
        } catch ( Exception e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }
        adherent.setMail( mail );
    }
    
    private void traiterMotDePasse( String motDePasse, String ConfirmationMotDePasse, Adherent adherent, Boolean membre) {
        try {
        	controler( motDePasse,6 );
        	
        	String lastFour = motDePasse.substring(motDePasse.length() - 4);
        	
        	String conditionMember = "1234";
        	
        	if(lastFour.equals(conditionMember)) membre = true;
        	
        	if (!motDePasse.equals(ConfirmationMotDePasse)) throw new Exception ("Les mots de passe ne correspondent pas");
        	
        	
        	
        } catch ( Exception e ) {
            setErreur( CHAMP_MOT_DE_PASSE, e.getMessage() );
        	
        	
        }
        
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
	    passwordEncryptor.setAlgorithm( ALGO_CHIFFREMENT );
	    passwordEncryptor.setPlainDigest( false );
	    String motDePasseChiffre = passwordEncryptor.encryptPassword( motDePasse );
        
        adherent.setMotDePasse( motDePasseChiffre );
        adherent.setMembre(membre);
    }
    
    private void traiterNumeroVoie( String numeroVoie, Adresse adresse) {
        try {
        	controler( numeroVoie,1 );
        	controlerInt( numeroVoie);
        } catch ( Exception e ) {
            setErreur( CHAMP_NUMERO_VOIE, e.getMessage() );
        }
        adresse.setNumeroVoie( numeroVoie );
    }
    
    private void traiterLibelleVoie( String libelleVoie, Adresse adresse) {
        try {
        	controler( libelleVoie,2 );
        } catch ( Exception e ) {
            setErreur( CHAMP_LIBELLE_VOIE, e.getMessage() );
        }
        adresse.setLibelleVoie( libelleVoie );
    }
    
    private void traiterNomVoie( String nomVoie, Adresse adresse) {
        try {
        	controler( nomVoie,2 );
        } catch ( Exception e ) {
            setErreur( CHAMP_NOM_VOIE, e.getMessage() );
        }
        adresse.setNomVoie( nomVoie );
    }
    
    private void traiterCodePostal( String codePostal, Adresse adresse) {
        try {
        	controler( codePostal,5 );
        	controlerInt( codePostal);
        } catch ( Exception e ) {
            setErreur( CHAMP_CODE_POSTAL, e.getMessage() );
        }
        adresse.setCodePostal( codePostal );
    }
    
    private void traiterVille( String ville, Adresse adresse) {
        try {
        	controler( ville,2 );
        } catch ( Exception e ) {
            setErreur( CHAMP_VILLE, e.getMessage() );
        }
        adresse.setVille( ville );
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
