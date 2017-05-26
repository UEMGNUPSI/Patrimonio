package view;

import dao.BlocoDAO;
import dao.HistoricoAcaoDAO;
import dao.UnidadeDAO;
import java.awt.event.KeyEvent;
import java.sql.Date;
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
import model.HistoricoAcaoM;
import model.UnidadeM;
import model.UsuarioM;
import util.LimiteDigitos;

/**
 * UNIVERSIDADE DO ESTADO DE MINAS GERAIS - Unidade Frutal
 * @author NUPSI - Núcle de Práticas em Sistemas de Informação
 */
public class BlocoView extends javax.swing.JInternalFrame {

    public BlocoView(UsuarioM usuarioAtivo) {
        blocoDAO = new BlocoDAO();
        listaBloco = new ArrayList<>();
        listaUnidade = new ArrayList<>();
        unidadeDAO = new UnidadeDAO();
        this.usuarioAtivo = usuarioAtivo;
        initComponents();
        this.setVisible(true);
        atualizaBoxUnidade();
        atualizaTabelaBloco();
        tfdDescricaoBloco.setDocument(new LimiteDigitos(45));
    }
    UsuarioM usuarioAtivo;
    int idHistorico;
    String acao;
    String descricaoHistorico;
    
    
    BlocoM bloco;
    BlocoDAO blocoDAO;
    List<BlocoM> listaBloco;
    List<UnidadeM> listaUnidade;
    UnidadeDAO unidadeDAO;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbeBloco = new javax.swing.JTable();
        pnlBloco = new javax.swing.JPanel();
        btnAlterarBloco = new javax.swing.JButton();
        btnExcluirBloco = new javax.swing.JButton();
        btnSalvarBloco = new javax.swing.JButton();
        btnNovoBloco = new javax.swing.JButton();
        cbxUnidade = new javax.swing.JComboBox<>();
        lblIDBloco = new javax.swing.JLabel();
        lblSelecUnidade1 = new javax.swing.JLabel();
        tfdIDBloco = new javax.swing.JTextField();
        btnCancelarBloco = new javax.swing.JButton();
        tfdDescricaoBloco = new javax.swing.JTextField();
        lblDescricaoBloco = new javax.swing.JLabel();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Bloco");

