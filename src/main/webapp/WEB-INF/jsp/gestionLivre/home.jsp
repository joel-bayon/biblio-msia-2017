<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	
	<jsp:include page="../template.jsp"></jsp:include>
	
	<div class="row">
		<h3>Gestion des livres</h3>
	</div>

	<br/><div class="row col-md-8 col-md-offset-2">
		<table class="table">
			<tr>
				<th>Titre</th>
				<th>Auteur</th>
				<th>Parution</th>
				<th> </th>
			</tr>
	        <c:forEach items="${allLivre}" var="listValue">
				<tr>
					<td>${listValue.titre}</td>
					<td>${listValue.auteur}</td>
					<td>${listValue.parution}</td>
					<td><a href="edition.spring?id=${listValue.id}"><button type="button" class="btn btn-primary">Modifier</button></a>
					<a href="delete.spring?id=${listValue.id}"><button type="button" class="btn btn-danger">Supprimer</button></a></td>
				</tr>
			</c:forEach>
		</table>
	
	
	<div class="row">
		<a href="edition.spring"><button type="button" class="btn btn-success">Création d'un livre</button></a>
	</div>
	
	<div style="text-align: center; margin-top:5%; color: red;">
		${deleteError}
	</div>
</body>
</html>
