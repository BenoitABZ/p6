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

/**
 * Servlet implementation class AjouterCommentaire
 */
@WebServlet( "/AjouterCommentaire" )
public class AjouterCommentaire extends HttpServlet {
	private static final long serialVersionUID         = 1L;
	private static final String ATT_FORM               = "form";
	private static final String VUE_LIST               = "/p6/ListerCommentaires";
	private static final String ATT_COMMENTAIRE        = "commentaire";
	private static final String VUE_COMMENTAIRE        = "/restreint/AjouterCommentaire.jsp";
    CommentaireDao commentaireDao = null;
       
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterCommentaire() {
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
		response.sendRedirect(request.getContextPath() + VUE_COMMENTAIRE);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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


