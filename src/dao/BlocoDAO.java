package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.BlocoM;
import model.UnidadeM;


public class BlocoDAO {
    PreparedStatement pst;
    String sql;
    
    public int salvar(BlocoM bloco) throws SQLException{
        int auxID = 0;
        sql = "insert into Bloco values(?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);     
        pst.setInt(1, 0);
        pst.setString(2, bloco.getDescricao());
        pst.setInt(3, bloco.getUnidadeM().getId());
        pst.execute();
        
        ResultSet rs = pst.getGeneratedKeys();
        while(rs.next()){
            auxID = rs.getInt(1);
        }
        
        pst.close();
        return auxID;
    }
    
     public BlocoM busca(int id) throws SQLException{
        sql = "select * from Bloco where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        BlocoM bloco = null;
        UnidadeDAO unidade = new UnidadeDAO();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
           bloco = new BlocoM(rs.getInt("id"),rs.getString("nome"), unidade.busca(rs.getInt("id_unidade")));
        }
        pst.close();
        return bloco;
    }
     public BlocoM busca_id_unidade(int id_unidade, String nome_bloco) throws SQLException{
        sql = "select * from Bloco where id_unidade = ? and nome = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id_unidade);
        pst.setString(2, nome_bloco);
        BlocoM bloco = null;
        UnidadeDAO unidade = new UnidadeDAO();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
           bloco = new BlocoM(rs.getInt("id"),rs.getString("nome"), unidade.busca(rs.getInt("id_unidade")));
        }
        pst.close();
        return bloco;
    }
     
     public List<BlocoM> listaTodos() throws SQLException{
        List<BlocoM> listaBloco = new ArrayList<BlocoM>();
        sql = "select * from Bloco order by id_unidade";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        UnidadeDAO unidade = new UnidadeDAO();
        while(rs.next()){
           listaBloco.add(new BlocoM(rs.getInt("id"),rs.getString("nome"), unidade.busca(rs.getInt("id_unidade"))));
        }
        pst.close();
        return listaBloco;
    }
     
    public void excluir(BlocoM bloco) throws SQLException{
        sql = "delete from Bloco where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, bloco.getId());
        pst.execute();
        pst.close();
    }
     public void alterar(BlocoM bloco) throws SQLException{
         sql = "update Bloco set nome = ? where id = ?" ;
         pst = Conexao.getInstance().prepareStatement(sql);
         pst.setString(1, bloco.getDescricao());
         pst.setInt(2, bloco.getId());
         pst.execute();
         pst.close();
     }
     
     public BlocoM buscaNome(String nome) throws SQLException{
           sql = "select * from Bloco where nome = ?";
           pst = Conexao.getInstance().prepareStatement(sql);
           pst.setString(1, nome);
           BlocoM bloco = null;
           UnidadeDAO unidade = new UnidadeDAO();
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               bloco = new BlocoM(rs.getInt("id"), rs.getString("nome"), unidade.busca(rs.getInt("id_unidade")));
            }
            pst.close();
            return bloco;
     }
     
     public List<BlocoM> buscaUni(int id) throws SQLException{
         List<BlocoM> listaBloco = new ArrayList<>();
         sql = "select * from Bloco where id_unidade = ?";
         pst = Conexao.getInstance().prepareStatement(sql);
         pst.setInt(1, id);
         ResultSet rs = pst.executeQuery();
         UnidadeDAO unidade = new UnidadeDAO();
         while(rs.next()){
            listaBloco.add(new BlocoM(rs.getInt("id"),rs.getString("nome"), unidade.busca(rs.getInt("id_unidade"))));
          }
         pst.close();
         return listaBloco;
     }
}
