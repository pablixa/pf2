/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controler.TreinoCtrl;
import Model.Aluno;
import Model.Exercicio;
import Model.Professor;
import Model.Treino;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 
 */
public class TreinoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TreinoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TreinoServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TreinoCtrl tc = new TreinoCtrl();
        Treino treino = new Treino();
        Exercicio exercicio = new Exercicio();
        exercicio.setId(Integer.parseInt(request.getParameter("exercicio")));
        treino.setExercicio(exercicio);
        treino.setCarga(Integer.parseInt(request.getParameter("carga")));
        treino.setRepeticao(Integer.parseInt(request.getParameter("repeticao")));
        treino.setDescanso(Integer.parseInt(request.getParameter("descanso")));
        treino.setTipo(request.getParameter("tipo"));
        Aluno aluno = new Aluno();
        aluno.setMatricula(Integer.parseInt(request.getParameter("matricula")));
        treino.setAluno(aluno);
        Professor professor = new Professor();
        professor.setCref(Integer.parseInt(request.getParameter("cref")));
        Date date = new Date();
        System.out.println("Servlet.TreinoServlet.doPost()" + request.getParameter("renovacao"));
        System.out.println("Servlet.TreinoServlet.doPost()" + request.getParameter("renovacao"));
//        try {
//            Date renovacao = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("renovacao"));

        Date renovacao = new Date(request.getParameter("renovacao"));

        treino.setDataRenovacao(renovacao);
//        } catch (ParseException ex) {
//            Logger.getLogger(TreinoServlet.class.getName()).log(Level.SEVERE, null, ex);
////
//        }
        treino.setDataAtual(date);
        if (request.getParameter("id") == null) {

            if (tc.adicionar(treino)) {
                response.sendRedirect("index.jsp");
            } else {
                response.sendRedirect("erro.jsp");
            }
        } else {
            treino.setTreinoId(Integer.parseInt(request.getParameter("id")));
            if (tc.alterar(treino)) {
                response.sendRedirect("index.jsp");
            } else {
                response.sendRedirect("erro.jsp");
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
