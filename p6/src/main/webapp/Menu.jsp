<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page isELIgnored ="false" %>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value="/inc/Style.css"/>"/>
<title>Menu</title>
</head>
<body>

<nav id="menu">
  
    <p class = "element"><a href="<c:url value="/Accueil"/>">Accueil</a></p>
    <p class = "element"><a href="<c:url value="/ListerSitesEscalade"/>">Sites d'escalade</a></p>
    <p class = "element"><a href="<c:url value="/ListerTopos"/>">Topos</a></p>
    <p class = "element"><a href="<c:url value="/ListerInformationsAdherent"/>">Mon espace</a></p>
   
    <c:if test="${sessionScope.adherent.membre}">
    
    <p class = "element"><a href="<c:url value="/ListerAdherents"/>">Espace Admin</a></p>
    
    </c:if>
    
</nav>

</body>
</html>