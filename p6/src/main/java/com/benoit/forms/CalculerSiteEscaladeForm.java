package com.benoit.forms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.benoit.entities.Secteur;
import com.benoit.entities.SiteEscalade;
import com.benoit.entities.Voie;

public class CalculerSiteEscaladeForm {
	
	@SuppressWarnings("rawtypes")
	public Map<String, Integer> trouverCotationMax(Set<SiteEscalade> sitesEscalade) {
		
		Map<String, Integer> mapCotationMax = new HashMap<>();
		
		Iterator isi = sitesEscalade.iterator();
		
		while (isi.hasNext()) {
			
			SiteEscalade siteEscalade = (SiteEscalade) isi.next();		   
			
			List<Integer> sortedList = new ArrayList<>();
					
			Set<Secteur> secteurs = siteEscalade.getSecteurs();
			
			Iterator is = secteurs.iterator();
			
			while (is.hasNext()) {
				
				 Secteur secteur = (Secteur) is.next();
				
				 Set<Voie> voies = secteur.getVoies();
				 
				 Iterator iv = voies.iterator();
				 
				 while (iv.hasNext()) {
					 
					 Voie voie = (Voie) iv.next();
					 
					 sortedList.add(voie.getCotationVoie());
					 
					 
				 }
			}
			
			Collections.sort(sortedList);
				
			mapCotationMax.put(siteEscalade.getNom(), sortedList.get(sortedList.size() - 1)) ;
			
			
			
		}
		return mapCotationMax;
		}
	
	@SuppressWarnings("rawtypes")
	public Map<SiteEscalade, Integer> trouverCotationMaxObject(Set<SiteEscalade> sitesEscalade) {
		
		Map<SiteEscalade, Integer> mapCotationMax = new HashMap<>();
		
		Iterator isi = sitesEscalade.iterator();
		
		while (isi.hasNext()) {
			
			SiteEscalade siteEscalade = (SiteEscalade) isi.next();		   
			
			List<Integer> sortedList = new ArrayList<>();
					
			Set<Secteur> secteurs = siteEscalade.getSecteurs();
			
			Iterator is = secteurs.iterator();
			
			while (is.hasNext()) {
				
				 Secteur secteur = (Secteur) is.next();
				
				 Set<Voie> voies = secteur.getVoies();
				 
				 Iterator iv = voies.iterator();
				 
				 while (iv.hasNext()) {
					 
					 Voie voie = (Voie) iv.next();
					 
					 sortedList.add(voie.getCotationVoie());
					 
					 
				 }
			}
			
			Collections.sort(sortedList);
				
			mapCotationMax.put(siteEscalade, sortedList.get(sortedList.size() - 1)) ;
			
			
			
		}
		return mapCotationMax;
		}
		
	@SuppressWarnings("rawtypes")
	public Map<String, Integer> trouverCotationMin(Set<SiteEscalade> sitesEscalade) {
		
		
		Map<String, Integer> mapCotationMin = new HashMap<>();
		
		Iterator isi = sitesEscalade.iterator();
		
		while (isi.hasNext()) {
			
			SiteEscalade siteEscalade = (SiteEscalade) isi.next();		   
			
			List<Integer> sortedList = new ArrayList<>();
					
			Set<Secteur> secteurs = siteEscalade.getSecteurs();
			
			Iterator is = secteurs.iterator();
			
			while (is.hasNext()) {
				
				 Secteur secteur = (Secteur) is.next();
				
				 Set<Voie> voies = secteur.getVoies();
				 
				 Iterator iv = voies.iterator();
				 
				 while (iv.hasNext()) {
					 
					 Voie voie = (Voie) iv.next();
					 
					 sortedList.add(voie.getCotationVoie());
					 
						
					 
				 }
			}
			
			Collections.sort(sortedList);
			
			mapCotationMin.put(siteEscalade.getNom(), sortedList.get(0)) ;
			
			
			
		}
		return mapCotationMin;
		
	}
	
	@SuppressWarnings("rawtypes")
	public Map<SiteEscalade, Integer> trouverCotationMinObject(Set<SiteEscalade> sitesEscalade) {
		
		
		Map<SiteEscalade, Integer> mapCotationMin = new HashMap<>();
		
		Iterator isi = sitesEscalade.iterator();
		
		while (isi.hasNext()) {
			
			SiteEscalade siteEscalade = (SiteEscalade) isi.next();		   
			
			List<Integer> sortedList = new ArrayList<>();
					
			Set<Secteur> secteurs = siteEscalade.getSecteurs();
			
			Iterator is = secteurs.iterator();
			
			while (is.hasNext()) {
				
				 Secteur secteur = (Secteur) is.next();
				
				 Set<Voie> voies = secteur.getVoies();
				 
				 Iterator iv = voies.iterator();
				 
				 while (iv.hasNext()) {
					 
					 Voie voie = (Voie) iv.next();
					 
					 sortedList.add(voie.getCotationVoie());
					 
						
					 
				 }
			}
			
			Collections.sort(sortedList);
			
			mapCotationMin.put(siteEscalade, sortedList.get(0)) ;
			
			
			
		}
		return mapCotationMin;
		
	}
	
	@SuppressWarnings("rawtypes")
	public Map<String, Integer> trouverNombreSecteur(Set<SiteEscalade> sitesEscalade) {
		
		Map<String, Integer> mapNombresSecteur = new HashMap<>();
		
        Iterator isi = sitesEscalade.iterator();
		
		while (isi.hasNext()) {
			
            SiteEscalade siteEscalade = (SiteEscalade) isi.next();		   
					
			Set<Secteur> secteurs = siteEscalade.getSecteurs();
			
			Integer nombreSecteur = new Integer(secteurs.size());
			
			mapNombresSecteur.put(siteEscalade.getNom(), nombreSecteur);
		}
		return mapNombresSecteur;
		
	}

}
