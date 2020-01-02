package com.benoit.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.benoit.dao.daoSession.DaoSession;
import com.benoit.dao.interfaceDao.AdherentDao;

/**
 * Servlet implementation class InformationsAdherent
 */
@WebServlet( "/ListerInformationsAdherent" )
public class ListerInformationsAdherent extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final String VUE_ADHERENT    = "/p6/restreint/ListerInformationsAdherent.jsp";
    AdherentDao adherentDao = null;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListerInformationsAdherent() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	DaoSession daoSession = new DaoSession();
    	this.adherentDao = daoSession.getAdherentDao();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
    response.sendRedirect(VUE_ADHERENT);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
