<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page isELIgnored ="false" %>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Afficher Topo</title>
</head>
<body>

    <c:import url="/Header.jsp" />
    <c:import url="/Menu.jsp" />
 <section>
<div class= form_display> 

    <h3 class = title_section><c:out value="${requestScope.topo.nom}"></c:out></h3>
    
    <h3 class = title_article><c:out value=" Ajouté par: ${requestScope.topo.adherent.prenom} ${requestScope.topo.adherent.nom} "></c:out></h3>
    
   
    
    <fieldset class="fieldset">
    
    <legend class= title_article>Caractéristiques</legend>
    
    <div class=container_form> 
   
    <label class=label for="DateParution">Date de Parution</label>
    
   <div class=text>
    
   
   <p><c:out value=" Ce manuel a été édité le: ${requestScope.dateTopo}"></c:out></p>
    
  </div>
  
 
   
   
       <label class=label for="Disponibilité">Disponibilité</label>
  <div class=text>
   <p><c:out value="Ce manuel est ${requestScope.topo.disponibilite}"></c:out></p> 
    </div>
    
      <label class=label for="Description">Description</label>
       
    <div class= text_comment>
   <c:out value="${requestScope.topo.description}"></c:out>
   
   
   
   
   
    </div>
  
             <c:if test="${sessionScope.adherent.reservation.statutReservation == 'En attente d\\\'acceptation' || sessionScope.adherent.reservation.statutReservation == 'Refusée'}">      
			<p><c:out value="Pour reserver ce topo, vous devez annuler votre dernière réservation."></c:out></p>
			</c:if>
			
			<c:if test="${sessionScope.adherent.reservation.statutReservation == 'Acceptée'}">      
			<p><c:out value="Pour reserver ce topo, vous devez rendre son manuel à ${sessionScope.adherent.reservation.topo.adherent.prenom }."></c:out></p>
			</c:if>
						
        </div>
        <c:if test="${empty sessionScope.adherent.reservation.numeroReservation && requestScope.topo.disponibilite == 'disponible'}"> 
       	<p><a class = "blue-link" href="<c:url value="/DemanderReservation"><c:param name="idTopo" value="${ topo.id }" /></c:url>">Reserver</a></p>
	    <span class="${empty formR.erreurs ? 'succes' : 'erreur'}">${formR.resultat}</span> 
	    </c:if> 
       
   </fieldset>
       
 </div>
</section> 
</body>
</html>