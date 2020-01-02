<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page isELIgnored ="false" %>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lister Topos</title>
</head>
<body>

    <c:import url="/Header.jsp" />
    <c:import url="/Menu.jsp" />
   <section>
    
    <h2 class = title_section>Annuaire des topos</h2>
    
    <fieldset class="fieldset_critere">
    
    <legend class = title_critere>Recherche par critères</legend> 

    
    <form method="post" action="<c:url value="/ListerTopos"/>">
   <div class=container_form_critere>
  
   <select name="dateParution" id="dateParution">
   <option value= >Année de parution minimum</option>
   <c:forEach var="i" begin="0" end="30" step="1">
   <option value="<c:out value="${2019 - i}"/>" ><c:out value="${2019 - i}"/></option> 
   </c:forEach>
   </select>
			
   
    <select name="disponibilite" id="disponibilite">
            <option value="<c:out value="${''}"/>">Disponibilité</option>
			<option value="disponible">Disponible</option>
			<option value="indisponible" >Indisponible</option>

    </select>
    
    
             
    <select name="critereTrieTopo" id="critereTrieTopo">
			<option value="<c:out value="${''}"/>">Trier par</option>
			<option value="dateParution" >Date de parution</option>
			
	
	
    </select>
    
    <input type="submit" value="Filtrer" class="green-button-special" />
    </div>
     </form>
     
      </fieldset> 
      
    <table>
           <caption class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</caption> 
           <c:if test="${!empty requestScope.mapTopos}">     
           <tr>
                    <th>Nom</th>
                    <th>Date de parution</th>
                    <th>Disponibilité</th>
                 
                                     
                </tr>
                <c:forEach items="${ requestScope.mapTopos }" var="mapTopo" varStatus="boucle1">
                
                <c:forEach items="${ requestScope.datesParution }" var="dateParution" varStatus="boucle2">
                <tr>
                <c:if test="${boucle1.count == boucle2.count}">
                
                   
                    <td class = "for_display"><a href="<c:url value="/AfficherTopo"><c:param name="idTopo" value="${ mapTopo.key }" /></c:url>"><c:out value="${ mapTopo.value.nom }"></c:out></a></td>
                    <td><c:out value="${ dateParution}"></c:out></td>
                    <td><c:out value="${ mapTopo.value.disponibilite }"></c:out></td>
                  </c:if>
                  </tr>
                </c:forEach>
                </c:forEach>
                       
               </c:if> 
            </table>
    

 </section>
</body>
</html>