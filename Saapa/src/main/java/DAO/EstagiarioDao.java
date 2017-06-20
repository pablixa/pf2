/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Aluno;
import Model.Cidade;
import Model.Endereco;
import Model.Estagiario;
import Model.Professor;
import Model.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 */
public class EstagiarioDao {

    /**
     * metodo que adiciona enderedo e usuario no banco de dados,
     *
     * @param usuario
     * @return se e verdadeiro ou falso caso consiga ou nao inserir o usuario
     * corretamente
     */
    public boolean adicionar(Usuario usuario) {

        PreparedStatement ps;
        try {
            ps = ConnectionFactory.getConnection().prepareStatement("INSERT INTO `estagiario`(`Usuario_id_Usuario`) VALUES (?)");
            ps.setInt(1, (int) usuario.getUsuarioId());
            System.err.println(ps.toString());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EstagiarioDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public void alterar(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Usuario pesquisado(int id) throws SQLException {
        EnderecoDao ed = new EnderecoDao();

        Estagiario u = new Estagiario();
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM `usuario` INNER join estagiario on (estagiario.Usuario_id_Usuario=usuario.id_Usuario) WHERE estagiario.ID_Estagiario=?");
        ps.setInt(0, id);
        ResultSet rs = ps.executeQuery();
        u.setIdEstagiario(id);
        while (rs.next()) {
            u.setNome(rs.getString("nome"));
            u.setCpf(rs.getString("cpf"));
            u.setEmail(rs.getString("email"));
            u.setTelefone(rs.getString("telefone"));
            u.setEndereço(ed.pesquisado(id));
            u.setDtNascimento(new java.util.Date(rs.getString("dtNascimento")));
            u.setSexo(rs.getString("sexo"));

        }
        return u;
    }
}
