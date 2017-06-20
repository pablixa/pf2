/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Treino;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * 
 */
public class TreinoDao extends Dao {

    public void adicionar(Treino treino) throws SQLException {
        FichaDao fd = new FichaDao();
        int fichaId;
        fichaId = fd.adicionar(treino);
        ps = connection.prepareStatement("insert into treino(`carga`, `repeticao`, `descanso`, `dataAtual`, `dataRenovacao`,  `ficha_id_ficha`) values(?,?,?,?,?,?)");
        ps.setInt(1, treino.getCarga());
        ps.setInt(2, treino.getRepeticao());
        ps.setInt(3, treino.getDescanso());
        ps.setDate(4, new Date(treino.getDataAtual().getTime()));
        ps.setDate(5, new Date(treino.getDataRenovacao().getTime()));
        ps.setInt(6, fichaId);

        ps.executeUpdate();
        treino.setTreinoId(selecionarUltimo());
        ps = connection.prepareStatement("insert into treino_has_exercicio (`Treino_TreinoID`, `Exercicio_idExercicio`) VALUES(?,?)");
        ps.setInt(1, treino.getTreinoId());
        ps.setInt(2, treino.getExercicio().getId());
        ps.executeUpdate();

    }

    public int selecionarUltimo() throws SQLException {
        ps = connection.prepareStatement("Select TreinoID from treino");
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("TreinoID");
        } else {
            return 0;
        }

    }
}
