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
import com.benoit.forms.MajCommentaireForm;
import com.benoit.forms.TrouverCommentaireForm;
import com.benoit.forms.TrouverTopoForm;

@WebServlet( "/ModifierCommentaire" )
public class ModifierCommentaire extends HttpServlet {
	private static final long serialVersionUID       = 1L;
	private static final String ATT_ID               = "idCommentaire";
	private static final String ATT_COMMENTAIRE      = "commentaire";
	private static final String ATT_FORM             = "form";
	private static final String VUE_LIST_COMMENTAIRE = "/p6/ListerCommentaires";
	private static final String VUE_COMMENTAIRE      = "/restreint/AfficherCommentaire.jsp"; 
	
	CommentaireDao commentaireDao = null;

    public ModifierCommentaire() {
        super();
      
    }
    
    public void init() {
    	DaoSession daoSession = new DaoSession();
    	this.commentaireDao = daoSession.getCommentaireDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter(ATT_ID);
		
		 request.setCharacterEncoding("UTF-8");
		
		TrouverCommentaireForm form = new TrouverCommentaireForm(commentaireDao);
		
		Commentaire commentaire = form.trouver(id);
		
		
		HttpSession session = request.getSession();
		
		session.setAttribute(ATT_COMMENTAIRE, commentaire);
		
		
		this.getServletContext().getRequestDispatcher( VUE_COMMENTAIRE ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		MajCommentaireForm formC = new MajCommentaireForm(commentaireDao);
		
		HttpSession session = request.getSession();
		
		Commentaire commentaire = formC.majCommentaire(request,session);
		
		request.setAttribute(ATT_FORM, formC);
		request.setAttribute(ATT_COMMENTAIRE, commentaire);
		
		if(formC.getErreurs().isEmpty()) {
			
			response.sendRedirect(VUE_LIST_COMMENTAIRE);
			
		} else {
			
			this.getServletContext().getRequestDispatcher( VUE_COMMENTAIRE ).forward( request, response );
		}
	 }

}
