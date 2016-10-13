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
import model.SalaM;


public class SalaDAO {
    PreparedStatement pst;
    String sql;
    
    public void salvar(SalaM sala) throws SQLException{
        sql = "insert into Sala values(?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, 0);
        pst.setString(2, sala.getDescricao());
        pst.setInt(3, sala.getPiso().getId());
        pst.execute();
        pst.close();
    }
    
     public SalaM busca(int id) throws SQLException{
        sql = "select * from Sala where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        SalaM sala = null;
        PisoDAO piso = new PisoDAO();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
           sala = new SalaM(rs.getInt("id"),rs.getString("nome"), piso.busca(rs.getInt("id_piso")));
        }
        pst.close();
        return sala;
    }
     
     public List<SalaM> listaTodos() throws SQLException{
        List<SalaM> listaSala = new ArrayList<SalaM>();
        sql = "select * from Sala order by id_piso";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        PisoDAO piso = new PisoDAO();
        while(rs.next()){
           listaSala.add(new SalaM(rs.getInt("id"),rs.getString("nome"), piso.busca(rs.getInt("id_piso"))));
        }
        pst.close();
        return listaSala;
    }
     
        public void excluir(SalaM sala) throws SQLException{
        sql = "delete from Sala where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, sala.getId());
        pst.execute();
        pst.close();
    }
        public void alterar(SalaM sala) throws SQLException{
         sql = "update Sala set nome = ? where id = ?" ;
         pst = Conexao.getInstance().prepareStatement(sql);
         pst.setString(1, sala.getDescricao());
         pst.setInt(2, sala.getId());
         pst.execute();
         pst.close();
     }
        
        public SalaM buscaNome(String nome) throws SQLException{
           sql = "select * from Sala where nome = ?";
           pst = Conexao.getInstance().prepareStatement(sql);
           pst.setString(1, nome);
           SalaM sala = null;
           PisoDAO piso = new PisoDAO();
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               sala = new SalaM(rs.getInt("id"),rs.getString("nome"), piso.busca(rs.getInt("id_piso")));
            }
            pst.close();
            return sala;
     }
         public SalaM buscaID(int id_piso, String nome) throws SQLException{
           sql = "select * from Sala where id_piso = ? and nome = ?";
           pst = Conexao.getInstance().prepareStatement(sql);
           pst.setInt(1, id_piso);
           pst.setString(2, nome);
           SalaM sala = null;
           PisoDAO piso = new PisoDAO();
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               sala = new SalaM(rs.getInt("id"),rs.getString("nome"), piso.busca(rs.getInt("id_piso")));
            }
            pst.close();
            return sala;
     }
        
        public List<SalaM> buscaPis(int id) throws SQLException{
         List<SalaM> listaSala = new ArrayList<>();
         sql = "select * from Sala where id_piso = ?";
         pst = Conexao.getInstance().prepareStatement(sql);
         pst.setInt(1, id);
         ResultSet rs = pst.executeQuery();
         PisoDAO piso = new PisoDAO();
         while(rs.next()){
            listaSala.add(new SalaM(rs.getInt("id"),rs.getString("nome"), piso.busca(rs.getInt("id_piso"))));
          }
         pst.close();
         return listaSala;
     }
        
        public List<SalaM> listaSelecionados(int id_piso, int id_bloco, int id_unidade) throws SQLException{
        List<SalaM> listaSala = new ArrayList<SalaM>();
        sql = "select * from Sala where id_piso = ? order by id_piso";
        
        /*sql = "select * from Sala s \n" +
                "inner join Piso p on (s.id_piso = ?)\n" +
                "inner join Bloco b on (p.id_bloco = ?)\n" +
                "inner join Unidade u on (b.id_unidade = ?)\n" +
                "order by \n" +
                "s.id";
        */
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id_piso);
        //pst.setInt(2, id_bloco);
        //pst.setInt(3, id_unidade);
        ResultSet rs = pst.executeQuery();
        PisoDAO piso = new PisoDAO();
        while(rs.next()){
           listaSala.add(new SalaM(rs.getInt("id"),rs.getString("nome"), piso.busca(rs.getInt("id_piso"))));
        }
        pst.close();
        return listaSala;
    }
        
        
        
}
