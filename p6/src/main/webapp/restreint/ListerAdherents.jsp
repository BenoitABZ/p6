<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page isELIgnored ="false" %>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lister adherents</title>
</head>
<body>

<c:import url="/Header.jsp" />
<c:import url="/Menu.jsp" />
<c:import url="/HeaderAdherent.jsp" />

<section>
<div class= form_display>      
     
        <div class=container_form> 
<table>
           <caption class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</caption>  
          <c:if test="${!empty mapAdherents}">
          
                <tr>
                    <th>Nom</th>
                    <th>Prenom</th>
                    <th>Mail</th>
                    <th>Telephone</th>
                    <th>Action</th>
                   
                                     
                </tr>
                <%-- Parcours de la liste des contacts, et utilisation de l'objet varStatus. --%>
                <c:forEach items="${ requestScope.mapAdherents }" var="mapAdherent" varStatus="boucle">
                
                    <%-- Affichage des propriétés du bean SiteEscalade--%>
                  <tr>  
                    <td><c:out value="${ mapAdherent.value.nom }"></c:out></td>                   
                    <td><c:out value="${ mapAdherent.value.prenom }"></c:out></td>
                    <td><c:out value="${ mapAdherent.value.mail}"></c:out></td>
                    <td><c:out value="${ mapAdherent.value.telephone}"></c:out></td>                     
                    <td><a class= blue-link href="<c:url value="/UpgradeAdherent"><c:param name="idAdherent" value="${ mapAdherent.key }" /></c:url>">upgrade</a></td>
               
                </c:forEach>
                
                
              </c:if>  
            </table>
 </div>

    
 
   </div>
</section> 

</body>
</html>