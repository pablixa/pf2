/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import DAO.AlunoDao;
import Model.Aluno;
import Model.Avaliacao;
import Model.Usuario;
import java.sql.SQLException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AlunoCtrl {

    AlunoDao alunoDao;

    public void adicionar(Usuario usuario) {
        alunoDao = new AlunoDao();
        alunoDao.adicionar(usuario);
    }

    public boolean existe(String matricula) {
        alunoDao = new AlunoDao();

        try {
            return alunoDao.existe(Integer.parseInt(matricula));
        } catch (SQLException ex) {
            Logger.getLogger(AlunoCtrl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Aluno pesquisado(int idInt) {
        alunoDao = new AlunoDao();
        Aluno a = new Aluno();
        try {
            a = (Aluno) alunoDao.pesquisado(idInt);

        } catch (SQLException ex) {
            Logger.getLogger(AlunoCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    public int getIdade(Aluno a) {
        Date dataUsuario = a.getDtNascimento();
        Date dataAtual = new Date(System.currentTimeMillis());

        int idade = dataAtual.getYear() - dataUsuario.getYear();
        if (dataAtual.getMonth() < dataUsuario.getMonth()) {
            idade--;
        } else if (dataAtual.getMonth() == dataUsuario.getMonth()) {
            if (dataAtual.getDay() < dataUsuario.getDay()) {
                idade--;
            }
        }
        return idade;
    }

}
