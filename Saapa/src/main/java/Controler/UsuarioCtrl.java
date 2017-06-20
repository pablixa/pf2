/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import DAO.AlunoDao;
import DAO.EstagiarioDao;
import DAO.ProfessorDao;
import DAO.UsuarioDao;
import Model.Aluno;
import Model.Usuario;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *

 */
public class UsuarioCtrl {

    UsuarioDao usuarioDao;

    public String autenticar(String user, String senha) throws SQLException {
        usuarioDao = new UsuarioDao();

        String tipo = usuarioDao.autenticar(user, senha);
        if (tipo != null) {

            return tipo;
        } else {
            return null;
        }
    }

    public Usuario selecionar(String user, String senha) throws SQLException {
        usuarioDao = new UsuarioDao();
        Usuario usuario = usuarioDao.selecionar(user, senha);
        if (usuario != null) {
            return usuario;
        } else {
            usuario = new Usuario();
            usuario.setUsuarioId(0);
            return usuario;
        }
    }

    public Usuario adicionar(Usuario usuario) throws SQLException {

        UsuarioDao uDao = new UsuarioDao();
        usuario = uDao.adicionar(usuario);
        return usuario;
    }

    public List<Usuario> listar() {
        usuarioDao = new UsuarioDao();
        System.out.println("Controler.UsuarioCtrl.listar()");
        List<Usuario> usuarios = new ArrayList<>();
        try {
            usuarios = usuarioDao.listar();
            return usuarios;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Map<String, String> pesquisar(String selectedValue) {
        usuarioDao = new UsuarioDao();
        try {
            return usuarioDao.pesquisar(selectedValue);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioCtrl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Usuario> pesquisado(String id, String tipo) {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        Usuario u = new Usuario();
        usuarioDao = new UsuarioDao();
        System.out.println("Controler.UsuarioCtrl.pesquisado()  " + tipo);
        int idInt = Integer.parseInt(id);
        try {
            switch (tipo) {
                case "Aluno":
                    System.out.println("Controler.UsuarioCtrl.pesquisado() case Aluno");
                    AlunoCtrl ac = new AlunoCtrl();

                    u = ac.pesquisado(idInt);

                    break;

                case "Professor":
                    ProfessorCtrl pc = new ProfessorCtrl();
                    u = pc.pesquisado(idInt);
                    break;
                case "Estagiario":
                    EstagiarioDao ec = new EstagiarioDao();
                    u = ec.pesquisado(idInt);
                    break;

            }
            System.out.println(u);
            usuarios.add(u);
            return usuarios;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioCtrl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public Usuario alterar(Usuario usuario) throws SQLException {
        UsuarioDao uDao = new UsuarioDao();
        usuario = uDao.alterar(usuario);
        return usuario;
    }

    public String exluir(String selectedValue) {
        UsuarioDao uDao = new UsuarioDao();
        Usuario usuario;
        try {
            if (uDao.excluir(Integer.parseInt(selectedValue))) {
                return "Usuario excluido com sucesso";
            } else {
                return "Usuario excluido com sucesso";
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioCtrl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
}
