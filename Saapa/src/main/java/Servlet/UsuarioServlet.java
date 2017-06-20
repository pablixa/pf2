/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controler.AlunoCtrl;
import Controler.CidadeCtrl;
import Controler.ProfessorCtrl;
import Controler.UsuarioCtrl;
import DAO.AlunoDao;
import DAO.EstagiarioDao;
import DAO.ProfessorDao;
import Model.Aluno;
import Model.Cidade;
import Model.Endereco;
import Model.Estagiario;
import Model.Professor;
import Model.Telefone;
import Model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
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
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

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
        String selectedValue = request.getParameter("value");
        String pesquisa = request.getParameter("pesquisa");
        CidadeCtrl cc = new CidadeCtrl();
        UsuarioCtrl uc = new UsuarioCtrl();

        if (pesquisa != null) {
            if (pesquisa.equals("excluir")) {
                System.out.println("Servlet.UsuarioServlet.doGet()");

                String resposta = uc.exluir(selectedValue);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(resposta);

            }
            if (pesquisa.equals("uf")) {
                System.out.println("Servlet.UsuarioServlet.doGet()");
                Map<String, String> options;
                try {
                    options = cc.getMunicipios(selectedValue);
                    System.out.println("Servlet.UsuarioServlet.doGet() options" + options.toString());
                    JSONObject my_obj = new JSONObject(options);
                    String json_string = my_obj.toString();
                    System.out.println("Servlet.UsuarioServlet.doGet() json" + my_obj);
                    System.out.println("Servlet.UsuarioServlet.doGet() json" + json_string);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json_string);
                } catch (SQLException ex) {

                }
            }
        } else if (request.getParameter("matricula") != null) {
            AlunoCtrl ac = new AlunoCtrl();
            if (ac.existe(request.getParameter("matricula"))) {
                if (request.getParameter("pagina").contains("acompanhar")) {
                    request.setAttribute("id", request.getParameter("matricula"));
                    RequestDispatcher dispatcher = request.getRequestDispatcher("acompanhamento.jsp");
                    dispatcher.forward(request, response);
                } else {
                    System.err.println("matricula");
                    request.setAttribute("id", request.getParameter("matricula"));
                    request.setAttribute("tipo", "Aluno");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("editar_usuario.jsp");
                    dispatcher.forward(request, response);
                }
            } else {

                request.setAttribute("erro", "Aluno não cadastrado");
                RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
                dispatcher.forward(request, response);
            }
        } else if (request.getParameter("Fid") != null) {
            if (request.getParameter("tipo") == "professor") {
                ProfessorCtrl pc = new ProfessorCtrl();
                if (pc.existe(request.getParameter("Fid"))) {

                }
            }
            request.setAttribute("id", request.getParameter("Fid"));
            request.setAttribute("tipo", request.getParameter("tipo"));
            RequestDispatcher dispatcher = request.getRequestDispatcher("editar_usuario.jsp");
            dispatcher.forward(request, response);

        } else {

            request.setAttribute("erro", "Aluno não cadastrado");
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

        System.err.println("TESTE");
        System.err.println("id:" + request.getParameter("id"));
        System.err.println("Matricula" + request.getParameter("matricula"));
        if (request.getParameter("id") == null) {
            System.err.println("adicionar");

            System.err.println("id:" + request.getParameter("nome"));
            Usuario usuario = new Usuario();

            UsuarioCtrl uc = new UsuarioCtrl();
            String[] data = request.getParameter("data").split("/");
            System.out.println("Servlet.UsuarioServlet.doPost()  " + request.getParameter("data").toString());
            System.out.println("Servlet.UsuarioServlet.doPost()  " + Integer.parseInt(data[2]));
            Date date;
            try {
                date = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("data"));
                usuario.setDtNascimento(date);
            } catch (ParseException ex) {
                Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            usuario.setSenha(request.getParameter("senha"));

            usuario.setCpf(String.valueOf(request.getParameter("cpf")));
            usuario.setNome(String.valueOf(request.getParameter("nome")));
            usuario.setSexo(String.valueOf(request.getParameter("sexo")));
            usuario.setTelefone(String.valueOf(request.getParameter("telefone")));
            usuario.setEmail(String.valueOf(request.getParameter("email")));
            Endereco endereco = new Endereco();
            endereco.setCep(String.valueOf(request.getParameter("cep")));
            Cidade cidade = new Cidade();
            cidade.setCodIBGE(Integer.parseInt(String.valueOf(request.getParameter("cidade"))));
            cidade.setUF(String.valueOf(request.getParameter("uf")));
            endereco.setCidade(cidade);
            endereco.setComplemento(String.valueOf(request.getParameter("complemento")));
            endereco.setLogradouro(request.getParameter("logradouro"));
            usuario.setEndereço(endereco);
            usuario.setStatus("ativo");
            System.out.println("Servlet.UsuarioServlet.doPost() logradouro:" + request.getParameter("logradouro"));

            try {
                usuario = uc.adicionar(usuario);

                switch (request.getParameter("tipo")) {
                    case "aluno":
                        AlunoCtrl ac = new AlunoCtrl();
                        ac.adicionar(usuario);
                        break;
                    case "professor":
                        ProfessorDao pd = new ProfessorDao();
                        String cref = request.getParameter("cref");
                        pd.adicionar(usuario, cref);
                        break;
                    case "estagiario":
                        EstagiarioDao ed = new EstagiarioDao();
                        ed.adicionar(usuario);
                        break;

                }
                response.sendRedirect("index.jsp");
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect("erro.jsp");
            }
        } else {

            System.err.println("tipo:" + request.getParameter("tipo"));

            System.err.println("id:" + request.getParameter("data"));
            Usuario usuario = new Usuario();

            UsuarioCtrl ac = new UsuarioCtrl();

            Date date = new Date(request.getParameter("data"));
            usuario.setUsuarioId(Integer.parseInt(request.getParameter("id")));
            System.out.println("Servlet.UsuarioServlet.doPost()  " + date.toString());
            usuario.setSenha(request.getParameter("senha"));
            usuario.setDtNascimento(date);
            usuario.setCpf(String.valueOf(request.getParameter("cpf")));
            usuario.setNome(String.valueOf(request.getParameter("nome")));
            usuario.setSexo(String.valueOf(request.getParameter("sexo")));
            usuario.setTelefone(String.valueOf(request.getParameter("telefone")));
            usuario.setEmail(String.valueOf(request.getParameter("email")));
            Endereco endereco = new Endereco();
            endereco.setCep(String.valueOf(request.getParameter("cep")));
            Cidade cidade = new Cidade();
            cidade.setCodIBGE(Integer.parseInt(String.valueOf(request.getParameter("cidade"))));
            cidade.setUF(String.valueOf(request.getParameter("uf")));
            endereco.setCidade(cidade);
            endereco.setComplemento(String.valueOf(request.getParameter("complemento")));
            endereco.setLogradouro(request.getParameter("logradouro"));
            usuario.setEndereço(endereco);
            usuario.setStatus("ativo");

            try {
                usuario = ac.alterar(usuario);

                if (request.getParameter("tipo") == "professor") {

                    ProfessorDao pd = new ProfessorDao();
                    String cref = request.getParameter("cref");

                    pd.alterar(usuario, cref);
                    response.sendRedirect("index.jsp");
                }
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
                   response.sendRedirect("erro.jsp");
            }

        };

    }

}
