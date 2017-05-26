package view;

import dao.HistoricoAcaoDAO;
import dao.UsuarioDAO;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.HistoricoAcaoM;
import model.UsuarioM;


public class HistoricoView extends javax.swing.JInternalFrame {

    HistoricoAcaoDAO historicoAcaoDAO;
    List<HistoricoAcaoM> listaHistorico;
    UsuarioDAO usuarioDAO;
    List<UsuarioM> listaUsuario;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
    Date dataIni = new Date(System.currentTimeMillis());;
    Date dataF = new Date(System.currentTimeMillis());;
    UsuarioM usuario = null;
    HistoricoAcaoM infoHistorico;
    
    int qntCampos = 0;
    int combCampos = 0;
    
    int inicio = 0, quantMax, pagAtual, pagUltima;
    int cont = 0;
    int ultimoID;
    
    public HistoricoView() throws SQLException {
        
        initComponents();
        this.setVisible(true);
        
        //tfdNavegacao.setVisible(false);
        //jLabel3.setVisible(false);
        historicoAcaoDAO = new HistoricoAcaoDAO();
        listaHistorico = new ArrayList<>();
        usuarioDAO = new UsuarioDAO();
        listaUsuario = new ArrayList<>();
        sdf.setLenient(false);
        
        infoHistorico = new HistoricoAcaoM();
        
        
        preencheComboUsuario();
        preencheComboAcoes();
        //atualizaTabelaHistorico100(inicio);
        listaHistorico = historicoAcaoDAO.lista100(inicio);
        atualizaTabelaHistorico();
        quantMax = historicoAcaoDAO.quantidadeTodos();
        validaQuantidadeTodos();
    } 
    
    public String converteData(Date data){
        java.util.Date testeData;
        testeData = new java.util.Date(data.getTime());
        String teste = sdf.format(testeData);
        return teste;
    }
    
