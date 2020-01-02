package com.benoit.forms;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.benoit.dao.classDao.DaoException;
import com.benoit.dao.interfaceDao.CommentaireDao;
import com.benoit.entities.Commentaire;
import com.benoit.entities.SiteEscalade;


public class ListerCommentairesForm {
	    
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
		
		public ListerCommentairesForm (CommentaireDao commentaireDao) {
			
		 this.commentaireDao=commentaireDao;
		}
		
		
		public List<Commentaire> listerCommentaires(HttpServletRequest request, HttpSession session) {
			
			
			SiteEscalade siteEscalade = (SiteEscalade) session.getAttribute(CHAMP_SITE_ESCALADE); 
			
			List<Commentaire> commentaires=null;
			
			try {
				
				
					
					commentaires = commentaireDao.trouver(siteEscalade);
					
					if(commentaires != null) {
					
					resultat = "Liste des commentaires"; 
					
					}else {
						
				     resultat = "Pas de commentaires"; 
						
					}
					
				
				}catch(DaoException e) {
					
					  setErreur( "imprévu", "Erreur imprévue lors de l'ajout." );
					  
			          resultat = "Échec: une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			          
			            e.printStackTrace();	
					}
				
			      return commentaires;
					
				}
	      private void setErreur( String champ, String message ) {
	          erreurs.put( champ, message );
	      }
	       		
	  		
	  	
	  	
}
