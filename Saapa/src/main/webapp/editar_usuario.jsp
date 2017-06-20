


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
        <link rel="stylesheet" href="/resources/demos/style.css">

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
//                if (param.tipo == "professor") {
//
//                    $('#cref').attr({required: true})
//                    $('#divcref').show();
//                    
//                }

                $('#dropdown1').change(function () {
                    var selectedValue = $(this).val();
                    var pesquisa = "uf";
                    $.getJSON('UsuarioServlet', {value: selectedValue, pesquisa: pesquisa}, function (options) {
                        var dropdown2 = $('#dropdown2');
                        $('>option', dropdown2).remove(); // Clean old options first.
                        if (options) {
                            $.each(options, function (key, value) {
                                dropdown2.append($('<option/>').val(key).text(value));
                            });
                        } else {
                            dropdown2.append($('<option/>').text("Please select dropdown1"));
                        }
                    });
                });
                $('#excluir').click(function () {
                    var id = $('#id').val();
                    console.log(id);
                    $.get("UsuarioServlet", {value: id, pesquisa: "excluir"}, function (options) {
                        $('#mensagem').show();
                        $('#mensagem').text("Excluido com sucesso");
                    });


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
                            <jsp:useBean id="uc" class="Controler.UsuarioCtrl"/>




                            <c:forEach items="${uc.pesquisado(requestScope.id,requestScope.tipo)}" var="usuario">


                                <div class="form-group">
                                    <div  id="divcref" hidden="true" class="form-group">
                                        <label for="nome">CREF:</label>
                                        <input   type="text" id="cref" name="cref" class="form-control">
                                    </div>
                                    <label for="nome">Nome</label>

                                    <input type="hidden" name="id" id="id" class="form-control" placeholder="Nome do Usuario" value="<c:out value="${usuario.getUsuarioId()}"/>">
                                    <input type="text" name="nome" id="nome" class="form-control" placeholder="Nome do Usuario" value="<c:out value="${usuario.getNome()}"/>">

                                </div>

                                <div class="form-group">
                                    <label for="cpf">CPF</label>
                                    <input type="text" name="cpf" id="cpf" class="form-control small" placeholder="696.569.456-56..." value="<c:out value="${usuario.getCpf()}"/>">
                                </div>

                                <div class="form-group">
                                    <label for="senha">Senha</label>
                                    <input type="text" name="senha" id="senha" class="form-control" value="<c:out value="${usuario.getSenha()}"/>">
                                </div>    
                                <div class="form-group">
                                    <label for="telefone">Telefone</label>
                                    <input type="text" name="telefone" id="telefone" class="form-control " placeholder="(21)2244-2222..." value="<c:out value="${usuario.getTelefone()}"/>">
                                </div>
                                <div class="form-group">
                                    <label for="email">E-mail</label>
                                    <input type="email" name="email" id="email" class="form-control" placeholder="joao@live.com..."value="<c:out value="${usuario.getEmail()}"/>">
                                </div>
                                <div class="form-group">
                                    <label for="senha">Data de Nascimento</label><c:out value='${usuario.getDtNascimento().getYear()}'/>
                                    <div  id="divdate" >Data:
                                        <input  class="form-control" type="text" name="data" id="data" maxlength="10" onkeypress="mascaraData(this)" value=" <c:out value='${usuario.getDtNascimento().getDate()}'/> / <c:out value='${usuario.getDtNascimento().getMonth()+1}'/>/<c:out value='${usuario.getDtNascimento().getYear()}'/>"/>
                                    </div>
                                </div><div class="form-group">

                                    <label for="sexo" class="active">Sexo</label>
                                    <select class=" btn btn-primary dropdown-toggle " name="sexo" id="sexo">
                                        <option value="m" >Masculino</option>
                                        <option value="f">Feminino</option>
                                    </select></div>
                        </div>


                        <br /><br /><br />
                        <h1>Endereço</h1>
                        <jsp:useBean id="cc" class="Controler.CidadeCtrl"/>
                        <div class="form-group">
                            <select class=" btn btn-primary dropdown-toggle  "name="uf" id="dropdown1">
                                <option value="<c:out value='${usuario.getEndereco().getCidade().getCodIBGE()}'/>"><c:out value='${usuario.getEndereco().getCidade().getUF()}'/></option

                                <c:forEach items="${cc.getUFs()}" var="consulta"> 
                                    <option value="<c:out value='${consulta.toString()}'/>">${consulta.toString()}</option>
                                </c:forEach></select>


                            <select class=" btn btn-primary dropdown-toggle " name="cidade" id="dropdown2">
                                <option value="<c:out value='${usuario.getEndereco().getCidade().getCodIBGE()}'/>"><c:out value="${usuario.getEndereco().getCidade().getNome()}"/></option>
                            </select></div>


                        <div class="form-group">
                            <label for="nome">Logadouro</label>
                            <input type="text" name="logradouro" id="logradouro" class="form-control" placeholder="rua João Pereira..." value="<c:out value="${usuario.getEndereco().getLogradouro()}"/>">
                        </div>



                        <div class="form-group">
                            <label for="nome">CEP</label>
                            <input type="text" name="cep" id="cep" class="form-control" placeholder="Digite o CEP"value="<c:out value="${usuario.getEndereco().getCep()}"/>">
                        </div>

                        <div class="form-group">
                            <label for="nome">Complemento</label>
                            <input type="text" name="complemento" id="complemento" class="form-control" placeholder="Digite o complemento"value="<c:out value="${usuario.getEndereco().getComplemento()}"/>">
                        </div>


                        <button type="submit" id="submit" class="btn btn-primary">Registrar</button>
                        <button type="button" id="excluir" class="btn btn-danger">Excluir</button>
                        <div hidden="true"id="mensagem" class="label alert alert-warning ">
                            <label id="texto" class="label-danger"></label>

                        </div>
                    </c:forEach>
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