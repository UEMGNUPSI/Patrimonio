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
import model.StatusM;

public class StatusDAO {
     PreparedStatement pst;
    String sql;
    
    public void salvar(StatusM status) throws SQLException{
        sql = "insert into Status values(?,?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, 0);
        pst.setString(2, status.getNome());
        pst.execute();
        pst.close();
    }
     
    public StatusM busca(int id) throws SQLException{
        sql = "select * from Status where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        StatusM status = null;
        while(rs.next()){
           status = new StatusM(rs.getInt("id"),rs.getString("nome"));
        }
        pst.close();
        return status;
    }
    
    public List<StatusM> listaTodos() throws SQLException{
        List<StatusM> listaStatus = new ArrayList<StatusM>();
        sql = "select * from Status order by nome";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
           listaStatus.add(new StatusM(rs.getInt("id"),rs.getString("nome")));
        }
        pst.close();
        return listaStatus;
    }
    
    public void excluir(StatusM status) throws SQLException{
        sql = "delete from Status where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, status.getId());
        pst.execute();
        pst.close();
    }
     public void alterar(StatusM status) throws SQLException{
        sql = "update Status set nome = ? where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, status.getNome());
        pst.setInt(2,status.getId());
        pst.execute();
        pst.close();
    }
     
     public StatusM buscaNome(String nome) throws SQLException{
           sql = "select * from Status where nome = ?";
           pst = Conexao.getInstance().prepareStatement(sql);
           pst.setString(1, nome);
           StatusM status = null;
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               status = new StatusM(rs.getInt("id"),rs.getString("nome"));
            }
            pst.close();
            return status;
     }
}
