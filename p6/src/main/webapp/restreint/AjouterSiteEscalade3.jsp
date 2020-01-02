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
    <form method="post" action="<c:url value="/AjouterSiteEscalade3"/>">
    <h3 class = title_section>Partage d'un site d'escalade</h3>
        <fieldset class="fieldset">
                
                <legend class= title_article>Completer les champs suivants</legend>
               <div class=container_form> 
                
                <c:forEach items="${ sessionScope.siteEscalade.secteurs}" var="secteur" varStatus="status1">
                   <label class=label for="secteur">Secteur <c:out value="${status1.count}"/> </label>
                   
                    <c:forEach items="${secteur.voies}" var="voie" varStatus="status2">
                       <label class=label for="voie">Voie <c:out value="${status2.count}"/> </label>
               <select class =input_field name="longueur<c:out value="${status2.count + status1.count * 3}"/>" id="longueur">
               
            <c:if test="${!empty voie.nombreLongueur}">      
			<option value="<c:out value="${voie.nombreLongueur}"/>"><c:out value="${voie.nombreLongueur}"/></option>
			</c:if>  
         
               
			<option value="">Nombre de longueur</option>
			<option value="1" >1</option>
			<option value="2" >2</option>
			<option value="3" >3</option>
			<option value="4" >4</option>
			<option value="5" >5</option>
			<option value="6" >6</option>
			<option value="7" >7</option>
			<option value="8" >8</option>
              </select>
              <p class="erreur">${form.erreurs['nombreLongueur']}</p>
    
             
                     <select class =input_field name="cotation<c:out value="${status2.count + status1.count * 3}"/>" id="cotation">
                     
                          <c:if test="${!empty voie.cotationVoie}">      
			<option value="<c:out value="${voie.cotationVoie}"/>"><c:out value="${voie.cotationVoie}"/></option>
			</c:if> 
                     
			<option value="">Cotation</option>
			<option value="1" >1</option>
			<option value="2" >2</option>
			<option value="3" >3</option>
			<option value="4" >4</option>
			<option value="5" >5</option>
			<option value="6" >6</option>
			<option value="7" >7</option>
			<option value="8" >8</option>
              </select>
              <p class="erreur">${form.erreurs['cotation']}</p>
              
                  
            <select class =input_field name="equipee<c:out value="${status2.count + status1.count * 3}"/>" id="equipee">
            
			<option value="Non">Equipement</option>
			<option value="Oui" >Oui</option>
			<option value="Non" >Non</option>	
            </select>
              
               <label class=label for="hauteur">Hauteur approximative <span class="requis">*</span></label>
                <input class =input_field type="text" id="hauteur" name="hauteur<c:out value="${status2.count + status1.count * 3}"/>" value="<c:out value="${voie.hauteur}"/>" size="20" maxlength="60" />
                 <p class="erreur">${form.erreurs['hauteur']}</p>
                    </c:forEach>
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