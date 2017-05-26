package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.BlocoM;
import model.SubTipoM;
import model.TipoM;


public class SubTipoDAO {
    PreparedStatement pst;
    String sql;
    
    public int salvar (SubTipoM subTipoM) throws SQLException{
        int auxID = 0;
        sql = "insert into Subtipo values(?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setInt(1, 0);
        pst.setString(2, subTipoM.getDescricao());
        pst.setInt(3, subTipoM.getTipo().getId());
        pst.execute();
        
        ResultSet rs = pst.getGeneratedKeys();
        while(rs.next()){
           auxID = rs.getInt(1);
        }
        pst.close();
        
        return auxID;
    }
    
     public SubTipoM busca(int id) throws SQLException{
        sql = "select * from Subtipo where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        SubTipoM subtipo = null;
        TipoDAO tipo = new TipoDAO();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
           subtipo = new SubTipoM(rs.getInt("id"),rs.getString("descricao"), tipo.busca(rs.getInt("id_tipo")));
        }
        pst.close();
        return subtipo;
    }
     
     public List<SubTipoM> listaTodos() throws SQLException{
        List<SubTipoM> listaSub = new ArrayList<SubTipoM>();
        sql = "select * from Subtipo order by descricao";
        pst = Conexao.getInstance().prepareStatement(sql);
        TipoDAO tipo = new TipoDAO();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
           listaSub.add(new SubTipoM(rs.getInt("id"),rs.getString("descricao"), tipo.busca(rs.getInt("id_tipo"))));
        }
        
        pst.close();
        return listaSub;
    }
     
        public void excluir(SubTipoM subt) throws SQLException{
        sql = "delete from Subtipo where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, subt.getId());
        pst.execute();
        pst.close();
    }
        public void alterar(SubTipoM subTipoM) throws SQLException{
        sql = "update Subtipo set descricao = ?, id_tipo = ? where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, subTipoM.getDescricao());
        pst.setInt(2, subTipoM.getTipo().getId());
        pst.setInt(3,subTipoM.getId());
        pst.execute();
        pst.close();
    }
         public SubTipoM buscaNome(String nome) throws SQLException{
           sql = "select * from Subtipo where descricao = ?";
           pst = Conexao.getInstance().prepareStatement(sql);
           pst.setString(1, nome);
           SubTipoM subTipo = new SubTipoM();
           TipoDAO tipo = new TipoDAO();
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               subTipo = new SubTipoM(rs.getInt("id"),rs.getString("descricao"), tipo.busca(rs.getInt("id_tipo")));
            }
            pst.close();
            return subTipo;
     }
         
         public List<SubTipoM> buscaTipo(int id) throws SQLException{
         List<SubTipoM> listaSub = new ArrayList<>();
         sql = "select * from Subtipo where id_tipo = ? order by descricao";
         pst = Conexao.getInstance().prepareStatement(sql);
         pst.setInt(1, id);
         ResultSet rs = pst.executeQuery();
         TipoDAO tipo = new TipoDAO();
         while(rs.next()){
            listaSub.add(new SubTipoM(rs.getInt("id"),rs.getString("descricao"), tipo.busca(rs.getInt("id_tipo"))));
          }
         pst.close();
         return listaSub;
     }
}
