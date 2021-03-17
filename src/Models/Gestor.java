package Models;

import java.io.Serializable;


public class Gestor extends Pessoa implements Serializable {
    
    private String senha;
    
    public Gestor(){    
    }

    public Gestor(String senha, String nome, String nomeUsuario, String genero, String id, String residencia, String contacto, String perfil) {
        super(nome, nomeUsuario, genero, id, residencia, contacto, perfil);
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return super.toString()+ "\n"+ "Senha= "+ senha;
    }
}
