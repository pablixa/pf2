/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import java.util.Date;
import java.util.List;

/**
 *
 * 
 */
public class Usuario {

    @Override
    public String toString() {
        return "Usuario{" + "dtNascimento=" + dtNascimento + ", cpf=" + cpf + ", sexo=" + sexo + ", email=" + email + ", status=" + status + ", nome=" + nome + ", senha=" + senha + ", usuarioId=" + usuarioId + ", telefone=" + telefone + ", endere\u00e7o=" + endereço + '}';
    }

    
   

    
Date dtNascimento;
    String cpf, sexo, telefone,email, status,  nome, senha;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    int usuarioId;


    Endereco endereço;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   

    public Endereco getEndereco() {
        return endereço;
    }

    public void setEndereço(Endereco endereço) {
        this.endereço = endereço;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int matricula) {
        this.usuarioId = matricula;
    }
}
