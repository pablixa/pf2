/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Aluno;
import Model.Cidade;
import Model.Endereco;
import Model.Professor;
import Model.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *
 */
public class ProfessorDao {

    Connection connection;
    PreparedStatement ps;
    Statement st;
    Professor p;
    ResultSet rs;

    /**
     * metodo que adiciona enderedo e usuario no banco de dados,
     *
     * @param usuario
     * @return se e verdadeiro ou falso caso consiga ou nao inserir o usuario
     * corretamente
     */
    public boolean adicionar(Usuario usuario, String cref) {

        PreparedStatement ps;
        try {
            ps = ConnectionFactory.getConnection().prepareStatement("INSERT INTO `professor`(`Usuario_id_Usuario`,`Cref`) VALUES (?,?)");
            ps.setInt(1, (int) usuario.getUsuarioId());
            ps.setInt(2, Integer.parseInt(cref));
            System.err.println(ps.toString());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public void alterar(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Usuario pesquisado(int id) throws SQLException, ParseException {
        EnderecoDao ed = new EnderecoDao();

        p = new Professor();
        connection = ConnectionFactory.getConnection();
        ps = connection.prepareStatement("SELECT * FROM `usuario` INNER join professor on (professor.Usuario_id_Usuario=usuario.id_Usuario) WHERE professor.Cref=?");
        ps.setInt(1, id);
        rs = ps.executeQuery();
        p.setCref(id);
        while (rs.next()) {
            p.setNome(rs.getString("nome"));
            p.setCpf(rs.getString("cpf"));
            p.setEmail(rs.getString("email"));
            p.setTelefone(rs.getString("telefone"));
            p.setEndereço(ed.pesquisado(id));
            Date date = rs.getDate("dtNascimento");
            p.setDtNascimento(new java.util.Date(date.getTime()));
           
            p.setSexo(rs.getString("sexo"));

        }connection.close();
        return p;
    }

    public void alterar(Usuario usuario, String cref) throws SQLException {

        ps = ConnectionFactory.getConnection().prepareStatement("UPDATE `professor` SET `Cref`=?WHERE `Usuario_id_Usuario`=?");
        ps.setInt(1, (int) usuario.getUsuarioId());
        ps.setInt(2, Integer.parseInt(cref));
        System.err.println(ps.toString());
        ps.executeUpdate();

    }

    public boolean existe(int id) throws SQLException {
        connection = ConnectionFactory.getConnection();
        ps = connection.prepareStatement("SELECT * FROM  professor  WHERE cref=?");
        ps.setInt(1, id);
        rs = ps.executeQuery();
        return rs.next();
    }

    public List<Professor> listarCrefs() throws SQLException {
        List<Professor> list = new ArrayList<Professor>();

        connection = ConnectionFactory.getConnection();
        ps = connection.prepareStatement("SELECT usuario.nome,professor.Cref FROM usuario INNER join professor on (professor.Usuario_id_Usuario = usuario.id_Usuario);");
        int i = 0;
        rs = ps.executeQuery();
        while (rs.next()) {
            i++;
            Professor p = new Professor();
            p.setNome(rs.getString(1));
            p.setCref(rs.getInt(2));
            list.add(p);

            System.out.println("DAO.ProfessorDao.listarCrefs() " + i);

        }

        return list;
    }
}
