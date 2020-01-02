package com.benoit.forms;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.benoit.entities.Commentaire;
import com.benoit.entities.Reservation;
import com.benoit.entities.Topo;

public class ConvertDateFrForm {
	
	public Set<String> convertFRTopo (List<Topo> topos) {
		
		
		
		Set<String> topoDates = new HashSet<>();
		
        Iterator<Topo> it = topos.iterator();
	    
		 
	    
			    while (it.hasNext()) {
			    	
			    	Topo topo = (Topo) it.next();
			    	
			    	LocalDate date = topo.getDateParution();
			    	
			        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-uuuu");
			        
			        String text = date.format(formatters);
			        
			        topoDates.add(text);
			    }
			    
			    return topoDates;
	}
	
	public Set<String> convertFRCommentaire (List<Commentaire> commentaires) {
		
		
		
		Set<String> commentaireDates = new HashSet<>();
		
        Iterator<Commentaire> it = commentaires.iterator();
	    
		 
	    
			    while (it.hasNext()) {
			    	
			    	Commentaire commentaire = (Commentaire) it.next();
			    	
			    	LocalDate date = commentaire.getDateCommentaire();
			    	
			        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-uuuu");
			        
			        String text = date.format(formatters);
			        
			        commentaireDates.add(text);
			    }
			    
			    return commentaireDates;
	}
	public String convertFRReservation (Reservation reservation) {
	
	                if (reservation == null) {
	                	return null;
	                }
			    	LocalDate date = reservation.getDateReservation();			    	
			        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-uuuu");
			        
			        String text = date.format(formatters);
			        
			        
			    
			    
			    return text;
	}
}
