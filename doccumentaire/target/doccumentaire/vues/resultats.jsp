<%@ page import="com.recherche.doccumentaire.metier.Doccuments"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Resultats</title>
		<%@include file="/vues/shared/_header.jsp" %>	
	</head>
	<body>
		<h2>Doodle resultats de la récheche</h2>
		<i>Nombre de documents trouvés: <c:out value="${nbDocuments}"></c:out></i>
		<div id="blocresultat">
			<c:if test="${tailleListe!=0}">
				<c:forEach items="${listeDocuments}" var="listeDocuments">
					<p id="paragraphe"> 
						<span id="titreDoc">
							Titre du document: <c:out value="${listeDocuments.titre}"/> 
							Score du doccumment: <c:out value="${listeDocuments.score}" />.<br>
						</span>
							<span id="textDoc">
								<c:forEach items="${listeDocuments.listeString}" var="terme">
									<c:set var="theString" value="${terme}"/>
									<c:choose>
										<c:when test="${fn:contains(theString, requette)}">
											<span id="stringBold"><c:out value="${theString}"></c:out> </span> 
										</c:when>	
										<c:otherwise>
											<span><c:out value="${theString}"></c:out> </span> 
										</c:otherwise>	
									</c:choose>																								
								</c:forEach>
							</span>
					</p>
				</c:forEach>
			</c:if>			
		</div>
	</body>
</html>