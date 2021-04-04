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


public class GuardarGestor {
    
    public static void guardar (ArrayList <Gestor> gestor ) throws IOException{
        
        if (!gestor.isEmpty()){
            try(FileOutputStream fos = new FileOutputStream("src/Files/Gestor.dat")){
                try (ObjectOutputStream oos = new ObjectOutputStream(fos)){
                    oos.writeObject(gestor);
                    oos.flush();
                    oos.close();
                    fos.flush();
                    fos.close();
                }
            }
        }
    }
    
    public static ArrayList<Gestor> mostrar () throws IOException, ClassNotFoundException{
        
        try (FileInputStream fis = new FileInputStream("src/Files/Gestor.dat")){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                return (ArrayList<Gestor>) ois.readObject();
            }
        }
    }
    
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
    
    public static boolean apagar(ArrayList<Gestor> gestor, String key){

        if (Files.exists(Paths.get("src/Files/Gestor.dat"))) {
            if (!gestor.isEmpty()) {
                try {
                    for (int i = 0; i < gestor.size(); i++) {
                        if (key.equalsIgnoreCase(gestor.get(i).getId())) {
                            gestor.remove(gestor.get(i));         
                            guardar(gestor);
                            return true;
                        } 
                    }
                } catch (HeadlessException | IOException e) {
                }
            }
        }
        return false;
    }
    
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