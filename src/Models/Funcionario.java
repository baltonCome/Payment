package Models;

import java.io.Serializable;

public class Funcionario extends Pessoa implements Serializable{
    
    public Funcionario(){}
    
    private String banco;
    private String nrConta;
    private String dataContrato;
    private String cargo;
    private double salario;
    private String senha;

    public Funcionario(String banco, String nrConta, String dataContrato, String cargo, double salario, String senha, String nome, String nomeUsuario, String genero, String id, String residencia, String contacto, String perfil) {
        super(nome, nomeUsuario, genero, id, residencia, contacto, perfil);
        this.banco = banco;
        this.nrConta = nrConta;
        this.dataContrato = dataContrato;
        this.cargo = cargo;
        this.salario = salario;
        this.senha = senha;
    }
    
    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getNrConta() {
        return nrConta;
    }

    public void setNrConta(String nrConta) {
        this.nrConta = nrConta;
    }

    public String getDataContrato() {
        return dataContrato;
    }

    public void setDataContrato(String dataContrato) {
        this.dataContrato = dataContrato;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return super.toString()+ "\n"+ "banco: " + banco + "\n"+ "nr.Conta: " + nrConta + 
        "\n"+"data Contrato: " + dataContrato + "\n"+"cargo: " + cargo + "\n"+"salario: " + salario +"\n\n\n";
    }    
}
