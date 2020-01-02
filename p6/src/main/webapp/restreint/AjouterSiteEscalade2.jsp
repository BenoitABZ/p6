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
    <form method="post" action="<c:url value="/AjouterSiteEscalade2"/>">
     <h3 class = title_section>Partage d'un site d'escalade</h3>
      <fieldset class="fieldset">
                
                <legend class= title_article>Completer les champs suivants</legend>
               <div class=container_form> 
               <c:forEach items="${ sessionScope.siteEscalade.secteurs}" var="secteur" varStatus="status1">
               <label class=label for="secteur">Secteur <c:out value="${status1.count}"/> </label>
               <select class =input_field name="voie<c:out value="${status1.count}"/>" id="voie">
               
            <c:if test="${!empty secteur.voies}">
            <c:forEach items="${ secteur.voies}" var="voie" varStatus="status2">
            <c:if test="${status2.last}">
			<option value="<c:out value="${status2.count}"/>"><c:out value="${status2.count}"/></option>
			</c:if>  
            </c:forEach> 
            </c:if>  
			<option value="">Nombre de voie</option>
			<option value="1" >1</option>
			<option value="2" >2</option>
			<option value="3" >3</option>
			<option value="4" >4</option>
			<option value="5" >5</option>
			<option value="6" >6</option>
			<option value="7" >7</option>
			<option value="8" >8</option>
              </select>
               <p class="erreur">${form.erreurs['nombreVoie']}</p>
              </c:forEach>
             
</div>

                <input type="submit" value="Suivant" class=green-button />
                
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
                
        
            </fieldset>
        </form>
         </div>
         </section>
</body>
</html>