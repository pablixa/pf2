/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import DAO.ExercicioDao;
import Model.Exercicio;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.fileupload.FileItem;

/**
 *
 
 */
public class ExercicioCtrl {

    ExercicioDao ed;
    private static String PASTA = "fotos";

    public boolean adicionar(Exercicio exercicio) {
        ed = new ExercicioDao();
        try {
            System.out.println("Servlet.ExercicioCtrl.adicionar()" + exercicio.toString());
            ed.adicionar(exercicio);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ExercicioCtrl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Exercicio exercicio) {
        ed = new ExercicioDao();
        try {
            ed.alterar(exercicio);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ExercicioCtrl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public int existe(String nome) {
        ed = new ExercicioDao();
        int id = 0;
        try {
            id = ed.existe(nome);
        } catch (SQLException ex) {
            Logger.getLogger(ExercicioCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public List listar() {
        ed = new ExercicioDao();
        try {
            List<Exercicio> es = ed.listar();
            return es;
        } catch (SQLException ex) {
            Logger.getLogger(ExercicioCtrl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public List pesquisado(String id) {
        ed = new ExercicioDao();
        int idInt = Integer.parseInt(id);
        try {
            List<Exercicio> es = ed.pesquisado(idInt);
            return es;
        } catch (SQLException ex) {
            Logger.getLogger(ExercicioCtrl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

}
