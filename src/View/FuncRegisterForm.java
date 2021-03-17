package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import Models.Funcionario;
import Controller.GuardarFuncionario;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ModelsDao.Metodos;


public class FuncRegisterForm extends JFrame implements ActionListener{
    
    
    JLabel nome = new JLabel("Nome Completo: ");
    JTextField nomeField = new JTextField();
    
    JLabel username = new JLabel("Nome do Usuario: ");
    JTextField usernameField = new JTextField();
    
    JLabel morada = new JLabel("Morada: ");
    JTextField moradaField = new JTextField();
    
    String [] generos = {"Masculino", "Feminino"};
    JLabel genero = new JLabel("Genero");
    JComboBox <String> generoBox = new JComboBox<>(generos);
    
    JLabel banco = new JLabel("Banco");
    String [] bancos = {"Millenium Bim", "BCI","Standard Bank", "FNB"};
    JComboBox <String> bancoBox = new JComboBox<>(bancos);
    
    JLabel conta = new JLabel("Nr. Conta:");
    JTextField contaField = new JTextField();
    
    JLabel senha = new JLabel("Senha: ");
    JPasswordField senhaField = new JPasswordField();
    
    JLabel confirmarSenha = new JLabel("Confirmar: ");
    JPasswordField confirmarSenhaField = new JPasswordField();
    
    JCheckBox show = new JCheckBox("Mostrar");
    
    JLabel contacto = new JLabel("Contacto: ");
    JTextField contactoField = new JTextField();
    
    String [] cargos = {null, "Vendedor", "Tecnico de T.I", "Seguranca","Faxineiro"};
    JLabel cargo = new JLabel("Cargo");
    JComboBox <String> cargoBox = new JComboBox<>(cargos);
    
    JPanel painel = new JPanel();
    JLabel dados = new JLabel("DADOS PESSOAIS");
    
    JPanel painel2 = new JPanel();
    JLabel funcao = new JLabel("FUNCAO");
    
    JButton registar = new JButton("Registar");
    JButton cancelar = new JButton("Cancelar");
    
    public FuncRegisterForm(){
        
        setTitle("Registo");
        setVisible(true);
        setPreferredSize(new Dimension(650,325));
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setLayout(null);
        
        nome.setBounds(30,50,100,20);
        add(nome);
        nomeField.setBounds(150,50,150,20);
        add(nomeField);
        
        genero.setBounds(325,50,100,20);
        add(genero);
        generoBox.setBounds(400,50,150,20);
        add(generoBox);
        
        contacto.setBounds(30,75,100,20);
        add(contacto);
        contactoField.setBounds(150,75,150,20);
        add(contactoField);
        
        morada.setBounds(325,75,100,20);
        add(morada);
        moradaField.setBounds(400,75,150,20);
        add(moradaField);
        
        banco.setBounds(30,100,100,20);
        add(banco);
        bancoBox.setBounds(150,100,150,20);
        add(bancoBox);
        
        conta.setBounds(325,100,100,20);
        add(conta);
        contaField.setBounds(400,100,150,20);
        add(contaField);
        
        cargo.setBounds(30,170,130,20);
        add(cargo);
        cargoBox.setBounds(150,170,150,20);
        add(cargoBox);
        
        senha.setBounds(325,170,100,20);
        add(senha);
        senhaField.setBounds(400,170,150,20);
        add(senhaField);
        
        show.setBounds(560,170,75,20);
        add(show);
        
        confirmarSenha.setBounds(325,200,200,20);
        add(confirmarSenha);
        confirmarSenhaField.setBounds(400,200,150,20);
        add(confirmarSenhaField);
        
        username.setBounds(30,200,130,20);
        add(username);
        usernameField.setBounds(150,200,150,20);
        add(usernameField);
        
        registar.setBackground(new Color(134,134,144 ));
        registar.setBounds(130,250,170,30);
        add(registar);
        
        cancelar.setBackground(new Color(134,134,144 ));
        cancelar.setBounds(315,250,170,30);
        add(cancelar);

        funcao.setBounds(275,130,150,30);
        add(funcao);
        
        dados.setBounds(255,10,120,30);
        add(dados);
        
        painel2.setBackground(Color.gray);
        painel.setBackground(Color.gray);
        painel.setBounds(40,10,555,30);
        painel2.setBounds(40,130,555,30);
        add(painel);
        add(painel2);
        
        registar.addActionListener(this);
        cancelar.addActionListener(this);
        cargoBox.addActionListener(this);
        
        pack();
        setLocationRelativeTo(null);
    } 
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== registar){
            if (Metodos.matches(senhaField.getPassword(), confirmarSenhaField.getPassword())){
                if (nomeField.getText().isEmpty() || cargoBox.getSelectedItem()==null || contactoField.getText().isEmpty() || contaField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Certifique-se de ter prienchido todos os campos");
                }else if(cargoBox.getSelectedIndex()== 1 && (senhaField.getPassword().length==0 || username.getText().isEmpty())){
                    JOptionPane.showMessageDialog(null, "Priencha dados para acesso do vendedor ao sistema");
                }else{
                    try{
                        String perfil = "Funcionario";
                        ArrayList <Funcionario> funcionario = new ArrayList<>();
                        if(Files.exists(Paths.get("src/Files/Funcionario.dat"))){   
                            if(!GuardarFuncionario.mostrar().isEmpty()){
                                funcionario = GuardarFuncionario.mostrar();
                            }
                        }
                        Funcionario func = new Funcionario((String)bancoBox.getSelectedItem(),contaField.getText(),
                            new Date(System.currentTimeMillis()).toString(),(String)cargoBox.getSelectedItem(), Metodos.salario((String)cargoBox.getSelectedItem()),
                            Metodos.password(senhaField.getPassword()),nomeField.getText(),usernameField.getText(),(String)generoBox.getSelectedItem(),
                            Metodos.funcId(perfil, (String)cargoBox.getSelectedItem()),moradaField.getText(),contactoField.getText(),perfil);
                        funcionario.add(func);
                        GuardarFuncionario.guardar(funcionario);
                        JOptionPane.showMessageDialog(null, "Funcionario Registado");
                        dispose();
                        new MenuGestor().Funcionarios();
                    }catch (IOException | ClassNotFoundException ex) {
                        Logger.getLogger(FuncRegisterForm.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                }
            }else{
                JOptionPane.showMessageDialog(null, "Senhas nao Correspondentes!");
            }
        }
        else if (ae.getSource() == cancelar){
           dispose();
        }else if (ae.getSource()== cargoBox){
            if (cargoBox.getSelectedIndex()==1){
               senha.setVisible(true);
               senhaField.setVisible(true);
               username.setVisible(true);
               usernameField.setVisible(true);
               show.setVisible(true);
               confirmarSenha.setVisible(true);
               confirmarSenhaField.setVisible(true);
            }else{
               senha.setVisible(false);
               senhaField.setVisible(false);
               username.setVisible(false);
               usernameField.setVisible(false);
               show.setVisible(false);
               confirmarSenha.setVisible(false);
               confirmarSenhaField.setVisible(false);
            }
        }
    }
    
}
