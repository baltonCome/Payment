package Controller;

import Models.Vendas;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class GuardarVenda {
    
    public static void guardar (ArrayList <Vendas> vendas ) throws IOException{
        
        if (!vendas.isEmpty()){
            try(FileOutputStream fos = new FileOutputStream("src/Files/Vendas.dat")){
                try (ObjectOutputStream oos = new ObjectOutputStream(fos)){
                    oos.writeObject(vendas);
                    oos.flush();
                    oos.close();
                    fos.flush();
                    fos.close();
                }
            }
        }
    }
    
    public static ArrayList<Vendas> mostrar () throws IOException, ClassNotFoundException{
        
        try (FileInputStream fis = new FileInputStream("src/Files/Vendas.dat")){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                return (ArrayList<Vendas>) ois.readObject();
            }
        }
    }
    
    public static String procurar(ArrayList<Vendas> venda, String username){

        String procurado = null;
        if (Files.exists(Paths.get("src/Files/Vendas.dat"))) {
            if (!venda.isEmpty()) {
                try {
                    for (int i = 0; i < venda.size(); i++) {
                        if (username.equalsIgnoreCase(venda.get(i).getNomeVendedor())) {
                            procurado+= venda.get(i)+"\n";
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        return procurado;
    }
}
