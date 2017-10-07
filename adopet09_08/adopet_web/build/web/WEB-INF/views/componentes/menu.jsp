<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Bootstrap -->
        <script src="<c:url value="/resources/js/jquery.js"/>"></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
        <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">

        <!-- Business Casual -->
        <link href="<c:url value="/resources/css/business-casual.css"/>" rel="stylesheet">
        <!--Estilo css -->
        <link href="<c:url value="/resources/css/estilo.css"/>" rel="stylesheet">
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


        <!--<div class="address-bar">Adote essa id�ia</div>-->
        <!--informa��es do usuario logado-->
        <!-- <c:if test="${not empty usuarioLogado}">
             <div id="logado">
                 <h1>O usu�rio logado � ${usuarioLogado}</h1>
 
             </div>
        </c:if>-->
        <!-- Navigation -->


        <nav class="fh5co-nav-style-1 " role="navigation" data-offcanvass-position="fh5co-offcanvass-left">
            <div class="container-fluid"><br><br>
                <div class="col-lg-2 col-md-3 col-sm-3 col-xs-12 fh5co-logo">
                    <a href="#" class="js-fh5co-mobile-toggle fh5co-nav-toggle"><i></i></a>
                    <a href="/adopet-web/index.jsp"><h1 class="cover-text-lead wow fadeInUp" data-wow-duration="1s" data-wow-delay=".5s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.5s; animation-name: fadeInUp;">AdoPet</h1></a>
                </div><br>
                <div class="col-lg-6 col-md-4 col-sm-3 text-center fh5co-link-wrap">
                    <ul data-offcanvass="yes">

                        <li>
                            <a href="/adopet-web/index.jsp">Home</a>
                        </li>
                        <li>
                            <a href="<c:url value="/adocao/"/>">Ado��o</a>
                        </li>
                        <li>
                            <a href="<c:url value="/perdido/"/>">Perdidos</a>
                        </li>
                        <li>
                            <a href="<c:url value="/posAdocao/"/>">P�s Ado��o</a>
                        </li>
                    </ul>
                </div> 
                <div class="col-lg-4 col-md-5 col-sm-3 text-right fh5co-link-wrap">
                    <ul class="fh5co-special" data-offcanvass="yes">
                        <c:if test="${not empty usuarioLogado}">
                            <li>
                                <a href="<c:url value="/anuncio/"/>">Gerenciar</a>
                            </li>
                            <li>
                                <a href="<c:url value="/logout/"/>">Logout</a>
                            </li>
                            <li>
                                <a href="<c:url value="/pessoa/cadastro/update"/>">Meus dados</a>
                            </li>  
                        </c:if>
                        <c:if test="${ empty usuarioLogado}">
                            <li>
                                <a href="<c:url value="/login/"/>">Login</a>       
                            </li>
                            <li>
                                <a href="<c:url value="/pessoa/cadastro/"/>" class="call-to-action">Cadastre-se</a>
                            </li> 
                        </c:if>

                    </ul>
                </div>
            </div>
        </nav>

        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <!-- Indicators -->
        <div class="container"><div id="myCarousel" class="carousel slide" data-ride="carousel">                                            <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="col-md-6"><h3 style="color:#000">Por que adotar um animal?</h3></div>
                                <div class="col-md-6"><p style="color:#000">Quando a crian�a tem menos de um aninho, ter um c�o ou um gato em casa reduz em at� 50% as chances do beb� desenvolver algum tipo e alergia.<font style="color:#ffff">e deixa o ambiente mais positivo para toda a fam�lia</font></p></div>

                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="col-md-6"><h3 style="color:#000">Por que adotar um animal?</h3></div>
                                <div class="col-md-6"><p style="color:#000"> Ao adotar um animal de estima��o voc� poder� dar e receber carinho. Pesquisas relevam que ter um animalzinho em casa afasta a depress�o e deixa o ambiente mais positivo para toda a fam�lia</p></div>

                            </div>
                        </div>


                    </div>
                    <div class="item"><div class="panel panel-default">
                            <div class="panel-body">
                                <div class="col-md-6"><h3 style="color:#000">Por que adotar um animal?</h3></div>
                                <div class="col-md-6"><p style="color:#000"> Ao adotar, voc� ajuda a reduzir o n�mero de c�es e gatos abandonados. Geralmente os animais de rua ou de abrigos j� passaram por muito sofrimento e tudo o que eles precisam � de um bom lar para serem felizes de verdade.</p></div>

                            </div>
                        </div>


                    </div>
                </div>


            </div>
    </body>
</html>

