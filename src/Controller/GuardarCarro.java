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

/**
 * Classe controller do objecto carro
 * @author Mr. Savagery
 */
public class GuardarCarro implements Serializable {
    
    /**
     * Metodo que guarda objectos de Carros
     * @param carros recebe um arraylist do objecto Carros
     */
    public static boolean guardar (ArrayList <Carros> carros ) throws IOException{
        
        if (!carros.isEmpty()){
            try(FileOutputStream fos = new FileOutputStream("src/Files/Carros.dat")){
                try (ObjectOutputStream oos = new ObjectOutputStream(fos)){
                    oos.writeObject(carros);
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
     * Metodo de Leitura
     * @return os objectos de Carros num arraylist
     */
    public static ArrayList<Carros> mostrar () throws IOException, ClassNotFoundException{
        
        try (FileInputStream fis = new FileInputStream("src/Files/Carros.dat")){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                return (ArrayList<Carros>) ois.readObject();
            }
        }
    } 
    
    /**
     * Metodo que elimina determinado objecto Carros
     * @param carros recebe um arraylist do objecto carros
     * @param chassi identificador usadio para identificar o objecto a ser eliminado
     */
    public static void apagar(ArrayList<Carros> carros, String chassi){

        boolean found = false;
        if (Files.exists(Paths.get("src/Files/Carros.dat"))) {
            if (!carros.isEmpty()) {
                try {
                    Carros procurado;
                    for (int i = 0; i < carros.size(); i++) {
                        if (chassi.equalsIgnoreCase(carros.get(i).getChassi())) {
                            found = true;
                            procurado = carros.get(i);
                            carros.remove(procurado);
                            JOptionPane.showMessageDialog(null, "Veiculo Removido","INFO",JOptionPane.PLAIN_MESSAGE);
                            if(!guardar(carros))
                                Files.delete(Paths.get("src/Files/Carros.dat"));
                            break;
                        }
                    }
                    if (!found){
                        JOptionPane.showMessageDialog(null, "Veiculo Nao encontrado","INFO",JOptionPane.PLAIN_MESSAGE);
                    }
                } catch (HeadlessException | IOException e) {
                }
            }
        }
    }

    /**
     * Metodo de pesquisa
     * @param carros carros recebe um arraylist do objecto carros
     * @param marca idenificador que e usado como chave de busca
     * @return o objecto de carros procurado caso exista 
     */
    public static Carros procurar(ArrayList<Carros> carros, String marca){

        if (Files.exists(Paths.get("src/Files/Carros.dat"))) {
            if (!carros.isEmpty()) {
                try {
                    for (int i = 0; i < carros.size(); i++) {
                        if (marca.equalsIgnoreCase(carros.get(i).getMarca())||marca.equalsIgnoreCase(carros.get(i).getModelo())){
                            return carros.get(i);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        return null;
    }
    
    
    public static boolean uniqueChassi(ArrayList<Carros> carros, String chassi){

        if (Files.exists(Paths.get("src/Files/Carros.dat"))) {
            if (!carros.isEmpty()) {
                try {
                    for (int i = 0; i < carros.size(); i++) {
                        if (chassi.equalsIgnoreCase(carros.get(i).getChassi())){
                            return false;
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        return true;
    }
    
    /**
     * Metodo que verifica a existencia de um determinado objecto Carros para sua posterior venda
     * @param car recebe um arraylist do objecto carros
     * @param marca idenificador que e usado como chave de busca
     * @param modelo idenificador que e usado como chave de busca
     * @param chassi idenificador que e usado como chave de busca
     * @return a posicao do objecto se encontrado, caso contrario retorna null
     */
    public static int podeVender(ArrayList<Carros> car, String marca, String modelo, String chassi){
        
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
    
    /**
     * Metodo que confirma a venda de um veiculo removendo-o da lista dos disponiveis
     * @param car recebe um arraylist do objecto carros
     * @param chassi idenificador que e usado como chave de busca
     */
    public static void vendido(ArrayList<Carros> car, String chassi) throws IOException{
        
        if(Files.exists(Paths.get("src/Files/Carros.dat"))){
            if(!car.isEmpty()){
                for (int i = 0; i< car.size(); i++){
                    if (chassi.equalsIgnoreCase(car.get(i).getChassi())){
                        car.remove(car.get(i));
                        break;
                    }
                }if(!guardar(car))
                    Files.delete(Paths.get("src/Files/Carros.dat"));
            }
        }
    }
}
