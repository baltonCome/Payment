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

/**
 * Classe de Controle do Objecto Vendas
 * @author Mr. Savagery
 */
public class GuardarVenda {
    
    /**
     * Metodo que guarda objectos de Salario
     * @param vendas arraylist dp objecto Vendas a ser guardado
     */
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
    
    /**
     * Metodo que exibe um arraylist dos objectos de Vendas
     * @return um arraylist dos objectos de Vendas
     */
    public static ArrayList<Vendas> mostrar () throws IOException, ClassNotFoundException{
        
        try (FileInputStream fis = new FileInputStream("src/Files/Vendas.dat")){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                return (ArrayList<Vendas>) ois.readObject();
            }
        }
    }
    
    /**
     * Metodo que procura objectos de Vendas que tenham sido efectuadas por determinado vendedor
     * @param venda recebe um arraylist dos objectos de Vendas
     * @param username recebe um identificador que sera usado como chave para comparar o nome do Vendedor
     * @return os objectos procurados se encontrados, caso contrario retorna null
     */
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