    public void atualizaTabelaHistorico() {
       
        String dados[][] = new String[listaHistorico.size()][6];
        int i = 0;
        
        for (HistoricoAcaoM hist : listaHistorico) {
           
            dados[i][0] = converteData(hist.getDataAcao());
            dados[i][1] = hist.getTipoObjeto();
            dados[i][2] = hist.getCodigo();
            dados[i][3] = hist.getAcao();
            dados[i][4] = hist.getUsuario().getNome();
            dados[i][5] = ""+(i+inicio+1);
            
            i++;
        }
        String tituloColuna[] = {"Data", "Descrição", "Código", "Ação", "Usuário","Indice"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        
        tabelaCliente.setDataVector(dados, tituloColuna);
        tbeHistorico.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tbeHistorico.getColumnModel().getColumn(0).setPreferredWidth(80);
        tbeHistorico.getColumnModel().getColumn(1).setPreferredWidth(110);
        tbeHistorico.getColumnModel().getColumn(2).setPreferredWidth(110);
        tbeHistorico.getColumnModel().getColumn(3).setPreferredWidth(110);
        tbeHistorico.getColumnModel().getColumn(4).setPreferredWidth(110);
        tbeHistorico.getColumnModel().getColumn(5).setPreferredWidth(50);
        

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tbeHistorico.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        tbeHistorico.getColumnModel().getColumn(5).setCellRenderer(centralizado);
        tbeHistorico.setRowHeight(25);
        tbeHistorico.updateUI();
        
    }
    
    
    
    public void preencheComboUsuario(){
        cbxUsuario.removeAllItems();
        cbxUsuario.addItem("--Selecione--");
        
        try {
            listaUsuario = usuarioDAO.listaTodos();
        } catch (SQLException ex) {
            Logger.getLogger(HistoricoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (UsuarioM user : listaUsuario){
            if (!user.getUsuario().equals("root"))
            {
                cbxUsuario.addItem(user.getNome());
            }   
        }        
        
    }
    
    public void preencheComboAcoes(){
        cbxAcoes.removeAllItems();
        cbxAcoes.addItem("--Selecione--");
        
        cbxAcoes.addItem("Novo");
        cbxAcoes.addItem("Alterar");
        cbxAcoes.addItem("Baixar");
        cbxAcoes.addItem("Excluir");
        
    }
    
    public void limpaCampos(){
        cbxUsuario.setSelectedIndex(0);
        tfdPeriodoInicio.setValue(null);
        tfdPeriodoFim.setValue(null);
        cbxAcoes.setSelectedIndex(0);
        tfdDescricao.setText("");
        listaHistorico = null;
        tfdCodigo.setText("");
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbeHistorico = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cbxUsuario = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        tfdPeriodoInicio = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfdPeriodoFim = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        cbxAcoes = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        tfdDescricao = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tfdCodigo = new javax.swing.JTextField();
        tfdNavegacao = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lblQuantPaginas = new javax.swing.JLabel();
        btnProximo = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Histórico");

        tbeHistorico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, "", null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Data", "Descrição", "Ação", "Usuario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbeHistorico.setSelectionForeground(new java.awt.Color(153, 0, 255));
        tbeHistorico.getTableHeader().setReorderingAllowed(false);
        tbeHistorico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbeHistoricoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbeHistorico);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtros"));
        jPanel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btnBuscar.setText("Buscar");
        btnBuscar.setPreferredSize(new java.awt.Dimension(65, 26));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel1.setText("Usuário:");

        jLabel2.setText("Período da ação");

        try {
            tfdPeriodoInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel4.setText("Início:");

        jLabel5.setText("Fim:");

        try {
            tfdPeriodoFim.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel6.setText("Ação:");

        jLabel7.setText("Descrição:");

        jLabel8.setText("Código:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxAcoes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxUsuario, 0, 216, Short.MAX_VALUE))
                            .addComponent(jLabel2)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfdPeriodoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfdPeriodoFim, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfdDescricao)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(tfdCodigo)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbxUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfdPeriodoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(tfdPeriodoFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbxAcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tfdDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 799, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tfdNavegacao.setPreferredSize(new java.awt.Dimension(6, 23));
        tfdNavegacao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfdNavegacaoKeyPressed(evt);
            }
        });

        jLabel3.setText("Ir para:");

        lblQuantPaginas.setText("quant de paginas");

        btnProximo.setText(">>");
        btnProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProximoActionPerformed(evt);
            }
        });

