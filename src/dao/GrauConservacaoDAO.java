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
import model.GrauConservacaoM;

/**
 *
 * @author nupsi-02
 */
public class GrauConservacaoDAO {
    PreparedStatement pst;
    String sql;
    
    public int salvar (GrauConservacaoM grauM) throws SQLException{
        
        int auxID = 0;
        
        sql = "insert into Grau_conservacao values(?,?)";
        pst = Conexao.getInstance().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setInt(1, 0);
        pst.setString(2, grauM.getDescricao());
        pst.execute();
        
        ResultSet rs = pst.getGeneratedKeys();
        while(rs.next()){
           auxID = rs.getInt(1);
        }
        pst.close();
        
        return auxID;
    }
    
     public GrauConservacaoM busca(int id) throws SQLException{
        sql = "select * from Grau_conservacao where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        GrauConservacaoM grau = null;
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
           grau = new GrauConservacaoM(rs.getInt("id"),rs.getString("descricao"));
        }
        pst.close();
        return grau;
    }
     
     public List<GrauConservacaoM> listaTodos() throws SQLException{
        List<GrauConservacaoM> listaGrau = new ArrayList<GrauConservacaoM>();
        sql = "select * from Grau_conservacao order by descricao";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
           listaGrau.add(new GrauConservacaoM(rs.getInt("id"),rs.getString("descricao")));
        }
        pst.close();
        return listaGrau;
    }
     
      public void excluir(GrauConservacaoM grau) throws SQLException{
        sql = "delete from Grau_conservacao where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, grau.getId());
        pst.execute();
        pst.close();
    }
      
      public void alterar(GrauConservacaoM grau) throws SQLException{
        sql = "update Grau_conservacao set descricao = ? where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, grau.getDescricao());
        pst.setInt(2,grau.getId());
        pst.execute();
        pst.close();
    }
      
      public GrauConservacaoM buscaNome(String nome) throws SQLException{
           sql = "select * from Grau_conservacao where descricao = ?";
           pst = Conexao.getInstance().prepareStatement(sql);
           pst.setString(1, nome);
           GrauConservacaoM grau = null;
           PisoDAO piso = new PisoDAO();
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               grau = new GrauConservacaoM(rs.getInt("id"),rs.getString("descricao"));
            }
            pst.close();
            return grau;
     }
}
