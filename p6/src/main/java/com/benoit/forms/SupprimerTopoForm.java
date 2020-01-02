package com.benoit.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.benoit.dao.classDao.DaoException;
import com.benoit.dao.interfaceDao.CommentaireDao;
import com.benoit.dao.interfaceDao.TopoDao;
import com.benoit.entities.Commentaire;
import com.benoit.entities.Topo;

public class SupprimerTopoForm {
	
	private static final String CHAMP_TOPO  = "topo";
	
	 private String              resultat;
	    
	    private Map<String, String> erreurs         = new HashMap<String, String>();
	    
	    public String getResultat() {
			return resultat;
		}

		public Map<String, String> getErreurs() {
			return erreurs;
		}

		
		
		private TopoDao topoDao = null;
		
		public SupprimerTopoForm (TopoDao topoDao) {
			
		 this.topoDao=topoDao;
		 
		}
		 
		 public void supprimer (HttpServletRequest request, HttpSession session) {
		 
		Topo topo = (Topo) request.getAttribute(CHAMP_TOPO); 
		 
		 try {
				
				if (erreurs.isEmpty()) {
					
					topoDao.supprimer(topo);
					
					resultat = "topo supprimé"; }
				
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


