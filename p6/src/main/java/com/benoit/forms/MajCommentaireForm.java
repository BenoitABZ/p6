package com.benoit.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.benoit.dao.classDao.DaoException;
import com.benoit.dao.interfaceDao.CommentaireDao;
import com.benoit.entities.Commentaire;



public class MajCommentaireForm {
	
	    private static final String CHAMP_CONTENU        = "contenuCommentaire";
	    private static final String CHAMP_COMMENTAIRE    = "commentaire";
	    
	    
	    private String              resultat;
	    
	    private Map<String, String> erreurs         = new HashMap<String, String>();
	    
	    public String getResultat() {
			return resultat;
		}

		public Map<String, String> getErreurs() {
			return erreurs;
		}

		
		
		private CommentaireDao commentaireDao = null;
		
		public MajCommentaireForm (CommentaireDao commentaireDao) {
			
		 this.commentaireDao=commentaireDao;
		}
		
		
		public Commentaire majCommentaire(HttpServletRequest request, HttpSession session) {
			
			
			String contenu = getChamp(request, CHAMP_CONTENU);
			
			
			Commentaire commentaire = (Commentaire) session.getAttribute(CHAMP_COMMENTAIRE);;
			
			
			traiterCommentaire(contenu,commentaire);
			
			
			
			try {
				
				if (erreurs.isEmpty()) {
					
					commentaireDao.maj(commentaire);
					
					resultat = "Commentaire modifié"; }
				
					else {
						
					resultat = "erreur(s)?";
					
				}
				
				}catch(DaoException e) {
					
					  setErreur( "imprévu", "Erreur imprévue" );
					  
			          resultat = "Échec : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			          
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
