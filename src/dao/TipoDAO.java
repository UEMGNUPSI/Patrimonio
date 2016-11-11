/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author nupsi-01
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.GrauConservacaoM;
import model.TipoM;


public class TipoDAO {
    PreparedStatement pst;
    String sql;
    
    public int salvar (TipoM tipoM) throws SQLException{
        int auxID = 0;
        sql = "insert into Tipo values(?,?)";
        pst = Conexao.getInstance().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setInt(1, 0);
        pst.setString(2, tipoM.getDescricao());
        pst.execute();
        
        ResultSet rs = pst.getGeneratedKeys();
        while(rs.next()){
           auxID = rs.getInt(1);
        }
        pst.close();
        
        return auxID;
    }
    
     public TipoM busca(int id) throws SQLException{
        sql = "select * from Tipo where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        TipoM tipo = null;
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
           tipo = new TipoM(rs.getInt("id"),rs.getString("descricao"));
        }
        pst.close();
        return tipo;
    }
     
      public List<TipoM> listaTodos() throws SQLException{
        List<TipoM> listaTipo = new ArrayList<TipoM>();
        sql = "select * from Tipo order by descricao";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
           listaTipo.add(new TipoM(rs.getInt("id"),rs.getString("descricao")));
        }
        pst.close();
        return listaTipo;
    }
      
        public void excluir(TipoM tipo) throws SQLException{
        sql = "delete from Tipo where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, tipo.getId());
        pst.execute();
        pst.close();
    }
        public void alterar(TipoM tipoM) throws SQLException{
        sql = "update Tipo set descricao = ? where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, tipoM.getDescricao());
        pst.setInt(2,tipoM.getId());
        pst.execute();
        pst.close();
    }
        
         public TipoM buscaNome(String nome) throws SQLException{
           sql = "select * from Tipo where descricao = ?";
           pst = Conexao.getInstance().prepareStatement(sql);
           pst.setString(1, nome);
           TipoM tipo = new TipoM();
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               tipo = new TipoM(rs.getInt("id"),rs.getString("descricao"));
            }
            pst.close();
            return tipo;
     }
}
