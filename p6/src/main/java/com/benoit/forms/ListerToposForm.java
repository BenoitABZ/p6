package com.benoit.forms;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.benoit.dao.classDao.DaoException;
import com.benoit.dao.interfaceDao.TopoDao;
import com.benoit.entities.Topo;

public class ListerToposForm {
	private static final String CHAMP_DATE_PARUTION          = "dateParution";
    private static final String CHAMP_NOM                    = "nom";
    private static final String CHAMP_DISPONIBILITE          = "disponibilite";
    private static final String CHAMP_CRITERE_TRIE_TOPO      = "critereTrieTopo";
  
	
    private String              resultat;
    
    private Map<String, String> erreurs         = new HashMap<String, String>();
    
    public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	
	
	private TopoDao topoDao = null;
	
	public ListerToposForm (TopoDao topoDao) {
		
	 this.topoDao=topoDao;
	}
	
	
	public List<Topo> listerTopos(HttpServletRequest request) {
		
		String dateParution             = getChamp (request, CHAMP_DATE_PARUTION);
		String nom                      = getChamp (request, CHAMP_NOM);
		String disponibilite            = getChamp (request, CHAMP_DISPONIBILITE);
		String critereTrieTopo          = getChamp (request, CHAMP_CRITERE_TRIE_TOPO);
		
		List<Topo> topos=null;
		
		
		try {
			
			
				
				topos = topoDao.rechercher(dateParution, nom, disponibilite, critereTrieTopo);
				
				if(topos.size()!= 0 ) {
							
				resultat = "Liste des topos"; 
					
				}else {
					
			    resultat = "Aucun topo ne correspond à vos critères de recherche";
			    
		
					
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

     private String getChamp (HttpServletRequest request, String parameter) {
	
	 String valeur = request.getParameter(parameter);
	
	 if(valeur == null) return null;
	
	 else {
		 
		 return valeur;
	 
	 }
  }

}
