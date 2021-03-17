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

        boolean found = false;
        if (Files.exists(Paths.get("src/Files/Gestor.dat"))) {
            if (!gestor.isEmpty()) {
                try {
                    int i = 0;
                    for (i = 0; i < gestor.size(); i++) {
                        if (username.equalsIgnoreCase(gestor.get(i).getNomeUsuario())) {
                            found = true;
                            break;
                        }
                    }
                    if (found){
                        gestor.get(i).setSenha(senha);
                    }
                    guardar(gestor);
                }catch(IOException e){
                }
            }
        }
        return found;
    }

    
    public static boolean isGestor(ArrayList<Gestor> gestor, String username, char[] jPassField){      
        boolean matches = false;
        
        String concat ="";
        for (int i = 0; i<jPassField.length; i++){
            concat +=jPassField[i];
        }
       
        if(Files.exists(Paths.get("src/Files/Gestor.dat"))){
            if(!gestor.isEmpty()){
                try{
                    for(int i = 0; i<gestor.size(); i++){
                        if(username.equalsIgnoreCase(gestor.get(i).getNomeUsuario()) && concat.equals(gestor.get(i).getSenha())){
                            matches = true;
                        }
                    }
                }catch(Exception e){
                }
            }
        }
        return matches;
    }
    
    public static int editar(ArrayList<Gestor> gestor, String key){
        
        int procurado = -1;
        if(Files.exists(Paths.get("src/Files/Gestor.dat"))){
            if(!gestor.isEmpty()){
                for (int i = 0; i< gestor.size(); i++){
                    if (key.equalsIgnoreCase(gestor.get(i).getNome()) || key.equalsIgnoreCase(gestor.get(i).getId())){
                        procurado = i;
                        break;
                    }
                }
            }
        }
        return procurado;
    }
    
    public static boolean apagar(ArrayList<Gestor> gestor, String key){

        boolean found = false;
        if (Files.exists(Paths.get("src/Files/Gestor.dat"))) {
            if (!gestor.isEmpty()) {
                try {
                    Gestor procurado;
                    for (int i = 0; i < gestor.size(); i++) {
                        if (key.equalsIgnoreCase(gestor.get(i).getId())) {
                            found = true;
                            procurado = gestor.get(i);
                            gestor.remove(procurado);                         
                            break;
                        }
                    }
                    guardar(gestor);
                } catch (HeadlessException | IOException e) {
                }
            }
        }
        return found;
    }
    
    public static Gestor procurar(ArrayList<Gestor> gestor, String key){

        Gestor procurado = null;
        if (Files.exists(Paths.get("src/Files/Gestor.dat"))) {
            if (!gestor.isEmpty()) {
                for (int i = 0; i < gestor.size(); i++) {
                    if (key.equalsIgnoreCase(gestor.get(i).getId())) {
                        procurado = gestor.get(i);
                        break;
                    }
                }
            }
        }
        return procurado;
    }
}