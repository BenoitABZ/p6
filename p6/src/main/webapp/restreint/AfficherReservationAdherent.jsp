<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page isELIgnored ="false" %>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Votre reservation</title>
</head>
<body>
    <c:import url="/Header.jsp" />
    <c:import url="/Menu.jsp" />
    <c:import url="/HeaderAdherent.jsp" />
    
   <section>
   
  <p class="caption_special"><c:out value="${form.resultat}"></c:out></p>
  
<div class= form_display>    
 
   <c:if test="${!empty requestScope.reservation.statutReservation }">  
     <fieldset class="fieldset">
     
   <legend class= title_article>Ticket de reservation</legend>
   <div class=container_form> 
   
 <label class=label for="numeroReservation">Numero de reservation</label>
   
   <div class=text>
   
   <c:out value="${requestScope.reservation.numeroReservation}"></c:out>
   
   </div>
    <label class=label for="dateReservation">Date de reservation</label> 
    
    <div class=text>
    <c:out value="${requestScope.dateReservation}"></c:out>
    </div>
    
    <label class=label for="statutReservation">Statut de la reservation</label> 
    <div class=text>
    
    <c:out value="${requestScope.reservation.statutReservation}"></c:out> 
    </div>
    
    <label class=label for="nomTopo">Nom du topo</label> 
    <div class=text>
    <c:out value="${requestScope.reservation.topo.nom}"></c:out>    
   
     </div>
      </div>
 
    
   
   <c:if test="${requestScope.reservation.statutReservation == 'En attente d\\\'acceptation' ||requestScope.reservation.statutReservation eq'Refusée'}" >
   
    <p><a class=red-link href="<c:url value="/SupprimerReservation"/>">Annuler</a></p>
   </c:if>
   
   </fieldset>
   </c:if>
    </div>
</section>
</body>
</html>