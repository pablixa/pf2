/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;

/**
 *
 * 
 */
public class Aluno extends Usuario {
 
   
    int matricula;
    List<Avaliacao> avaliacoes;
    List<Treino> treinos;
    List<Telefone> telefones;

 


    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public List<Treino> getTreinos() {
        return treinos;
    }

    public void setTreinos(List<Treino> treinos) {
        this.treinos = treinos;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    @Override
    public String toString() {
    
        return super.toString()+"Aluno{" + "matricula=" + matricula + ", avaliacoes=" + avaliacoes + ", treinos=" + treinos + ", telefones=" + telefones + '}';
    }
    
    
}
