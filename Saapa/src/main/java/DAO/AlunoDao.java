/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Aluno;
import Model.Avaliacao;
import Model.Cidade;
import Model.Endereco;
import Model.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 */
public class AlunoDao {

    PreparedStatement ps;
    Connection connection;
    EnderecoDao ed;
    ResultSet rs;

    /**
     * metodo que adiciona enderedo e usuario no banco de dados,
     *
     * @param usuario
     * @return se e verdadeiro ou falso caso consiga ou nao inserir o usuario
     * corretamente
     */
    public boolean adicionar(Usuario usuario) {

        try {
            ps = ConnectionFactory.getConnection().prepareStatement("INSERT INTO `aluno`(`Usuario_id_Usuario`) VALUES (?)");
            ps.setInt(1, (int) usuario.getUsuarioId());
            System.err.println(ps.toString());
            ps.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public void alterar(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Usuario pesquisado(int id) throws SQLException {
        connection = ConnectionFactory.getConnection();
        ed = new EnderecoDao();

        Aluno u = new Aluno();

        ps = connection.prepareStatement("SELECT * FROM `usuario` INNER join aluno on (aluno.Usuario_id_Usuario=usuario.id_Usuario) WHERE aluno.matricula=?");
        ps.setInt(1, id);
        rs = ps.executeQuery();
        u.setMatricula(id);
        while (rs.next()) {

            u.setNome(rs.getString("nome"));
            u.setUsuarioId(rs.getInt("id_Usuario"));
            u.setCpf(rs.getString("cpf"));
            u.setEmail(rs.getString("email"));
            u.setSenha(rs.getString("senha"));
            u.setTelefone(rs.getString("telefone"));
            u.setEndereço(ed.pesquisado(id));
            System.out.println("DAO.AlunoDao.pesquisado() DATe " + rs.getString("dtNascimento"));
            Date date = rs.getDate("dtNascimento");
            u.setDtNascimento(new java.util.Date(date.getTime()));
            u.setSexo(rs.getString("sexo"));

        }
        System.out.println("DAO.AlunoDao.pesquisado()" + u.getMatricula());
        ps.close();
        rs.close();
        connection.close();
        return u;
    }

    public boolean existe(int id) throws SQLException {
        connection = ConnectionFactory.getConnection();
        ps = connection.prepareStatement("SELECT * FROM  aluno  WHERE aluno.matricula=?");
        ps.setInt(1, id);
        rs = ps.executeQuery();
        boolean rss = rs.next();
        connection.close();
        return rss;

    }

}
