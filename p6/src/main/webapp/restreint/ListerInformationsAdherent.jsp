<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ page isELIgnored ="false" %>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vos informations</title>
</head>
<body>
    <c:import url="/Header.jsp" />
    <c:import url="/Menu.jsp" />
    <c:import url="/HeaderAdherent.jsp" />
   <section>
<div class= form_display>      
      <fieldset class="fieldset">
                    
      <legend class= title_article>Vos informations personnelles</legend>
        <div class=container_form> 
        
        <label class=label for="nomAdherent">Nom</label>
        
         <div class=text>
                    
      <c:out value="${ sessionScope.adherent.nom}"></c:out>
       </div>
       
       <label class=label for="prenomAdherent">Prenom</label>
       
       <div class=text>
       
      <c:out value="${ sessionScope.adherent.prenom}"></c:out>
      
      </div>
      <label class=label for="telephoneAdherent">Téléphone</label>
       <div class=text>
      
     <c:out value="${ sessionScope.adherent.telephone}"></c:out>
       </div>
       
       <label class=label for="mailAdherent">Mail</label>
        <div class=text>
       <c:out value="${ sessionScope.adherent.mail}"></c:out>
       </div>
       
       <label class=label for="mailAdherent">Adresse</label>
       <div class=text>
       <c:out value="${ sessionScope.adherent.adresse.numeroVoie}"></c:out>
       
       <c:out value="${ sessionScope.adherent.adresse.libelleVoie}"></c:out>
       
       <c:out value="${ sessionScope.adherent.adresse.nomVoie}"></c:out><span>,</span>
       
       <c:out value="${ sessionScope.adherent.adresse.codePostal}"></c:out>
       
       <c:out value="${ sessionScope.adherent.adresse.ville}"></c:out>
         </div>  
         
     </div> 
    <p><a class = "blue-link" href="<c:url value="/ModifierInformationsAdherent"/>">Modifier</a></p>
              
      </fieldset>
   </div>
</section>   
</body>
</html>