<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%@ page isELIgnored ="false" %>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des Sites d'escalade</title>
</head>
<body>
    <c:import url="/Header.jsp" />
    <c:import url="/Menu.jsp" />
    
    <section>
    
    <h2 class = title_section>Annuaire des sites d'escalade</h2>
    
    <fieldset class="fieldset_critere">
    <legend class = title_critere>Recherche par critères</legend>
    
    <form method="post" action="<c:url value="/ListerSitesEscalade"/>">
   
    <div class=container_form_critere>
    <select name="departement" id="departement">
			<option value="<c:out value="${''}"></c:out>">Département</option>
			<option value="Ain" >01 -- Ain</option>
			<option value="Aisne" >02 -- Aisne</option>
			<option value="Allier" >03 -- Allier</option>
			<option value="Alpes de Haute Provence" >04 -- Alpes de Haute Provence</option>
			<option value="Alpes Maritimes" >06 -- Alpes Maritimes</option>
			<option value="Ardèche" >07 -- Ardèche</option>
			<option value="Ardennes" >08 -- Ardennes</option>
			<option value="Ariège" >09 -- Ariège</option>
			<option value="Aube" >10 -- Aube</option>
			<option value="Aveyron" >12 -- Aveyron</option>
			<option value="Bas-Rhin" >67 -- Bas-Rhin</option>
			<option value="Bouches du Rhône" >13 -- Bouches du Rhône</option>
			<option value="Calvados" >14 -- Calvados</option>
			<option value="Cantal" >15 -- Cantal</option>
			<option value="Charente" >16 -- Charente</option>
			<option value="Charente Maritime" >17 -- Charente Maritime</option>
			<option value="Cher" >18 -- Cher</option>
			<option value="Corrèze" >19 -- Corrèze</option>
			<option value="Côte d'or" >21 -- Côte d'or</option>
			<option value="Cotes d'Armor" >22 -- Cotes d'Armor</option>
			<option value="Creuse" >23 -- Creuse</option>
			<option value="Deux Sèvres" >79 -- Deux Sèvres</option>
			<option value="Dordogne" >24 -- Dordogne</option>
			<option value="Doubs" >25 -- Doubs</option>
			<option value="Drôme" >26 -- Drôme</option>
			<option value="Essonne" >91 -- Essonne</option>
			<option value="Eure" >27 -- Eure</option>
			<option value="Eure-et-Loir" >28 -- Eure-et-Loir</option>
			<option value="Finistère" >29 -- Finistère</option>
			<option value="Gard" >30 -- Gard</option>
			<option value="Gers" >32 -- Gers</option>
			<option value="Gironde" >33 -- Gironde</option>
			<option value="Haut-Rhin" >68 -- Haut-Rhin</option>
			<option value="Haute Garonne" >31 -- Haute Garonne</option>
			<option value="Haute Loire" >43 -- Haute Loire</option>
			<option value="Haute-Savoie" >74 -- Haute-Savoie</option>
			<option value="Haute-Vienne" >87 -- Haute-Vienne</option>
			<option value="Hautes Pyrénées" >65 -- Hautes Pyrénées</option>
			<option value="Hautes-Alpes" >05 -- Hautes-Alpes</option>
			<option value="Hauts de Seine" >92 -- Hauts de Seine</option>
			<option value="Hérault" >34 -- Hérault</option>
			<option value="Ille et Vilaine" >35 -- Ille et Vilaine</option>
			<option value="Indre" >36 -- Indre</option>
			<option value="Indre et Loire" >37 -- Indre et Loire</option>
			<option value="Isère" >38 -- Isère</option>
			<option value="Jura" >39 -- Jura</option>
			<option value="Landes" >40 -- Landes</option>
			<option value="Loir et Cher" >41 -- Loir et Cher</option>
			<option value="Loire" >42 -- Loire</option>
			<option value="Loire Atlantique" >44 -- Loire Atlantique</option>
			<option value="Loiret" >45 -- Loiret</option>
			<option value="Lot" >46 -- Lot</option>
			<option value="Lot et Garonne" >47 -- Lot et Garonne</option>
			<option value="Lozère" >48 -- Lozère</option>
			<option value="Maine et Loire" >49 -- Maine et Loire</option>
			<option value="Manche" >50 -- Manche</option>
			<option value="Marne" >51 -- Marne</option>
			<option value="Marne Haute" >52 -- Marne Haute</option>
			<option value="Martinique" >972 -- Martinique</option>
			<option value="Mayenne" >53 -- Mayenne</option>
			<option value="Meurthe et Moselle" >54 -- Meurthe et Moselle</option>
			<option value="Meuse" >55 -- Meuse</option>
			<option value="Morbihan" >56 -- Morbihan</option>
			<option value="Moselle" >57 -- Moselle</option>
			<option value="Nièvre" >58 -- Nièvre</option>
			<option value="Nord" >59 -- Nord</option>
		    <option value="Oise" >60 -- Oise</option>
		    <option value="Orne" >61 -- Orne</option>
		    <option value="Paris" >75 -- Paris</option>
		    <option value="Pas de Calais" >62 -- Pas de Calais</option>
		    <option value="Polynésie Française" >987 -- Polynésie Française</option>
		    <option value="Puy-de-Dôme" >63 -- Puy-de-Dôme</option>
		    <option value="Pyrénées Orientales" >66 -- Pyrénées Orientales</option>
		    <option value="Pyrénées-Atlantiques" >64 -- Pyrénées-Atlantiques</option>
		    <option value="Réunion" >974 -- Réunion</option>
		    <option value="Rhône" >69 -- Rhône</option>
		    <option value="Saint-Pierre-et-Miquelon" >975 -- Saint-Pierre-et-Miquelon</option>
		    <option value="Saône et loire" >71 -- Saône et loire</option>
		    <option value="Saône haute" >70 -- Saône haute</option>
		    <option value="Sarthe" >72 -- Sarthe</option>
		    <option value="Savoie" >73 -- Savoie</option>
		    <option value="Seine et Marne" >77 -- Seine et Marne</option>
		    <option value="Seine Maritime" >76 -- Seine Maritime</option>
		    <option value="Seine Saint-Denis" >93 -- Seine Saint-Denis</option>
		    <option value="Somme" >80 -- Somme</option>
		    <option value="Tarn">81 -- Tarn</option>
		    <option value="Tarn et Garonne" >82 -- Tarn et Garonne</option>
		    <option value="Territoire de Belfort" >90 -- Territoire de Belfort</option>
		    <option value="Val d'Oise" >95 -- Val d'Oise</option>
		    <option value="Val de Marne" >94 -- Val de Marne</option>
		    <option value="Var" >83 -- Var</option>
		    <option value="Vaucluse" >84 -- Vaucluse</option>
		    <option value="Vendée" >85 -- Vendée</option>
		    <option value="Vienne" >86 -- Vienne</option>
		    <option value="Vosges" >88 -- Vosges</option>
		    <option value="Yonne" >89 -- Yonne</option>
		    <option value="Yvelines" >78 -- Yvelines</option>
			</select>
			
    
    <select name="cotationMin" id="cotationMin">
			<option value="1">Cotation minimum</option>
			<option value="1" >1</option>
			<option value="2" >2</option>
			<option value="3" >3</option>
			<option value="4" >4</option>
			<option value="5" >5</option>
			<option value="6" >6</option>
			<option value="7" >7</option>
			<option value="8" >8</option>
    </select>
    
       
    <select name="cotationMax" id="cotationMax">
			<option value="8">Cotation maximum</option>
			<option value="1" >1</option>
			<option value="2" >2</option>
			<option value="3" >3</option>
			<option value="4" >4</option>
			<option value="5" >5</option>
			<option value="6" >6</option>
			<option value="7" >7</option>
			<option value="8" >8</option>
    </select>
    
                
    <select name="secteurMin" id="secteurMin">
			<option value="1">Nombre de secteur minimum</option>
			<option value="1" >1</option>
			<option value="2" >2</option>
			<option value="3" >3</option>
			<option value="4" >4</option>
			<option value="5" >5</option>
			<option value="6" >6</option>
			<option value="7" >7</option>
			<option value="8" >8</option>
    </select>
    
            
    <select name="secteurMax" id="secteurMax">
			<option value="8">Nombre de secteur maximum</option>
			<option value="1" >1</option>
			<option value="2" >2</option>
			<option value="3" >3</option>
			<option value="4" >4</option>
			<option value="5" >5</option>
			<option value="6" >6</option>
			<option value="7" >7</option>
			<option value="8" >8</option>
    </select>
    

              
    <select name="critereTrieSite" id="critereTrieSite">
			<option value="se.nom">Trier par</option>
			<option value="se.nom" >nom</option>
			
			
	
	
    </select>
    <input type="submit" value="Filtrer" class="green-button-special" />
    </div>
    
    
     
    
     </form>
     
   </fieldset> 
    
    <table>
             <caption class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</caption>   
             <c:if test="${!empty requestScope.mapSitesEscalade}"> 
             <tr>
                    <th>Nom</th>
                    <th>Département</th>
                    <th>Commune</th>                   
                    <th>Cotation Min</th>
                    <th>Cotation Max</th>        
                    <th>Nombre de secteurs</th>
                                     
                </tr>
                <%-- Parcours de la liste des contacts, et utilisation de l'objet varStatus. --%>
                
                 
                <c:forEach items="${ requestScope.mapSitesEscalade }" var="mapSites" varStatus="boucle1">
                    <c:forEach items="${ requestScope.mapCotationMin }" var="mapCotationMin" varStatus="boucle2">
                        <c:forEach items="${ requestScope.mapCotationMax }" var="mapCotationMax" varStatus="boucle3">
                            <c:forEach items="${ requestScope.mapNombresSecteur }" var="mapNombreSecteur" varStatus="boucle4">
                    <tr>
                  
                    
                    <c:if test="${mapSites.value.nom == mapCotationMin.key && mapSites.value.nom == mapCotationMax.key && mapSites.value.nom == mapNombreSecteur.key}">
                    
                    <td class = "for_display"><a href="<c:url value="/AfficherSiteEscalade"><c:param name="idSiteEscalade" value="${ mapSites.key }" /></c:url>"><c:out value="${ mapSites.value.nom }"></c:out></a></td>
                    <td><c:out value="${mapSites.value.departement}"></c:out></td>
                    <td><c:out value="${mapSites.value.commune }"></c:out></td>
                    <td><c:out value="${mapCotationMin.value}"></c:out></td>
                    <td><c:out value="${mapCotationMax.value}"></c:out></td>
                    <td><c:out value="${mapNombreSecteur.value}"></c:out></td>
                    </c:if>
                    
                 
                    </tr>
                    
                  
                   
                    </c:forEach>
                    
                   </c:forEach>
                  </c:forEach>
                </c:forEach>                    
              </c:if>     
            </table>
    

    
    <p><a class = "blue-link" href="<c:url value="/AjouterSiteEscalade1"/>">Ajouter un site d'escalade</a></p>
    
    
 </section>
    
</body>
</html>