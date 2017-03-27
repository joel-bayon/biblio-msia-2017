<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
	<jsp:include page="template.jsp"></jsp:include>
	
	<div class="row" style="margin-top: 6%;">
		<div class="col-md-10 col-md-offset-1">
			<div class="jumbotron">
			  <h1>Bienvenue sur le site de gestion de la bibliothèque !</h1>
			  <p>Vous pouvez, à partir de ce site, gérer tous les emprunts de vos adhérents !</p>
			  <p><a class="btn btn-primary btn-lg" href="/biblio/gestionEmprunt/home.spring" role="button">Gérer vos emprunts</a></p>
			</div>
		</div>
	</div>
	
	<div class="row" style="margin-top: 2%;">
		<h3>
		Dernière connexion : <fmt:formatDate type="both" value="${dateDerniereConnexion}" />.
		</h3>
	</div>
</body>