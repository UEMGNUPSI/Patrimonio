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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author NUPSI-01
 */
public class RelatorioView extends javax.swing.JInternalFrame {
    
    SalaM sala;
    SalaDAO salaDAO;
    List<SalaM> listaSala;
    List<SalaM> listaSalaSelecionados;
    PisoDAO pisoDAO;
    BlocoDAO blocoDAO;
    UnidadeDAO unidadeDAO;
    List<BlocoM> listaBloco;
    List<UnidadeM> listaUnidade;
    List<PisoM> listaPiso;
    List<PatrimonioM> listaPatrimonio;
    PisoM pisoM;
    BlocoM blocoM;
    UnidadeM unidadeM;
    PatrimonioDAO patrimonioDAO;
    public RelatorioView() {
        salaDAO = new SalaDAO();
        listaSala = new ArrayList<>();
        pisoDAO = new PisoDAO();
        blocoDAO = new BlocoDAO();
        unidadeDAO = new UnidadeDAO();
        listaUnidade = new ArrayList<>();
        listaBloco = new ArrayList<>();
        listaPiso = new ArrayList<>();
        unidadeM = new UnidadeM();
        blocoM = new BlocoM();
        pisoM = new PisoM();
        listaSalaSelecionados = new ArrayList<>();
        listaPatrimonio = new ArrayList<>();
        patrimonioDAO = new PatrimonioDAO();
        initComponents();
        this.setVisible(true);
        atualizaTabelaSala();
        atualizaBoxUnidade();
    }
    
