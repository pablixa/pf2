/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import DAO.TreinoDao;
import Model.Treino;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *

 */
public class TreinoCtrl {

    Treino treino;
    TreinoDao td;

    public boolean adicionar(Treino treino) {
        td = new TreinoDao();
        try {
            td.adicionar(treino);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TreinoCtrl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Treino treino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
