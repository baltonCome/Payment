package ModelsDao;

import Models.Carro;
import Models.Salario;
import Models.Venda;
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


public class Metodos {
    
    public static String currentUser = "";
    
    ArrayList<Carro> carro = new ArrayList<>();
    
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
    
        
    public static void relatorioVendas(ArrayList<Venda> venda, String titulo) throws IOException{
        
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
    
    public static void relatorioStock(ArrayList<Carro> carro, String titulo) throws IOException{
        
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
  
    public static void open(String titulo) throws IOException{
        
        String[] open = new String[2];
        open[0] = "notepad.exe";
        open[1] = "src/Files/"+titulo+".txt";
        
        Runtime rt = Runtime.getRuntime();
        rt.exec(open,null);
    }
    
    public static String funcId (String perfil, String cargo) throws IOException{
        return perfil.substring(0,1).toUpperCase()+cargo.substring(0,1).toUpperCase()+codigo();
    }
    
    public static String gestID (String perfil) throws IOException{
        return perfil.substring(0,1).toUpperCase()+codigo();
    }
    
    public static String vendaId(String marca, String modelo) throws IOException{
        return marca.substring(0,1).toUpperCase()+modelo.substring(0,1).toUpperCase()+codigo();
    }
    
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
    
    public static String password(char[] jPassField){
        
        String concat ="";
        for (int i = 0; i<jPassField.length; i++){
            concat +=jPassField[i];
        }
        return concat;
    }
    
    public static Boolean matches(char[] pass1, char[] pass2){
        
        String password1 = "";
        String password2 = "";
        
        for(int i = 0; i<pass1.length; i++){
            password1 +=pass1[i];
        }
        for(int i = 0; i<pass2.length; i++){
            password2 +=pass2[i];
        }
        return password1.equals(password2);
    }
    
    public static double bonus(double salario){
        return (salario/3);
    }
    
    public static double faltas(int faltas, double salario){
        return -(faltas*(salario/30));
    }
    
    public static double horasExtras(int horas, double salario){
        return (horas*(salario/220));
    }
    
    public static double funeral(double salario){
        return -((salario*2)/100);
    }
    
    public static double medica(double salario){
        return -((salario*2)/100);
    }
    
    public static double aposentacao(double salario){
        return -((salario*3)/100);
    }
    
    public static double irps(double salario){
        return -((salario*15)/100);
    }
    
    public static String chassiGen(String marca, String modelo, String ano, String cor) throws IOException{
        return marca.substring(0,2).toUpperCase()+modelo.substring(modelo.length()-2, modelo.length()).toUpperCase()
                +ano+cor.substring(modelo.length()-2,modelo.length()).toUpperCase()+codigo();
    }
}