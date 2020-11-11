<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:if test= "${sessionScope.utenteAttribute == null}"  >
		<c:redirect url = "index.jsp"/>
</c:if>

<%@page import="it.gestionearticoli.model.Articolo"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="./header.jsp" />
<title>Pagina dei risultati</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="./navbar.jsp" />

	<main role="main" class="container">

		<div
			class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}"
			role="alert">
			${successMessage}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="alert alert-danger alert-dismissible fade show d-none"
			role="alert">
			Esempio di operazione fallita!
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="alert alert-info alert-dismissible fade show d-none"
			role="alert">
			Aggiungere d-none nelle class per non far apparire
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div class='card'>
			<div class='card-header'>
				<h5>Lista dei risultati della categoria: "${requestScope.categoria.descrizione}"</h5>
			</div>
			<div class='card-body'>

				<div class='table-responsive'>
					<table class='table table-striped '>
						<thead>
							<tr>
								<th>Id</th>
								<th>Codice</th>
								<th>Descrizione</th>
								<th>Prezzo</th>
							</tr>
						</thead>
						<tbody>
						
							<c:forEach var = "articolo" items = "${requestScope.categoria.listaArticoli}">
								<tr>
								<td><c:out value="${articolo.getId()}"/></td>
								<td><c:out value="${articolo.getCodice()}"/></td>
								<td><c:out value="${articolo.getDescrizione()}"/></td>
								<td><c:out value="${articolo.getPrezzo()}"/></td>
							</tr>
							</c:forEach>
							
						</tbody>
					</table>
				</div>

				<!-- end card-body -->
			</div>
		</div>
		<!-- end container -->
	</main>
	<jsp:include page="./footer.jsp" />
</body>
</html>