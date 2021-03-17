package View;

import Controller.GuardarCarros;
import Controller.GuardarVendas;
import Models.Carros;
import Models.Vendas;
import ModelsDao.Metodos;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.JTable.AUTO_RESIZE_OFF;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class SellForm extends JFrame implements ActionListener {
    
    ArrayList <String> car = new ArrayList<>();
    JPanel listPane = new JPanel();
    JLabel lista = new JLabel("CARROS DISPONIVEIS");
    
    JPanel listPane2 = new JPanel();
    JLabel lista2 = new JLabel("VENDA");
    
    JLabel marca = new JLabel("Marca do veiculo");
    JTextField marcaField = new JTextField();
    
    JLabel modelo = new JLabel("Modelo do Veiculo");
    JTextField modeloField = new JTextField();
    
    JLabel quantidade = new JLabel("Quantidade");
    JTextField quantidadeField = new JTextField();
    
    JLabel nomeCliente = new JLabel("Nome do Cliente");
    JTextField clienteField = new  JTextField();
    
    JButton processar  = new JButton("Processar Venda");
    JTextField exibicao = new JTextField();
    JButton confirmar = new JButton("Confirmar");
    JButton cancelar = new JButton("Cancelar");
    
    JLabel ano = new JLabel("ANO DE FABRICO: ");
    JLabel getAno = new JLabel();
    
    JLabel cor = new JLabel("COR DO VEICULO: ");
    JLabel getCor = new JLabel();
    
    JLabel preco = new JLabel("PRECO POR UNIDADE: ");
    JLabel getPreco = new JLabel();
    
    JLabel total = new JLabel("VALOR DA COMPRA: ");
    JLabel getTotal = new JLabel();
    
    boolean[] cellEditable = {false, false, false, false, false, false, false, false}; 
    DefaultTableModel colunas = new DefaultTableModel();
    JTable tabela;
    JScrollPane scroll; 
    
    public SellForm() throws ClassNotFoundException{
        
        setTitle("Menu Vendedor");
        setVisible(true);
        setPreferredSize(new Dimension(800,380));
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setLayout(null);
        
        marca.setBounds(250,50,200,20);
        add(marca);
        marcaField.setBounds(250,70,200,20);
        add(marcaField);
        
        modelo.setBounds(550,50,200,20);
        add(modelo);
        modeloField.setBounds(550,70,200,20);
        add(modeloField);
       
        quantidade.setBounds(250,110,200,20);
        add(quantidade);
        quantidadeField.setBounds(250,130,200,20);
        add(quantidadeField);
        
        nomeCliente.setBounds(550,110,200,20);
        add(nomeCliente);
        clienteField.setBounds(550,130,200,20);
        add(clienteField);
        
        ano.setBounds(250,210,150,20);
        add(ano);
        getAno.setBounds(400,210,100,20);
        add(getAno);
        
        cor.setBounds(500,210,150,20);
        add(cor);
        getCor.setBounds(650,210,100,20);
        add(getCor);
        
        preco.setBounds(250,250,150,20);
        add(preco);
        getPreco.setBounds(400,250,100,20);
        add(getPreco);
        
        total.setBounds(500,250,150,20);
        add(total);
        getTotal.setBounds(650,250,100,20);
        add(getTotal);
        
        processar.setBackground(new Color(134,134,144));
        processar.setBounds(495,160,150,27);
        add(processar);
        
        cancelar.setBackground(new Color(134,134,144));
        cancelar.setBounds(330,160,150,27);
        add(cancelar);
        
        confirmar.setBackground(new Color(134,134,144));
        confirmar.setBounds(410,300,150,30);
        add(confirmar);
        
        ano.setVisible(false);
        preco.setVisible(false);
        total.setVisible(false);
        cor.setVisible(false);
        confirmar.setVisible(false);
        
        listPane2.setBackground(Color.gray);
        lista2.setBounds(475,10,100,30);
        listPane2.setBounds(250,10,500,30);
        add(lista2);
        add(listPane2);
        
        add(lista);
        lista.setBounds(50,10,146,30); 
        add(listPane);
        listPane.setBounds(10,10,198,30);    
        listPane.setBackground(Color.gray);
        
        confirmar.addActionListener(this);
        processar.addActionListener(this);
        cancelar.addActionListener(this);
        
        colunas.addColumn("Marca");
        colunas.addColumn("Modelo");
        
        tabela = new JTable(colunas) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return cellEditable[columnIndex];
            }
        };
        
        tabela.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me){
                if(tabela.getSelectedRow() >= 0){
                    ArrayList<String> car_ = new ArrayList<>();
                    for(int i = 0; i < tabela.getColumnCount(); i++){
                        car_.add((String) colunas.getValueAt(tabela.getSelectedRow(), i));
                    }
                    car = car_;
                }
            }
        });
        
        for(int i = 0; i < tabela.getColumnCount(); i++){
            TableColumn size = tabela.getColumnModel().getColumn(i);
            size.setPreferredWidth(100);
            size.setMaxWidth(150);
        }
        tabela.setAutoResizeMode(AUTO_RESIZE_OFF);
        scroll = new JScrollPane(tabela);
        tabela.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        scroll.setBounds(10,50,200,280);
        dadosTabelaCar();
        add(scroll);
        pack();
        setLocationRelativeTo(null);
    }
    
    private void dadosTabelaCar() throws ClassNotFoundException{
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setNumRows(0);
        ArrayList <Carros> cars = new ArrayList<>();
        try {           
            cars = GuardarCarros.mostrar();
            if(!cars.isEmpty()){
                for (int i = 0; i < cars.size(); i++) {
                    model.addRow(new String[]{
                        cars.get(i).getMarca(),
                        cars.get(i).getModelo(),
                    }); 
                }
            }            
        } catch (IOException ex) {
        }
    }  
    
    @Override
    public void actionPerformed(ActionEvent ae){
        ArrayList<Carros> carro = new ArrayList<>();
        double precoF = 0;
        if(ae.getSource()== processar){
            if (Files.exists(Paths.get("src/Files/Carros.dat"))){ 
                try {
                    if(!GuardarCarros.mostrar().isEmpty()){
                        carro = GuardarCarros.mostrar();
                    }
                    if (GuardarCarros.podeVender(carro, marcaField.getText(), modeloField.getText(), 
                            Integer.parseInt(quantidadeField.getText())) != -1){
                        getAno.setText(""+carro.get(GuardarCarros.podeVender(carro, marcaField.getText(),
                            modeloField.getText(), Integer.parseInt(quantidadeField.getText()))).getAno());
                        getCor.setText(carro.get(GuardarCarros.podeVender(carro, marcaField.getText(),
                            modeloField.getText(), Integer.parseInt(quantidadeField.getText()))).getCor());
                        getPreco.setText(""+carro.get(GuardarCarros.podeVender(carro, marcaField.getText(),
                            modeloField.getText(), Integer.parseInt(quantidadeField.getText()))).getPreco());
                        precoF = carro.get(GuardarCarros.podeVender(carro, marcaField.getText(), modeloField.getText(),
                            Integer.parseInt(quantidadeField.getText()))).getPreco()*Integer.parseInt(quantidadeField.getText());
                        Metodos.preco = precoF;
                        getTotal.setText(""+precoF);
                        ano.setVisible(true);
                        preco.setVisible(true);
                        total.setVisible(true);
                        cor.setVisible(true);
                        confirmar.setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(null, "Nao foi possivel processar a venda, verifique os dados e Tente Novamente!");
                    }
                } catch (IOException | ClassNotFoundException ex) {
                }
            }
        }else if(ae.getSource()== confirmar){
            try{
                if(!GuardarCarros.mostrar().isEmpty()){
                    carro = GuardarCarros.mostrar();
                }
                GuardarCarros.vendido(carro, marcaField.getText(), modeloField.getText(), Integer.parseInt(quantidadeField.getText()));
                ArrayList <Vendas> venda = new ArrayList<>();
                if(Files.exists(Paths.get("src/Files/Vendas.dat"))){
                    if(!GuardarVendas.mostrar().isEmpty()){
                        venda = GuardarVendas.mostrar();
                    }
                }       
                Vendas vend = new Vendas(Metodos.vendaId(marcaField.getText(), modeloField.getText()),clienteField.getText(),marcaField.getText(),
                    modeloField.getText(),getAno.getText(),getCor.getText(),Metodos.preco,Integer.parseInt(quantidadeField.getText()),Metodos.currentUser);
                venda.add(vend);
                GuardarVendas.guardar(venda);
                JOptionPane.showMessageDialog(null, "Carro vendido");
                dispose();
                new MenuVendedor().Vendas();
            }catch(IOException | ClassNotFoundException ex){
            }
        }else if(ae.getSource()== cancelar){
            dispose();
            try {
                new MenuVendedor().Vendas();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SellForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}