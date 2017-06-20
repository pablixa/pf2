/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function gerarImc(val) {
    $.get('GraficoServlet', {grafico: "imc", matricula: val}, function (options) {
        window.location.href = "imc.jsp?matricula=" + val;
    });
}

function gerarRcq(val) {
    $.get('GraficoServlet', {grafico: "rcq", matricula: val}, function (options) {
        window.location.href = "rcq.jsp?matricula=" + val;
    });
}
function gerarGordura(val) {
    $.get('GraficoServlet', {grafico: "gordura", matricula: val}, function (options) {
        window.location.href = "gordura.jsp?matricula=" + val;
    });
}
;

