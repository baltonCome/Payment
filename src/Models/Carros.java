package Models;

import java.io.Serializable;


public class Carros implements Serializable {
    
    public Carros(){}
    
    private String marca;
    private String modelo;
    private int ano;
    private String cor;
    private double preco;
    private int quantidade;

    public Carros(String marca, String modelo, int ano, String cor, double preco, int quantidade) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.preco = preco;
        this.quantidade = quantidade;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "marca: " + marca + "\n"+"modelo: " + modelo + "\n"+"ano: " + ano + "\n"+"cor: " + cor + "\n"+"preco: " + preco + "\n"+"quantidade: " + quantidade+"\n\n\n";
    }
}
