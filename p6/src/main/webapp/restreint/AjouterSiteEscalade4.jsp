<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page isELIgnored ="false" %>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajouter un site d'escalade</title>
</head>
<body>

    <c:import url="/Header.jsp" />
    <c:import url="/Menu.jsp" />
   <section>
<div class= form_display>  
     <form method="post" action="<c:url value="/AjouterSiteEscalade4"/>">
            
          <h3 class = title_section>Partage d'un site d'escalade</h3>
        <fieldset class="fieldset">
                
                <legend class= title_article>Description</legend>
               <div class=container_form> 
                
    <textarea id="description" name ="description" class="commentaire_field"><c:out value="${sessionScope.siteEscalade.description}"/></textarea>
     <p class="erreur">${form.erreurs['description']}</p>           
                
               
</div>
                <input type="submit" value="Suivant" class=green-button />
                
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
                
        
            </fieldset>
        </form>

  
     </div>
</section> 
 

</body>
</html>