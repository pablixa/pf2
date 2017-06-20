


<html>

    <%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <head>
        <meta charset="utf-8">
        <title>SAAPA</title>
        <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/usuarios.css">
        <script type="text/javascript" src="js/mascaras.js"></script>
        <script type="text/javascript" src="lib/jquery/jquery.min.js"></script>
        <script type="text/javascript" src="lib/bootstrap/js/bootstrap.min.js"></script>
        <script src="lib/jquery/external/jquery/jquery.js"></script>
        <script src="lib/jquery/jquery.min.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">


        <script src="https://code.jquery.com/jquery-2.1.4.js"></script>

        <script src="http://code.jquery.com/jquery-latest.js">
        </script>
        <script>
            $(document).ready(function () {
                $("#cep").mask("99.999-999");
                $("#telefone").mask("(99)9999-9999");
                $("#cpf").mask("999.999.999-99");
            });
        </script>
        <script src="lib/jquery/jquery.maskedinput.js">

        </script>


    </head>


    <body>

        <%@include file="header.html" %>




        <section>




            <div id="painel" class="container">
                <div class="alert alert-success" id="teste" role="alert" hidden="true">



                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" style="text-align: center;"><strong>Incluir Treino</strong></div>
                    <div class="panel-body">


                        <form method="POST" action="ExercicioServlet"  >

                            <div class="container">


                                <jsp:useBean id="ec" class="Controler.ExercicioCtrl"/>




                                <c:forEach items="${ec.pesquisado(requestScope.id)}" var="exercicio">

                                    <div class="col-sm106">
                                        <div class="col-sm-3">

                                            <div class="row">
                                                <div class="panel panel-default">
                                                    <div class="panel-heading" style="text-align: center;">Dados do exercício</div>
                                                    <div class="panel-body">
                                                        <input type="hidden"class="form-control" id="id" name="id" value="${exercicio.getId()}">
                                                        <label>Nome<input class="form-control" id="nome" name="nome" value="${exercicio.getNome()}"></label>
                                                        <label>Tipo<input class="form-control" id="tipo" name="tipo"value="${exercicio.getTipo()}"></label>

                                                        <div class="panel panel-default" style="border-radius: 0px;">
                                                            <label>Cod.imagem<input class="form-control small" id="tipo" name="tipo">
                                                                <input  class="form-control "id="planiha" type="file" name="imagem" accept=".jpg"><br>
                                                            </label>
                                                        </div>

                                                        <input  class="btn btn-group"type="submit" value="Cadastrar">

                                                    </div>
                                                </div>
                                            </div> 

                                        </div>


                                    </div>

                                </c:forEach>


                            </div>




                        </form>


                    </div>
                </div>






            </div>
        </div>
        <%@include file="footer.html" %>
    </section>






    <script type="text/javascript" src="lib/bootstrap/js/bootstrap.min.js"></script>

</body>

</html>