package View;

import Controller.GuardarGestor;
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
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class ChangePassForm extends JFrame implements ActionListener {
    
    JLabel user = new JLabel("Nome do usuario/ID");
    JLabel senha = new JLabel("Senha Actual");
    JLabel senhaNova = new JLabel("Nova Senha");
    JLabel confirmarNova = new JLabel("Confirme Nova");
    
    JTextField userField = new JTextField();
    JPasswordField senhaField = new JPasswordField();
    JPasswordField novaField = new  JPasswordField();
    JPasswordField confirmarField = new JPasswordField();
    
    JButton confirmar = new JButton("Confirmar");
    JButton cancelar = new JButton("Cancelar");
    JCheckBox show = new JCheckBox("Mostrar");
    
    JPanel painel = new JPanel();
    JLabel dados = new JLabel("ALTERACAO DA SENHA");
    
    public ChangePassForm(){
        
        setTitle("Alterar Senha");
        setVisible(true);
        setPreferredSize(new Dimension(650,250));
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setLayout(null);
        
        user.setBounds(30,50,150,20);
        add(user);
        userField.setBounds(150,50,150,20);
        add(userField);
        
        senha.setBounds(315,50,100,20);
        add(senha);
        senhaField.setBounds(400,50,150,20);
        add(senhaField);
        
        senhaNova.setBounds(30,90,100,20);
        add(senhaNova);
        novaField.setBounds(150,90,150,20);
        add(novaField);
        
        confirmarNova.setBounds(315,90,100,20);
        add(confirmarNova);
        confirmarField.setBounds(400,90,150,20);
        add(confirmarField);
        
        show.setBounds(550,90,100,20);
        add(show);
        
        confirmar.setBackground(new Color(134,134,144 ));
        confirmar.setBounds(130,170,170,30);
        add(confirmar);
        
        cancelar.setBackground(new Color(134,134,144 ));
        cancelar.setBounds(315,170,170,30);
        add(cancelar);
        
        dados.setBounds(255,10,250,30);
        add(dados);
        
        painel.setBackground(Color.gray);
        painel.setBounds(40,10,555,30);
        add(painel);
          
        confirmar.addActionListener(this);
        cancelar.addActionListener(this);
        show.addActionListener(this);
        pack();
        setLocationRelativeTo(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== confirmar){
            ArrayList <Gestor> gestor = new ArrayList<>();
            if(Files.exists(Paths.get("src/Files/Gestor.dat"))){
                try{
                    if(!GuardarGestor.mostrar().isEmpty()){
                        gestor= GuardarGestor.mostrar();
                    }  
                    if(GuardarGestor.isGestor(gestor, userField.getText(), senhaField.getPassword()) && 
                        Metodos.matches(novaField.getPassword(), confirmarField.getPassword())){
                        gestor.get(GuardarGestor.editar(gestor, userField.getText())).setSenha(Metodos.password(confirmarField.getPassword()));
                        GuardarGestor.guardar(gestor);
                        JOptionPane.showMessageDialog(null, "Senha Alterada");
                        dispose();
                        new MenuGestor().Settings();
                    }else{
                        System.out.println(GuardarGestor.isGestor(gestor, userField.getText(), senhaField.getPassword()));
                        System.out.println(Metodos.matches(novaField.getPassword(), confirmarField.getPassword()));
                        JOptionPane.showMessageDialog(null, "Operacao sem sucesso","ERRO",JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(ChangePassForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else if(ae.getSource() == cancelar){
            dispose();
            try {
                new MenuGestor().Settings();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ChangePassForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(ae.getSource() == show){
            if (show.isSelected()){
                novaField.setEchoChar((char)0);
                confirmarField.setEchoChar((char)0);
            }else{
                novaField.setEchoChar((char)'*');
                confirmarField.setEchoChar((char)'*');
            }
        }
    }

}
