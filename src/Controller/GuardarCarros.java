package Controller;

import Models.Carro;
import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class GuardarCarros implements Serializable {
    
    public static void guardar (ArrayList <Carro> carros ) throws IOException{
        
        if (!carros.isEmpty()){
            try(FileOutputStream fos = new FileOutputStream("src/Files/Carros.dat")){
                try (ObjectOutputStream oos = new ObjectOutputStream(fos)){
                    oos.writeObject(carros);
                    oos.flush();
                    oos.close();
                    fos.flush();
                    fos.close();
                }
            }
        }
    }
     
    public static ArrayList<Carro> mostrar () throws IOException, ClassNotFoundException{
        
        try (FileInputStream fis = new FileInputStream("src/Files/Carros.dat")){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                return (ArrayList<Carro>) ois.readObject();
            }
        }
    } 
    
    public static void apagar(ArrayList<Carro> carros, String chassi){

        boolean found = false;
        if (Files.exists(Paths.get("src/Files/Carros.dat"))) {
            if (!carros.isEmpty()) {
                try {
                    Carro procurado;
                    for (int i = 0; i < carros.size(); i++) {
                        if (chassi.equalsIgnoreCase(carros.get(i).getChassi())) {
                            found = true;
                            procurado = carros.get(i);
                            carros.remove(procurado);
                            JOptionPane.showMessageDialog(null, "Veiculo Removido","INFO",JOptionPane.PLAIN_MESSAGE);
                            break;
                        }
                    }
                    if (!found){
                        JOptionPane.showMessageDialog(null, "Veiculo Nao encontrado","INFO",JOptionPane.PLAIN_MESSAGE);
                    }
                    guardar(carros);
                } catch (HeadlessException | IOException e) {
                }
            }
        }
    }

    public static Carro procurar(ArrayList<Carro> carros, String marca){

        Carro procurado = null;
        if (Files.exists(Paths.get("src/Files/Carros.dat"))) {
            if (!carros.isEmpty()) {
                try {
                    for (int i = 0; i < carros.size(); i++) {
                        if (marca.equalsIgnoreCase(carros.get(i).getMarca())||marca.equalsIgnoreCase(carros.get(i).getModelo())){
                            procurado = carros.get(i);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        return procurado;
    }
    
    public static int podeVender(ArrayList<Carro> car, String marca, String modelo, String chassi){
        
        if(Files.exists(Paths.get("src/Files/Carros.dat"))){
            if(!car.isEmpty()){
                for (int i = 0; i< car.size(); i++){
                    if (modelo.equalsIgnoreCase(car.get(i).getModelo()) && marca.equalsIgnoreCase(car.get(i).getMarca()) &&
                            chassi.equalsIgnoreCase(car.get(i).getChassi())){
                        return i;
                    }
                }
            }
        }
        return -1;
    }
    
    public static void vendido(ArrayList<Carro> car, String chassi) throws IOException{
        
        if(Files.exists(Paths.get("src/Files/Carros.dat"))){
            if(!car.isEmpty()){
                for (int i = 0; i< car.size(); i++){
                    if (chassi.equalsIgnoreCase(car.get(i).getChassi())){
                        car.remove(car.get(i));
                        break;
                    }
                }guardar(car);
            }
        }
    }
}
