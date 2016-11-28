/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.BlocoDAO;
import dao.OrgaoDAO;
import dao.GrauConservacaoDAO;
import dao.HistoricoAcaoDAO;
import dao.PatrimonioCompostoDAO;
import dao.PatrimonioDAO;
import dao.PisoDAO;
import dao.SalaDAO;
import dao.StatusDAO;
import dao.SubTipoDAO;
import dao.TipoDAO;
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
import model.OrgaoM;
import model.GrauConservacaoM;
import model.HistoricoAcaoM;
import model.PatrimonioCompostoM;
import model.PatrimonioM;
import model.PisoM;
import model.SalaM;
import model.StatusM;
import model.SubTipoM;
import model.TipoM;
import model.UnidadeM;
import model.UsuarioM;
import util.LimitaDigitosNum;
import util.LimiteDigitos;

/**
 * UNIVERSIDADE DO ESTADO DE MINAS GERAIS - Unidade Frutal
 * @author NUPSI - Núcle de Práticas em Sistemas de Informação
 * Equipe: Gustavo Pinoti,Leopoldo Ferreira, Marlon Moro, Murillo Cuervo
 */
public class PatrimonioView extends javax.swing.JInternalFrame {

//DAOS
    UnidadeDAO unidadeDAO;
    BlocoDAO blocoDAO;
    PisoDAO pisoDAO;
    SalaDAO salaDAO;
    TipoDAO tipoDAO;
    SubTipoDAO subtipoDAO;
    GrauConservacaoDAO grauDAO;
    GrauConservacaoDAO conservacaoDAO; //DUPLICADO 
    StatusDAO statusDAO;
    OrgaoDAO entidadeDAO;
    OrgaoDAO orgaoDAO; //DUPLICADO 
    PatrimonioDAO patrimonioDAO;  
    PatrimonioCompostoDAO patrimonioCompostoDAO;
    
//MODELS
    UnidadeM unid;
    BlocoM bloc;
    PisoM pis;
    PisoM piso; //DUPLICADO
    //SALA
    //TIPO
    SubTipoM subtipo;
    GrauConservacaoM conservacao;
    //STATUS
    OrgaoM orgao;
    PatrimonioM patrimonio;
    PatrimonioM auxPatrimonio; //PORQUE DUPLICADO?
    PatrimonioCompostoM patComposto;
    
//ARRAYS
    List<UnidadeM> listaUnidade;
    List<BlocoM> listaBloco;
    List<PisoM> listaPiso;
    List<SalaM> listaSala;
    List<TipoM> listaTipo;
    List<SubTipoM> listaSub;
    List<GrauConservacaoM> listaGrau;
    List<StatusM> listaStatus;
    List<OrgaoM> listaEntidade;
    List<PatrimonioM> listaPatrimonio;
    List<PatrimonioCompostoM> listaComposto;
    
    //Historico
    int idHistorico;
    String acao, codigoHistorico;
    String descricaoHistorico;
    UsuarioM usuarioAtivo;
    
//AUXILIARES
    int inicio = 0, quantMax, pagAtual, pagUltima;
    int cont = 0;
    int ultimoID;
    
