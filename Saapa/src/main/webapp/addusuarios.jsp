


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
                    <div class="panel-heading" style="text-align: center;"><strong>ADICIONAR USUÁRIO</strong></div>
                    <div class="panel-body">


                        <form method="POST" action="UsuarioServlet">

                            <div class="form-group">
                                <label for="nome">Tipo</label>

                                <select class=" btn btn-primary dropdown-toggle " name="tipo"id="tipo">
                                    <option  value="aluno">Aluno</option>
                                    <option  value="professor">Professor</option>
                                    <option  value="estagiario">Estagiário</option>
                                </select>
                            </div>  <div  id="divcref" hidden="true" class="form-group">
                                <label for="nome">CREF:</label>
                                <input   type="text" id="cref" name="cref" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="nome">Nome</label>

                                <input required="true" type="text" name="nome" id="nome" class="form-control" placeholder="Nome do Usuario">

                            </div>

                            <div class="form-group">
                                <label for="cpf">CPF</label>
                                <input type="text" required="true" name="cpf" id="cpf" class="form-control small" placeholder="696.569.456-56...">
                            </div>

                            <div class="form-group">
                                <label for="senha">Senha</label>
                                <input type="text" required="true" name="senha" id="senha" class="form-control" placeholder="Senha">
                            </div>    
                            <div class="form-group">
                                <label for="telefone">Telefone</label>
                                <input type="text" required="true" name="telefone" id="telefone" class="form-control " placeholder="(21)2244-2222...">
                            </div>
                            <div class="form-group">
                                <label for="email">E-mail</label>
                                <input type="email" required="true" name="email" id="email" class="form-control" placeholder="joao@live.com...">
                            </div>
                            <div class="form-group">
                                <label for="senha">Data de Nascimento</label>
                                <div  id="divdate" >Data:
                                    <input  class="form-control" required="true" type="text" name="data" id="data" maxlength="10" onkeypress="mascaraData(this)" />
                                </div>
                            </div><div class="form-group">
                                <label for="sexo" class="active">Sexo</label>

                                <select class=" btn btn-primary dropdown-toggle " name="sexo" id="sexo">
                                    <option value="m" >Masculino</option>
                                    <option value="f">Feminino</option>
                                </select></div> </div>


                    <br /><br /><br />
                    <h1>Endereço</h1>
                    <jsp:useBean id="cc" class="Controler.CidadeCtrl"/>
                    <div class="form-group">
                        <select required="true" class="  btn btn-primary dropdown-toggle  "name="uf" id="dropdown1">
                            <c:forEach items="${cc.getUFs()}" var="consulta"> 
                                <option value="${consulta.toString()}">${consulta.toString()}</option>
                            </c:forEach></select>


                        <select required="true" class=" btn btn-primary dropdown-toggle " name="cidade" id="dropdown2">
                            <option>Município</option>
                        </select></div>


                    <div class="form-group">
                        <label for="nome">Logadouro</label>
                        <input required="true" type="text" name="logradouro" id="logradouro" class="form-control" placeholder="rua João Pereira...">
                    </div>


                    <div class="form-group">
                        <label for="nome">CEP</label>
                        <input required="true" type="text" name="cep" id="cep" class="form-control" placeholder="Digite o CEP">
                    </div>

                    <div class="form-group">
                        <label for="nome">Complemento</label>
                        <input required="true" type="text" name="complemento" id="complemento" class="form-control" placeholder="Digite o complemento">
                    </div>


                    <button type="submit" id="submit" class="btn btn-primary">Registrar</button>


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