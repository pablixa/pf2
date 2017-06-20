/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Treino;
import java.sql.SQLException;

/**
 *
 * 
 */
class FichaDao extends Dao {

    public int adicionar(Treino treino) throws SQLException {
        int id = 0;
        ps = connection.prepareStatement("INSERT INTO `ficha`( `Aluno_idAluno`) values(?)");
        ps.setInt(1, treino.getAluno().getMatricula());
        System.out.println("DAO.FichaDao.adicionar()" + ps.toString());
        ps.executeUpdate();
        ps = connection.prepareStatement("select id_ficha from ficha order by id_ficha desc limit 1");
        rs = ps.executeQuery();
        if (rs.next()) {
            id = rs.getInt("id_ficha");

        }
        return id;
    }

}
