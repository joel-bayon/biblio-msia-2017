<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

	<jsp:include page="../template.jsp"></jsp:include>
	
	<c:if test="${not empty unAdherent}">
		<div class="row">
	    	<h3>Modification de l'adherent n°${unAdherent.id}</h3>
    	</div>
	</c:if>
	
	<c:if test="${empty unAdherent}">
		<div class="row">
	    	<h3>Création d'un adherent</h3>
    	</div>
	</c:if>
	
	<br/><div class="row">
		<div class="col-md-8 col-md-offset-2"></div>
			<c:if test="${not empty unAdherent}">
				<form action="edition.spring?id=${unAdherent.id}" method="POST" class="form col-md-8 col-md-offset-2">
			</c:if>
			<c:if test="${empty unAdherent}">
			    <form action="edition.spring" method="POST" class="form col-md-8 col-md-offset-2">
			</c:if>
				<div class="form-group">
					<label for="nom">Nom</label><input type="text" name="nom" value="${unAdherent.nom}" class="form-control"/><br/>
				</div>
				<div class="form-group">
					<label for="prenom">Prénom</label><input type="text" name="prenom" value="${unAdherent.prenom}" class="form-control"/><br/>
				</div>
				<div class="form-group">
					<label for="tel">Tel</label><input type="text" name="tel" value="${unAdherent.tel}" class="form-control"/><br/><br/>
				</div>
				<div class="form-group">
					<label for="email">Email</label><input type="text" name="email" value="${unAdherent.email}" class="form-control"/><br/><br/>
				</div>
				
				<c:if test="${not empty unAdherent}">
				    <button type="submit" name="a" value="Modifier" class="btn btn-primary">Modifier</button>
				</c:if>
				<c:if test="${empty unAdherent}">
				    <button type="submit" name="a" value="Ajouter" class="btn btn-primary">Ajouter</button>
				</c:if>
			</form>
		</div>
	</div>
		
	<div class="row">
		<a href="home.spring"><button type="button" class="btn btn-default">Retour</button></a>
	</div>
	
</body>
