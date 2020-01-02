<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page isELIgnored ="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inscription</title>
</head>
<body>
    <c:import url="/Header.jsp" />
    <c:import url="/Menu.jsp" />
    
      <section>
    <div class= form_display>
    
    <form method="post" action="<c:url value="/Inscription"/>">
            <fieldset class="fieldset">
            
                <legend class= title_article>Inscription</legend>
               <div class=container_form>

                <label class=label for="nom">nom <span class="requis">*</span></label>
                <input class =input_field type="text" id="nom" name="nom" value="<c:out value="${adherent.nom}"/>" size="20" maxlength="60" />
                <p class="erreur">${erreurs['nom']}</p>
                
                <label class=label for="prenom">prenom <span class="requis">*</span></label>
                <input class =input_field type="text" id="prenom" name="prenom" value="<c:out value="${adherent.prenom}"/>" size="20" maxlength="60" />
                <p class="erreur">${erreurs['prenom']}</p>
                
                <label class=label for="telephone">Téléphone <span class="requis">*</span></label>
                <input class =input_field type="text" id="telephone" name="telephone" value="<c:out value="${adherent.telephone}"/>" size="20" maxlength="60" />
                <p class="erreur">${erreurs['telephone']}</p>
                
                <label class=label for="mail">Adresse email <span class="requis">*</span></label>
                <input class =input_field type="email" id="mail" name="mail" value="<c:out value="${adherent.mail}"/>" size="20" maxlength="60" />
                <p class="erreur">${erreurs['mail']}</p>
                
                <label class=label for="numeroVoie">Numéro voie <span class="requis">*</span></label>
                <input class =input_field type="text" id="numeroVoie" name="numeroVoie" value="<c:out value="${adherent.adresse.numeroVoie}"/>" size="20" maxlength="60" />
                <p class="erreur">${erreurs['numeroVoie']}</p>
                
                <label class=label for="libelleVoie">Libellé voie <span class="requis">*</span></label>
                <input class =input_field type="text" id="libelleVoie" name="libelleVoie" value="<c:out value="${adherent.adresse.libelleVoie}"/>" size="20" maxlength="60" />
                <p class="erreur">${erreurs['libelleVoie']}</p>
                
                <label class=label for="nomVoie">Nom voie <span class="requis">*</span></label>
                <input class =input_field  type="text" id="nomVoie" name="nomVoie" value="<c:out value="${adherent.adresse.nomVoie}"/>" size="20" maxlength="60" />
                <p class="erreur">${erreurs['nomVoie']}</p>
                
                <label class=label for="codePostal">Code postal <span class="requis">*</span></label>
                <input class =input_field type="text" id="codePostal" name="codePostal" value="<c:out value="${adherent.adresse.codePostal}"/>" size="20" maxlength="60" />
                <p class="erreur">${erreurs['codePostal']}</p>
                
                <label class=label for="ville">Ville <span class="requis">*</span></label>
                <input class =input_field type="text" id="ville" name="ville" value="<c:out value="${adherent.adresse.ville}"/>" size="20" maxlength="60" />
                <p class="erreur">${erreurs['ville']}</p>
                

                <label class=label for="motDePasse">Mot de passe <span class="requis">*</span></label>
                <input class =input_field type="password" id="motDePasse" name="motDePasse" value="" size="20" maxlength="20" />
                <p class="erreur">${erreurs['motDePasse']}</p>
                
                <label class=label for="ConfirmationMotDePasse">Confirmation Mot de passe <span class="requis">*</span></label>
                <input class =input_field type="password" id="ConfirmationMotDePasse" name="ConfirmationMotDePasse" value="" size="20" maxlength="20" />
                <p class="erreur">${erreurs['motDePasse']}</p>

</div>
                <input type="submit" value="Inscription" class="green-button" />
                
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
                
        
            </fieldset>
        </form>

</div>
            </section>

</body>
</html>