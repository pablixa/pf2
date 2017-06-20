/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import DAO.CidadeDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *

 */
public class CidadeCtrl {

    public Map getMunicipios(String uf) throws SQLException {
        System.err.println("Getmuni");
        CidadeDao dao = new CidadeDao();

        return dao.getMunicipios(uf);
    }  public List getUFs() throws SQLException {
       System.err.println("Getuf");
        CidadeDao dao = new CidadeDao();

        return dao.getUFs();
       
    }
}