    public PatrimonioView(UsuarioM usuarioAtivo) throws SQLException {
        //DAOS
        orgaoDAO = new OrgaoDAO();
        conservacaoDAO = new GrauConservacaoDAO();
        patrimonioCompostoDAO = new PatrimonioCompostoDAO();
        pisoDAO = new PisoDAO();
        blocoDAO = new BlocoDAO();       
        unidadeDAO = new UnidadeDAO();
        salaDAO = new SalaDAO();    
        grauDAO = new GrauConservacaoDAO();
        statusDAO = new StatusDAO();
        entidadeDAO = new OrgaoDAO();   
        tipoDAO = new TipoDAO();
        subtipoDAO = new SubTipoDAO();
        patrimonioDAO = new PatrimonioDAO();
        
        //MODELS
        orgao = new OrgaoM();
        conservacao = new GrauConservacaoM();
        subtipo = new SubTipoM();
        bloc = new BlocoM();
        unid = new UnidadeM();
        pis = new PisoM();
        auxPatrimonio = new PatrimonioM();
        
        //ARRAYS
        listaComposto = new ArrayList<>();
        listaPiso = new ArrayList<>();
        listaBloco = new ArrayList<>();
        listaUnidade = new ArrayList<>();
        listaSala = new ArrayList<>();
        listaGrau = new ArrayList<>();
        listaStatus = new ArrayList<>();
        listaEntidade = new ArrayList<>();
        listaTipo = new ArrayList<>();
        listaSub = new ArrayList<>();
        listaPatrimonio = new ArrayList<>();
        
        this.usuarioAtivo = usuarioAtivo;
        initComponents();
        
        this.setVisible(true);
        pnlPatrimonioComposto.setVisible(false);
       
        atualizaBoxUnidade();
        atualizaBoxTipo();
        atualizaBoxGrauConservacao();
        atualizaBoxStatus();
        atualizaBoxEntidade();
        
        atualizaTabelaPatrimonio(inicio);
        
        validaQuantidade();
        
        
        tfdCodigoPatrimonio.setDocument(new LimiteDigitos(60));
        tfdDescricaoPatrimonio.setDocument(new LimiteDigitos(90));
        tfdNotaFiscalPatrimonio.setDocument(new LimiteDigitos(45));
        tfdFiltroBusca.setDocument(new LimiteDigitos(45));
        tfdNavegacao.setDocument(new LimitaDigitosNum(3));
        
        
        preencheFiltro();

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
        cbxBloco = new javax.swing.JComboBox<>();
        lblSelecPiso = new javax.swing.JLabel();
        cbxPiso = new javax.swing.JComboBox<>();
        cbxOrgao = new javax.swing.JComboBox<>();
        ckxPatrimonioComposto = new javax.swing.JCheckBox();
        lblDescricao = new javax.swing.JLabel();
        lblSelecSala = new javax.swing.JLabel();
        tfdDescricaoPatrimonio = new javax.swing.JTextField();
        cbxSala = new javax.swing.JComboBox<>();
        lblGrauConservacao = new javax.swing.JLabel();
        cbxConservacao = new javax.swing.JComboBox<>();
        cbxStatus = new javax.swing.JComboBox<>();
        btnExcluirPatrimonio = new javax.swing.JButton();
        btnSalvarPatrimonio = new javax.swing.JButton();
        btnAlterarPatrimonio = new javax.swing.JButton();
        btnNovoPatrimonio = new javax.swing.JButton();
        lblID = new javax.swing.JLabel();
        tfdIDPatrimonio = new javax.swing.JTextField();
        lblSelecTipo = new javax.swing.JLabel();
        cbxTipo = new javax.swing.JComboBox<>();
        lblSelecSubtipo = new javax.swing.JLabel();
        cbxSubtipo = new javax.swing.JComboBox<>();
        lblStatus = new javax.swing.JLabel();
        btnCancelarPatrimonio = new javax.swing.JButton();
        lblSelecUnidade = new javax.swing.JLabel();
        lblNotaFiscal = new javax.swing.JLabel();
        tfdNotaFiscalPatrimonio = new javax.swing.JTextField();
        cbxUnidade = new javax.swing.JComboBox<>();
        lblCodigo = new javax.swing.JLabel();
        lblSelecBloco = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfdCodigoPatrimonio = new javax.swing.JTextField();
        pnlPatrimonioComposto = new javax.swing.JPanel();
        btnSalvarPatrimonioComposto = new javax.swing.JButton();
        btnAlterarPatrimonioComposto = new javax.swing.JButton();
        btnNovoPatrimonioComposto = new javax.swing.JButton();
        tfdIDComposto = new javax.swing.JTextField();
        cbxStatusPatrimonioComposto = new javax.swing.JComboBox<>();
        tfdDescricaoPatrimonioComposto = new javax.swing.JTextField();
        lblGrauConservacao1 = new javax.swing.JLabel();
        lblDescricao1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblStatus1 = new javax.swing.JLabel();
        cbxConservacaoPatrimonioComposto = new javax.swing.JComboBox<>();
        btnCancelarPatrimonioComposto = new javax.swing.JButton();
        btnExcluirPatrimonioComposto = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbePatrimonioComposto = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbePatrimonio = new javax.swing.JTable();
        btnProximo = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        lblQuantPaginas = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        tfdFiltroBusca = new javax.swing.JTextField();
        cbxFiltro = new javax.swing.JComboBox<>();
        btnLimpar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        tfdNavegacao = new javax.swing.JTextField();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Patrimônio");

        cbxBloco.setEnabled(false);
        cbxBloco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxBlocoActionPerformed(evt);
            }
        });
        cbxBloco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxBlocoKeyPressed(evt);
            }
        });

        lblSelecPiso.setText("Selecione o piso:");

        cbxPiso.setEnabled(false);
        cbxPiso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxPisoActionPerformed(evt);
            }
        });
        cbxPiso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxPisoKeyPressed(evt);
            }
        });

        cbxOrgao.setEnabled(false);
        cbxOrgao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxOrgaoActionPerformed(evt);
            }
        });
        cbxOrgao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxOrgaoKeyPressed(evt);
            }
        });

        ckxPatrimonioComposto.setText("Patrimonio Composto");
        ckxPatrimonioComposto.setEnabled(false);
        ckxPatrimonioComposto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckxPatrimonioCompostoActionPerformed(evt);
            }
        });

        lblDescricao.setText("Descrição");

        lblSelecSala.setText("Selecione a sala:");

        tfdDescricaoPatrimonio.setEnabled(false);
        tfdDescricaoPatrimonio.setMinimumSize(new java.awt.Dimension(6, 25));
        tfdDescricaoPatrimonio.setPreferredSize(new java.awt.Dimension(6, 25));
        tfdDescricaoPatrimonio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfdDescricaoPatrimonioKeyPressed(evt);
            }
        });

        cbxSala.setEnabled(false);
        cbxSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSalaActionPerformed(evt);
            }
        });
        cbxSala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxSalaKeyPressed(evt);
            }
        });

        lblGrauConservacao.setText("Grau de Conservação");

        cbxConservacao.setEnabled(false);
        cbxConservacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxConservacaoActionPerformed(evt);
            }
        });
        cbxConservacao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxConservacaoKeyPressed(evt);
            }
        });

        cbxStatus.setEnabled(false);
        cbxStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxStatusActionPerformed(evt);
            }
        });
        cbxStatus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxStatusKeyPressed(evt);
            }
        });

        btnExcluirPatrimonio.setText("Dar Baixa");
        btnExcluirPatrimonio.setEnabled(false);
        btnExcluirPatrimonio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirPatrimonioActionPerformed(evt);
            }
        });

        btnSalvarPatrimonio.setText("Salvar");
        btnSalvarPatrimonio.setEnabled(false);
        btnSalvarPatrimonio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarPatrimonioActionPerformed(evt);
            }
        });
        btnSalvarPatrimonio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnSalvarPatrimonioKeyPressed(evt);
            }
        });

        btnAlterarPatrimonio.setText("Alterar");
        btnAlterarPatrimonio.setEnabled(false);
        btnAlterarPatrimonio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarPatrimonioActionPerformed(evt);
            }
        });

        btnNovoPatrimonio.setText("Novo");
        btnNovoPatrimonio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoPatrimonioActionPerformed(evt);
            }
        });

        lblID.setText("ID");

        tfdIDPatrimonio.setEditable(false);
        tfdIDPatrimonio.setEnabled(false);

        lblSelecTipo.setText("Selecione o Tipo:");

        cbxTipo.setEnabled(false);
        cbxTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTipoActionPerformed(evt);
            }
        });
        cbxTipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxTipoKeyPressed(evt);
            }
        });

        lblSelecSubtipo.setText("Selecione a Subtipo:");

        cbxSubtipo.setEnabled(false);
        cbxSubtipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSubtipoActionPerformed(evt);
            }
        });
        cbxSubtipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxSubtipoKeyPressed(evt);
            }
        });

        lblStatus.setText("Status");

        btnCancelarPatrimonio.setText("Cancelar");
        btnCancelarPatrimonio.setEnabled(false);
        btnCancelarPatrimonio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarPatrimonioActionPerformed(evt);
            }
        });

        lblSelecUnidade.setText("Selecione a unidade:");

        lblNotaFiscal.setText("Nota Fiscal");

        tfdNotaFiscalPatrimonio.setEnabled(false);
        tfdNotaFiscalPatrimonio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfdNotaFiscalPatrimonioKeyPressed(evt);
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

        lblCodigo.setText("Código");

        lblSelecBloco.setText("Selecione o bloco:");

        jLabel2.setText("Orgão");

        tfdCodigoPatrimonio.setEnabled(false);
        tfdCodigoPatrimonio.setPreferredSize(new java.awt.Dimension(6, 25));
        tfdCodigoPatrimonio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfdCodigoPatrimonioKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSelecUnidade)
                    .addComponent(lblSelecBloco)
                    .addComponent(cbxBloco, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSelecTipo)
                    .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSelecSubtipo)
                    .addComponent(cbxSubtipo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxPiso, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSelecSala)
                    .addComponent(cbxSala, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSelecPiso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(lblDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGrauConservacao)
                    .addComponent(lblStatus)
                    .addComponent(lblNotaFiscal)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbxOrgao, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ckxPatrimonioComposto))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(tfdNotaFiscalPatrimonio, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbxConservacao, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfdDescricaoPatrimonio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblID)
                                .addComponent(tfdIDPatrimonio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfdCodigoPatrimonio, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(cbxStatus, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btnNovoPatrimonio, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalvarPatrimonio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelarPatrimonio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAlterarPatrimonio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcluirPatrimonio))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigo)
                    .addComponent(lblID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdCodigoPatrimonio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdIDPatrimonio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(lblDescricao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdDescricaoPatrimonio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblGrauConservacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxConservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStatus)
                .addGap(0, 0, 0)
                .addComponent(cbxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNotaFiscal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdNotaFiscalPatrimonio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxOrgao, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckxPatrimonioComposto))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluirPatrimonio)
                    .addComponent(btnNovoPatrimonio)
                    .addComponent(btnSalvarPatrimonio)
                    .addComponent(btnAlterarPatrimonio)
                    .addComponent(btnCancelarPatrimonio)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblSelecUnidade)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSelecBloco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxBloco, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSelecPiso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxPiso, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSelecSala)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxSala, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSelecTipo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSelecSubtipo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxSubtipo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnSalvarPatrimonioComposto.setText("Salvar");
        btnSalvarPatrimonioComposto.setEnabled(false);
        btnSalvarPatrimonioComposto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarPatrimonioCompostoActionPerformed(evt);
            }
        });
        btnSalvarPatrimonioComposto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnSalvarPatrimonioCompostoKeyPressed(evt);
            }
        });

        btnAlterarPatrimonioComposto.setText("Alterar");
        btnAlterarPatrimonioComposto.setEnabled(false);
        btnAlterarPatrimonioComposto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarPatrimonioCompostoActionPerformed(evt);
            }
        });

        btnNovoPatrimonioComposto.setText("Novo");
        btnNovoPatrimonioComposto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoPatrimonioCompostoActionPerformed(evt);
            }
        });

        tfdIDComposto.setEnabled(false);
        tfdIDComposto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfdIDCompostoActionPerformed(evt);
            }
        });

        cbxStatusPatrimonioComposto.setEnabled(false);
        cbxStatusPatrimonioComposto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxStatusPatrimonioCompostoActionPerformed(evt);
            }
        });
        cbxStatusPatrimonioComposto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxStatusPatrimonioCompostoKeyPressed(evt);
            }
        });

        tfdDescricaoPatrimonioComposto.setEnabled(false);
        tfdDescricaoPatrimonioComposto.setPreferredSize(new java.awt.Dimension(6, 25));
        tfdDescricaoPatrimonioComposto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfdDescricaoPatrimonioCompostoKeyPressed(evt);
            }
        });

        lblGrauConservacao1.setText("Grau de Conservação");

        lblDescricao1.setText("Descrição");

        jLabel1.setText("ID:");

        lblStatus1.setText("Status");

        cbxConservacaoPatrimonioComposto.setEnabled(false);
        cbxConservacaoPatrimonioComposto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxConservacaoPatrimonioCompostoActionPerformed(evt);
            }
        });
        cbxConservacaoPatrimonioComposto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxConservacaoPatrimonioCompostoKeyPressed(evt);
            }
        });

        btnCancelarPatrimonioComposto.setText("Cancelar");
        btnCancelarPatrimonioComposto.setEnabled(false);
        btnCancelarPatrimonioComposto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarPatrimonioCompostoActionPerformed(evt);
            }
        });

        btnExcluirPatrimonioComposto.setText("Excluir");
        btnExcluirPatrimonioComposto.setEnabled(false);
        btnExcluirPatrimonioComposto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirPatrimonioCompostoActionPerformed(evt);
            }
        });

        jLabel4.setText("Patrimonio Composto");

        tbePatrimonioComposto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Descrição", "Grau de Conservação", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbePatrimonioComposto.getTableHeader().setReorderingAllowed(false);
        tbePatrimonioComposto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbePatrimonioCompostoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbePatrimonioComposto);

        javax.swing.GroupLayout pnlPatrimonioCompostoLayout = new javax.swing.GroupLayout(pnlPatrimonioComposto);
        pnlPatrimonioComposto.setLayout(pnlPatrimonioCompostoLayout);
        pnlPatrimonioCompostoLayout.setHorizontalGroup(
            pnlPatrimonioCompostoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPatrimonioCompostoLayout.createSequentialGroup()
                .addGroup(pnlPatrimonioCompostoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPatrimonioCompostoLayout.createSequentialGroup()
                        .addGroup(pnlPatrimonioCompostoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cbxStatusPatrimonioComposto, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxConservacaoPatrimonioComposto, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlPatrimonioCompostoLayout.createSequentialGroup()
                                .addGroup(pnlPatrimonioCompostoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblGrauConservacao1)
                                    .addComponent(lblStatus1)
                                    .addComponent(jLabel1)
                                    .addGroup(pnlPatrimonioCompostoLayout.createSequentialGroup()
                                        .addGroup(pnlPatrimonioCompostoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblDescricao1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfdIDComposto, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(29, 29, 29)
                                        .addComponent(jLabel4)))
                                .addGap(60, 60, 60))
                            .addComponent(tfdDescricaoPatrimonioComposto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(119, 119, 119))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPatrimonioCompostoLayout.createSequentialGroup()
                        .addComponent(btnNovoPatrimonioComposto, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalvarPatrimonioComposto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarPatrimonioComposto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAlterarPatrimonioComposto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluirPatrimonioComposto)
                        .addGap(29, 29, 29)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 892, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlPatrimonioCompostoLayout.setVerticalGroup(
            pnlPatrimonioCompostoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPatrimonioCompostoLayout.createSequentialGroup()
                .addGroup(pnlPatrimonioCompostoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnlPatrimonioCompostoLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfdIDComposto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDescricao1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfdDescricaoPatrimonioComposto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblGrauConservacao1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxConservacaoPatrimonioComposto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblStatus1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxStatusPatrimonioComposto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlPatrimonioCompostoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnExcluirPatrimonioComposto)
                            .addComponent(btnSalvarPatrimonioComposto)
                            .addComponent(btnAlterarPatrimonioComposto)
                            .addComponent(btnCancelarPatrimonioComposto)
                            .addComponent(btnNovoPatrimonioComposto)))
                    .addGroup(pnlPatrimonioCompostoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbePatrimonio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Código", "Descrição", "Grau de Consevação", "Status", "Nota Fiscal", "Orgão"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbePatrimonio.getTableHeader().setReorderingAllowed(false);
        tbePatrimonio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbePatrimonioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbePatrimonio);
        if (tbePatrimonio.getColumnModel().getColumnCount() > 0) {
            tbePatrimonio.getColumnModel().getColumn(0).setPreferredWidth(20);
        }

        btnProximo.setText(">>");
        btnProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProximoActionPerformed(evt);
            }
        });

        btnAnterior.setText("<<");
        btnAnterior.setEnabled(false);
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        lblQuantPaginas.setText("quant de paginas");

        btnBuscar.setText("Buscar");
        btnBuscar.setPreferredSize(new java.awt.Dimension(65, 26));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tfdFiltroBusca.setPreferredSize(new java.awt.Dimension(566, 26));
        tfdFiltroBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfdFiltroBuscaActionPerformed(evt);
            }
        });
        tfdFiltroBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfdFiltroBuscaKeyPressed(evt);
            }
        });

        cbxFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnLimpar.setText("Limpar");
        btnLimpar.setPreferredSize(new java.awt.Dimension(65, 26));
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        jLabel3.setText("Ir para:");

        tfdNavegacao.setPreferredSize(new java.awt.Dimension(6, 23));
        tfdNavegacao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfdNavegacaoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlPatrimonioComposto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 1, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 909, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnAnterior)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblQuantPaginas)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnProximo)
                                        .addGap(266, 266, 266)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfdNavegacao, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(19, 19, 19))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbxFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfdFiltroBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cbxFiltro)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfdFiltroBusca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblQuantPaginas)
                            .addComponent(btnAnterior)
                            .addComponent(btnProximo)
                            .addComponent(jLabel3)
                            .addComponent(tfdNavegacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPatrimonioComposto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void iniciaComposto(){
        
        atualizaStatusComposto();
        atualizaGrauComposto();
        atualizaTabelaCompostoExistente();
        
    }
    
    
    public void atualizaTabelaPatrimonio(int inicio) {

        try {
           //listaPatrimonio = patrimonioDAO.listaTodos();
            listaPatrimonio = patrimonioDAO.lista100(inicio);
            
        } catch (SQLException ex) {
            Logger.getLogger(OrgaoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[listaPatrimonio.size()][8];
        int i = 0;
        for (PatrimonioM pat : listaPatrimonio) {
            dados[i][0] = String.valueOf(pat.getId());
            dados[i][1] = pat.getCodigo();
            dados[i][2] = pat.getDescricao();
            dados[i][3] = pat.getSubTipo().getDescricao();
            dados[i][4] = pat.getSala().getDescricao();
            dados[i][5] = pat.getGrau_conservacao().getDescricao();
            dados[i][6] = pat.getStatus().getNome();
            dados[i][7] = pat.getEntidade().getNome();
            i++;
        }
        
        String tituloColuna[] = {"ID", "Codigo", "Descrição", "Subtipo", "Sala", "Grau de Conservação", "Status", "Entidade"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        tbePatrimonio.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        tbePatrimonio.getColumnModel().getColumn(0).setPreferredWidth(60);
        tbePatrimonio.getColumnModel().getColumn(1).setPreferredWidth(90);
        tbePatrimonio.getColumnModel().getColumn(2).setPreferredWidth(215);
        tbePatrimonio.getColumnModel().getColumn(3).setPreferredWidth(90);
        tbePatrimonio.getColumnModel().getColumn(4).setPreferredWidth(215);
        tbePatrimonio.getColumnModel().getColumn(5).setPreferredWidth(90);
        tbePatrimonio.getColumnModel().getColumn(6).setPreferredWidth(90);
        tbePatrimonio.getColumnModel().getColumn(7).setPreferredWidth(90);

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tbePatrimonio.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        tbePatrimonio.setRowHeight(25);
        tbePatrimonio.updateUI();
    }
    
    public void atualizaTabelaBusca(){
         String dados[][] = new String[listaPatrimonio.size()][8];
        int i = 0;
        for (PatrimonioM pat : listaPatrimonio) {
            dados[i][0] = String.valueOf(pat.getId());
            dados[i][1] = pat.getCodigo();
            dados[i][2] = pat.getDescricao();
            dados[i][3] = pat.getSubTipo().getDescricao();
            dados[i][4] = pat.getSala().getDescricao();
            dados[i][5] = pat.getGrau_conservacao().getDescricao();
            dados[i][6] = pat.getStatus().getNome();
            dados[i][7] = pat.getEntidade().getNome();
            i++;
        }
        String tituloColuna[] = {"ID", "Codigo", "Descrição", "Subtipo", "Sala", "Grau de Conservação", "Status", "Entidade"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        tbePatrimonio.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        
        

        tbePatrimonio.getColumnModel().getColumn(0).setPreferredWidth(60);
        tbePatrimonio.getColumnModel().getColumn(1).setPreferredWidth(90);
        tbePatrimonio.getColumnModel().getColumn(2).setPreferredWidth(215);
        tbePatrimonio.getColumnModel().getColumn(3).setPreferredWidth(90);
        tbePatrimonio.getColumnModel().getColumn(4).setPreferredWidth(215);
        tbePatrimonio.getColumnModel().getColumn(5).setPreferredWidth(90);
        tbePatrimonio.getColumnModel().getColumn(6).setPreferredWidth(90);
        tbePatrimonio.getColumnModel().getColumn(7).setPreferredWidth(90);

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tbePatrimonio.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        tbePatrimonio.setRowHeight(25);
        tbePatrimonio.updateUI();
    
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
    
    public void preencheFiltro(){
        cbxFiltro.removeAllItems();
        cbxFiltro.addItem("--Selecione--");
        cbxFiltro.addItem("Codigo");
        cbxFiltro.addItem("Descrição");
    }
    
    public void atualizaBoxTipo() {
        cbxTipo.removeAllItems();
        cbxTipo.addItem("--Selecione--");
        try {
            listaTipo = tipoDAO.listaTodos();
        } catch (SQLException ex) {
            Logger.getLogger(OrgaoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[listaTipo.size()][5];
        int i = 0;
        for (TipoM tip : listaTipo) {
            cbxTipo.addItem(tip.getDescricao());
        }
    }

    public void atualizaBoxEntidade() {
        cbxOrgao.removeAllItems();
        cbxOrgao.addItem("--Selecione--");
        try {
            listaEntidade = entidadeDAO.listaTodos();
        } catch (SQLException ex) {
            Logger.getLogger(OrgaoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[listaEntidade.size()][5];
        int i = 0;
        for (OrgaoM ent : listaEntidade) {
            cbxOrgao.addItem(ent.getNome());
        }
    }

    public void atualizaBoxGrauConservacao() {
        cbxConservacao.removeAllItems();
        cbxConservacao.addItem("--Selecione--");
        try {
            listaGrau = grauDAO.listaTodos();
        } catch (SQLException ex) {
            Logger.getLogger(OrgaoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[listaGrau.size()][5];
        int i = 0;
        for (GrauConservacaoM gra : listaGrau) {
            cbxConservacao.addItem(gra.getDescricao());
        }
    }

    public void atualizaBoxStatus() {
        cbxStatus.removeAllItems();
        cbxStatus.addItem("--Selecione--");
        try {
            listaStatus = statusDAO.listaTodos();
        } catch (SQLException ex) {
            Logger.getLogger(OrgaoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[listaStatus.size()][5];
        int i = 0;
        for (StatusM stat : listaStatus) {
            cbxStatus.addItem(stat.getNome());
        }
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

   

    private void cbxConservacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxConservacaoActionPerformed

    }//GEN-LAST:event_cbxConservacaoActionPerformed

    private void cbxTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoActionPerformed
        if (cbxTipo.getSelectedIndex() < 1) {
            cbxSubtipo.removeAllItems();
            cbxSubtipo.addItem("--Selecione--");
        } else {
            cbxSubtipo.removeAllItems();
            cbxSubtipo.addItem("--Selecione--");
            TipoM tip = new TipoM();
            try {
                tip = tipoDAO.buscaNome(cbxTipo.getSelectedItem().toString());
                listaSub = subtipoDAO.buscaTipo(tip.getId());
                for (SubTipoM subt : listaSub) {
                    cbxSubtipo.addItem(subt.getDescricao());
                }
            } catch (SQLException ex) {
                cbxSubtipo.removeAllItems();
                cbxSubtipo.addItem("--Selecione--");
            }
        }

    }//GEN-LAST:event_cbxTipoActionPerformed

    private void btnNovoPatrimonioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoPatrimonioActionPerformed
        //limpaCamposPatrimonio();
        preparaNovo();
        ativaCamposNovo();
        tfdCodigoPatrimonio.setText("");
        tfdIDPatrimonio.setText("");
        cbxUnidade.requestFocusInWindow();
        //tfdCodigoPatrimonio.requestFocusInWindow();  
        pnlPatrimonioComposto.setVisible(false);
    }//GEN-LAST:event_btnNovoPatrimonioActionPerformed

    
    public PatrimonioM pegaPatrimonio(){
        try {
            PatrimonioM patrimo = patrimonioDAO.busca(Integer.parseInt(tbePatrimonio.getValueAt(tbePatrimonio.getSelectedRow(), 0).toString()));
            return patrimo;
        } catch (SQLException ex) {
            Logger.getLogger(PatrimonioView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private void btnSalvarPatrimonioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarPatrimonioActionPerformed
     
        if (tfdDescricaoPatrimonio.getText().isEmpty() || tfdCodigoPatrimonio.getText().isEmpty() || tfdNotaFiscalPatrimonio.getText().isEmpty() || cbxSala.getSelectedIndex() == 0 || cbxConservacao.getSelectedIndex() == 0 || cbxOrgao.getSelectedIndex() == 0 || cbxStatus.getSelectedIndex() == 0 || cbxSubtipo.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Prencha todos os campos.", "Erro", JOptionPane.WARNING_MESSAGE);
            tfdDescricaoPatrimonio.requestFocusInWindow();
        } else  if (tfdIDPatrimonio.getText().isEmpty()){
            //Se estiver o campo ID estiver vazio siginifica que será um novo objeto.
            patrimonio = new PatrimonioM();
            patrimonio.setDescricao(tfdDescricaoPatrimonio.getText());
            patrimonio.setCodigo(tfdCodigoPatrimonio.getText());
            patrimonio.setEntidade(pegaEntidade());
            patrimonio.setGrau_conservacao(pegaGrau(cbxConservacao.getSelectedItem().toString()));
            patrimonio.setNotaFiscal(tfdNotaFiscalPatrimonio.getText());
            patrimonio.setSala(pegaSala());
            patrimonio.setStatus(pegaStatus(cbxStatus.getSelectedItem().toString()));
            patrimonio.setSubTipo(pegaSubtipo());
            patrimonio.setKit(ckxPatrimonioComposto.isSelected());
            try {
                //Recebe o ultimo ID gerado
                ultimoID = patrimonioDAO.salvar(patrimonio);
                auxPatrimonio = patrimonioDAO.busca(ultimoID);
                
                idHistorico = ultimoID;
                acao = "Novo Patrimonio";
                codigoHistorico = patrimonio.getCodigo();
                descricaoHistorico = patrimonio.getDescricao();
                salvaHistorico();
                // a partir daqui tem que liberar a tela do composto
                if (ckxPatrimonioComposto.isSelected())
                {                    
                    preparaSalvareCancelar();
                    salvarMantendoInformacoes();
                    pnlPatrimonioComposto.setVisible(true);
                    tfdIDPatrimonio.setText("" + ultimoID);
                    iniciaComposto();
                    JOptionPane.showMessageDialog(null, "Cadastre os itens do kit", "Cadastro de Kit", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Patrimônio cadastrado com sucesso..", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    preparaSalvareCancelar();                    
                    salvarMantendoInformacoes(); 
                }                         
                //atualizaTabelaPatrimonio(inicio);
                limpaCamposPatrimonio();
                //limpa a busca
                inicio = 0;
                atualizaTabelaPatrimonio(inicio);
                cbxFiltro.setSelectedIndex(0);
                tfdFiltroBusca.setText("");
                btnAnterior.setEnabled(false);
                btnProximo.setEnabled(true);

                try {
                    validaQuantidade();
                } catch (SQLException ex) {
                    Logger.getLogger(PatrimonioView.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            } catch (SQLException ex) {
                Logger.getLogger(OrgaoView.class.getName()).log(Level.SEVERE, null, ex);
                if (ex.getErrorCode() == 1062) {
                    JOptionPane.showMessageDialog(null, "Patrimônio já existente.", "Erro", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
                }
            }
        } else {
            int auxID = Integer.parseInt(tfdIDPatrimonio.getText());
            //Se for uma situação de alteração ele vai pegar o ID do patrimonio direto do textfield
            try {
                
                //cria o auxiliar a partir do ID
                auxPatrimonio = patrimonioDAO.busca(auxID);
            } catch (SQLException ex) {
                Logger.getLogger(PatrimonioView.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Se o campo ID não estiver vazio trata-se de uma alteração
            patrimonio = new PatrimonioM();
            patrimonio.setId(Integer.parseInt(tfdIDPatrimonio.getText()));
            patrimonio.setDescricao(tfdDescricaoPatrimonio.getText());
            patrimonio.setCodigo(tfdCodigoPatrimonio.getText());
            patrimonio.setNotaFiscal(tfdNotaFiscalPatrimonio.getText());
            try {
                patrimonio.setGrau_conservacao(grauDAO.buscaNome(cbxConservacao.getSelectedItem().toString()));
            } catch (SQLException ex) {
                Logger.getLogger(PatrimonioView.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                patrimonio.setStatus(statusDAO.buscaNome(cbxStatus.getSelectedItem().toString()));
            } catch (SQLException ex) {
                Logger.getLogger(PatrimonioView.class.getName()).log(Level.SEVERE, null, ex);
            }
            patrimonio.setKit(ckxPatrimonioComposto.isSelected());
            
            try{
                
                idHistorico = patrimonio.getId();
                acao = "Alterar Patrimonio";
                descricaoHistorico = patrimonio.getDescricao();
                salvaHistorico();
                
                patrimonioDAO.alterar(patrimonio);               
                JOptionPane.showMessageDialog(null, "Patrimônio atualizado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                desativaCampos();
                preparaSalvareCancelar();
                limpaCamposPatrimonio();
                if(cont == 0){
                    atualizaTabelaPatrimonio(inicio);
                }else if(cont ==1 ){
                    listaPatrimonio = patrimonioDAO.buscaPatrimonio100(tfdFiltroBusca.getText(), inicio);
                    atualizaTabelaBusca();
                }else if(cont == 2){
                    listaPatrimonio = patrimonioDAO.buscaDescricao100(tfdFiltroBusca.getText(), inicio);
                    atualizaTabelaBusca();
                }
                //atualizaTabelaPatrimonio(inicio);
                
                
            }catch (SQLException ex) {
                Logger.getLogger(OrgaoView.class.getName()).log(Level.SEVERE, null, ex);
                 if (ex.getErrorCode() == 1062) {
                    JOptionPane.showMessageDialog(null, "Patrimônio já existente.", "Erro", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
                }
            } 
        }    
    }//GEN-LAST:event_btnSalvarPatrimonioActionPerformed

    private void btnExcluirPatrimonioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirPatrimonioActionPerformed
        if (tfdIDPatrimonio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione um Patrimônio.", "Erro", JOptionPane.WARNING_MESSAGE);
        } else {
            if (JOptionPane.showConfirmDialog(null, "Deseja dar Baixa em: " + tfdDescricaoPatrimonio.getText() + "\nCodigo: "+tfdCodigoPatrimonio.getText()+" ?") == 0) {
                if(JOptionPane.showConfirmDialog(null, "O Processo de baixa é algo irreversível. Deseja realmente baixar?") == 0){
                try {
                    PatrimonioM patrimonioBaixar = patrimonioDAO.busca(Integer.parseInt(tfdIDPatrimonio.getText()));
                    PatrimonioDAO.baixar(patrimonioBaixar);
                    limpaCamposPatrimonio();
                    //atualizaTabelaPatrimonio();
                    //atualizaTabelaPatrimonio(inicio);
                    switch (cont) {
                        case 0:
                            atualizaTabelaPatrimonio(inicio);
                            break;
                        case 1:
                            listaPatrimonio = patrimonioDAO.buscaPatrimonio100(tfdNavegacao.getText(), inicio);
                            atualizaTabelaBusca();
                            break;
                        case 2:
                            listaPatrimonio = patrimonioDAO.buscaDescricao100(tfdNavegacao.getText(), inicio);
                            atualizaTabelaBusca();
                            break;
                        default:
                            break;
                    }
                    preparaExcluir();
                    desativaCampos();
                    acao = "Baixar Patrimomio";
                    idHistorico = patrimonioBaixar.getId();
                    descricaoHistorico = patrimonioBaixar.getDescricao();
                    salvaHistorico();
                    
                    JOptionPane.showMessageDialog(null, "Patrimônio Baixado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    pnlPatrimonioComposto.setVisible(false);
                } catch (SQLException ex) {
                    //Logger.getLogger(OrgaoView.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage());
                    
                }

            }
            }
        
        }
        
    }//GEN-LAST:event_btnExcluirPatrimonioActionPerformed

    private void btnAlterarPatrimonioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarPatrimonioActionPerformed
        preparaAlterar();
        ativaCamposAlterar();
    }//GEN-LAST:event_btnAlterarPatrimonioActionPerformed

    private void btnCancelarPatrimonioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarPatrimonioActionPerformed
        limpaCamposPatrimonio();
        preparaSalvareCancelar();
        desativaCampos();       
    }//GEN-LAST:event_btnCancelarPatrimonioActionPerformed

    private void cbxOrgaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxOrgaoActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cbxOrgaoActionPerformed

    private void ckxPatrimonioCompostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckxPatrimonioCompostoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ckxPatrimonioCompostoActionPerformed

    private void cbxSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSalaActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cbxSalaActionPerformed

    private void cbxPisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxPisoActionPerformed
        if (cbxPiso.getSelectedIndex() < 1) {
            cbxSala.removeAllItems();
            cbxSala.addItem("--Selecione--");
        } else {
            cbxSala.removeAllItems();
            cbxSala.addItem("--Selecione--");
            //PisoM pis = new PisoM();

            try {
                //pis = pisoDAO.buscaNome(cbxPiso.getSelectedItem().toString());
                pis = pisoDAO.busca_id_bloco(bloc.getId(), cbxPiso.getSelectedItem().toString());
                listaSala = salaDAO.buscaPis(pis.getId());
                for (SalaM sal : listaSala) {
                    cbxSala.addItem(sal.getDescricao());
                }
            } catch (SQLException ex) {
                cbxPiso.removeAllItems();
                cbxPiso.addItem("--Selecione--");
            }
        }
        
    }//GEN-LAST:event_cbxPisoActionPerformed

    private void cbxBlocoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxBlocoActionPerformed
        if (cbxBloco.getSelectedIndex() < 1) {
            cbxPiso.removeAllItems();
            cbxPiso.addItem("--Selecione--");
        } else {
            cbxPiso.removeAllItems();
            cbxPiso.addItem("--Selecione--");

            try {
                //bloc = blocoDAO.buscaNome(cbxBloco.getSelectedItem().toString());
                bloc = blocoDAO.busca_id_unidade(unid.getId(), cbxBloco.getSelectedItem().toString());
                listaPiso = pisoDAO.buscaBloc(bloc.getId());
                for (PisoM pis : listaPiso) {
                    cbxPiso.addItem(pis.getDescricao());
                }
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
            //UnidadeM unid = new UnidadeM();
            try {
                unid = unidadeDAO.buscaNome(cbxUnidade.getSelectedItem().toString());
                listaBloco = blocoDAO.buscaUni(unid.getId());
                for (BlocoM bloc : listaBloco) {
                    cbxBloco.addItem(bloc.getDescricao());
                }
            } catch (SQLException ex) {
                Logger.getLogger(PisoView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      

    }//GEN-LAST:event_cbxUnidadeActionPerformed

    private void tbePatrimonioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbePatrimonioMouseClicked
        tfdDescricaoPatrimonioComposto.setText("");
        tfdIDComposto.setText("");
        

        tfdIDPatrimonio.setText(tbePatrimonio.getValueAt(tbePatrimonio.getSelectedRow(), 0).toString());
        tfdCodigoPatrimonio.setText(tbePatrimonio.getValueAt(tbePatrimonio.getSelectedRow(), 1).toString());
        tfdDescricaoPatrimonio.setText(tbePatrimonio.getValueAt(tbePatrimonio.getSelectedRow(), 2).toString());
        PatrimonioM patri = pegaPatrimonio();

        cbxTipo.setSelectedItem(patri.getSubTipo().getTipo().getDescricao());
        cbxSubtipo.setSelectedItem(tbePatrimonio.getValueAt(tbePatrimonio.getSelectedRow(), 3).toString());

        cbxUnidade.setSelectedItem(patri.getSala().getPiso().getBloco().getUnidadeM().getNome());
        cbxBloco.setSelectedItem(patri.getSala().getPiso().getBloco().getDescricao());
        cbxPiso.setSelectedItem(patri.getSala().getPiso().getDescricao());
        cbxSala.setSelectedItem(patri.getSala().getDescricao());
        cbxConservacao.setSelectedItem(tbePatrimonio.getValueAt(tbePatrimonio.getSelectedRow(), 5).toString());
        cbxStatus.setSelectedItem(tbePatrimonio.getValueAt(tbePatrimonio.getSelectedRow(), 6).toString());
        cbxOrgao.setSelectedItem(tbePatrimonio.getValueAt(tbePatrimonio.getSelectedRow(), 7).toString());
        tfdNotaFiscalPatrimonio.setText(patri.getNotaFiscal());
        ckxPatrimonioComposto.setSelected(patri.getKit());

        auxPatrimonio = patri;
        
        preparaSelecaoTabelaPatrimonio();
        if(patri.getKit())
        {
            iniciaComposto();
            pnlPatrimonioComposto.setVisible(true);
        }else
        {
            pnlPatrimonioComposto.setVisible(false);
            limpaCamposComposto();
            preparaSalvarCancelarComposto();
            desativaCamposComposto();
        }

        
    }//GEN-LAST:event_tbePatrimonioMouseClicked

    private void btnExcluirPatrimonioCompostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirPatrimonioCompostoActionPerformed
        // TODO add your handling code here:
        if (tfdIDComposto.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Selecione um Item.", "Erro", JOptionPane.WARNING_MESSAGE);
        } else {
            patComposto = new PatrimonioCompostoM();
            patComposto.setId(Integer.parseInt(tfdIDComposto.getText()));
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja Excluir: " + tfdDescricaoPatrimonioComposto.getText() + " ?");
            if (confirma == 0) {
                try {
                    PatrimonioCompostoDAO.excluir(patComposto);
                    atualizaTabelaCompostoExistente();
                    limpaCamposComposto();
                    preparaExcluir();
                } catch (SQLException ex) {
                    Logger.getLogger(PatrimonioView.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
                }

            }
        }
    }//GEN-LAST:event_btnExcluirPatrimonioCompostoActionPerformed

    private void btnCancelarPatrimonioCompostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarPatrimonioCompostoActionPerformed
        // TODO add your handling code here:
        limpaCamposComposto();
        preparaSalvarCancelarComposto();
        desativaCamposComposto();
    }//GEN-LAST:event_btnCancelarPatrimonioCompostoActionPerformed

    private void cbxConservacaoPatrimonioCompostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxConservacaoPatrimonioCompostoActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cbxConservacaoPatrimonioCompostoActionPerformed

    private void tfdIDCompostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfdIDCompostoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfdIDCompostoActionPerformed

    private void btnNovoPatrimonioCompostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoPatrimonioCompostoActionPerformed
        // TODO add your handling code here:
        limpaCamposComposto();
        ativaCamposComposto();
        preparaNovoComposto();
        tfdDescricaoPatrimonioComposto.requestFocusInWindow();
    }//GEN-LAST:event_btnNovoPatrimonioCompostoActionPerformed

    private void btnAlterarPatrimonioCompostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarPatrimonioCompostoActionPerformed
        // TODO add your handling code here:
        preparaAlterarComposto();
        ativaCamposComposto();
    }//GEN-LAST:event_btnAlterarPatrimonioCompostoActionPerformed

    private void btnSalvarPatrimonioCompostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarPatrimonioCompostoActionPerformed
        // TODO add your handling code here:
        String status, conservacao;
        if (tfdDescricaoPatrimonioComposto.getText().isEmpty() || cbxConservacaoPatrimonioComposto.getSelectedIndex() == 0 || cbxStatusPatrimonioComposto.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Prencha todos os campos.", "Erro", JOptionPane.WARNING_MESSAGE);
            tfdDescricaoPatrimonioComposto.requestFocusInWindow();
        } else if (tfdIDComposto.getText().isEmpty()){
            status = cbxStatusPatrimonioComposto.getSelectedItem().toString();
            conservacao = cbxConservacaoPatrimonioComposto.getSelectedItem().toString();
            patComposto = new PatrimonioCompostoM();
            patComposto.setDescricao(tfdDescricaoPatrimonioComposto.getText());
            patComposto.setGrau(pegaGrau(conservacao));
            patComposto.setStatus(pegaStatus(status));
            patComposto.setPatrimonio(auxPatrimonio);
            try {
                patrimonioCompostoDAO.salvar(patComposto);
                JOptionPane.showMessageDialog(null, "Gravado com Sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            preparaSalvarCancelarComposto();
            desativaCamposComposto();
            atualizaTabelaCompostoExistente();
            limpaCamposComposto();

        } else {
            status = cbxStatusPatrimonioComposto.getSelectedItem().toString();
            conservacao = cbxConservacaoPatrimonioComposto.getSelectedItem().toString();

            patComposto = new PatrimonioCompostoM();
            patComposto.setId(Integer.parseInt(tfdIDComposto.getText()));
            patComposto.setDescricao(tfdDescricaoPatrimonioComposto.getText());
            patComposto.setGrau(pegaGrau(conservacao));
            patComposto.setStatus(pegaStatus(status));
            patComposto.setPatrimonio(auxPatrimonio);
            try{
                patrimonioCompostoDAO.alterar(patComposto);
                JOptionPane.showMessageDialog(null, "Alterado com Sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(PatrimonioView.class.getName()).log(Level.SEVERE, null, ex);
            }
            preparaSalvarCancelarComposto();
            desativaCamposComposto();
            atualizaTabelaCompostoExistente();
            limpaCamposComposto();
        }
    }//GEN-LAST:event_btnSalvarPatrimonioCompostoActionPerformed

    private void tbePatrimonioCompostoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbePatrimonioCompostoMouseClicked
        // TODO add your handling code here:
        PatrimonioCompostoM auxIDComposto = new PatrimonioCompostoM();
        try {
            auxIDComposto = patrimonioCompostoDAO.buscaDescricao(tbePatrimonioComposto.getValueAt(tbePatrimonioComposto.getSelectedRow(), 0).toString());
        } catch (SQLException ex) {
            Logger.getLogger(PatrimonioView.class.getName()).log(Level.SEVERE, null, ex);
        }
        tfdIDComposto.setText(tbePatrimonioComposto.getValueAt(tbePatrimonioComposto.getSelectedRow(), 0).toString());
        tfdDescricaoPatrimonioComposto.setText(tbePatrimonioComposto.getValueAt(tbePatrimonioComposto.getSelectedRow(), 1).toString());
        cbxConservacaoPatrimonioComposto.setSelectedItem(tbePatrimonioComposto.getValueAt(tbePatrimonioComposto.getSelectedRow(), 2).toString());
        cbxStatusPatrimonioComposto.setSelectedItem(tbePatrimonioComposto.getValueAt(tbePatrimonioComposto.getSelectedRow(), 3).toString());
        preparaSelecaoComposto();
    }//GEN-LAST:event_tbePatrimonioCompostoMouseClicked

    private void btnProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProximoActionPerformed
        switch (cont) {
            case 0:
                proximoNormal();
                atualizaTabelaBusca();
                break;
            case 1:
                proximoBuscaPatrimonio();
                atualizaTabelaBusca();
                break;
            case 2:
                proximoBuscaDescricao();
                atualizaTabelaBusca();
                break;
            default:
                break;
        }
    }//GEN-LAST:event_btnProximoActionPerformed
   
    
    public void proximoNormal(){
        inicio+=100;
        atualizaTabelaPatrimonio(inicio);
        btnAnterior.setEnabled(true);
        pagAtual++;
        lblQuantPaginas.setText(pagAtual+"/"+pagUltima);
        if(inicio>=(quantMax-100)){
            btnProximo.setEnabled(false);
        }
    }
    public void proximoBuscaPatrimonio(){
        inicio+=100;
        try {
            listaPatrimonio = patrimonioDAO.buscaPatrimonio100(tfdFiltroBusca.getText(), inicio);
        } catch (SQLException ex) {
            Logger.getLogger(PatrimonioView.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnAnterior.setEnabled(true);
        pagAtual++;
        lblQuantPaginas.setText(pagAtual+"/"+pagUltima);
        if(inicio>=(quantMax-100)){
            btnProximo.setEnabled(false);
        }
    }
    public void proximoBuscaDescricao(){
        inicio+=100;
        try {
            listaPatrimonio = patrimonioDAO.buscaDescricao100(tfdFiltroBusca.getText(), inicio);
        } catch (SQLException ex) {
            Logger.getLogger(PatrimonioView.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnAnterior.setEnabled(true);
        pagAtual++;
        lblQuantPaginas.setText(pagAtual+"/"+pagUltima);
        if(inicio>=(quantMax-100)){
            btnProximo.setEnabled(false);
        }
    }
    
    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        switch (cont) {
            case 0:
                anteriorNormal();
                atualizaTabelaBusca();
                break;
            case 1:
                anteriorBuscaPatrimonio();
                atualizaTabelaBusca();
                break;
            case 2:
                anteriorBuscaDescricao();
                atualizaTabelaBusca();
                break;
            default:
                break;
        }
    }//GEN-LAST:event_btnAnteriorActionPerformed
   
    
    public void anteriorNormal(){
        inicio -=100;
        atualizaTabelaPatrimonio(inicio);
        btnProximo.setEnabled(true);
        pagAtual--;
        lblQuantPaginas.setText(pagAtual+"/"+pagUltima);
        if(inicio==0){
            btnAnterior.setEnabled(false);
        }
    }
    public void anteriorBuscaPatrimonio(){
        inicio -=100;
        try {
            listaPatrimonio = patrimonioDAO.buscaPatrimonio100(tfdFiltroBusca.getText(), inicio);
        } catch (SQLException ex) {
            Logger.getLogger(PatrimonioView.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnProximo.setEnabled(true);
        pagAtual--;
        lblQuantPaginas.setText(pagAtual+"/"+pagUltima);
        if(inicio==0){
            btnAnterior.setEnabled(false);
        }
    }
    public void anteriorBuscaDescricao(){
        inicio -=100;
        try {
            listaPatrimonio = patrimonioDAO.buscaDescricao100(tfdFiltroBusca.getText(), inicio);
        } catch (SQLException ex) {
            Logger.getLogger(PatrimonioView.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnProximo.setEnabled(true);
        pagAtual--;
        lblQuantPaginas.setText(pagAtual+"/"+pagUltima);
        if(inicio==0){
            btnAnterior.setEnabled(false);
        }
    }
    
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        inicio = 0;
        btnProximo.setEnabled(true);
        btnAnterior.setEnabled(false);
        
        if(tfdFiltroBusca.getText().equals("") || cbxFiltro.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Selecione um Filtro!!");
        }else{

            try {
                
                if(cbxFiltro.getSelectedItem().toString().equals("Codigo")) {
                    listaPatrimonio = null;
                    listaPatrimonio = patrimonioDAO.buscaPatrimonio100(tfdFiltroBusca.getText(), inicio);
                    validaQuantidadeBuscaCodigo();
                    cont = 1;
                    if(listaPatrimonio.size() < 1){
                           JOptionPane.showMessageDialog(null, "Código não Encontrado");
                           btnAnterior.setEnabled(false);
                           btnProximo.setEnabled(false);
                           lblQuantPaginas.setText("0/0");
                    }
                }else
                if(cbxFiltro.getSelectedItem().toString().equals("Descrição")){
                    listaPatrimonio = null;
                    listaPatrimonio = patrimonioDAO.buscaDescricao100(tfdFiltroBusca.getText(), inicio);
                    validaQuantidadeBuscaDescricao();
                    cont = 2;
                    if(listaPatrimonio.size() < 1){
                           JOptionPane.showMessageDialog(null, "Descrição não Encontrada");
                           btnAnterior.setEnabled(false);
                           btnProximo.setEnabled(false);
                           lblQuantPaginas.setText("0/0");
                    }
                }

                
                    atualizaTabelaBusca();
                

            } catch (SQLException ex) {
                Logger.getLogger(ConsultaView.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ""+ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        inicio = 0;
        atualizaTabelaPatrimonio(inicio);
        cbxFiltro.setSelectedIndex(0);
        tfdFiltroBusca.setText("");
        btnAnterior.setEnabled(false);
        btnProximo.setEnabled(true);
        
        try {
            validaQuantidade();
        } catch (SQLException ex) {
            Logger.getLogger(PatrimonioView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLimparActionPerformed

    private void tfdFiltroBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfdFiltroBuscaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfdFiltroBuscaActionPerformed

    private void tfdNavegacaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdNavegacaoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){  
            if(Integer.parseInt(tfdNavegacao.getText()) > pagUltima || Integer.parseInt(tfdNavegacao.getText()) < 1){
                JOptionPane.showMessageDialog(null, "Página Invalida!");
                tfdNavegacao.setText("");
            }else{
                inicio = (Integer.parseInt(tfdNavegacao.getText()) -1) * 100;
                //atualizaTabelaPatrimonio(inicio);
                switch (cont) {
                    case 0:
                        atualizaTabelaPatrimonio(inicio);
                        break;
                    case 1:
                        try {
                            listaPatrimonio = patrimonioDAO.buscaPatrimonio100(tfdNavegacao.getText(), inicio);
                        } catch (SQLException ex) {
                            Logger.getLogger(PatrimonioView.class.getName()).log(Level.SEVERE, null, ex);
                        }   atualizaTabelaBusca();
                        break;
                    case 2:
                        try {
                            listaPatrimonio = patrimonioDAO.buscaDescricao100(tfdNavegacao.getText(), inicio);
                        } catch (SQLException ex) {
                            Logger.getLogger(PatrimonioView.class.getName()).log(Level.SEVERE, null, ex);
                        }   atualizaTabelaBusca();
                        break;                
                    default:
                        break;
                }
                
                pagAtual = Integer.parseInt(tfdNavegacao.getText());
                lblQuantPaginas.setText(pagAtual+"/"+pagUltima);
                if(inicio == 0){
                    btnAnterior.setEnabled(false);
                }else{
                    btnAnterior.setEnabled(true);
                }
                if(pagAtual == pagUltima){
                    btnProximo.setEnabled(false);
                }else{
                    btnProximo.setEnabled(true);
                }
                
                
            }
        }
    }//GEN-LAST:event_tfdNavegacaoKeyPressed

    private void tfdFiltroBuscaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdFiltroBuscaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){ 
            inicio = 0;
        btnProximo.setEnabled(true);
        btnAnterior.setEnabled(false);
        
        if(tfdFiltroBusca.getText().equals("") || cbxFiltro.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Selecione um Filtro!!");
        }else{

            try {
                
                if(cbxFiltro.getSelectedItem().toString().equals("Codigo")) {
                    listaPatrimonio = null;
                    listaPatrimonio = patrimonioDAO.buscaPatrimonio100(tfdFiltroBusca.getText(), inicio);
                    validaQuantidadeBuscaCodigo();
                    cont = 1;
                    if(listaPatrimonio.size() < 1){
                           JOptionPane.showMessageDialog(null, "Código não Encontrado");
                           btnAnterior.setEnabled(false);
                           btnProximo.setEnabled(false);
                           lblQuantPaginas.setText("0/0");
                    }
                }else
                if(cbxFiltro.getSelectedItem().toString().equals("Descrição")){
                    listaPatrimonio = null;
                    listaPatrimonio = patrimonioDAO.buscaDescricao100(tfdFiltroBusca.getText(), inicio);
                    validaQuantidadeBuscaDescricao();
                    cont = 2;
                    if(listaPatrimonio.size() < 1){
                           JOptionPane.showMessageDialog(null, "Descrição não Encontrada");
                           btnAnterior.setEnabled(false);
                           btnProximo.setEnabled(false);
                           lblQuantPaginas.setText("0/0");
                    }
                }

                
                    atualizaTabelaBusca();
                

            } catch (SQLException ex) {
                Logger.getLogger(ConsultaView.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ""+ex.getMessage());
            }
        }
        }
    }//GEN-LAST:event_tfdFiltroBuscaKeyPressed

    private void tfdCodigoPatrimonioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdCodigoPatrimonioKeyPressed

        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbxBloco.requestFocusInWindow();
        }
    }//GEN-LAST:event_tfdCodigoPatrimonioKeyPressed

    private void tfdDescricaoPatrimonioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdDescricaoPatrimonioKeyPressed

        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbxPiso.requestFocusInWindow();
        }
    }//GEN-LAST:event_tfdDescricaoPatrimonioKeyPressed

    private void cbxStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxStatusActionPerformed


    }//GEN-LAST:event_cbxStatusActionPerformed

    private void tfdNotaFiscalPatrimonioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdNotaFiscalPatrimonioKeyPressed

        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbxSubtipo.requestFocusInWindow();
        }
    }//GEN-LAST:event_tfdNotaFiscalPatrimonioKeyPressed

    private void cbxSubtipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSubtipoActionPerformed

    }//GEN-LAST:event_cbxSubtipoActionPerformed

    private void btnSalvarPatrimonioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSalvarPatrimonioKeyPressed

        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tfdDescricaoPatrimonio.getText().isEmpty() || tfdCodigoPatrimonio.getText().isEmpty() || tfdNotaFiscalPatrimonio.getText().isEmpty() || cbxSala.getSelectedIndex() == 0 || cbxConservacao.getSelectedIndex() == 0 || cbxOrgao.getSelectedIndex() == 0 || cbxStatus.getSelectedIndex() == 0 || cbxSubtipo.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Prencha todos os campos.", "Erro", JOptionPane.WARNING_MESSAGE);
                tfdDescricaoPatrimonio.requestFocusInWindow();
            } else if (tfdIDPatrimonio.getText().isEmpty()) {
                //Se estiver o campo ID estiver vazio siginifica que será um novo objeto.
                patrimonio = new PatrimonioM();
                patrimonio.setDescricao(tfdDescricaoPatrimonio.getText());
                patrimonio.setCodigo(tfdCodigoPatrimonio.getText());
                patrimonio.setEntidade(pegaEntidade());
                patrimonio.setGrau_conservacao(pegaGrau(cbxConservacao.getSelectedItem().toString()));
                patrimonio.setNotaFiscal(tfdNotaFiscalPatrimonio.getText());
                patrimonio.setSala(pegaSala());
                patrimonio.setStatus(pegaStatus(cbxStatus.getSelectedItem().toString()));
                patrimonio.setSubTipo(pegaSubtipo());
                patrimonio.setKit(ckxPatrimonioComposto.isSelected());
                try {
                    //Recebe o ultimo ID gerado
                    ultimoID = patrimonioDAO.salvar(patrimonio);
                    auxPatrimonio = patrimonioDAO.busca(ultimoID);

                    idHistorico = ultimoID;
                    acao = "Novo Patrimonio";
                    descricaoHistorico = patrimonio.getDescricao();
                    salvaHistorico();
                    // a partir daqui tem que liberar a tela do composto
                    if (ckxPatrimonioComposto.isSelected()) {
                        preparaSalvareCancelar();
                        salvarMantendoInformacoes();
                        pnlPatrimonioComposto.setVisible(true);
                        tfdIDPatrimonio.setText("" + ultimoID);
                        iniciaComposto();
                        JOptionPane.showMessageDialog(null, "Cadastre os itens do kit", "Cadastro de Kit", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Patrimônio cadastrado com sucesso..", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        preparaSalvareCancelar();
                        salvarMantendoInformacoes();
                    }
                    //atualizaTabelaPatrimonio(inicio);
                    limpaCamposPatrimonio();
                    //limpa a busca
                    inicio = 0;
                    atualizaTabelaPatrimonio(inicio);
                    cbxFiltro.setSelectedIndex(0);
                    tfdFiltroBusca.setText("");
                    btnAnterior.setEnabled(false);
                    btnProximo.setEnabled(true);

                    try {
                        validaQuantidade();
                    } catch (SQLException ex) {
                        Logger.getLogger(PatrimonioView.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(OrgaoView.class.getName()).log(Level.SEVERE, null, ex);
                    if (ex.getErrorCode() == 1062) {
                        JOptionPane.showMessageDialog(null, "Patrimônio já existente.", "Erro", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } else {
                int auxID = Integer.parseInt(tfdIDPatrimonio.getText());
                //Se for uma situação de alteração ele vai pegar o ID do patrimonio direto do textfield
                try {

                    //cria o auxiliar a partir do ID
                    auxPatrimonio = patrimonioDAO.busca(auxID);
                } catch (SQLException ex) {
                    Logger.getLogger(PatrimonioView.class.getName()).log(Level.SEVERE, null, ex);
                }
                //Se o campo ID não estiver vazio trata-se de uma alteração
                patrimonio = new PatrimonioM();
                patrimonio.setId(Integer.parseInt(tfdIDPatrimonio.getText()));
                patrimonio.setDescricao(tfdDescricaoPatrimonio.getText());
                patrimonio.setCodigo(tfdCodigoPatrimonio.getText());
                patrimonio.setNotaFiscal(tfdNotaFiscalPatrimonio.getText());
                try {
                    patrimonio.setGrau_conservacao(grauDAO.buscaNome(cbxConservacao.getSelectedItem().toString()));
                } catch (SQLException ex) {
                    Logger.getLogger(PatrimonioView.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    patrimonio.setStatus(statusDAO.buscaNome(cbxStatus.getSelectedItem().toString()));
                } catch (SQLException ex) {
                    Logger.getLogger(PatrimonioView.class.getName()).log(Level.SEVERE, null, ex);
                }
                patrimonio.setKit(ckxPatrimonioComposto.isSelected());

                try {

                    idHistorico = patrimonio.getId();
                    acao = "Alterar Patrimonio";
                    descricaoHistorico = patrimonio.getDescricao();
                    salvaHistorico();

                    patrimonioDAO.alterar(patrimonio);
                    JOptionPane.showMessageDialog(null, "Patrimônio atualizado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    desativaCampos();
                    preparaSalvareCancelar();
                    limpaCamposPatrimonio();
                    if (cont == 0) {
                        atualizaTabelaPatrimonio(inicio);
                    } else if (cont == 1) {
                        listaPatrimonio = patrimonioDAO.buscaPatrimonio100(tfdFiltroBusca.getText(), inicio);
                        atualizaTabelaBusca();
                    } else if (cont == 2) {
                        listaPatrimonio = patrimonioDAO.buscaDescricao100(tfdFiltroBusca.getText(), inicio);
                        atualizaTabelaBusca();
                    }
                    //atualizaTabelaPatrimonio(inicio);

                } catch (SQLException ex) {
                    Logger.getLogger(OrgaoView.class.getName()).log(Level.SEVERE, null, ex);
                    if (ex.getErrorCode() == 1062) {
                        JOptionPane.showMessageDialog(null, "Patrimônio já existente.", "Erro", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnSalvarPatrimonioKeyPressed

    private void tfdDescricaoPatrimonioCompostoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdDescricaoPatrimonioCompostoKeyPressed

        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbxConservacaoPatrimonioComposto.requestFocusInWindow();
        }
    }//GEN-LAST:event_tfdDescricaoPatrimonioCompostoKeyPressed

    private void cbxStatusPatrimonioCompostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxStatusPatrimonioCompostoActionPerformed

      
    }//GEN-LAST:event_cbxStatusPatrimonioCompostoActionPerformed

    private void btnSalvarPatrimonioCompostoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSalvarPatrimonioCompostoKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String status, conservacao;
            if (tfdDescricaoPatrimonioComposto.getText().isEmpty() || cbxConservacaoPatrimonioComposto.getSelectedIndex() == 0 || cbxStatusPatrimonioComposto.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Prencha todos os campos.", "Erro", JOptionPane.WARNING_MESSAGE);
                tfdDescricaoPatrimonioComposto.requestFocusInWindow();
            } else if (tfdIDComposto.getText().isEmpty()) {
                status = cbxStatusPatrimonioComposto.getSelectedItem().toString();
                conservacao = cbxConservacaoPatrimonioComposto.getSelectedItem().toString();
                patComposto = new PatrimonioCompostoM();
                patComposto.setDescricao(tfdDescricaoPatrimonioComposto.getText());
                patComposto.setGrau(pegaGrau(conservacao));
                patComposto.setStatus(pegaStatus(status));
                patComposto.setPatrimonio(auxPatrimonio);
                try {
                    patrimonioCompostoDAO.salvar(patComposto);
                    JOptionPane.showMessageDialog(null, "Gravado com Sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                preparaSalvarCancelarComposto();
                desativaCamposComposto();
                atualizaTabelaCompostoExistente();
                limpaCamposComposto();

            } else {
                status = cbxStatusPatrimonioComposto.getSelectedItem().toString();
                conservacao = cbxConservacaoPatrimonioComposto.getSelectedItem().toString();

                patComposto = new PatrimonioCompostoM();
                patComposto.setId(Integer.parseInt(tfdIDComposto.getText()));
                patComposto.setDescricao(tfdDescricaoPatrimonioComposto.getText());
                patComposto.setGrau(pegaGrau(conservacao));
                patComposto.setStatus(pegaStatus(status));
                patComposto.setPatrimonio(auxPatrimonio);
                try {
                    patrimonioCompostoDAO.alterar(patComposto);
                    JOptionPane.showMessageDialog(null, "Alterado com Sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    Logger.getLogger(PatrimonioView.class.getName()).log(Level.SEVERE, null, ex);
                }
                preparaSalvarCancelarComposto();
                desativaCamposComposto();
                atualizaTabelaCompostoExistente();
                limpaCamposComposto();
            }
        }
    }//GEN-LAST:event_btnSalvarPatrimonioCompostoKeyPressed

    private void cbxUnidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxUnidadeKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tfdCodigoPatrimonio.requestFocusInWindow();
        }
    }//GEN-LAST:event_cbxUnidadeKeyPressed

    private void cbxBlocoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxBlocoKeyPressed

         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tfdDescricaoPatrimonio.requestFocusInWindow();
        }
    }//GEN-LAST:event_cbxBlocoKeyPressed

    private void cbxPisoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxPisoKeyPressed

         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbxConservacao.requestFocusInWindow();
        }
    }//GEN-LAST:event_cbxPisoKeyPressed

    private void cbxConservacaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxConservacaoKeyPressed

         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbxSala.requestFocusInWindow();
        }
    }//GEN-LAST:event_cbxConservacaoKeyPressed

    private void cbxSalaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxSalaKeyPressed

         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbxStatus.requestFocusInWindow();
        }
    }//GEN-LAST:event_cbxSalaKeyPressed

    private void cbxStatusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxStatusKeyPressed

         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbxTipo.requestFocusInWindow();
        }
    }//GEN-LAST:event_cbxStatusKeyPressed

    private void cbxTipoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxTipoKeyPressed

         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tfdNotaFiscalPatrimonio.requestFocusInWindow();
        }
    }//GEN-LAST:event_cbxTipoKeyPressed

    private void cbxSubtipoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxSubtipoKeyPressed

         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbxOrgao.requestFocusInWindow();
        }
    }//GEN-LAST:event_cbxSubtipoKeyPressed

    private void cbxOrgaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxOrgaoKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnSalvarPatrimonio.requestFocusInWindow();
        }
    }//GEN-LAST:event_cbxOrgaoKeyPressed

    private void cbxConservacaoPatrimonioCompostoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxConservacaoPatrimonioCompostoKeyPressed

         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbxStatusPatrimonioComposto.requestFocusInWindow();
        }
    }//GEN-LAST:event_cbxConservacaoPatrimonioCompostoKeyPressed

    private void cbxStatusPatrimonioCompostoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxStatusPatrimonioCompostoKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnSalvarPatrimonioComposto.requestFocusInWindow();
        }
    }//GEN-LAST:event_cbxStatusPatrimonioCompostoKeyPressed

    public OrgaoM pegaEntidade() {
        try {
            return entidadeDAO.buscaNome(cbxOrgao.getSelectedItem().toString());
        } catch (SQLException ex) {
            Logger.getLogger(BlocoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    

    public GrauConservacaoM pegaGrau(String grau) {
        try {
            return grauDAO.buscaNome(grau);
        } catch (SQLException ex) {
            Logger.getLogger(BlocoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public SalaM pegaSala() {
        try {
            return salaDAO.buscaNome(cbxSala.getSelectedItem().toString());
        } catch (SQLException ex) {
            Logger.getLogger(BlocoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public SubTipoM pegaSubtipo() {
        try {
            return subtipoDAO.buscaNome(cbxSubtipo.getSelectedItem().toString());
        } catch (SQLException ex) {
            Logger.getLogger(BlocoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int pegaIDStatus(String status) {
        try {
            StatusM aux = new StatusM();
            aux = statusDAO.buscaNome(status);
            
            return aux.getId();
        } catch (SQLException ex) {
            Logger.getLogger(BlocoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }
    
    public StatusM pegaStatus(String status) {
        try {
            return statusDAO.buscaNome(status);
        } catch (SQLException ex) {
            Logger.getLogger(BlocoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    // INÍCIO MÉTODOS DE CONTROLE DE BOTÕES 
    
    public void salvarMantendoInformacoes(){
        cbxUnidade.setEnabled(false);
        cbxBloco.setEnabled(false);
        cbxPiso.setEnabled(false);
        cbxSala.setEnabled(false);
        cbxTipo.setEnabled(false);
        cbxSubtipo.setEnabled(false);
        tfdCodigoPatrimonio.setEnabled(false);
        tfdDescricaoPatrimonio.setEnabled(false);
        cbxConservacao.setEnabled(false);
        cbxStatus.setEnabled(false);
        tfdNotaFiscalPatrimonio.setEnabled(false);        
        cbxOrgao.setEnabled(false);
        ckxPatrimonioComposto.setEnabled(false);                      
}
    public void limpaCamposPatrimonio() {
        
        cbxUnidade.setSelectedIndex(0);
        cbxTipo.setSelectedIndex(0);
        cbxConservacao.setSelectedIndex(0);
        cbxStatus.setSelectedIndex(0);
        cbxOrgao.setSelectedIndex(0);
        tfdIDPatrimonio.setText("");
        tfdCodigoPatrimonio.setText("");
        tfdDescricaoPatrimonio.setText("");
        tfdNotaFiscalPatrimonio.setText("");
        btnNovoPatrimonio.setEnabled(true);
        ckxPatrimonioComposto.setSelected(false);
        
        
    }
    
    public void preparaNovo() {
        pnlPatrimonioComposto.setVisible(false);
        btnNovoPatrimonio.setEnabled(false);
        btnCancelarPatrimonio.setEnabled(true);
        btnSalvarPatrimonio.setEnabled(true);
        btnAlterarPatrimonio.setEnabled(false);
        btnExcluirPatrimonio.setEnabled(false);
        tbePatrimonio.setEnabled(false);
        tbePatrimonio.clearSelection();
        
    }
    
    public void preparaSalvareCancelar() {
        btnNovoPatrimonio.setEnabled(true);
        btnCancelarPatrimonio.setEnabled(false);
        btnSalvarPatrimonio.setEnabled(false);
        tbePatrimonio.setEnabled(true);
        pnlPatrimonioComposto.setVisible(false);
    }
    
    public void ativaCamposNovo() {
        tfdCodigoPatrimonio.setEnabled(true);
        tfdDescricaoPatrimonio.setEnabled(true);
        tfdNotaFiscalPatrimonio.setEnabled(true);
       
        cbxConservacao.setEnabled(true);     
        cbxStatus.setEnabled(true);
        cbxConservacao.setEnabled(true);
        cbxOrgao.setEnabled(true);
   
        cbxUnidade.setEnabled(true);
        cbxBloco.setEnabled(true);
        cbxPiso.setEnabled(true);
        cbxSala.setEnabled(true);
        cbxTipo.setEnabled(true);
        cbxSubtipo.setEnabled(true);
        ckxPatrimonioComposto.setEnabled(true);
     
        
    }
    
    public void ativaCamposAlterar(){
        tfdCodigoPatrimonio.setEnabled(true);
        tfdDescricaoPatrimonio.setEnabled(true);
        tfdNotaFiscalPatrimonio.setEnabled(true);
       
        cbxConservacao.setEnabled(true);     
        cbxStatus.setEnabled(true);
        cbxStatus.setEnabled(true);
        cbxConservacao.setEnabled(true);
        cbxOrgao.setEnabled(true);
        //ckxPatrimonioComposto.setEnabled(true);
        
    }
    
    public void desativaCampos(){
              
        cbxConservacao.setSelectedIndex(0);
        cbxConservacao.setEnabled(false);
        cbxStatus.setSelectedIndex(0);
        cbxStatus.setEnabled(false);
        cbxOrgao.setSelectedIndex(0);
        cbxOrgao.setEnabled(false);
        cbxUnidade.setEnabled(false);
        cbxUnidade.setSelectedIndex(0);
        cbxBloco.setEnabled(false);
        cbxBloco.setSelectedIndex(0);
        cbxPiso.setEnabled(false);
        cbxPiso.setSelectedIndex(0);
        cbxSala.setEnabled(false);
        cbxSala.setSelectedIndex(0);
        cbxTipo.setEnabled(false);
        cbxTipo.setSelectedIndex(0);
        cbxSubtipo.setEnabled(false);
        cbxSubtipo.setSelectedIndex(0);
       
        //tfdIDPatrimonio.setText("");
        tfdIDPatrimonio.setEnabled(false);
        //tfdCodigoPatrimonio.setText("");
        tfdCodigoPatrimonio.setEnabled(false);
        //tfdDescricaoPatrimonio.setText("");
        tfdDescricaoPatrimonio.setEnabled(false);
        //tfdNotaFiscalPatrimonio.setText("");
        tfdNotaFiscalPatrimonio.setEnabled(false);
        
        ckxPatrimonioComposto.setEnabled(false);
 
        btnCancelarPatrimonio.setEnabled(false);
        btnSalvarPatrimonio.setEnabled(false);
        btnAlterarPatrimonio.setEnabled(false);
        btnExcluirPatrimonio.setEnabled(false);
    } 
    
    public void preparaAlterar() {
        btnNovoPatrimonio.setEnabled(false);
        btnExcluirPatrimonio.setEnabled(false);
        btnAlterarPatrimonio.setEnabled(false);
        btnCancelarPatrimonio.setEnabled(true);
        btnSalvarPatrimonio.setEnabled(true);
        
        cbxConservacao.setEnabled(false);
        cbxStatus.setEnabled(false);
        cbxOrgao.setEnabled(false);
        tbePatrimonio.setEnabled(false);        
        tbePatrimonio.clearSelection();
        tfdDescricaoPatrimonio.requestFocusInWindow();
    
    }
    
    public void preparaExcluir() {
        btnExcluirPatrimonio.setEnabled(false);
        btnAlterarPatrimonio.setEnabled(false);
        
    }
     
    public void preparaSelecaoTabelaPatrimonio(){
       
        btnNovoPatrimonio.setEnabled(true);
        btnExcluirPatrimonio.setEnabled(true);
        btnAlterarPatrimonio.setEnabled(true);       
 
    }
         
    // FIM MÉTODOS DE CONTROLE DE BOTÕES 
    
    
    
    // INICIO CONTROLE BOOTES COMPOSTO
    public void limpaCamposComposto(){
        tfdDescricaoPatrimonioComposto.setText("");
        cbxConservacaoPatrimonioComposto.setSelectedIndex(0);
        cbxStatusPatrimonioComposto.setSelectedIndex(0);                
    }
    
    public void ativaCamposComposto(){
        tfdDescricaoPatrimonioComposto.setEnabled(true);
        cbxConservacaoPatrimonioComposto.setEnabled(true);
        cbxStatusPatrimonioComposto.setEnabled(true);   
    }
    
    public void preparaNovoComposto(){
        btnNovoPatrimonioComposto.setEnabled(false);
        btnSalvarPatrimonioComposto.setEnabled(true);
        btnCancelarPatrimonioComposto.setEnabled(true);
        btnAlterarPatrimonioComposto.setEnabled(false);
        btnExcluirPatrimonioComposto.setEnabled(false);
        
        
        //tfdDescricaoPatrimonioComposto.setEnabled(true);
        cbxConservacaoPatrimonioComposto.setEnabled(true);
        cbxStatusPatrimonioComposto.setEnabled(true);
        tbePatrimonioComposto.setEnabled(false);
        tbePatrimonioComposto.clearSelection();
    }
    
    public void preparaSalvarCancelarComposto(){
        btnNovoPatrimonioComposto.setEnabled(true);
        btnSalvarPatrimonioComposto.setEnabled(false);
        btnCancelarPatrimonioComposto.setEnabled(false);
        
        
        cbxConservacaoPatrimonioComposto.setEnabled(false);
        cbxStatusPatrimonioComposto.setEnabled(false);
        tbePatrimonioComposto.setEnabled(true);
    }
    
    public void desativaCamposComposto(){
       tfdIDComposto.setText("");
       tfdIDComposto.setEnabled(false);
       tfdDescricaoPatrimonioComposto.setEnabled(false);
       cbxConservacaoPatrimonioComposto.setSelectedIndex(0);
       cbxConservacaoPatrimonioComposto.setEnabled(false);
       cbxStatusPatrimonioComposto.setSelectedIndex(0);
       cbxStatusPatrimonioComposto.setEnabled(false);
      
       btnSalvarPatrimonioComposto.setEnabled(false);
       btnCancelarPatrimonioComposto.setEnabled(false);
       btnAlterarPatrimonioComposto.setEnabled(false);
       btnExcluirPatrimonioComposto.setEnabled(false);
    }
    
    public void preparaAlterarComposto(){
        btnNovoPatrimonioComposto.setEnabled(false);
        btnExcluirPatrimonioComposto.setEnabled(false);
        btnAlterarPatrimonioComposto.setEnabled(false);
        btnSalvarPatrimonioComposto.setEnabled(true);
        btnCancelarPatrimonioComposto.setEnabled(true);
        tbePatrimonioComposto.setEnabled(false);
        tbePatrimonioComposto.clearSelection();
    }
    
    public void preparaSelecaoComposto(){
        btnNovoPatrimonioComposto.setEnabled(true);
        btnExcluirPatrimonioComposto.setEnabled(true);
        btnAlterarPatrimonioComposto.setEnabled(true);
    }
    
    public void ativaComposto(){
        tbePatrimonioComposto.setEnabled(true);
        btnNovoPatrimonioComposto.setEnabled(true);
    }
    
    public PatrimonioCompostoM pegaComposto(String descricao){
        try {
            return patrimonioCompostoDAO.buscaDescricao(descricao);
        } catch (SQLException ex) {
            Logger.getLogger(BlocoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public void atualizaGrauComposto(){
        cbxConservacaoPatrimonioComposto.removeAllItems();
        cbxConservacaoPatrimonioComposto.addItem("--Selecione--");
        try {
            listaGrau = grauDAO.listaTodos();
        } catch (SQLException ex) {
            Logger.getLogger(OrgaoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[listaGrau.size()][5];
        int i = 0;
        for (GrauConservacaoM gra : listaGrau) {
            cbxConservacaoPatrimonioComposto.addItem(gra.getDescricao());
        }
    }
    
    public void atualizaStatusComposto(){
        cbxStatusPatrimonioComposto.removeAllItems();
        cbxStatusPatrimonioComposto.addItem("--Selecione--");
        try {
            listaStatus = statusDAO.listaTodos();
        } catch (SQLException ex) {
            Logger.getLogger(OrgaoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[listaStatus.size()][5];
        int i = 0;
        for (StatusM stat : listaStatus) {
            cbxStatusPatrimonioComposto.addItem(stat.getNome());
        }
        
    }
        
    public void atualizaTabelaCompostoExistente(){
        try {
            //compoe a lista de patrimonio composto a partir do ID do patrimonio que está sendo salvo.
            listaComposto = PatrimonioCompostoDAO.listaTodosExistentes(auxPatrimonio);
        } catch (SQLException ex) {
            Logger.getLogger(PatrimonioView.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[listaComposto.size()][4];
        int i = 0;
        for (PatrimonioCompostoM patComposto : listaComposto){
            dados[i][0] = String.valueOf(patComposto.getId());
            dados[i][1] = patComposto.getDescricao();
            dados[i][2] = patComposto.getGrau().getDescricao();
            dados[i][3] = patComposto.getStatus().getNome();
            i++;
        }
        String tituloColuna[] = {"ID","Descrição", "Grau de Conservação", "Status"};
        DefaultTableModel tabelaComposto = new DefaultTableModel();
        tabelaComposto.setDataVector(dados, tituloColuna);
        tbePatrimonioComposto.setModel(new DefaultTableModel(dados, tituloColuna){
            boolean[] canEdit = new boolean[]{ false, false, false 
            };
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return canEdit[columnIndex];
            }
        });
        
        tbePatrimonioComposto.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbePatrimonioComposto.getColumnModel().getColumn(1).setPreferredWidth(300);
        tbePatrimonioComposto.getColumnModel().getColumn(2).setPreferredWidth(300);
        tbePatrimonioComposto.getColumnModel().getColumn(3).setPreferredWidth(300);
        
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tbePatrimonioComposto.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        tbePatrimonioComposto.setRowHeight(25);
        tbePatrimonioComposto.updateUI();
    }
    public void validaQuantidade() throws SQLException{
        this.quantMax = patrimonioDAO.quantidade();
        if (quantMax < 100){
            lblQuantPaginas.setText(1 + "/" + 1);
             btnProximo.setEnabled(false);
              btnAnterior.setEnabled(false);
        }else{
        pagAtual = 1;
        
        if(quantMax % 100 == 0){
             pagUltima = quantMax / 100;
        }else if(quantMax <= 100){
            pagUltima = 1;
            btnProximo.setEnabled(false);
        }else{
             pagUltima = (quantMax / 100) + 1;
        }
        
        lblQuantPaginas.setText(pagAtual + "/" + pagUltima);
        }
    }
    public void validaQuantidadeBuscaDescricao() throws SQLException{
        this.quantMax = patrimonioDAO.quantidadeDescricao(tfdFiltroBusca.getText());
        
        pagAtual = 1;
        
        if(quantMax % 100 == 0){
             pagUltima = quantMax / 100;
        }else if(quantMax <= 100){
            pagUltima = 1;
            btnProximo.setEnabled(false);
        }else{
             pagUltima = (quantMax / 100) + 1;
        }
        
        lblQuantPaginas.setText(pagAtual + "/" + pagUltima);
        
    }
     public void validaQuantidadeBuscaCodigo() throws SQLException{
        this.quantMax = patrimonioDAO.quantidadeCodigo(tfdFiltroBusca.getText());
        
        pagAtual = 1;
        
        if(quantMax % 100 == 0){
             pagUltima = quantMax / 100;
        }else if(quantMax <= 100){
            pagUltima = 1;
            btnProximo.setEnabled(false);
        }else{
             pagUltima = (quantMax / 100) + 1;
        }
        
        lblQuantPaginas.setText(pagAtual + "/" + pagUltima);
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterarPatrimonio;
    private javax.swing.JButton btnAlterarPatrimonioComposto;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelarPatrimonio;
    private javax.swing.JButton btnCancelarPatrimonioComposto;
    private javax.swing.JButton btnExcluirPatrimonio;
    private javax.swing.JButton btnExcluirPatrimonioComposto;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnNovoPatrimonio;
    private javax.swing.JButton btnNovoPatrimonioComposto;
    private javax.swing.JButton btnProximo;
    private javax.swing.JButton btnSalvarPatrimonio;
    private javax.swing.JButton btnSalvarPatrimonioComposto;
    private javax.swing.JComboBox<String> cbxBloco;
    private javax.swing.JComboBox<String> cbxConservacao;
    private javax.swing.JComboBox<String> cbxConservacaoPatrimonioComposto;
    private javax.swing.JComboBox<String> cbxFiltro;
    private javax.swing.JComboBox<String> cbxOrgao;
    private javax.swing.JComboBox<String> cbxPiso;
    private javax.swing.JComboBox<String> cbxSala;
    private javax.swing.JComboBox<String> cbxStatus;
    private javax.swing.JComboBox<String> cbxStatusPatrimonioComposto;
    private javax.swing.JComboBox<String> cbxSubtipo;
    private javax.swing.JComboBox<String> cbxTipo;
    private javax.swing.JComboBox<String> cbxUnidade;
    private javax.swing.JCheckBox ckxPatrimonioComposto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblDescricao1;
    private javax.swing.JLabel lblGrauConservacao;
    private javax.swing.JLabel lblGrauConservacao1;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblNotaFiscal;
    private javax.swing.JLabel lblQuantPaginas;
    private javax.swing.JLabel lblSelecBloco;
    private javax.swing.JLabel lblSelecPiso;
    private javax.swing.JLabel lblSelecSala;
    private javax.swing.JLabel lblSelecSubtipo;
    private javax.swing.JLabel lblSelecTipo;
    private javax.swing.JLabel lblSelecUnidade;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblStatus1;
    private javax.swing.JPanel pnlPatrimonioComposto;
    private javax.swing.JTable tbePatrimonio;
    private javax.swing.JTable tbePatrimonioComposto;
    private javax.swing.JTextField tfdCodigoPatrimonio;
    private javax.swing.JTextField tfdDescricaoPatrimonio;
    private javax.swing.JTextField tfdDescricaoPatrimonioComposto;
    private javax.swing.JTextField tfdFiltroBusca;
    private javax.swing.JTextField tfdIDComposto;
    private javax.swing.JTextField tfdIDPatrimonio;
    private javax.swing.JTextField tfdNavegacao;
    private javax.swing.JTextField tfdNotaFiscalPatrimonio;
    // End of variables declaration//GEN-END:variables
}
