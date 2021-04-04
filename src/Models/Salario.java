package Models;

import java.io.Serializable;


public class Salario implements Serializable {
    
    String nome;
    private double salarioBase;
    private double bonus;
    private String cargo;
    private double faltas;
    private double horasExtras;
    private double subsidioFuneral;
    private double assistenciaMedica;
    private double aposentacao;
    private double irps;
    private double salarioLiquido;
    
    public Salario(){
    }
    
    public Salario(String nome, double salarioBase, double bonus, String cargo, double faltas, double horasExtras, double subsidioFuneral, double assistenciaMedica, double aposentacao, double irps, double salarioLiquido) {
        this.nome = nome;
        this.salarioBase = salarioBase;
        this.bonus = bonus;
        this.cargo = cargo;
        this.faltas = faltas;
        this.horasExtras = horasExtras;
        this.subsidioFuneral = subsidioFuneral;
        this.assistenciaMedica = assistenciaMedica;
        this.aposentacao = aposentacao;
        this.irps = irps;
        this.salarioLiquido = salarioLiquido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public double getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(int horasExtras) {
        this.horasExtras = horasExtras;
    }

    public double getSubsidioFuneral() {
        return subsidioFuneral;
    }

    public void setSubsidioFuneral(double subsidioFuneral) {
        this.subsidioFuneral = subsidioFuneral;
    }

    public double getAssistenciaMedica() {
        return assistenciaMedica;
    }

    public void setAssistenciaMedica(double assistenciaMedica) {
        this.assistenciaMedica = assistenciaMedica;
    }

    public double getAposentacao() {
        return aposentacao;
    }

    public void setAposentacao(double aposentacao) {
        this.aposentacao = aposentacao;
    }

    public double getIrps() {
        return irps;
    }

    public void setIrps(double irps) {
        this.irps = irps;
    }

    public double getSalarioLiquido() {
        return salarioLiquido;
    }

    public void setSalarioLiquido(double salarioLiquido) {
        this.salarioLiquido = salarioLiquido;
    }

    @Override
    public String toString(){
        return "Nome: "+ nome+ "\n"+ "salario Base: " + salarioBase + "\n"+"bonus: " + bonus + "\n"+"cargo: " + cargo + "\n"+"faltas: " + faltas + 
        "\n"+"horas Extras: " + horasExtras + "\n"+"subsidio Funeral: " + subsidioFuneral + "\n"+"assistencia Medica: " + assistenciaMedica + 
        "\n"+"aposentacao: " + aposentacao + "\n"+"IRPS: " + irps + "\n"+"salario Liquido: " + salarioLiquido+"\n\n\n";
    }
}
