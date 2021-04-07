package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Models.Gestor;
import Controller.GuardarGestor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Classe contento a interface grafica do formulario de atualizacao de dados do gestor
 * @author Mr. Savagery
 */
public class GestUpdateForm extends JFrame implements ActionListener {
    
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
       
    JPanel painel = new JPanel();
    JLabel dados = new JLabel("DADOS PESSOAIS");
    
    JButton atualizar = new JButton("Atualizar");
    JButton cancelar = new JButton("Cancelar");
    
    int pos = 0;
    
    /**
     * Contrutor contento a GUI do formulario de atualizacao de dados do gestor
     * @param posicao reserva a posicao do objecto Gestor a ser atualizado
     * @see MenuGestor
     */
    public GestUpdateForm(int posicao){
        
        pos = posicao;
        
        setTitle("Registo");
        setVisible(true);
        setPreferredSize(new Dimension(650,250));
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
        
        atualizar.setBackground(new Color(134,134,144 ));
        atualizar.setBounds(130,170,170,30);
        add(atualizar);
        
        cancelar.setBackground(new Color(134,134,144 ));
        cancelar.setBounds(315,170,170,30);
        add(cancelar);

        dados.setBounds(255,10,120,30);
        add(dados);
        
        painel.setBackground(Color.gray);
        painel.setBounds(40,10,555,30);
        add(painel);
        
        atualizar.addActionListener(this);
        cancelar.addActionListener(this);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    /**
     * Metodo contendo todos eventos da classe
     * @param ae como variavel do evento
     */
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== atualizar){
            ArrayList <Gestor> gestor = new ArrayList<>();
            if(Files.exists(Paths.get("src/Files/Gestor.dat"))){
                try{
                    if(!GuardarGestor.mostrar().isEmpty()){
                        gestor= GuardarGestor.mostrar();
                    }     
                    gestor.get(pos).setNome(nomeField.getText());
                    gestor.get(pos).setGenero((String)generoBox.getSelectedItem());
                    gestor.get(pos).setContacto(contactoField.getText());
                    gestor.get(pos).setResidencia(moradaField.getText());
                    GuardarGestor.guardar(gestor);
                    JOptionPane.showMessageDialog(null, "Dados Actualizados");
                    dispose();
                    new MenuGestor().Settings();
                }catch(IOException e){
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(GestUpdateForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }             
        }else if(ae.getSource() == cancelar){
            dispose();
            try {
                new MenuGestor().Settings();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GestUpdateForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
