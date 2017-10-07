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
        <script src="<c:url value="/resources/js/jquery.js"/>"></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>

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

        <link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.css"/>
        <script type="text/javascript" src="jquery-2.1.4.min.js"></script>
        <script type="text/javascript" src="bootstrap-3.3.5-dist/js/bootstrap.js"></script>

    </head>

    <body>
        <jsp:include page = "/WEB-INF/views/componentes/menu_Inicial.jsp" />
        <div class="fh5co-cover fh5co-cover-style-2 " data-stellar-background-ratio="0.5" data-next="yes"  style="background-image: url(resources/img/wallpaper.jpg);">

            <div class="fh5co-overlay"></div>
            <div class="fh5co-cover-text">
                <div class="container">
                    <div class="row">

                        <div class="col-md-push-1 col-md-10 full-height ">
                            <div class="fh5co-cover-intro text-center">
                                <h3 class="brand">Adote, porque o amor não tem preço nem raça.</h3>
                                <hr class="tagline-divider">
                                <h1 class="brand">
                                    <small class="text-center"><a href="<c:url value="/adocao/"/>" class="btn btn-primary btn-lg" role="button"> <font size="5"> ADOTAR AGORA</font>  </a></small>
                                </h1>
                            </div>
                        </div>


                    </div>
                </div>
            </div>	
        </div>

        <div class="fh5co-blog-style-1">
            <div class="container">
                <div class="row p-b">
                    <div class="col-md-6 col-md-offset-3 text-center">
                        <h2 class="fh5co-heading wow fadeInUp" data-wow-duration="1s" style="color:#000">Animais em adoção</h2>
                        <p class="wow fadeInUp" data-wow-duration="1s" data-wow-delay=".8s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.8s; animation-name: fadeInUp;"></p>
                    </div>
                </div>
                <div class="row p-b">
                    <div class="col-md-4 col-sm-6 col-xs-6 col-xxs-12">
                        <div class="fh5co-post wow fadeInLeft" data-wow-duration="1s" data-wow-delay="1.1s" style="visibility: visible; animation-duration: 1s; animation-delay: 1.1s; animation-name: fadeInLeft;">
                            <div class="fh5co-post-image">
                                <div class="fh5co-overlay"></div>	
                                <div class="fh5co-category"><a href="#">AdoPet</a></div>	
                                <img src="resources/img/img_same_dimension_2.jpg" alt="Image" class="img-responsive">
                            </div>

                            <div class="fh5co-post-text">
                                <h3><a href="#">Raça indefinida</a>
                                    <small>fêmea</small></h3>
                                <p>mary é uma gatinha muito fofa, gosta de carinho, é muito carinhosa, não faz muita bagunça e gosta de dormir</p>
                            </div>
                            <div class="fh5co-post-meta">
                                <a href="#"><i class="icon-chat"></i> por</a>
                                <a href="#"><i class="icon-clock2"></i> Leticia Kelli</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-6 col-xs-6 col-xxs-12">
                        <div class="fh5co-post wow fadeInLeft" data-wow-duration="1s" data-wow-delay="1.4s" style="visibility: visible; animation-duration: 1s; animation-delay: 1.4s; animation-name: fadeInLeft;">
                            <div class="fh5co-post-image">
                                <div class="fh5co-overlay"></div>	
                                <div class="fh5co-category"><a href="#">AdoPet</a></div>	
                                <img src="resources/img/img_same_dimension_3.jpg" alt="Image" class="img-responsive">
                            </div>
                            <div class="fh5co-post-text">
                                <h3><a href="#">Vira lata</a>
                                    <small>Macho</small></h3>
                                <p>scooby é um cão muito docil, carinhoso, gosta muito de brincar, mas é apenas um pouco arteiro, ele gosta muito de crianças, adote-o</p>
                            </div>
                            <div class="fh5co-post-meta">
                                <a href="#"><i class="icon-chat"></i> por</a>
                                <a href="#"><i class="icon-clock2"></i> Caio Zeurgo</a>
                            </div>
                        </div>
                    </div>
                    <div class="clearfix visible-sm-block"></div>
                    <div class="col-md-4 col-sm-6 col-xs-6 col-xxs-12">
                        <div class="fh5co-post wow fadeInLeft" data-wow-duration="1s" data-wow-delay="1.7s" style="visibility: visible; animation-duration: 1s; animation-delay: 1.7s; animation-name: fadeInLeft;">
                            <div class="fh5co-post-image">
                                <div class="fh5co-overlay"></div>	
                                <div class="fh5co-category"><a href="#">AdoPet</a></div>	
                                <img src="resources/img/img_same_dimension_4.jpg" alt="Image" class="img-responsive">
                            </div>
                            <div class="fh5co-post-text">
                                <h3><a href="#">Vira lata</a>
                                    <small>Macho</small></h3>

                                <p>Este é o totó, um cãozinho dócil e muito brincalhão, gosta de crianças e foi acustumado dentro de casa
                                </p>
                            </div>
                            <div class="fh5co-post-meta">
                                <a href="#"><i class="icon-chat"></i> por</a>
                                <a href="#"><i class="icon-clock2"></i> Gabriel </a>
                            </div>
                        </div>
                    </div>
                    <div class="clearfix visible-sm-block"></div>
                </div>
                <div class="row">
                    <div class="col-md-4 col-md-offset-4 text-center wow fadeInUp" data-wow-duration="1s" data-wow-delay="2s" style="visibility: visible; animation-duration: 1s; animation-delay: 2s; animation-name: fadeInUp;">
                        <a href="<c:url value="/adocao/"/>" class="btn btn-primary btn-lg">Ver animais</a>
                    </div>
                </div>
            </div>
        </div>


        <div class="fh5co-features-style-1" style="background-image: url(resources/img/full_1.jpg);" data-stellar-background-ratio="0.5">
            <div class="fh5co-overlay"></div>
            <div class="container" style="z-index: 3; position: relative;">
                <div class="row p-b">
                    <div class="col-md-6 col-md-offset-3 text-center wow fadeInUp" data-wow-duration="1s" data-wow-delay=".5s">
                        <h2 class="fh5co-heading">AdoPet, Adote um animal, adote essa ideia!</h2>
                    </div>
                </div>
                <div class="row">
                    <div class="fh5co-features">
                        <div class="fh5co-feature wow fadeInUp" data-wow-duration="1s" data-wow-delay=".8s">
                            <div class="icon"><i class="icon-ribbon"></i></div>
                            <h3>Esperança</h3>
                            <p>O abandono de animais pode ser combatido com informação, prevenção e conscientização, se você pode mudar o destino de um animal carente, por que você não faria isso?.</p>
                        </div>
                        <div class="fh5co-feature wow fadeInUp" data-wow-duration="1s" data-wow-delay="1.1s">
                            <div class="icon"><i class="icon-image22"></i></div>
                            <h3>Mortes</h3>
                            <p>A cada ano, aproximadamente 2,7 milhões de cães e gatos morrem porque os abrigos estão muito cheios ou há uma grande falta de lares adotivos.</p>
                        </div>
                        <div class="fh5co-feature wow fadeInUp" data-wow-duration="1s" data-wow-delay="1.4s">
                            <div class="icon"><i class=" icon-monitor"></i></div>
                            <h3>Abandono</h3>
                            <p>De acordo com a Organização Mundial da Saúde, há cerca de 30 milhões de animais abandonados no Brasil. Destes, 20 milhões são cachorros, enquanto 10 milhões são gatos.</p>
                        </div>

                        <div class="fh5co-feature wow fadeInUp" data-wow-duration="1s" data-wow-delay="1.7s">
                            <div class="icon"><i class="icon-video2"></i></div>
                            <h3>Sentimentos</h3>
                            <p>Adotar um animal é valorizar a vida. Um cão ou gato é capaz de sentir emoções, e por isso, sofre tanto quanto nós, humanos.</p>
                        </div>
                        <div class="fh5co-feature wow fadeInUp" data-wow-duration="1s" data-wow-delay="2s">
                            <div class="icon"><i class="icon-bag"></i></div>
                            <h3>Adotar</h3>
                            <p>É recuperar uma vida literalmente jogada fora.</p>
                        </div>
                        <div class="fh5co-feature wow fadeInUp" data-wow-duration="1s" data-wow-delay="2.3s">
                            <div class="icon"><i class="icon-mail2"></i></div>
                            <h3>Ensinamentos</h3>
                            <p>Ao adotar um animal carente, você ensina ao seu filho, às crianças com quem você convive, verdadeiros valores de responsabilidade, comprometimento e, sobretudo, humanidade.</p>
                        </div>

                    </div>
                </div>
            </div>
        </div>


        <div class="fh5co-project-style-2" style="background-image: url(resources/img/branco.png);" data-stellar-background-ratio="0">
            <div class="container">
                <div class="row p-b">
                    <div class="col-md-6 col-md-offset-3 text-center">
                        <hr>
                        <h2 class="fh5co-heading a" style="color:#000">AdoPet</h2>
                        <hr>
                    </div>
                    <div class="col-lg-2 col-md-4 "> <h1></h1> </div>
                    <div class="col-md-push-2 col-md-8 full-height text-center">

                        <hr class="visible-xs">
                        <p style="color:#000" ><font size="4">
                            O projeto AdoPet tem como objetivo, facilitar a adoção de animal de estimação, buscando facilitar
                            o processo de anunciar animal para adoção e de adoção, onde qualquer pessoa pode anunciar seu animal,
                            ou mesmo procurar por um pet que mais lhe agrada.
                            <strong>Atenção esta aplicação não foi desenvolvida com intuito de venda animais e associações a Petshop</strong>
                            </font></p>
                    </div>
                </div>
            </div>




            <div class="fh5co-projects">
                <ul>
                    <li class="wow fadeInUp" style="background-image: url(resources/img/caesgatos.jpg);" data-wow-duration="1s" data-wow-delay="1.4s" data-stellar-background-ratio="2.5">
                        <a href="#">
                            <div class="fh5co-overlay"></div>
                            <div class="container">
                                <div class="fh5co-text">
                                    <div class="fh5co-text-inner">
                                        <div id="myCarousel" class="carousel slide" data-ride="carousel">


                                            <div class="carousel-inner" role="listbox">
                                                <div class="item active">
                                                    <div class="col-md-6"><h3>Por que adotar um animal?</h3></div>
                                                    <div class="col-md-6"><p>Quando a criança tem menos de um aninho, ter um cão ou um gato em casa reduz em até 50% as chances do bebê desenvolver algum tipo e alergia.</p></div>

                                                </div>
                                                <div class="item">
                                                    <div class="col-md-6"><h3>Por que adotar um animal?</h3></div>
                                                    <div class="col-md-6"><p> Ao adotar um animal de estimação você poderá dar e receber carinho. Pesquisas relevam que ter um animalzinho em casa afasta a depressão e deixa o ambiente mais positivo para toda a família</p></div>

                                                </div>
                                                <div class="item">
                                                    <div class="text-center" style="size:7"><h1>ADOTE!</h1></div>

                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </li>

                </ul>
            </div>

            <!-- /.container -->

            <jsp:include page = "/WEB-INF/views/componentes/footer.jsp"/>

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
