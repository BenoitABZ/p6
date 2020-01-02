<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page isELIgnored ="false" %>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifier un commentaire</title>
</head>
<body>
    <c:import url="/Header.jsp" />
    <c:import url="/Menu.jsp" />
    
    <section>
    <div class= form_display> 
    <h3 class = title_section>Commentaires du site: ${sessionScope.siteEscalade.nom}</h3>
    
    <form method="post" action="<c:url value="/ModifierCommentaire"/>">
    
    <fieldset class="fieldset">
                    
                    <legend class= title_comment><c:out value=" Ajoutée par: ${ sessionScope.commentaire.adherent.nom} ${ sessionScope.commentaire.adherent.prenom} le: ${ sessionScope.commentaire.dateCommentaire}"></c:out></legend>
                   <div class=container_form>    
                             
                
    <textarea id="contenuCommentaire" name="contenuCommentaire" class="commentaire_field"><c:out value="${sessionScope.commentaire.contenuCommentaire}"/></textarea>
                
                
               
</div>
                    <span class="erreur">${form.erreurs['contenuCommentaire']}</span>
                     
             
                    
                      <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
                      
                    <input class = "green-button" type="submit" value="modifier" />
                    
                    </fieldset>
                    
                      </form>
      </div>                
  </section>                      
</body>
</html>