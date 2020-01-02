<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page isELIgnored ="false" %>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mes topos</title>
</head>
<body>

<c:import url="/Header.jsp" />
<c:import url="/Menu.jsp" />
<c:import url="/HeaderAdherent.jsp" />
<section>



<table>
            
            <caption class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</caption> 
            
            <c:if test="${!empty requestScope.mapTopos }">
                <tr>
                    <th>Nom</th>
                    <th>Date de parution</th>
                    <th>Disponibilité</th>
                                     
                </tr>
                
                <c:forEach items="${ requestScope.mapTopos }" var="mapTopos" varStatus="boucle1">
                
                <c:forEach items="${ requestScope.datesParution }" var="dateParution" varStatus="boucle2">
                <tr>
                <c:if test="${boucle1.count == boucle2.count}">
                
                    <%-- Affichage des propriétés du bean SiteEscalade--%>
                    <td class = "for_display"><a  href="<c:url value="/ModifierTopo"><c:param name="idTopo" value="${ mapTopos.key }" /></c:url>"><c:out value="${ mapTopos.value.nom }"></c:out></a></td>
                    <td><c:out value="${ dateParution}"></c:out></td>                   
                    <td><c:out value="${ mapTopos.value.disponibilite }"></c:out></td>
                  </c:if>
                   </tr>
                </c:forEach>
                </c:forEach>
                
                
               </c:if>               
            </table>
   
   
 

    <div class=container_vertical> 
    <a class = "blue-link" href="<c:url value="/AjouterTopo"/>">Ajouter un topo</a>
    
    <a class = "blue-link" href="<c:url value="/ListerReservationsTopos"/>">Voir mes demandes de reservation<span> 
            
            <c:forEach items="${ requestScope.reservations}" var="reservation" varStatus="status">
            
             <c:if test="${status.last}">
     <c:out value="(${status.count})"/>
  </c:if>
            
            </c:forEach>
           <c:if test="${empty requestScope.reservations}">
         <c:out value="(${0})"/>
            </c:if>
            </span>
            
            </a>
    
    <a class = "blue-link" href="<c:url value="/ListerReservationsAcceptees"/>">Voir mes reservations acceptées</a>
    
</div>


 </section>
</body>
</html>