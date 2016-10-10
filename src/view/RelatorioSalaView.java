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
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.PatrimonioCompostoDAO;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static javafx.scene.text.Font.font;
import model.PatrimonioCompostoM;
import static javafx.scene.text.Font.font;

/**
 *
 * @author NUPSI-01
 */
public class RelatorioSalaView extends javax.swing.JInternalFrame {
    
    SalaM sala;
    SalaDAO salaDAO;
    List<SalaM> listaSala;
    List<SalaM> listaSalaSelecionados;
    List<PatrimonioCompostoM> listaComposto;
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
    Document doc;
    PatrimonioCompostoDAO patri;

    public RelatorioSalaView() {
        salaDAO = new SalaDAO();
        listaSala = new ArrayList<>();
        pisoDAO = new PisoDAO();
        blocoDAO = new BlocoDAO();
        unidadeDAO = new UnidadeDAO();
        listaUnidade = new ArrayList<>();
        listaBloco = new ArrayList<>();
        listaPiso = new ArrayList<>();
        listaComposto = new ArrayList<>();
        unidadeM = new UnidadeM();
        blocoM = new BlocoM();
        pisoM = new PisoM();
        listaSalaSelecionados = new ArrayList<>();
        listaPatrimonio = new ArrayList<>();
        patrimonioDAO = new PatrimonioDAO();
        patri =  new PatrimonioCompostoDAO();
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
        tbeSala.getColumnModel().getColumn(1).setPreferredWidth(250);
        tbeSala.getColumnModel().getColumn(2).setPreferredWidth(80);
        tbeSala.getColumnModel().getColumn(3).setPreferredWidth(80);
        tbeSala.getColumnModel().getColumn(4).setPreferredWidth(100);

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
        tbeSala.getColumnModel().getColumn(1).setPreferredWidth(200);
        tbeSala.getColumnModel().getColumn(2).setPreferredWidth(80);
        tbeSala.getColumnModel().getColumn(3).setPreferredWidth(80);
        tbeSala.getColumnModel().getColumn(4).setPreferredWidth(100);
        
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
        pnlRelatorio = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
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
        btnGerarPDF = new javax.swing.JButton();

        setClosable(true);
        setResizable(true);

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

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
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

        btnGerarPDF.setText("Gerar PDF");
        btnGerarPDF.setEnabled(false);
        btnGerarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarPDFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlRelatorioLayout = new javax.swing.GroupLayout(pnlRelatorio);
        pnlRelatorio.setLayout(pnlRelatorioLayout);
        pnlRelatorioLayout.setHorizontalGroup(
            pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRelatorioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(cbxRelatorioUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addGroup(pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(tfdIDSala, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(tfdDescricaoSala, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlRelatorioLayout.createSequentialGroup()
                            .addGroup(pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbxRelatorioBloco, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(cbxRelatorioPiso, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(btnGerarPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        pnlRelatorioLayout.setVerticalGroup(
            pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRelatorioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxRelatorioUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxRelatorioBloco, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxRelatorioPiso, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdIDSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdDescricaoSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGerarPDF)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 317, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
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
            Logger.getLogger(RelatorioSalaView.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Selecione um piso para a busca.", "Erro", JOptionPane.WARNING_MESSAGE);
        }
        atualizaTabelaSelecionados(pisoM.getId(),blocoM.getId(),unidadeM.getId());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnGerarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarPDFActionPerformed
        String nomediretorio = null;
        String nomepasta = "Relatorios"; // Informe o nome da pasta que armazenará o relatório
        String separador = java.io.File.separator;
        try {
            nomediretorio = "C:" + separador + nomepasta;
            if (!new File(nomediretorio).exists()) {
                (new File(nomediretorio)).mkdir();
            }
            gerarDocumento();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnGerarPDFActionPerformed

    public void gerarDocumento() throws FileNotFoundException{
        try {
            int numeroSala = Integer.parseInt(tbeSala.getValueAt(tbeSala.getSelectedRow(), 0).toString());
            listaPatrimonio = patrimonioDAO.listaTodosSala(numeroSala);
            doc = new Document(PageSize.A4, 41.5f, 41.5f, 55.2f, 55.2f);
            String caminho = "C:/Relatorios/Relatorio" + tbeSala.getValueAt(tbeSala.getSelectedRow(), 1).toString() +  ".pdf";
            PdfWriter.getInstance(doc, new FileOutputStream(caminho));
            doc.open();
            
            
            Font f1 = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Font f2 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Font f3 = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
            Font f4 = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
            Font f5 = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);

            Paragraph titulo1 = new Paragraph("Universidade do Estado de Minas Gerais",f2);
            titulo1.setAlignment(Element.ALIGN_CENTER);
            titulo1.setSpacingAfter(10);

            Paragraph titulo2 = new Paragraph("" + tbeSala.getValueAt(tbeSala.getSelectedRow(), 1).toString() ,f2);
            titulo2.setAlignment(Element.ALIGN_CENTER);
            titulo2.setSpacingAfter(10);
            
            
            doc.add(titulo1);
            doc.add(titulo2);

            PdfPTable tabela = new PdfPTable(new float[]{0.25f, 0.90f, 0.25f,0.20f});
            tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.setWidthPercentage(100f);

            PdfPCell cabecalho1 = new PdfPCell(new Paragraph("Codigo",f3));
            cabecalho1.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cabecalho1.setBorder(0);

            PdfPCell cabecalho2 = new PdfPCell(new Paragraph("Descrição",f3));
            cabecalho2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cabecalho2.setBorder(0);
            
            PdfPCell cabecalho3 = new PdfPCell(new Paragraph("Conservação",f3));
            cabecalho3.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cabecalho3.setBorder(0);
            
            PdfPCell cabecalho4 = new PdfPCell(new Paragraph("Orgão",f3));
            cabecalho4.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cabecalho4.setBorder(0);

            tabela.addCell(cabecalho1);
            tabela.addCell(cabecalho2);
            tabela.addCell(cabecalho3);
            tabela.addCell(cabecalho4);

            for (PatrimonioM patrimonio : listaPatrimonio) {
                
                
                if(patrimonio.getKit()){
                    listaComposto = patri.listaTodosExistentes(patrimonio);
                    for(PatrimonioCompostoM composto : listaComposto){
                        Paragraph p1 = new Paragraph(patrimonio.getCodigo(),f5);
                        p1.setAlignment(Element.ALIGN_JUSTIFIED);
                        PdfPCell col1 = new PdfPCell(p1);
                        col1.setBorder(0);

                        Paragraph p2 = new Paragraph(composto.getDescricao(),f5);
                        p2.setAlignment(Element.ALIGN_JUSTIFIED);
                        PdfPCell col2 = new PdfPCell(p2);
                        col2.setBorder(0);

                        Paragraph p3 = new Paragraph(composto.getGrau().getDescricao(),f5);
                        p3.setAlignment(Element.ALIGN_JUSTIFIED);
                        PdfPCell col3 = new PdfPCell(p3);
                        col3.setBorder(0);

                        Paragraph p4 = new Paragraph(patrimonio.getEntidade().getNome(),f5);
                        p4.setAlignment(Element.ALIGN_JUSTIFIED);
                        PdfPCell col4 = new PdfPCell(p4);
                        col4.setBorder(0);

                        tabela.addCell(col1);
                        tabela.addCell(col2);
                        tabela.addCell(col3);
                        tabela.addCell(col4);
                    }
                }else{
                    Paragraph p1 = new Paragraph(patrimonio.getCodigo(),f5);
                    p1.setAlignment(Element.ALIGN_JUSTIFIED);
                    PdfPCell col1 = new PdfPCell(p1);
                    col1.setBorder(0);

                    Paragraph p2 = new Paragraph(patrimonio.getDescricao(),f5);
                    p2.setAlignment(Element.ALIGN_JUSTIFIED);
                    PdfPCell col2 = new PdfPCell(p2);
                    col2.setBorder(0);

                    Paragraph p3 = new Paragraph(patrimonio.getGrau_conservacao().getDescricao(),f5);
                    p3.setAlignment(Element.ALIGN_JUSTIFIED);
                    PdfPCell col3 = new PdfPCell(p3);
                    col3.setBorder(0);

                    Paragraph p4 = new Paragraph(patrimonio.getEntidade().getNome(),f5);
                    p4.setAlignment(Element.ALIGN_JUSTIFIED);
                    PdfPCell col4 = new PdfPCell(p4);
                    col4.setBorder(0);
                    
                    tabela.addCell(col1);
                    tabela.addCell(col2);
                    tabela.addCell(col3);
                    tabela.addCell(col4);
                }
            }

            doc.add(tabela);

            doc.close();
            JOptionPane.showMessageDialog(null, "Relatório salvo com sucesso em C:/Relatorios/Relatorio");
            Desktop.getDesktop().open(new File(caminho));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException exx) {
            exx.printStackTrace();
            JOptionPane.showMessageDialog(null, "Documento de Relatorios aberto. Feche para gerar um novo.");
        }
    }
    
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
    private javax.swing.JPanel pnlRelatorio;
    private javax.swing.JTable tbeSala;
    private javax.swing.JTextField tfdDescricaoSala;
    private javax.swing.JTextField tfdIDSala;
    // End of variables declaration//GEN-END:variables
}

