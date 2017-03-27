<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
	<jsp:include page="template.jsp"></jsp:include>
	
	<div class="row" style="margin-top: 6%;">
		<div class="col-md-10 col-md-offset-1">
			<div class="jumbotron">
			  <h1>Bienvenue sur le site de gestion de la biblioth�que !</h1>
			  <p>Vous pouvez, � partir de ce site, g�rer tous les emprunts de vos adh�rents !</p>
			  <p><a class="btn btn-primary btn-lg" href="/biblio/gestionEmprunt/home.spring" role="button">G�rer vos emprunts</a></p>
			</div>
		</div>
	</div>
	
	<div class="row" style="margin-top: 2%;">
		<h3>
		Derni�re connexion : <fmt:formatDate type="both" value="${dateDerniereConnexion}" />.
		</h3>
	</div>
</body>