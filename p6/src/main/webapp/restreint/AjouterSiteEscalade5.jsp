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
    <form method="post" action="<c:url value="/AjouterSiteEscalade5"/>">
          
                     <h3 class = title_section>Partage d'un site d'escalade</h3>
        <fieldset class="fieldset">
                
                <legend class= title_article>Récapitulatif</legend>
               <div class=container_form> 
          
                
                <c:forEach items="${ sessionScope.siteEscalade.secteurs}" var="secteur" varStatus="status1">
                   <label class=label for="secteur">Secteur <c:out value="${status1.count}"/> </label>
                    <c:forEach items="${secteur.voies}" var="voie" varStatus="status2">
                       <label class=label for="voie">Voie <c:out value="${status2.count}"/> </label>
                       <div class=text>
                       
                       <p>
                       
                        Nombre de longueur: <c:out value="${voie.nombreLongueur}"/>
                             
                       </p>
          
                       <p>
                       
                        Cotation: <c:out value="${voie.cotationVoie}"/>
                            
                       </p>
              
                      <p >
                       
                       La voie est-t-elle d'un equipée? <c:out value="${voie.equipee ? 'oui' : 'non'}"/>
                             
                       </p>   
              
                       <p>
                       
                       Hauteur approximative: <c:out value="${voie.hauteur} mètres"/> 
                             
                       </p>
                       
            
                      </div> 
                   </c:forEach>
                  
                      
              </c:forEach>
               
                                  
                          
                     <label class=label for="description">Description </label>     
                    <div class=text_comment>
                  
                       
                       <p><c:out value="${sessionScope.siteEscalade.description}"/></p>
                      </div> 
                       
</div>

                <p><a class = "blue-link" href="<c:url value="/AjouterSiteEscalade1"/>">Modifier</a></p>
                
                <input type="submit" value="valider" class= green-button /> 
                
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
                
               
                
        
            </fieldset>
             
        </form>
     </div>
</section> 

</body>
</html>