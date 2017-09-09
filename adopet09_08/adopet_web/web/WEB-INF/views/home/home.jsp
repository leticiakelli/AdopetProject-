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
        <script src="<c:url value="resources/js/html5shiv.min.js"/>"</script>
          <script src="<c:url value="resources/js/respond.min.js"/>"</script>
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
                    <div class="col-lg-12 text-center">
                        <div id="carousel-example-generic" class="carousel slide">
                            <!-- Indicators -->
                            <ol class="carousel-indicators hidden-xs">
                                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                            </ol>

                            <!-- Wrapper for slides -->
                            <div class="carousel-inner">
                                <div class="item active">
                                    <img class="img-responsive img-full" src="resources/img/all.jpg" alt="">
                                </div>
                                <div class="item">
                                    <img class="img-responsive img-full" src="resources/img/cat.jpg" alt="">
                                </div>
                                <div class="item">
                                    <img class="img-responsive img-full" src="resources/img/dog.jpg" alt="">
                                </div>
                            </div>

                            <!-- Controls -->
                            <a class="left carousel-control" href="<c:url value="#carousel-example-generic"/>" data-slide="prev">
                                <span class="icon-prev"></span>
                            </a>
                            <a class="right carousel-control" href="<c:url value = "#carousel-example-generic"/>" data-slide="next">
                                <span class="icon-next"></span>
                            </a>
                        </div>
                        <h2 class="brand-before">
                            <small>Bem vindo ao</small>
                        </h2>
                        <h1 class="brand-name">AdoPet</h1>
                        <hr class="tagline-divider">
                        <h2>
                            <small>Atividade:
                                <strong>Projeto Interdisciplinar</strong>
                            </small>
                        </h2>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="box">
                    <div class="col-lg-12">
                        <hr>
                        <h2 class="intro-text text-center">
                            <strong>AdoPet</strong>
                        </h2>
                        <hr>
                        <img class="img-responsive img-border img-left" src="resources/img/intro-pic.jpg" alt="">
                        <hr class="visible-xs">
                        <p>
                            O projeto AdoPet tem como objetivo, facilitar a adoção de animal de estimação, buscando facilitar
                            o processo de anunciar animal para adoção e de adoção, onde qualquer pessoa pode anunciar seu animal,
                            ou mesmo procurar por um pet que mais lhe agrada.
                            <strong>Atenção esta aplicação não foi desenvolvida com intuito de venda animais e associações a Petshop</strong>
                        </p>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="box">
                    <div class="col-lg-12">
                        <hr>
                        <h2 class="intro-text text-center">
                            <strong>-O projeto-</strong>
                        </h2>
                        <hr>
                        <p>
                            O projeto AdoPet foi desenvolvido por alunos do terceiro ano de Sistema de Informação da Fai-Centro de Ensino
                            Superior em Gestão, Tecnologia e Educação. Supervionados pelos Professores, Júlio Rezende, Silvana Lima e Welligtom Openheimer.
                        </p>
                    </div>
                </div>
            </div>

        </div>

        <div class="container">

            <div class="row">
                <div class="box">
                    <div class="col-lg-12">
                        <hr>
                        <h2 class="intro-text text-center">About
                            <strong>business casual</strong>
                        </h2>
                        <hr>
                    </div>
                    <div class="col-md-6">
                        <img class="img-responsive img-border-left" src="img/slide-2.jpg" alt="">
                    </div>
                    <div class="col-md-6">
                        <p>This is a great place to introduce your company or project and describe what you do.</p>
                        <p>Lid est laborum dolo rumes fugats untras. Etharums ser quidem rerum facilis dolores nemis omnis fugats vitaes nemo minima rerums unsers sadips amets.</p>
                        <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.</p>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>

            <div class="row">
                <div class="box">
                    <div class="col-lg-12">
                        <hr>
                        <h2 class="intro-text text-center">Our
                            <strong>Team</strong>
                        </h2>
                        <hr>
                    </div>
                    <div class="col-sm-4 text-center">
                        <img class="img-responsive" src="http://placehold.it/750x450" alt="">
                        <h3>John Smith
                            <small>Job Title</small>
                        </h3>
                    </div>
                    <div class="col-sm-4 text-center">
                        <img class="img-responsive" src="http://placehold.it/750x450" alt="">
                        <h3>John Smith
                            <small>Job Title</small>
                        </h3>
                    </div>
                    <div class="col-sm-4 text-center">
                        <img class="img-responsive" src="http://placehold.it/750x450" alt="">
                        <h3>John Smith
                            <small>Job Title</small>
                        </h3>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>

        </div>
     
        <!-- /.container -->

        <footer>
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <p>Copyright &copy; AdoPet 2017</p>
                    </div>
                </div>
            </div>
        </footer>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="<c:url value="/resources/js/jquery.js"/>"</script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="<c:url value="/resources/js/bootstrap.min.js"/>"</script>

        <!-- Script to Activate the Carousel -->
        <script>
            $('.carousel').carousel({
                interval: 5000 //changes the speed
            })
        </script>

    </body>

</html>
