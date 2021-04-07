package View;

import Controller.GuardarFuncionario;
import Controller.GuardarSalario;
import Models.Funcionario;
import Models.Salario;
import ModelsDao.Metodos;
import static View.MenuGestor.allDone;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Classe da interface grafica do processamento de pagamentos
 * @author Mr. Savagery
 */
public class PayRollForm extends JFrame{
    
    JLabel nome = new JLabel("Nome: ");
    JLabel nomeField = new JLabel();
    
    JLabel id = new JLabel("ID:");
    JLabel idField = new JLabel();
    
    JLabel cargo = new JLabel("Cargo:");
    JLabel cargoField = new JLabel();
    
    JLabel salario = new JLabel("Salario Base:");
    JLabel salarioField = new JLabel();
    
    JLabel banco = new JLabel("Banco:");
    JLabel bancoField = new JLabel();
    
    JLabel conta = new JLabel("Nr. Conta:");
    JLabel contaField = new JLabel();
    
    JLabel horas = new JLabel("Horas Extras");
    JTextField horasField = new JTextField();
    
    JLabel faltas = new JLabel("Faltas");
    JTextField faltasField = new JTextField();
    
    JPanel painel = new JPanel();
    JLabel dados = new JLabel("DADOS DO FUNCIONARIO");
    
    JPanel painel2 = new JPanel();
    JLabel bonus = new JLabel("BONUS/FALTAS");
    
    JButton processar = new JButton("Processar");
    
    Font fonte = new Font("San Serif", Font.BOLD,12);
    
    ArrayList <Funcionario> funcionario = new ArrayList<>();
    
    /**
     * Construtor vazio da classe
     */
    public PayRollForm(){}
      
    /**
     * Contrutor contendo a GUI dos pagamentos
     * @param name recebe o nome do objecto funcionario e coloca na JtextField do nome
     * @param ident recebe o ID do objecto funcionario e coloca na JtextField do ID
     * @param chair recebe o cargo do objecto funcionario e coloca na JtextField do cargo
     * @param salary recebe o salario do objecto funcionario e coloca na JtextField do salario
     * @param bank recebe o banco do objecto funcionario e coloca na JtextField do Banco 
     * @param account recebe o numero da conta do objecto funcionario e coloca na JtextField do Numero da conta
     * @see MenuGestor
     */
    public PayRollForm(String name, String ident,String chair, double salary, String bank, String account){
        
  
        setTitle("Processamento de Salarios");
        setVisible(true);
        setPreferredSize(new Dimension(650,325));
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setLayout(null);
        
        nomeField.setText(name);
        idField.setText(ident);
        cargoField.setText(chair);
        salarioField.setText(""+salary);
        bancoField.setText(bank);
        contaField.setText(account+"");   
        
        nome.setFont(fonte);
        nome.setBounds(30,50,100,20);
        add(nome);
        nomeField.setBounds(110,50,150,20);
        add(nomeField);
        
        id.setFont(fonte);
        id.setBounds(325,50,100,20);
        add(id);
        idField.setBounds(420,50,150,20);
        add(idField);
        
        cargo.setFont(fonte);
        cargo.setBounds(30,75,100,20);
        add(cargo);
        cargoField.setBounds(110,75,150,20);
        add(cargoField);
        
        salario.setFont(fonte);
        salario.setBounds(325,75,100,20);
        add(salario);
        salarioField.setBounds(420,75,150,20);
        add(salarioField);
        
        banco.setFont(fonte);
        banco.setBounds(30,100,100,20);
        add(banco);
        bancoField.setBounds(110,100,150,20);
        add(bancoField);
        
        conta.setFont(fonte);
        conta.setBounds(325,100,100,20);
        add(conta);
        contaField.setBounds(420,100,150,20);
        add(contaField);
        
        horas.setBounds(30,170,130,20);
        add(horas);
        horasField.setBounds(150,170,150,20);
        add(horasField);
        
        faltas.setBounds(325,170,100,20);
        add(faltas);
        faltasField.setBounds(400,170,150,20);
        add(faltasField);
        
        processar.setBackground(new Color(134,134,144 ));
        processar.setBounds(235,220,170,30);
        add(processar);
        
        bonus.setBounds(275,130,150,30);
        add(bonus);
        
        dados.setBounds(255,10,200,30);
        add(dados);
        
        painel2.setBackground(Color.gray);
        painel.setBackground(Color.gray);
        painel.setBounds(40,10,555,30);
        painel2.setBounds(40,130,555,30);
        add(painel);
        add(painel2);
        
        pack();
        setLocationRelativeTo(null);
        
        processar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    ArrayList <Salario> pay = new ArrayList<>();    
                    if(Files.exists(Paths.get("src/Files/Salarios.dat"))){ 
                        if(!GuardarSalario.mostrar().isEmpty()){
                            pay = GuardarSalario.mostrar();
                        }
                    }
                    Salario sal = new Salario(nomeField.getText(), Double.parseDouble(salarioField.getText()), 
                        Metodos.bonus(Double.parseDouble(salarioField.getText())),cargoField.getText(),
                        Metodos.faltas(Integer.parseInt(faltasField.getText()), Double.parseDouble(salarioField.getText())),
                        Metodos.horasExtras(Integer.parseInt(horasField.getText()), Double.parseDouble(salarioField.getText())),
                        Metodos.funeral(Double.parseDouble(salarioField.getText())),
                        Metodos.medica(Double.parseDouble(salarioField.getText())),
                        Metodos.aposentacao(Double.parseDouble(salarioField.getText())),
                        Metodos.irps(Double.parseDouble(salarioField.getText())),
                        Double.parseDouble(salarioField.getText())+
                            Metodos.bonus(Double.parseDouble(salarioField.getText()))+
                            Metodos.funeral(Double.parseDouble(salarioField.getText()))+
                            Metodos.medica(Double.parseDouble(salarioField.getText()))+
                            Metodos.aposentacao(Double.parseDouble(salarioField.getText()))+
                            Metodos.irps(Double.parseDouble(salarioField.getText()))+
                            Metodos.faltas(Integer.parseInt(faltasField.getText()), Double.parseDouble(salarioField.getText()))+
                            Metodos.horasExtras(Integer.parseInt(horasField.getText()), Double.parseDouble(salarioField.getText())));
                    pay.add(sal);
                    GuardarSalario.guardar(pay);                  
                    dispose();
                    JOptionPane.showMessageDialog(null, "Pagamento do "+nomeField.getText()+" Processado"); 
                    ArrayList<Funcionario> funcionario = new ArrayList<>();
                    if (Files.exists(Paths.get("src/Files/Funcionario.dat"))){
                        if (!GuardarFuncionario.mostrar().isEmpty()){
                            funcionario = GuardarFuncionario.mostrar();
                        }
                    }
                    /*I mean, have no doubts SAVAGERY THE GOAT*/
                    if(MenuGestor.allDone == funcionario.size()-1){
                        JOptionPane.showMessageDialog(null, "Todos Pagamentos Proessados");
                        new MenuGestor().Funcionarios();
                    }else{
                        MenuGestor.allDone++;
                        new PayRollForm(funcionario.get(allDone).getNome(), funcionario.get(allDone).getId(),
                        funcionario.get(allDone).getCargo(), funcionario.get(allDone).getSalario(),
                        funcionario.get(allDone).getBanco(), funcionario.get(allDone).getNrConta());
                    }                            
                }catch(IOException | ClassNotFoundException ex){
                    Logger.getLogger(PayRollForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }       
}