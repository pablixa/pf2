/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 */
public class EnderecoDao {

    public boolean adicionar(Endereco e) {
        System.out.println("DAO.EnderecoDao.adicionar() lgradouro: " + e.getLogradouro());
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement("INSERT INTO `endereco`(`cep`, `logradouro`, `complemento`, `Cidade_Cod.IBGE`) VALUES (?,?,?,?)");

            ps.setString(1, e.getCep());
            ps.setString(2, e.getLogradouro());
            ps.setString(3, e.getComplemento());
            ps.setInt(4, e.getCidade().getCodIBGE());
            System.err.println(ps.toString());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public int porUsuario(double id) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT `Endereco_idEndereco` FROM `usuario` WHERE `id_Usuario`=?");
        ps.setInt(1, (int) id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("Endereco_idEndereco");

        }
        return 0;
    }

    public Endereco pesquisado(int id) throws SQLException {
        Endereco e = new Endereco();
        Connection connection = ConnectionFactory.getConnection();
        CidadeDao cd = new CidadeDao();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM `endereco` WHERE `idEndereco`=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            e.setId(id);
            e.setCep(rs.getString("cep"));
            e.setLogradouro(rs.getString("logradouro"));
            e.setComplemento(rs.getString("complemento"));
            e.setCidade(cd.pesquisado(rs.getInt("Cidade_Cod.IBGE")));

        }
        connection.close();
        return e;

    }

    public int alterar(Endereco e, double id) throws SQLException {
        e.setId(porUsuario(id));
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement("UPDATE `endereco` SET `cep`=?,`logradouro`=?,`complemento`=?,`Cidade_Cod.IBGE`=? WHERE  idEndereco=?");

            ps.setString(1, e.getCep());
            ps.setString(2, e.getLogradouro());
            ps.setString(3, e.getComplemento());
            ps.setInt(4, e.getCidade().getCodIBGE());
            ps.setInt(5, e.getId());
            System.err.println(ps.toString());

            ps.executeUpdate();
            return e.getId();
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDao.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
}
