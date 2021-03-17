package View;

import Controller.GuardarFuncionario;
import Controller.GuardarGestor;
import Models.Funcionario;
import Models.Gestor;
import ModelsDao.Metodos;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Login extends JFrame implements ActionListener{
    
    ArrayList <Gestor> gestor = new ArrayList<>();
    ArrayList <Funcionario> funcionario = new ArrayList<>();
    
    JButton entrar = new JButton("Entrar", new ImageIcon("src/Files/Icons/login.png"));
    JButton cancelar = new JButton("Cancelar", new ImageIcon("src/Files/Icons/cancel.png"));
    JCheckBox show = new JCheckBox("Mostrar");
    JLabel user = new JLabel("Usuario: ");
    JTextField userField = new JTextField();
    JLabel pass = new JLabel("Senha: ");
    JPasswordField passField = new JPasswordField();
    JPanel painel = new JPanel();
    
    public Login(){
        
        setTitle("Login");
        setVisible(true);
        setPreferredSize(new Dimension(400,200));
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setLayout(null);
        
        entrar.setBackground(new Color(134,134,144 ));
        entrar.setBounds(195,120,150,30);
        add(entrar);
        
        cancelar.setBackground(new Color(134,134,144 ));
        cancelar.setBounds(35,120,150,30);
        add(cancelar);
        
        user.setBounds(30,20,70,23);
        add(user);
        
        userField.setBounds(100,20,200,23);
        add(userField);
        
        pass.setBounds(30,70,70,23);
        add(pass);
        
        passField.setBounds(100,70,200,23);
        add(passField);
        
        show.setBounds(300,70,100,23);
        add(show);
        
        painel.setBackground(new Color(156,115,115));
        painel.setBounds(15,5,350,10);
        
        entrar.addActionListener(this);
        show.addActionListener(this);
        cancelar.addActionListener(this);
        pack();
        
        setLocationRelativeTo(null);
        
        if(!Files.exists(Paths.get("src/Files/Gestor.dat"))){        
            JOptionPane.showMessageDialog(null,"Bem Vindo, Registe um Gestor", "INFO", PLAIN_MESSAGE);
            new GestRegisterForm();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== entrar){
            try{
                gestor = GuardarGestor.mostrar();
                if (GuardarGestor.isGestor(gestor, userField.getText(), passField.getPassword())){
                    if(!Files.exists(Paths.get("src/Files/Carros.dat"))){        
                        JOptionPane.showMessageDialog(null,"Bem Vindo "+userField.getText()+
                            ", \npara poder ter acesso a todas as Funcionalidades do sistema, \ncertifique-se de registar um funcionario "+
                            "\ncom o cargo de vendedor para que tenha acesso ao \nsistema e possa efectuar vendas."+
                            "\nRegiste tambem veiculos a serem vendidos!", "PRIMEIRO LOGIN", PLAIN_MESSAGE);
                        dispose();
                    }
                    new MenuGestor().Funcionarios();
                    dispose();
                }
                if(Files.exists(Paths.get("src/Files/Funcionario.dat"))){
                    if(!GuardarFuncionario.mostrar().isEmpty()){
                        funcionario = GuardarFuncionario.mostrar();
                        if(GuardarFuncionario.isFuncionario(funcionario, userField.getText(), passField.getPassword()) &&
                            !userField.getText().isEmpty()){
                            new MenuVendedor().Vendas();
                            dispose();
                        }
                    }
                }
                if(!GuardarGestor.isGestor(gestor, userField.getText(), passField.getPassword()) && 
                        !GuardarFuncionario.isFuncionario(funcionario, userField.getText(), passField.getPassword())){
                    JOptionPane.showMessageDialog(null,"Dados Nao reconhecidos", "ERRO", ERROR_MESSAGE);
                }
                Metodos.currentUser = userField.getText();
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(ae.getSource()== cancelar){
            dispose();
        }else if(ae.getSource()== show){
            if(show.isSelected()){
                passField.setEchoChar((char)0);
            }else{
                passField.setEchoChar((char)'*');
            }
        }
    }
    
    public static void main(String[]args){
        new Login();
    }
}
