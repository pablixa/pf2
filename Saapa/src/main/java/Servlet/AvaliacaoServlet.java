/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controler.AvaliacaoCtrl;
import Controler.ProfessorCtrl;
import Model.Aluno;
import Model.Avaliacao;
import Model.Professor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * 
 */
@WebServlet(name = "AvaliacaoServlet", urlPatterns = {"/AvaliacaoServlet"})
public class AvaliacaoServlet extends HttpServlet {

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
        AvaliacaoCtrl ac = new AvaliacaoCtrl();
        
        Date date;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("data_atual"));
            
            if (ac.existe(request.getParameter("matricula"), date)) {
                
                System.err.println("matricula");
                request.setAttribute("id", request.getParameter("matricula"));
                request.setAttribute("data", request.getParameter("data_atual"));
                RequestDispatcher dispatcher = request.getRequestDispatcher("editar_avaliacao.jsp");
                dispatcher.forward(request, response);
                
            } else {
                
                request.setAttribute("erro", "Aluno não cadastrado");
                RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ParseException ex) {
            request.setAttribute("erro", "Data inexistente");
            RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);
        }
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
        
        Avaliacao avaliacao = new Avaliacao();
        AvaliacaoCtrl ac = new AvaliacaoCtrl();
        avaliacao.setAbdominal(Double.parseDouble(request.getParameter("abdominal")));
        avaliacao.setAltura(Double.parseDouble(request.getParameter("altura")));
        Aluno a = new Aluno();
        a.setMatricula(Integer.parseInt(request.getParameter("matricula")));
        avaliacao.setAluno(a);
        avaliacao.setCintura(Double.parseDouble(request.getParameter("cintura")));
        avaliacao.setPeso(Double.parseDouble(request.getParameter("peso")));
        avaliacao.setCoxa(Double.parseDouble(request.getParameter("coxa")));
        avaliacao.setSubaxilar(Double.parseDouble(request.getParameter("sub-axilar")));
        avaliacao.setSubescapular(Double.parseDouble(request.getParameter("subescapular")));
        avaliacao.setSuprailiaca(Double.parseDouble(request.getParameter("suprailiaca")));
        avaliacao.setTriceps(Double.parseDouble(request.getParameter("triceps")));
        avaliacao.setTorax(Double.parseDouble(request.getParameter("torax")));
        avaliacao.setQuadril(Double.parseDouble(request.getParameter("quadril")));
        avaliacao.setMetaC(Double.parseDouble(request.getParameter("meta_c")));
        avaliacao.setMetaQ(Double.parseDouble(request.getParameter("meta_q")));
        avaliacao.setMetaP(Double.parseDouble(request.getParameter("meta_p")));
        avaliacao.setQuadril(Double.parseDouble(request.getParameter("quadril")));
        Professor p = new Professor();
        p.setCref(Integer.parseInt(request.getParameter("cref")));
        avaliacao.setProfessor(p);
        if (request.getParameter("id") != null) {
            a.setMatricula(Integer.parseInt(request.getParameter("matricula")));
            avaliacao.setAluno(a);
            avaliacao.setId(Double.parseDouble(request.getParameter("id")));
            ac.alterar(avaliacao);
        } else {
            a.setMatricula(Integer.parseInt(request.getParameter("matricula")));
            avaliacao.setAluno(a);
            ac.adicionar(avaliacao);
        }
        System.out.println("Servlet.AvaliacaoServlet.doPost()" + avaliacao.toString());
        System.out.println("Servlet.AvaliacaoServlet.doPost()" + avaliacao.getAluno().getMatricula());
        response.sendRedirect("index");
        
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
