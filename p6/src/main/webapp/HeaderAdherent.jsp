<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page isELIgnored ="false" %>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value="/inc/Style.css"/>"/>
<title>Header Adherent</title>
</head>
<body>



<div id="header_adherent">

    <p class = "element_adherent"><a href="<c:url value="/ListerInformationsAdherent"/>">Mes informations</a></p>
    <p class = "element_adherent"><a href="<c:url value="/AfficherReservationAdherent"/>">Ma reservation</a></p>
    <p class = "element_adherent"><a href="<c:url value="/ListerToposAdherent"/>">Mes Topos</a></p>
     
</div>

</body>
</html>