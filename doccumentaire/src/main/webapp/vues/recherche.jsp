<%@ page language="java" contentType="text/html; charset=UTF-8"  isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title>Recherche</title>
		<%@include file="/vues/shared/_header.jsp" %>	
	</head>
	<body>
	<h1>Recherche</h1>
		<form id="searchchBar" action="${pageContext.request.contextPath}/resultats" method="get" >
			<input type="text" name="requette" id="zoneSaisie">
			<input type="submit" name="submit" id="valider">
		</form>
	</body>
</html>
