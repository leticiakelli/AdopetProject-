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

        <div class="brand">Business Casual</div>
        <div class="address-bar">3481 Melrose Place | Beverly Hills, CA 90210 | 123.456.7890</div>

        <c:if test="${not empty usuarioLogado}">
            <div id="logado">
                <h1>O usuário logado é ${usuarioLogado}</h1>
                <a type="button" class="btn btn-default" href="<c:url value="/logout"></c:url>">
                        Logout
                    </a>
                </div>
        </c:if>

        <!-- Navigation -->
        <nav class="navbar navbar-default" role="navigation">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <!-- navbar-brand is hidden on larger screens, but visible when the menu is collapsed -->
                    <a class="navbar-brand" href="index.html">Business Casual</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="index.html">Home</a>
                        </li>
                        <li>
                            <a href="about.html">About</a>
                        </li>
                        <li>
                            <a href="blog.html">Blog</a>
                        </li>
                        <li>
                            <a href="contact.html">Contact</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
        </nav>

        <div class="container">

            <div class="row">
                <div class="box">
                    <c:if test="${not empty usuarioLogado}">
                        <div class="col-lg-12">
                            <hr>

                            <a class="btn btn-info" href="<c:url value="/anuncio"></c:url>">Gerenciar Anuncios</a>

                                <hr>
                            </div>
                    </c:if>
                    <div class="col-lg-12">
                        <hr>
                        <h2 class="intro-text text-center">Company
                            <strong>blog</strong>
                        </h2>
                        <hr>
                    </div>
                     <c:forEach items="${anuncioList}" var="anuncio">
                        
                    
                    <div class="col-lg-12 text-center">
                        <img class="img-responsive img-border img-full" src="img/slide-1.jpg" alt="">
                        <h2>${anuncio.raca}
                            <br>
                            <small>${anuncio.sexo}</small>
                        </h2>
                        <p>${anuncio.caracteristicas}</p>
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
