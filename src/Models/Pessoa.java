package Models;

import java.io.Serializable;


public abstract class Pessoa implements Serializable{
    
    
    public Pessoa(){}
    
    private String nome;
    private String nomeUsuario;
    private String genero;
    private String id;
    private String residencia;
    private String contacto;
    private String perfil;
    
    public Pessoa(String nome, String nomeUsuario, String genero, String id, String residencia, String contacto, String perfil) {
        this.nome = nome;
        this.nomeUsuario = nomeUsuario;
        this.genero = genero;
        this.id = id;
        this.residencia = residencia;
        this.contacto = contacto;
        this.perfil = perfil;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResidencia() {
        return residencia;
    }

    public void setResidencia(String residencia) {
        this.residencia = residencia;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "\n"+"nome: " + nome + "\n"+"nome do Usuario: " + nomeUsuario + "\n"+"genero: " + genero + "\n"+"id: " + id + "\n"+"residencia: " + residencia + 
                "\n"+"contacto: " + contacto + "\n"+"perfil: " + perfil;
    }
}
