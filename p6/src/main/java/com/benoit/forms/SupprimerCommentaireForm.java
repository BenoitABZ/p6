package com.benoit.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.benoit.dao.classDao.DaoException;
import com.benoit.dao.interfaceDao.CommentaireDao;
import com.benoit.entities.Commentaire;

public class SupprimerCommentaireForm {
	
	private static final String CHAMP_COMMENTAIRE  = "commentaire";
	
	 private String              resultat;
	    
	    private Map<String, String> erreurs         = new HashMap<String, String>();
	    
	    public String getResultat() {
			return resultat;
		}

		public Map<String, String> getErreurs() {
			return erreurs;
		}

		
		
		private CommentaireDao commentaireDao = null;
		
		public SupprimerCommentaireForm (CommentaireDao commentaireDao) {
			
		 this.commentaireDao=commentaireDao;
		 
		}
		 
		 public void supprimer(HttpServletRequest request, HttpSession session) {
		 
		Commentaire commentaire = (Commentaire) request.getAttribute(CHAMP_COMMENTAIRE); 
		 
		 try {
				
				if (erreurs.isEmpty()) {
					
					commentaireDao.supprimer(commentaire);
					
					resultat = "succes de l'inscription"; }
				
					else {
						
					resultat = "erreur(s)?";
					
				}
				
				}catch(DaoException e) {
					
					  setErreur( "imprévu", "Erreur imprévue lors de la suppression" );
					  
			          resultat = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			          
			            e.printStackTrace();	
					}
				
					
				}
				
	      private void setErreur( String champ, String message ) {
	        erreurs.put( champ, message );
	    }
		

}