        btnAnterior.setText("<<");
        btnAnterior.setEnabled(false);
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAnterior)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblQuantPaginas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnProximo)
                        .addGap(276, 276, 276)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfdNavegacao, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuantPaginas)
                    .addComponent(btnProximo)
                    .addComponent(jLabel3)
                    .addComponent(tfdNavegacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnterior))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbeHistoricoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbeHistoricoMouseClicked
    }//GEN-LAST:event_tbeHistoricoMouseClicked

    private void tfdNavegacaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdNavegacaoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){  
           if(Integer.parseInt(tfdNavegacao.getText()) <= 0 || Integer.parseInt(tfdNavegacao.getText()) > pagUltima){
               JOptionPane.showMessageDialog(null, "Pagina Invalida!");
               
           }else{
           inicio = ((Integer.parseInt(tfdNavegacao.getText()) - 1) * 100);
           pagAtual = Integer.parseInt(tfdNavegacao.getText());
           
           lblQuantPaginas.setText(pagAtual + "/" + pagUltima);
           if(pagAtual == pagUltima){
               btnProximo.setEnabled(false);
               btnAnterior.setEnabled(true);
           }
           
           if(pagAtual == 1){
               btnAnterior.setEnabled(false);
               btnProximo.setEnabled(true);
           }
           
           if(pagAtual == 1 && pagAtual== pagUltima){
               btnProximo.setEnabled(false);
               btnAnterior.setEnabled(false);
           }
           
           if(pagAtual > 1 && pagAtual<pagUltima){
                btnProximo.setEnabled(true);
                btnAnterior.setEnabled(true);
           }
           
           
                try {
                    if(qntCampos == 0){
                        listaHistorico = historicoAcaoDAO.lista100(inicio);
                        atualizaTabelaHistorico();
                    }else{
                        //listaHistorico = historicoAcaoDAO.buscaConcatenada(infoHistorico, dataIni, dataF, qntCampos, combCampos, inicio);
                        atualizaTabelaHistorico();
            }
                } catch (SQLException ex) {
                    Logger.getLogger(HistoricoView.class.getName()).log(Level.SEVERE, null, ex);
                }
           
            
                
            
        }
        
        }
        tfdNavegacao.setText("");
    
    }//GEN-LAST:event_tfdNavegacaoKeyPressed
    
    public void proximoNormal() throws SQLException{
        inicio+=100;
        if(qntCampos == 0){
            listaHistorico = historicoAcaoDAO.lista100(inicio);
        }else{
             listaHistorico = historicoAcaoDAO.buscaNova100(infoHistorico, dataIni, dataF, inicio);
        }
       
        atualizaTabelaHistorico();
        btnAnterior.setEnabled(true);
        pagAtual++;
        lblQuantPaginas.setText(pagAtual+"/"+pagUltima);
        if(inicio>=(quantMax-100)){
            btnProximo.setEnabled(false);
        }
    }
    
    
    private void btnProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProximoActionPerformed
        try {
                    proximoNormal();
                } catch (SQLException ex) {
                    Logger.getLogger(HistoricoView.class.getName()).log(Level.SEVERE, null, ex);
                }
        
    }//GEN-LAST:event_btnProximoActionPerformed
   
    public void anteriorNormal() throws SQLException{
        inicio -=100;
        if(qntCampos == 0){
            listaHistorico = historicoAcaoDAO.lista100(inicio);
        }else{
             listaHistorico = historicoAcaoDAO.buscaNova100(infoHistorico, dataIni, dataF, inicio);
        }
        atualizaTabelaHistorico();
        btnProximo.setEnabled(true);
        pagAtual--;
        lblQuantPaginas.setText(pagAtual+"/"+pagUltima);
        if(inicio==0){
            btnAnterior.setEnabled(false);
        }
    }
    
    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed

                try {
                    anteriorNormal();
                } catch (SQLException ex) {
                    Logger.getLogger(HistoricoView.class.getName()).log(Level.SEVERE, null, ex);
                }
           
    }//GEN-LAST:event_btnAnteriorActionPerformed
    
    public void validaQuantidadeTodos() throws SQLException{
        pagAtual = 1;
        
        if(quantMax > 100){
            if(quantMax % 100 == 0){
                pagUltima = quantMax / 100;
                btnProximo.setEnabled(true);
            }else{
                pagUltima = (quantMax / 100) + 1;
                btnProximo.setEnabled(true);
            }
        }else{
            pagUltima = 1;
            pagAtual = 1;
            btnProximo.setEnabled(false);
            btnAnterior.setEnabled(false);
        }
        
        lblQuantPaginas.setText(pagAtual + "/" + pagUltima);
        
    }

        
    private void contaFiltros(){
                
        if (cbxUsuario.getSelectedIndex() != 0)
        {
            qntCampos++;
            //combCampos = combCampos + 2;
        }
        if (!tfdPeriodoInicio.getText().equals("  /  /    ") && !tfdPeriodoFim.getText().equals("  /  /    "))
        {
            qntCampos++;
            //combCampos = combCampos + 3;
        }
        if (cbxAcoes.getSelectedIndex() != 0)
        {
            qntCampos++;
            //combCampos = combCampos + 5;
        }
        if (!tfdDescricao.getText().isEmpty()){
            qntCampos++;
            //combCampos = combCampos + 7;
        }
        if(!tfdCodigo.getText().isEmpty()){
            qntCampos++;
        }
   
    }
    
    private HistoricoAcaoM pegaInfoFiltros() throws SQLException{
        
        if(cbxUsuario.getSelectedIndex() != 0){
            usuario = usuarioDAO.buscaNome(cbxUsuario.getSelectedItem().toString());
            infoHistorico.setUsuario(usuario);
        }
        
        if(cbxAcoes.getSelectedIndex() != 0){
            infoHistorico.setAcao(cbxAcoes.getSelectedItem().toString());
        }else{
            infoHistorico.setAcao("");
        }
        
        if(!tfdDescricao.getText().isEmpty()){
            infoHistorico.setTipoObjeto(tfdDescricao.getText());
        }else{
            infoHistorico.setTipoObjeto("");
        }
        
        if(!tfdPeriodoInicio.getText().equals("  /  /    ")){
            
            if(tfdPeriodoFim.getText().equals("  /  /    ")){
            //se a data final estiver vazia ele considera ate a data atual
            //pega a data atual já convertida para o formato MySQL yyyy-mm-dd
            dataF = new Date(System.currentTimeMillis());
            String dataInicio = tfdPeriodoInicio.getText();
            try {
                dataIni = new Date(sdf.parse(dataInicio).getTime());
            } catch (ParseException ex) {
                Logger.getLogger(HistoricoView.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
            }
            else{
                String dataInicio = tfdPeriodoInicio.getText();
                String dataFim = tfdPeriodoFim.getText();
                try {
                    dataIni = new Date(sdf.parse(dataInicio).getTime());
                    dataF = new Date(sdf.parse(dataFim).getTime());
                } catch (ParseException ex) {
                    Logger.getLogger(HistoricoView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }   
        }else{
            String dataInicio = "01/01/2000"; //para nao ter problemas com data, setamos uma data antiga
            try {
                dataIni = new Date(sdf.parse(dataInicio).getTime());
                dataF = new Date(System.currentTimeMillis());
            } catch (ParseException ex) {
                Logger.getLogger(HistoricoView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(!tfdCodigo.getText().equals("")){
            infoHistorico.setCodigo(tfdCodigo.getText());
        }else{
            infoHistorico.setCodigo("");
        }
        
        

        return infoHistorico;
    }
 
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        
        inicio = 0;
        qntCampos = 0;
        combCampos = 0;
        btnAnterior.setEnabled(false);
        btnProximo.setEnabled(false);
        try {
            pegaInfoFiltros();
        } catch (SQLException ex) {
            Logger.getLogger(HistoricoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        contaFiltros();
        
        
        if (qntCampos == 0){
            try {
                listaHistorico = historicoAcaoDAO.lista100(inicio);
                quantMax = historicoAcaoDAO.quantidadeTodos();
                validaQuantidadeTodos();
                atualizaTabelaHistorico();
                limpaCampos();
                listaHistorico = null;
                
            } catch (SQLException ex) {
                Logger.getLogger(HistoricoView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            try {
                listaHistorico = historicoAcaoDAO.buscaNova100(infoHistorico, dataIni, dataF, inicio);
                quantMax = historicoAcaoDAO.buscaQuantidade(infoHistorico, dataIni, dataF, inicio);
                validaQuantidadeTodos();
                atualizaTabelaHistorico();
                limpaCampos();
                listaHistorico = null;
            } catch (SQLException ex) {
                Logger.getLogger(HistoricoView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
 
        
        
        
 
    }//GEN-LAST:event_btnBuscarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnProximo;
    private javax.swing.JComboBox<String> cbxAcoes;
    private javax.swing.JComboBox<String> cbxUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblQuantPaginas;
    private javax.swing.JTable tbeHistorico;
    private javax.swing.JTextField tfdCodigo;
    private javax.swing.JTextField tfdDescricao;
    private javax.swing.JTextField tfdNavegacao;
    private javax.swing.JFormattedTextField tfdPeriodoFim;
    private javax.swing.JFormattedTextField tfdPeriodoInicio;
    // End of variables declaration//GEN-END:variables

    private void proximoBuscaDescricao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
