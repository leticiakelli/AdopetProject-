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

        <div class="container">
            <c:if test="${empty anuncio}">
                <h1>Novo Cadastro</h1>
            </c:if>

            <c:if test="${not empty anuncio}">
                <h1>Editar Cadastro</h1>
            </c:if>
            <form method="post" enctype="multipart/form-data">
                <div class="jumbotron" left="30px" top="20px" rigth="30px">

                    <div class="row">
                        <input type="hidden" value="${usuario.id}" name="usuarioId"/>
                        <input type="hidden" value="${pessoa.id}" name="pessoaId"/>
                        <div class="col-xs-5">
                            <label for="email">Email</label>
                            <input type="email" class="form-control" name="email" id="email" value = "${usuario.email}"/>
                        </div>
                        <div class="col-xs-5">
                            <label for="senha">Senha</label>
                            <input type="password" class="form-control" name="senha" id="senha" value = "${usuario.senha}"/>
                        </div>

                        <div class="col-xs-5">
                            <label for="nome">Nome</label>
                            <input type="text" class="form-control" name="nome" id="nome" value = "${pessoa.nome}"/>
                        </div>


                        <div class="col-xs-5">
                            <label for="cpf">CPF</label>
                            <input type="text" class="form-control" name="cpf" id="cpf" value = "${pessoa.cpf}"/>
                        </div>


                        <div class="col-xs-5">
                            <label for="logradouro">Logradouro</label>
                            <input type="text" class="form-control" name="logradouro" id="logradouro" value = "${pessoa.logradouro}"/>
                        </div>

                        <div class="col-xs-5">
                            <label for="numero">Numero</label>
                            <input type="number" class="form-control" name="numero" id="numero" value = "${pessoa.numero}"/>
                        </div>

                        <div class="col-xs-5">
                            <label for="complemento">Complemento</label>
                            <input type="text" class="form-control" name="complemento" id="complemento" value = "${pessoa.complemento}"/>
                        </div>

                        <div class="col-xs-5">
                            <label for="cidade">Cidade</label>
                            <input type="text" class="form-control" name="cidade" id="cidade" value = "${pessoa.cidade}"/>
                        </div>

                        <div class="col-xs-5">
                            <label for="bairro">Bairro</label>
                            <input type="text" class="form-control" name="bairro" id="bairro" value = "${pessoa.bairro}"/>
                        </div>

                        <div class="col-xs-5">
                            <label for="uf">UF</label>
                            <input type="text" class="form-control" name="uf" id="uf" value = "${pessoa.estado}"/>
                        </div>

                        <div class="col-xs-5">
                            <label for="telefone">Telefone</label>
                            <input type="text" class="form-control" name="telefone" id="telefone" value = "${telefone.telefone}"/>
                        </div>

                        <div class="col-xs-5">
                            <label for="celular">Celular</label>
                            <input type="text" class="form-control" name="celular" id="celular" value = "${telefone.celular}"/>
                        </div>

                        <div class="col-xs-5">
                            <label for="file">Foto</label>
                            <input type="file"  class="form-control" name="foto" id="foto" />
                            <p class="help-block">Realizar upload de um arquivo jpeg.</p>
                        </div>


                        <div class="col-xs-3">
                            <br>
                            <button type="submit" class="btn btn-info">Confirmar Cadastro</button>
                        </div>

                      
                        <div class="col-xs-3 ">
                            <br>
                            <a class="btn btn-default" href="<c:url value="/home"/>">Cancelar Cadastro</a>
                        </div>
                    </div>
                </div>     
            </form>

            <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
            <script src="<c:url value="/resources/js/jquery.js"/>"</script>
            <!-- Include all compiled plugins (below), or include individual files as needed -->
            <script src="<c:url value="/resources/js/bootstrap.min.js"/>"</script>
    </body>

</html>


