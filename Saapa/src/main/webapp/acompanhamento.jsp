


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
        <script type="text/javascript" src="js/graficos.js"></script>
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
                <c:forEach items="${uc.pesquisado(requestScope.id,'Aluno')}" var="usuario">






                    <h3 class="panel-title"> <label for="nome"><c:out value="${usuario.getNome()}"/></label></h3>
                </div>
                <div class="panel-body">



                    <div class="btn-group btn-group-sm" role="group" aria-label="...">
                        <button class="  btn btn-default  " onclick="gerarImc(<c:out value='${usuario.getMatricula()}'/>)"> IMC</button>

                        <br>      
                        <div class="btn-group btn-group-sm" role="group" aria-label="..."> 
                            <button class="  btn btn-default  " onclick="gerarRcq(<c:out value='${usuario.getMatricula()}'/>)"> RCQ</button>

                        </div> <br>
                        <div class="btn-group btn-group-sm" role="group" aria-label="...">

                            <a class=" btn btn-default  "  onclick="gerarGordura(<c:out value='${usuario.getMatricula()}'/>)" chref="pesquisar_exercicio.jsp">%Gordura</a>
                        </div> <br>
                        <div class="btn-group btn-group-sm" role="group" aria-label="..."><a class="  btn btn-default  " href="addusuarios.jsp">Avaliação</a>  


                        </div> <br>
                        <div class="btn-group btn-group-sm" role="group" aria-label="..."><a class="  btn btn-default  " href="adicionar_treino.jsp">Metas</a>
                        </c:forEach>  
                    </div><br>
                </div>
            </div>
    </body> 

</html><%}%>