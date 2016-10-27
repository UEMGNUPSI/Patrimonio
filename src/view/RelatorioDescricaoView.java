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
import java.awt.event.KeyEvent;
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
public class RelatorioDescricaoView extends javax.swing.JInternalFrame {
    
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

    public RelatorioDescricaoView() {
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
        atualizaTabela();
        lblDescricao.setText("");
        lblQuant.setText("");
    }
    
    public void atualizaTabela() {
        try {
            listaPatrimonio = patrimonioDAO.listaTodosDescricao();
        } catch (SQLException ex) {
            Logger.getLogger(OrgaoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String dados[][] = new String[listaPatrimonio.size()][2];
        int i = 0;
        for (PatrimonioM patri : listaPatrimonio) {
            dados[i][0] = patri.getDescricao();
            dados[i][1] = ""+patri.getQuantidade();
            i++;
        }
        String tituloColuna[] = {"Descricao","Quantidade"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        tbeDescricao.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tbeDescricao.getColumnModel().getColumn(0).setPreferredWidth(400);
        tbeDescricao.getColumnModel().getColumn(1).setPreferredWidth(40);
        
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tbeDescricao.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        tbeDescricao.setRowHeight(25);
        tbeDescricao.updateUI();
    }
    
     public void atualizaTabelaBusca() {
        try {
            listaPatrimonio = patrimonioDAO.buscaDescricaoGroup(txtDescricao.getText());
        } catch (SQLException ex) {
            Logger.getLogger(RelatorioDescricaoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String dados[][] = new String[listaPatrimonio.size()][2];
        int i = 0;
        if(listaPatrimonio.size() == 0){
            JOptionPane.showMessageDialog(null, "Nenhum valor encontrado");
        }else{
            for (PatrimonioM patri : listaPatrimonio) {
                dados[i][0] = patri.getDescricao();
                dados[i][1] = ""+patri.getQuantidade();
                i++;
            }
            String tituloColuna[] = {"Descrição","Quantidade"};
            DefaultTableModel tabelaCliente = new DefaultTableModel();
            tabelaCliente.setDataVector(dados, tituloColuna);
            tbeDescricao.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });
            
            tbeDescricao.getColumnModel().getColumn(0).setPreferredWidth(400);
            tbeDescricao.getColumnModel().getColumn(1).setPreferredWidth(40);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tbeDescricao.getColumnModel().getColumn(1).setCellRenderer(centralizado);
            tbeDescricao.setRowHeight(25);
            tbeDescricao.updateUI();
        }
    }
    
    
    
    
    //Atualiza o comboBox das unidades
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbeDescricao = new javax.swing.JTable();
        pnlRelatorio = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lblDescricao = new javax.swing.JLabel();
        btnGerarPDF = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblQuant = new javax.swing.JLabel();

        setClosable(true);
        setResizable(true);

        tbeDescricao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Descrição", "Quantidade"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
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
        tbeDescricao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbeDescricaoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbeDescricao);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        btnBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnBuscarKeyPressed(evt);
            }
        });

        jLabel6.setText("Descrição:");

        txtDescricao.setNextFocusableComponent(btnBuscar);
        txtDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescricaoKeyPressed(evt);
            }
        });

        jLabel1.setText("Selecionado:");

        lblDescricao.setText("jLabel2");

        btnGerarPDF.setText("Gerar PDF");
        btnGerarPDF.setEnabled(false);
        btnGerarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarPDFActionPerformed(evt);
            }
        });

        jLabel2.setText("Quantidade:");

        lblQuant.setText("jLabel2");

        javax.swing.GroupLayout pnlRelatorioLayout = new javax.swing.GroupLayout(pnlRelatorio);
        pnlRelatorio.setLayout(pnlRelatorioLayout);
        pnlRelatorioLayout.setHorizontalGroup(
            pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRelatorioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRelatorioLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlRelatorioLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDescricao))))
                    .addGroup(pnlRelatorioLayout.createSequentialGroup()
                        .addGroup(pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlRelatorioLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDescricao))
                            .addComponent(btnGerarPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlRelatorioLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblQuant)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pnlRelatorioLayout.setVerticalGroup(
            pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRelatorioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(btnBuscar)
                .addGap(27, 27, 27)
                .addGroup(pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblDescricao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblQuant))
                .addGap(28, 28, 28)
                .addComponent(btnGerarPDF)
                .addContainerGap(375, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 63, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbeDescricaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbeDescricaoMouseClicked
        btnGerarPDF.setEnabled(true);
        lblDescricao.setText(tbeDescricao.getValueAt(tbeDescricao.getSelectedRow(), 0).toString());
        lblQuant.setText(tbeDescricao.getValueAt(tbeDescricao.getSelectedRow(), 1).toString());
        
    }//GEN-LAST:event_tbeDescricaoMouseClicked

    
    
    
    
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if(txtDescricao.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Digite uma chave para a busca!");
        }else{
        atualizaTabelaBusca();
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnGerarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarPDFActionPerformed
        String nomediretorio = null;
        String nomepasta = "Relatorios Descricao"; // Informe o nome da pasta que armazenará o relatório
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
        btnGerarPDF.setEnabled(false);
    }//GEN-LAST:event_btnGerarPDFActionPerformed

    private void btnBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER){  
         try {
            listaPatrimonio = patrimonioDAO.buscaDescricaoGroup(txtDescricao.getText());
            atualizaTabelaBusca();
        } catch (SQLException ex) {
            Logger.getLogger(RelatorioDescricaoView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }//GEN-LAST:event_btnBuscarKeyPressed

    private void txtDescricaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescricaoKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER){  
         try {
            listaPatrimonio = patrimonioDAO.buscaDescricaoGroup(txtDescricao.getText());
            atualizaTabelaBusca();
        } catch (SQLException ex) {
            Logger.getLogger(RelatorioDescricaoView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }//GEN-LAST:event_txtDescricaoKeyPressed

    public void gerarDocumento() throws FileNotFoundException{
        try {
            
            String descricao = tbeDescricao.getValueAt(tbeDescricao.getSelectedRow(), 0).toString();
            
            listaPatrimonio = patrimonioDAO.listaTodosPorDescricao(descricao);
            
            doc = new Document(PageSize.A4, 41.5f, 41.5f, 55.2f, 55.2f);
            String caminho = "C:/Relatorios Descricao/Relatorio " + tbeDescricao.getValueAt(tbeDescricao.getSelectedRow(), 0).toString() +  ".pdf";
            PdfWriter.getInstance(doc, new FileOutputStream(caminho));
            doc.open();
            
            
            Font f1 = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Font f2 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Font f3 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Font f4 = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
            Font f5 = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);

            Paragraph titulo1 = new Paragraph("Universidade do Estado de Minas Gerais",f2);
            titulo1.setAlignment(Element.ALIGN_CENTER);
            titulo1.setSpacingAfter(10);

            Paragraph titulo2 = new Paragraph("" + tbeDescricao.getValueAt(tbeDescricao.getSelectedRow(), 0).toString() ,f2);
            titulo2.setAlignment(Element.ALIGN_CENTER);
            titulo2.setSpacingAfter(10);
            
            
            
            doc.add(titulo1);
            doc.add(titulo2);

            PdfPTable tabela = new PdfPTable(new float[]{0.20f, 0.80f, 0.18f,0.42f});
            tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.setWidthPercentage(100f);

            PdfPCell cabecalho1 = new PdfPCell(new Paragraph("Codigo",f3));
            cabecalho1.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cabecalho1.setBorder(0);

            PdfPCell cabecalho2 = new PdfPCell(new Paragraph("Sala",f3));
            cabecalho2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cabecalho2.setBorder(0);
            
            PdfPCell cabecalho3 = new PdfPCell(new Paragraph("Estado",f3));
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

                        Paragraph p2 = new Paragraph(composto.getPatrimonio().getSala().getDescricao(),f5);
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

                    Paragraph p2 = new Paragraph(patrimonio.getSala().getDescricao(),f5);
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
            JOptionPane.showMessageDialog(null, "Relatório salvo com sucesso em C:/Relatorios Decricao/");
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblQuant;
    private javax.swing.JPanel pnlRelatorio;
    private javax.swing.JTable tbeDescricao;
    private javax.swing.JTextField txtDescricao;
    // End of variables declaration//GEN-END:variables
}

