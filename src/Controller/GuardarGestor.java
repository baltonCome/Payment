package Controller;

import Models.Gestor;
import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * CLasse de controle do objecto Gestor
 * @author Mr. Savagery
 */
public class GuardarGestor {
    
    /**
     * Metodo que guarda objectos de Gestor
     * @param gestor arraylist dp objecto Gestor a ser guardado
     */
    public static boolean guardar (ArrayList <Gestor> gestor ) throws IOException{
        
        if (!gestor.isEmpty()){
            try(FileOutputStream fos = new FileOutputStream("src/Files/Gestor.dat")){
                try (ObjectOutputStream oos = new ObjectOutputStream(fos)){
                    oos.writeObject(gestor);
                    oos.flush();
                    oos.close();
                    fos.flush();
                    fos.close();
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Metodo que exibe um arraylist dos objectos de Gestor
     * @return um arraylist dos objectos de Gestor
     */
    public static ArrayList<Gestor> mostrar () throws IOException, ClassNotFoundException{
        
        try (FileInputStream fis = new FileInputStream("src/Files/Gestor.dat")){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                return (ArrayList<Gestor>) ois.readObject();
            }
        }
    }
    
    /**
     * Metodo que permite a alteracao da senha de um objecto Gestor
     * @param gestor recebe um arraylist dos objectos de Gestor
     * @param username recebe um identificador que sera usado como chave para comparar o nome do usuario
     * @param senha recebe um identificador que sera usado como chave para comparar a senha
     * @return o valor booleano da funcao, se a senha foi alterada
     */
    public static boolean alterarSenha(ArrayList<Gestor> gestor, String username, String senha) throws IOException{

        if (Files.exists(Paths.get("src/Files/Gestor.dat"))) {
            if (!gestor.isEmpty()) {
                try {
                    for (int i = 0; i < gestor.size(); i++) {
                        if (username.equalsIgnoreCase(gestor.get(i).getNomeUsuario()) || username.equalsIgnoreCase(gestor.get(i).getId())) {
                            gestor.get(i).setSenha(senha);
                            return true;
                        }
                    }
                    guardar(gestor);
                }catch(IOException e){
                }
            }
        }
        return false;
    }

    /**
     * Metodo que verifica se os dados inseridos correspondem a um determinado objecto Gestor
     * @param gestor recebe um arraylist dos objectos de Gestor
     * @param username recebe um identificador que sera usado como chave para comparar o nome do usuario
     * @param jPassField recebe um identificador que sera usado como chave para comparar a senha
     * @return o valor booleano da funcao, se os dados inseridos correspondem a dados de um objecto ja existente
     */
    public static boolean isGestor(ArrayList<Gestor> gestor, String username, char[] jPassField){      
        
        String concat ="";
        for (int i = 0; i<jPassField.length; i++){
            concat +=jPassField[i];
        }

        if(Files.exists(Paths.get("src/Files/Gestor.dat"))){
            if(!gestor.isEmpty()){
                try{
                    for(int i = 0; i<gestor.size(); i++){
                        if((username.equalsIgnoreCase(gestor.get(i).getNomeUsuario()) || username.equalsIgnoreCase(gestor.get(i).getId()))
                                && concat.equals(gestor.get(i).getSenha())){
                            return true;
                        }
                    }
                }catch(Exception e){
                }
            }
        }
        return false;
    }
    
    /**
     * Metodo que edita dados de um objecto Gestor
     * @param gestor recebe um arraylist dos objectos de Gestor
     * @param key recebe um identificador que sera usado como chave para comparar o nome ou id do usuario 
     * @return a posicao no arraylist do objecto procurado se encontrado
     */
    public static int editar(ArrayList<Gestor> gestor, String key){
        
        if(Files.exists(Paths.get("src/Files/Gestor.dat"))){
            if(!gestor.isEmpty()){
                for (int i = 0; i< gestor.size(); i++){
                    if (key.equalsIgnoreCase(gestor.get(i).getNomeUsuario()) || key.equalsIgnoreCase(gestor.get(i).getId())){
                        return i;
                    }
                }
            }
        }
        return -1;
    }
    
    /**
     * Metodo que elimina determinado objecto Gestor
     * @param gestor recebe um arraylist dos objectos de Gestor
     * @param key recebe um identificador que sera usado como chave para eliminar determinado objecto
     * @return o valor booleano da funcao se o objecto tiver sido elimidado
     */
    public static boolean apagar(ArrayList<Gestor> gestor, String key){

        if (Files.exists(Paths.get("src/Files/Gestor.dat"))) {
            if (!gestor.isEmpty()) {
                try {
                    for (int i = 0; i < gestor.size(); i++) {
                        if (key.equalsIgnoreCase(gestor.get(i).getId())) {
                            gestor.remove(gestor.get(i));         
                            if(!guardar(gestor))
                                Files.delete(Paths.get("src/Files/Carros.dat"));
                            return true;
                        } 
                    }
                } catch (HeadlessException | IOException e) {
                }
            }
        }
        return false;
    }
    
    /**
     * Metodo que procura um determinado objecto de Gestor
     * @param gestor recebe um arraylist dos objectos de Gestor
     * @param key recebe um identificador que sera usado como chave para comparar o Id
     * @return o objecto procurado se encontrado, caso contrario retorna null
     */
    public static Gestor procurar(ArrayList<Gestor> gestor, String key){

        if (Files.exists(Paths.get("src/Files/Gestor.dat"))) {
            if (!gestor.isEmpty()) {
                for (int i = 0; i < gestor.size(); i++) {
                    if (key.equalsIgnoreCase(gestor.get(i).getId())) {
                        return gestor.get(i);
                    }
                }
            }
        }
        return null;
    }
}