        tbeBloco.setModel(new javax.swing.table.DefaultTableModel(
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
        tbeBloco.getTableHeader().setReorderingAllowed(false);
        tbeBloco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbeBlocoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbeBloco);

        btnAlterarBloco.setText("Alterar");
        btnAlterarBloco.setEnabled(false);
        btnAlterarBloco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarBlocoActionPerformed(evt);
            }
        });

        btnExcluirBloco.setText("Excluir");
        btnExcluirBloco.setEnabled(false);
        btnExcluirBloco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirBlocoActionPerformed(evt);
            }
        });

        btnSalvarBloco.setText("Salvar");
        btnSalvarBloco.setEnabled(false);
        btnSalvarBloco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarBlocoActionPerformed(evt);
            }
        });
        btnSalvarBloco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnSalvarBlocoKeyPressed(evt);
            }
        });

        btnNovoBloco.setText("Novo");
        btnNovoBloco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoBlocoActionPerformed(evt);
            }
        });
        btnNovoBloco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnNovoBlocoKeyPressed(evt);
            }
        });

        cbxUnidade.setEnabled(false);
        cbxUnidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxUnidadeActionPerformed(evt);
            }
        });
        cbxUnidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxUnidadeKeyPressed(evt);
            }
        });

        lblIDBloco.setText("ID");

        lblSelecUnidade1.setText("Selecione a unidade:");

        tfdIDBloco.setEditable(false);

        btnCancelarBloco.setText("Cancelar");
        btnCancelarBloco.setEnabled(false);
        btnCancelarBloco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarBlocoActionPerformed(evt);
            }
        });
        btnCancelarBloco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCancelarBlocoKeyPressed(evt);
            }
        });

        tfdDescricaoBloco.setEnabled(false);
        tfdDescricaoBloco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfdDescricaoBlocoKeyPressed(evt);
            }
        });

        lblDescricaoBloco.setText("Descrição");

        javax.swing.GroupLayout pnlBlocoLayout = new javax.swing.GroupLayout(pnlBloco);
        pnlBloco.setLayout(pnlBlocoLayout);
        pnlBlocoLayout.setHorizontalGroup(
            pnlBlocoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBlocoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBlocoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfdDescricaoBloco)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBlocoLayout.createSequentialGroup()
                        .addComponent(btnNovoBloco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalvarBloco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarBloco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAlterarBloco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluirBloco, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBlocoLayout.createSequentialGroup()
                        .addGroup(pnlBlocoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlBlocoLayout.createSequentialGroup()
                                .addComponent(lblIDBloco)
                                .addGap(152, 152, 152))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBlocoLayout.createSequentialGroup()
                                .addComponent(tfdIDBloco, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(pnlBlocoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBlocoLayout.createSequentialGroup()
                                .addComponent(lblSelecUnidade1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnlBlocoLayout.createSequentialGroup()
                                .addComponent(cbxUnidade, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))
                    .addGroup(pnlBlocoLayout.createSequentialGroup()
                        .addComponent(lblDescricaoBloco)
                        .addGap(12, 12, 12))))
        );
        pnlBlocoLayout.setVerticalGroup(
            pnlBlocoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBlocoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBlocoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIDBloco)
                    .addComponent(lblSelecUnidade1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBlocoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdIDBloco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricaoBloco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdDescricaoBloco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(pnlBlocoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluirBloco)
                    .addComponent(btnAlterarBloco)
                    .addComponent(btnCancelarBloco)
                    .addComponent(btnSalvarBloco)
                    .addComponent(btnNovoBloco))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlBloco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlBloco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 277, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void salvaHistorico() throws SQLException{
        HistoricoAcaoM historico = new HistoricoAcaoM();
        historico.setIdObjeto(idHistorico);
        historico.setTipoObjeto(descricaoHistorico);
        historico.setAcao(acao);
        historico.setDataAcao(new Date(System.currentTimeMillis()));
        historico.setUsuario(usuarioAtivo);
        historico.setCodigo("");
        
        HistoricoAcaoDAO.salvar(historico);
    } 
    public void atualizaBoxUnidade() {
        cbxUnidade.removeAllItems();
        cbxUnidade.addItem("--Selecione--");
        try {
            listaUnidade = unidadeDAO.listaTodos();
        } catch (SQLException ex) {
            Logger.getLogger(OrgaoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[listaUnidade.size()][5];
        int i = 0;
        for (UnidadeM uni : listaUnidade) {
            cbxUnidade.addItem(uni.getNome());
        }

    }

    public void atualizaTabelaBloco() {

        try {
            listaBloco = blocoDAO.listaTodos();
        } catch (SQLException ex) {
            Logger.getLogger(OrgaoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[listaBloco.size()][4];
        int i = 0;
        for (BlocoM blo : listaBloco) {
            dados[i][0] = String.valueOf(blo.getId());
            dados[i][1] = blo.getDescricao();
            dados[i][2] = blo.getUnidadeM().getNome();
            i++;
        }
        String tituloColuna[] = {"ID", "Nome", "Unidade Pertencente"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        tbeBloco.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tbeBloco.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbeBloco.getColumnModel().getColumn(1).setPreferredWidth(300);
        tbeBloco.getColumnModel().getColumn(2).setPreferredWidth(200);

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tbeBloco.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        tbeBloco.setRowHeight(25);
        tbeBloco.updateUI();
    }

    private void cbxUnidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxUnidadeActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cbxUnidadeActionPerformed

    private void tbeBlocoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbeBlocoMouseClicked
        tfdIDBloco.setText(tbeBloco.getValueAt(tbeBloco.getSelectedRow(), 0).toString());
        tfdDescricaoBloco.setText(tbeBloco.getValueAt(tbeBloco.getSelectedRow(), 1).toString());
        cbxUnidade.setSelectedItem(tbeBloco.getValueAt(tbeBloco.getSelectedRow(), 2).toString());
        preparaSelecaoTabela();
    }//GEN-LAST:event_tbeBlocoMouseClicked

    private void btnSalvarBlocoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarBlocoActionPerformed
        if (tfdDescricaoBloco.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Prencha todos os campos.", "Erro", JOptionPane.WARNING_MESSAGE);
            tfdDescricaoBloco.requestFocusInWindow();
        } else if (tfdIDBloco.getText().isEmpty()){
            bloco = new BlocoM();
            bloco.setDescricao(tfdDescricaoBloco.getText());
            bloco.setUnidadeM(pegaUnidade());
            try {
                
                idHistorico = blocoDAO.salvar(bloco);
                acao = "Novo Bloco";
                descricaoHistorico = bloco.getDescricao();
                salvaHistorico();
                JOptionPane.showMessageDialog(null, "Gravado com Sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE); 
                atualizaTabelaBloco();
                preparaSalvareCancelar();
                desativaCampos();
                limpaCamposBloco();
            } catch (SQLException ex) {
                Logger.getLogger(OrgaoView.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else {
            bloco = new BlocoM();
            bloco.setId(Integer.parseInt(tfdIDBloco.getText()));
            bloco.setDescricao(tfdDescricaoBloco.getText());
            try {
                idHistorico = bloco.getId();
                acao = "Alterar Bloco";
                descricaoHistorico = bloco.getDescricao();
                salvaHistorico();
                blocoDAO.alterar(bloco);
                JOptionPane.showMessageDialog(null, "Bloco atualizado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                atualizaTabelaBloco();
                preparaSalvareCancelar();
                desativaCampos();
                limpaCamposBloco();
            } catch (SQLException ex) {
                Logger.getLogger(OrgaoView.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage());

            }
            
            
        }
        
    }//GEN-LAST:event_btnSalvarBlocoActionPerformed

    private void btnExcluirBlocoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirBlocoActionPerformed
        if (tfdIDBloco.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione um Bloco", "Erro", JOptionPane.WARNING_MESSAGE);
        } else {
            bloco = new BlocoM();
            bloco.setId(Integer.parseInt(tfdIDBloco.getText()));
            bloco.setDescricao(tfdDescricaoBloco.getText());
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja Excluir: " + tfdDescricaoBloco.getText() + " ?");
            if (confirma == 0) {
                try {
                    acao = "Excluir Bloco";
                    idHistorico = bloco.getId();
                    descricaoHistorico = bloco.getDescricao();
                    salvaHistorico();
                    blocoDAO.excluir(bloco);
                    atualizaTabelaBloco();
                    limpaCamposBloco();
                    preparaExcluir();
                } catch (SQLException ex) {
                    Logger.getLogger(OrgaoView.class.getName()).log(Level.SEVERE, null, ex);
                    if (ex.getErrorCode() == 1451) {
                        JOptionPane.showMessageDialog(null, "Impossível excluir esse Bloco, ele já possui Pisos cadastrados!", "Erro", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
                    }
                }

            }
        }
    }//GEN-LAST:event_btnExcluirBlocoActionPerformed

    private void btnNovoBlocoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoBlocoActionPerformed
        limpaCamposBloco();
        preparaNovo();
        ativaCampos();
        cbxUnidade.requestFocusInWindow();
        
    }//GEN-LAST:event_btnNovoBlocoActionPerformed

    private void btnAlterarBlocoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarBlocoActionPerformed
        preparaAlterar();
        ativaCampos();
        cbxUnidade.setEnabled(false);
    }//GEN-LAST:event_btnAlterarBlocoActionPerformed

    private void btnCancelarBlocoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarBlocoActionPerformed
        // TODO add your handling code here:
        limpaCamposBloco();
        preparaSalvareCancelar();
        desativaCampos();
    }//GEN-LAST:event_btnCancelarBlocoActionPerformed

    private void tfdDescricaoBlocoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdDescricaoBlocoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnSalvarBloco.requestFocusInWindow();
        }
    }//GEN-LAST:event_tfdDescricaoBlocoKeyPressed

    private void btnSalvarBlocoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSalvarBlocoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tfdDescricaoBloco.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Prencha todos os campos.", "Erro", JOptionPane.WARNING_MESSAGE);
                tfdDescricaoBloco.requestFocusInWindow();
            } else if (tfdIDBloco.getText().isEmpty()) {
                bloco = new BlocoM();
                bloco.setDescricao(tfdDescricaoBloco.getText());
                bloco.setUnidadeM(pegaUnidade());
                try {

                    idHistorico = blocoDAO.salvar(bloco);
                    acao = "Novo Bloco";
                    descricaoHistorico = bloco.getDescricao();
                    salvaHistorico();
                    JOptionPane.showMessageDialog(null, "Gravado com Sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    atualizaTabelaBloco();
                    preparaSalvareCancelar();
                    desativaCampos();
                    limpaCamposBloco();
                } catch (SQLException ex) {
                    Logger.getLogger(OrgaoView.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } else {
                bloco = new BlocoM();
                bloco.setId(Integer.parseInt(tfdIDBloco.getText()));
                bloco.setDescricao(tfdDescricaoBloco.getText());
                try {
                    idHistorico = bloco.getId();
                    acao = "Alterar Bloco";
                    descricaoHistorico = bloco.getDescricao();
                    salvaHistorico();
                    blocoDAO.alterar(bloco);
                    JOptionPane.showMessageDialog(null, "Bloco atualizado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    atualizaTabelaBloco();
                    preparaSalvareCancelar();
                    desativaCampos();
                    limpaCamposBloco();
                } catch (SQLException ex) {
                    Logger.getLogger(OrgaoView.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage());

                }

            }
        }
    }//GEN-LAST:event_btnSalvarBlocoKeyPressed

    private void cbxUnidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxUnidadeKeyPressed
        
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tfdDescricaoBloco.requestFocusInWindow();
        }
        
    }//GEN-LAST:event_cbxUnidadeKeyPressed

    private void btnNovoBlocoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnNovoBlocoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            limpaCamposBloco();
            preparaNovo();
            ativaCampos();
            cbxUnidade.requestFocusInWindow();
        }
        
    }//GEN-LAST:event_btnNovoBlocoKeyPressed

    private void btnCancelarBlocoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCancelarBlocoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            limpaCamposBloco();
            preparaSalvareCancelar();
            desativaCampos();
        }
    }//GEN-LAST:event_btnCancelarBlocoKeyPressed
    
      public UnidadeM pegaUnidade() {
        try {
            if (cbxUnidade.getSelectedIndex() == 0 ){
                JOptionPane.showMessageDialog(null, "Selecione uma Unidade.");
            }else {
                return unidadeDAO.buscaNome(cbxUnidade.getSelectedItem().toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlocoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    // INÍCIO MÉTODOS DE CONTROLE DE BOTÕES
    
    public void preparaAlterar(){
        btnNovoBloco.setEnabled(false);
        btnExcluirBloco.setEnabled(false);
        btnAlterarBloco.setEnabled(false);
        btnSalvarBloco.setEnabled(true);
        btnCancelarBloco.setEnabled(true);
        cbxUnidade.setEnabled(true);
        tbeBloco.setEnabled(false);
        tbeBloco.clearSelection();
       
    }
    public void limpaCamposBloco() {
        tfdIDBloco.setText("");
        tfdDescricaoBloco.setText("");
        cbxUnidade.setSelectedIndex(0);

    }
    
    public void preparaNovo(){
        btnNovoBloco.setEnabled(false);
        btnSalvarBloco.setEnabled(true);
        btnCancelarBloco.setEnabled(true);
        btnAlterarBloco.setEnabled(false);
        btnExcluirBloco.setEnabled(false);
        tbeBloco.setEnabled(false);
        tbeBloco.clearSelection();
        
    }
    
    public void preparaSalvareCancelar(){
        btnNovoBloco.setEnabled(true);
        btnSalvarBloco.setEnabled(false);
        btnCancelarBloco.setEnabled(false);
        btnExcluirBloco.setEnabled(false);
        tbeBloco.setEnabled(true);
        tbeBloco.clearSelection();
        cbxUnidade.setSelectedIndex(0);
    }
    
    public void desativaCampos(){
        tfdDescricaoBloco.setEnabled(false);
        cbxUnidade.setEnabled(false);
    }
    
    public void preparaExcluir(){
        btnExcluirBloco.setEnabled(false);
        btnAlterarBloco.setEnabled(false);
        cbxUnidade.setSelectedIndex(0);
    }
    public void ativaCampos(){
        tfdDescricaoBloco.setEnabled(true);
        cbxUnidade.setEnabled(true);
    }
    
    public void preparaSelecaoTabela(){
        btnNovoBloco.setEnabled(true);
        btnExcluirBloco.setEnabled(true);
        btnAlterarBloco.setEnabled(true);
        
    }
    
   // INÍCIO MÉTODOS DE CONTROLE DE BOTÕES
    
  


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterarBloco;
    private javax.swing.JButton btnCancelarBloco;
    private javax.swing.JButton btnExcluirBloco;
    private javax.swing.JButton btnNovoBloco;
    private javax.swing.JButton btnSalvarBloco;
    private javax.swing.JComboBox<String> cbxUnidade;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescricaoBloco;
    private javax.swing.JLabel lblIDBloco;
    private javax.swing.JLabel lblSelecUnidade1;
    private javax.swing.JPanel pnlBloco;
    private javax.swing.JTable tbeBloco;
    private javax.swing.JTextField tfdDescricaoBloco;
    private javax.swing.JTextField tfdIDBloco;
    // End of variables declaration//GEN-END:variables
}
