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
        
            
            
                
        
       <div class="container">
           <c:if test="${empty anuncio}">
                <h1>Novo Anuncio</h1>
            </c:if>

            <c:if test="${not empty anuncio}">
                <h1>Editar anuncio</h1>
            </c:if>
                <form method="post">
               <div class="jumbotron" left="30px" top="20px" rigth="30px">

                     <div class="row">
                         
                            <div class="col-xs-5">
                                <label for="tipo">Tipo Anuncio</label>

                                <select class="form-control" id="tipo" 
                                        name="tipo">

                                    <option value="">Selecione...</option>
                                    <option <c:if test="${anuncio.tipo eq 'Adoção'}">selected</c:if>value="Adoção">Adoção</option>
                                    <option <c:if test="${anuncio.tipo eq 'Perdido'}">selected</c:if>value="Perdido">Perdido</option>
                                    </select>
                                </div>
                           
                            
                                <div class="col-xs-5">
                                    <label for="nome">Raça</label>
                                    <input type="text" class="form-control" name="nome" id="nome" value = "${anuncio.nome}"/>
                                 </div>
                       
                        
                        
                       
                            <div class="col-xs-5">
                                <label for="especie">Espécie</label>

                                <select class="form-control" id="especie" 
                                        name="especie">

                                    <option value="">Selecione...</option>
                                    <option <c:if test="${anuncio.especie eq 'Cão'}">selected</c:if>value="Cão">Cão</option>
                                    <option <c:if test="${anuncio.especie eq 'Gato'}">selected</c:if>value="Gato">Gato</option>

                                    </select>
                                </div>
                            
                            </br>
                            </br>
                            
                               
                                <div class="col-xs-5">
                                    <label for="sexo">Sexo</label>

                                    <select class="form-control" id="sexo" 
                                            name="sexo">

                                        <option value="">Selecione...</option>
                                        <option <c:if test="${anuncio.sexo eq 'Macho'}">selected</c:if>value="Macho">Macho</option>
                                    <option <c:if test="${anuncio.sexo eq 'Fêmea'}">selected</c:if>value="Fêmea">Fêmea</option>
                                    </select>
                                </div>  
                            
                            
                            
                                <div class="col-xs-5">
                                    <label for="porte">Porte</label>

                                    <select class="form-control" id="porte" 
                                            name="porte">
                                        
                                        <option value="">Selecione...</option>
                                        <option <c:if test="${anuncio.porte eq 'Pequeno'}">selected</c:if>value="Pequeno">Pequeno</option>
                                    <option <c:if test="${anuncio.porte eq 'Medio'}">selected</c:if>value="Medio">Médio</option>
                                    <option <c:if test="${anuncio.porte eq 'Grande'}">selected</c:if>value="Grande">Grande</option>
                                    </select>
                                </div> 
                            
                            
                             
                                <div class="col-xs-5">
                                    <label for="idade">idade</label>
                                    <input type="text" class="form-control" name="idade" id="idade" value = "${anuncio.idade}"/>
                            </div>
                        
                        </br>
                        </br>
                        
                        
                            <div class="col-xs-5">
                                <label for="caracteristica">Caracteristicas</label>

                                <textarea class="form-control" rows="3" name="caracteristica" id="caracteristica" value = "${anuncio.caracteristica}"></textarea>
                            </div>
                            
                            <div class="col-xs-5">
                                <label for="caracteristica">Foto</label>

                                <textarea class="form-control" rows="3" name="caracteristica" id="caracteristica" value = "${anuncio.caracteristica}"></textarea>
                            </div>
                        
                        </br>
                        </br>
                    </div>
                            </br>
                        </br>
                        <button type="submit" class="btn btn-info">Confirmar</button>    
                        <a class="btn btn-default" href="<c:url value="/anuncio"/>">Cancelar</a>
                 
               </div>
                 </div>
       
                      

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="<c:url value="/resources/js/jquery.js"/>"</script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="<c:url value="/resources/js/bootstrap.min.js"/>"</script>
    </body>

</html>


