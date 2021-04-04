package View;

import java.awt.Color;
import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.JTable.AUTO_RESIZE_OFF;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import Models.Funcionario;
import Controller.GuardarFuncionario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;
import Models.Carros;
import Controller.GuardarCarro;
import Controller.GuardarGestor;
import Controller.GuardarSalario;
import Controller.GuardarVenda;
import Models.Gestor;
import Models.Salario;
import Models.Vendas;
import ModelsDao.Metodos;
import java.awt.Dimension;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class MenuGestor extends JFrame implements ActionListener{
    
    ArrayList<String> func = new ArrayList<>();
    ArrayList<String> car = new ArrayList<>();
    ArrayList<String> pay = new ArrayList<>();
    
    JPanel upColorPanel = new JPanel();
    JLabel funcionarios = new JLabel("Funcionarios");
    JPanel listPane = new JPanel();
    JLabel lista = new JLabel("LISTA");
    
    JLabel menuDoGestor = new JLabel("MENU DO GESTOR");
    JLabel funcIcon = new JLabel(new ImageIcon("src/Files/Icons/workers.png"));
    JLabel userIcon = new JLabel(new ImageIcon("src/Files/Icons/user.png"));
    JButton workers = new JButton("Func.s", new ImageIcon("src/Files/Icons/workers.png"));
    JButton vehicles = new JButton("Veiculos", new ImageIcon("src/Files/Icons/car.png"));
    JButton money = new JButton("Financas", new ImageIcon("src/Files/Icons/money.png"));
    JButton report = new JButton("Relatorios", new ImageIcon("src/Files/Icons/report.png"));
    JButton configurations = new JButton("Configuracoes", new ImageIcon("src/Files/Icons/settings.png"));
    JButton information = new JButton("Sobre", new ImageIcon("src/Files/Icons/info.png"));
    JButton leave = new JButton("Sair", new ImageIcon("src/Files/Icons/exit.png"));
    
    JButton register = new JButton("Adicionar", new ImageIcon("src/Files/Icons/adduser.png"));
    JButton update = new JButton("Alterar", new ImageIcon("src/Files/Icons/change.png"));
    JButton remove = new JButton("Rem.", new ImageIcon("src/Files/Icons/remove.png"));
    JTextField searchBox = new JTextField();
    JButton search = new JButton(new ImageIcon("src/Files/Icons/search.png"));
    JButton searchCar = new JButton(new ImageIcon("src/Files/Icons/search.png"));
    
    JLabel carIcon = new JLabel(new ImageIcon("src/Files/Icons/car.png"));
    JButton addCar = new JButton("Adicionar", new ImageIcon("src/Files/Icons/addCar.png"));
    JButton removeCar = new JButton("Remover", new ImageIcon("src/Files/Icons/removeCar.png"));
    
    JButton processar = new JButton("Procesar Salarios", new ImageIcon("src/Files/Icons/processar.png"));
    JButton vendas = new JButton("Vendas Efectuadas", new ImageIcon("src/Files/Icons/vendas.png"));
    JButton folha = new JButton("Folha de Pagamento", new ImageIcon("src/Files/Icons/folha.png"));
    
    JButton sellReport = new JButton("Relatorio de Vendas", new ImageIcon("src/Files/Icons/sellreport.png"));
    JButton paymentReport = new JButton("Relatorio dos Salarios", new ImageIcon("src/Files/Icons/paymentReport.png"));
    JButton stockReport = new JButton("Relatorio de Stock", new ImageIcon("src/Files/Icons/stock.png"));
    
    JButton addGest = new JButton("Adicionar Gestor", new ImageIcon("src/Files/Icons/addGest.png"));
    JButton alterarGest = new JButton("Alterar Dados", new ImageIcon("src/Files/Icons/changeData.png"));
    JButton alterarSenha = new JButton("Alterar Senha", new ImageIcon("src/Files/Icons/changePassword.png"));
    JButton deleteAcc = new JButton("Eliminar Conta", new ImageIcon("src/Files/Icons/deleteAcc.png"));
    JButton myData = new JButton("Meus Dados", new ImageIcon("src/Files/Icons/myData.png"));
   
    public static int allDone;
    
    public MenuGestor() throws ClassNotFoundException{
        
        setTitle("Menu Gestor");
        setVisible(true);
        setPreferredSize(new Dimension(800,380));
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setLayout(null);
        setBackground(new Color(27,33,33));
        
        userIcon.setBounds(5,5,150,70);
        add(userIcon);
        menuDoGestor.setBounds(30,70,120,20);
        add(menuDoGestor);
        
        workers.setBackground(new Color(134,134,144 ));
        workers.setBounds(5,100,150,30);
        add(workers);
        vehicles.setBackground(new Color(134,134,144));
        vehicles.setBounds(5,135,150,30);
        add(vehicles);
        money.setBackground(new Color(134,134,144));
        money.setBounds(5,170,150,30);
        add(money);
        report.setBackground(new Color(134,134,144));
        report.setBounds(5,205,150,30);
        add(report);
        configurations.setBackground(new Color(134,134,144));
        configurations.setBounds(5,240,150,30);
        add(configurations);
        information.setBackground(new Color(134,134,144));
        information.setBounds(5,275,150,30);
        add(information);
        leave.setBackground(new Color(134,134,144));
        leave.setBounds(5,310,150,30);
        add(leave);
        
        configurations.addActionListener(this);
        leave.addActionListener(this);
        workers.addActionListener(this);
        vehicles.addActionListener(this);
        report.addActionListener(this);
        money.addActionListener(this);
        information.addActionListener(this);
        register.addActionListener(this);
        update.addActionListener(this);
        remove.addActionListener(this);
        search.addActionListener(this);
        searchCar.addActionListener(this);
        removeCar.addActionListener(this);
        processar.addActionListener(this);
        vendas.addActionListener(this);
        folha.addActionListener(this);
        sellReport.addActionListener(this);
        paymentReport.addActionListener(this);
        stockReport.addActionListener(this);
        addGest.addActionListener(this);
        alterarGest.addActionListener(this);
        alterarSenha.addActionListener(this);
        deleteAcc.addActionListener(this);
        myData.addActionListener(this);
        addCar.addActionListener(this);
 
    }
   
    boolean[] cellEditable = {false, false, false, false, false, false, false, false}; 
    DefaultTableModel colunas = new DefaultTableModel();
    JTable tabela;
    JScrollPane scroll;   
    
    public void Funcionarios() throws ClassNotFoundException{
        
        register.setBackground(new Color(102,133,121));
        register.setBounds(200,40,130,70);
        add(register);
        
        update.setBackground(new Color(102,133,121));
        update.setBounds(340,40,130,70);
        add(update);
        
        remove.setBackground(new Color(102,133,121));
        remove.setBounds(480,40,130,70);
        add(remove);
        
        searchBox.setBounds(575,5,120,25);
        add(searchBox);
        
        search.setBackground(new Color(140,170,151));
        search.setBounds(700,5,50,25);
        add(search);
        
        add(funcionarios);
        funcionarios.setBounds(210,5,100,30);
        add(funcIcon);
        funcIcon.setBounds(270,5,50,30);
        add(upColorPanel);
        upColorPanel.setBounds(200,5,340,30);
        upColorPanel.setBackground(Color.gray);
        
        add(lista);
        lista.setBounds(460,116,50,20); 
        add(listPane);
        listPane.setBounds(200,116,549,20);    
        listPane.setBackground(Color.gray);
        
        
        colunas.addColumn("ID");
        colunas.addColumn("Nome");
        colunas.addColumn("Genero");
        colunas.addColumn("Residencia");
        colunas.addColumn("Contacto");
        colunas.addColumn("Banco");
        colunas.addColumn("Nr.Conta");
        colunas.addColumn("Data de Contrato");
        colunas.addColumn("Cargo");
        colunas.addColumn("Salario Base");
        
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
                    ArrayList<String> func_ = new ArrayList<>();
                    for(int i = 0; i < tabela.getColumnCount(); i++){
                        func_.add((String) colunas.getValueAt(tabela.getSelectedRow(), i));
                    }
                    func = func_;
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
        
        dadosTabela();
        add(scroll);
        pack();
        setLocationRelativeTo(null);
    }
    
    private void dadosTabela() throws ClassNotFoundException{
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setNumRows(0);
        ArrayList <Funcionario> funcs = new ArrayList<>();
        try {           
            funcs = GuardarFuncionario.mostrar();
            if(!funcs.isEmpty()){
                for (int i = 0; i < funcs.size(); i++) {
                    model.addRow(new String[]{
                        String.valueOf(funcs.get(i).getId()),
                        funcs.get(i).getNome(),
                        funcs.get(i).getGenero(),
                        funcs.get(i).getResidencia(),
                        funcs.get(i).getContacto(),
                        funcs.get(i).getBanco(),
                        String.valueOf(funcs.get(i).getNrConta()),
                        funcs.get(i).getDataContrato(),
                        funcs.get(i).getCargo(),
                        String.valueOf(funcs.get(i).getSalario())
                    }); 
                }
            }            
        } catch (IOException ex) {
        }
    }  
    
    public void Veiculos() throws ClassNotFoundException{
        
        JPanel upColorPanel = new JPanel();
        JLabel veiculos = new JLabel("Veiculos");
        JPanel listPane = new JPanel();
        JLabel lista = new JLabel("LISTA");
        
        addCar.setBackground(new Color(102,133,121));
        addCar.setBounds(200,40,130,70);
        add(addCar);
        
        removeCar.setBackground(new Color(102,133,121));
        removeCar.setBounds(340,40,130,70);
        add(removeCar);
        
        searchBox.setBounds(575,5,120,25);
        add(searchBox);
        
        searchCar.setBackground(new Color(140,170,151));
        searchCar.setBounds(700,5,50,25);
        add(searchCar);
        
        add(veiculos);
        veiculos.setBounds(210,5,100,30);
        add(carIcon);
        carIcon.setBounds(270,5,50,30);
        add(upColorPanel);
        upColorPanel.setBounds(200,5,340,30);
        upColorPanel.setBackground(Color.gray);
        
        add(lista);
        lista.setBounds(460,116,50,20); 
        add(listPane);
        listPane.setBounds(200,116,549,20);    
        listPane.setBackground(Color.gray);
        
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
    
    private void dadosTabelaCar() throws ClassNotFoundException{
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setNumRows(0);
        ArrayList <Carros> cars = new ArrayList<>();
        try {           
            cars = GuardarCarro.mostrar();
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
    
    public void Financas(){
        
        JPanel upColorPanel = new JPanel();
        JLabel financas = new JLabel("Financas");
        JLabel moneyIcon = new JLabel(new ImageIcon("src/Files/Icons/money.png"));
        
        processar.setBackground(new Color(102,133,121));
        processar.setBounds(200,100,200,70);
        add(processar);
        
        vendas.setBackground(new Color(102,133,121));
        vendas.setBounds(340,180,200,70);
        add(vendas);
        
        folha.setBackground(new Color(102,133,121));
        folha.setBounds(480,260,200,70);
        add(folha);
        
        add(financas);
        financas.setBounds(210,30,100,30);
        add(moneyIcon);
        moneyIcon.setBounds(270,30,50,30);
        add(upColorPanel);
        upColorPanel.setBounds(200,10,400,60);
        upColorPanel.setBackground(Color.gray);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    public void Relatorios(){
        
        JPanel upColorPanel = new JPanel();
        JLabel relatorios = new JLabel("Relatorios");
        JLabel reportIcon = new JLabel(new ImageIcon("src/Files/Icons/report.png"));
        
        sellReport.setBackground(new Color(102,133,121));
        sellReport.setBounds(200,100,200,70);
        add(sellReport);
        
        paymentReport.setBackground(new Color(102,133,121));
        paymentReport.setBounds(340,180,200,70);
        add(paymentReport);
        
        stockReport.setBackground(new Color(102,133,121));
        stockReport.setBounds(480,260,200,70);
        add(stockReport);
        
        add(relatorios);
        relatorios.setBounds(210,30,100,30);
        add(reportIcon);
        reportIcon.setBounds(270,30,50,30);
        add(upColorPanel);
        upColorPanel.setBounds(200,10,400,60);
        upColorPanel.setBackground(Color.gray);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    public void Settings(){
        
        JPanel upColorPanel = new JPanel();
        JLabel settings = new JLabel("Configuracoes");
        JLabel settingsIcon = new JLabel(new ImageIcon("src/Files/Icons/settings.png"));
        
        addGest.setBackground(new Color(102,133,121));
        addGest.setBounds(200,100,190,70);
        add(addGest);
        
        alterarGest.setBackground(new Color(102,133,121));
        alterarGest.setBounds(200,180,190,70);
        add(alterarGest);
        
        alterarSenha.setBackground(new Color(102,133,121));
        alterarSenha.setBounds(400,100,190,70);
        add(alterarSenha);
        
        deleteAcc.setBackground(new Color(102,133,121));
        deleteAcc.setBounds(400,180,190,70);
        add(deleteAcc);
        
        myData.setBackground(new Color(102,133,121));
        myData.setBounds(200,260,190,70);
        add(myData);
        
        add(settings);
        settings.setBounds(210,30,100,30);
        add(settingsIcon);
        settingsIcon.setBounds(300,30,50,30);
        add(upColorPanel);
        upColorPanel.setBounds(200,10,400,60);
        upColorPanel.setBackground(Color.gray);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    public void About(){
        
        JPanel upColorPanel = new JPanel();
        JLabel about = new JLabel("PaySell");
        JLabel aboutIcon = new JLabel(new ImageIcon("src/Files/Icons/info.png"));    
        JPanel infoColorPanel = new JPanel();
        JLabel paysell = new JLabel("<html>*SOBRE ESTE SYS:<br/>Um Sistema de gerenciamento de uma macro-empresa."+
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
            "<br/>"+"Ultima Atualizacao: EM CURSO_____________PaySell Versao 1.2.0</html>", SwingConstants.CENTER);   
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
        }else if(ae.getSource()== vehicles){
            try {
                new MenuGestor().Veiculos();dispose();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MenuGestor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(ae.getSource()== money){
            try {
                new MenuGestor().Financas();dispose();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MenuGestor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if (ae.getSource()== report){
            try {
                new MenuGestor().Relatorios();dispose();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MenuGestor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(ae.getSource()== configurations){
            try {
                dispose();new MenuGestor().Settings();dispose();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MenuGestor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(ae.getSource()== information){
            try {
                new MenuGestor().About();dispose();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MenuGestor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(ae.getSource()== workers){
            try {
                new MenuGestor().Funcionarios();dispose();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MenuGestor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(ae.getSource()== register){
            dispose();
            new FuncRegisterForm();
        }else if(ae.getSource()== update){            
            String key = JOptionPane.showInputDialog("Nome/ID do funcionario a alterar");
            ArrayList <Funcionario> funcionario = new ArrayList<>();
            if(Files.exists(Paths.get("src/Files/Funcionario.dat"))){
                try{
                    if(!GuardarFuncionario.mostrar().isEmpty()){
                        funcionario = GuardarFuncionario.mostrar();
                    }
                    if (GuardarFuncionario.editar(funcionario, key) != -1){
                        dispose();
                        new FuncUpdateForm(GuardarFuncionario.editar(funcionario,key));
                    }else{
                        JOptionPane.showMessageDialog(null, "Nome/ID nao encontrado");
                    }
                }catch(IOException e){
                }catch (ClassNotFoundException ex) {
                    Logger.getLogger(MenuGestor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else if(ae.getSource()== remove){
            if (Files.exists(Paths.get("src/Files/Funcionario.dat"))){ 
                try {
                    if(!GuardarFuncionario.mostrar().isEmpty()){
                        ArrayList<Funcionario> remover = GuardarFuncionario.mostrar();
                        String id = JOptionPane.showInputDialog("Nome/ID Do Funcionario: ");
                        GuardarFuncionario.apagar(remover, id);
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(MenuGestor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else if(ae.getSource()== search){
            if (Files.exists(Paths.get("src/Files/Funcionario.dat"))){ 
                try {
                    if(!GuardarFuncionario.mostrar().isEmpty()){
                        ArrayList<Funcionario> procurar = GuardarFuncionario.mostrar();
                        JOptionPane.showMessageDialog(null, GuardarFuncionario.procurar(procurar, searchBox.getText()));
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(MenuGestor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else if(ae.getSource()== addCar){
            dispose();
            new CarRegisterForm();
        }else if(ae.getSource()== searchCar){
            if (Files.exists(Paths.get("src/Files/Carros.dat"))){ 
                try {
                    if(!GuardarCarro.mostrar().isEmpty()){
                        ArrayList<Carros> procurar = GuardarCarro.mostrar();
                        JOptionPane.showMessageDialog(null, GuardarCarro.procurar(procurar, searchBox.getText()));
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(MenuGestor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else if(ae.getSource()== removeCar){
            if (Files.exists(Paths.get("src/Files/Carros.dat"))){ 
                try {
                    if(!GuardarCarro.mostrar().isEmpty()){
                        ArrayList<Carros> remover = GuardarCarro.mostrar();
                        String id = JOptionPane.showInputDialog("Chassi do Carro: ");
                        GuardarCarro.apagar(remover, id);
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(MenuGestor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else if(ae.getSource()== processar){
            dispose();
            ArrayList<Funcionario> funcionario = new ArrayList<>();
            if (Files.exists(Paths.get("src/Files/Funcionario.dat"))){
                try{
                    if (!GuardarFuncionario.mostrar().isEmpty()){
                        funcionario = GuardarFuncionario.mostrar();
                    }
                    allDone=0;
                    new PayRollForm(funcionario.get(allDone).getNome(), funcionario.get(allDone).getId(),
                    funcionario.get(allDone).getCargo(), funcionario.get(allDone).getSalario(),
                    funcionario.get(allDone).getBanco(), funcionario.get(allDone).getNrConta());
                }catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(MenuGestor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Sem Funcionarios Registados");
                try {
                    new MenuGestor().Funcionarios();
                } catch (ClassNotFoundException ex) {
                }
            }
        }else if(ae.getSource()== vendas){
            try {
                new TabelaVendas();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MenuGestor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(ae.getSource()== folha){
            try {
                new TabelaPagamentos();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MenuGestor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(ae.getSource()== sellReport){
            if(Files.exists(Paths.get("src/Files/Vendas.dat"))){
                try {
                    if(!GuardarVenda.mostrar().isEmpty()){
                        ArrayList <Vendas> vendas = new ArrayList<>();
                        vendas = GuardarVenda.mostrar();
                        Metodos.relatorioVendas(vendas, "RelatorioVendas");
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(MenuGestor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else if(ae.getSource()== paymentReport){
            ArrayList <Salario> salarios = new ArrayList<>();
            if(Files.exists(Paths.get("src/Files/Salarios.dat"))){
                try {
                    if(!GuardarSalario.mostrar().isEmpty()){                      
                        salarios = GuardarSalario.mostrar();
                        Metodos.relatorioPagamentos(salarios, "RelatorioPay");
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(MenuGestor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else if(ae.getSource()== stockReport){
            if(Files.exists(Paths.get("src/Files/Carros.dat"))){
                try {
                    if(!GuardarCarro.mostrar().isEmpty()){
                        ArrayList <Carros> carro = new ArrayList<>();
                        carro = GuardarCarro.mostrar();
                        Metodos.relatorioStock(carro, "RelatorioStock");
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(MenuGestor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else if(ae.getSource()== addGest){
            new GestRegisterForm();
        }else if(ae.getSource()== alterarGest){
            String key = JOptionPane.showInputDialog("Nome/ID");
            ArrayList <Gestor> gestor = new ArrayList<>();
            if(Files.exists(Paths.get("src/Files/Gestor.dat"))){
                try{
                    if(!GuardarGestor.mostrar().isEmpty()){
                        gestor = GuardarGestor.mostrar();
                    }
                    if (GuardarGestor.editar(gestor, key) != -1){
                        dispose();
                        new GestUpdateForm(GuardarGestor.editar(gestor,key));
                    }else{
                        JOptionPane.showMessageDialog(null, "Nome/ID nao encontrado");
                    }
                }catch(IOException e){
                }catch (ClassNotFoundException ex) {
                    Logger.getLogger(MenuGestor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else if(ae.getSource()== alterarSenha){
            dispose();new ChangePassForm();
        }else if(ae.getSource()== deleteAcc){
            if (Files.exists(Paths.get("src/Files/Gestor.dat"))){ 
                try {
                    ArrayList<Gestor> remover = new ArrayList();
                    if(!GuardarGestor.mostrar().isEmpty()){
                        remover = GuardarGestor.mostrar();
                    }
                    String key = JOptionPane.showInputDialog("Insira o ID: ");
                        if(remover.remove(GuardarGestor.procurar(remover, key))){
                            GuardarGestor.guardar(remover);
                            JOptionPane.showMessageDialog(null, "Conta Removida","INFO",JOptionPane.PLAIN_MESSAGE);
                            dispose();
                            new Login();
                        }
                        /*if (GuardarGestor.apagar(remover, key)){
                            JOptionPane.showMessageDialog(null, "Conta Removida","INFO",JOptionPane.PLAIN_MESSAGE);
                            
                            dispose();
                            new Login();
                        }*/else{
                            JOptionPane.showMessageDialog(null, "Operacao sem sucesso","ERRO",JOptionPane.ERROR_MESSAGE);
                        }
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(MenuGestor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else if (ae.getSource()== myData){
            String key = JOptionPane.showInputDialog("Insira ID");
            ArrayList <Gestor> gestor = new ArrayList<>();
            if (Files.exists(Paths.get("src/Files/Gestor.dat"))){
                try{
                    if (!GuardarGestor.mostrar().isEmpty()){
                        gestor = GuardarGestor.mostrar();
                    }
                    JOptionPane.showMessageDialog(null, GuardarGestor.procurar(gestor, key));
                } catch (IOException | ClassNotFoundException ex ) {
                    Logger.getLogger(MenuGestor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public static void main (String [] args) throws ClassNotFoundException{
        new MenuGestor().Funcionarios();
    }
}
