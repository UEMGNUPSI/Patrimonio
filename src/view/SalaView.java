/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.BlocoDAO;
import dao.PisoDAO;
import dao.SalaDAO;
import dao.UnidadeDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.BlocoM;
import model.PisoM;
import model.SalaM;
import model.UnidadeM;
import util.LimiteDigitos;

/**
 * UNIVERSIDADE DO ESTADO DE MINAS GERAIS - Unidade Frutal
 * @author NUPSI - Núcle de Práticas em Sistemas de Informação
 * Equipe: Gustavo Pinoti,Leopoldo Ferreira, Marlon Moro, Murillo Cuervo
 */
public class SalaView extends javax.swing.JInternalFrame {

    SalaM sala;
    SalaDAO salaDAO;
    List<SalaM> listaSala;
    PisoDAO pisoDAO;
    BlocoDAO blocoDAO;
    UnidadeDAO unidadeDAO;
    List<BlocoM> listaBloco;
    List<UnidadeM> listaUnidade;
    List<PisoM> listaPiso;

    /**
     * Creates new form SalaView
     */
    public SalaView() {
        salaDAO = new SalaDAO();
        listaSala = new ArrayList<>();
        pisoDAO = new PisoDAO();
        blocoDAO = new BlocoDAO();
        unidadeDAO = new UnidadeDAO();
        listaUnidade = new ArrayList<>();
        listaBloco = new ArrayList<>();
        listaPiso = new ArrayList<>();
        initComponents();
        this.setVisible(true);
        atualizaTabelaSala();
        atualizaBoxUnidade();
        btnExcluirSala.setEnabled(false);
        btnAlterarSala.setEnabled(false);
        tfdDescricaoSala.setDocument(new LimiteDigitos(45));
    }

