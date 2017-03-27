<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

	<jsp:include page="../template.jsp"></jsp:include>
	
	<c:if test="${not empty unEmprunt}">
		<div class="row">
	    	<h3>Modification de l'emprunt n°${unEmprunt.id}</h3>
    	</div>
	</c:if>
	
	<c:if test="${empty unEmprunt}">
		<div class="row">
	    	<h3>Création d'un emprunt</h3>
    	</div>
	</c:if>
	
	<br/><div class="row">
		<div class="col-md-8 col-md-offset-2"></div>
			<c:if test="${not empty unEmprunt}">
				<form action="edition.spring?id=${unEmprunt.id}" method="POST" class="form col-md-8 col-md-offset-2">
			</c:if>
			<c:if test="${empty unEmprunt}">
			    <form action="edition.spring" method="POST" class="form col-md-8 col-md-offset-2">
			</c:if>
				<c:if test="${empty unEmprunt}">
					<div class="form-group">
						<label for="livre">Livre</label>
						<select name="livre" class="form-control">
							<option value="null">  </option>
							<c:forEach items="${allLivre}" var="listValue">
								<c:if test="${unEmprunt.livre.id == listValue.id}">
									<option value="${listValue.id}" selected>${listValue.titre} (${listValue.auteur})</option>
								</c:if>
								<c:if test="${unEmprunt.livre.id != listValue.id}">
									<option value="${listValue.id}">${listValue.titre} (${listValue.auteur})</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
				</c:if>
				<div class="form-group">
					<label for="adherent">Adherent</label>
					<select name="adherent" class="form-control">
						<option value="null">  </option>
						<c:forEach items="${allAdherent}" var="listValue">
							<c:if test="${unEmprunt.adherent.id == listValue.id}">
								<option value="${listValue.id}" selected>${listValue.nom} ${listValue.prenom}</option>
							</c:if>
							<c:if test="${unEmprunt.adherent.id != listValue.id}">
								<option value="${listValue.id}">${listValue.nom} ${listValue.prenom}</option>
							</c:if>
						</c:forEach>
					</select>
				</div>

				<c:if test="${not empty unEmprunt}">
				    <button type="submit" name="a" value="Modifier" class="btn btn-primary">Modifier</button>
				</c:if>
				<c:if test="${empty unEmprunt}">
				    <button type="submit" name="a" value="Ajouter" class="btn btn-primary">Ajouter</button>
				</c:if>
			</form>
		</div>
	</div>
		
	<div class="row">
		<a href="home.spring"><button type="button" class="btn btn-default">Retour</button></a>
	</div>
	
</body>
