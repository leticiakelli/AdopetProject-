<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

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

        <jsp:include page = "/WEB-INF/views/componentes/menu.jsp" />
        <div class="container">

            <div class="row">
                <div class="box">
                  
                    <div class="col-lg-12">
                        <hr>
                        <h2 class="intro-text text-center">Company
                            <strong>blog</strong>
                        </h2>
                        <hr>
                    </div>
                    <c:forEach items="${anuncioList}" var="anuncio" varStatus="loop">


                        <div class="col-lg-12 text-center">
                            <img class="img-responsive img-border img-full" src="img/slide-1.jpg" alt="">
                            <h2>${anuncio.raca}
                                <br>
                                <small>${anuncio.sexo}</small>
                            </h2>
                            <p>${anuncio.caracteristicas}</p>
                             <img src="data:image/jpeg;base64,${anuncioImageList[loop.index]}" style="height:200px;width:300px"/>
                             <br/> <br/>
                             <a href="#" class="btn btn-default btn-lg">Read More</a>
                            <hr>
                        </div>
                    </c:forEach>

                    <div class="col-lg-12 text-center">
                        <ul class="pager">
                            <li class="previous"><a href="#">&larr; Older</a>
                            </li>
                            <li class="next"><a href="#">Newer &rarr;</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

        </div>
        <!-- /.container -->

        <footer>
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <p>Copyright &copy; Your Website 2014</p>
                    </div>
                </div>
            </div>
        </footer>


        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="<c:url value="/resources/js/jquery.js"/>"</script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="<c:url value="/resources/js/bootstrap.min.js"/>"</script>
    </body>

</html>
