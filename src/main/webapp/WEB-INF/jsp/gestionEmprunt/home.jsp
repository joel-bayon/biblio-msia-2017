<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	
	<jsp:include page="../template.jsp"></jsp:include>
	
	<div class="row">
		<h3>Gestion des emprunts</h3>
	</div>

	<br/><div class="row">
		<div class="col-md-8 col-md-offset-2">
			<div class="panel panel-default">
			  	<div class="panel-body">
			    	<a href="home.spring?a=cours"><button type="button" class="btn btn-info">Emprunts en cours</button></a>&nbsp
			    	<a href="home.spring?a=termines"><button type="button" class="btn btn-danger">Emprunts terminés</button></a>&nbsp
<!-- 			    	<a href="liste.spring?type=0"><button type="button" class="btn btn-warning">Rechercher un emprunt</button></a>&nbsp -->
<!-- 			    	<a href="liste.spring?type=0"><button type="button" class="btn btn-warning">Emprunts en terminés</button></a>&nbsp -->
			  	</div>
			</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			
				<c:if test="${a == null || a == 'cours'}">
					<div class="panel panel-info">
					<div class="panel-heading">Emprunts en cours</div>
				</c:if>
				<c:if test="${a == 'termines'}">
					<div class="panel panel-danger">
					<div class="panel-heading">Emprunts terminés</div>
				</c:if>
				<table class="table">
					<tr>
						<th>Debut</th>
						<c:if test="${a == 'termines'}">
							<th>Fin</th>
						</c:if>
						<th>Livre</th>
						<th>Adherent</th>
						<th> </th>
					</tr>
			        <c:forEach items="${allEmprunt}" var="listValue">
						<tr>
							<td><fmt:formatDate type="date" value="${listValue.debut}" /></td>
							<c:if test="${a == 'termines'}">
								<td><fmt:formatDate type="date" value="${listValue.fin}" /></td>
							</c:if>	
							<td>${listValue.livre.titre} <i>(${listValue.livre.auteur})</i></td>
							<td>${listValue.adherent.nom} ${listValue.adherent.prenom}</td>
							<td>
								<c:if test="${a == null || a == 'cours'}">
									<a href="edition.spring?id=${listValue.id}"><button type="button" class="btn btn-primary">Modifier l'adherent de l'emprunt</button></a>
								</c:if>
								<c:if test="${listValue.fin == null}">
									<a href="restituer.spring?idLivre=${listValue.livre.id}&idAdherent=${listValue.adherent.id}"><button type="button" class="btn btn-warning">Restituer le livre</button></a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			
		</div>
	</div>
	
	<div class="row">
		<a href="edition.spring"><button type="button" class="btn btn-success">Création d'un emprunt</button></a>
	</div>
	
	<div class="row text-center" style="margin-top:5%; color: red;">
		${deleteError}
	</div>
	
</body>
</html>
