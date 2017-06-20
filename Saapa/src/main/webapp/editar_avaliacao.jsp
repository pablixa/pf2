


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

                                <jsp:useBean id="ac" class="Controler.AvaliacaoCtrl"/>




                                <c:forEach items="${ac.pesquisado(requestScope.id,requestScope.data)}" var="avaliacao">

                                    <input type="hidden" class="form-control" id="matricla" name="matricula" value="${requestScope.id}">

                                    <div class="col-sm106">
                                        <div class="col-sm-3">

                                            <div class="row">
                                                <div class="panel panel-default">
                                                    <div class="panel-heading" style="text-align: center;">medidas Cutaneas</div>
                                                    <div class="panel-body">
                                                        <label>Toráx:<input required="true" type="number" class="form-control" id="torax" name="torax" value="${avaliacao.getTorax()}"></label>cm
                                                        <label>Coxa<input required="true" type="number" class="form-control" id="coxa" name="coxa"value="${avaliacao.getCoxa()}"></label>cm
                                                        <label>Subescapular<input required="true" type="number" class="form-control" id="subescapular" name="subescapular"value="${avaliacao.getSubescapular()}"></label>cm
                                                        <label>Abdominal<input required="true" type="number" class="form-control" id="abdominal" name="abdominal"value="${avaliacao.getAbdominal()}"></label>cm
                                                        <label>Supra-ílica<input required="true" type="number" class="form-control" id="suprailiaca" name="suprailiaca"value="${avaliacao.getSuprailiaca()}"></label>cm
                                                        <label>Tríceps<input required="true" type="number" class="form-control" id="triceps" name="triceps"value="${avaliacao.getTriceps()}"></label>cm
                                                        <label>Sub-axilar<input required="true" type="number" class="form-control" id="sub-axilar" name="sub-axilar"value="${avaliacao.getSubaxilar()}" ></label>cm

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
                                                            <label>Peso(Kg)<input class="form-control" id="peso" required="true" type="number" name="peso" value="${avaliacao.getPeso()}"></label>
                                                            <label>Altua(cm)<input class="form-control" required="true" type="number" id="altura" name="altura"value="${avaliacao.getAltura()}"></label>
                                                            <label>Cintura(cm)<input class="form-control" id="cintura" name="cintura"required="true" type="number" value="${avaliacao.getCintura()}"></label>
                                                            <label>Quadril(cm)<input class="form-control" id="quadril" required="true" type="number"name="quadril"value="${avaliacao.getQuadril()}"></label>


                                                        </fieldset>

                                                    </div>
                                                    <div class="col-sm-5">
                                                        <fieldset class="scheduler-borders  "style="text-align: center;">
                                                            <legend class="scheduler-border">Meta</legend>
                                                            <label>P<input class="form-control" required="true" type="number"id="meta_p" name="meta_p" value="${avaliacao.getMetaP()}"></label>
                                                            <label>Q<input class="form-control" id="meta_q" name="meta_q"required="true" type="number" value="${avaliacao.getMetaQ()}"></label>
                                                            <label>C<input class="form-control" id="meta_c" name="meta_c"required="true" type="number" value="${avaliacao.getMetaC()}"></label>

                                                        </fieldset>

                                                    </div>
                                                </div>


                                            </div> 
                                        </div>

                                    </div>

                                    <div class="row">
                                        <div class="col-md-10">  
                                            <fieldset class="scheduler-borders  ">

                                                <label class="scheduler-borders">Matricula do Aluno?<input readonly="true" class="form-control" id="matriula" name="matricula"value="${avaliacao.getAluno().getMatricula()}"></label>


                                                <jsp:useBean id="cc" class="Controler.ProfessorCtrl"/>

                                                <label for="profesor">Professor:
                                                    <select required="true" class="  btn btn-primary dropdown-toggle  "name="cref" id="cref">
                                                        <c:forEach items="${cc.listarCrefs()}" var="consulta"> 
                                                            <option value="${consulta.getCref()}">${consulta.getNome()}</option>
                                                        </c:forEach></select></label>

                                                <button type="submit" id="submit" class="btn btn-primary">Editar</button>
                                            </fieldset>
                                        </div>


                                    </div>
                                </div>





                            </form>
                        </c:forEach>

                    </div>
                </div>






            </div>
        </div>
        <%@include file="footer.html" %>
    </section>






    <script type="text/javascript" src="lib/bootstrap/js/bootstrap.min.js"></script>

</body>

</html>