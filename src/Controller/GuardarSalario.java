package Controller;

import Models.Salario;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class GuardarSalario implements Serializable {
    
    public static boolean guardar (ArrayList <Salario> salarios ) throws IOException{
        
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
    
      public static ArrayList<Salario> mostrar () throws IOException, ClassNotFoundException{
        
        try (FileInputStream fis = new FileInputStream("src/Files/Salarios.dat")){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                return (ArrayList<Salario>) ois.readObject();
            }
        }
    } 
}
