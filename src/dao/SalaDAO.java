package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.SalaM;


public class SalaDAO {
    PreparedStatement pst;
    String sql;
    
    public int salvar(SalaM sala) throws SQLException{
        int auxID = 0;
        sql = "insert into Sala values(?,?,?,1)";
        pst = Conexao.getInstance().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setInt(1, 0);
        pst.setString(2, sala.getDescricao());
        pst.setInt(3, sala.getPiso().getId());
        pst.execute();
        
        ResultSet rs = pst.getGeneratedKeys();
        while(rs.next()){
           auxID = rs.getInt(1);
        }
        pst.close();
        
        return auxID;
    }
    
     public SalaM busca(int id) throws SQLException{
        sql = "select * from Sala where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        SalaM sala = null;
        PisoDAO piso = new PisoDAO();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
           sala = new SalaM(rs.getInt("id"),rs.getString("nome"), piso.busca(rs.getInt("id_piso")), rs.getInt("inventario"));
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
           listaSala.add(new SalaM(rs.getInt("id"),rs.getString("nome"), piso.busca(rs.getInt("id_piso")), rs.getInt("inventario")));
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
               sala = new SalaM(rs.getInt("id"),rs.getString("nome"), piso.busca(rs.getInt("id_piso")), rs.getInt("inventario"));
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
               sala = new SalaM(rs.getInt("id"),rs.getString("nome"), piso.busca(rs.getInt("id_piso")), rs.getInt("inventario"));
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
            listaSala.add(new SalaM(rs.getInt("id"),rs.getString("nome"), piso.busca(rs.getInt("id_piso")), rs.getInt("inventario")));
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
           listaSala.add(new SalaM(rs.getInt("id"),rs.getString("nome"), piso.busca(rs.getInt("id_piso")), rs.getInt("inventario")));
        }
        pst.close();
        return listaSala;
    }
        
        public static void iniciaInventario() throws SQLException{
            String sql;
            PreparedStatement pst;
            
            sql = "update Sala set inventario = 0";
            pst = Conexao.getInstance().prepareStatement(sql);
            pst.execute();
            pst.close();
        }
        
        public List<SalaM> listaTodosInventario() throws SQLException{
        List<SalaM> listaSala = new ArrayList<>();
        sql = "select * from Sala order by inventario, id_piso";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        PisoDAO piso = new PisoDAO();
        while(rs.next()){
           listaSala.add(new SalaM(rs.getInt("id"),rs.getString("nome"), piso.busca(rs.getInt("id_piso")), rs.getInt("inventario")));
        }
        pst.close();
        return listaSala;
    }
        public List<SalaM> listaBuscaInventario(int id_piso, int id_bloco, int id_unidade) throws SQLException{
        List<SalaM> listaSala = new ArrayList<SalaM>();
        sql = "select * from Sala where id_piso = ? order by inventario, id_piso";
        
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
           listaSala.add(new SalaM(rs.getInt("id"),rs.getString("nome"), piso.busca(rs.getInt("id_piso")), rs.getInt("inventario")));
        }
        pst.close();
        return listaSala;
    }
        public static void finalizaSala(int numeroSala, int situacao) throws SQLException{
            String sql;
            PreparedStatement pst;
            
            sql = "update Sala set inventario = ? where id = ?";
            pst = Conexao.getInstance().prepareStatement(sql);
            pst.setInt(1, situacao);
            pst.setInt(2, numeroSala);
            pst.execute();
            pst.close();
        }
        
        
        
}
