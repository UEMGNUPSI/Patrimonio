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
import model.EntidadeM;

public class EntidadeDAO {
     PreparedStatement pst;
    String sql;
    
    public void salvar(EntidadeM entidade) throws SQLException{
        sql = "insert into Entidade values(?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, 0);
        pst.setString(2, entidade.getNome());
        pst.setString(3, entidade.getCnpj());
        pst.setString(4, entidade.getContato());
        pst.execute();
        pst.close();
    }
    
     public EntidadeM busca(int id) throws SQLException{
        sql = "select * from Entidade where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        EntidadeM entidade = null;
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
           entidade = new EntidadeM(rs.getInt("id"),rs.getString("nome"),rs.getString("cnpj"),rs.getString("contato"));
        }
        pst.close();
        return entidade;
    }
     
     public List<EntidadeM> listaTodos() throws SQLException{
        List<EntidadeM> listaEnti = new ArrayList<EntidadeM>();
        sql = "select * from Entidade order by nome";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
           listaEnti.add(new EntidadeM(rs.getInt("id"),rs.getString("nome"),rs.getString("cnpj"),rs.getString("contato")));
        }
        pst.close();
        return listaEnti;
    }
     
      public void excluir(EntidadeM entidade) throws SQLException{
        sql = "delete from Entidade where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, entidade.getId());
        pst.execute();
        pst.close();
    }
      
     public void alterar(EntidadeM entidade) throws SQLException{
         sql = "update Entidade set nome = ?, cnpj = ?, contato = ? where id = ?";
         pst = Conexao.getInstance().prepareStatement(sql);
         pst.setString(1, entidade.getNome());
         pst.setString(2, entidade.getCnpj());
         pst.setString(3, entidade.getContato());
         pst.setInt(4, entidade.getId());
         pst.execute();
         pst.close();
     }
     
     public EntidadeM buscaNome(String nome) throws SQLException{
           sql = "select * from Entidade where nome = ?";
           pst = Conexao.getInstance().prepareStatement(sql);
           pst.setString(1, nome);
           EntidadeM entidade = null;
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               entidade = new EntidadeM(rs.getInt("id"),rs.getString("nome"),rs.getString("cnpj"),rs.getString("contato"));
            }
            pst.close();
            return entidade;
     }
}
