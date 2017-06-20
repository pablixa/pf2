


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
                    $.getJSON('AvaliacaoServlet', {value: selectedValue, pesquisa: pesquisa}, function (options) {
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


                        <form method="POST" action="AvaliacaoServlet">

                            <div class="container">




                                <div class="col-sm106">
                                    <div class="col-sm-3">

                                        <div class="row">
                                            <div class="panel panel-default">
                                                <div class="panel-heading" style="text-align: center;">medidas Cutaneas</div>
                                                <div class="panel-body">
                                                    <label>Toráx:<input type="number"class="form-control" id="torax" name="torax"></label>
                                                    <label>Coxa<input  type="number" class="form-control" id="coxa" name="coxa"></label>
                                                    <label>Subescapular<input type="number" class="form-control" id="subescapular" name="subescapular"></label>
                                                    <label>Abdominal<input type="number" class="form-control" id="abdominal" name="abdominal"></label>
                                                    <label>Supra-ílica<input type="number" class="form-control" id="suprailiaca" name="suprailiaca"></label>
                                                    <label>Tríceps<input type="number" class="form-control" id="triceps" name="triceps"></label>
                                                    <label>Sub-axilar<input class="form-control" id="sub-axilar" name="sub-axilar"></label>

                                                </div>
                                            </div>
                                        </div> 

                                    </div>


                                </div>
                                <div class="col-sm-3">
                                    <div class="row">    

                                        <div class="panel panel-default">
                                            <div class="panel-heading" style="text-align: center;">Outras Medidas</div>
                                            <div class="panel-body">
                                                <div class="col-sm-5">
                                                    <fieldset class="scheduler-borders  ">
                                                        <legend class="scheduler-border">Atual</legend>
                                                        <label>Peso<input class="form-control" id="peso" type="number" name="peso"placeholder="KG "></label>
                                                        <label>Altua<input class="form-control" id="altura" type="number"name="altura" placeholder="Metros"></label>
                                                        <label>Cintura<input class="form-control" id="cintura" type="number"name="cintura"></label>
                                                        <label>Quadril<input class="form-control" id="quadril" type="number"name="quadril"></label>


                                                    </fieldset>

                                                </div>
                                                <div class="col-sm-5">
                                                    <fieldset class="scheduler-borders  "style="text-align: center;">
                                                        <legend class="scheduler-border">Meta</legend>
                                                        <label>P<input class="form-control" id="meta_p" name="meta_p"></label>
                                                        <label>Q<input class="form-control" id="meta_q" name="meta_q"></label>
                                                        <label>C<input class="form-control" id="meta_c" name="meta_c"></label>

                                                    </fieldset>

                                                </div>
                                            </div>


                                        </div> 
                                    </div>

                                </div>

                                <div class="row">
                                    <div class="col-md-10">  
                                        <fieldset class="scheduler-borders  ">

                                            <label class="scheduler-borders">Matricula do Aluno?<input class="form-control" id="matriula" name="matricula"></label>


                                            <jsp:useBean id="cc" class="Controler.ProfessorCtrl"/>

                                            <label for="profesor">Professor:
                                                <select required="true" class="  btn btn-primary dropdown-toggle  "name="cref" id="cref">
                                                    <c:forEach items="${cc.listarCrefs()}" var="consulta"> 
                                                        <option value="${consulta.getCref()}">${consulta.getNome()}</option>
                                                    </c:forEach></select></label>

                                            <button type="submit" id="submit" class="btn btn-primary">Registrar</button>
                                        </fieldset>
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