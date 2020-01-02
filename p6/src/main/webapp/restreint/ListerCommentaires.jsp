<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page isELIgnored ="false" %>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des commentaires</title>
</head>
<body>
    <c:import url="/Header.jsp" />
    <c:import url="/Menu.jsp" />
       <section>
 
     <h3 class = title_section>Commentaires du site: ${sessionScope.siteEscalade.nom}</h3>
     
      <c:if test="${empty requestScope.mapCommentaires}">
      
      <h3 class ="title_section">Aucun commentaire pour ce site</h3>
      </c:if> 
     
     <c:if test="${!empty requestScope.mapCommentaires}">
    
    <c:forEach items="${ requestScope.mapCommentaires }" var="mapCom" varStatus="boucle1">
    
    
                
                 
                    
                    
                   <fieldset class="fieldset_commentaire">
                    
                    <legend class= title_comment><c:out value=" Ajoutée par:  ${ mapCom.value.adherent.prenom} ${ mapCom.value.adherent.nom} le: ${ mapCom.value.dateCommentaire}"></c:out></legend>
                    
                    
                     <c:out value="${ mapCom.value.contenuCommentaire}"></c:out>
                     
                      
                     <c:if test="${sessionScope.adherent.membre}">
                   
                    <p><a class = "blue-link" href="<c:url value="/ModifierCommentaire"><c:param name="idCommentaire" value="${ mapCom.key }" /></c:url>">Modifier</a></p>
                    
                    <p><a class = "red-link" href="<c:url value="/SupprimerCommentaire"><c:param name="idCommentaire" value="${ mapCom.key }" /></c:url>">Supprimer</a></p>

                    </c:if>
                   
                    </fieldset>
                   
                    
              
               
                </c:forEach>
</c:if>
</section> 

</body>
</html>