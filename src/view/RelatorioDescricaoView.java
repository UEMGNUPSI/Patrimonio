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
    }
    
    public void atualizaTabela() {
        try {
            listaPatrimonio = patrimonioDAO.listaTodosDescricao();
        } catch (SQLException ex) {
            Logger.getLogger(OrgaoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[listaPatrimonio.size()][1];
        int i = 0;
        for (PatrimonioM patri : listaPatrimonio) {
            dados[i][0] = patri.getDescricao();
            i++;
        }
        String tituloColuna[] = {"Descrição"};
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
        
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tbeSala.setRowHeight(25);
        tbeSala.updateUI();
    }
    
     public void atualizaTabelaBusca() {
        
        String dados[][] = new String[listaPatrimonio.size()][1];
        int i = 0;
        for (PatrimonioM patri : listaPatrimonio) {
            dados[i][0] = patri.getDescricao();
            i++;
        }
        String tituloColuna[] = {"Descrição"};
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
        
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tbeSala.setRowHeight(25);
        tbeSala.updateUI();
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
        tbeSala = new javax.swing.JTable();
        pnlRelatorio = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
        btnGerarPDF = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();

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

        btnGerarPDF.setText("Gerar PDF");
        btnGerarPDF.setEnabled(false);
        btnGerarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarPDFActionPerformed(evt);
            }
        });

        jLabel6.setText("Descrição:");

        javax.swing.GroupLayout pnlRelatorioLayout = new javax.swing.GroupLayout(pnlRelatorio);
        pnlRelatorio.setLayout(pnlRelatorioLayout);
        pnlRelatorioLayout.setHorizontalGroup(
            pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRelatorioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGerarPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlRelatorioLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDescricao)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        pnlRelatorioLayout.setVerticalGroup(
            pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRelatorioLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(btnBuscar)
                .addGap(103, 103, 103)
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

    private void tbeSalaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbeSalaMouseClicked
        //limpaCamposSala();
        
    }//GEN-LAST:event_tbeSalaMouseClicked

    
    
    
    
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            listaPatrimonio = patrimonioDAO.buscaDescricaoGroup(txtDescricao.getText());
            atualizaTabelaBusca();
        } catch (SQLException ex) {
            Logger.getLogger(RelatorioDescricaoView.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlRelatorio;
    private javax.swing.JTable tbeSala;
    private javax.swing.JTextField txtDescricao;
    // End of variables declaration//GEN-END:variables
}

