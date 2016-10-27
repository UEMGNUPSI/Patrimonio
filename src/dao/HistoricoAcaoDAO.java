/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.HistoricoAcaoM;

/**
 *
 * @author NUPSI-03
 */
public class HistoricoAcaoDAO {
    PreparedStatement pst;
    String sql;
    
    public void salvar(HistoricoAcaoM historico) throws SQLException{
        sql = "insert into HistoricoAcoes values(?,?,?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, 0);
        pst.setInt(2, historico.getIdObjeto());
        pst.setString(3, historico.getTipoObjeto());
        pst.setString(4, historico.getAcao());
        pst.setString(5, historico.getDataAcao());
        pst.setString(6, historico.getUsario().getNome());
        pst.execute();
        pst.close();
    }  
    
    public void busca(String dataAcao, String tipoObjeto, String Usuario) throws SQLException{
        sql = "select * from HistoricoAcoes where ";
        
        //concatenar as strings de busca
        if(!dataAcao.equals("")){
            sql = sql.concat();
        }
        if(){
            sql = sql.concat();
        }
        if(){
            sql = sql.concat();
        }
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, 0);
        pst.setInt(2, historico.getIdObjeto());
        pst.setString(3, historico.getTipoObjeto());
        pst.setString(4, historico.getAcao());
        pst.setString(5, historico.getDataAcao());
        pst.setString(6, historico.getUsario().getNome());
        pst.execute();
        pst.close();
    }  
    
    
    
    
}
