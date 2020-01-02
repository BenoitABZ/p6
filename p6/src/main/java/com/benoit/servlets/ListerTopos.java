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
import com.benoit.dao.interfaceDao.TopoDao;
import com.benoit.entities.Topo;
import com.benoit.forms.ConvertDateFrForm;
import com.benoit.forms.ListerToposForm;


/**
 * Servlet implementation class ListerTopo
 */
@WebServlet( "/ListerTopos" )
public class ListerTopos extends HttpServlet {
	private static final long serialVersionUID       = 1L;
	private static final String ATT_LIST             = "listeTopos";
	private static final String ATT_FORM             = "form";
	private static final String VUE_TOPO             = "/restreint/ListerTopos.jsp";
	private static final String ATT_MAP              = "mapTopos";
	private static final String ATT_ADHERENT         = "adherent";
	private static final String ATT_FORMC            = "formC";
	private static final String ATT_LIST_DATE        = "datesParution";
	TopoDao topoDao = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListerTopos() {
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
		
            ListerToposForm form = new ListerToposForm(topoDao);

           /* Traitement de la requête et récupération du bean en résultant */
            List<Topo> topos = form.listerTopos(request);
            
            ConvertDateFrForm formC = new ConvertDateFrForm(); 
            
            Set<String> datesParution = formC.convertFRTopo(topos);
            
            Map<Long, Topo> mapTopos = new HashMap<Long, Topo>();
            for (  Topo  topo : topos) {
            mapTopos.put( topo.getId(),topo );
            }
            
            request.setAttribute( ATT_MAP, mapTopos );
     
        	request.setAttribute( ATT_FORM, form );
        	
        	request.setAttribute( ATT_FORMC, formC );
        	
        	request.setAttribute( ATT_LIST, topos );
        	
        	request.setAttribute(ATT_LIST_DATE, datesParution);
        	
        	HttpSession session = request.getSession();
        	
        	if (session.getAttribute(ATT_ADHERENT) != null) {
        		
        		this.getServletContext().getRequestDispatcher( VUE_TOPO).forward( request, response );
        	}else {
        		
        		response.sendRedirect(request.getContextPath() + VUE_TOPO);
        	}
        	
		    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
