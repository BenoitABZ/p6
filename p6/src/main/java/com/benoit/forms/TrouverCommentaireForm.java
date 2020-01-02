package com.benoit.forms;

import java.util.HashMap;
import java.util.Map;

import com.benoit.dao.classDao.DaoException;
import com.benoit.dao.interfaceDao.CommentaireDao;
import com.benoit.entities.Commentaire;


public class TrouverCommentaireForm {
	
    private String              resultat;
    
    private Map<String, String> erreurs         = new HashMap<String, String>();
    
    public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	private CommentaireDao commentaireDao = null;
	
	public TrouverCommentaireForm (CommentaireDao commentaireDao) {
		
	 this.commentaireDao =commentaireDao ;
	}
	
	
	public Commentaire trouver(String id) {
		
        Commentaire commentaire = null;
		
		try {
			
			
				
				commentaire = commentaireDao.trouver(id);
				
				if(commentaire != null) {
				
				resultat = "Commentaire de:" + commentaire.getAdherent().getPrenom(); 
				
				}else {
					
			     resultat = "Commentaire introuvable"; 
					
				}
				
			
			}catch(DaoException e) {
				
				  setErreur( "imprévu", "Erreur imprévue" );
				  
		          resultat = "Échec: une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
		          
		            e.printStackTrace();	
				}
			
		      return commentaire;
				
			}
      private void setErreur( String champ, String message ) {
          erreurs.put( champ, message );
      }


}
