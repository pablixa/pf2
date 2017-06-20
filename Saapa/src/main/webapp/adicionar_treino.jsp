


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
        <script>$(document).ready(function () {



                $('#dropdown1').change(function () {
                    var selectedValue = $(this).val();
                    console.log(selectedValue);
                    var pesquisa = "uf";
                    $.getJSON('UsuarioServlet', {value: selectedValue, pesquisa: pesquisa}, function (options) {
                        var dropdown2 = $('#dropdown2');
                        $('>option', dropdown2).remove(); // Clean old options first.
                        if (options) {
                            console.log(options)
                            $.each(options, function (key, value) {
                                dropdown2.append($('<option/>').val(key).text(value));
                            });
                        } else {
                            dropdown2.append($('<option/>').text("Please select dropdown1"));
                        }
                    });
                });


                $('#tipo').click(function () {
                    console.log($('#tipo').val())
                    if ($('#tipo').val() == "professor") {
                        $('#cref').attr({required: true})
                        $('#divcref').show();

                    } else {
                        $('#divcref').hide();
                        $('#cref').attr({required: false})
                    }

                });
            });
        </script>


    </head>


    <body>

        <%@include file="header.html" %>




        <section>




            <div id="painel" class="container">
                <div class="alert alert-success" id="teste" role="alert" hidden="true">



                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" style="text-align: center;"><strong>Avaliação</strong></div>
                    <div class="panel-body">


                        <form method="POST" action="TreinoServlet">

                            <div class="container">

                                <jsp:useBean id="ec" class="Controler.ExercicioCtrl"/>
                                <label for="exercicio">Exercício:
                                    <select required="true" class="  btn btn-primary dropdown-toggle  "name="exercicio" id="exercicio">
                                        <c:forEach items="${ec.listar()}" var="consulta"> 
                                            <option value="${consulta.getId()}">${consulta.getNome()}</option>
                                        </c:forEach></select></label>

                                <div class="row">
                                    <div class="col-md-3">



                                        <div class="form-group">
                                            <label>Carga<input  type="number" required="true" class="form-control" id="carga" name="carga"></label>Kg

                                            <label>Repetição<input type="number" required="true" class="form-control" id="repeticao" name="repeticao"></label>X
                                            <label>Descanso<input  type="number" required="true"class="form-control" id="descanso" name="descanso"></label>Segundos
                                            <label>Tipo<input type="text" required="true" class="form-control" id="tipo" name="tipo"></label>




                                        </div>
                                    </div>
                                </div>


                                <div class="row">
                                    <div class="col-sm-3">  

                                        <div class="form-group">
                                            Matricula do Aluno:<input class="form-control" id="matriula" name="matricula"></label>

                                        </div>

                                        <jsp:useBean id="cc" class="Controler.ProfessorCtrl"/>
                                        <div class="form-group">
                                            <label for="profesor">Professor:
                                                <select required="true" class="  form-control btn btn-primary dropdown-toggle  "name="cref" id="cref">
                                                    <c:forEach items="${cc.listarCrefs()}" var="consulta"> 
                                                        <option value="${consulta.getCref()}">${consulta.getNome()}</option>
                                                    </c:forEach></select></label>
                                        </div>
                                        <div class="form-group">                        
                                            <label >Data de Renovação:<input class="form-control"type="datetime" class="form-control " id="renovacao" name="renovacao"></label>
                                        </div>
                                        <button type="submit" id="submit" class="btn btn-primary">Registrar</button>

                                    </div>


                                </div>
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