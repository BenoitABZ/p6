<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page isELIgnored ="false" %>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajouter un topo</title>
</head>
<body>


   <c:import url="/Header.jsp" />
    <c:import url="/Menu.jsp" />
    <c:import url="/HeaderAdherent.jsp" />
    
        <section>
    <div class= form_display>
    
    <form method="post" action="<c:url value="/AjouterTopo"/>">
    <h3 class = title_section>Ajouter un topo</h3>
    
            <fieldset class="fieldset">
                <legend class= title_article>Renseigner les champs suivants</legend>
                
<div class=container_form>
                <label class=label for="nom">nom <span class="requis">*</span></label>
                <input class =input_field type="text" id="nom" name="nom" value="<c:out value="${topo.nom}"/>" size="20" maxlength="60" />
                <p class="erreur">${form.erreurs['nom']}</p>
                
                <label class=label for="dateParution">Date de parution <span class="requis">*</span></label>
                <input class =input_field  style='font-weight: bold' type="date" id="dateParution" name="dateParution" value="<c:out value="${topo.dateParution}"/>" size="20" maxlength="60" />
                
                
                
                   <label class=label for="disponibilite">Disponibilité <span class="requis">*</span></label>
            <select class =input_field name="disponibilite" id="disponibilite">
			<option value="disponible">Disponible</option>
			<option value="indisponible" >Indisponible</option>
		
    </select>
                
                
                <label class=label for="description">Description <span class="requis">*</span></label>
                <textarea id="description" name ="description" class="description"><c:out value="${topo.description}"/></textarea>
               
                <p class="erreur">${form.erreurs['description']}</p>
                
         
                
           </div>
                
                      <input type="submit" value="Ajouter" class="green-button" />
                
                
             
                
        
            </fieldset>
        </form>
</div>
            </section>
</body>
</html>