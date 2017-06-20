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
public class Professor extends Usuario{
    int cref;
    String nome;

    public int getCref() {
        return cref;
    }

    public void setCref(int cref) {
        this.cref = cref;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return super.toString()+ "Professor{" + "cref=" + cref + ", nome=" + nome + '}';
    }

  
    
}
