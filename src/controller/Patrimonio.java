/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BlocoDAO;
import dao.EntidadeDAO;
import dao.GrauConservacaoDAO;
import dao.PatrimonioDAO;
import dao.PisoDAO;
import dao.SalaDAO;
import dao.StatusDAO;
import dao.SubTipoDAO;
import dao.TipoDAO;
import dao.UnidadeDAO;
import java.sql.SQLException;
import model.BlocoM;
import model.EntidadeM;
import model.GrauConservacaoM;
import model.PatrimonioM;
import model.PisoM;
import model.SalaM;
import model.StatusM;
import model.SubTipoM;
import model.TipoM;
import model.UnidadeM;
import view.PrincipalView;

/**
 *
 * @author Leopoldo
 */
public class Patrimonio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        UnidadeDAO unidade = new UnidadeDAO();
        BlocoDAO bloco = new BlocoDAO();
        PisoDAO piso = new PisoDAO();
        SalaDAO sala = new SalaDAO();
        StatusDAO status = new StatusDAO();
        TipoDAO tipo = new TipoDAO();
        SubTipoDAO subtipo= new SubTipoDAO();
        EntidadeDAO entidade = new EntidadeDAO();
        PatrimonioDAO patrimonio = new PatrimonioDAO();
        GrauConservacaoDAO grau = new GrauConservacaoDAO();
        
       /*unidade.salvar(new UnidadeM(0,"alvorada","123456","asd","alvorada@email"));
        bloco.salvar(new BlocoM(0,"Bloco C UEMG ",unidade.busca(1)));
       piso.salvar(new PisoM(0,"Piso 2",bloco.busca(1)));
         sala.salvar(new SalaM(0,"Sala 1",piso.busca(1)));
     status.salvar(new StatusM(0,"Usando"));
     tipo.salvar(new TipoM(0,"Mobilia"));
     subtipo.salvar(new SubTipoM(0,"Cadeira",tipo.busca(1)));
    entidade.salvar(new EntidadeM(0,"entidade","123456","contato.entidade"));
    grau.salvar(new GrauConservacaoM(0,"Conservado"));
     patrimonio.salvar(new PatrimonioM(0,"codigo123","descricao",subtipo.busca(1), grau.busca(1), status.busca(1) ,sala.busca(1),"notaFiscal", entidade.busca(1)));
       */
       
       new Patrimonio().executar();
    }
    
    public void executar(){
        PrincipalView principal = new PrincipalView();
    }
    
    
    
}
