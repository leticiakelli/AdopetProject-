
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
        <div class="container"  encype="multipart/form-data">
            <div class="jumbotron" left="30px" top="20px" rigth="30px">

                <div class="row">
                    <div class="form-group col-lg-6  rounded float-right">
                        <img src="data:image/jpeg;base64,${imageBase64}" class="rounded float-right"  style="height:200px;width:300px;"/>
                       
                        <br>
                        <br>
                        <div>
                        <c:if test="${anuncio.status == 'adotado'}"> 
                            <a class="btn btn-info" href="<c:url value="/home"/>">Sair</a>
                        </c:if>
                        <c:if test="${anuncio.status == 'pendente'}"> 
                            <a href="<c:url value="/anuncio/${anuncio.id}/read/status"/>" type="submit" class="btn btn-info">Adotar</a>    
                            <a class="btn btn-default" href="<c:url value="/anuncio"/>">Cancelar</a>
                        </c:if></div>
                    </div>
                    <div class="form-group col-lg-6">
                        <input type="hidden" value="${anuncio.id}" name="anuncioId"/>


                        <div>
                            <h2 style="color:#000">${anuncio.tipo}</h2>
                        </div>
                        <div >
                            <h3 style="color:#000">RAÇA: ${anuncio.raca}</h3>
                        </div>

                        <div >
                            <h3 style="color:#000">ESPÉCIE: ${especie.nome}</h3>
                        </div>
                        <div >
                            <h3 style="color:#000">SEXO: ${anuncio.sexo}</h3>
                        </div>  
                        <div>
                            <h3 style="color:#000">PORTE: ${anuncio.porte}</h3>
                        </div> 
                        <div >
                            <h3 style="color:#000">IDADE: ${anuncio.idade}</h3>
                            <div>
                                <h3 style="color:#000">CARACTERISTICAS: ${anuncio.caracteristicas}</h3>
                            </div>
                        </div>
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



                    <c:if test="${not empty usuarioLogado}">
                        <div class="container">
                            <h3 style="color:#000">Envie suas duvidas!</h3>
                            <form method="post">
                                <div class="container"> 
                                    <div class="form-group">
                                        <label for="comment">Dúvida:</label>
                                        <textarea class="form-control " rows="5" name="texto" ></textarea>
                                    </div>
                                    <div>
                                        <button type="submit" class="btn btn-success">Enviar</button>
                                    </div>
                                </div>

                            </form>
                        </c:if>
                        <c:forEach items="${timelineList}" var="timeline" varStatus="loop">
                            <h4 style="color:#000">${pessoaTimeline[loop.index].nome}</h4>
                            <textarea class="form-control" rows="5" name="resposta" disabled >${timeline.texto}</textarea>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>


        <jsp:include page = "/WEB-INF/views/componentes/footer.jsp" />
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


        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    </body>

</html>


