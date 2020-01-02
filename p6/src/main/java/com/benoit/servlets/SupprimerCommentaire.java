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

/**
 * Servlet implementation class SupprimerCommentaire
 */
@WebServlet( "/SupprimerCommentaire" )
public class SupprimerCommentaire extends HttpServlet {
	private static final long serialVersionUID         = 1L;
	private static final String ATT_ID                 = "idCommentaire";
	private static final String ATT_COMMENTAIRE        = "commentaire";
	private static final String VUE_LISTE_COMMENTAIRE  = "/p6/ListerCommentaires";
	private static final String ATT_FORM_S             = "formS";
	private static final String ATT_FORM_T             = "formT";
	CommentaireDao commentaireDao = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerCommentaire() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	DaoSession daoSession = new DaoSession();
    	this.commentaireDao = daoSession.getCommentaireDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
