package Controller;

import Models.Carros;
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
    
    public static void guardar (ArrayList <Carros> carros ) throws IOException{
        
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
     
    public static ArrayList<Carros> mostrar () throws IOException, ClassNotFoundException{
        
        try (FileInputStream fis = new FileInputStream("src/Files/Carros.dat")){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                return (ArrayList<Carros>) ois.readObject();
            }
        }
    } 
    
    public static void apagar(ArrayList<Carros> carros, String modelo){

        boolean found = false;
        if (Files.exists(Paths.get("src/Files/Carros.dat"))) {
            if (!carros.isEmpty()) {
                try {
                    Carros procurado;
                    for (int i = 0; i < carros.size(); i++) {
                        if (modelo.equalsIgnoreCase(carros.get(i).getModelo())) {
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

    public static Carros procurar(ArrayList<Carros> carros, String marca){

        Carros procurado = null;
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
    
    public static int podeVender(ArrayList<Carros> car, String marca, String modelo, int quantidade){
        
        int procurado = -1;
        if(Files.exists(Paths.get("src/Files/Carros.dat"))){
            if(!car.isEmpty()){
                for (int i = 0; i< car.size(); i++){
                    if (marca.equalsIgnoreCase(car.get(i).getMarca()) && modelo.equalsIgnoreCase(car.get(i).getModelo()) &&
                    quantidade<=car.get(i).getQuantidade()){
                        procurado = i;
                        break;
                    }
                }
            }
        }
        return procurado;
    }
    
    public static void vendido(ArrayList<Carros> car, String marca, String modelo, int quantidade) throws IOException{
        
        if(Files.exists(Paths.get("src/Files/Carros.dat"))){
            if(!car.isEmpty()){
                for (int i = 0; i< car.size(); i++){
                    if (marca.equalsIgnoreCase(car.get(i).getMarca()) && modelo.equalsIgnoreCase(car.get(i).getModelo()) &&
                    quantidade<=car.get(i).getQuantidade()){
                        int novaQuant = car.get(i).getQuantidade() - quantidade;
                        car.get(i).setQuantidade(novaQuant);
                        break;
                    }
                }guardar(car);
            }
        }
    }
}
