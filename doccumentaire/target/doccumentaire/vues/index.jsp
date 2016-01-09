<%@ page language="java" contentType="text/html; charset=UTF-8"  isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title>Home</title>
		<%@include file="/vues/shared/_header.jsp" %>	
	</head>
	<body >	
		<h2 id="moteur">Moteur de recherche Doodle</h2>
		<form id="Menu" method="post">
			<fieldset id="field">
			 <legend>MENU Indexation/Recherche</legend>
				<span id="indexer">INDEXER <button id="btn" name="OK">OK</button></span><br>
				<a id = "indexer" href="${pageContext.request.contextPath}/recherche">RECHERCHER</a>
			</fieldset>			
		</form><br>
		<span id="resultatSucces" >${succes}</span>
		<span id="resultatErreur" >${erreur}</span>
	</body>
</html>
