


<html><%@include file="valida.jsp" %>

    <head>
        <meta charset="utf-8">
        <title>SAAPA</title>
        <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/usuarios.css">
    </head>


    <body>



        <%@include file="header.html" %>

        <section>
    

            <div class="container">



                <div class="erro">
                    <h2>ERRO</h2>
                    <p>A operação não pode ser concluida! Caso o erro persista entre em contato com o suporte.</p>
                    <p><% out.print(request.getAttribute("erro"));%></p>
                </div>   


            </div><%@include file="footer.html" %>

        </section>




        <script type="text/javascript" src="lib/jquery/jquery.min.js"></script>
        <script type="text/javascript" src="lib/bootstrap/js/bootstrap.min.js"></script>

    </body>

</html><%}%>