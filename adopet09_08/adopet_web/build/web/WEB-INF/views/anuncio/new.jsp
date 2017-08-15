<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>AdoCão</title>

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
            
            <br/>
            <a class="btn btn-success" href="<c:url value="/anuncio"/>">Voltar</a>
            <br/>
            <br/>
           
           <div class="jumbotron"> 
            <table class="table">
                <tr>
                    <th>id</th>
                    <th>Tipo</th>
                    <th>Especie</th>
                    <th>Sexo</th>
                    <th>Porte</th>
                    <th>Idade</th>
                    <th>Caracteristica</th>
                    
                </tr>
           
                <c:forEach items="${anuncioList}" var="anuncio">
                  
                    <tr>
                         
                        <h1>${anuncio.nome}</h1>
                        <td>${anuncio.id}</td>
                        <td>${anuncio.tipo}</td>
                        <td>${anuncio.especie}</td>
                        <td>${anuncio.sexo}</td>
                        <td>${anuncio.porte}</td>
                        <td>${anuncio.idade}</td>
                        <td>${anuncio.caracteristica}</td>
                        
                        

                        <td><a class="btn-sm btn-warning" href="<c:url value="/anuncio/${anuncio.id}/alterar"/>">Alterar</a></td>
                        <td><a class="btn-sm btn-danger" href="<c:url value="/anuncio/${anuncio.id}/excluir"/>">Excluir</a></td>

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
