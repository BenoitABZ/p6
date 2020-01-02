<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page isELIgnored ="false" %>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Afficher topo</title>
</head>
<body>

<c:import url="/Header.jsp" />
<c:import url="/Menu.jsp" />
<c:import url="/HeaderAdherent.jsp" />

  <section>
 <div class= form_display> 
 
    <form method="post" action="<c:url value="/ModifierTopo"/>">
                
      <fieldset class="fieldset">
                <legend class= title_article><c:out value="${requestScope.topo.nom}"></c:out></legend>
               
 <div class=container_form> 
 
                <label class=label for="nom">Nom <span class="requis">*</span></label>               
                <input class =input_field type="text" id="nom" name="nom" value="<c:out value="${requestScope.topo.nom}"/>"  />
                <p class="erreur">${form.erreurs['nom']}</p>
                
                <label class=label for="dateParution">Date de parution <span class="requis">*</span></label>
                <input class =input_field  type="date" id="dateParution" name="dateParution" value="<c:out value="${requestScope.topo.dateParution}"/>"  />
                
           
                <label class=label for="disponibilite">Disponibilité <span class="requis">*</span></label>
                <select class =input_field name="disponibilite" id="disponibilite">               
			    <option value="disponible">Disponible</option>
			    <option value="indisponible" >Indisponible</option>
                </select>
                
                     
                <label class=label for="description">Description <span class="requis">*</span></label>
                <textarea id="description" name ="description" class="description"><c:out value="${requestScope.topo.description}"/></textarea>
                <p class="erreur">${form.erreurs['description']}</p>
                
                
                
                
           </div>
                
                      <input type="submit" value="modifier" class="green-button" />
                
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
                
        
            </fieldset>
        </form>
 </div>
            </section>  
 
</body>
</html>