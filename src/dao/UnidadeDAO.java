package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.UnidadeM;


public class UnidadeDAO {

    PreparedStatement pst;
    String sql;
    
    public int salvar(UnidadeM unidade) throws SQLException{
        int auxID = 0;
        sql = "insert into Unidade values(?,?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setInt(1, 0);
        pst.setString(2, unidade.getNome());
        pst.setString(3, unidade.getTelefone());
        pst.setString(4, unidade.getEndereco());
        pst.setString(5, unidade.getEmail());
        pst.execute();
        
        ResultSet rs = pst.getGeneratedKeys();
        while(rs.next()){
           auxID = rs.getInt(1);
        }
        pst.close();
        
        return auxID;
    }
    
    public UnidadeM busca(int id) throws SQLException{
        sql = "select * from Unidade where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        UnidadeM unidade = null;
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
           unidade = new UnidadeM(rs.getInt("id"),rs.getString("nome"),rs.getString("telefone"),rs.getString("endereco"),rs.getString("email"));
        }
        pst.close();
        return unidade;
    }
    
    public List<UnidadeM> listaTodos() throws SQLException{
        List<UnidadeM> listaUnidade = new ArrayList<UnidadeM>();
        sql = "select * from Unidade order by nome";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
           listaUnidade.add(new UnidadeM(rs.getInt("id"),rs.getString("nome"),rs.getString("telefone"),rs.getString("endereco"),rs.getString("email")));
        }
        pst.close();
        return listaUnidade;
    }
    
       public void excluir(UnidadeM uni) throws SQLException{
        sql = "delete from Unidade where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, uni.getId());
        pst.execute();
        pst.close();
    }
       
       public void alterar(UnidadeM unidade) throws SQLException{
         sql = "update Unidade set nome = ?, telefone = ?, endereco = ?, email = ? where id = ?";
         pst = Conexao.getInstance().prepareStatement(sql);
         pst.setString(1, unidade.getNome());
         pst.setString(2, unidade.getTelefone());
         pst.setString(3, unidade.getEndereco());
         pst.setString(4, unidade.getEmail());
         pst.setInt(5, unidade.getId());
         pst.execute();
         pst.close();
     }
       
       public UnidadeM buscaNome(String nome) throws SQLException{
           sql = "select * from Unidade where nome = ?";
           pst = Conexao.getInstance().prepareStatement(sql);
           pst.setString(1, nome);
           UnidadeM unidade = null;
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
              unidade = new UnidadeM(rs.getInt("id"),rs.getString("nome"),rs.getString("telefone"),rs.getString("endereco"),rs.getString("email"));
           }
           pst.close();
           return unidade;
       }
    
    
}
