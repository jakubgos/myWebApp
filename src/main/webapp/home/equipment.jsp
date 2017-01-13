<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

        <title>Wypozyczalnia</title>


      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
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
          <a class="navbar-brand" href="#">Wypozyczalnia</a>
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
            <li><a href="/home/home">Podsumowanie<span class="sr-only">(current)</span></a></li>
            <li><a href="/home/users">Uzytkownicy</a></li>
            <li class="active"><a href="/home/equipment">Przedmioty</a></li>
            <li><a href="/home/producers">Producenci</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <h1 class="page-header">Producenci</h1>
            <h2 class="sub-header">Dodaj Przedmiot</h2>
            <c:if test="${resultCause =='1'}">
                <div class="alert alert-success" role="alert">
                    <strong>Well done!</strong> ${msg}
                </div>
            </c:if>

            <c:if test="${resultCause =='0'}">
                <div class="alert alert-danger" role="alert">
                    <strong>Oh No! </strong> ${msg}
                </div>
            </c:if>

            <div class="table-responsive">
                <table class="table">
                    <form:form action="/home/addEquipment" method="POST" >
                    <thead>
                    <tr>

                        <th><form:label path="name">Nazwa</form:label></th>
                        <th><form:label path="producerId">Producent</form:label></th>
                        <th>Akcja</th>
                    </tr>

                    </thead>
                    <tbody>
                    <tr>
                        <td><form:input class="form-control" placeholder="Nazwa..." path="name" /> </td>
                        <td>
                            <form:select class="form-control"  path="idEqSelected">
                                <form:option value="NONE"> --SELECT--</form:option>
                                <c:forEach var="eqa" items="${producentList}">
                                    <form:option value="${eqa.id}"  >${eqa.name}</form:option>
                                </c:forEach>
                            </form:select>
                        </td>
                        <td><input type="submit" value="Dodaj Przedmiot" class="btn btn-primary"/></td>
                    </tr>


                    </form:form>
                    </tbody>
                </table>


                <h2 class="page-header">Lista Przedmiotow</h2>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nazwa</th>
                            <th>Producent</th>
                            <th>Status</th>
                            <th>Akcje</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${not empty equipmentList}">
                            <c:forEach var="product" items="${equipmentList}">
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

                                    <td>
                                        <form:form action="/home/userActionEquipment" method="post">
                                            <button type="submit" class="btn btn-info"  name="action" value="editEq">Edycja Przedmiotu</button>
                                            <button type="submit" class="btn btn-warning"  name ="action" value="delEq">Usuwanie</button>
                                            <form:hidden path="id" value="${product.id}"/>
                                        </form:form>

                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>


                        <button type="button" class="btn btn-xs btn-default" onclick="location.href='/home/equipment?size=${size}&page=${page-1}'" >&lt;prev</button>
                        <span class="badge">&nbsp;${page}&nbsp; </span>
                        <button type="button" class="btn btn-xs btn-default" onclick="location.href='/home/equipment?size=${size}&page=${page+1}'" >next&gt;</button>
                        </tbody>
                    </table>

                    </table>

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
