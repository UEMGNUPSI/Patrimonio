/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.PatrimonioCompostoM;
import model.PatrimonioM;

/**
 *
 * @author Marlon
 */
public class PatrimonioCompostoDAO {
    PreparedStatement pst;
    String sql;
    
    //lista os itens de um patrimonio especificado 
    //usado para alterar o KIT
    public List<PatrimonioCompostoM> listaComposto(PatrimonioM pat) throws SQLException{
        List<PatrimonioCompostoM> listaComposto = new ArrayList<PatrimonioCompostoM>();
        sql = "select * from Patrimonio_composto where id_patrimonio = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, pat.getId());
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
           listaComposto.add(new PatrimonioCompostoM(rs.getInt("id"), 
           rs.getString("descricao"),
           pat.getGrau_conservacao().getId(),
           pat.getStatus().getId(),
           pat.getId()));
        }
        pst.close();
        return listaComposto;
    }
    
    public void salvar(PatrimonioCompostoM patrimonioComposto) throws SQLException{
        sql = "insert into Patrimonio_composto values(?, ?, ?, ?, ?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, 0);
        pst.setString(2, patrimonioComposto.getDescricao());
        pst.setInt(3, patrimonioComposto.getId_grau_conservacao());
        pst.setInt(4, patrimonioComposto.getId_status());
        pst.setInt(5, patrimonioComposto.getId_patrimonio());
        pst.execute();
        pst.close();
    }
    
    public void alterar(PatrimonioCompostoM patrimonioComposto) throws SQLException{
         sql = "update Patrimonio_composto set descricao = ?, id_grau_conservacao = ?, id_status = ? where id = ?";
         pst = Conexao.getInstance().prepareStatement(sql);
         pst.setString(1, patrimonioComposto.getDescricao());
         pst.setInt(2, patrimonioComposto.getId_grau_conservacao());
         pst.setInt(3, patrimonioComposto.getId_status());
         pst.setInt(4, patrimonioComposto.getId());
         pst.execute();
         pst.close();
     }
    
    public void excluir(PatrimonioCompostoM patrimonioComposto) throws SQLException{
        sql = "delete from Patrimonio_composto where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, patrimonioComposto.getId());
        pst.execute();
        pst.close();
    }
    
    public PatrimonioCompostoM buscaDescricao(String descricao) throws SQLException{
        sql = "select * from Patrimonio_composto where descricao = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, descricao);
        PatrimonioCompostoM patrimonioComposto = null;
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            patrimonioComposto = new PatrimonioCompostoM(rs.getInt("id"), rs.getString("descricao"), rs.getInt("id_grau_conservacao"), rs.getInt("id_status"), rs.getInt("id_patrimonio") );
        }
        pst.close();
        return patrimonioComposto;
     }
    
    
    
}
