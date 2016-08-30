/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.SubTipoDAO;
import dao.TipoDAO;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.GrauConservacaoM;
import model.StatusM;
import model.SubTipoM;
import model.TipoM;
import model.UnidadeM;
import util.LimiteDigitos;

/**
 * UNIVERSIDADE DO ESTADO DE MINAS GERAIS - Unidade Frutal
 * @author NUPSI - Núcle de Práticas em Sistemas de Informação
 * Equipe: Gustavo Pinoti,Leopoldo Ferreira, Marlon Moro, Murillo Cuervo
 */
//concertar alterar do subTipo ********************************************************************************************
public class TipoSubtipoView extends javax.swing.JInternalFrame {

    /**
     * Creates new form TipoSubtipo
     */
    public TipoSubtipoView() {

        tipoDAO = new TipoDAO();
        subTipoDAO = new SubTipoDAO();
        listaTipo = new ArrayList<>();
        listaSubTipo = new ArrayList<>();

        initComponents();
        this.setVisible(true);
        atualizaTabelaTipo();
        atualizaTabelaSubTipo();
        preencheComboBox();
        limpaCamposTipo();
        limpaCamposSubTipo();
        tfdDescricaoSubTipo.setDocument(new LimiteDigitos(45));
        tfdDescricaoTipo.setDocument(new LimiteDigitos(45));
        
        //preparaSalvareCancelarTipo();
        //btnSalvarSubtipo.setEnabled(false);
    }
    TipoM tipo;
    SubTipoM subTipo;
    TipoDAO tipoDAO;
    SubTipoDAO subTipoDAO;
    List<TipoM> listaTipo;
    List<SubTipoM> listaSubTipo;
    BufferedImage bi = null;
    File fi = new File("");
    String caminho = fi.getAbsolutePath() + "/imagem/";
    
    
    
