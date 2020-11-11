<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:if test= "${sessionScope.utenteAttribute == null}"  >
		<c:redirect url = "index.jsp"/>
</c:if>

<%@page import="it.gestionearticoli.model.Articolo"%>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Visualizza elemento</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />
	
	<main role="main" class="container">
		
		<div class='card'>
		    <div class='card-header'>
		        Visualizza dettaglio
		    </div>
		    
			<%Articolo articolo = (Articolo)request.getAttribute("articoloParam");%>
		    <div class='card-body'>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Codice:</dt>
				  <dd class="col-sm-9"><%= articolo.getCodice()%></dd>
		    	</dl>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Descrizione:</dt>
				  <dd class="col-sm-9"><%= articolo.getDescrizione()%></dd>
		    	</dl>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Prezzo:</dt>
				  <dd class="col-sm-9"><%= articolo.getPrezzo()%></dd>
		    	</dl>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Categoria:</dt>
				  <dd class="col-sm-9"><%= articolo.getCategoria().getDescrizione()%></dd>
		    	</dl>
		    	
		    	
		    </div>
		    
		</div>	
	
	
	
	<!-- end main container -->	
	</main>
	<jsp:include page="./footer.jsp" />
	
</body>
</html>