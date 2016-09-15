/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author nupsi-02
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.PisoM;


public class PisoDAO {
    
    PreparedStatement pst;
    String sql;
    
    public void salvar(PisoM piso) throws SQLException{
        sql = "insert into Piso values(?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, 0);
        pst.setString(2, piso.getDescricao());
        pst.setInt(3, piso.getBloco().getId());
        pst.execute();
        pst.close();
    }
    
     public PisoM busca(int id) throws SQLException{
        sql = "select * from Piso where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        PisoM piso = null;
        BlocoDAO bloco = new BlocoDAO();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
           piso = new PisoM(rs.getInt("id"),rs.getString("nome"), bloco.busca(rs.getInt("id_bloco")));
        }
        pst.close();
        return piso;
    }
      public PisoM busca_id_bloco(int id_bloco, String nome_piso) throws SQLException{
        sql = "select * from Piso where id_bloco = ? and nome = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id_bloco);
        pst.setString(2, nome_piso);
        PisoM piso = null;
        BlocoDAO bloco = new BlocoDAO();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
           piso = new PisoM(rs.getInt("id"),rs.getString("nome"), bloco.busca(rs.getInt("id_bloco")));
        }
        pst.close();
        return piso;
    }
 
      public List<PisoM> listaTodos() throws SQLException{
        List<PisoM> listaPiso = new ArrayList<PisoM>();
        sql = "select * from Piso order by id_bloco";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        BlocoDAO bloco = new BlocoDAO();       
        while(rs.next()){
           listaPiso.add(new PisoM(rs.getInt("id"),rs.getString("nome"), bloco.busca(rs.getInt("id_bloco"))));
        }
        pst.close();
        return listaPiso;
    }
      
       public void excluir(PisoM piso) throws SQLException{
        sql = "delete from Piso where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, piso.getId());
        pst.execute();
        pst.close();
    }
    public void alterar(PisoM piso) throws SQLException{
         sql = "update Piso set nome = ? where id = ?" ;
         pst = Conexao.getInstance().prepareStatement(sql);
         pst.setString(1, piso.getDescricao());
         pst.setInt(2, piso.getId());
         pst.execute();
         pst.close();
     }
    
    public List<PisoM> buscaBloc(int id) throws SQLException{
         List<PisoM> listaPiso = new ArrayList<>();
         sql = "select * from Piso where id_bloco = ?";
         pst = Conexao.getInstance().prepareStatement(sql);
         pst.setInt(1, id);
         ResultSet rs = pst.executeQuery();
         BlocoDAO bloco = new BlocoDAO();
         while(rs.next()){
            listaPiso.add(new PisoM(rs.getInt("id"),rs.getString("nome"), bloco.busca(rs.getInt("id_bloco"))));
          }
         pst.close();
         return listaPiso;
     }
    
    public PisoM buscaNome(String nome) throws SQLException{
           sql = "select * from Piso where nome = ?";
           pst = Conexao.getInstance().prepareStatement(sql);
           pst.setString(1, nome);
           PisoM piso = null;
           BlocoDAO bloco = new BlocoDAO();
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               piso = new PisoM(rs.getInt("id"),rs.getString("nome"), bloco.busca(rs.getInt("id_bloco")));
            }
            pst.close();
            return piso;
     }
}
