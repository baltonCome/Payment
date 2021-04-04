package View;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.JTable.AUTO_RESIZE_OFF;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import Models.Venda;
import Controller.GuardarVendas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.ImageIcon;


public class TabelaVendas extends JFrame implements ActionListener {
    
    ArrayList<String> vend = new ArrayList<>();
    
    boolean[] cellEditable = {false, false, false, false, false, false, false, false}; 
    DefaultTableModel colunas = new DefaultTableModel();
    JTable tabela;
    JScrollPane scroll; 
    
    JPanel listPane = new JPanel();
    JLabel lista = new JLabel("Vendas Efectuadas");
    JButton fechar = new JButton("Fechar");
    JLabel vendasIcon = new JLabel(new ImageIcon("src/Files/Icons/vendas.png"));
    
    public TabelaVendas() throws ClassNotFoundException{
        
        setTitle("VENDAS");
        setVisible(true);
        setPreferredSize(new Dimension(800,380));
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setLayout(null);
        
        colunas.addColumn("ID da Venda");
        colunas.addColumn("Nome do Cliente");
        colunas.addColumn("Marca do Veiculo");
        colunas.addColumn("Modelo do Veiculo");
        colunas.addColumn("Ano de Fabrico");
        colunas.addColumn("Cor");
        colunas.addColumn("Valor da Venda");
        colunas.addColumn("Chassi");
        colunas.addColumn("Nome do Vendedor");
        
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
                    ArrayList<String> vend_ = new ArrayList<>();
                    for(int i = 0; i < tabela.getColumnCount(); i++){
                        vend_.add((String) colunas.getValueAt(tabela.getSelectedRow(), i));
                    }
                    vend = vend_;
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
        scroll.setBounds(10,50,760,255);
        
        fechar.setBackground(new Color(140,170,151));
        fechar.setBounds(300,310,200,30);
        add(fechar);
        
        lista.setBounds(100,10,200,30);
        add(lista);
        
        vendasIcon.setBounds(20,10,80,30);
        add(vendasIcon);
        
        add(listPane);
        listPane.setBounds(10,10,760,30);    
        listPane.setBackground(Color.gray);
        
        fechar.addActionListener(this);
        
        dadosTabela();
        add(scroll);
        pack();
        setLocationRelativeTo(null);
    }
    
    private void dadosTabela() throws ClassNotFoundException{
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setNumRows(0);
        ArrayList <Venda> vendas = new ArrayList<>();
        try {           
            vendas = GuardarVendas.mostrar();
            if(!vendas.isEmpty()){
                for (int i = 0; i < vendas.size(); i++) {
                    model.addRow(new String[]{
                        vendas.get(i).getIdVenda(),
                        vendas.get(i).getNomeCliente(),
                        vendas.get(i).getMarcaCarro(),
                        vendas.get(i).getModeloCarro(), 
                        String.valueOf(vendas.get(i).getAnoCarro()),
                        vendas.get(i).getCorCarro(),
                        String.valueOf(vendas.get(i).getPrecoCarro()),
                        vendas.get(i).getChassi(),
                        vendas.get(i).getNomeVendedor()
                    }); 
                }
            }            
        } catch (IOException ex) {
        }
    }  
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== fechar){
            dispose();
        }
    }
}
