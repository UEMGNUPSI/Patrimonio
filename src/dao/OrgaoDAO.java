package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.OrgaoM;


public class OrgaoDAO {
    PreparedStatement pst;
    String sql;
    
    public int salvar(OrgaoM entidade) throws SQLException{
        int auxID = 0;
        sql = "insert into Entidade values(?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setInt(1, 0);
        pst.setString(2, entidade.getNome());
        pst.setString(3, entidade.getCnpj());
        pst.setString(4, entidade.getContato());
        pst.execute();
        
        ResultSet rs = pst.getGeneratedKeys();
        while(rs.next()){
           auxID = rs.getInt(1);
        }
        pst.close();
        return auxID;
    }
    
     public OrgaoM busca(int id) throws SQLException{
        sql = "select * from Entidade where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        OrgaoM entidade = null;
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
           entidade = new OrgaoM(rs.getInt("id"),rs.getString("nome"),rs.getString("cnpj"),rs.getString("contato"));
        }
        pst.close();
        return entidade;
    }
     
     public List<OrgaoM> listaTodos() throws SQLException{
        List<OrgaoM> listaEnti = new ArrayList<OrgaoM>();
       sql = "select * from Entidade order by nome";
       pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
           listaEnti.add(new OrgaoM(rs.getInt("id"),rs.getString("nome"),rs.getString("cnpj"),rs.getString("contato")));
        }
        pst.close();
        return listaEnti;
    }
     
      public void excluir(OrgaoM entidade) throws SQLException{
        sql = "delete from Entidade where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, entidade.getId());
        pst.execute();
        pst.close();
    }
      
     public void alterar(OrgaoM entidade) throws SQLException{
         sql = "update Entidade set nome = ?, cnpj = ?, contato = ? where id = ?";
         pst = Conexao.getInstance().prepareStatement(sql);
         pst.setString(1, entidade.getNome());
         pst.setString(2, entidade.getCnpj());
         pst.setString(3, entidade.getContato());
         pst.setInt(4, entidade.getId());
         pst.execute();
         pst.close();
     }
     
     public OrgaoM buscaNome(String nome) throws SQLException{
           sql = "select * from Entidade where nome = ?";
           pst = Conexao.getInstance().prepareStatement(sql);
           pst.setString(1, nome);
           OrgaoM entidade = null;
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               entidade = new OrgaoM(rs.getInt("id"),rs.getString("nome"),rs.getString("cnpj"),rs.getString("contato"));
            }
            pst.close();
            return entidade;
     }
}
