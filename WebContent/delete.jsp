<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:if test= "${sessionScope.utenteAttribute == null or sessionScope.utenteAttribute.ruolo == 'GUEST' or sessionScope.utenteAttribute.ruolo == 'OPERATORE'}"  >
		<c:redirect url = "index.jsp"/>
</c:if>

<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Inserisci nuovo</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />
	
	<main role="main" class="container">
	
		<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
			 	Operazione fallita!
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
		</div>
		
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Sei sicuro di voler eliminare?</h5> 
		    </div>
		    <div class='card-body'>


					<form method="get" novalidate="novalidate">
					
						<input type="hidden" name="id" id="id" value="${requestScope.id}">
						
						<button type="submit" name="submit" value="submit" id="submit" formaction="ConfermaDeleteArticoliServlet" class="btn btn-primary">Conferma</button>
						<button type="submit" name="submit" value="submit" id="submit" formaction="ListArticoliServlet" class="btn btn-primary">No</button>
					

					</form>

		    
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="./footer.jsp" />
	
</body>
</html>