package com.benoit.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.benoit.dao.daoSession.DaoSession;
import com.benoit.dao.interfaceDao.AdherentDao;
import com.benoit.dao.interfaceDao.ReservationDao;
import com.benoit.dao.interfaceDao.TopoDao;
import com.benoit.entities.Adherent;
import com.benoit.entities.Reservation;
import com.benoit.forms.AccepterReservationForm;
import com.benoit.forms.TrouverAdherentForm;
import com.benoit.forms.TrouverReservationForm;
import com.benoit.forms.UpgradeAdherentForm;

@WebServlet( "/UpgradeAdherent" )
public class UpgradeAdherent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_ADHERENT             = "adherent";
	private static final String ATT_FORM_T               = "formT";
	private static final String ATT_FORM_U               = "formU";
	private static final String VUE_LISTE_ADHERENT       = "/AfficherListeAdherent";
	AdherentDao adherentDao = null;

    public UpgradeAdherent() {
        super();
    
    }
    
    public void init() {
    	DaoSession daoSession = new DaoSession();
    	this.adherentDao = daoSession.getAdherentDao();
  
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TrouverAdherentForm formT = new TrouverAdherentForm(adherentDao);
		
		Adherent adherent = formT.trouver(request);
	
        UpgradeAdherentForm formU = new UpgradeAdherentForm(adherentDao);
        		
        adherent = formU.upgradeAdherent(adherent);	
        
        System.out.println(adherent.getMembre());
        
        request.setAttribute(ATT_ADHERENT, adherent);
        request.setAttribute(ATT_FORM_T, formT);
        request.setAttribute(ATT_FORM_U, formU);
		
		this.getServletContext().getRequestDispatcher(VUE_LISTE_ADHERENT).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
