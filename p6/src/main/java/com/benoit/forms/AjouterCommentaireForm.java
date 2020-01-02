package com.benoit.forms;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.benoit.dao.classDao.DaoException;
import com.benoit.dao.interfaceDao.CommentaireDao;
import com.benoit.entities.Adherent;
import com.benoit.entities.Commentaire;
import com.benoit.entities.SiteEscalade;


public class AjouterCommentaireForm {
	

    private static final String CHAMP_CONTENU   = "contenu";
    private static final String CHAMP_ADHERENT  = "adherent";
    private static final String CHAMP_SITE_ESCALADE  = "siteEscalade";
    
    
    private String              resultat;
    
    private Map<String, String> erreurs         = new HashMap<String, String>();
    
    public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	
	
	private CommentaireDao commentaireDao = null;
	
	public AjouterCommentaireForm (CommentaireDao commentaireDao) {
		
	 this.commentaireDao=commentaireDao;
	}
	
	
	public Commentaire creerCommentaire(HttpServletRequest request, HttpSession session) {
		
		LocalDate date = LocalDate.now();
		
		Adherent adherent = (Adherent) session.getAttribute(CHAMP_ADHERENT); 
		
		SiteEscalade siteEscalade = (SiteEscalade) session.getAttribute(CHAMP_SITE_ESCALADE);
		
		String contenu = getChamp(request, CHAMP_CONTENU);
		
		
		Commentaire commentaire = new Commentaire();
		
		commentaire.setAdherent(adherent);
		
		traiterCommentaire(contenu,commentaire);
		
		commentaire.setDateCommentaire(date);	
			
		commentaire.setSiteEscalade(siteEscalade);
		
		
		try {
			
			if (erreurs.isEmpty()) {
				
				commentaireDao.creer(commentaire);
				
				resultat = "succes de l'inscription"; }
			
				else {
					
				resultat = "erreur(s)?";
				
			}
			
			}catch(DaoException e) {
				
				  setErreur( "imprévu", "Erreur imprévue lors de la création." );
				  
		          resultat = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
		          
		            e.printStackTrace();	
				}
			
		      return commentaire;
				
			}
	
	private void traiterCommentaire( String contenu, Commentaire commentaire) {
        try {
            controler( contenu,50 );
        } catch ( Exception  e ) {
            setErreur( CHAMP_CONTENU, e.getMessage() );
        }
        commentaire.setContenuCommentaire(contenu);
	}
	
	private void controler(String parametre, int nbreCa) throws Exception {
		
		if (parametre == null || parametre.length() == 0) {
			
			throw new Exception ("Veuillez renseigner ce champ.");
	}
		else if (parametre.length()<nbreCa) {
			
			throw new Exception ("Ce champ doit contenir au moins " + nbreCa + " caractère(s).");
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
