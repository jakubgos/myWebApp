<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/bootstrap/docs/favicon.ico">

    <title>Dashboard Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="/bootstrap/docs/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/bootstrap/docs/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/bootstrap/docs/examples/dashboard/dashboard.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]>
      <script src="/bootstrap/docs/assets/js/ie8-responsive-file-warning.js"></script>
      <![endif]-->
    <script src="/bootstrap/docs/assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#">Dashboard</a></li>
            <li><a href="#">Settings</a></li>
            <li><a href="#">Profile</a></li>
            <li><a href="#">Help</a></li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li class="active"><a href="#">Podsumowanie<span class="sr-only">(current)</span></a></li>
            <li><a href="/home/users">Uzytkownicy</a></li>
            <li><a href="/home/equipment">Przedmioty</a></li>
            <li><a href="/home/producers">Producenci</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">Podsumowanie</h1>
          <h2 class="sub-header">Uzytkownicy</h2>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                    <th>Id</th>
                  <th>Imie</th>
                  <th>Nazwisko</th>
                  <th>Produkty</th>
                </tr>
              </thead>
              <tbody>
                <c:if test="${not empty userList}">
                    <c:forEach var="user" items="${userList}">
                <tr>
                    <td style="width: 5px">${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                  <td>
                    <c:forEach var="product" items="${user.products}">
                        ${product.name}

                    </c:forEach>
                  </td>
                </tr>
                    </c:forEach>
                </c:if>
              </tbody>
            </table>
          </div>
        </div>

          <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
              <h2 class="sub-header">Produkty</h2>
              <div class="table-responsive">
                  <table class="table table-striped">
                      <thead>
                      <tr>
                          <th>Id</th>
                          <th>Nazwa</th>
                          <th>Producent</th>
                          <th>Status</th>
                      </tr>
                      </thead>
                      <tbody>
                      <c:if test="${not empty productList}">
                          <c:forEach var="product" items="${productList}">
                              <tr>
                                  <td style="width: 5px">${product.id}</td>
                                  <td>${product.name}</td>
                                  <td>${product.producer.name}</td>
                                  <td>
                                      <c:if test="${product.isBorrowed=='0'}">
                                          <span class="label label-success">Wolny</span>
                                      </c:if>
                                      <c:if test="${product.isBorrowed =='1'}">
                                          <span class="label label-danger">Wypozyczony</span>
                                      </c:if>
                                  </td>
                              </tr>
                          </c:forEach>
                      </c:if>
                      </tbody>
                  </table>
              </div>
          </div>


          <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
              <h2 class="sub-header">Producenci</h2>
              <div class="table-responsive">
                  <table class="table table-striped">
                      <thead>
                      <tr>
                          <th>Id</th>
                          <th>Nazwa</th>
                      </tr>
                      </thead>
                      <tbody>
                      <c:if test="${not empty producerList}">
                          <c:forEach var="producer" items="${producerList}">
                              <tr>
                                  <td style="width: 5px">${producer.id}</td>
                                  <td>${producer.name}</td>
                              </tr>
                          </c:forEach>
                      </c:if>
                      </tbody>
                  </table>
              </div>
          </div>

      </div>


    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="/bootstrap/docs/assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="/bootstrap/docs/dist/js/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="/bootstrap/docs/assets/js/vendor/holder.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="/bootstrap/docs/assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
