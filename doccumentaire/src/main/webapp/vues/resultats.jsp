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
		<%@ include file="/vues/shared/_header.jsp" %>	
	</head>
	<body>
		<h2>Resultats de la récheche</h2>
		<i>Temps de recherche: <c:out value="${temps}"> millisecondes</c:out></i>
		<div id="blocresultat">
			<c:if test="${tailleListe!=0}">
				<c:forEach items="${listeDocuments}" var="listeDocuments">
					<p id="paragraphe"> 
						<span id="titreDoc">
							Titre du document: <c:out value="${listeDocuments.titre}"/> 
							Score du doccumment: <c:out value="${listeDocuments.score}" />.<br>
						</span> 
						<span id="textDoc"> 						
							<c:if test="${compose==0}">
								<c:forEach items="${listeDocuments.listeString}" var="theString">
									<c:choose>
										<c:when test="${fn:contains(theString, requette)}">
											<span id="stringBold"><c:out value="${theString}"></c:out></span>
										</c:when>
										<c:otherwise>
											<span><c:out value="${theString}"></c:out> </span>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:if>
							<c:if test="${compose==1}">
								<c:forEach items="${listeDocuments.texte}" var="theString"> 
									<c:if test="${fn:contains(theString, requette)}">										
										<c:set var="chaine" value="${fn:substring(theString, 0, fn:indexOf(theString,requette))}" />
										<c:set var="caine1" 
											value="${fn:substring(theString, fn:indexOf(theString,requette), fn:indexOf(theString,requette)+fn:length(requette)) }">
										</c:set>	
										<c:set var= "chaine2"
											value="${fn:substring(theString,fn:indexOf(theString,requette)+fn:length(requette)+1, fn:length(theString)) }">
										</c:set>				
										<span><c:out value="${chaine}"></c:out></span>
										<span id="stringBold"><c:out value="${caine1}"></c:out></span>
										<span><c:out value="${chaine2}"></c:out></span>										
									</c:if>
									<c:if test="${!fn:contains(theString, requette) }">
										<span><c:out value="${theString}"></c:out></span>
									</c:if>
									
								</c:forEach>
							</c:if>
							<c:if test="${compose==2}">
								<c:forEach items="${listeDocuments.listeString}" var="theString">
									<c:choose>
										<c:when test="${fn:startsWith(theString, Prefix)}">
											<span id="stringBold"><c:out value="${theString}"></c:out></span>
										</c:when>
										<c:otherwise>
											<span><c:out value="${theString}"></c:out> </span>
										</c:otherwise>
									</c:choose>
								</c:forEach>							
							</c:if>
						</span>
					</p>
				</c:forEach>
			</c:if>			
		</div>
	</body>
</html>