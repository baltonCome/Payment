package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Models.Carros;
import Controller.GuardarCarro;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Classe da interface grafica para o registo de veiculos
 * @author Mr. Savagery
 */
public class CarRegisterForm extends JFrame implements ActionListener{
    
    
    JLabel marca = new JLabel("Marca: ");
    JTextField marcaField = new JTextField();
    
    JLabel modelo = new JLabel("Modelo: ");
    JTextField modeloField = new JTextField();
    
    JLabel ano = new JLabel("Ano: ");
    JTextField anoField = new JTextField();
    
    JLabel cor = new JLabel("Cor: ");
    JTextField corField = new JTextField();
    
    JLabel preco = new JLabel("Preco: ");
    JTextField precoField = new JTextField();
    
    JLabel chassi = new JLabel("Chassi: ");
    JTextField chassiField = new JTextField();
    
    JButton guardar = new JButton("Guardar");
    JButton cancelar = new JButton("Cancelar");
    
    JPanel painel = new JPanel();
    JLabel carRegister = new JLabel("REGISTAR VEICULO");
    
    /**
     * Construtor contento a GUI com o formulario para o registo de veiculos
     */
    public CarRegisterForm(){
        
        setTitle("Registo de Veiculos");
        setVisible(true);
        setPreferredSize(new Dimension(650,275));
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setLayout(null);
        
        marca.setBounds(30,50,100,20);
        add(marca);
        marcaField.setBounds(150,50,150,20);
        add(marcaField);
        
        modelo.setBounds(325,50,100,20);
        add(modelo);
        modeloField.setBounds(400,50,150,20);
        add(modeloField);
        
        ano.setBounds(30,90,100,20);
        add(ano);
        anoField.setBounds(150,90,150,20);
        add(anoField);
        
        cor.setBounds(325,90,100,20);
        add(cor);
        corField.setBounds(400,90,150,20);
        add(corField);
        
        preco.setBounds(30,130,100,20);
        add(preco);
        precoField.setBounds(150,130,150,20);
        add(precoField);
        
        chassi.setBounds(325,130,100,20);
        add(chassi);
        chassiField.setBounds(400,130,150,20);
        add(chassiField);
        
        guardar.setBackground(new Color(134,134,144 ));
        guardar.setBounds(130,190,170,30);
        add(guardar);
        
        cancelar.setBackground(new Color(134,134,144 ));
        cancelar.setBounds(315,190,170,30);
        add(cancelar);
        
        guardar.addActionListener(this);
        cancelar.addActionListener(this);
        
        carRegister.setBounds(255,10,120,30);
        add(carRegister);
        
        painel.setBackground(Color.gray);
        painel.setBounds(40,10,555,30);
        add(painel);
            
        pack();
        setLocationRelativeTo(null);
    }
    
    /**
     * Metodo contendo todos eventos da classe
     * @param ae como variavel do evento
     */
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== guardar){
            try{
                ArrayList <Carros> cars = new ArrayList<>();
                if(Files.exists(Paths.get("src/Files/Carros.dat"))){
                    if(!GuardarCarro.mostrar().isEmpty()){
                        cars = GuardarCarro.mostrar();
                    }
                }
                if(!GuardarCarro.uniqueChassi(cars, chassiField.getText())){
                    JOptionPane.showMessageDialog(null, "Chassi Ja Existente, verifique e tente novamente");
                    dispose();
                    new CarRegisterForm();
                }
                Carros car = new Carros(marcaField.getText(),modeloField.getText(),Integer.parseInt(anoField.getText()),
                    corField.getText(),Double.parseDouble(precoField.getText()),chassiField.getText());
                cars.add(car);
                GuardarCarro.guardar(cars);
                JOptionPane.showMessageDialog(null, "Veiculo Registado");
                dispose();
                new MenuGestor().Veiculos();
            }catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(CarRegisterForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if (ae.getSource() == cancelar){
           dispose();
            try {
                new MenuGestor().Veiculos();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CarRegisterForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
