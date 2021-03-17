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


public class GuardarFuncionario {
 
    public static void guardar (ArrayList <Funcionario> funcionario ) throws IOException{
        
        if (!funcionario.isEmpty()){
            try(FileOutputStream fos = new FileOutputStream("src/Files/Funcionario.dat")){
                try (ObjectOutputStream oos = new ObjectOutputStream(fos)){
                    oos.writeObject(funcionario);
                    oos.flush();
                    oos.close();
                    fos.flush();
                    fos.close();
                }
            }
        }
    }
    
    public static ArrayList<Funcionario> mostrar () throws IOException, ClassNotFoundException{
        
        try (FileInputStream fis = new FileInputStream("src/Files/Funcionario.dat")){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                return (ArrayList<Funcionario>) ois.readObject();
            }
        }
    }
    
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
                    guardar(funcionario);
                } catch (HeadlessException | IOException e) {
                }
            }
        }
    }
    
    public static boolean isFuncionario(ArrayList<Funcionario> funcionario, String username, char[] jPassField){
        
        boolean matches = false;
        
        String concat ="";
        for (int i = 0; i<jPassField.length; i++){
            concat +=jPassField[i];
        }
       
        if(Files.exists(Paths.get("src/Files/Funcionario.dat"))){
            if(!funcionario.isEmpty()){
                try{
                    for(int i = 0; i<funcionario.size(); i++){
                        if(username.equalsIgnoreCase(funcionario.get(i).getNomeUsuario()) && concat.equals(funcionario.get(i).getSenha())){
                            matches = true;
                        }
                    }
                }catch(Exception e){
                }
            }
        }
        return matches;
    }
    
    public static int editar(ArrayList<Funcionario> funcionario, String key){
        
        int procurado = -1;
        if(Files.exists(Paths.get("src/Files/Funcionario.dat"))){
            if(!funcionario.isEmpty()){
                for (int i = 0; i< funcionario.size(); i++){
                    if (key.equalsIgnoreCase(funcionario.get(i).getNome()) || key.equalsIgnoreCase(funcionario.get(i).getId())){
                        procurado = i;
                        break;
                    }
                }
            }
        }
        return procurado;
    }
    
    public static Funcionario procurar(ArrayList<Funcionario> funcionario, String nome){

        Funcionario procurado = null;
        if (Files.exists(Paths.get("src/Files/Funcionario.dat"))) {
            if (!funcionario.isEmpty()) {
                for (int i = 0; i < funcionario.size(); i++) {
                    if (nome.equalsIgnoreCase(funcionario.get(i).getNome())|| nome.equalsIgnoreCase(funcionario.get(i).getId())) {
                        procurado = funcionario.get(i);
                        break;
                    }
                }
            }
        }
        return procurado;
    }
}
