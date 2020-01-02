<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page isELIgnored ="false" %>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connexion</title>
</head>
<body>

    <c:import url="/Header.jsp" />
    <c:import url="/Menu.jsp" />
    
    <section>
    <div class= form_display>
    
    <form method="post" action="<c:url value="/Connexion"/>">
    
    <fieldset class="fieldset">
           
            
                <legend class= title_article>Connexion</legend>
                <div class=container_form>

                <label class=label for="mail">Adresse email <span class="requis">*</span></label>
                <input class =input_field type="email" id="mail" name="mail" value="<c:out value="${adherent.mail}"/>" size="20" maxlength="60" />
                <p class="erreur">${form.erreurs['mail']}</p>
                

                <label class=label for="motDePasse">Mot de passe <span class="requis">*</span></label>
                <input class =input_field type="password" id="motDePasse" name="motDePasse" value="" size="20" maxlength="20" />
                <p class="erreur">${form.erreurs['motDePasse']}</p>
       

</div>
                <input type="submit" value="Connexion" class="green-button" />
                
                <p class="erreur">${form.erreurs['erreur']}</p>
               
                
        
            </fieldset>
        </form>
</div>
            </section>
</body>
</html>