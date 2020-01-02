<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page isELIgnored ="false" %>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value="/inc/Style.css"/>"/>
<title>Header</title>
</head>
<body>
    
   <div id="header">  
    <c:if test="${empty sessionScope.adherent}">
    
    <p class = "element"><a href="<c:url value="/Connexion"/>">Connexion</a></p>
    <p class = "element"><a href="<c:url value="/Inscription"/>">S'inscrire</a></p>
    
    </c:if>
    
    <c:if test="${!empty sessionScope.adherent}">
    <p class = "element">Bienvenu ${sessionScope.adherent.prenom}!</p>
    <p class = "element"><a href="<c:url value="/Deconnexion"/>">Deconnexion</a></p>
   
    
    </c:if>
    
    </div>
    
    <h1 id=titre_principal>Les amis de l'escalade</h1>
     


</body>
</html>