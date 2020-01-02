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
import com.benoit.dao.interfaceDao.AdherentDao;
import com.benoit.dao.interfaceDao.SiteEscaladeDao;
import com.benoit.entities.Adherent;
import com.benoit.entities.SiteEscalade;
import com.benoit.entities.Topo;
import com.benoit.forms.CalculerSiteEscaladeForm;
import com.benoit.forms.ConnexionForm;
import com.benoit.forms.FiltrerSitesEscaladeForm;
import com.benoit.forms.ListerSitesEscaladeForm;

@WebServlet( "/ListerSitesEscalade" )
public class ListerSitesEscalade extends HttpServlet {
	private static final long serialVersionUID       = 1L;
	private static final String ATT_LIST             = "listerSiteEscalade";
    private static final String ATT_FORM             = "form";
    private static final String ATT_MAP              = "mapSitesEscalade";
    private static final String VUE_SITE_ESCALADE    = "/ListerSitesEscalade.jsp";
	private static final String ATT_CMAL             = "mapCotationMax";
	private static final String ATT_CMIL             = "mapCotationMin";
	private static final String ATT_NS               = "mapNombresSecteur";
	
    SiteEscaladeDao siteEscaladeDao = null;
       

    public ListerSitesEscalade() {
        super();
      
    }
    public void init() {
    DaoSession daoSession = new DaoSession();
    this.siteEscaladeDao = daoSession.getSiteEscaladeDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		        ListerSitesEscaladeForm form = new ListerSitesEscaladeForm(siteEscaladeDao);

	            Set<SiteEscalade> sitesEscalade = form.lister();
	            
	            Map<Long, SiteEscalade> mapSitesEscalade = new HashMap<Long, SiteEscalade>();
	            for (  SiteEscalade siteEscalade : sitesEscalade) {
	            mapSitesEscalade.put( siteEscalade.getId(),siteEscalade );
	            }

	            CalculerSiteEscaladeForm calcul = new CalculerSiteEscaladeForm();
	            
	            Map<String, Integer>mapCotationMax = calcul.trouverCotationMax(sitesEscalade);
	            
	            Map<String, Integer>mapCotationMin = calcul.trouverCotationMin(sitesEscalade);
	            
	            Map<String, Integer>mapNombresSecteur = calcul.trouverNombreSecteur(sitesEscalade);
	            
	            request.setAttribute( ATT_CMAL, mapCotationMax );
	            
	            request.setAttribute( ATT_CMIL, mapCotationMin );
	            
	            request.setAttribute( ATT_NS, mapNombresSecteur );
	            
	        	request.setAttribute( ATT_FORM, form );
	        	
	        	request.setAttribute( ATT_LIST, sitesEscalade );
	        	
	        	request.setAttribute( ATT_MAP, mapSitesEscalade );
	            
	            this.getServletContext().getRequestDispatcher(VUE_SITE_ESCALADE).forward( request, response );
	}

	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

     
		        FiltrerSitesEscaladeForm form = new FiltrerSitesEscaladeForm(siteEscaladeDao);

		        Set<SiteEscalade> sitesEscalade = form.filtrer(request);
	            
	            Map<Long, SiteEscalade> mapSitesEscalade = new HashMap<Long, SiteEscalade>();
	            for (  SiteEscalade siteEscalade : sitesEscalade) {
	            mapSitesEscalade.put( siteEscalade.getId(),siteEscalade );
	            }

	            CalculerSiteEscaladeForm calcul = new CalculerSiteEscaladeForm();
	            
                Map<String, Integer>mapCotationMax = calcul.trouverCotationMax(sitesEscalade);
	            
	            Map<String, Integer>mapCotationMin = calcul.trouverCotationMin(sitesEscalade);
	            
	            Map<String, Integer>mapNombresSecteur = calcul.trouverNombreSecteur(sitesEscalade);
	            
	            request.setAttribute( ATT_CMAL, mapCotationMax );
	            
	            request.setAttribute( ATT_CMIL, mapCotationMin );
	            
	            request.setAttribute( ATT_NS, mapNombresSecteur );
	          
	        	request.setAttribute( ATT_FORM, form );
	        	
	        	request.setAttribute( ATT_LIST, sitesEscalade );
	        	
	        	request.setAttribute( ATT_MAP, mapSitesEscalade );
	            
	            this.getServletContext().getRequestDispatcher(VUE_SITE_ESCALADE).forward( request, response );
	}

	}
	


