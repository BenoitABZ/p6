package com.benoit.forms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.benoit.dao.classDao.DaoException;
import com.benoit.dao.interfaceDao.TopoDao;
import com.benoit.entities.Adherent;
import com.benoit.entities.Topo;

public class ListerToposAdherentForm {
	
	
	private static final String CHAMP_ADHERENT = "adherent";
	
    private String              resultat;
    
    private Map<String, String> erreurs         = new HashMap<String, String>();
    
    public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	
	
	private TopoDao topoDao = null;
	
	public ListerToposAdherentForm (TopoDao topoDao) {
		
	 this.topoDao=topoDao;
	}
	
	
	public List<Topo> listerToposAdherent(HttpServletRequest request, HttpSession session) {
		
		
		Adherent adherent = (Adherent) session.getAttribute(CHAMP_ADHERENT); 
		
		List<Topo> topos=null;
		
		try {
			
			
				
				topos = topoDao.trouver(adherent);
				
				if(topos.size() != 0) {
				
				resultat = "Vos topos"; 
				
				}else {
					
			     resultat = "Vous n'avez aucun topo"; 
					
				}
				
			
			}catch(DaoException e) {
				
				  setErreur( "imprévu", "Erreur imprévue" );
				  
		          resultat = "Échec: une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
		          
		            e.printStackTrace();	
				}
			
		      return topos;
				
			}
      private void setErreur( String champ, String message ) {
          erreurs.put( champ, message );
      }

}
