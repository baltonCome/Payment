package Controller;

import Models.Funcionario;
import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Classe de controle do objecto funcionario
 * @author Mr. Savagery
 */
public class GuardarFuncionario {
 
    /**
     * Metodo que guarda objectos de Funcionario
     * @param funcionario arraylist dp objecto Funcionario a ser guardado
     */
    public static boolean guardar (ArrayList <Funcionario> funcionario ) throws IOException{
        
        if (!funcionario.isEmpty()){
            try(FileOutputStream fos = new FileOutputStream("src/Files/Funcionario.dat")){
                try (ObjectOutputStream oos = new ObjectOutputStream(fos)){
                    oos.writeObject(funcionario);
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
     * Metodo que exibe um arraylist dos objectos de Funcionario
     * @return um arraylist dos objectos de Funcionario
     * 
     */
    public static ArrayList<Funcionario> mostrar () throws IOException, ClassNotFoundException{
        
        try (FileInputStream fis = new FileInputStream("src/Files/Funcionario.dat")){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                return (ArrayList<Funcionario>) ois.readObject();
            }
        }
    }
    
    /**
     * Metodo que elimina determinado objecto Funcionario
     * @param funcionario recebe um arraylist dos objectos de Funcionario
     * @param username recebe um identificador que sera usado como chave para eliminar determinado objecto
     */
    public static void apagar(ArrayList<Funcionario> funcionario, String username){

        boolean found = false;
        if (Files.exists(Paths.get("src/Files/Funcionario.dat"))) {
            if (!funcionario.isEmpty()) {
                try {
                    Funcionario procurado;
                    for (int i = 0; i < funcionario.size(); i++) {
                        if (username.equalsIgnoreCase(funcionario.get(i).getNomeUsuario())||username.equalsIgnoreCase(funcionario.get(i).getId())) {
                            found = true;
                            procurado = funcionario.get(i);
                            funcionario.remove(procurado);
                            break;
                        }
                    }
                    if (!found){
                        JOptionPane.showMessageDialog(null, "Funcionario Nao encontrado","INFO",JOptionPane.PLAIN_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "Funcionario Eliminado","INFO",JOptionPane.PLAIN_MESSAGE);
                    }
                    if(!guardar(funcionario))
                        Files.delete(Paths.get("src/Files/Funcionario.dat"));
                } catch (HeadlessException | IOException e) {
                }
            }
        }
    }
    
    /**
     * Metodo que verifica se os dados inseridos correspondem a um determinado objecto Funcionario
     * @param funcionario recebe um arraylist dos objectos de Funcionario
     * @param username recebe um identificador que sera usado como chave para comparar o nome do usuario
     * @param jPassField recebe um identificador que sera usado como chave para comparar a senha
     * @return o valor booleano da funcao, se os dados inseridos correspondem a dados de um objecto ja existente
     */
    public static boolean isFuncionario(ArrayList<Funcionario> funcionario, String username, char[] jPassField){
        
        String concat ="";
        for (int i = 0; i<jPassField.length; i++){
            concat +=jPassField[i];
        }
       
        if(Files.exists(Paths.get("src/Files/Funcionario.dat"))){
            if(!funcionario.isEmpty()){
                try{
                    for(int i = 0; i<funcionario.size(); i++){
                        if(username.equalsIgnoreCase(funcionario.get(i).getNomeUsuario()) && concat.equals(funcionario.get(i).getSenha())){
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
     * Metodo que edita dados de um objecto Funcionario
     * @param funcionario recebe um arraylist dos objectos de Funcionario
     * @param key recebe um identificador que sera usado como chave para comparar o nome ou id do usuario 
     * @return a posicao no arraylist do objecto procurado se encontrado
     */
    public static int editar(ArrayList<Funcionario> funcionario, String key){
        
        if(Files.exists(Paths.get("src/Files/Funcionario.dat"))){
            if(!funcionario.isEmpty()){
                for (int i = 0; i< funcionario.size(); i++){
                    if (key.equalsIgnoreCase(funcionario.get(i).getNome()) || key.equalsIgnoreCase(funcionario.get(i).getId())){
                        return i;
                    }
                }
            }
        }
        return -1;
    }
    
    /**
     * Metodo que procura um determinado objecto de Funcionario
     * @param funcionario recebe um arraylist dos objectos de Funcionario
     * @param nome recebe um identificador que sera usado como chave para comparar o nome
     * @return o objecto procurado se encontrado, caso contrario retorna null
     */
    public static Funcionario procurar(ArrayList<Funcionario> funcionario, String nome){

        if (Files.exists(Paths.get("src/Files/Funcionario.dat"))) {
            if (!funcionario.isEmpty()) {
                for (int i = 0; i < funcionario.size(); i++) {
                    if (nome.equalsIgnoreCase(funcionario.get(i).getNome())|| nome.equalsIgnoreCase(funcionario.get(i).getId())) {
                        return funcionario.get(i);
                    }
                }
            }
        }
        return null;
    }
}
