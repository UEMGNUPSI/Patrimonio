/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.BlocoDAO;
import dao.PatrimonioDAO;
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
import model.PatrimonioM;
import model.PisoM;
import model.SalaM;
import model.UnidadeM;

/**
 *
 * @author NUPSI-01
 */
public class MovimentacaoView extends javax.swing.JInternalFrame {

    /**
     * Creates new form MovimentacaoView
     */
    
    List<PatrimonioM> listaPatrimonio;
    List<UnidadeM> listaUnidade;
    UnidadeDAO unidadeDAO;
    List<BlocoM> listaBloco;
    BlocoDAO blocoDAO;
    List<PisoM> listaPiso;
    PatrimonioDAO patrimonioDAO;
    PisoDAO pisoDAO;
    PisoM pisoM;
    BlocoM blocoM;
    UnidadeM unidM;
    SalaDAO salaDAO;
    SalaM salaM;
    List<SalaM> listaSala;
    List<PatrimonioM> listaPatrimonioSelecionados;
    List<PatrimonioM> listaMovimentacao;
    PatrimonioM patrimonio;
    
    public MovimentacaoView() {
        initComponents();
        
        this.setVisible(true);
        
        listaPatrimonio = new ArrayList<>();
        patrimonioDAO = new PatrimonioDAO();
        listaUnidade = new ArrayList<>();
        unidadeDAO = new UnidadeDAO();
        listaBloco = new ArrayList<>();
        blocoDAO = new BlocoDAO();
        listaPiso = new ArrayList<>();
        pisoDAO = new PisoDAO();
        blocoM = new BlocoM();
        unidM = new UnidadeM();
        salaDAO = new SalaDAO();
        salaM = new SalaM();
        listaSala = new ArrayList<>();
        pisoM = new PisoM();
        listaPatrimonioSelecionados = new ArrayList<>();
        listaMovimentacao = new ArrayList<>();
        
        atualizaTabelaEsquerda();
        consertaTamanhoTabelaDireita();
        
        atualizaBoxUnidade();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tbeDireita = new javax.swing.JTable();
        btnEsquerdaDireita = new javax.swing.JButton();
        btnDireitaEsquerda = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbeEsquerda = new javax.swing.JTable();
        btnEfetuarMovimentacao = new javax.swing.JButton();
        cbxUnidadeDestino = new javax.swing.JComboBox<>();
        cbxBlocoDestino = new javax.swing.JComboBox<>();
        cbxSalaDestino = new javax.swing.JComboBox<>();
        cbxPisoDestino = new javax.swing.JComboBox<>();
        cbxPiso = new javax.swing.JComboBox<>();
        cbxBloco = new javax.swing.JComboBox<>();
        cbxUnidade = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnCancelarMovimentacao = new javax.swing.JButton();
        cbxSala = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setPreferredSize(new java.awt.Dimension(1130, 645));
        setRequestFocusEnabled(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(1130, 645));
        jPanel1.setRequestFocusEnabled(false);

        tbeDireita.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Codigo", "Descrição", "Conservação"
            }
        ));
        jScrollPane2.setViewportView(tbeDireita);

        btnEsquerdaDireita.setText(">>");
        btnEsquerdaDireita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEsquerdaDireitaActionPerformed(evt);
            }
        });

        btnDireitaEsquerda.setText("<<");
        btnDireitaEsquerda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDireitaEsquerdaActionPerformed(evt);
            }
        });

        tbeEsquerda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbeEsquerda);

        btnEfetuarMovimentacao.setText("Efetuar Movimentação");
        btnEfetuarMovimentacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEfetuarMovimentacaoActionPerformed(evt);
            }
        });

        cbxUnidadeDestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxUnidadeDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxUnidadeDestinoActionPerformed(evt);
            }
        });

        cbxBlocoDestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxBlocoDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxBlocoDestinoActionPerformed(evt);
            }
        });

        cbxSalaDestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxSalaDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSalaDestinoActionPerformed(evt);
            }
        });

        cbxPisoDestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxPisoDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxPisoDestinoActionPerformed(evt);
            }
        });

        cbxPiso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxPiso.setPreferredSize(new java.awt.Dimension(253, 20));
        cbxPiso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxPisoActionPerformed(evt);
            }
        });

        cbxBloco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxBloco.setPreferredSize(new java.awt.Dimension(275, 20));
        cbxBloco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxBlocoActionPerformed(evt);
            }
        });

        cbxUnidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxUnidade.setPreferredSize(new java.awt.Dimension(253, 20));
        cbxUnidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxUnidadeActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Unidade: ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Bloco: ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Piso: ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Sala: ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Unidade: ");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Bloco: ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Piso: ");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Sala: ");

        btnCancelarMovimentacao.setText("Cancelar Movimentação");

        cbxSala.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxSala.setPreferredSize(new java.awt.Dimension(253, 20));
        cbxSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSalaActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Sala Saída");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Sala Destino");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cbxPiso, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbxBloco, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbxSala, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(cbxUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEsquerdaDireita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDireitaEsquerda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEfetuarMovimentacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancelarMovimentacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbxSalaDestino, javax.swing.GroupLayout.Alignment.TRAILING, 0, 253, Short.MAX_VALUE)
                            .addComponent(cbxPisoDestino, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxBlocoDestino, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxUnidadeDestino, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxUnidadeDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbxBlocoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxPisoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxSalaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxBloco, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxPiso, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxSala, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(btnEsquerdaDireita)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDireitaEsquerda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEfetuarMovimentacao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelarMovimentacao))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxBlocoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxBlocoActionPerformed
       if (cbxBloco.getSelectedIndex() < 1) {
            cbxPiso.removeAllItems();
            cbxPiso.addItem("--Selecione--");

        } else {
            cbxPiso.removeAllItems();
            cbxPiso.addItem("--Selecione--");
            //UnidadeM unid = new UnidadeM();
            try {
                blocoM = blocoDAO.buscaNome(cbxBloco.getSelectedItem().toString());
                listaPiso = pisoDAO.buscaBloc(blocoM.getId());
                for (PisoM piso : listaPiso) {
                    cbxPiso.addItem(piso.getDescricao());
                }
                cbxPiso.requestFocusInWindow();
            } catch (SQLException ex) {
                cbxPiso.removeAllItems();
                cbxPiso.addItem("--Selecione--");
            }
        }
    }//GEN-LAST:event_cbxBlocoActionPerformed

    
    
    
    private void cbxUnidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxUnidadeActionPerformed
        if (cbxUnidade.getSelectedIndex() < 1) {
            cbxBloco.removeAllItems();
            cbxBloco.addItem("--Selecione--");

        } else {
            cbxBloco.removeAllItems();
            cbxBloco.addItem("--Selecione--");
            
            try {
                unidM = unidadeDAO.buscaNome(cbxUnidade.getSelectedItem().toString());
                listaBloco = blocoDAO.buscaUni(unidM.getId());
                for (BlocoM bloc : listaBloco) {
                    cbxBloco.addItem(bloc.getDescricao());
                }
                cbxBloco.requestFocusInWindow();
            } catch (SQLException ex) {
                cbxBloco.removeAllItems();
                cbxBloco.addItem("--Selecione--");
            }
        }
    }//GEN-LAST:event_cbxUnidadeActionPerformed

    private void cbxPisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxPisoActionPerformed
      if (cbxPiso.getSelectedIndex() < 1) {
            cbxSala.removeAllItems();
            cbxSala.addItem("--Selecione--");
            
        } else {
            cbxSala.removeAllItems();
            cbxSala.addItem("--Selecione--");
            //UnidadeM unid = new UnidadeM();
            try {
                pisoM = pisoDAO.buscaNome(cbxPiso.getSelectedItem().toString());
                listaSala = salaDAO.buscaPis(pisoM.getId());
                for (SalaM sala : listaSala) {
                    cbxSala.addItem(sala.getDescricao());
                }
                cbxSala.requestFocusInWindow();
            } catch (SQLException ex) {
                cbxSala.removeAllItems();
                cbxSala.addItem("--Selecione--");
            }
        }
    }//GEN-LAST:event_cbxPisoActionPerformed

    private void btnEsquerdaDireitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEsquerdaDireitaActionPerformed
       
        try {
            patrimonio = patrimonioDAO.busca(Integer.parseInt(tbeEsquerda.getValueAt(tbeEsquerda.getSelectedRow(), 0).toString()));
            listaMovimentacao.add(patrimonio);
            for(int i = 0; i < listaPatrimonioSelecionados.size(); i++)
            {
                PatrimonioM p = listaPatrimonioSelecionados.get(i);

                if(p.getId() == patrimonio.getId())
                {
                    listaPatrimonioSelecionados.remove(p);
                    break;
                }
            }
             atualizaTabelaSelecionados();
             atualizaTabelaDireita();
        } catch (SQLException ex) {
            Logger.getLogger(MovimentacaoView.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnEsquerdaDireitaActionPerformed

    private void btnDireitaEsquerdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDireitaEsquerdaActionPerformed
        try {
            patrimonio = patrimonioDAO.busca(Integer.parseInt(tbeDireita.getValueAt(tbeDireita.getSelectedRow(), 0).toString()));
            listaPatrimonioSelecionados.add(patrimonio);
            for(int i = 0; i < listaMovimentacao.size(); i++)
            {
                PatrimonioM p = listaMovimentacao.get(i);

                if(p.getId() == patrimonio.getId())
                {
                    listaMovimentacao.remove(p);
                    break;
                }
            }
             atualizaTabelaSelecionados();
             atualizaTabelaDireita();
        } catch (SQLException ex) {
            Logger.getLogger(MovimentacaoView.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnDireitaEsquerdaActionPerformed

    private void cbxBlocoDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxBlocoDestinoActionPerformed
              if (cbxBlocoDestino.getSelectedIndex() < 1) {
            cbxPisoDestino.removeAllItems();
            cbxPisoDestino.addItem("--Selecione--");

        } else {
            cbxPisoDestino.removeAllItems();
            cbxPisoDestino.addItem("--Selecione--");
            //UnidadeM unid = new UnidadeM();
            try {
                blocoM = blocoDAO.buscaNome(cbxBlocoDestino.getSelectedItem().toString());
                listaPiso = pisoDAO.buscaBloc(blocoM.getId());
                for (PisoM piso : listaPiso) {
                    cbxPisoDestino.addItem(piso.getDescricao());
                }
                cbxPisoDestino.requestFocusInWindow();
            } catch (SQLException ex) {
                cbxPisoDestino.removeAllItems();
                cbxPisoDestino.addItem("--Selecione--");
            }
        }
    }//GEN-LAST:event_cbxBlocoDestinoActionPerformed

    private void cbxPisoDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxPisoDestinoActionPerformed
        if (cbxPisoDestino.getSelectedIndex() < 1) {
            cbxSalaDestino.removeAllItems();
            cbxSalaDestino.addItem("--Selecione--");
            
        } else {
            cbxSalaDestino.removeAllItems();
            cbxSalaDestino.addItem("--Selecione--");
            //UnidadeM unid = new UnidadeM();
            try {
                pisoM = pisoDAO.buscaNome(cbxPisoDestino.getSelectedItem().toString());
                listaSala = salaDAO.buscaPis(pisoM.getId());
                for (SalaM sala : listaSala) {
                    cbxSalaDestino.addItem(sala.getDescricao());
                }
                cbxSalaDestino.requestFocusInWindow();
            } catch (SQLException ex) {
                cbxSalaDestino.removeAllItems();
                cbxSalaDestino.addItem("--Selecione--");
            }
        }
    }//GEN-LAST:event_cbxPisoDestinoActionPerformed

    private void cbxUnidadeDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxUnidadeDestinoActionPerformed
         if (cbxUnidadeDestino.getSelectedIndex() < 1) {
            cbxBlocoDestino.removeAllItems();
            cbxBlocoDestino.addItem("--Selecione--");

        } else {
            cbxBlocoDestino.removeAllItems();
            cbxBlocoDestino.addItem("--Selecione--");
            
            try {
                unidM = unidadeDAO.buscaNome(cbxUnidadeDestino.getSelectedItem().toString());
                listaBloco = blocoDAO.buscaUni(unidM.getId());
                for (BlocoM bloc : listaBloco) {
                    cbxBlocoDestino.addItem(bloc.getDescricao());
                }
                cbxBlocoDestino.requestFocusInWindow();
            } catch (SQLException ex) {
                cbxBlocoDestino.removeAllItems();
                cbxBlocoDestino.addItem("--Selecione--");
            }
        }
    }//GEN-LAST:event_cbxUnidadeDestinoActionPerformed

    private void btnEfetuarMovimentacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEfetuarMovimentacaoActionPerformed
        
        try {
            unidM = unidadeDAO.buscaNome(cbxUnidadeDestino.getSelectedItem().toString());//pega a unidade selecionada
            blocoM = blocoDAO.busca_id_unidade(unidM.getId(), cbxBlocoDestino.getSelectedItem().toString());// todos os blocos da unidade de cima
            pisoM = pisoDAO.busca_id_bloco(blocoM.getId(), cbxPisoDestino.getSelectedItem().toString());//todos os pisos da unidade de cima
            salaM = salaDAO.buscaID(pisoM.getId(), cbxSalaDestino.getSelectedItem().toString());
            patrimonioDAO.movimentar(listaMovimentacao, salaM.getId());
            JOptionPane.showMessageDialog(null, "Movimentação realizada com Sucesso!\nSala Destino: "+salaM.getDescricao());
            listaPatrimonioSelecionados = patrimonioDAO.listaSelecionados(salaM.getId());
            listaMovimentacao = new ArrayList<>();
            atualizaTabelaEsquerda();
            atualizaTabelaDireita();
        } catch (SQLException ex) {
            Logger.getLogger(MovimentacaoView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEfetuarMovimentacaoActionPerformed

    
    private void cbxSalaDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSalaDestinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxSalaDestinoActionPerformed

    private void cbxSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSalaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxSalaActionPerformed
    
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarMovimentacao;
    private javax.swing.JButton btnDireitaEsquerda;
    private javax.swing.JButton btnEfetuarMovimentacao;
    private javax.swing.JButton btnEsquerdaDireita;
    private javax.swing.JComboBox<String> cbxBloco;
    private javax.swing.JComboBox<String> cbxBlocoDestino;
    private javax.swing.JComboBox<String> cbxPiso;
    private javax.swing.JComboBox<String> cbxPisoDestino;
    private javax.swing.JComboBox<String> cbxSala;
    private javax.swing.JComboBox<String> cbxSalaDestino;
    private javax.swing.JComboBox<String> cbxUnidade;
    private javax.swing.JComboBox<String> cbxUnidadeDestino;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbeDireita;
    private javax.swing.JTable tbeEsquerda;
    // End of variables declaration//GEN-END:variables
    
    public void consertaTamanhoTabelaDireita(){
        tbeDireita.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbeDireita.getColumnModel().getColumn(1).setPreferredWidth(250);
        tbeDireita.getColumnModel().getColumn(2).setPreferredWidth(80);
        tbeDireita.getColumnModel().getColumn(3).setPreferredWidth(80);

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tbeDireita.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        tbeDireita.setRowHeight(25);
        tbeDireita.updateUI();
    }
    
    public void atualizaTabelaEsquerda() {
        
        String dados[][] = new String[listaPatrimonio.size()][4];
        int i = 0;
        for (PatrimonioM patri : listaPatrimonio) {
            dados[i][0] = String.valueOf(patri.getId());
            dados[i][1] = patri.getCodigo();
            dados[i][2] = patri.getDescricao();
            dados[i][3] = patri.getGrau_conservacao().getDescricao();
            i++;
        }
        String tituloColuna[] = {"ID", "Codigo", "Descrição", "Conservação"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        tbeEsquerda.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tbeEsquerda.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbeEsquerda.getColumnModel().getColumn(1).setPreferredWidth(250);
        tbeEsquerda.getColumnModel().getColumn(2).setPreferredWidth(80);
        tbeEsquerda.getColumnModel().getColumn(3).setPreferredWidth(80);

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tbeEsquerda.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        tbeEsquerda.setRowHeight(25);
        tbeEsquerda.updateUI();
    }
    
    public void atualizaTabelaSelecionados() {
        
        String dados[][] = new String[listaPatrimonioSelecionados.size()][4];
        int i = 0;
        for (PatrimonioM patri : listaPatrimonioSelecionados) {
            dados[i][0] = String.valueOf(patri.getId());
            dados[i][1] = patri.getCodigo();
            dados[i][2] = patri.getDescricao();
            dados[i][3] = patri.getGrau_conservacao().getDescricao();
            i++;
        }
        String tituloColuna[] = {"ID", "Codigo", "Descrição", "Conservação"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        tbeEsquerda.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tbeEsquerda.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbeEsquerda.getColumnModel().getColumn(1).setPreferredWidth(200);
        tbeEsquerda.getColumnModel().getColumn(2).setPreferredWidth(80);
        tbeEsquerda.getColumnModel().getColumn(3).setPreferredWidth(80);
        
        
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tbeEsquerda.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        tbeEsquerda.setRowHeight(25);
        tbeEsquerda.updateUI();
    }
    
    public void atualizaTabelaDireita(){
        String dados[][] = new String[listaMovimentacao.size()][4];
        int i = 0;
        for (PatrimonioM patri : listaMovimentacao) {
            dados[i][0] = String.valueOf(patri.getId());
            dados[i][1] = patri.getCodigo();
            dados[i][2] = patri.getDescricao();
            dados[i][3] = patri.getGrau_conservacao().getDescricao();
            i++;
        }
        String tituloColuna[] = {"ID", "Codigo", "Descrição", "Conservação"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        tbeDireita.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tbeDireita.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbeDireita.getColumnModel().getColumn(1).setPreferredWidth(200);
        tbeDireita.getColumnModel().getColumn(2).setPreferredWidth(80);
        tbeDireita.getColumnModel().getColumn(3).setPreferredWidth(80);
        
        
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tbeDireita.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        tbeDireita.setRowHeight(25);
        tbeDireita.updateUI();
    }
    
    
    public void atualizaBoxUnidade() {
        cbxUnidade.removeAllItems();
        cbxUnidade.addItem("--Selecione--");
        
        cbxUnidadeDestino.removeAllItems();
        cbxUnidadeDestino.addItem("--Selecione--");
        
        try {
            listaUnidade = unidadeDAO.listaTodos();
        } catch (SQLException ex) {
            Logger.getLogger(OrgaoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[listaUnidade.size()][5];
        int i = 0;
        for (UnidadeM uni : listaUnidade) {
            cbxUnidade.addItem(uni.getNome());
            cbxUnidadeDestino.addItem(uni.getNome());
        }

    }

}
