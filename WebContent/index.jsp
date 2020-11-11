<!doctype html>
<html lang="it">
  <head>
    
    <jsp:include page="./header.jsp" />
    
    <!-- Custom styles for this template -->
    <link href="./assets/css/global.css" rel="stylesheet">
    <style type="text/css">
    	body {
		  padding-top: 3.5rem;
		}	
    </style>
    
    <title>Gestione degli Articoli!</title>
  </head>
  <body>
  
  
	<main role="main">

	  <!-- Main jumbotron for a primary marketing message or call to action -->
	  <div class="jumbotron" >
	    <div class="container">
	      <h1 class="display-3">Effettua il LOG IN per la Gestione degli Articoli!</h1>
	    	
	    	<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
	    	
	    	<form method="post" action="LogInServlet" novalidate="novalidate">
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>USERNAME <span class="text-danger">*</span></label>
								<input type="text" name="username" id="username" class="form-control" placeholder="Inserire l'username" required>
							</div>
							
							<div class="form-group col-md-6">
								<label>PASSWORD <span class="text-danger">*</span></label>
								<input type="text" name="password" id="password" class="form-control" placeholder="Inserire la password" required>
							</div>
						</div>
							
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">LOG IN</button>
			</form>
	    </div>
	  </div>
	</main>
  </body>
</html>