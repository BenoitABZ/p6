package com.benoit.servlets;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.benoit.dao.daoSession.DaoSession;
import com.benoit.dao.interfaceDao.CommentaireDao;
import com.benoit.entities.Adherent;
import com.benoit.entities.Commentaire;
import com.benoit.forms.ConvertDateFrForm;
import com.benoit.forms.ListerCommentairesForm;

@WebServlet( "/ListerCommentaires" )
public class ListerCommentaires extends HttpServlet {
	private static final long serialVersionUID       = 1L;
	private static final String ATT_LIST             = "listeCommentaires";
	private static final String ATT_FORM             = "form";
	private static final String ATT_MAP              = "mapCommentaires";
	private static final String VUE_COMMENTAIRE      = "/restreint/ListerCommentaires.jsp";
	private static final String ATT_ADHERENT         = "adherent";
	private static final String ATT_FORMC            = "formC";
	private static final String ATT_LIST_DATE        = "datesCommentaire";
	
	CommentaireDao commentaireDao = null;

    public ListerCommentaires() {
        super();
      
    }
    
    public void init() {
        DaoSession daoSession = new DaoSession();
        this.commentaireDao = daoSession.getCommentaireDao();
    
        }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        ListerCommentairesForm form = new ListerCommentairesForm(commentaireDao);
         
        HttpSession session = request.getSession();

        List<Commentaire> commentaires = form.listerCommentaires(request, session);
        
        ConvertDateFrForm formC = new ConvertDateFrForm(); 
        
        Set<String> datesCommentaire = formC.convertFRCommentaire(commentaires);
        
   	    Map<Long, Commentaire> mapCommentaires = new HashMap<Long, Commentaire>();
        for ( Commentaire commentaire : commentaires) {
        mapCommentaires.put( commentaire.getId(),commentaire );
     }

    	request.setAttribute( ATT_FORM, form );
    	
    	request.setAttribute( ATT_MAP, mapCommentaires );
    	
    	request.setAttribute( ATT_LIST, commentaires );
    	
    	request.setAttribute( ATT_FORMC, formC );
    	
    	request.setAttribute(ATT_LIST_DATE, datesCommentaire);
    	
    	if (session.getAttribute(ATT_ADHERENT) != null) {
    		
    		this.getServletContext().getRequestDispatcher( VUE_COMMENTAIRE).forward( request, response );
    	}else {
    		
    		response.sendRedirect(request.getContextPath() + VUE_COMMENTAIRE);
    	}
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
