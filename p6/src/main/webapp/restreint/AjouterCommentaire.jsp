<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     
<%@ page isELIgnored ="false" %>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajouter un commentaire</title>
</head>
<body>

 <c:import url="/Header.jsp" />
    <c:import url="/Menu.jsp" />
 <section>
<div class= form_display>  
<form method="post" action="<c:url value="/AjouterCommentaire"/>">



  
   
       <h3 class = title_section><c:out value="${sessionScope.siteEscalade.nom}"/></h3>
        <fieldset class="fieldset">
                
                <legend class= title_article>Ajouter un commentaire</legend>  

                
                <div class=container_form>
                
             
<textarea id="contenu" name ="contenu" class="commentaire_field"><c:out value="${requestScope.commentaire.contenuCommentaire}"/></textarea>

</div>
                <input type="submit" value="Ajouter" class="green-button" />
                
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
                
        
            
      
    
   </fieldset>
   
     </form>
  
     </div> 
</section> 
</body>
</html>