    public void atualizaTabelaSala() {
        try {
            listaSala = salaDAO.listaTodos();
        } catch (SQLException ex) {
            Logger.getLogger(OrgaoView.class.getName()).log(Level.SEVERE, null, ex);
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
        tbeSala.getColumnModel().getColumn(1).setPreferredWidth(150);
        tbeSala.getColumnModel().getColumn(2).setPreferredWidth(150);
        tbeSala.getColumnModel().getColumn(3).setPreferredWidth(150);

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tbeSala.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        tbeSala.setRowHeight(25);
        tbeSala.updateUI();
    }
    
    public void atualizaTabelaSelecionados(int id_piso, int id_bloco, int id_unidade) {
        try {
            listaSalaSelecionados = salaDAO.listaSelecionados(id_piso, id_bloco, id_unidade);
        } catch (SQLException ex) {
            Logger.getLogger(OrgaoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[listaSalaSelecionados.size()][5];
        int i = 0;
        for (SalaM sal : listaSalaSelecionados) {
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
        tbeSala.getColumnModel().getColumn(1).setPreferredWidth(150);
        tbeSala.getColumnModel().getColumn(2).setPreferredWidth(150);
        tbeSala.getColumnModel().getColumn(3).setPreferredWidth(150);

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tbeSala.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        tbeSala.setRowHeight(25);
        tbeSala.updateUI();
    }
    
    
    
    //Atualiza o comboBox das unidades
    public void atualizaBoxUnidade() {
        cbxRelatorioUnidade.removeAllItems();
        cbxRelatorioUnidade.addItem("--Selecione--");
        try {
            listaUnidade = unidadeDAO.listaTodos();
        } catch (SQLException ex) {
            Logger.getLogger(OrgaoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[listaUnidade.size()][5];
        int i = 0;
        for (UnidadeM uni : listaUnidade) {
            cbxRelatorioUnidade.addItem(uni.getNome());
        }

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
        tbeSala = new javax.swing.JTable();
        btnGerarPDF = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cbxRelatorioUnidade = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cbxRelatorioBloco = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cbxRelatorioPiso = new javax.swing.JComboBox<>();
        tfdIDSala = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfdDescricaoSala = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();

        setClosable(true);

        tbeSala.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Descrição", "ID da Sala"
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

        btnGerarPDF.setText("Gerar PDF");
        btnGerarPDF.setEnabled(false);
        btnGerarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarPDFActionPerformed(evt);
            }
        });

        jLabel1.setText("Selecione a Unidade:");

        cbxRelatorioUnidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxRelatorioUnidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxRelatorioUnidadeActionPerformed(evt);
            }
        });

        jLabel2.setText("Selecione o Bloco:");

        cbxRelatorioBloco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxRelatorioBloco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxRelatorioBlocoActionPerformed(evt);
            }
        });

        jLabel3.setText("Selecione o Piso:");

        cbxRelatorioPiso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxRelatorioPiso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxRelatorioPisoActionPerformed(evt);
            }
        });

        tfdIDSala.setEditable(false);
        tfdIDSala.setEnabled(false);
        tfdIDSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfdIDSalaActionPerformed(evt);
            }
        });

        jLabel4.setText("ID:");

        tfdDescricaoSala.setEditable(false);
        tfdDescricaoSala.setEnabled(false);
        tfdDescricaoSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfdDescricaoSalaActionPerformed(evt);
            }
        });

        jLabel5.setText("Descrição:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnGerarPDF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 203, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(64, 64, 64))
                            .addComponent(cbxRelatorioUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(tfdIDSala, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(tfdDescricaoSala, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbxRelatorioBloco, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(cbxRelatorioPiso, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxRelatorioUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxRelatorioBloco, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxRelatorioPiso, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfdIDSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfdDescricaoSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101)))
                .addComponent(btnGerarPDF)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxRelatorioUnidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxRelatorioUnidadeActionPerformed
        if (cbxRelatorioUnidade.getSelectedIndex() < 1) {
            cbxRelatorioBloco.removeAllItems();
            cbxRelatorioBloco.addItem("--Selecione--");

        } else {
            cbxRelatorioBloco.removeAllItems();
            cbxRelatorioBloco.addItem("--Selecione--");
            UnidadeM unid = new UnidadeM();
            try {
                unid = unidadeDAO.buscaNome(cbxRelatorioUnidade.getSelectedItem().toString());
                listaBloco = blocoDAO.buscaUni(unid.getId());
                for (BlocoM bloc : listaBloco) {
                    cbxRelatorioBloco.addItem(bloc.getDescricao());
                }
                cbxRelatorioBloco.requestFocusInWindow();
            } catch (SQLException ex) {
                cbxRelatorioBloco.removeAllItems();
                cbxRelatorioBloco.addItem("--Selecione--");
            }
        }
    }//GEN-LAST:event_cbxRelatorioUnidadeActionPerformed

    private void cbxRelatorioBlocoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxRelatorioBlocoActionPerformed
       if (cbxRelatorioBloco.getSelectedIndex() < 1) {
            cbxRelatorioPiso.removeAllItems();
            cbxRelatorioPiso.addItem("--Selecione--");
        } else {
            cbxRelatorioPiso.removeAllItems();
            cbxRelatorioPiso.addItem("--Selecione--");
            BlocoM bloc = new BlocoM();

            try {
                bloc = blocoDAO.buscaNome(cbxRelatorioBloco.getSelectedItem().toString());
                listaPiso = pisoDAO.buscaBloc(bloc.getId());
                for (PisoM pis : listaPiso) {
                    cbxRelatorioPiso.addItem(pis.getDescricao());
                }
                cbxRelatorioPiso.requestFocusInWindow();
            } catch (SQLException ex) {
                cbxRelatorioPiso.removeAllItems();
                cbxRelatorioPiso.addItem("--Selecione--");
            }
        }
    }//GEN-LAST:event_cbxRelatorioBlocoActionPerformed

    private void tbeSalaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbeSalaMouseClicked
        //limpaCamposSala();
        
        tfdIDSala.setText(tbeSala.getValueAt(tbeSala.getSelectedRow(), 0).toString());
        tfdDescricaoSala.setText(tbeSala.getValueAt(tbeSala.getSelectedRow(), 1).toString());
        cbxRelatorioUnidade.setSelectedItem(tbeSala.getValueAt(tbeSala.getSelectedRow(), 4).toString());
        cbxRelatorioBloco.setSelectedItem(tbeSala.getValueAt(tbeSala.getSelectedRow(), 3).toString());
        cbxRelatorioPiso.setSelectedItem(tbeSala.getValueAt(tbeSala.getSelectedRow(), 2).toString());
        preparaSelecaoTabelaSala();
        
    }//GEN-LAST:event_tbeSalaMouseClicked

       public PisoM pegaSala() {
        try {
            return pisoDAO.buscaNome(cbxRelatorioPiso.getSelectedItem().toString());
        } catch (SQLException ex) {
            Logger.getLogger(BlocoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
        public void preparaSelecaoTabelaSala(){
        btnGerarPDF.setEnabled(true);   
    }
       
    
    
    
    //2 metodos sem importancia.
    private void tfdIDSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfdIDSalaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfdIDSalaActionPerformed

    private void tfdDescricaoSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfdDescricaoSalaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfdDescricaoSalaActionPerformed

    private void cbxRelatorioPisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxRelatorioPisoActionPerformed
        // TODO add your handling code here:
        tfdDescricaoSala.requestFocusInWindow();
    }//GEN-LAST:event_cbxRelatorioPisoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (cbxRelatorioUnidade.getSelectedIndex() == 0 || cbxRelatorioBloco.getSelectedIndex() == 0 || cbxRelatorioPiso.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Prencha todos os campos.", "Erro", JOptionPane.WARNING_MESSAGE);       
        }else{
        try {
            unidadeM = unidadeDAO.buscaNome(cbxRelatorioUnidade.getSelectedItem().toString());//pega a unidade selecionada
            blocoM = blocoDAO.busca_id_unidade(unidadeM.getId(), cbxRelatorioBloco.getSelectedItem().toString());// todos os blocos da unidade de cima
            pisoM = pisoDAO.busca_id_bloco(blocoM.getId(), cbxRelatorioPiso.getSelectedItem().toString());//todos os pisos da unidade de cima
        } catch (SQLException ex) {
            Logger.getLogger(RelatorioView.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Selecione um piso para a busca.", "Erro", JOptionPane.WARNING_MESSAGE);
        }
        atualizaTabelaSelecionados(pisoM.getId(),blocoM.getId(),unidadeM.getId());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnGerarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarPDFActionPerformed
       Document document = new Document();
        try {
            int numeroSala = Integer.parseInt(tbeSala.getValueAt(tbeSala.getSelectedRow(), 0).toString());
            listaPatrimonio = patrimonioDAO.listaTodosSala(numeroSala);
            try {
                    
                    PdfWriter.getInstance(document, new FileOutputStream("C:\\PDF\\Relatorio "+tbeSala.getValueAt(tbeSala.getSelectedRow(), 1).toString()+".pdf"));
                     document.open();
                     document.add(new Paragraph("Patrimonios Presentes na Sala "+tbeSala.getValueAt(tbeSala.getSelectedRow(), 1).toString()));
                     for(PatrimonioM patrimonio : listaPatrimonio){
                        document.add(new Paragraph(""+patrimonio.getEntidade().getNome()+"    "+patrimonio.getCodigo().toString()+"    "+patrimonio.getDescricao().toString()
                        +"    "+patrimonio.getGrau_conservacao().getDescricao()));
                        
                     }
                     JOptionPane.showMessageDialog(null, "PDF criado com uscesso");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(RelatorioView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
               Logger.getLogger(RelatorioView.class.getName()).log(Level.SEVERE, null, ex);
           }
            document.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Impossivel se conectar ao banco");
            Logger.getLogger(RelatorioView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnGerarPDFActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGerarPDF;
    private javax.swing.JComboBox<String> cbxRelatorioBloco;
    private javax.swing.JComboBox<String> cbxRelatorioPiso;
    private javax.swing.JComboBox<String> cbxRelatorioUnidade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbeSala;
    private javax.swing.JTextField tfdDescricaoSala;
    private javax.swing.JTextField tfdIDSala;
    // End of variables declaration//GEN-END:variables
}

