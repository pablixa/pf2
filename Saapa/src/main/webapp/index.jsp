

<html><%@include file="valida.jsp" %>

    <head>
        <meta charset="utf-8">
        <title>Login - SIS CENTRAL REL</title>
        <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/usuarios.css">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, shrink-to-fit=no, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Simple Sidebar - Start Bootstrap Template</title>

        <!-- Bootstrap Core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="css/simple-sidebar.css" rel="stylesheet">
    </head>

    <%@include file="header.html" %>
    <body>

        <% if (perfil.contains("Aluno")) {
        %><%@include file= 'barra_gerente.jsp' %> 

        <% }%>  <% if (perfil.contains("administrador")) {

        %><%@include file= 'barra_administrador.jsp' %> 
        <% }%>  <% if (perfil.contains("atendente")) {

        %><%@include file= 'barra_atendente.jsp' %> 
        <% }%>

   
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->

        <!-- /#page-content-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Menu Toggle Script -->


</body>
<% }%>
</html>