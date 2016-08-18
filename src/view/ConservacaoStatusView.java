/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.GrauConservacaoDAO;
import dao.StatusDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.GrauConservacaoM;
import model.StatusM;

/**
 *
 * @author Leopoldo
 */
public class ConservacaoStatusView extends javax.swing.JInternalFrame {

    /**
     * Creates new form ConservacaoStatusView
     */
    public ConservacaoStatusView() {
        grauConservacaoDAO = new GrauConservacaoDAO();
        statusDAO = new StatusDAO();

        listaGrauConservacao = new ArrayList<>();
        listaStatus = new ArrayList<>();

        initComponents();
        this.setVisible(true);
        atualizaTabelaConservacao();
        atualizaTabelaStatus();
        limpaCamposConservacao();
        limpaStatus();

    }

    GrauConservacaoM grauConservacao;
    StatusM status;
    StatusDAO statusDAO;
    GrauConservacaoDAO grauConservacaoDAO;
    List<StatusM> listaStatus;
    List<GrauConservacaoM> listaGrauConservacao;

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

    public void limpaCamposConservacao() {
        tfdIDConservacao.setText("");
        tdfDescricaoConservacao.setText("");
        btnAdicionarConservacao.setEnabled(true);
        btnExcluirConservacao.setEnabled(false);
        btnAlterarConservacao.setEnabled(false);
    }

