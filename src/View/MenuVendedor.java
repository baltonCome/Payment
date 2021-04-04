package View;

import Controller.GuardarCarros;
import Controller.GuardarVendas;
import Models.Carro;
import Models.Venda;
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
import javax.swing.ImageIcon;
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
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class MenuVendedor extends JFrame implements ActionListener{
    
    ArrayList<String> car = new ArrayList<>();
    
    JPanel upColorPanel = new JPanel();
    JLabel sells = new JLabel("Vendas");
    JLabel sellsIcon = new JLabel(new ImageIcon("src/Files/Icons/vendas.png"));
    
    JLabel menuDoVendedor = new JLabel("MENU DO VENDEDOR");
    JLabel userIcon = new JLabel(new ImageIcon("src/Files/Icons/user.png"));
    JButton report = new JButton("Rel. de vendas", new ImageIcon("src/Files/Icons/report.png"));
    JButton information = new JButton("Sobre", new ImageIcon("src/Files/Icons/info.png"));
    JButton leave = new JButton("Sair", new ImageIcon("src/Files/Icons/exit.png"));
    JTextField searchBox = new JTextField();
    JButton search = new JButton(new ImageIcon("src/Files/Icons/search.png"));
    JButton sell = new JButton("Efectuar Venda", new ImageIcon("src/Files/Icons/vendas.png"));
    JButton viewSells = new JButton("Vendas Efectuadas", new ImageIcon("src/Files/Icons/sellreport.png"));
    JButton vendas = new JButton("Vendas", new ImageIcon("src/Files/Icons/vendas.png"));
    
    JPanel listPane = new JPanel();
    JLabel lista = new JLabel("CARROS A VENDA");
    
    boolean[] cellEditable = {false, false, false, false, false, false, false, false}; 
    DefaultTableModel colunas = new DefaultTableModel();
    JTable tabela;
    JScrollPane scroll;   
    
    public MenuVendedor() throws ClassNotFoundException{
        
        setTitle("Menu Vendedor");
        setVisible(true);
        setPreferredSize(new Dimension(800,380));
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setLayout(null);
        setBackground(new Color(27,33,33));
        
        userIcon.setBounds(5,5,150,70);
        add(userIcon);
        menuDoVendedor.setBounds(30,70,120,20);
        add(menuDoVendedor);
        
        vendas.setBackground(new Color(134,134,144 ));
        vendas.setBounds(5,100,150,30);
        add(vendas);
        
        report.setBackground(new Color(134,134,144));
        report.setBounds(5,150,150,30);
        add(report);
        information.setBackground(new Color(134,134,144));
        information.setBounds(5,205,150,30);
        add(information);
        leave.setBackground(new Color(134,134,144));
        leave.setBounds(5,260,150,30);
        add(leave);
        
        leave.addActionListener(this);
        report.addActionListener(this);
        information.addActionListener(this);
        search.addActionListener(this);
        sell.addActionListener(this);
        viewSells.addActionListener(this);
        vendas.addActionListener(this);
        
    }
    
    private void dadosTabelaCar() throws ClassNotFoundException{
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setNumRows(0);
        ArrayList <Carro> cars = new ArrayList<>();
        try {           
            cars = GuardarCarros.mostrar();
            if(!cars.isEmpty()){
                for (int i = 0; i < cars.size(); i++) {
                    model.addRow(new String[]{
                        cars.get(i).getMarca(),
                        cars.get(i).getModelo(),
                        String.valueOf(cars.get(i).getAno()),
                        cars.get(i).getCor(),
                        String.valueOf(cars.get(i).getPreco()),
                        cars.get(i).getChassi()
                    }); 
                }
            }            
        } catch (IOException ex) {
        }
    }  
    
    public void Vendas() throws ClassNotFoundException{
        
        searchBox.setBounds(575,5,120,25);
        add(searchBox);
        
        search.setBackground(new Color(140,170,151));
        search.setBounds(700,5,50,25);
        add(search);
        
        sell.setBackground(new Color(102,133,121));
        sell.setBounds(200,40,190,70);
        add(sell);
        
        viewSells.setBackground(new Color(102,133,121));
        viewSells.setBounds(400,40,190,70);
        add(viewSells);
        
        add(lista);
        lista.setBounds(420,116,200,20); 
        add(listPane);
        listPane.setBounds(200,116,549,20);    
        listPane.setBackground(Color.gray);
        
        add(sells);
        sells.setBounds(210,5,100,30);
        add(sellsIcon);
        sellsIcon.setBounds(270,5,50,30);
        add(upColorPanel);
        upColorPanel.setBounds(200,5,340,30);
        upColorPanel.setBackground(Color.gray);
        
        colunas.addColumn("Marca");
        colunas.addColumn("Modelo");
        colunas.addColumn("Ano");
        colunas.addColumn("Cor");
        colunas.addColumn("Preco");
        colunas.addColumn("Chassi");    
        
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
            size.setPreferredWidth(150);
            size.setMaxWidth(200);
        }
        tabela.setAutoResizeMode(AUTO_RESIZE_OFF);
        scroll = new JScrollPane(tabela);
        tabela.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        scroll.setBounds(200,135, 550, 200);
        dadosTabelaCar();
        add(scroll);
        pack();
        setLocationRelativeTo(null);
    }
    
    public void About(){
        
        JPanel upColorPanel = new JPanel();
        JLabel about = new JLabel("PaySell");
        JLabel aboutIcon = new JLabel(new ImageIcon("src/Files/Icons/info.png"));    
        JPanel infoColorPanel = new JPanel();
        JLabel paysell = new JLabel("<html>*SOBRE ESTE APP:<br/>Um Sistema de gerenciamento de uma macro-empresa."+
            "Venda e pagamento de salarios."+
            "<br/>"+"*O Sistema Permite:"+
            "<br/>"+"1.Criar, exibir, atualizar e remover dados de dois perfis."+
            "<br/>"+"2.Criar, exibir, atualizar e remover dados da Mercadoria."+
            "<br/>"+"3.Simular venda da Mercadoria registada(Carros)."+
            "<br/>"+"4.Pagar os salarios dos trabalhadores existentes."+
            "<br/>"+"5.Gerar relatorios de vendas e pagamentos."+
            "<br/>"+"*Em caso de problemas contacte: baltoncome@gmail.com"+
            "<br/>"+"*INFO DO APP:"+
            "<br/>"+"Densenvolvido por: Balton Come"+
            "<br/>"+"Densenvolvido em: Java SE"+
            "<br/>"+"Terminado na data: EM CURSO_____________PaySell Versao 1.2.0</html>", SwingConstants.CENTER);
        
        add(paysell);     
        paysell.setBounds(210,90,400,250);
        add(infoColorPanel);
        infoColorPanel.setBounds(200,100,400,230);
        infoColorPanel.setBackground(new Color(102,133,121));
        add(about);
        about.setBounds(210,30,100,30);
        add(aboutIcon);
        aboutIcon.setBounds(250,30,50,30);
        add(upColorPanel);
        upColorPanel.setBounds(200,10,400,60);
        upColorPanel.setBackground(Color.gray);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== leave){
            int sair = JOptionPane.showConfirmDialog(null,"Encerrar Sessao?","SAIR",JOptionPane.YES_NO_OPTION);
            if(sair == JOptionPane.YES_OPTION){
                dispose();
                new Login();
            }
        }else if(ae.getSource()== report){
            if(Files.exists(Paths.get("src/Files/Vendas.dat"))){
                try {
                    if(!GuardarVendas.mostrar().isEmpty()){
                        ArrayList <Venda> vendas = new ArrayList<>();
                        vendas = GuardarVendas.mostrar();
                        Metodos.relatorioVendas(vendas, "RelatorioVendas");
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(MenuGestor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else if(ae.getSource()== information){
            dispose();
            try {
                new MenuVendedor().About();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MenuVendedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(ae.getSource()== search){
            if (Files.exists(Paths.get("src/Files/Carros.dat"))){ 
                try {
                    if(!GuardarCarros.mostrar().isEmpty()){
                        ArrayList<Carro> procurar = GuardarCarros.mostrar();
                        JOptionPane.showMessageDialog(null, GuardarCarros.procurar(procurar, searchBox.getText()));
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(MenuGestor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else if(ae.getSource()== sell){
            try {
                dispose();new SellForm();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MenuVendedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(ae.getSource()== viewSells){
            try {
                new TabelaVendas();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MenuGestor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(ae.getSource()== vendas){
            dispose();
            try {
                new MenuVendedor().Vendas();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MenuVendedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
