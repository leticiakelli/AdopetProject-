<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%-- 
    Document   : index
    Created on : 30/05/2017, 22:07:55
    Author     : Alunos
--%>



<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>AdoPet</title>

        <!-- Bootstrap -->
        <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">

        <!-- Business Casual -->
        <link href="<c:url value="/resources/css/business-casual.css"/>" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="<c:url value="resource/js/html5shiv.min.js"/>"</script>
          <script src="<c:url value="resource/js/respond.min.js"/>"</script>
        <![endif]-->
    <!-- Fonts -->
    <link href="<c:url value="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="https://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"/>" rel="stylesheet" type="text/css">

    </head>
    <body>
      
        <div class ="container">
            
            <h1>Clinicas</h1>
            <br/>
            <a class="btn btn-success" href="<c:url value="/anuncio/novo"/>">Adicionar</a>
            <br/>
            <br/>
            </div>
            <div class ="container">  
            <div class="jumbotron">
                   
            <table class="table">
                <tr>
                    <th>Id</th>
                    <th>Raca</th>
                    <th>Sexo</th>
                    <th>Tipo</th>
                    
                </tr>
                <c:forEach items="${anuncioList}" var="anuncio">
                    <tr>
                        <td>${anuncio.id}</td>
                        <td>${anuncio.raca}</td> 
                        <td>${anuncio.sexo}</td> 
                        <td>${anuncio.tipo}</td>
                        <td><a class="btn btn-success" href="<c:url value="/anuncio/${anuncio.id}/ver"/>">Ver Anuncio</a></td>
                        
                        </td>   
                    </tr>
                </c:forEach>
            </table>
        </div></div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="<c:url value="/resources/js/jquery.js"/>"</script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="<c:url value="/resources/js/bootstrap.min.js"/>"</script>
    </body>

</html>