    public void limpaStatus() {
        tfdIDStatus.setText("");
        tfdDescricaoStatus.setText("");
        btnAdicionarStatus.setEnabled(true);
        btnExcluirStatus.setEnabled(false);
        btnAlterarStatus.setEnabled(false);
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
        btnLimpaStatus = new javax.swing.JButton();
        btnAlterarStatus = new javax.swing.JButton();
        lblDescricaoConservacao = new javax.swing.JLabel();
        btnLimpaConservacao = new javax.swing.JButton();
        btnExcluirStatus = new javax.swing.JButton();
        lblConservacao = new javax.swing.JLabel();
        btnAdicionarStatus = new javax.swing.JButton();
        tfdDescricaoStatus = new javax.swing.JTextField();
        lblDescricaoStatus = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        spdTipoSubtipo = new javax.swing.JSeparator();
        lblIDStatus = new javax.swing.JLabel();
        btnAdicionarConservacao = new javax.swing.JButton();
        tfdIDStatus = new javax.swing.JTextField();
        btnExcluirConservacao = new javax.swing.JButton();
        tfdIDConservacao = new javax.swing.JTextField();
        btnAlterarConservacao = new javax.swing.JButton();

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

        btnLimpaStatus.setText("Novo");
        btnLimpaStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpaStatusActionPerformed(evt);
            }
        });

        btnAlterarStatus.setText("Alterar");
        btnAlterarStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarStatusActionPerformed(evt);
            }
        });

        lblDescricaoConservacao.setText("Descrição");

        btnLimpaConservacao.setText("Novo");
        btnLimpaConservacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpaConservacaoActionPerformed(evt);
            }
        });

        btnExcluirStatus.setText("Excluir");
        btnExcluirStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirStatusActionPerformed(evt);
            }
        });

        lblConservacao.setText("Grau de Conservação");

        btnAdicionarStatus.setText("Adicionar");
        btnAdicionarStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarStatusActionPerformed(evt);
            }
        });

        lblDescricaoStatus.setText("Descrição");

        lblStatus.setText("Status de Patrimônio");

        lblIDStatus.setText("ID");

        btnAdicionarConservacao.setText("Adicionar");
        btnAdicionarConservacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarConservacaoActionPerformed(evt);
            }
        });

        tfdIDStatus.setEditable(false);

        btnExcluirConservacao.setText("Excluir");
        btnExcluirConservacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirConservacaoActionPerformed(evt);
            }
        });

        tfdIDConservacao.setEditable(false);

        btnAlterarConservacao.setText("Alterar");
        btnAlterarConservacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarConservacaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(lblConservacao))
                    .addComponent(lblDescricaoConservacao)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(lblStatus))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(lblIDConservacao))
                            .addComponent(tfdIDConservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAdicionarConservacao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExcluirConservacao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAlterarConservacao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLimpaConservacao)))
                        .addComponent(tdfDescricaoConservacao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(spdTipoSubtipo)
                    .addComponent(tfdDescricaoStatus)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblDescricaoStatus)
                        .addComponent(tfdIDStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblIDStatus)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnAdicionarStatus)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnExcluirStatus)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnAlterarStatus)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnLimpaStatus))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(btnAdicionarConservacao)
                    .addComponent(btnExcluirConservacao)
                    .addComponent(btnAlterarConservacao)
                    .addComponent(btnLimpaConservacao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spdTipoSubtipo, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIDStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdIDStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricaoStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdDescricaoStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionarStatus)
                    .addComponent(btnExcluirStatus)
                    .addComponent(btnAlterarStatus)
                    .addComponent(btnLimpaStatus))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarConservacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarConservacaoActionPerformed
        // TODO add your handling code here:
        if (tdfDescricaoConservacao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha Todos os campos!", "Erro", JOptionPane.WARNING_MESSAGE);
            tdfDescricaoConservacao.requestFocusInWindow();
        } else {
            grauConservacao = new GrauConservacaoM();
            grauConservacao.setDescricao(tdfDescricaoConservacao.getText());

            try {
                grauConservacaoDAO.salvar(grauConservacao);
                JOptionPane.showMessageDialog(null, "Gravado com Sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                atualizaTabelaConservacao();
                limpaCamposConservacao();
            } catch (SQLException ex) {
                Logger.getLogger(ConservacaoStatusView.class.getName()).log(Level.SEVERE, null, ex);
                if (ex.getErrorCode() == 1062) {
                    JOptionPane.showMessageDialog(null, "Conservacao já existente", "Erro", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }

        }


    }//GEN-LAST:event_btnAdicionarConservacaoActionPerformed

    private void btnExcluirConservacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirConservacaoActionPerformed
        // TODO add your handling code here:
        if (tfdIDConservacao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione um Grau de Concervação!", "Erro", JOptionPane.WARNING_MESSAGE);
        } else {
            grauConservacao = new GrauConservacaoM();
            grauConservacao.setId(Integer.parseInt(tfdIDConservacao.getText()));
            int confirma = JOptionPane.showConfirmDialog(null, "Dejesa Excluir: " + tdfDescricaoConservacao.getText());
            if (confirma == 0) {
                try {
                    grauConservacaoDAO.excluir(grauConservacao);
                    limpaCamposConservacao();
                    atualizaTabelaConservacao();
                } catch (SQLException ex) {
                    Logger.getLogger(ConservacaoStatusView.class.getName()).log(Level.SEVERE, null, ex);
                    if (ex.getErrorCode() == 1451) {
                        JOptionPane.showMessageDialog(null, "Impossível excluir essa Conservação, ela já possui Patrimonios cadastrados!", "Erro", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
                    }
                }

            }
        }
    }//GEN-LAST:event_btnExcluirConservacaoActionPerformed

    private void btnAlterarConservacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarConservacaoActionPerformed
        // TODO add your handling code here:
        if (tfdIDConservacao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione Grau de Conservação!", "Erro", JOptionPane.WARNING_MESSAGE);
        } else {
            grauConservacao = new GrauConservacaoM();
            grauConservacao.setDescricao(tdfDescricaoConservacao.getText());
            grauConservacao.setId(Integer.parseInt(tfdIDConservacao.getText()));
        }
        try {
            grauConservacaoDAO.alterar(grauConservacao);
            JOptionPane.showMessageDialog(null, "Conservação alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            atualizaTabelaConservacao();
            limpaCamposConservacao();
        } catch (SQLException ex) {
            Logger.getLogger(ConservacaoStatusView.class.getName()).log(Level.SEVERE, null, ex);
            if (ex.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "Conservacao já existente", "Erro", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

    }//GEN-LAST:event_btnAlterarConservacaoActionPerformed

    private void btnLimpaConservacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpaConservacaoActionPerformed
        // TODO add your handling code here:
        limpaCamposConservacao();
    }//GEN-LAST:event_btnLimpaConservacaoActionPerformed


    private void btnAdicionarStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarStatusActionPerformed
        // TODO add your handling code here:
        if (tfdDescricaoStatus.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha Todos os campos!", "Erro", JOptionPane.WARNING_MESSAGE);
            tfdDescricaoStatus.requestFocusInWindow();
        } else {
            status = new StatusM();
            status.setNome(tfdDescricaoStatus.getText());

            try {
                statusDAO.salvar(status);
                JOptionPane.showMessageDialog(null, "Gravado com Sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                atualizaTabelaStatus();
                limpaStatus();
            } catch (SQLException ex) {
                Logger.getLogger(ConservacaoStatusView.class.getName()).log(Level.SEVERE, null, ex);
                if (ex.getErrorCode() == 1062) {
                    JOptionPane.showMessageDialog(null, "Status já existente", "Erro", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
                }
            }

        }
    }//GEN-LAST:event_btnAdicionarStatusActionPerformed

    private void btnExcluirStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirStatusActionPerformed

        if (tfdIDStatus.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione um Status de Patrimônio!", "Erro", JOptionPane.WARNING_MESSAGE);

        } else {
            status = new StatusM();
            status.setId(Integer.parseInt(tfdIDStatus.getText()));
            int confirma = JOptionPane.showConfirmDialog(null, "Dejesa Excluir: " + tfdDescricaoStatus.getText());
            if (confirma == 0) {
                try {
                    statusDAO.excluir(status);
                    limpaStatus();
                    atualizaTabelaStatus();
                } catch (SQLException ex) {
                    Logger.getLogger(ConservacaoStatusView.class.getName()).log(Level.SEVERE, null, ex);
                    if (ex.getErrorCode() == 1451) {
                        JOptionPane.showMessageDialog(null, "Impossível excluir esse Status, ele já possui Patrimonio cadastrados!", "Erro", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }

            }
        }
    }//GEN-LAST:event_btnExcluirStatusActionPerformed

    private void btnAlterarStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarStatusActionPerformed
        if (tfdIDStatus.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione Grau de Conservação!", "Erro", JOptionPane.WARNING_MESSAGE);
        } else {
            status = new StatusM();
            status.setNome(tfdDescricaoStatus.getText());
            status.setId(Integer.parseInt(tfdIDStatus.getText()));

        }
        try {
            statusDAO.alterar(status);
            JOptionPane.showMessageDialog(null, "Status alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limpaStatus();
            atualizaTabelaStatus();
        } catch (SQLException ex) {
            Logger.getLogger(ConservacaoStatusView.class.getName()).log(Level.SEVERE, null, ex);
            if (ex.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "Status já existente", "Erro", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }


    }//GEN-LAST:event_btnAlterarStatusActionPerformed

    private void btnLimpaStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpaStatusActionPerformed

        limpaStatus();
    }//GEN-LAST:event_btnLimpaStatusActionPerformed

    private void tbeConservacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbeConservacaoMouseClicked
        // TODO add your handling code here:
        tfdIDConservacao.setText(tbeConservacao.getValueAt(tbeConservacao.getSelectedRow(), 0).toString());
        tdfDescricaoConservacao.setText(tbeConservacao.getValueAt(tbeConservacao.getSelectedRow(), 1).toString());
        btnAdicionarConservacao.setEnabled(false);
        btnExcluirConservacao.setEnabled(true);
        btnAlterarConservacao.setEnabled(true);

    }//GEN-LAST:event_tbeConservacaoMouseClicked

    private void tbeStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbeStatusMouseClicked
        // TODO add your handling code here:
        tfdIDStatus.setText(tbeStatus.getValueAt(tbeStatus.getSelectedRow(), 0).toString());
        tfdDescricaoStatus.setText(tbeStatus.getValueAt(tbeStatus.getSelectedRow(), 1).toString());
        btnAdicionarStatus.setEnabled(false);
        btnExcluirStatus.setEnabled(true);
        btnAlterarStatus.setEnabled(true);
    }//GEN-LAST:event_tbeStatusMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarConservacao;
    private javax.swing.JButton btnAdicionarStatus;
    private javax.swing.JButton btnAlterarConservacao;
    private javax.swing.JButton btnAlterarStatus;
    private javax.swing.JButton btnExcluirConservacao;
    private javax.swing.JButton btnExcluirStatus;
    private javax.swing.JButton btnLimpaConservacao;
    private javax.swing.JButton btnLimpaStatus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblConservacao;
    private javax.swing.JLabel lblDescricaoConservacao;
    private javax.swing.JLabel lblDescricaoStatus;
    private javax.swing.JLabel lblIDConservacao;
    private javax.swing.JLabel lblIDStatus;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JSeparator spdTipoSubtipo;
    private javax.swing.JTable tbeConservacao;
    private javax.swing.JTable tbeStatus;
    private javax.swing.JTextField tdfDescricaoConservacao;
    private javax.swing.JTextField tfdDescricaoStatus;
    private javax.swing.JTextField tfdIDConservacao;
    private javax.swing.JTextField tfdIDStatus;
    // End of variables declaration//GEN-END:variables
}
