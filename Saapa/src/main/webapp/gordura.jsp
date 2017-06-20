


<%@page import="Controler.UsuarioCtrl"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page import="Model.Usuario"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <%@include file="valida.jsp" %>
    <head>
        <meta charset="utf-8">
        <title>SAAPA</title>
        <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/usuarios.css">

        <script src="lib/jquery/jquery.min.js"></script>

        <script src="https://code.jquery.com/jquery-2.1.4.js" ></script>

        <script type="text/javascript" src="lib/jquery/jquery.min.js"></script>
        <script type="text/javascript" src="lib/bootstrap/js/bootstrap.min.js">



        </script>
        <script>
//            $(document).ready(function () {
//                var matricula = $('#matricula').val();
//                $('#button').click(function (even ))
//                $.get('UsuarioSrv', {matricula: matricula}, function (responseText) {
//                    $('#erro').text(responseText).show();
//
//                });
//
//            });
        </script>


    </head>



    <body>
        <header >  <%@include file="header.html" %></header>
        <div class="panel panel-default">
            <div class="panel-heading">
                <jsp:useBean id="uc" class="Controler.UsuarioCtrl"/>
                <jsp:useBean id="ac" class="Controler.AvaliacaoCtrl"/>
                <jsp:useBean id="gc" class="Controler.GraficoCtrl"/>
                <c:forEach items="${uc.pesquisado(param.matricula,'Aluno')}" var="usuario">






                    <h3 class="panel-title"> <label for="nome">${usuario.getNome()}</label></h3>
                </div>
                <div class="panel-body">

                    <img src="images/graficos/gordura/<c:out value='${usuario.getMatricula()}'/>gordura.png" width="700" height="600" alt="grafico"/></c:forEach>  


                    <script>$(document).ready(function () {
                            var cl = $('#cls').val();
                            if (cl == "Normal") {
                                $('#Excelente').attr({class: "alert alert-success"});
                            } else
                            if (cl == "Bom") {
                                $('#mensagem').attr({class: "alert alert-warning"});
                            }if (cl == "Acima da Média") {
                                $('#mensagem').attr({class: "alert alert-warning"});
                            } else {
                                $('#mensagem').attr({class: " alert alert-danger"});
                            }
                        });</script> 
                    <div id="mensagem" class="label alert alert-warning ">
                        <input type="hidden"  id="cls"name="cls" value="${ac.classificaGordura(param.matricula)}">
                    Risco de complicação metabólica: <span id="span" class="active ">${ac.classificaGordura(param.matricula)}   </span>
                </div>
            </div>

    </body> 

</html><%}%>