package Controller;

import Models.Salarios;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class GuardarSalarios implements Serializable {
    
    public static boolean guardar (ArrayList <Salarios> salarios ) throws IOException{
        
        if (!salarios.isEmpty()){
            try(FileOutputStream fos = new FileOutputStream("src/Files/Salarios.dat")){
                try (ObjectOutputStream oos = new ObjectOutputStream(fos)){
                    oos.writeObject(salarios);
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
    
      public static ArrayList<Salarios> mostrar () throws IOException, ClassNotFoundException{
        
        try (FileInputStream fis = new FileInputStream("src/Files/Salarios.dat")){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                return (ArrayList<Salarios>) ois.readObject();
            }
        }
    } 
}
