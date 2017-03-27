<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	
	<jsp:include page="../template.jsp"></jsp:include>
	
	<div class="row">
		<h3>Gestion des adherent</h3>
	</div>

	<br/><div class="row col-md-8 col-md-offset-2">
		<table class="table">
			<tr>
				<th>Nom</th>
				<th>Prenom</th>
				<th>Tel</th>
				<th>Email</th>
				<th> </th>
			</tr>
	        <c:forEach items="${allAdherent}" var="listValue">
				<tr>
					<td>${listValue.nom}</td>
					<td>${listValue.prenom}</td>
					<td>${listValue.tel}</td>
					<td>${listValue.email}</td>
					<td><a href="edition.spring?id=${listValue.id}"><button type="button" class="btn btn-primary">Modifier</button></a>
					<a href="delete.spring?id=${listValue.id}"><button type="button" class="btn btn-danger">Supprimer</button></a></td>
				</tr>
			</c:forEach>
		</table>
	
	
	<div class="row">
		<a href="edition.spring"><button type="button" class="btn btn-success">Création d'un adherent</button></a>
	</div>
	
	<div style="text-align: center; margin-top:5%; color: red;">
		${deleteError}
	</div>
</body>
</html>
