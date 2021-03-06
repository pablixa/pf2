/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controler.ExercicioCtrl;
import Model.Exercicio;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * 
 */
public class ExercicioServlet extends HttpServlet {

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
            out.println("<title>Servlet ExercicioServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ExercicioServlet at " + request.getContextPath() + "</h1>");
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
        ExercicioCtrl ec = new ExercicioCtrl();
        int id;
        id = ec.existe(request.getParameter("nome"));
        if (id > 0) {

            request.setAttribute("id", id);

            RequestDispatcher dispatcher = request.getRequestDispatcher("editar_exercicio.jsp");
            dispatcher.forward(request, response);

        } else {

            request.setAttribute("erro", "Exercício não cadastrado");
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
        ExercicioCtrl ec = new ExercicioCtrl();
        Exercicio exercicio = new Exercicio();
        exercicio.setNome(request.getParameter("nome"));
        exercicio.setTipo(request.getParameter("tipo"));

        if (request.getParameter("id") == null) {
            System.out.println("Servlet.ExercicioServlet.doPost()" + exercicio.toString());
            System.out.println("Servlet.ExercicioServlet.doPost()" + exercicio.toString());
            System.out.println("Servlet.ExercicioServlet.doPost()" + exercicio.toString());
            if (ec.adicionar(exercicio)) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");

                dispatcher.forward(request, response);

            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");

                dispatcher.forward(request, response);
            }

        } else {
            exercicio.setId(Integer.parseInt(request.getParameter("id")));
            if (ec.alterar(exercicio)) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");

                dispatcher.forward(request, response);

            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");

                dispatcher.forward(request, response);
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
