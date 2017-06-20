/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.ConnectionFactory;
import Model.Exercicio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 
 */
public class ExercicioDao {

    PreparedStatement ps;
    Connection connection = ConnectionFactory.getConnection();
    ResultSet rs;

    public void adicionar(Exercicio exercicio) throws SQLException {
        System.out.println("Servlet.ExercicioDao.adicionar()" + exercicio.toString()
        );
        ps = connection.prepareStatement("insert into exercicio (nome,tipo)values(?,?)");
        ps.setString(1, exercicio.getNome());
        ps.setString(2, exercicio.getTipo());
        ps.executeUpdate();
    }

    public void alterar(Exercicio exercicio) throws SQLException {
        ps = connection.prepareStatement("update exercicio set nome=?,tipo=? where IdExercicio=?");
        ps.setString(1, exercicio.getNome());
        ps.setString(2, exercicio.getTipo());
        ps.setInt(3, exercicio.getId());
        ps.executeUpdate();
    }

    public int existe(String nome) throws SQLException {
        int id = 0;
        ps = connection.prepareStatement("Select * from exercicio where nome=?");
        ps.setString(1, nome);
        rs = ps.executeQuery();
        if (rs.next()) {
            id = rs.getInt(1);

        }
        return id;
    }

    public List<Exercicio> pesquisado(int id) throws SQLException {
        List<Exercicio> es = new ArrayList<Exercicio>();
        ps = connection.prepareStatement("select * from exercicio where idExercicio=?");
        ps.setInt(1, id);
        rs = ps.executeQuery();
        while (rs.next()) {
            Exercicio e = new Exercicio();
            e.setNome(rs.getString("nome"));
            e.setTipo(rs.getString("tipo"));
            e.setId(rs.getInt("idExercicio"));
            es.add(e);
        }
        return es;
    }

    public List<Exercicio> listar() throws SQLException {
        List<Exercicio> es = new ArrayList<Exercicio>();
        ps = connection.prepareStatement("select * from exercicio");

        rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println("DAO.ExercicioDao.listar()");
            Exercicio e = new Exercicio();
            e.setNome(rs.getString("nome"));
            e.setTipo(rs.getString("tipo"));
            e.setId(rs.getInt("idExercicio"));
            es.add(e);
        }
        connection.close();
        return es;
    }

}
