package Models;

import java.io.Serializable;


public class Carro implements Serializable {
    
    public Carro(){}
    
    private String marca;
    private String modelo;
    private int ano;
    private String cor;
    private double preco;
    private String chassi;

    public Carro(String marca, String modelo, int ano, String cor, double preco, String chassi) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.preco = preco;
        this.chassi = chassi;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }
    
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "marca: " + marca + "\n"+"modelo: " + modelo + "\n"+"ano: " + ano + 
                "\n"+"cor: " + cor + "\n"+"preco: " + preco +"\n"+ "chassi: "+chassi+ "\n\n\n";
    }
}
