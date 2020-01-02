package com.benoit.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.benoit.dao.daoSession.DaoSession;
import com.benoit.dao.interfaceDao.AdherentDao;
import com.benoit.dao.interfaceDao.TopoDao;
import com.benoit.entities.Topo;
import com.benoit.forms.AjouterTopoForm;

/**
 * Servlet implementation class AjouterTopo
 */
@WebServlet( "/AjouterTopo" )
public class AjouterTopo extends HttpServlet {
	private static final long serialVersionUID   = 1L;
	private static final String ATT_FORM         = "form";
	private static final String VUE_LIST         = "/p6/ListerTopos";
	private static final String ATT_TOPO         = "topo";
	private static final String VUE_TOPO1         = "/p6/restreint/AjouterTopo.jsp";
	private static final String VUE_TOPO2         = "/restreint/AjouterTopo.jsp";
    TopoDao topoDao = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterTopo() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	DaoSession daoSession = new DaoSession();
    	this.topoDao = daoSession.getTopoDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect(VUE_TOPO1);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AjouterTopoForm form = new AjouterTopoForm(topoDao);
		
		HttpSession session = request.getSession();
		
		 request.setCharacterEncoding("UTF-8");
		
		Topo topo = form.creerTopo(request, session);
		
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_TOPO, topo);
		
		if ( form.getErreurs().isEmpty() ) {
			
			response.sendRedirect(VUE_LIST);
		}
		
		else {
			
			this.getServletContext().getRequestDispatcher( VUE_TOPO2 ).forward( request, response );
		}
		
	}

}
