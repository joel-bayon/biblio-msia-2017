<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

	<jsp:include page="../template.jsp"></jsp:include>
	
	<c:if test="${not empty unLivre}">
		<div class="row">
	    	<h3>Modification du livre n°${unLivre.id}</h3>
    	</div>
	</c:if>
	
	<c:if test="${empty unLivre}">
		<div class="row">
	    	<h3>Création d'un livre</h3>
    	</div>
	</c:if>
	
	<br/><div class="row">
		<div class="col-md-8 col-md-offset-2"></div>
			<c:if test="${not empty unLivre}">
				<form action="edition.spring?id=${unLivre.id}" method="POST" class="form col-md-8 col-md-offset-2">
			</c:if>
			<c:if test="${empty unLivre}">
			    <form action="edition.spring" method="POST" class="form col-md-8 col-md-offset-2">
			</c:if>
				<div class="form-group">
					<label for="titre">Titre</label><input type="text" name="titre" value="${unLivre.titre}" class="form-control"/><br/>
				</div>
				<div class="form-group">
					<label for="parution">Parution</label><input type="text" name="parution" value="${unLivre.parution}" class="form-control"/><br/>
				</div>
				<div class="form-group">
					<label for="auteur">Auteur</label><input type="text" name="auteur" value="${unLivre.auteur}" class="form-control"/><br/><br/>
				</div>
				
				<c:if test="${not empty unLivre}">
				    <button type="submit" name="a" value="Modifier" class="btn btn-primary">Modifier</button>
				</c:if>
				<c:if test="${empty unLivre}">
				    <button type="submit" name="a" value="Ajouter" class="btn btn-primary">Ajouter</button>
				</c:if>
			</form>
		</div>
	</div>
		
	<div class="row">
		<a href="home.spring"><button type="button" class="btn btn-default">Retour</button></a>
	</div>
	
</body>
