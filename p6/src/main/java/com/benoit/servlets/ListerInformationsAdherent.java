package com.benoit.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.benoit.dao.daoSession.DaoSession;
import com.benoit.dao.interfaceDao.AdherentDao;

@WebServlet( "/ListerInformationsAdherent" )
public class ListerInformationsAdherent extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final String VUE_ADHERENT    = "/p6/restreint/ListerInformationsAdherent.jsp";
    AdherentDao adherentDao = null;
 
    public ListerInformationsAdherent() {
        super();
       
    }
    
    public void init() {
    	DaoSession daoSession = new DaoSession();
    	this.adherentDao = daoSession.getAdherentDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
    response.sendRedirect(VUE_ADHERENT);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
