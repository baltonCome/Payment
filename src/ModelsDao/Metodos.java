package ModelsDao;

import Models.Carros;
import Models.Salario;
import Models.Vendas;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import View.Login;

/**
 * Classe de metodos auxiliares do programa
 * @author Mr. Savagery
 */
public class Metodos {
    
    /**
     * Variavel que guarda o usuario enquanto o programa e executado
     * @see Login
     */
    public static String currentUser = "";
    
    ArrayList<Carros> carro = new ArrayList<>();
    
    /**
     * Metodo que funciona como um contador, armazena o contador actual e substitui por contador+1 a cada itineracao
     * Armazena o contador num ficheiro TXT
     * @return o numero + 1 existente no ficheiro TXT 
     */
    public static int codigo() throws IOException{
        
        if(!Files.exists(Paths.get("src/Files/Counter.txt"))){
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/Files/Counter.txt", true));
            bw.write("1");
            bw.flush();
            bw.close();
        }
        
        File actual = new File("src/Files/Counter.txt");
        File next = new File("src/Files/Next.txt");
        
        BufferedReader br = new BufferedReader(new FileReader(actual));
        BufferedWriter bw1 = new BufferedWriter(new FileWriter(next));
        
        String busca ="";
        String line = br.readLine();
        while (line != null){
            busca+=line;
            line = br.readLine();
        }
        int numero = Integer.parseInt(busca);
         
        numero+=1;
        br.close();
        
        bw1.write(numero+"");
        bw1.flush();
        bw1.close();
        
        actual.delete();
        next.renameTo(actual);
        return numero;
    }
    
