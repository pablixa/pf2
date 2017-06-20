/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * 
 */
public class Cidade {
    int CodIBGE;
    String nome,UF;

    public int getCodIBGE() {
        return CodIBGE;
    }

    public void setCodIBGE(int CodIBGE) {
        this.CodIBGE = CodIBGE;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    @Override
    public String toString() {
        return "Cidade{" + "CodIBGE=" + CodIBGE + ", nome=" + nome + ", UF=" + UF + '}';
    }
    
}
