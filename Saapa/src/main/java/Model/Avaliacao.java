/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 *
 */
public class Avaliacao {
    double peso,altura,quadril,cintura,torax,subaxilar,triceps,abdominal,coxa,subescapular,suprailiaca,metaQ,metaC,metaP,id;
    Aluno aluno;
    Professor professor;
    Date dataAtual,dataRenovacao;

    public Date getDataAtual() {
        return dataAtual;
    }

    public void setDataAtual(Date dataAtual) {
        this.dataAtual = dataAtual;
    }

    public Date getDataRenovacao() {
        return dataRenovacao;
    }

    public void setDataRenovacao(Date dataRenovacao) {
        this.dataRenovacao = dataRenovacao;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getQuadril() {
        return quadril;
    }

    public void setQuadril(double quadril) {
        this.quadril = quadril;
    }

    public double getCintura() {
        return cintura;
    }

    public void setCintura(double cintura) {
        this.cintura = cintura;
    }

    public double getTorax() {
        return torax;
    }

    public void setTorax(double torax) {
        this.torax = torax;
    }

    public double getSubaxilar() {
        return subaxilar;
    }

    public void setSubaxilar(double subaxilar) {
        this.subaxilar = subaxilar;
    }

    public double getTriceps() {
        return triceps;
    }

    public void setTriceps(double triceps) {
        this.triceps = triceps;
    }

    public double getAbdominal() {
        return abdominal;
    }

    public void setAbdominal(double adbominal) {
        this.abdominal = adbominal;
    }

    public double getCoxa() {
        return coxa;
    }

    public void setCoxa(double coxa) {
        this.coxa = coxa;
    }

    public double getSubescapular() {
        return subescapular;
    }

    public void setSubescapular(double subescapaular) {
        this.subescapular = subescapaular;
    }

    public double getSuprailiaca() {
        return suprailiaca;
    }

    public void setSuprailiaca(double suprailiaca) {
        this.suprailiaca = suprailiaca;
    }

   
    public double getMetaQ() {
        return metaQ;
    }

    public void setMetaQ(double metaQ) {
        this.metaQ = metaQ;
    }

    public double getMetaC() {
        return metaC;
    }

    public void setMetaC(double metaC) {
        this.metaC = metaC;
    }

    public double getMetaP() {
        return metaP;
    }

    public void setMetaP(double metaP) {
        this.metaP = metaP;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "Avaliacao{" + "peso=" + peso + ",\n altura=" + altura + ",\n quadril=" + quadril + ",\n cintura=" + cintura + ",\n torax=" + torax + ",\n subaxilar=" + subaxilar + ",\n triceps=" + triceps + ",\n adbominal=" + abdominal + ",\n coxa=" + coxa + ",\n subescapaular=" + subescapular + ",\n suprailiaca=" + suprailiaca + ",\n dataAtual=" + dataAtual + ",\n dataRenovacao=" + dataRenovacao + ",\n metaQ=" + metaQ + ",\n metaC=" + metaC + ",\n metaP=" + metaP + ",\n id=" + id + ",\n aluno=" + aluno + ",\n professor=" + professor + '}';
    }
    
}
