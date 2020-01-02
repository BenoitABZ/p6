package com.benoit.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.benoit.dao.daoSession.DaoSession;
import com.benoit.dao.interfaceDao.CommentaireDao;
import com.benoit.dao.interfaceDao.TopoDao;
import com.benoit.entities.Commentaire;
import com.benoit.entities.Topo;
import com.benoit.forms.SupprimerCommentaireForm;
import com.benoit.forms.SupprimerTopoForm;
import com.benoit.forms.TrouverCommentaireForm;
import com.benoit.forms.TrouverTopoForm;

@WebServlet( "/SupprimerCommentaire" )
public class SupprimerCommentaire extends HttpServlet {
	private static final long serialVersionUID         = 1L;
	private static final String ATT_ID                 = "idCommentaire";
	private static final String ATT_COMMENTAIRE        = "commentaire";
	private static final String VUE_LISTE_COMMENTAIRE  = "/p6/ListerCommentaires";
	private static final String ATT_FORM_S             = "formS";
	private static final String ATT_FORM_T             = "formT";
	CommentaireDao commentaireDao = null;

    public SupprimerCommentaire() {
        super();
      
    }
    
    public void init() {
    	DaoSession daoSession = new DaoSession();
    	this.commentaireDao = daoSession.getCommentaireDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String id = request.getParameter(ATT_ID);
		
		TrouverCommentaireForm formT = new TrouverCommentaireForm(commentaireDao);
		
		Commentaire commentaire = formT.trouver(id);
			
		HttpSession session = request.getSession();
		
		request.setAttribute(ATT_COMMENTAIRE, commentaire);
		
		SupprimerCommentaireForm formS = new SupprimerCommentaireForm(commentaireDao);
		
		formS.supprimer(request, session);
		
		request.setAttribute(ATT_FORM_S, formS);
		
		request.setAttribute(ATT_FORM_T, formT);
		
		response.sendRedirect(VUE_LISTE_COMMENTAIRE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
