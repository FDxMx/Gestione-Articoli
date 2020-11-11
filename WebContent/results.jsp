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
				<h5>Lista dei risultati</h5>
			</div>
			<div class='card-body'>
				<c:if test="${sessionScope.utenteAttribute.ruolo == 'ADMIN' or sessionScope.utenteAttribute.ruolo == 'OPERATORE'}">
				<a class="btn btn-primary " href="PrepareInsertArticoloServlet">Add New</a>
				</c:if>

				<div class='table-responsive'>
					<table class='table table-striped '>
						<thead>
							<tr>
								<th>Id</th>
								<th>Codice</th>
								<th>Descrizione</th>
								<th>Prezzo</th>
								<th>Categoria</th>
								<th>Azioni</th>
							</tr>
						</thead>
						<tbody>
						
							<c:forEach var = "articolo" items = "${requestScope.listaArticoliAttribute}">
								<tr>
								<td><c:out value="${articolo.getId()}"/></td>
								<td><c:out value="${articolo.getCodice()}"/></td>
								<td><c:out value="${articolo.getDescrizione()}"/></td>
								<td><c:out value="${articolo.getPrezzo()}"/></td>
								<td><c:out value="${articolo.getCategoria().getDescrizione()}"/></td>
								<td>
									<c:if test="${sessionScope.utenteAttribute.ruolo == 'ADMIN' or sessionScope.utenteAttribute.ruolo == 'GUEST' or sessionScope.utenteAttribute.ruolo == 'OPERATORE'}">
									<a class="btn  btn-sm btn-outline-secondary"
									href="ShowArticoloServlet?idParametro=${articolo.getId()}">Visualizza</a>
									</c:if>
									<c:if test="${sessionScope.utenteAttribute.ruolo == 'ADMIN' or sessionScope.utenteAttribute.ruolo == 'OPERATORE'}">
									<a class="btn  btn-sm btn-outline-primary ml-2 mr-2"
									href="PrepareUpdateArticoloServlet?idParametro=${articolo.getId()}">Edit</a>
									</c:if>
									<c:if test="${sessionScope.utenteAttribute.ruolo == 'ADMIN'}">
									<a class="btn btn-outline-danger btn-sm"
									href="DeleteArticoliServlet?idParametro=${articolo.getId()}">Delete</a>
									</c:if>
								</td>
							</tr>
							</c:forEach>
							
							
							<%-- <% List<Articolo> listaArticoli = (List<Articolo>)request.getAttribute("listaArticoliAttribute");
		                		for(Articolo item:listaArticoli){ %>
							<tr>
								<td><%=item.getId() %></td>
								<td><%=item.getCodice() %></td>
								<td><%=item.getDescrizione() %></td>
								<td><%=item.getPrezzo() %></td>
								<td><a class="btn  btn-sm btn-outline-secondary"
									href="ShowArticoloServlet?idParametro=<%=item.getId()%>">Visualizza</a>
									<a class="btn  btn-sm btn-outline-primary ml-2 mr-2"
									href="PrepareUpdateArticoloServlet?idParametro=<%=item.getId()%>">Edit</a>
									<a class="btn btn-outline-danger btn-sm"
									href="DeleteArticoliServlet?idParametro=<%=item.getId()%>">Delete</a>
								</td>
							</tr>
							<% } %> --%>
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