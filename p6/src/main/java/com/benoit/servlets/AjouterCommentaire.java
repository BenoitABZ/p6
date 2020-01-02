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
import com.benoit.forms.AjouterCommentaireForm;
import com.benoit.forms.AjouterTopoForm;


@WebServlet( "/AjouterCommentaire" )
public class AjouterCommentaire extends HttpServlet {
	private static final long serialVersionUID         = 1L;
	private static final String ATT_FORM               = "form";
	private static final String VUE_LIST               = "/p6/ListerCommentaires";
	private static final String ATT_COMMENTAIRE        = "commentaire";
	private static final String VUE_COMMENTAIRE        = "/restreint/AjouterCommentaire.jsp";
    CommentaireDao commentaireDao = null;
       

    public AjouterCommentaire() {
        super();
    
    }
    
    public void init() {
    	DaoSession daoSession = new DaoSession();
    	this.commentaireDao = daoSession.getCommentaireDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect(request.getContextPath() + VUE_COMMENTAIRE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    AjouterCommentaireForm form = new AjouterCommentaireForm(commentaireDao);
	    
	    request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		Commentaire commentaire = form.creerCommentaire(request, session);
		
		request.setAttribute(ATT_FORM, form);
		
		request.setAttribute(ATT_COMMENTAIRE, commentaire);
		
		if ( form.getErreurs().isEmpty() ) {
			
			response.sendRedirect(VUE_LIST);
		}
		
		else {
			
			this.getServletContext().getRequestDispatcher( VUE_COMMENTAIRE ).forward( request, response );
		}
		
	}
	}


