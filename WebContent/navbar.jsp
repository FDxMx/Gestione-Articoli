<!-- navbar -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary">

	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
       <span class="navbar-toggler-icon"></span>
     </button>

  <div class="collapse navbar-collapse" id="navbarsExampleDefault">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="home.jsp">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Link</a>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
        <div class="dropdown-menu" aria-labelledby="dropdown01">
          <a class="dropdown-item" href="home.jsp">Home</a>
          <a class="dropdown-item" href="ListArticoliServlet">Risultati articoli</a>
          <a class="dropdown-item" href="ListCategorieServlet">Risultati categorie</a>
          <c:if test="${sessionScope.utenteAttribute.ruolo == 'ADMIN' or sessionScope.utenteAttribute.ruolo == 'OPERATORE'}">
          <a class="dropdown-item" href="PrepareInsertArticoloServlet">Inserisci nuovo elemento</a>
          <a class="dropdown-item" href="PrepareInsertCategoriaServlet">Inserisci una nuova categoria</a>
       	  </c:if>
        </div>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0">
      Accesso effettuato da: ${sessionScope.utenteAttribute.nome} ${sessionScope.utenteAttribute.cognome} come: ${sessionScope.utenteAttribute.ruolo}
      &nbsp;
      &nbsp;
      <button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary" formaction = "LogOutServlet" formmethod = "get">LOG OUT</button>
      <!-- <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search"> -->
      &nbsp;
      &nbsp;
      <button class="btn btn-success my-2 my-sm-0" type="submit" formaction = "PrepareSearchArticoloServlet" formmethod = "get" >Search Articolo</button>
      &nbsp;
      &nbsp;
      <button class="btn btn-success my-2 my-sm-0" type="submit" formaction = "searchCategoria.jsp" >Search Categoria</button>
    </form>
  </div>
</nav>
