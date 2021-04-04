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
import Models.Salario;
import Controller.GuardarSalarios;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.ImageIcon;


public class TabelaPagamentos extends JFrame implements ActionListener {
    
    ArrayList<String> pay = new ArrayList<>();
    
    boolean[] cellEditable = {false, false, false, false, false, false, false, false}; 
    DefaultTableModel colunas = new DefaultTableModel();
    JTable tabela;
    JScrollPane scroll; 
    
    JPanel listPane = new JPanel();
    JLabel lista = new JLabel("Folha de pagamento");
    JButton fechar = new JButton("Fechar");
    JLabel payIcon = new JLabel(new ImageIcon("src/Files/Icons/folha.png"));
    
    public TabelaPagamentos() throws ClassNotFoundException{
        
        setTitle("Salarios");
        setVisible(true);
        setPreferredSize(new Dimension(800,380));
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setLayout(null);
        
        colunas.addColumn("Nome");
        colunas.addColumn("Posicao");
        colunas.addColumn("Salario Base");
        colunas.addColumn("Bonus");
        colunas.addColumn("Desconto por Faltas");
        colunas.addColumn("Acrescimo Horas Extras");
        colunas.addColumn("Seguro de Funeral");
        colunas.addColumn("Assistencia Medica");
        colunas.addColumn("Aposentacao");
        colunas.addColumn("IRPS");
        colunas.addColumn("Salario Liquido");
        
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
                    ArrayList<String> pay_ = new ArrayList<>();
                    for(int i = 0; i < tabela.getColumnCount(); i++){
                        pay_.add((String) colunas.getValueAt(tabela.getSelectedRow(), i));
                    }
                    pay = pay_;
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
        
        payIcon.setBounds(20,10,80,30);
        add(payIcon);
        
        add(listPane);
        listPane.setBounds(10,10,760,30);    
        listPane.setBackground(Color.gray);
        
        fechar.addActionListener(this);
        
        dadosTabelar();
        add(scroll);
        pack();
        setLocationRelativeTo(null);
    }
    
    private void dadosTabelar() throws ClassNotFoundException{
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setNumRows(0);
        ArrayList <Salario> salary = new ArrayList<>();
        try {           
            salary = GuardarSalarios.mostrar();
            if(!salary.isEmpty()){
                for (int i = 0; i < salary.size(); i++) {
                    model.addRow(new String[]{
                        salary.get(i).getNome(),
                        salary.get(i).getCargo(),
                        String.valueOf(salary.get(i).getSalarioBase()),
                        String.valueOf(salary.get(i).getBonus()),
                        String.valueOf(salary.get(i).getFaltas()),
                        String.valueOf(salary.get(i).getHorasExtras()),
                        String.valueOf(salary.get(i).getSubsidioFuneral()),
                        String.valueOf(salary.get(i).getAssistenciaMedica()),
                        String.valueOf(salary.get(i).getAposentacao()),
                        String.valueOf(salary.get(i).getIrps()),
                        String.valueOf(salary.get(i).getSalarioLiquido())
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
