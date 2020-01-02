<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored ="false" %>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vos réservations acceptées</title>
</head>
<body>

<body>
    <c:import url="/Header.jsp" />
    <c:import url="/Menu.jsp" />
    <c:import url="/HeaderAdherent.jsp" />
      <section>
     <p class="caption_special"><c:out value="${form.resultat}"></c:out></p>
                  
                  
            <c:if test="${!empty requestScope.listeReservations}">    
                    
          <div class= form_display>       
                  <fieldset class="fieldset">  
                  
                  <legend class= title_article><c:out value="Vos réservations acceptées"></c:out></legend>   
                  <div class=container_form>                   
                  
                   <c:forEach items="${ requestScope.listeReservations }" var="reservation" varStatus="boucle">
              
                   
                   <label class=label for="reservationTopoAccept"><c:out value="${reservation.topo.nom}"/> </label>
                   <div class=text>
                   <h3 class = "title_article">Envoyé votre topo à <c:out value="${reservation.adherent.nom} ${reservation.adherent.prenom }"></c:out> </h3>
                   
                   <h3 class = "title_article" >Ses coordonnées: </h3>  
                    
                         
                   <p><c:out value="Mail: ${reservation.adherent.mail}"></c:out></p>
                     
                   <p><c:out value="Téléphone: ${reservation.adherent.telephone}"></c:out></p>
                    <p>
                   <c:out value="Adresse: ${reservation.adherent.adresse.numeroVoie}"></c:out>
                    
                   <c:out value="${reservation.adherent.adresse.libelleVoie}"></c:out>
                     
                   <c:out value="${reservation.adherent.adresse.nomVoie},"></c:out>
                    
                   <c:out value="${reservation.adherent.adresse.codePostal}"></c:out>
                    
                   <c:out value="${reservation.adherent.adresse.ville}"></c:out>
                   </p>
                   </div> 
                   
                      </c:forEach>
                      </div>
                    </fieldset>
               
              

 </div>
  </c:if>
</section> 

</body>
</html>