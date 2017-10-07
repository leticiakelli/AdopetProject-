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
        <jsp:include page = "/WEB-INF/views/componentes/menu.jsp" />
<div class="container-fluid">

            <div class="col-lg-1"></div>
            <div class="panel panel-default col-lg-10 col-md-10 full-height js-full-height">
                <div class="panel-heading full-height">
        
            <c:if test="${empty anuncio}">
                <h1 style="color:#122b40">Novo Anúncio</h1>
            </c:if>

            <c:if test="${not empty anuncio}">
                <h1 style="color:#122b40">Editar anuncio</h1>
            </c:if>
                </div>
            <form method="post" enctype="multipart/form-data">

                    <div class="panel-body">

                        <div class="row">
                        <input type="hidden" value="${anuncio.id}" name="anuncioId"/>

                        <div class="form-group col-lg-6">
                            <label for="tipo">Tipo Anuncio</label>

                            <select class="form-control" id="tipo" 
                                    name="tipo">

                                <option value="">Selecione...</option>
                                <option <c:if test="${anuncio.tipo eq 'adocao'}"> selected="true" </c:if>value="adocao">Adoção</option>
                                <option <c:if test="${anuncio.tipo eq 'perdido'}"> selected="true" </c:if>value="perdido">Perdido</option>
                                </select>
                            </div>

                            <div class="form-group col-lg-6">
                                <label for="nome">Raça</label>
                                <input type="text" class="form-control" name="raca" id="nome" value = "${anuncio.raca}"/>
                        </div>

                        <div class="form-group col-lg-6">
                            <label for="especie">Espécie</label>

                            <select  class="form-control" id="especie" 
                                     name="especie">

                                <option value="">Selecione...</option>
                                <option <c:if test="${especie.nome eq 'Cão'}"> selected="true" </c:if>value="Cão">Cão</option>
                                <option <c:if test="${especie.nome eq 'Gato'}"> selected="true" </c:if>value="Gato">Gato</option>

                                </select>
                            </div>

                            </br>
                            </br>


                            <div class="form-group col-lg-6">
                                <label for="sexo">Sexo</label>

                                <select class="form-control" id="sexo" 
                                        name="sexo">

                                    <option value="">Selecione...</option>
                                    <option <c:if test="${anuncio.sexo eq 'm'}"> selected="true" </c:if>value="Macho">Macho</option>
                                <option <c:if test="${anuncio.sexo eq 'f'}"> selected="true" </c:if>value="Fêmea">Fêmea</option>
                                </select>
                            </div>  



                            <div class="form-group col-lg-6">
                                <label for="porte">Porte</label>

                                <select class="form-control" id="porte" 
                                        name="porte">

                                    <option value="">Selecione...</option>
                                    <option <c:if test="${anuncio.porte eq 'Pequeno'}"> selected="true" </c:if>value="Pequeno">Pequeno</option>
                                <option <c:if test="${anuncio.porte eq 'Medio'}"> selected="true" </c:if>value="Medio">Médio</option>
                                <option <c:if test="${anuncio.porte eq 'Grande'}"> selected="true" </c:if>value="Grande">Grande</option>
                                </select>
                            </div> 



                            <div class="form-group col-lg-6">
                                <label for="idade">idade</label>
                                <input type="text" class="form-control" name="idade" id="idade" value = "${anuncio.idade}"/>
                        </div>

                        </br>
                        </br>


                        <div class="form-group col-lg-6">
                            <label for="caracteristica">Caracteristicas</label>

                            <textarea class="form-control" rows="3" name="caracteristica" id="caracteristica" value = "${anuncio.caracteristicas}">${anuncio.caracteristicas}</textarea>
                        </div>

                        <div class="form-group col-lg-6">
                            <label for="file">Foto</label>
                            <input type="file"  class="form-control" name="foto" id="foto"/>
                            <p class="help-block">Realizar upload de um arquivo jpeg.</p>
                        </div>

                        </br>
                        </br>
                    </div>
                    </br>
                    </br>
                    <div id="formPerdido" style="display: none">

                        <div class="col - xs - 5">
                            <label for="recompensa">Recompensa</label>
                            <input type="text" class="form - control" name="recompensa" id="recompensa" value = "${anuncio.recompensa}" />
                        </div>
                        <div class="col - xs - 5">
                            <label for="local">Local</label>
                            <input type="text" class="form - control" name="local" id="local" value = "${anuncio.local}" />
                        </div>
                    </div>
                    <button id="buttonConfirmaAnuncio" type="submit" class="btn btn-info">Confirmar</button>    
                    <a class="btn btn-default" href="<c:url value="/anuncio"/>">Cancelar</a>

                </div>
        </div>
        </div>



        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="<c:url value="/resources/js/jquery.js"/>"></script>
        <script type="text/javascript">
            $("#tipo").change(function () {
                console.log("ativou funcao");
                var selectBox = document.getElementById("tipo");
                var tipo = selectBox.options[selectBox.selectedIndex].value;
                console.log(tipo);
                var formDiv = document.getElementById("formPerdido");
                if (tipo === "perdido") {
                    formDiv.style.display = 'block';
                } else {
                    formDiv.style.display = 'none';
                }
            });

            $("#buttonConfirmaAnuncio").click(function () {
                var formDiv = document.getElementById("formPerdido");
                formDiv.style.display = 'block';
            });

            //Exibe formulario com dados extras caso seja update
            var selectBox = document.getElementById("tipo");
            var tipo = selectBox.options[selectBox.selectedIndex].value;
            console.log(tipo);
            var formDiv = document.getElementById("formPerdido");
            if (tipo === "perdido") {
                formDiv.style.display = 'block';
            } else {
                formDiv.style.display = 'none';
            }

        </script>

        <jsp:include page = "/WEB-INF/views/componentes/footer.jsp" />

        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    </body>

</html>


