<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
	<spring:url value="/resources/bootstrap/css/bootstrap.min.css" var="bootstrapCSS" />
	<spring:url value="/resources/js/jquery.min.js" var="jqueryJS" />
	<spring:url value="/resources/bootstrap/js/bootstrap.min.js" var="bootstrapJS" />
	<link href="${bootstrapCSS}" rel="stylesheet" />
	<script src="${jqueryJS}"></script>
	<script src="${bootstrapJS}"></script>
	<meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Biblio</title>
</head>
<body style="text-align: center;">
<jsp:include page="menu.jsp"></jsp:include>