    public void atualizaTabelaTipo() {
        try {
            listaTipo = tipoDAO.listaTodos();
        } catch (SQLException ex) {
            Logger.getLogger(TipoSubtipoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[listaTipo.size()][2];
        int i = 0;
        for (TipoM tipo1 : listaTipo) {
            dados[i][0] = String.valueOf(tipo1.getId());
            dados[i][1] = tipo1.getDescricao();
            i++;
        }
        String tituloColuna[] = {"ID", "Descrição"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        tbeTipo.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tbeTipo.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbeTipo.getColumnModel().getColumn(1).setPreferredWidth(300);

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tbeTipo.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        tbeTipo.setRowHeight(25);
        tbeTipo.updateUI();

    }

    public void atualizaTabelaSubTipo() {
        try {
            listaSubTipo = subTipoDAO.listaTodos();
        } catch (SQLException ex) {
            Logger.getLogger(TipoSubtipoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[listaSubTipo.size()][3];
        int i = 0;
        for (SubTipoM subTipo1 : listaSubTipo) {
            dados[i][0] = String.valueOf(subTipo1.getId());
            dados[i][1] = subTipo1.getDescricao();
            dados[i][2] = subTipo1.getTipo().getDescricao();
            i++;
        }
        String tituloColuna[] = {"ID", "Descrição", "Tipo Pertencente"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        tbeSubTipo.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tbeSubTipo.getColumnModel().getColumn(0).setPreferredWidth(75);
        tbeSubTipo.getColumnModel().getColumn(1).setPreferredWidth(175);
        tbeSubTipo.getColumnModel().getColumn(2).setPreferredWidth(175);

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tbeSubTipo.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        tbeSubTipo.setRowHeight(25);
        tbeSubTipo.updateUI();

    }

    public void limpaCamposTipo() {
        tfdIDTipo.setText("");
        tfdDescricaoTipo.setText("");
        //btnSalvarTipo.setEnabled(true);
        //btnExcluirTipo.setEnabled(false);
        //btnAlterarTipo.setEnabled(false);
    }

    public void limpaCamposSubTipo() {
        tfdIDSubTipo.setText("");
        tfdDescricaoSubTipo.setText("");
        cbxSubtipo.setSelectedIndex(0);
        //btnSalvarSubtipo.setEnabled(true);
        //btnExcluirSubtipo.setEnabled(false);
       // btnAlterarSubtipo.setEnabled(false);
        jLabel1.setIcon(null);
    }

    //metodo para preencher o comboBox tipo
    public void preencheComboBox() {
        cbxSubtipo.removeAllItems();
        try {
            listaTipo = tipoDAO.listaTodos();
        } catch (SQLException ex) {
            Logger.getLogger(TipoSubtipoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        cbxSubtipo.addItem("--Selecione--");
        for (TipoM tipo1 : listaTipo) {
            cbxSubtipo.addItem(tipo1.getDescricao());
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

        pnlTipoSubtipo = new javax.swing.JPanel();
        lblDescricaoTipo = new javax.swing.JLabel();
        tfdDescricaoTipo = new javax.swing.JTextField();
        spdTipoSubtipo = new javax.swing.JSeparator();
        lblSubtipo = new javax.swing.JLabel();
        lblDescricaoSubtipo = new javax.swing.JLabel();
        tfdDescricaoSubTipo = new javax.swing.JTextField();
        lblTipo = new javax.swing.JLabel();
        btnAlterarTipo = new javax.swing.JButton();
        btnExcluirTipo = new javax.swing.JButton();
        btnSalvarTipo = new javax.swing.JButton();
        btnAlterarSubtipo = new javax.swing.JButton();
        btnSalvarSubtipo = new javax.swing.JButton();
        btnAdicionarImagem = new javax.swing.JButton();
        lblIDTipo = new javax.swing.JLabel();
        tfdIDTipo = new javax.swing.JTextField();
        tfdIDSubTipo = new javax.swing.JTextField();
        lblIDTipo1 = new javax.swing.JLabel();
        cbxSubtipo = new javax.swing.JComboBox<>();
        btnNovoTipo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnCancelarSubtipo = new javax.swing.JButton();
        btnNovoSubtipo = new javax.swing.JButton();
        btnExcluirSubtipo = new javax.swing.JButton();
        btnCancelarTipo = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbeTipo = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbeSubTipo = new javax.swing.JTable();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de Tipos e Subtipos");

        lblDescricaoTipo.setText("Descrição");

        tfdDescricaoTipo.setEnabled(false);

        lblSubtipo.setText("Subtipo");

        lblDescricaoSubtipo.setText("Descrição");

        tfdDescricaoSubTipo.setEnabled(false);

        lblTipo.setText("Tipo");

        btnAlterarTipo.setText("Alterar");
        btnAlterarTipo.setEnabled(false);
        btnAlterarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarTipoActionPerformed(evt);
            }
        });

        btnExcluirTipo.setText("Excluir");
        btnExcluirTipo.setEnabled(false);
        btnExcluirTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirTipoActionPerformed(evt);
            }
        });

        btnSalvarTipo.setText("Salvar");
        btnSalvarTipo.setEnabled(false);
        btnSalvarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarTipoActionPerformed(evt);
            }
        });

        btnAlterarSubtipo.setText("Alterar");
        btnAlterarSubtipo.setEnabled(false);
        btnAlterarSubtipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarSubtipoActionPerformed(evt);
            }
        });

        btnSalvarSubtipo.setText("Salvar");
        btnSalvarSubtipo.setEnabled(false);
        btnSalvarSubtipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarSubtipoActionPerformed(evt);
            }
        });

        btnAdicionarImagem.setText("Adicionar Imagem");
        btnAdicionarImagem.setEnabled(false);
        btnAdicionarImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarImagemActionPerformed(evt);
            }
        });

        lblIDTipo.setText("ID");

        tfdIDTipo.setEditable(false);
        tfdIDTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfdIDTipoActionPerformed(evt);
            }
        });

        tfdIDSubTipo.setEditable(false);

        lblIDTipo1.setText("ID");

        cbxSubtipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Selecione--" }));
        cbxSubtipo.setEnabled(false);
        cbxSubtipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSubtipoActionPerformed(evt);
            }
        });

        btnNovoTipo.setText("Novo");
        btnNovoTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoTipoActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Imagem"));
        jPanel2.setMaximumSize(new java.awt.Dimension(150, 150));
        jPanel2.setMinimumSize(new java.awt.Dimension(150, 150));
        jPanel2.setName(""); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCancelarSubtipo.setText("Cancelar");
        btnCancelarSubtipo.setEnabled(false);
        btnCancelarSubtipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarSubtipoActionPerformed(evt);
            }
        });

        btnNovoSubtipo.setText("Novo");
        btnNovoSubtipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoSubtipoActionPerformed(evt);
            }
        });

        btnExcluirSubtipo.setText("Excluir");
        btnExcluirSubtipo.setEnabled(false);
        btnExcluirSubtipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirSubtipoActionPerformed(evt);
            }
        });

        btnCancelarTipo.setText("Cancelar");
        btnCancelarTipo.setEnabled(false);
        btnCancelarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarTipoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTipoSubtipoLayout = new javax.swing.GroupLayout(pnlTipoSubtipo);
        pnlTipoSubtipo.setLayout(pnlTipoSubtipoLayout);
        pnlTipoSubtipoLayout.setHorizontalGroup(
            pnlTipoSubtipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTipoSubtipoLayout.createSequentialGroup()
                .addGroup(pnlTipoSubtipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTipoSubtipoLayout.createSequentialGroup()
                        .addGroup(pnlTipoSubtipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlTipoSubtipoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(tfdDescricaoTipo))
                            .addGroup(pnlTipoSubtipoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlTipoSubtipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDescricaoTipo)
                                    .addComponent(lblDescricaoSubtipo)
                                    .addGroup(pnlTipoSubtipoLayout.createSequentialGroup()
                                        .addGap(102, 102, 102)
                                        .addComponent(lblSubtipo))
                                    .addComponent(btnAdicionarImagem)
                                    .addComponent(lblIDTipo)
                                    .addComponent(tfdIDTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlTipoSubtipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlTipoSubtipoLayout.createSequentialGroup()
                                            .addComponent(btnNovoTipo)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnSalvarTipo)
                                            .addGap(3, 3, 3)
                                            .addComponent(btnCancelarTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnAlterarTipo)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnExcluirTipo)
                                            .addGap(23, 23, 23))
                                        .addGroup(pnlTipoSubtipoLayout.createSequentialGroup()
                                            .addGroup(pnlTipoSubtipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(lblIDTipo1, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlTipoSubtipoLayout.createSequentialGroup()
                                                    .addComponent(tfdIDSubTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(88, 88, 88)
                                                    .addComponent(cbxSubtipo, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(pnlTipoSubtipoLayout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addComponent(lblTipo)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlTipoSubtipoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlTipoSubtipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTipoSubtipoLayout.createSequentialGroup()
                                .addComponent(btnNovoSubtipo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSalvarSubtipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelarSubtipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAlterarSubtipo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExcluirSubtipo)
                                .addGap(12, 12, 12))
                            .addComponent(spdTipoSubtipo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tfdDescricaoSubTipo)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlTipoSubtipoLayout.setVerticalGroup(
            pnlTipoSubtipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTipoSubtipoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTipo)
                .addGap(7, 7, 7)
                .addComponent(lblIDTipo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdIDTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricaoTipo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfdDescricaoTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTipoSubtipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvarTipo)
                    .addComponent(btnExcluirTipo)
                    .addComponent(btnAlterarTipo)
                    .addComponent(btnNovoTipo)
                    .addComponent(btnCancelarTipo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spdTipoSubtipo, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSubtipo)
                .addGap(1, 1, 1)
                .addComponent(lblIDTipo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTipoSubtipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdIDSubTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxSubtipo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricaoSubtipo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdDescricaoSubTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdicionarImagem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTipoSubtipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlterarSubtipo)
                    .addComponent(btnNovoSubtipo)
                    .addComponent(btnSalvarSubtipo)
                    .addComponent(btnCancelarSubtipo)
                    .addComponent(btnExcluirSubtipo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbeTipo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Descrição Tipo"
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
        tbeTipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbeTipoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbeTipo);

        tbeSubTipo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Descrição SubTipo"
            }
        ));
        tbeSubTipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbeSubTipoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbeSubTipo);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTipoSubtipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTipoSubtipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarTipoActionPerformed
        if (tfdDescricaoTipo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha Todos os campos!", "Erro", JOptionPane.WARNING_MESSAGE);
            tfdDescricaoTipo.requestFocusInWindow();
        } else  if (tfdIDTipo.getText().isEmpty()){
            tipo = new TipoM();
            tipo.setDescricao(tfdDescricaoTipo.getText());

            try {
                tipoDAO.salvar(tipo);
                JOptionPane.showMessageDialog(null, "Gravado com Sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                
                preparaSalvareCancelarTipo();
                desativaCamposTipo();
                atualizaTabelaTipo();
                preencheComboBox();
                atualizaTabelaSubTipo();
                limpaCamposTipo();
                
                
            } catch (SQLException ex) {
                Logger.getLogger(ConservacaoStatusView.class.getName()).log(Level.SEVERE, null, ex);
                if (ex.getErrorCode() == 1062) {
                    JOptionPane.showMessageDialog(null, "Tipo já existente.", "Erro", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
                
                

        }else{
            
            tipo = new TipoM();
            tipo.setDescricao(tfdDescricaoTipo.getText());
            tipo.setId(Integer.parseInt(tfdIDTipo.getText()));

            try {
                tipoDAO.alterar(tipo);
                JOptionPane.showMessageDialog(null, "Alterado com Sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                
                preparaSalvareCancelarTipo();
                desativaCamposTipo();
                atualizaTabelaTipo();
                preencheComboBox();
                atualizaTabelaSubTipo();
                limpaCamposTipo();
                
            } catch (SQLException ex) {
                Logger.getLogger(ConservacaoStatusView.class.getName()).log(Level.SEVERE, null, ex);
                if (ex.getErrorCode() == 1062) {
                    JOptionPane.showMessageDialog(null, "Tipo já existente.", "Erro", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
                
        }
        
    }//GEN-LAST:event_btnSalvarTipoActionPerformed

    private void btnExcluirTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirTipoActionPerformed

        if (tfdIDTipo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione um Tipo!", "Erro", JOptionPane.WARNING_MESSAGE);
        } else {
            tipo = new TipoM();
            tipo.setId(Integer.parseInt(tfdIDTipo.getText()));
            int confirma = JOptionPane.showConfirmDialog(null, "Dejesa Excluir: " + tfdDescricaoTipo.getText());
            if (confirma == 0) {
                try {
                    tipoDAO.excluir(tipo);
                    limpaCamposTipo();
                    atualizaTabelaTipo();
                    preencheComboBox();
                } catch (SQLException ex) {
                    Logger.getLogger(ConservacaoStatusView.class.getName()).log(Level.SEVERE, null, ex);
                    if (ex.getErrorCode() == 1451) {
                        JOptionPane.showMessageDialog(null, "Impossivel deletar este Tipo, pois possui Subtipos vinculados.", "Erro", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
                    }
                }

            }
        }
        desativaCamposTipo();
        preparaSalvareCancelarTipo();
    }//GEN-LAST:event_btnExcluirTipoActionPerformed

    private void btnAlterarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarTipoActionPerformed
        
        preparaAlterarTipo();
        ativaCamposTipo();
        
        /*if (tfdIDTipo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione um Tipo!", "Erro", JOptionPane.WARNING_MESSAGE);
        } else {
            tipo = new TipoM();
            tipo.setDescricao(tfdDescricaoTipo.getText());
            tipo.setId(Integer.parseInt(tfdIDTipo.getText()));

        }
        try {
            tipoDAO.alterar(tipo);
            JOptionPane.showMessageDialog(null, "Tipo Alterado Com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            atualizaTabelaTipo();
            preencheComboBox();
            atualizaTabelaSubTipo();
            limpaCamposTipo();
        } catch (SQLException ex) {
            Logger.getLogger(ConservacaoStatusView.class.getName()).log(Level.SEVERE, null, ex);
            if (ex.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "Tipo já existente", "Erro", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
*/
    }//GEN-LAST:event_btnAlterarTipoActionPerformed

    private void btnNovoTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoTipoActionPerformed
        // TODO add your handling code here:
        limpaCamposTipo();
        preparaNovoTipo();
        
    }//GEN-LAST:event_btnNovoTipoActionPerformed

    private void tbeTipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbeTipoMouseClicked
        tfdIDTipo.setText(tbeTipo.getValueAt(tbeTipo.getSelectedRow(), 0).toString());
        tfdDescricaoTipo.setText(tbeTipo.getValueAt(tbeTipo.getSelectedRow(), 1).toString());
        btnSalvarTipo.setEnabled(false);
        btnExcluirTipo.setEnabled(true);
        btnAlterarTipo.setEnabled(true);
    }//GEN-LAST:event_tbeTipoMouseClicked


    private void tfdIDTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfdIDTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfdIDTipoActionPerformed


    private void btnSalvarSubtipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarSubtipoActionPerformed

        if (tfdDescricaoSubTipo.getText().isEmpty() || cbxSubtipo.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Preencha Todos os campos!", "Erro", JOptionPane.WARNING_MESSAGE);
            tfdDescricaoSubTipo.requestFocusInWindow();
        } else if(tfdIDSubTipo.getText().isEmpty()){
            subTipo = new SubTipoM();
            subTipo.setDescricao(tfdDescricaoSubTipo.getText());
            subTipo.setTipo(listaTipo.get(cbxSubtipo.getSelectedIndex() - 1));
            try {               
                subTipoDAO.salvar(subTipo);                
                if(bi!=null){
                salvarImagen();                
                }   
                
                limpaCamposSubTipo();
                desativaCamposSubtipo();
                preparaSalvareCancelarSubtipo();
                atualizaTabelaSubTipo();
   
                JOptionPane.showMessageDialog(null, "Gravado com Sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(ConservacaoStatusView.class.getName()).log(Level.SEVERE, null, ex);
                if (ex.getErrorCode() == 1062) {
                    JOptionPane.showMessageDialog(null, "Subtipo já existente.", "Erro", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }       
    }else{
            subTipo = new SubTipoM();
            subTipo.setDescricao(tfdDescricaoSubTipo.getText());
            subTipo.setTipo(listaTipo.get(cbxSubtipo.getSelectedIndex() - 1));
            subTipo.setId(Integer.parseInt(tfdIDSubTipo.getText()));
            try {
                subTipoDAO.alterar(subTipo);
                
                if(bi!=null){
                salvarImagen();               
                }
                JOptionPane.showMessageDialog(null, "Alterado com Sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                
                limpaCamposSubTipo();
                limpaCamposSubTipo();
                desativaCamposSubtipo();
                preparaSalvareCancelarSubtipo();
               
                atualizaTabelaSubTipo();
            } catch (SQLException ex) {
                Logger.getLogger(ConservacaoStatusView.class.getName()).log(Level.SEVERE, null, ex);
                if (ex.getErrorCode() == 1062) {
                    JOptionPane.showMessageDialog(null, "Subtipo já existente.", "Erro", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }
        
    }//GEN-LAST:event_btnSalvarSubtipoActionPerformed


    private void btnExcluirSubtipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirSubtipoActionPerformed
        // TODO add your handling code here:
        if (tfdIDSubTipo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione um SubTipo!", "Erro", JOptionPane.WARNING_MESSAGE);
        } else {
            subTipo = new SubTipoM();
            subTipo.setId(Integer.parseInt(tfdIDSubTipo.getText()));
            int confirma = JOptionPane.showConfirmDialog(null, "Dejesa Excluir: " + tfdDescricaoSubTipo.getText());
            if (confirma == 0) {
                try {
                    File file = new File(caminho + tfdDescricaoSubTipo.getText() + ".jpg");

                    if (file.exists() == true) {
                        file.delete();
                    }
                    subTipoDAO.excluir(subTipo);
                    limpaCamposTipo();
                    atualizaTabelaSubTipo();
                    limpaCamposSubTipo();
                } catch (SQLException ex) {
                    Logger.getLogger(ConservacaoStatusView.class.getName()).log(Level.SEVERE, null, ex);
                    if (ex.getErrorCode() == 1451) {
                        JOptionPane.showMessageDialog(null, "Impossível excluir este subtipo, existem patrimônios cadastrados com esse subtipo!", "Erro", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
                    }
                }

            }
        }
         desativaCamposSubtipo();
         preparaSalvareCancelarSubtipo();
    }//GEN-LAST:event_btnExcluirSubtipoActionPerformed

    private void btnAlterarSubtipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarSubtipoActionPerformed
        // TODO add your handling code here:      
        preparaAlterarSubtipo();
        ativaCamposSubtipo();
        
        
/*
        if (tfdIDSubTipo.getText().isEmpty() || cbxSubtipo.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione SubTipo!", "Erro", JOptionPane.WARNING_MESSAGE);
        } else {
            subTipo = new SubTipoM();
            subTipo.setDescricao(tfdDescricaoSubTipo.getText());
            subTipo.setId(Integer.parseInt(tfdIDSubTipo.getText()));

            subTipo.setTipo(listaTipo.get(cbxSubtipo.getSelectedIndex() - 1));

            try {
                subTipoDAO.alterar(subTipo);
                salvarImagen();
                JOptionPane.showMessageDialog(null, "SubTipo alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                atualizaTabelaSubTipo();
                cbxSubtipo.setSelectedIndex(0);
                limpaCamposSubTipo();

            } catch (SQLException ex) {
                Logger.getLogger(ConservacaoStatusView.class.getName()).log(Level.SEVERE, null, ex);
                if (ex.getErrorCode() == 1062) {
                    JOptionPane.showMessageDialog(null, "Subtipo já existente", "Erro", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }

        }
*/
    }//GEN-LAST:event_btnAlterarSubtipoActionPerformed

    private void btnNovoSubtipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoSubtipoActionPerformed
        // TODO add your handling code here:
        preparaNovoSubTipo();
        limpaCamposSubTipo();
        //cbxSubtipo.setSelectedIndex(0);
        //btnSalvarSubtipo.setEnabled(true);
        //cbxSubtipo.setEnabled(true);

    }//GEN-LAST:event_btnNovoSubtipoActionPerformed

    private void tbeSubTipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbeSubTipoMouseClicked
        tfdIDSubTipo.setText(tbeSubTipo.getValueAt(tbeSubTipo.getSelectedRow(), 0).toString());
        tfdDescricaoSubTipo.setText(tbeSubTipo.getValueAt(tbeSubTipo.getSelectedRow(), 1).toString());
        btnExcluirSubtipo.setEnabled(true);
        btnAlterarSubtipo.setEnabled(true);
        cbxSubtipo.setSelectedItem(tbeSubTipo.getValueAt(tbeSubTipo.getSelectedRow(), 2).toString());
        
        String nome = tbeSubTipo.getValueAt(tbeSubTipo.getSelectedRow(), 2).toString();
        String descricao = tbeSubTipo.getValueAt(tbeSubTipo.getSelectedRow(), 1).toString();
        
        File arq = new File(caminho + descricao + ".jpg");
        if (arq.exists() == true) {
            try {
                bi = ImageIO.read(arq); //carrega a imagem real num buffer
            } catch (IOException ex) {
                Logger.getLogger(TipoSubtipoView.class.getName()).log(Level.SEVERE, null, ex);
            }
            BufferedImage aux = new BufferedImage(150, 120, bi.getType());//cria um buffer auxiliar com o tamanho desejado  
            Graphics2D g = aux.createGraphics();//pega a classe graphics do aux para edicao  
            AffineTransform at = AffineTransform.getScaleInstance((double) 150 / bi.getWidth(), (double) 120 / bi.getHeight());//cria a transformacao  
            g.drawRenderedImage(bi, at);
            jLabel1.setText("");
            jLabel1.setIcon(new ImageIcon(aux));
        } else {
            jLabel1.setIcon(null);
            jLabel1.setText("Sem Imagem");
            bi = null;
        }

        //cbxSubtipo.setEnabled(false);

    }//GEN-LAST:event_tbeSubTipoMouseClicked

    private void cbxSubtipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSubtipoActionPerformed
        //TODO add your handling code here:
    }//GEN-LAST:event_cbxSubtipoActionPerformed


    private void btnAdicionarImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarImagemActionPerformed
        bi = null;
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Importar imagem");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        // int a = fileChooser.showOpenDialog(null);  
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File arquivo = fileChooser.getSelectedFile();//arquivo  

            try {
                bi = ImageIO.read(arquivo); //carrega a imagem real num buffer

            } catch (IOException ex) {
                Logger.getLogger(ConservacaoStatusView.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Erro!");
            }
            BufferedImage aux = new BufferedImage(150, 120, bi.getType());//cria um buffer auxiliar com o tamanho desejado  
            Graphics2D g = aux.createGraphics();//pega a classe graphics do aux para edicao  
            AffineTransform at = AffineTransform.getScaleInstance((double) 150 / bi.getWidth(), (double) 120 / bi.getHeight());//cria a transformacao  
            g.drawRenderedImage(bi, at);//pinta e transforma a imagem real no auxiliar 
            jLabel1.setText("");
            jLabel1.setIcon(new ImageIcon(aux));//seta no jlabe

        }
    }//GEN-LAST:event_btnAdicionarImagemActionPerformed

    private void btnCancelarSubtipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarSubtipoActionPerformed
        // TODO add your handling code here:
        limpaCamposSubTipo();
        preparaSalvareCancelarSubtipo();
        desativaCamposSubtipo();
    }//GEN-LAST:event_btnCancelarSubtipoActionPerformed

    private void btnCancelarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarTipoActionPerformed
        // TODO add your handling code here:
        limpaCamposTipo();
        preparaSalvareCancelarTipo();
        desativaCamposTipo();
    }//GEN-LAST:event_btnCancelarTipoActionPerformed

    public void salvarImagen() {

        try {
            ImageIO.write(bi, "jpg", new File(caminho + tfdDescricaoSubTipo.getText() + ".jpg"));
        } catch (IOException ex) {
            Logger.getLogger(TipoSubtipoView.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void preparaNovoSubTipo(){  
        btnNovoSubtipo.setEnabled(false);
        cbxSubtipo.setEnabled(true);
        tfdDescricaoSubTipo.setEnabled(true);
        btnAdicionarImagem.setEnabled(true);
        btnSalvarSubtipo.setEnabled(true);
        btnCancelarSubtipo.setEnabled(true);
        btnAlterarSubtipo.setEnabled(false);
        btnExcluirSubtipo.setEnabled(false);
        tbeSubTipo.setEnabled(false);
        tbeSubTipo.clearSelection();
        tfdDescricaoSubTipo.requestFocusInWindow();
    }
    public void preparaNovoTipo(){
        tfdDescricaoTipo.setEnabled(true);
        btnNovoTipo.setEnabled(false);
        btnSalvarTipo.setEnabled(true);
        btnCancelarTipo.setEnabled(true);
        btnAlterarTipo.setEnabled(false);
        btnExcluirTipo.setEnabled(false);
        tbeTipo.setEnabled(false);
        tbeTipo.clearSelection();   
    }
    public void preparaSalvareCancelarSubtipo(){
        btnNovoSubtipo.setEnabled(true);
        btnSalvarSubtipo.setEnabled(false);
        btnCancelarSubtipo.setEnabled(false);
        tbeSubTipo.setEnabled(true); 
    }
    public void preparaSalvareCancelarTipo(){
        btnNovoTipo.setEnabled(true);
        btnSalvarTipo.setEnabled(false);
        btnCancelarTipo.setEnabled(false);
        tbeTipo.setEnabled(true);
    }
    public void desativaCamposSubtipo(){
        cbxSubtipo.setEnabled(false);
        tfdDescricaoSubTipo.setEnabled(false);
        btnAdicionarImagem.setEnabled(false);
        btnSalvarSubtipo.setEnabled(false);
        btnCancelarSubtipo.setEnabled(false);
        btnAlterarSubtipo.setEnabled(false);
        btnExcluirSubtipo.setEnabled(false);
    } 
    public void desativaCamposTipo(){
        tfdDescricaoTipo.setEnabled(false);
    }
    public void ativaCamposTipo(){
        tfdDescricaoTipo.setEnabled(true);
    }
    public void ativaCamposSubtipo(){
        cbxSubtipo.setEnabled(true);
        tfdDescricaoSubTipo.setEnabled(true);
        btnAdicionarImagem.setEnabled(true);
    }
    public void preparaAlterarSubtipo(){
        btnNovoSubtipo.setEnabled(false);
        btnExcluirSubtipo.setEnabled(false);
        btnAlterarSubtipo.setEnabled(false);
        btnSalvarSubtipo.setEnabled(true);
        btnCancelarSubtipo.setEnabled(true);
        tbeSubTipo.setEnabled(false);
        tbeSubTipo.clearSelection();
    }
    public void preparaAlterarTipo(){
        btnNovoTipo.setEnabled(false);
        btnExcluirTipo.setEnabled(false);
        btnAlterarTipo.setEnabled(false);
        btnSalvarTipo.setEnabled(true);
        btnCancelarTipo.setEnabled(true);
        tbeTipo.setEnabled(false);
        tbeTipo.clearSelection();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarImagem;
    private javax.swing.JButton btnAlterarSubtipo;
    private javax.swing.JButton btnAlterarTipo;
    private javax.swing.JButton btnCancelarSubtipo;
    private javax.swing.JButton btnCancelarTipo;
    private javax.swing.JButton btnExcluirSubtipo;
    private javax.swing.JButton btnExcluirTipo;
    private javax.swing.JButton btnNovoSubtipo;
    private javax.swing.JButton btnNovoTipo;
    private javax.swing.JButton btnSalvarSubtipo;
    private javax.swing.JButton btnSalvarTipo;
    private javax.swing.JComboBox<String> cbxSubtipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDescricaoSubtipo;
    private javax.swing.JLabel lblDescricaoTipo;
    private javax.swing.JLabel lblIDTipo;
    private javax.swing.JLabel lblIDTipo1;
    private javax.swing.JLabel lblSubtipo;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JPanel pnlTipoSubtipo;
    private javax.swing.JSeparator spdTipoSubtipo;
    private javax.swing.JTable tbeSubTipo;
    private javax.swing.JTable tbeTipo;
    private javax.swing.JTextField tfdDescricaoSubTipo;
    private javax.swing.JTextField tfdDescricaoTipo;
    private javax.swing.JTextField tfdIDSubTipo;
    private javax.swing.JTextField tfdIDTipo;
    // End of variables declaration//GEN-END:variables
}
