/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import DAO.AlunoDao;
import DAO.ProfessorDao;
import Model.Professor;
import Model.Usuario;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *
 */
public class ProfessorCtrl {

    private ProfessorDao pDao;

    public boolean existe(String cref) {
        pDao = new ProfessorDao();

        try {
            return pDao.existe(Integer.parseInt(cref));
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorCtrl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List listarCrefs() {
        List<Professor> list = new ArrayList<Professor>();
        ProfessorDao pd = new ProfessorDao();
        try {
            list.addAll(pd.listarCrefs());
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    Usuario pesquisado(int idInt) {
        pDao = new ProfessorDao();
        Professor p = new Professor();
        try {
            p = (Professor) pDao.pesquisado(idInt);
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorCtrl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ProfessorCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

}
