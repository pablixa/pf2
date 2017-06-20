/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Controler.UsuarioCtrl;
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
public class UsuarioDao {

    private Connection connection;
    PreparedStatement ps;

    public String autenticar(String user, String senha) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement ps = conn.prepareStatement("SELECT usuario.nome,aluno.matricula from usuario INNER JOIN aluno on (aluno.Usuario_id_Usuario=usuario.id_Usuario) WHERE usuario.status=1 and usuario.cpf= ? AND usuario.senha=?");
        System.out.println(user + "," + senha);
        System.out.println(ps);
        ps.setString(1, user);
        ps.setString(2, senha);
        System.out.println(ps);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Aluno usuario = new Aluno();
            usuario.setMatricula(rs.getInt(2));
            usuario.setNome(rs.getString(1));
            System.out.println(usuario.getClass().getName());
            conn.close();
            return usuario.getClass().getName();
        } else {
            ps = conn.prepareStatement("SELECT usuario.nome,professor.cref from usuario INNER JOIN professor on (professor.Usuario_id_Usuario=usuario.id_Usuario) WHERE usuario.cpf= ? AND usuario.senha=?");
            ps.setString(1, user);
            ps.setString(2, senha);
            rs = ps.executeQuery();

            if (rs.next()) {
                Professor professor = new Professor();
                professor.setCref(rs.getInt(2));
                professor.setNome(rs.getString(1));
                rs.close();
                conn.close();
                return professor.getClass().getName() + "." + professor.getNome();
            } else {
                ps = conn.prepareStatement("SELECT usuario.nome,estagiario.ID_estagiario from usuario INNER JOIN estagiario on (estagiario.Usuario_id_Usuario=usuario.id_Usuario) WHERE usuario.cpf= ? AND usuario.senha=?");

                ps.setString(1, user);
                ps.setString(2, senha);
                rs = ps.executeQuery();
                if (rs.next()) {
                    Professor professor = new Professor();
                    professor.setCref(rs.getInt(2));
                    professor.setNome(rs.getString(1));
                    rs.close();
                    conn.close();
                    return professor.getClass().getName();
                } else {
                    conn.close();
                    return null;
                }
            }

        }
    }

    public Usuario selecionar(String user, String senha) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement ps = conn.prepareStatement("SELECT * from usuario where cpf=? and senha =?");

        ps.setString(1, user);
        ps.setString(2, senha);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Usuario usuario = null;
            return usuario;
        } else {
            return null;
        }

    }

    public Usuario adicionar(Usuario usuario) throws SQLException {
        Endereco e = usuario.getEndereco();
        System.out.println("DAO.UsuarioDao.adicionar() logradouro:" + e.getLogradouro());
        Usuario u = usuario;
        EnderecoDao ed = new EnderecoDao();
        Cidade c = usuario.getEndereco().getCidade();
//connection e a classe que conecta o sistema com o banco de dados .      
        connection = ConnectionFactory.getConnection();

        if (ed.adicionar(e)) {

            ps = connection.prepareStatement("SELECT `idEndereco` FROM `endereco`  order by endereco.idEndereco desc limit 1");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                e.setId(rs.getInt(1));
                System.err.println(e.toString());
                ps = ConnectionFactory.getConnection().prepareStatement("INSERT INTO `usuario`( `nome`, `cpf`,  `email`, `senha`,dtNascimento, `Endereco_idEndereco`,sexo,telefone,status) VALUES (?,?,?,?,?,?,?,?,1)");
                ps.setString(1, usuario.getNome());
                ps.setString(2, usuario.getCpf());
                ps.setString(3, usuario.getEmail());
                ps.setString(4, usuario.getSenha());
                ps.setDate(5, new Date(usuario.getDtNascimento().getTime()));
                ps.setInt(6, e.getId());
                ps.setString(7, usuario.getSexo());
                ps.setString(8, usuario.getTelefone());

                System.err.println(ps.toString());
                ps.executeUpdate();
                ps = connection.prepareStatement("SELECT `id_Usuario` FROM `usuario` ORDER by id_Usuario DESC LIMIT 1");
                System.err.println(ps.toString());
                rs = ps.executeQuery();
                if (rs.next()) {
                    u.setUsuarioId(rs.getInt(1));
                }
            }

        }
        return usuario;
    }

    public List<Usuario> listar() throws SQLException {
        System.out.println("DAO.UsuarioDao.listar()");
        connection = ConnectionFactory.getConnection();
        ps = connection.prepareStatement("SELECT * FROM `usuario` ");
        ResultSet rs = ps.executeQuery();
        List<Usuario> usuarios = new ArrayList<Usuario>();

        while (rs.next()) {
            Endereco endereco = new Endereco();
            Usuario u = new Usuario();
            u.setUsuarioId(rs.getInt(1));
            u.setNome(rs.getString(2));
            u.setCpf(rs.getString(3));
            u.setDtNascimento(rs.getDate(4));
            u.setEmail(rs.getString(5));
            u.setSenha(rs.getString(6));
            endereco.setId(rs.getInt(7));
            u.setEndereço(endereco);
            usuarios.add(u);
            System.out.println(u.toString());
        }
        return usuarios;
    }

    public Map<String, String> pesquisar(String selectedValue) throws SQLException {
        Map<String, String> usuario = new TreeMap<String, String>();
        connection = ConnectionFactory.getConnection();
        ps = connection.prepareStatement("SELECT * FROM `usuario` INNER join aluno on (aluno.Usuario_id_Usuario=usuario.id_Usuario)INNER JOIN endereco on (endereco.idEndereco=usuario.Endereco_idEndereco) WHERE aluno.matricula=?");
        ps.setInt(0, Integer.parseInt(selectedValue));
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            usuario.put("nome", rs.getString("nome"));
            usuario.put("cpf", rs.getString("cpf"));
            usuario.put("email", rs.getString("email"));
            usuario.put("telefone", rs.getString("telefone"));
            usuario.put("endereco", rs.getString("logradouro") + " " + rs.getString("complemento"));
            usuario.put("nascimento", rs.getString("dtNascimento"));
            usuario.put("sexo", rs.getString("sexo"));

        }
        return usuario;
    }

    public Usuario alterar(Usuario usuario) throws SQLException {
        Endereco e = usuario.getEndereco();
        System.out.println("DAO.UsuarioDao.adicionar() logradouro:" + e.getLogradouro());

        EnderecoDao ed = new EnderecoDao();

//connection e a classe que conecta o sistema com o banco de dados .      
        connection = ConnectionFactory.getConnection();
        e.setId(ed.alterar(e, usuario.getUsuarioId()));
        if (e.getId() > 0) {
            System.err.println(e.toString());
            ps = ConnectionFactory.getConnection().prepareStatement("UPDATE `usuario` SET `nome`=?,`cpf`=?,`dtNascimento`=?,`email`=?,`senha`=?,`Endereco_idEndereco`=?,`telefone`=?,`sexo`=? WHERE `id_Usuario`=?");
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getCpf());
            ps.setDate(3, new Date(usuario.getDtNascimento().getTime()));
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getSenha());

            ps.setInt(6, e.getId());
            ps.setString(7, usuario.getTelefone());
            ps.setString(8, usuario.getSexo());
            ps.setInt(9, (int) usuario.getUsuarioId());

            System.err.println(ps.toString());
            ps.executeUpdate();

        }
        return usuario;

    }

    public Usuario pesquisado(int id) throws SQLException {
        connection = ConnectionFactory.getConnection();

        EnderecoDao ed = new EnderecoDao();

        Usuario u = new Usuario();

        ps = connection.prepareStatement("SELECT * FROM `usuario` where id_Usuario");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            u.setNome(rs.getString("nome"));
            u.setUsuarioId(rs.getInt("id_Usuario"));
            u.setCpf(rs.getString("cpf"));
            u.setEmail(rs.getString("email"));
            u.setSenha(rs.getString("senha"));
            u.setTelefone(rs.getString("telefone"));
            u.setEndereço(ed.pesquisado(id));

            Date date = rs.getDate("dtNascimento");
            u.setDtNascimento(new java.util.Date(date.getTime()));
            u.setSexo(rs.getString("sexo"));

        }

        ps.close();
        rs.close();
        connection.close();
        return u;

    }

    public boolean excluir(int parseInt) throws SQLException {
        connection = ConnectionFactory.getConnection();
        boolean tf;
        ps = connection.prepareStatement("UPDATE `saapa_db`.`usuario` SET `status` = '0' WHERE `usuario`.`id_Usuario` = ?;");
        ps.setInt(1, parseInt);
        return ps.execute();

    }

}
