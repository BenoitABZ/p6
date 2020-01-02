<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%@ page isELIgnored ="false" %>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vos réservations de topos</title>
</head>
<body>


    <c:import url="/Header.jsp" />
    <c:import url="/Menu.jsp" />
    <c:import url="/HeaderAdherent.jsp" />    
    
     <section>
     <p class="caption_special"><c:out value="${form.resultat}"></c:out></p>
     
     <c:if test="${!empty requestScope.mapReservations}">
<div class= form_display>       
                  <fieldset class="fieldset">
                   <legend class= title_article><c:out value="Vos demandes de réservation"></c:out></legend> 
                   <div class=container_form> 
                        
                   <c:forEach items="${ requestScope.mapReservations }" var="mapReservation" varStatus="boucle">               
               
                   <label class=label for="reservationTopo"><c:out value="${ mapReservation.value.topo.nom}"/> </label><br/>
                   
                   
                   <label class=label for="reservationTopoNum"><c:out value="Numero de réservation"/> </label>
                   
                   <div class=text> 
                    <c:out value="${ mapReservation.value.numeroReservation}"></c:out>
                     </div>
                    
                    <label class=label for="reservationTopoDate"><c:out value="Date de réservation"/> </label>
                    
                    <div class=text> 
                    <c:out value="${ mapReservation.value.dateReservation }"></c:out>
                    </div>
                    
                    <label class=label for="reservationTopoNom"><c:out value="Nom de l'adherent"/> </label>
                    
                    <div class=text> 
                    <c:out value="${ mapReservation.value.adherent.nom}"></c:out>
                    </div>
                    
                     <label class=label for="reservationTopoNom"><c:out value="Prénom de l'adherent"/> </label>
                     
                     <div class=text> 
                    <c:out value="${ mapReservation.value.adherent.prenom}"></c:out>
                    </div>
                 
                    <p><a class = "blue-link" href="<c:url value="/AccepterReservation"><c:param name="idReservation" value="${ mapReservation.key }" /></c:url>"><c:out value="Accepter"></c:out></a></p>
                     </c:forEach>
                    </div>
                    </fieldset>
                
              
   </div>
   </c:if>
</section> 
</body>
</html>