    public void atualizaTabelaSala() {
        try {
            listaSala = salaDAO.listaTodos();
        } catch (SQLException ex) {
            Logger.getLogger(EntidadeView.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[listaSala.size()][5];
        int i = 0;
        for (SalaM sal : listaSala) {
            dados[i][0] = String.valueOf(sal.getId());
            dados[i][1] = sal.getDescricao();
            dados[i][2] = sal.getPiso().getDescricao();
            dados[i][3] = sal.getPiso().getBloco().getDescricao();
            dados[i][4] = sal.getPiso().getBloco().getUnidadeM().getNome();
            i++;
        }
        String tituloColuna[] = {"ID", "Nome", "Piso Pertencente", "Bloco Pertencente", "Unidade"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        tbeSala.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tbeSala.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbeSala.getColumnModel().getColumn(1).setPreferredWidth(200);
        tbeSala.getColumnModel().getColumn(2).setPreferredWidth(200);
        tbeSala.getColumnModel().getColumn(3).setPreferredWidth(200);

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tbeSala.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        tbeSala.setRowHeight(25);
        tbeSala.updateUI();
    }

    public void atualizaBoxUnidade() {
        cbxUnidade1.removeAllItems();
        cbxUnidade1.addItem("--Selecione--");
        try {
            listaUnidade = unidadeDAO.listaTodos();
        } catch (SQLException ex) {
            Logger.getLogger(EntidadeView.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[listaUnidade.size()][5];
        int i = 0;
        for (UnidadeM uni : listaUnidade) {
            cbxUnidade1.addItem(uni.getNome());
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbeSala = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblDescricaoSala = new javax.swing.JLabel();
        btnSalvarSala = new javax.swing.JButton();
        tfdIDSala = new javax.swing.JTextField();
        btnNovoSala = new javax.swing.JButton();
        tfdDescricaoSala = new javax.swing.JTextField();
        cbxPiso1 = new javax.swing.JComboBox<>();
        cbxUnidade1 = new javax.swing.JComboBox<>();
        lblSelecPiso3 = new javax.swing.JLabel();
        lblSelecUnidade1 = new javax.swing.JLabel();
        lblSelecBloco1 = new javax.swing.JLabel();
        cbxBloco1 = new javax.swing.JComboBox<>();
        btnAlterarSala = new javax.swing.JButton();
        lblIDSala = new javax.swing.JLabel();
        btnCancelarSala = new javax.swing.JButton();
        btnExcluirSala = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastrar Sala");

        tbeSala.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Descrição"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbeSala.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbeSalaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbeSala);

        lblDescricaoSala.setText("Descrição");

        btnSalvarSala.setText("Salvar");
        btnSalvarSala.setEnabled(false);
        btnSalvarSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarSalaActionPerformed(evt);
            }
        });

        tfdIDSala.setEditable(false);
        tfdIDSala.setEnabled(false);
        tfdIDSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfdIDSalaActionPerformed(evt);
            }
        });

        btnNovoSala.setText("Novo");
        btnNovoSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoSalaActionPerformed(evt);
            }
        });

        tfdDescricaoSala.setEnabled(false);

        cbxPiso1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Piso 1" }));
        cbxPiso1.setEnabled(false);
        cbxPiso1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxPiso1ActionPerformed(evt);
            }
        });

        cbxUnidade1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UEMG", "Cadastrar Unidade" }));
        cbxUnidade1.setEnabled(false);
        cbxUnidade1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxUnidade1ActionPerformed(evt);
            }
        });

        lblSelecPiso3.setText("Selecione o piso:");

        lblSelecUnidade1.setText("Selecione a unidade:");

        lblSelecBloco1.setText("Selecione o bloco:");

        cbxBloco1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bloco A", "Cadastrar Bloco" }));
        cbxBloco1.setEnabled(false);
        cbxBloco1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxBloco1ActionPerformed(evt);
            }
        });

        btnAlterarSala.setText("Alterar");
        btnAlterarSala.setEnabled(false);
        btnAlterarSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarSalaActionPerformed(evt);
            }
        });

        lblIDSala.setText("ID");

        btnCancelarSala.setText("Cancelar");
        btnCancelarSala.setEnabled(false);
        btnCancelarSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarSalaActionPerformed(evt);
            }
        });

        btnExcluirSala.setText("Excluir");
        btnExcluirSala.setEnabled(false);
        btnExcluirSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirSalaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIDSala)
                            .addComponent(tfdIDSala, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfdDescricaoSala)
                            .addComponent(cbxUnidade1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSelecBloco1)
                                    .addComponent(cbxBloco1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblSelecPiso3)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(cbxPiso1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnNovoSala, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSalvarSala, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCancelarSala)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAlterarSala, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnExcluirSala, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSelecUnidade1)
                                    .addComponent(lblDescricaoSala))
                                .addGap(94, 94, 94)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSelecUnidade1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxUnidade1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSelecBloco1)
                    .addComponent(lblSelecPiso3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxBloco1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxPiso1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIDSala, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdIDSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricaoSala)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdDescricaoSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovoSala)
                    .addComponent(btnSalvarSala)
                    .addComponent(btnCancelarSala)
                    .addComponent(btnAlterarSala)
                    .addComponent(btnExcluirSala))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 852, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfdIDSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfdIDSalaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfdIDSalaActionPerformed

    private void cbxUnidade1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxUnidade1ActionPerformed
        if (cbxUnidade1.getSelectedIndex() < 1) {
            cbxBloco1.removeAllItems();
            cbxBloco1.addItem("--Selecione--");

        } else {
            cbxBloco1.removeAllItems();
            cbxBloco1.addItem("--Selecione--");
            UnidadeM unid = new UnidadeM();
            try {
                unid = unidadeDAO.buscaNome(cbxUnidade1.getSelectedItem().toString());
                listaBloco = blocoDAO.buscaUni(unid.getId());
                for (BlocoM bloc : listaBloco) {
                    cbxBloco1.addItem(bloc.getDescricao());
                }
                cbxBloco1.requestFocusInWindow();
            } catch (SQLException ex) {
                cbxBloco1.removeAllItems();
                cbxBloco1.addItem("--Selecione--");
            }
        }
    }//GEN-LAST:event_cbxUnidade1ActionPerformed

    private void cbxBloco1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxBloco1ActionPerformed
        if (cbxBloco1.getSelectedIndex() < 1) {
            cbxPiso1.removeAllItems();
            cbxPiso1.addItem("--Selecione--");
        } else {
            cbxPiso1.removeAllItems();
            cbxPiso1.addItem("--Selecione--");
            BlocoM bloc = new BlocoM();

            try {
                bloc = blocoDAO.buscaNome(cbxBloco1.getSelectedItem().toString());
                listaPiso = pisoDAO.buscaBloc(bloc.getId());
                for (PisoM pis : listaPiso) {
                    cbxPiso1.addItem(pis.getDescricao());
                }
                cbxPiso1.requestFocusInWindow();
            } catch (SQLException ex) {
                cbxPiso1.removeAllItems();
                cbxPiso1.addItem("--Selecione--");
            }
        }
    }//GEN-LAST:event_cbxBloco1ActionPerformed

    private void btnSalvarSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarSalaActionPerformed
        if (tfdDescricaoSala.getText().isEmpty() || cbxUnidade1.getSelectedIndex() == 0 || cbxBloco1.getSelectedIndex() == 0 || cbxPiso1.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Prencha todos os campos.", "Erro", JOptionPane.WARNING_MESSAGE);
            tfdDescricaoSala.requestFocusInWindow();
        } else if (tfdIDSala.getText().isEmpty()){
            sala = new SalaM();
            sala.setDescricao(tfdDescricaoSala.getText());
            sala.setPiso(pegaSala());
            try {
                salaDAO.salvar(sala);
            } catch (SQLException ex) {
                Logger.getLogger(EntidadeView.class.getName()).log(Level.SEVERE, null, ex);
            }
            atualizaTabelaSala();
            preparaSalvareCancelar();
            desativaCampos();
            limpaCamposSala();
        } else{

            sala = new SalaM();
            sala.setDescricao(tfdDescricaoSala.getText());
            sala.setPiso(pegaSala());
            sala.setId(Integer.parseInt(tfdIDSala.getText()));
            btnSalvarSala.setEnabled(true);
            try {
                salaDAO.alterar(sala);
                JOptionPane.showMessageDialog(null, "Alterado com Sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(EntidadeView.class.getName()).log(Level.SEVERE, null, ex);
            }
            atualizaTabelaSala();
            preparaSalvareCancelar();
            desativaCampos();
            limpaCamposSala();
        }
       
    }//GEN-LAST:event_btnSalvarSalaActionPerformed

    private void btnExcluirSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirSalaActionPerformed
        if (tfdIDSala.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione um Piso.", "Erro", JOptionPane.WARNING_MESSAGE);
        } else {
            sala = new SalaM();
            sala.setId(Integer.parseInt(tfdIDSala.getText()));
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja Excluir: " + tfdDescricaoSala.getText() + " ?");
            if (confirma == 0) {
                try {
                    salaDAO.excluir(sala);
                    atualizaTabelaSala();
                    limpaCamposSala();
                    btnExcluirSala.setEnabled(false);
                    btnAlterarSala.setEnabled(false);
                    btnSalvarSala.setEnabled(true);
                } catch (SQLException ex) {
                    Logger.getLogger(EntidadeView.class.getName()).log(Level.SEVERE, null, ex);
                    if (ex.getErrorCode() == 1451) {
                        JOptionPane.showMessageDialog(null, "Impossível deletar esta sala, pois possuem patrimônios cadastrados.", "Erro", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
                    }
                }

            }
        }
        limpaCamposSala();
        atualizaTabelaSala();
        preparaExcluir();
        desativaCampos();
    }//GEN-LAST:event_btnExcluirSalaActionPerformed

    private void tbeSalaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbeSalaMouseClicked
    
        limpaCamposSala();
        tfdIDSala.setText(tbeSala.getValueAt(tbeSala.getSelectedRow(), 0).toString());
        tfdDescricaoSala.setText(tbeSala.getValueAt(tbeSala.getSelectedRow(), 1).toString());
        cbxUnidade1.setSelectedItem(tbeSala.getValueAt(tbeSala.getSelectedRow(), 4).toString());
        cbxBloco1.setSelectedItem(tbeSala.getValueAt(tbeSala.getSelectedRow(), 3).toString());
        cbxPiso1.setSelectedItem(tbeSala.getValueAt(tbeSala.getSelectedRow(), 2).toString());
        preparaSelecaoTabelaSala();
    }//GEN-LAST:event_tbeSalaMouseClicked

    private void btnNovoSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoSalaActionPerformed
        limpaCamposSala();
        preparaNovo();
        ativaCampos();
               
    }//GEN-LAST:event_btnNovoSalaActionPerformed

    private void btnAlterarSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarSalaActionPerformed
        preparaAlterar();
        ativaCampos();
        /*if (tfdIDSala.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione uma Sala", "Erro", JOptionPane.WARNING_MESSAGE);
        } else {
            sala = new SalaM();
            sala.setId(Integer.parseInt(tfdIDSala.getText()));
            sala.setDescricao(tfdDescricaoSala.getText());

            try {
                salaDAO.alterar(sala);
                JOptionPane.showMessageDialog(null, "Sala atualizada com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                atualizaTabelaSala();
                btnAlterarSala.setEnabled(false);
                btnExcluirSala.setEnabled(false);
                btnSalvarSala.setEnabled(true);
                limpaCamposSala();
            } catch (SQLException ex) {
                Logger.getLogger(EntidadeView.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
            }

        }*/
    }//GEN-LAST:event_btnAlterarSalaActionPerformed

    private void cbxPiso1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxPiso1ActionPerformed
        // TODO add your handling code here:
        tfdDescricaoSala.requestFocusInWindow();
    }//GEN-LAST:event_cbxPiso1ActionPerformed

    private void btnCancelarSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarSalaActionPerformed
        // TODO add your handling code here:
        limpaCamposSala();
        preparaSalvareCancelar();
        desativaCampos();
    }//GEN-LAST:event_btnCancelarSalaActionPerformed

    public PisoM pegaSala() {
        try {
            return pisoDAO.buscaNome(cbxPiso1.getSelectedItem().toString());
        } catch (SQLException ex) {
            Logger.getLogger(BlocoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
     // INÍCIO MÉTODOS DE CONTROLE DE BOTÕES 
    
    public void limpaCamposSala() {
        cbxUnidade1.setSelectedIndex(0);
        tfdDescricaoSala.setText("");
        tfdIDSala.setText("");
    }
    
    public void preparaNovo() {
        btnNovoSala.setEnabled(false);
        btnSalvarSala.setEnabled(true);
        btnCancelarSala.setEnabled(true);
        btnAlterarSala.setEnabled(false);
        btnExcluirSala.setEnabled(false);
        tbeSala.setEnabled(false);
        tbeSala.clearSelection();
        cbxUnidade1.setEnabled(true);
        cbxBloco1.setEnabled(true);
        cbxPiso1.setEnabled(true);
        cbxUnidade1.requestFocusInWindow();
        
    }
    public void preparaSalvareCancelar() {
        btnNovoSala.setEnabled(true);
        btnSalvarSala.setEnabled(false);
        btnCancelarSala.setEnabled(false);
        tbeSala.setEnabled(true);
    }
    
    public void ativaCampos() {
        tfdIDSala.setEnabled(true);
        tfdDescricaoSala.setEnabled(true);
    }
    
    public void desativaCampos(){
       cbxUnidade1.setSelectedIndex(0);
       cbxUnidade1.setEnabled(false);
       cbxBloco1.setSelectedIndex(0);
       cbxBloco1.setEnabled(false);
       cbxPiso1.setSelectedIndex(0);
       cbxPiso1.setEnabled(false);
       tfdIDSala.setText("");
       tfdIDSala.setEnabled(false);
       tfdDescricaoSala.setEnabled(false);
       btnSalvarSala.setEnabled(false);
       btnCancelarSala.setEnabled(false);
       btnAlterarSala.setEnabled(false);
       btnExcluirSala.setEnabled(false);
    } 
    public void preparaAlterar() {
        btnNovoSala.setEnabled(false);
        btnExcluirSala.setEnabled(false);
        btnAlterarSala.setEnabled(false);
        btnSalvarSala.setEnabled(true);
        btnCancelarSala.setEnabled(true);
        cbxUnidade1.setEnabled(false);
        cbxBloco1.setEnabled(false);
        cbxPiso1.setEnabled(false);
        tbeSala.setEnabled(false);        
        tbeSala.clearSelection();
        tfdDescricaoSala.requestFocusInWindow();
    
    }
    public void preparaExcluir() {
        btnExcluirSala.setEnabled(false);
        btnAlterarSala.setEnabled(false);
    }
     
    public void preparaSelecaoTabelaSala(){
        btnNovoSala.setEnabled(true);
        btnExcluirSala.setEnabled(true);
        btnAlterarSala.setEnabled(true);
         
    
    }
    
     
     // FIM MÉTODOS DE CONTROLE DE BOTÕES 
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterarSala;
    private javax.swing.JButton btnCancelarSala;
    private javax.swing.JButton btnExcluirSala;
    private javax.swing.JButton btnNovoSala;
    private javax.swing.JButton btnSalvarSala;
    private javax.swing.JComboBox<String> cbxBloco1;
    private javax.swing.JComboBox<String> cbxPiso1;
    private javax.swing.JComboBox<String> cbxUnidade1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescricaoSala;
    private javax.swing.JLabel lblIDSala;
    private javax.swing.JLabel lblSelecBloco1;
    private javax.swing.JLabel lblSelecPiso3;
    private javax.swing.JLabel lblSelecUnidade1;
    private javax.swing.JTable tbeSala;
    private javax.swing.JTextField tfdDescricaoSala;
    private javax.swing.JTextField tfdIDSala;
    // End of variables declaration//GEN-END:variables
}
