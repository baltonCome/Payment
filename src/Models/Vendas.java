package Models;

import java.io.Serializable;

public class Vendas implements Serializable {
    
    private String idVenda;
    private String nomeCliente;
    private String marcaCarro;
    private String modeloCarro;
    private String anoCarro;
    private String corCarro;
    private double precoVenda;
    private String chassi;
    private String nomeVendedor;
    
    public Vendas(){
    }

    public Vendas(String idVenda, String nomeCliente, String marcaCarro, String modeloCarro,
            String anoCarro, String corCarro, double precoVenda, String chassi, String nomeVendedor) {
        this.idVenda = idVenda;
        this.nomeCliente = nomeCliente;
        this.marcaCarro = marcaCarro;
        this.modeloCarro = modeloCarro;
        this.anoCarro = anoCarro;
        this.corCarro = corCarro;
        this.precoVenda = precoVenda;
        this.chassi = chassi;
        this.nomeVendedor = nomeVendedor;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getMarcaCarro() {
        return marcaCarro;
    }

    public void setMarcaCarro(String marcaCarro) {
        this.marcaCarro = marcaCarro;
    }

    public String getModeloCarro() {
        return modeloCarro;
    }

    public void setModeloCarro(String modeloCarro) {
        this.modeloCarro = modeloCarro;
    }

    public String getAnoCarro() {
        return anoCarro;
    }

    public void setAnoCarro(String anoCarro) {
        this.anoCarro = anoCarro;
    }

    public String getCorCarro() {
        return corCarro;
    }

    public void setCorCarro(String corCarro) {
        this.corCarro = corCarro;
    }

    public double getPrecoCarro() {
        return precoVenda;
    }

    public void setPrecoCarro(double precoCarro) {
        this.precoVenda = precoCarro;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }
    
    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public void setNomeVendedor(String nomeVendedor) {
        this.nomeVendedor = nomeVendedor;
    }

    public String getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(String idVenda) {
        this.idVenda = idVenda;
    }

    @Override
    public String toString() {
        return "ID da Venda: " +idVenda+"\n"+ "Nome do Cliente: " + nomeCliente +"\n"+ "Marca do Carro: " + marcaCarro +"\n"+ "Modelo do Carro: " + modeloCarro + 
                "\n"+"Ano Fabrico: " + anoCarro + "\n"+"Cor Do Carro: " + corCarro + "\n"+"Preco do Carro: " + precoVenda + 
                "\n"+"Chassi: " + chassi + "\n"+"Vendedor: " + nomeVendedor+"\n\n\n";
    }
}
