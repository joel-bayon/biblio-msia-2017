<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<title>Authentification</title>
</head>
<body>
	<form action="execute.spring" style="text-align: center; margin-top:15%; font-family: Calibri;" method="POST">
		Login : <input type="text" name="login" value="${login}"/><br/>
		Password : <input type="password" name="password" value="${password}"/><br/><br/>
		<input type="submit" value="Valider" /><br/>
	</form>
	<div style="text-align: center; margin-top:5%; font-family: Calibri; color: red;">
		${AuthentificationError}
	</div>
</body>
</html>