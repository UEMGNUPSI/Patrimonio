/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.GrauConservacaoDAO;
import dao.HistoricoAcaoDAO;
import dao.StatusDAO;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.GrauConservacaoM;
import model.HistoricoAcaoM;
import model.StatusM;
import model.UsuarioM;
import util.LimiteDigitos;
/**
 * UNIVERSIDADE DO ESTADO DE MINAS GERAIS - Unidade Frutal
 * @author NUPSI - Núcle de Práticas em Sistemas de Informação
 * Equipe: Gustavo Pinoti,Leopoldo Ferreira, Marlon Moro, Murillo Cuervo
 */
public class ConservacaoStatusView extends javax.swing.JInternalFrame {

    /**
     * Creates new form ConservacaoStatusView
     */
    public ConservacaoStatusView(UsuarioM usuarioAtivo) {
        grauConservacaoDAO = new GrauConservacaoDAO();
        statusDAO = new StatusDAO();
        listaGrauConservacao = new ArrayList<>();
        listaStatus = new ArrayList<>();

        this.usuarioAtivo = usuarioAtivo;
        
        initComponents();
        this.setVisible(true);
        atualizaTabelaConservacao();
        atualizaTabelaStatus();
        tdfDescricaoConservacao.setDocument(new LimiteDigitos(45));
        tfdDescricaoStatus.setDocument(new LimiteDigitos(45));
        //limpaCamposConservacao();
        //limpaCamposStatus();

    }

    GrauConservacaoM grauConservacao;
    StatusM status;
    StatusDAO statusDAO;
    GrauConservacaoDAO grauConservacaoDAO;
    List<StatusM> listaStatus;
    List<GrauConservacaoM> listaGrauConservacao;

    int idHistorico;
    String acao;
    String descricaoHistorico;
    UsuarioM usuarioAtivo;
    
    public void atualizaTabelaConservacao() {

        try {
            listaGrauConservacao = grauConservacaoDAO.listaTodos();
        } catch (SQLException ex) {
            Logger.getLogger(ConservacaoStatusView.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[listaGrauConservacao.size()][4];
        int i = 0;
        for (GrauConservacaoM grauConservacao1 : listaGrauConservacao) {
            dados[i][0] = String.valueOf(grauConservacao1.getId());
            dados[i][1] = grauConservacao1.getDescricao();
            i++;
        }
        String tituloColuna[] = {"ID", "Grau de Conservação"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        tbeConservacao.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tbeConservacao.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbeConservacao.getColumnModel().getColumn(1).setPreferredWidth(300);

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tbeConservacao.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        tbeConservacao.setRowHeight(25);
        tbeConservacao.updateUI();

    }

    public void atualizaTabelaStatus() {

        try {
            listaStatus = statusDAO.listaTodos();
        } catch (SQLException ex) {
            Logger.getLogger(ConservacaoStatusView.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[listaStatus.size()][4];
        int i = 0;
        for (StatusM status1 : listaStatus) {
            dados[i][0] = String.valueOf(status1.getId());
            dados[i][1] = status1.getNome();
            i++;
        }
        String tituloColuna[] = {"ID", "Status de Patrimonio"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        tbeStatus.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        tbeStatus.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbeStatus.getColumnModel().getColumn(1).setPreferredWidth(300);

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tbeStatus.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        tbeStatus.setRowHeight(25);
        tbeStatus.updateUI();

    }
    
    public void salvaHistorico() throws SQLException{
        HistoricoAcaoM historico = new HistoricoAcaoM();
        historico.setIdObjeto(idHistorico);
        historico.setTipoObjeto(descricaoHistorico);
        historico.setAcao(acao);
        historico.setDataAcao(new Date(System.currentTimeMillis()));
        historico.setUsuario(usuarioAtivo);
        
        HistoricoAcaoDAO.salvar(historico);
    }

    public void limpaCamposConservacao() {
        tfdIDConservacao.setText("");
        tdfDescricaoConservacao.setText("");
        //btnSalvarConservacao.setEnabled(true);
        btnExcluirConservacao.setEnabled(false);
        btnAlterarConservacao.setEnabled(false);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbeStatus = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbeConservacao = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblIDConservacao = new javax.swing.JLabel();
        tdfDescricaoConservacao = new javax.swing.JTextField();
        btnNovoStatus = new javax.swing.JButton();
        btnAlterarStatus = new javax.swing.JButton();
        lblDescricaoConservacao = new javax.swing.JLabel();
        btnNovoConservacao = new javax.swing.JButton();
        btnExcluirStatus = new javax.swing.JButton();
        lblConservacao = new javax.swing.JLabel();
        btnSalvarStatus = new javax.swing.JButton();
        tfdDescricaoStatus = new javax.swing.JTextField();
        lblDescricaoStatus = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        lblIDStatus = new javax.swing.JLabel();
        btnSalvarConservacao = new javax.swing.JButton();
        tfdIDStatus = new javax.swing.JTextField();
        btnExcluirConservacao = new javax.swing.JButton();
        tfdIDConservacao = new javax.swing.JTextField();
        btnAlterarConservacao = new javax.swing.JButton();
        btnCancelarConservacao = new javax.swing.JButton();
        btnCancelarStatus = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de Grau de Conservação e Status");

        tbeStatus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Status de Patrimônio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbeStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbeStatusMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbeStatus);

        tbeConservacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Grau de Conservação"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbeConservacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbeConservacaoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbeConservacao);

        lblIDConservacao.setText("ID");

        tdfDescricaoConservacao.setEnabled(false);
        tdfDescricaoConservacao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tdfDescricaoConservacaoKeyPressed(evt);
            }
        });

        btnNovoStatus.setText("Novo");
        btnNovoStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoStatusActionPerformed(evt);
            }
        });

