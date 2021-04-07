package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import Models.Gestor;
import Controller.GuardarGestor;
import ModelsDao.Metodos;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Classe contento a interface grafica do formulario do registo do gestor
 * @author Mr. Savagery
 */
public class GestRegisterForm extends JFrame implements ActionListener{
    
    
    JLabel nome = new JLabel("Nome Completo: ");
    JTextField nomeField = new JTextField();
    
    JLabel username = new JLabel("Nome do Usuario: ");
    JTextField usernameField = new JTextField();
    
    JLabel morada = new JLabel("Morada: ");
    JTextField moradaField = new JTextField();
    
    String [] generos = {"Masculino", "Feminino"};
    JLabel genero = new JLabel("Genero");
    JComboBox <String> generoBox = new JComboBox<>(generos);
    
    JLabel contacto = new JLabel("Contacto: ");
    JTextField contactoField = new JTextField();
    
    JLabel senha = new JLabel("Senha: ");
    JPasswordField senhaField = new JPasswordField();
    
    JLabel confirmarSenha = new JLabel("Confirme a Senha: ");
    JPasswordField confirmarSenhaField = new JPasswordField();
    
    JCheckBox show = new JCheckBox("Mostrar");
    
    JPanel painel = new JPanel();
    JPanel painel2 = new JPanel();
    JLabel dados = new JLabel("DADOS PESSOAIS");
    JLabel autenticacao = new JLabel("AUTENTICACAO");
    
    JButton registar = new JButton("Registar");
    JButton cancelar = new JButton("Cancelar");
    
    /**
     * Construtor contento a GUI com o formulario do registo do gestor
     */
    public GestRegisterForm(){
        
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
        
        contacto.setBounds(30,90,100,20);
        add(contacto);
        contactoField.setBounds(150,90,150,20);
        add(contactoField);
        
        morada.setBounds(325,90,100,20);
        add(morada);
        moradaField.setBounds(400,90,150,20);
        add(moradaField);
        
        username.setBounds(30,170,130,20);
        add(username);
        usernameField.setBounds(150,170,150,20);
        add(usernameField);
        
        senha.setBounds(325,170,100,20);
        add(senha);
        senhaField.setBounds(400,170,150,20);
        add(senhaField);
        
        show.setBounds(560,170,75,20);
        add(show);
        
        confirmarSenha.setBounds(30,200,130,20);
        add(confirmarSenha);
        confirmarSenhaField.setBounds(150,200,150,20);
        add(confirmarSenhaField);
        
        registar.setBackground(new Color(134,134,144 ));
        registar.setBounds(130,250,170,30);
        add(registar);
        
        cancelar.setBackground(new Color(134,134,144 ));
        cancelar.setBounds(315,250,170,30);
        add(cancelar);
        
        painel.setBackground(Color.gray);
        painel.setBounds(40,10,555,30);
        autenticacao.setBounds(260,130,150,30);
        add(autenticacao);
        
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
        show.addActionListener(this);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    /**
     * Metodo contendo todos eventos da classe
     * @param ae como variavel do evento
     */
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== registar){
            if (Metodos.matches(senhaField.getPassword(), confirmarSenhaField.getPassword())){
                if (username.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Certifique-se de ter prienchido todos os campos");
                }else{
                    try{
                    String perfil = "Gestor";
                    ArrayList <Gestor> gestor = new ArrayList<>();
                    if(Files.exists(Paths.get("src/Files/Gestor.dat"))){
                        if(!GuardarGestor.mostrar().isEmpty()){
                            gestor = GuardarGestor.mostrar();
                        }
                    }
                    Gestor gest = new Gestor(Metodos.password(senhaField.getPassword()),nomeField.getText(),usernameField.getText(),
                    (String)generoBox.getSelectedItem(), Metodos.gestID(perfil),moradaField.getText(),contactoField.getText(),perfil);
                    gestor.add(gest);
                    GuardarGestor.guardar(gestor);
                    JOptionPane.showMessageDialog(null, "Novo Gestor Registado");
                    dispose();
                    }catch (IOException | ClassNotFoundException ex) {
                        Logger.getLogger(GestRegisterForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
            }else{
                JOptionPane.showMessageDialog(null, "Senhas nao Correspondentes!");
            }
        }else if (ae.getSource() == cancelar){
            dispose();
        }else if (ae.getSource()== show){
            if (show.isSelected()){
                senhaField.setEchoChar((char)0);
            }else{
                senhaField.setEchoChar((char)'*');
            }
        }
    }
    
}
