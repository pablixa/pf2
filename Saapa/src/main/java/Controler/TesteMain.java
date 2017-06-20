/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.Aluno;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 
 */
public class TesteMain {

    public static void main(String[] args) {
        GraficoCtrl ctrl = new GraficoCtrl();
        try {
            Aluno a = new Aluno();
            a.setMatricula(1);
            OutputStream arquivo = ctrl.geraImc(a);
        } catch (IOException ex) {
            Logger.getLogger(TesteMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
