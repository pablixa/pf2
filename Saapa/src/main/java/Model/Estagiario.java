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
public class Estagiario extends Usuario{
    int IdEstagiario;
    String nome;
 

    public int getIdEstagiario() {
        return IdEstagiario;
    }

    public void setIdEstagiario(int IdEstagiario) {
        this.IdEstagiario = IdEstagiario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
