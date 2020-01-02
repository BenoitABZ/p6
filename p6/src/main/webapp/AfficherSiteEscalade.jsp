<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page isELIgnored ="false" %>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Site d'escalade</title>
</head>
<body>

    <c:import url="/Header.jsp" />
    <c:import url="/Menu.jsp" />

 <section>
<div class= form_display> 

 <h3 class = label_site ><c:out value="${sessionScope.siteEscalade.label}"></c:out></h3>

    <h3 class = title_section><c:out value="${sessionScope.siteEscalade.nom}"></c:out></h3>
      
    
    <h3 class = title_article><c:out value=" Ajouté par :${sessionScope.siteEscalade.adherent.nom} ${sessionScope.siteEscalade.adherent.prenom}"></c:out></h3>
    
   
    
    <fieldset class="fieldset">
     
    
   <legend class= title_article>Caractéristiques</legend>
   
   <div class=container_form> 
   
    <label class=label for="Département">Localité</label>
   <div class=text>
   
   
   <p><c:out value=" Département: ${sessionScope.siteEscalade.departement}"></c:out></p> 
   
   <p><c:out value=" Ville la plus proche: ${sessionScope.siteEscalade.commune}"></c:out></p>
    
    </div>

   
                   <c:forEach items="${ sessionScope.siteEscalade.secteurs}" var="secteur" varStatus="status1">
                   
                   <label class=label for="secteur">Secteur <c:out value="${status1.count}"/> </label>
                       <c:forEach items="${secteur.voies}" var="voie" varStatus="status2">
                          <label class = label for="voie">Voie <c:out value="${status2.count}"/> </label>
                          
                  <div class=text>      
               <p><c:out value=" Nombre de longueur: ${voie.nombreLongueur}"></c:out></p>
               
               <p><c:out value=" Cotation de la voie: ${voie.cotationVoie}"></c:out></p>
               
               <p><c:out value=" Hauteur approximative: ${voie.hauteur} metres"></c:out></p>
               
            <c:if test="${voie.equipee}">      
			<p>La voie est équipée</p> 
			</c:if>  
			
			<c:if test="${!voie.equipee}">      
			<p>La voie n'est pas équipée</p> 
			</c:if>  
         
 </div>
                       </c:forEach>
                   </c:forEach>
                    <label class=label for="description">Description </label>     
   <div class=text_comment>
                  
                       
                       <c:out value="${sessionScope.siteEscalade.description}"/>
                     
      
    </div>
   
   
        
              
   
</div>
           <p><a class = "blue-link"  href="<c:url value="/AjouterCommentaire"/>">Ajouter un commentaire</a></p>
            
            <p><a class = "blue-link" href="<c:url value="/ListerCommentaires"/>">Voir les commentaires<span> 
            
            <c:forEach items="${ requestScope.commentaires}" var="commentaire" varStatus="status">
            
             <c:if test="${status.last}">
     <c:out value="(${status.count})"/>
  </c:if>
            
            </c:forEach>
           
         <c:if test="${empty requestScope.commentaires}">
         <c:out value="(${0})"/>
            </c:if>
            
            </span>
            
            </a></p>
            
             <c:if test="${sessionScope.adherent.membre}">
                    
             <p><a class = "blue-link" href="<c:url value="/LabelSiteEscalade"><c:param name="idSiteEscalade" value="${ sessionScope.siteEscalade.id}" /></c:url>">Labelliser</a></p>
                  

             </c:if>
    
            </fieldset>
            
    
      </div>
</section> 
</body>
</html>