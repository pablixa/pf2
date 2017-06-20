/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Aluno;
import Model.Avaliacao;
import Model.Professor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 *
 */
public class AvaliacaoDao {

    Connection connection;
    PreparedStatement ps;
    Statement st;

    ResultSet rs;

    public void adicionar(Avaliacao avaliacao) throws SQLException {
        connection = ConnectionFactory.getConnection();
        ps = connection.prepareStatement("INSERT INTO avaliacao(peso, altura, quadril, cintura, torax, subAxiliar,triceps, abdominal, coxa, subescapular, suprailiaca, dataAtual,dataRenovacao, metaQ, metaC, metaP,  Aluno_idAluno, Professor_Cref) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        ps.setDouble(1, avaliacao.getPeso());
        ps.setDouble(2, avaliacao.getAltura());
        ps.setDouble(3, avaliacao.getQuadril());
        ps.setDouble(4, avaliacao.getCintura());
        ps.setDouble(5, avaliacao.getTorax());
        ps.setDouble(6, avaliacao.getSubaxilar());
        ps.setDouble(7, avaliacao.getTriceps());
        ps.setDouble(8, avaliacao.getAbdominal());
        ps.setDouble(9, avaliacao.getCoxa());
        ps.setDouble(10, avaliacao.getSubescapular());
        ps.setDouble(11, avaliacao.getSuprailiaca());
        ps.setDate(12, new java.sql.Date(avaliacao.getDataAtual().getTime()));
        ps.setDate(13, new java.sql.Date(avaliacao.getDataRenovacao().getTime()));
        ps.setDouble(14, avaliacao.getMetaQ());
        ps.setDouble(15, avaliacao.getMetaC());
        ps.setDouble(16, avaliacao.getMetaP());
        ps.setInt(17, avaliacao.getAluno().getMatricula());
        ps.setInt(18, avaliacao.getProfessor().getCref());
        ps.executeUpdate();
        connection.close();

    }

    public boolean existe(int matricula, Date data) throws SQLException {
        connection = ConnectionFactory.getConnection();
        ps = connection.prepareStatement("SELECT * FROM avaliacao WHERE Aluno_idAluno= ? and dataAtual=?");
        ps.setInt(1, matricula);
        ps.setDate(2, new java.sql.Date(data.getTime()));
        rs = ps.executeQuery();
        System.out.println("DAO.AvaliacaoDao.existe()" + data.toString());
        System.out.println("DAO.AvaliacaoDao.existe()" + ps.toString());

        return rs.next();
    }

    public List<Avaliacao> pesquisado(int matricula, Date data) throws SQLException, ParseException {
        System.out.println("DAO.AvaliacaoDao.pesquisado()" + data);
        AlunoDao ad = new AlunoDao();
        ProfessorDao pd = new ProfessorDao();
        List<Avaliacao> list = new ArrayList<Avaliacao>();
        connection = ConnectionFactory.getConnection();
        ps = connection.prepareStatement("SELECT * FROM avaliacao WHERE Aluno_idAluno=? and dataAtual=?");
        ps.setInt(1, matricula);
        ps.setDate(2, new java.sql.Date(data.getTime()));
        System.out.println("DAO.AvaliacaoDao.pesquisado()" + ps.toString());
        rs = ps.executeQuery();
        while (rs.next()) {
            Avaliacao avaliacao = new Avaliacao();
            avaliacao.setPeso(rs.getDouble("peso"));
            avaliacao.setAltura(rs.getDouble("altura"));
            avaliacao.setQuadril(rs.getDouble("quadril"));
            avaliacao.setCintura(rs.getDouble("cintura"));
            avaliacao.setTorax(rs.getDouble("torax"));
            avaliacao.setSubaxilar(rs.getDouble("subAxiliar"));
            avaliacao.setTriceps(rs.getDouble("triceps"));
            avaliacao.setAbdominal(rs.getDouble("abdominal"));
            avaliacao.setCoxa(rs.getDouble("coxa"));
            avaliacao.setSubescapular(rs.getDouble("subescapular"));
            avaliacao.setSuprailiaca(rs.getDouble("suprailiaca"));
            java.sql.Date date = rs.getDate("dataAtual");
            avaliacao.setDataAtual(new java.util.Date(
                    date.getTime()));
            date = rs.getDate("dataRenovacao");
            avaliacao.setDataRenovacao(new java.util.Date(date.getYear(), date.getMonth(), date.getDate()));

            avaliacao.setMetaQ(rs.getDouble("metaQ"));
            avaliacao.setMetaC(rs.getDouble(15));
            avaliacao.setMetaP(rs.getDouble(16));
            System.out.println("DAO.AvaliacaoDao.pesquisado()" + rs.getInt("Aluno_idAluno"));
            avaliacao.setAluno((Aluno) ad.pesquisado(rs.getInt("Aluno_idAluno")));
            avaliacao.setProfessor((Professor) pd.pesquisado(rs.getInt("Professor_Cref")));
            list.add(avaliacao);
            System.out.println("DAO.AvaliacaoDao.pesquisado()" + avaliacao.toString());
        }
        connection.close();
        return list;
    }

    public void alterar(Avaliacao avaliacao) throws SQLException {
        connection = ConnectionFactory.getConnection();
        System.out.println("DAO.AvaliacaoDao.alterar()" + avaliacao.toString());
        ps = connection.prepareStatement("UPDATE avaliacao SET peso=?, altura=?, quadril=?, cintura=?, torax=?, subAxiliar=?,triceps=?, abdominal=?, coxa=?, subescapular=?, suprailiaca=?, dataAtual=?,dataRenovacao=?, metaQ=?, metaC=?, metaP=?,  Professor_Cref=? where  Aluno_idAluno=?");

        ps.setDouble(1, avaliacao.getPeso());
        ps.setDouble(2, avaliacao.getAltura());
        ps.setDouble(3, avaliacao.getQuadril());
        ps.setDouble(4, avaliacao.getCintura());
        ps.setDouble(5, avaliacao.getTorax());
        ps.setDouble(6, avaliacao.getSubaxilar());
        ps.setDouble(7, avaliacao.getTriceps());
        ps.setDouble(8, avaliacao.getAbdominal());
        ps.setDouble(9, avaliacao.getCoxa());
        ps.setDouble(10, avaliacao.getSubescapular());
        ps.setDouble(11, avaliacao.getSuprailiaca());
        ps.setDate(12, new java.sql.Date(avaliacao.getDataAtual().getTime()));
        ps.setDate(13, new java.sql.Date(avaliacao.getDataRenovacao().getTime()));
        ps.setDouble(14, avaliacao.getMetaQ());
        ps.setDouble(15, avaliacao.getMetaC());
        ps.setDouble(16, avaliacao.getMetaP());
        ps.setInt(18, avaliacao.getAluno().getMatricula());
        ps.setInt(17, avaliacao.getProfessor().getCref());
        System.out.println("DAO.AvaliacaoDao.alterar()" + ps.toString());
        ps.executeUpdate();
        connection.close();

    }

    public List<Avaliacao> listarAvaliacoes(Aluno a) throws SQLException, ParseException {
        AlunoDao ad = new AlunoDao();
        ProfessorDao pd = new ProfessorDao();
        connection = ConnectionFactory.getConnection();
        ps = connection.prepareStatement("SELECT * FROM `avaliacao` WHERE Aluno_idAluno=?");
        ps.setInt(1, a.getMatricula());
        rs = ps.executeQuery();
        int i;
        List<Avaliacao> as = new ArrayList<Avaliacao>();

        while (rs.next()) {
            Avaliacao avaliacao = new Avaliacao();
            avaliacao.setPeso(rs.getDouble("peso"));
            avaliacao.setAltura(rs.getDouble("altura"));
            avaliacao.setQuadril(rs.getDouble("quadril"));
            avaliacao.setCintura(rs.getDouble("cintura"));
            avaliacao.setTorax(rs.getDouble("torax"));
            avaliacao.setSubaxilar(rs.getDouble("subAxiliar"));
            avaliacao.setTriceps(rs.getDouble("triceps"));
            avaliacao.setAbdominal(rs.getDouble("abdominal"));
            avaliacao.setCoxa(rs.getDouble("coxa"));
            avaliacao.setSubescapular(rs.getDouble("subescapular"));
            avaliacao.setSuprailiaca(rs.getDouble("suprailiaca"));
            java.sql.Date date = rs.getDate("dataAtual");
            avaliacao.setDataAtual(new java.util.Date(
                    date.getTime()));
            date = rs.getDate("dataRenovacao");
            avaliacao.setDataRenovacao(new java.util.Date(date.getYear(), date.getMonth(), date.getDate()));

            avaliacao.setMetaQ(rs.getDouble("metaQ"));
            avaliacao.setMetaC(rs.getDouble(15));
            avaliacao.setMetaP(rs.getDouble(16));
            System.out.println("DAO.AvaliacaoDao.pesquisado()" + rs.getInt("Aluno_idAluno"));
            avaliacao.setAluno((Aluno) ad.pesquisado(rs.getInt("Aluno_idAluno")));
            avaliacao.setProfessor((Professor) pd.pesquisado(rs.getInt("Professor_Cref")));
            as.add(avaliacao);
        }
        connection.close();
        return as;
    }

}