    /**
     * Metodo que escreve e exibe o ficheiro de vendas efectuadas
     * @param venda arrayList contendo os objectos de Vendas a serem escritos
     * @param titulo reserva o nome do ficheiro
     */
    public static void relatorioVendas(ArrayList<Vendas> venda, String titulo) throws IOException{
        
        String rel = "PaySell\nMaputo\n\n\n"+"\n==================================\n"+String.valueOf(venda)+
                "\n==================================\n"+new Date(System.currentTimeMillis()).toString()+"\nOperador: "+currentUser;
        if(Files.exists(Paths.get("src/Files/"+titulo+".txt"))){
           File f = new File("src/Files/"+titulo+".txt");
           f.delete();
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/Files/"+titulo+".txt",true));
        bw.write(rel);
        bw.flush();
        bw.close();
        
        open(titulo);
    }
    
    /**
     * Metodo que escreve e exibe o ficheiro do relatorio de pagamentos
     * @param salario arrayList contendo os objectos de Salario a serem escritos
     * @param titulo reserva o nome do ficheiro 
     */
    public static void relatorioPagamentos(ArrayList<Salario> salario, String titulo) throws IOException{
        
        String rel = "PaySell\nMaputo\n\n\n"+"\n==================================\n"+String.valueOf(salario)+
                "\n==================================\n"+new Date(System.currentTimeMillis()).toString()+"\nOperador: "+currentUser;
        if(Files.exists(Paths.get("src/Files/"+titulo+".txt"))){
           File f = new File("src/Files/"+titulo+".txt");
           f.delete();
        }
        
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/Files/"+titulo+".txt",true));
        bw.write(rel);
        bw.flush();
        bw.close();
        
        open(titulo);
    }
    
    /**
     * Metodo que escreve e exibe o ficheiro do relatorio de Stock
     * @param carro arrayList contendo os objectos de Carro a serem escritos
     * @param titulo reserva o nome do ficheiro 
     */
    public static void relatorioStock(ArrayList<Carros> carro, String titulo) throws IOException{
        
        String rel = "PaySell\nMaputo\n\n\n"+"\n==================================\n"+String.valueOf(carro)+
                "\n==================================\n"+new Date(System.currentTimeMillis()).toString()+"\nOperador: "+currentUser;
        if(Files.exists(Paths.get("src/Files/"+titulo+".txt"))){
           File f = new File("src/Files/"+titulo+".txt");
           f.delete();
        }
        
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/Files/"+titulo+".txt",true));
        bw.write(rel);
        bw.flush();
        bw.close();
        
        open(titulo);
    }
    
    /**
     * Metodo que escreve e exibe o ficheiro do comprovativo de venda
     * @param venda variavel do objecto de Vendas a ser escrito
     * @param titulo reserva o nome do ficheiro 
     */
    public static void comprovativoVenda (Object venda, String titulo) throws IOException{
        
        String rel = "PaySell\nMaputo\n\n\n"+"\n==================================\n"+String.valueOf(venda)+
                "\n==================================\n"+new Date(System.currentTimeMillis()).toString()+"\nOperador: "+currentUser;
        if(Files.exists(Paths.get("src/Files/"+titulo+".txt"))){
           File f = new File("src/Files/"+titulo+".txt");
           f.delete();
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/Files/"+titulo+".txt",true));
        bw.write(rel);
        bw.flush();
        bw.close();
        
        open(titulo);
    }
  
    /**
     * Metodo responsavel por abrir os ficheiros
     * @param titulo variavel que recebe o nome do ficheiro a ser aberto
     */
    public static void open(String titulo) throws IOException{
        
        String[] open = new String[2];
        open[0] = "notepad.exe";
        open[1] = "src/Files/"+titulo+".txt";
        
        Runtime rt = Runtime.getRuntime();
        rt.exec(open,null);
    }
    
    /**
     * Metodo Responsavel por gerar o ID do Funcionario
     * @param perfil recebe o perfil do funcionario
     * @param cargo recebe o cargo do funcionario
     * @return a primeira letra do perfil, a primeira letra do cargo e o numero itinerado combinados
     */
    public static String funcId (String perfil, String cargo) throws IOException{
        return perfil.substring(0,1).toUpperCase()+cargo.substring(0,1).toUpperCase()+codigo();
    }
    
    /**
     * Metodo Responsavel por gerar o ID do Gestor
     * @param perfil recebe o perfil do gestor
     * @return a combinacao da primeira letra do perfil e o numero itinerado combinados
     */
    public static String gestID (String perfil) throws IOException{
        return perfil.substring(0,1).toUpperCase()+codigo();
    }
    
    /**
     * Metodo responsavel por gerar o ID da venda
     * @param marca recebe a marca do veiculo 
     * @param modelo recebe o modelo do veiculo
     * @return a primeira letra do veiculo, a primeira letra do modelo e o numero itinerado combinados
     */
    public static String vendaId(String marca, String modelo) throws IOException{
        return marca.substring(0,1).toUpperCase()+modelo.substring(0,1).toUpperCase()+codigo();
    }
    
    /**
     * Metodo responsavel por determinar o salario do funcionario
     * @param cargo recebe o cargo do funcionario
     * @return o salario do funcionario de acordo com o seu cargo
     */
    public static int salario(String cargo){
        
        int pagamento=0;
        if (cargo.equalsIgnoreCase("Vendedor")){
            pagamento = 23000;
        }else if(cargo.equalsIgnoreCase("Tecnico de T.I")){
            pagamento = 32000;
        }else if (cargo.equalsIgnoreCase("Seguranca")){
            pagamento = 15000;
        }else if (cargo.equalsIgnoreCase("Faxineiro")){
            pagamento = 9000;
        }
        return pagamento;
    }
    
    /**
     * Metodo responsavel por transformar uma JPassword em uma String
     * @param jPassField recebe o array de chars da JPassword
     * @return a JPassword em String
     */
    public static String password(char[] jPassField){
        
        String concat ="";
        for (int i = 0; i<jPassField.length; i++){
            concat +=jPassField[i];
        }
        return concat;
    }
    
    /**
     * Metodo que verifica se duas Jpassword sao iguais
     * @param pass1 recebe a primeira Jpassword
     * @param pass2 recebe a segunda Jpassword
     * @return o valor logico da comparacao das duas JpassWords
     */
    public static Boolean matches(char[] pass1, char[] pass2){
        
        return password(pass1).equals(password(pass2));
    }
    
    /**
     * Metodo que faz o calculo do bonus no salario
     * @param salario recebe o valor do salario
     * @return o bunus de acordo com o salario
     */
    public static double bonus(double salario){
        return (salario/3);
    }
    
    /**
     * Faz o calcculo do valor a ser descontado pelas faltas
     * @param faltas recebe o numero de faltas
     * @param salario recebe o valor do salario
     * @return o desconto no salario em funcao das faltas
     */
    public static double faltas(int faltas, double salario){
        return -(faltas*(salario/30));
    }
    
    /**
     * Faz o calculo do valor das horas extras
     * @param horas recebe o numero de horas extras
     * @param salario recebe o valor do salario
     * @return o valor das horas extras 
     */
    public static double horasExtras(int horas, double salario){
        return (horas*(salario/220));
    }
    
    /**
     * faz o calculo do valor do subsidio de funeral
     * @param salario recebe o valor do salario
     * @return o valor do subsidio de funeral
     */
    public static double funeral(double salario){
        return -((salario*2)/100);
    }
    
    /**
     * Faz o calculo do valor da assistencia medica
     * @param salario recebe o valor do salario
     * @return o valor da assistencia medica
     */
    public static double medica(double salario){
        return -((salario*2)/100);
    }
    
    /**
     * Faz o calculo do valor da aposentacao
     * @param salario recebe o valor do salario
     * @return o nvalor da aposentacao
     */
    public static double aposentacao(double salario){
        return -((salario*3)/100);
    }
    
    /**
     * Faz o calculo do valor do IRPS
     * @param salario rebe o valor do salario
     * @return o valor do IRPS
     */
    public static double irps(double salario){
        return -((salario*15)/100);
    }
  
}