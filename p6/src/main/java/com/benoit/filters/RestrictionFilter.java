package com.benoit.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class RestrictionFilter
 */
@WebFilter( urlPatterns = "/restreint/*" )
public class RestrictionFilter implements Filter {
	
	 public static final String VUE_CONNEXION     = "/Connexion";
	 public static final String ATT_ADHERENT     = "adherent";

    /**
     * Default constructor. 
     */
    public RestrictionFilter() {
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
        /* Cast des objets request et response */
	      HttpServletRequest request = (HttpServletRequest) req;
	      HttpServletResponse response = (HttpServletResponse) res;
	      
	      /* Non-filtrage des ressources statiques */
	        String chemin = request.getRequestURI().substring( request.getContextPath().length() );
	        if ( chemin.startsWith( "/inc" ) ) {
	            chain.doFilter( request, response );
	            return;
	        }

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        /**
         * Si l'objet utilisateur n'existe pas dans la session en cours, alors
         * l'utilisateur n'est pas connecté.
         */
        if ( session.getAttribute( ATT_ADHERENT ) == null ) {
          
        	 request.getRequestDispatcher( VUE_CONNEXION ).forward( request, response );
        } else {
            /* Affichage de la page restreinte */
        	request.setCharacterEncoding("UTF-8");
            chain.doFilter( request, response );
        }
	}



}