        btnAlterarStatus.setText("Alterar");
        btnAlterarStatus.setEnabled(false);
        btnAlterarStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarStatusActionPerformed(evt);
            }
        });

        lblDescricaoConservacao.setText("Descrição");

        btnNovoConservacao.setText("Novo");
        btnNovoConservacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoConservacaoActionPerformed(evt);
            }
        });

        btnExcluirStatus.setText("Excluir");
        btnExcluirStatus.setEnabled(false);
        btnExcluirStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirStatusActionPerformed(evt);
            }
        });

        lblConservacao.setText("Grau de Conservação");

        btnSalvarStatus.setText("Salvar");
        btnSalvarStatus.setEnabled(false);
        btnSalvarStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarStatusActionPerformed(evt);
            }
        });
        btnSalvarStatus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnSalvarStatusKeyPressed(evt);
            }
        });

        tfdDescricaoStatus.setEnabled(false);
        tfdDescricaoStatus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfdDescricaoStatusKeyPressed(evt);
            }
        });

        lblDescricaoStatus.setText("Descrição");

        lblStatus.setText("Status de Patrimônio");

        lblIDStatus.setText("ID");

        btnSalvarConservacao.setText("Salvar");
        btnSalvarConservacao.setEnabled(false);
        btnSalvarConservacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarConservacaoActionPerformed(evt);
            }
        });
        btnSalvarConservacao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnSalvarConservacaoKeyPressed(evt);
            }
        });

        tfdIDStatus.setEditable(false);
        tfdIDStatus.setEnabled(false);

        btnExcluirConservacao.setText("Excluir");
        btnExcluirConservacao.setEnabled(false);
        btnExcluirConservacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirConservacaoActionPerformed(evt);
            }
        });

        tfdIDConservacao.setEditable(false);
        tfdIDConservacao.setEnabled(false);

        btnAlterarConservacao.setText("Alterar");
        btnAlterarConservacao.setEnabled(false);
        btnAlterarConservacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarConservacaoActionPerformed(evt);
            }
        });

        btnCancelarConservacao.setText("Cancelar");
        btnCancelarConservacao.setEnabled(false);
        btnCancelarConservacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarConservacaoActionPerformed(evt);
            }
        });

        btnCancelarStatus.setText("Cancelar");
        btnCancelarStatus.setEnabled(false);
        btnCancelarStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarStatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tdfDescricaoConservacao)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(tfdDescricaoStatus, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnNovoStatus)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSalvarStatus)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCancelarStatus)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnAlterarStatus)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnExcluirStatus))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(72, 72, 72)
                                        .addComponent(lblStatus))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(lblIDConservacao))
                                    .addComponent(tfdIDConservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(69, 69, 69)
                                        .addComponent(lblConservacao))
                                    .addComponent(lblDescricaoConservacao)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnNovoConservacao)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSalvarConservacao)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCancelarConservacao)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnAlterarConservacao)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnExcluirConservacao))))
                            .addComponent(tfdIDStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIDStatus)
                            .addComponent(lblDescricaoStatus))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblConservacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIDConservacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdIDConservacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricaoConservacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tdfDescricaoConservacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvarConservacao)
                    .addComponent(btnExcluirConservacao)
                    .addComponent(btnAlterarConservacao)
                    .addComponent(btnNovoConservacao)
                    .addComponent(btnCancelarConservacao))
                .addGap(22, 22, 22)
                .addComponent(lblStatus)
                .addGap(3, 3, 3)
                .addComponent(lblIDStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdIDStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDescricaoStatus)
                .addGap(4, 4, 4)
                .addComponent(tfdDescricaoStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvarStatus)
                    .addComponent(btnExcluirStatus)
                    .addComponent(btnAlterarStatus)
                    .addComponent(btnNovoStatus)
                    .addComponent(btnCancelarStatus))
                .addGap(112, 112, 112))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarConservacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarConservacaoActionPerformed
        // TODO add your handling code here:
        if (tdfDescricaoConservacao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha Todos os campos!", "Erro", JOptionPane.WARNING_MESSAGE);
            tdfDescricaoConservacao.requestFocusInWindow();
        } else if (tfdIDConservacao.getText().isEmpty()) {
            grauConservacao = new GrauConservacaoM();
            grauConservacao.setDescricao(tdfDescricaoConservacao.getText());

            try {
                idHistorico = grauConservacaoDAO.salvar(grauConservacao);
                acao = "Novo Grau Conservação";
                descricaoHistorico = grauConservacao.getDescricao();
                salvaHistorico();
                JOptionPane.showMessageDialog(null, "Gravado com Sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                atualizaTabelaConservacao();
                preparaSalvareCancelarConservacao();
                desativaCamposConservacao();
            limpaCamposConservacao(); 
            } catch (SQLException ex) {
                Logger.getLogger(ConservacaoStatusView.class.getName()).log(Level.SEVERE, null, ex);
                if (ex.getErrorCode() == 1062) {
                    JOptionPane.showMessageDialog(null, "Conservacao já existente.", "Erro", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
             
        } else {
            
            grauConservacao = new GrauConservacaoM();
            grauConservacao.setDescricao(tdfDescricaoConservacao.getText());
            grauConservacao.setId(Integer.parseInt(tfdIDConservacao.getText()));
        
            try {
                idHistorico = grauConservacao.getId();
                acao = "Alterar Grau de Conservação";
                descricaoHistorico = grauConservacao.getDescricao();
                salvaHistorico();
                grauConservacaoDAO.alterar(grauConservacao);
                JOptionPane.showMessageDialog(null, "Conservação alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                atualizaTabelaConservacao();
                preparaSalvareCancelarConservacao();
                desativaCamposConservacao();
                limpaCamposConservacao();
            } catch (SQLException ex) {
                Logger.getLogger(ConservacaoStatusView.class.getName()).log(Level.SEVERE, null, ex);
                if (ex.getErrorCode() == 1062) {
                    JOptionPane.showMessageDialog(null, "Conservacao já existente.", "Erro", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }


    }//GEN-LAST:event_btnSalvarConservacaoActionPerformed

    private void btnExcluirConservacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirConservacaoActionPerformed
        // TODO add your handling code here:
        if (tfdIDConservacao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione um Grau de Concervação!", "Erro", JOptionPane.WARNING_MESSAGE);
        } else {
            grauConservacao = new GrauConservacaoM();
            grauConservacao.setId(Integer.parseInt(tfdIDConservacao.getText()));
            grauConservacao.setDescricao(tdfDescricaoConservacao.getText());
            int confirma = JOptionPane.showConfirmDialog(null, "Dejesa Excluir: " + tdfDescricaoConservacao.getText());
            if (confirma == 0) {
                try {
                    idHistorico = grauConservacao.getId();
                    acao = "Excluir Grau Conservação";
                    descricaoHistorico = grauConservacao.getDescricao();
                    salvaHistorico();
                    
                    grauConservacaoDAO.excluir(grauConservacao);
                } catch (SQLException ex) {
                    Logger.getLogger(ConservacaoStatusView.class.getName()).log(Level.SEVERE, null, ex);
                    if (ex.getErrorCode() == 1451) {
                        JOptionPane.showMessageDialog(null, "Impossível excluir essa Conservação, ela já possui patrimônios cadastrados!", "Erro", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
                    }
                }
                limpaCamposConservacao();
                atualizaTabelaConservacao();
                preparaExcluirConservacao();

            }
        }
    }//GEN-LAST:event_btnExcluirConservacaoActionPerformed

    private void btnAlterarConservacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarConservacaoActionPerformed
        // TODO add your handling code here:
        preparaAlterarConservacao();
        ativaCamposConservacao();

    }//GEN-LAST:event_btnAlterarConservacaoActionPerformed

    private void btnNovoConservacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoConservacaoActionPerformed
        // TODO add your handling code here:
        limpaCamposConservacao();
        preparaNovoConservacao();
        ativaCamposConservacao();
        
    }//GEN-LAST:event_btnNovoConservacaoActionPerformed


    private void btnSalvarStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarStatusActionPerformed
        
        if (tfdDescricaoStatus.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha Todos os campos!", "Erro", JOptionPane.WARNING_MESSAGE);
            tfdDescricaoStatus.requestFocusInWindow();
        } else if (tfdIDStatus.getText().isEmpty()){
            status = new StatusM();
            status.setNome(tfdDescricaoStatus.getText());

            try {
                idHistorico = statusDAO.salvar(status);
                acao = "Novo Status";
                descricaoHistorico = status.getNome();
                salvaHistorico();
                JOptionPane.showMessageDialog(null, "Gravado com Sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                atualizaTabelaStatus();
                preparaSalvareCancelarStatus();
                desativaCamposStatus();
                limpaCamposStatus();  
            } catch (SQLException ex) {
                Logger.getLogger(ConservacaoStatusView.class.getName()).log(Level.SEVERE, null, ex);
                if (ex.getErrorCode() == 1062) {
                    JOptionPane.showMessageDialog(null, "Status já existente.", "Erro", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
                }
            }
                 
        } else {         
            status = new StatusM();
            status.setNome(tfdDescricaoStatus.getText());
            status.setId(Integer.parseInt(tfdIDStatus.getText()));
            try {
                idHistorico = status.getId();
                acao = "Alterar Status";
                descricaoHistorico = status.getNome();
                salvaHistorico();
                statusDAO.alterar(status);
                JOptionPane.showMessageDialog(null, "Status alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                atualizaTabelaStatus();
                preparaSalvareCancelarStatus();
                desativaCamposStatus();
                limpaCamposStatus();
            } catch (SQLException ex) {
                Logger.getLogger(ConservacaoStatusView.class.getName()).log(Level.SEVERE, null, ex);
                if (ex.getErrorCode() == 1062) {
                    JOptionPane.showMessageDialog(null, "Status já existente.", "Erro", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
            
        }
    }//GEN-LAST:event_btnSalvarStatusActionPerformed

    private void btnExcluirStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirStatusActionPerformed

        if (tfdIDStatus.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione um Status de Patrimônio!", "Erro", JOptionPane.WARNING_MESSAGE);
        } else {
            status = new StatusM();
            status.setId(Integer.parseInt(tfdIDStatus.getText()));
            status.setNome(tfdDescricaoStatus.getText());
            int confirma = JOptionPane.showConfirmDialog(null, "Dejesa Excluir: " + tfdDescricaoStatus.getText());
            if (confirma == 0) {
                try {
                    acao = "Excluir Status";
                    idHistorico = status.getId();
                    descricaoHistorico = status.getNome();
                    salvaHistorico();
                    statusDAO.excluir(status);
                } catch (SQLException ex) {
                    Logger.getLogger(ConservacaoStatusView.class.getName()).log(Level.SEVERE, null, ex);
                    if (ex.getErrorCode() == 1451) {
                        JOptionPane.showMessageDialog(null, "Impossível excluir esse Status, ele já possui patrimônio cadastrados!", "Erro", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
                limpaCamposStatus();
                atualizaTabelaStatus();
                preparaExcluirStatus();

            }
        }
    }//GEN-LAST:event_btnExcluirStatusActionPerformed

    private void btnAlterarStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarStatusActionPerformed
        
        preparaAlterarStatus();
        ativaCamposStatus();
       
        
    }//GEN-LAST:event_btnAlterarStatusActionPerformed

    private void btnNovoStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoStatusActionPerformed
       limpaCamposStatus();
       preparaNovoStatus();
       ativaCamposStatus();
    }//GEN-LAST:event_btnNovoStatusActionPerformed

    private void tbeConservacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbeConservacaoMouseClicked
        // TODO add your handling code here:
        tfdIDConservacao.setText(tbeConservacao.getValueAt(tbeConservacao.getSelectedRow(), 0).toString());
        tdfDescricaoConservacao.setText(tbeConservacao.getValueAt(tbeConservacao.getSelectedRow(), 1).toString());
        preparaSelecaoTabelaConservacao();
    }//GEN-LAST:event_tbeConservacaoMouseClicked

    private void tbeStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbeStatusMouseClicked
        // TODO add your handling code here:
        tfdIDStatus.setText(tbeStatus.getValueAt(tbeStatus.getSelectedRow(), 0).toString());
        tfdDescricaoStatus.setText(tbeStatus.getValueAt(tbeStatus.getSelectedRow(), 1).toString());
        preparaSelecaoTabelaStatus();
    }//GEN-LAST:event_tbeStatusMouseClicked

    private void btnCancelarConservacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarConservacaoActionPerformed
        // TODO add your handling code here:
        limpaCamposConservacao();
        preparaSalvareCancelarConservacao();
        desativaCamposConservacao();
    }//GEN-LAST:event_btnCancelarConservacaoActionPerformed

    private void btnCancelarStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarStatusActionPerformed
        // TODO add your handling code here:
        limpaCamposStatus();
        preparaSalvareCancelarStatus();
        desativaCamposStatus();
    }//GEN-LAST:event_btnCancelarStatusActionPerformed

    private void tdfDescricaoConservacaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tdfDescricaoConservacaoKeyPressed

         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnSalvarConservacao.requestFocusInWindow();
        }
    }//GEN-LAST:event_tdfDescricaoConservacaoKeyPressed

    private void btnSalvarConservacaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSalvarConservacaoKeyPressed

         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
             if (tdfDescricaoConservacao.getText().isEmpty()) {
                 JOptionPane.showMessageDialog(null, "Preencha Todos os campos!", "Erro", JOptionPane.WARNING_MESSAGE);
                 tdfDescricaoConservacao.requestFocusInWindow();
             } else if (tfdIDConservacao.getText().isEmpty()) {
                 grauConservacao = new GrauConservacaoM();
                 grauConservacao.setDescricao(tdfDescricaoConservacao.getText());

                 try {
                     idHistorico = grauConservacaoDAO.salvar(grauConservacao);
                     acao = "Novo Grau Conservação";
                     descricaoHistorico = grauConservacao.getDescricao();
                     salvaHistorico();
                     JOptionPane.showMessageDialog(null, "Gravado com Sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                     atualizaTabelaConservacao();
                     preparaSalvareCancelarConservacao();
                     desativaCamposConservacao();
                     limpaCamposConservacao();
                 } catch (SQLException ex) {
                     Logger.getLogger(ConservacaoStatusView.class.getName()).log(Level.SEVERE, null, ex);
                     if (ex.getErrorCode() == 1062) {
                         JOptionPane.showMessageDialog(null, "Conservacao já existente.", "Erro", JOptionPane.WARNING_MESSAGE);
                     } else {
                         JOptionPane.showMessageDialog(null, ex.getMessage());
                     }
                 }

             } else {

                 grauConservacao = new GrauConservacaoM();
                 grauConservacao.setDescricao(tdfDescricaoConservacao.getText());
                 grauConservacao.setId(Integer.parseInt(tfdIDConservacao.getText()));

                 try {
                     idHistorico = grauConservacao.getId();
                     acao = "Alterar Grau de Conservação";
                     descricaoHistorico = grauConservacao.getDescricao();
                     salvaHistorico();
                     grauConservacaoDAO.alterar(grauConservacao);
                     JOptionPane.showMessageDialog(null, "Conservação alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                     atualizaTabelaConservacao();
                     preparaSalvareCancelarConservacao();
                     desativaCamposConservacao();
                     limpaCamposConservacao();
                 } catch (SQLException ex) {
                     Logger.getLogger(ConservacaoStatusView.class.getName()).log(Level.SEVERE, null, ex);
                     if (ex.getErrorCode() == 1062) {
                         JOptionPane.showMessageDialog(null, "Conservacao já existente.", "Erro", JOptionPane.WARNING_MESSAGE);
                     } else {
                         JOptionPane.showMessageDialog(null, ex.getMessage());
                     }
                 }
             }

        }
    }//GEN-LAST:event_btnSalvarConservacaoKeyPressed

    private void tfdDescricaoStatusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdDescricaoStatusKeyPressed

         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnSalvarStatus.requestFocusInWindow();
        }
    }//GEN-LAST:event_tfdDescricaoStatusKeyPressed

    private void btnSalvarStatusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSalvarStatusKeyPressed

         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
             if (tfdDescricaoStatus.getText().isEmpty()) {
                 JOptionPane.showMessageDialog(null, "Preencha Todos os campos!", "Erro", JOptionPane.WARNING_MESSAGE);
                 tfdDescricaoStatus.requestFocusInWindow();
             } else if (tfdIDStatus.getText().isEmpty()) {
                 status = new StatusM();
                 status.setNome(tfdDescricaoStatus.getText());

                 try {
                     idHistorico = statusDAO.salvar(status);
                     acao = "Novo Status";
                     descricaoHistorico = status.getNome();
                     salvaHistorico();
                     JOptionPane.showMessageDialog(null, "Gravado com Sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                     atualizaTabelaStatus();
                     preparaSalvareCancelarStatus();
                     desativaCamposStatus();
                     limpaCamposStatus();
                 } catch (SQLException ex) {
                     Logger.getLogger(ConservacaoStatusView.class.getName()).log(Level.SEVERE, null, ex);
                     if (ex.getErrorCode() == 1062) {
                         JOptionPane.showMessageDialog(null, "Status já existente.", "Erro", JOptionPane.WARNING_MESSAGE);
                     } else {
                         JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
                     }
                 }

             } else {
                 status = new StatusM();
                 status.setNome(tfdDescricaoStatus.getText());
                 status.setId(Integer.parseInt(tfdIDStatus.getText()));
                 try {
                     idHistorico = status.getId();
                     acao = "Alterar Status";
                     descricaoHistorico = status.getNome();
                     salvaHistorico();
                     statusDAO.alterar(status);
                     JOptionPane.showMessageDialog(null, "Status alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                     atualizaTabelaStatus();
                     preparaSalvareCancelarStatus();
                     desativaCamposStatus();
                     limpaCamposStatus();
                 } catch (SQLException ex) {
                     Logger.getLogger(ConservacaoStatusView.class.getName()).log(Level.SEVERE, null, ex);
                     if (ex.getErrorCode() == 1062) {
                         JOptionPane.showMessageDialog(null, "Status já existente.", "Erro", JOptionPane.WARNING_MESSAGE);
                     } else {
                         JOptionPane.showMessageDialog(null, ex.getMessage());
                     }
                 }

             }
        }
    }//GEN-LAST:event_btnSalvarStatusKeyPressed

    // INÍCIO MÉTODOS DE CONTROLE DE BOTÕES CONSERVAÇÃO
    
     public void limpaCamposConservacaoo() {
        tfdIDConservacao.setText("");
        tdfDescricaoConservacao.setText("");
    }
    
    public void preparaNovoConservacao() {
        btnNovoConservacao.setEnabled(false);
        btnSalvarConservacao.setEnabled(true);
        btnCancelarConservacao.setEnabled(true);
        tbeConservacao.setEnabled(false);
        tbeConservacao.clearSelection();
        tdfDescricaoConservacao.requestFocusInWindow();
    }
    public void preparaSalvareCancelarConservacao() {
        btnNovoConservacao.setEnabled(true);
        btnSalvarConservacao.setEnabled(false); 
        btnCancelarConservacao.setEnabled(false);
        tbeConservacao.setEnabled(true);
    }
    
    public void ativaCamposConservacao() {
        
        tdfDescricaoConservacao.setEnabled(true);
    }
    
    public void desativaCamposConservacao(){
       tdfDescricaoConservacao.setEnabled(false);
       
    } 
     public void preparaAlterarConservacao() {
        btnNovoConservacao.setEnabled(false);
        btnExcluirConservacao.setEnabled(false);
        btnAlterarConservacao.setEnabled(false);
        btnSalvarConservacao.setEnabled(true);
        btnCancelarConservacao.setEnabled(true);
        tbeConservacao.setEnabled(false);
        tbeConservacao.clearSelection();
     }
     public void preparaExcluirConservacao() {
        btnExcluirConservacao.setEnabled(false);
        btnAlterarConservacao.setEnabled(false);
    }
     public void preparaSelecaoTabelaConservacao(){
     btnSalvarConservacao.setEnabled(false);
        btnExcluirConservacao.setEnabled(true);
        btnAlterarConservacao.setEnabled(true);
     }
    
    // FIM MÉTODOS DE CONTROLE DE BOTÕES CONSERVAÇÃO 
       
    // INÍCIO MÉTODOS DE CONTROLE DE BOTÕES STATUS
     
      public void limpaCamposStatus() {
        tfdIDStatus.setText("");
        tfdDescricaoStatus.setText("");
    }
    
    public void preparaNovoStatus() {
        btnNovoStatus.setEnabled(false);
        btnSalvarStatus.setEnabled(true);
        btnCancelarStatus.setEnabled(true);
        btnAlterarStatus.setEnabled(false);
        btnExcluirStatus.setEnabled(false);
        tbeStatus.setEnabled(false);
        tbeStatus.clearSelection();
        tfdDescricaoStatus.requestFocusInWindow();
    }
    public void preparaSalvareCancelarStatus() {
        btnNovoStatus.setEnabled(true);
        btnSalvarStatus.setEnabled(false);
        btnCancelarStatus.setEnabled(false);
        btnAlterarStatus.setEnabled(false);
        btnExcluirStatus.setEnabled(false);
        tbeStatus.setEnabled(true);
    }
    
    public void ativaCamposStatus() {
        tfdDescricaoStatus.setEnabled(true);
        
    }
    
    public void desativaCamposStatus(){
       tfdDescricaoStatus.setEnabled(false);
    } 
     public void preparaAlterarStatus() {
         btnNovoStatus.setEnabled(false);
         btnExcluirStatus.setEnabled(false);
         btnAlterarStatus.setEnabled(false);
         btnSalvarStatus.setEnabled(true);
         btnCancelarStatus.setEnabled(true);
         tbeStatus.setEnabled(false);
         tbeStatus.clearSelection();
       
     }
     public void preparaExcluirStatus() {
        btnExcluirStatus.setEnabled(false);
        btnAlterarStatus.setEnabled(false);
    }
     
    public void preparaSelecaoTabelaStatus() {
        btnSalvarStatus.setEnabled(false);
        btnExcluirStatus.setEnabled(true);
        btnAlterarStatus.setEnabled(true);
    }
    //  FIM MÉTODOS DE CONTROLE DE BOTÕES STATUS
     

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterarConservacao;
    private javax.swing.JButton btnAlterarStatus;
    private javax.swing.JButton btnCancelarConservacao;
    private javax.swing.JButton btnCancelarStatus;
    private javax.swing.JButton btnExcluirConservacao;
    private javax.swing.JButton btnExcluirStatus;
    private javax.swing.JButton btnNovoConservacao;
    private javax.swing.JButton btnNovoStatus;
    private javax.swing.JButton btnSalvarConservacao;
    private javax.swing.JButton btnSalvarStatus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblConservacao;
    private javax.swing.JLabel lblDescricaoConservacao;
    private javax.swing.JLabel lblDescricaoStatus;
    private javax.swing.JLabel lblIDConservacao;
    private javax.swing.JLabel lblIDStatus;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTable tbeConservacao;
    private javax.swing.JTable tbeStatus;
    private javax.swing.JTextField tdfDescricaoConservacao;
    private javax.swing.JTextField tfdDescricaoStatus;
    private javax.swing.JTextField tfdIDConservacao;
    private javax.swing.JTextField tfdIDStatus;
    // End of variables declaration//GEN-END:variables
}
