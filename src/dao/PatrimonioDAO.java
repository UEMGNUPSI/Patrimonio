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
import javax.swing.JOptionPane;
import model.PatrimonioM;
import model.SalaM;

public class PatrimonioDAO {
    PreparedStatement pst;
    String sql;
    
     
    public int salvar(PatrimonioM patrimonio) throws SQLException{
        int auxID = 0;
        
        sql = "insert into Patrimonio values(?,?,?,?,?,?,?,?,?,?,1)";
        //acrescentei o PreparedStatement.RETURN_GENERATED_KEYS
        pst = Conexao.getInstance().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setInt(1, 0);
        pst.setString(2, patrimonio.getDescricao());
        pst.setString(3, patrimonio.getCodigo());
        pst.setInt(4, patrimonio.getSubTipo().getId());
        pst.setInt(5, patrimonio.getGrau_conservacao().getId());
        pst.setInt(6, patrimonio.getStatus().getId());
        pst.setInt(7, patrimonio.getSala().getId());
        pst.setString(8, patrimonio.getNotaFiscal());
        pst.setInt(9, patrimonio.getEntidade().getId());
        pst.setBoolean(10, patrimonio.getKit());
        pst.execute();
        //pst.close();
        
        ResultSet rs = pst.getGeneratedKeys();
        while(rs.next()){
           auxID = rs.getInt(1);
        }
 
        pst.close(); 
        return auxID;
    }
    
    public PatrimonioM busca(int id) throws SQLException{
        sql = "select * from Patrimonio where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        PatrimonioM pat = null;
        SubTipoDAO subtipo = new SubTipoDAO();
        GrauConservacaoDAO grau = new GrauConservacaoDAO();
        StatusDAO status = new StatusDAO();
        SalaDAO sala = new SalaDAO();
        OrgaoDAO entidade = new OrgaoDAO();
        while(rs.next()){
           pat = new PatrimonioM(rs.getInt("id"), rs.getString("descricao"),rs.getString("codigo"), subtipo.busca(rs.getInt("id_subtipo")),grau.busca(rs.getInt("id_grau_conservacao")),status.busca(rs.getInt("id_status")),sala.busca(rs.getInt("id_sala")),rs.getString("nota_fiscal"),entidade.busca(rs.getInt("id_entidade")), rs.getBoolean("kit"));
        }
        pst.close();
        return pat;
    }
    
    public List<PatrimonioM> listaTodos() throws SQLException{
        List<PatrimonioM> listaPat = new ArrayList<PatrimonioM>();
        sql = "select * from Patrimonio order by id_sala";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
       
        
        
        
        SubTipoDAO subtipo = new SubTipoDAO();
        GrauConservacaoDAO grau = new GrauConservacaoDAO();
        StatusDAO status = new StatusDAO();
        SalaDAO sala = new SalaDAO();
        OrgaoDAO entidade = new OrgaoDAO();
        
        while(rs.next()){
           listaPat.add(new PatrimonioM(rs.getInt("id"),
                   rs.getString("descricao"),
                   rs.getString("codigo"),
                   subtipo.busca(rs.getInt("id_subtipo")),
                   grau.busca(rs.getInt("id_grau_conservacao")),
                   status.busca(rs.getInt("id_status")),
                   sala.busca(rs.getInt("id_sala")),
                   rs.getString("nota_fiscal"),
                   entidade.busca(rs.getInt("id_entidade")),
                   rs.getBoolean("kit")));
        }
      
        
        pst.close();
        return listaPat;
    }
    
    public void excluir(PatrimonioM patrimonio) throws SQLException{
        sql = "delete from Patrimonio where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, patrimonio.getId());
        pst.execute();
        pst.close();
    }
    
     public void alterar(PatrimonioM patrimonio) throws SQLException{
         sql = "update Patrimonio set descricao = ?, codigo = ?, nota_fiscal = ?, kit = ?, id_grau_conservacao = ?, id_status = ? where id = ?" ;
         pst = Conexao.getInstance().prepareStatement(sql);
         pst.setString(1, patrimonio.getDescricao());
         pst.setString(2, patrimonio.getCodigo());
         pst.setString(3, patrimonio.getNotaFiscal());
         pst.setBoolean(4, patrimonio.getKit());
         pst.setInt(5, patrimonio.getGrau_conservacao().getId());
         pst.setInt(6, patrimonio.getStatus().getId());
         pst.setInt(7, patrimonio.getId());
         pst.execute();
         pst.close();
     }
     
     public List<PatrimonioM> listaTodosSala(int id_sala) throws SQLException{
        List<PatrimonioM> listaPat = new ArrayList<PatrimonioM>();
        sql = "select * from Patrimonio where id_sala = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id_sala);
        ResultSet rs = pst.executeQuery();
        SubTipoDAO subtipo = new SubTipoDAO();
        GrauConservacaoDAO grau = new GrauConservacaoDAO();
        StatusDAO status = new StatusDAO();
        SalaDAO sala = new SalaDAO();
        OrgaoDAO entidade = new OrgaoDAO();
        while(rs.next()){
           listaPat.add(new PatrimonioM(rs.getInt("id"),
                   rs.getString("descricao"),
                   rs.getString("codigo"),
                   subtipo.busca(rs.getInt("id_subtipo")),grau.busca(rs.getInt("id_grau_conservacao")),status.busca(rs.getInt("id_status")),sala.busca(rs.getInt("id_sala")),rs.getString("nota_fiscal"),entidade.busca(rs.getInt("id_entidade")), rs.getBoolean(("kit"))));
        }
        pst.close();
        return listaPat;
    }
     
     public List<PatrimonioM> buscaPatrimonio(String codigo) throws SQLException{
        String aux = "%"+codigo+"%";
        List<PatrimonioM> listaPat = new ArrayList<PatrimonioM>();
        sql = "select * from Patrimonio where codigo like ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, aux);
        ResultSet rs = pst.executeQuery();
        SubTipoDAO subtipo = new SubTipoDAO();
        GrauConservacaoDAO grau = new GrauConservacaoDAO();
        StatusDAO status = new StatusDAO();
        SalaDAO sala = new SalaDAO();
        OrgaoDAO entidade = new OrgaoDAO();
        while(rs.next()){
           listaPat.add(new PatrimonioM(rs.getInt("id"),
                   rs.getString("descricao"),
                   rs.getString("codigo"),
                   subtipo.busca(rs.getInt("id_subtipo")),grau.busca(rs.getInt("id_grau_conservacao")),status.busca(rs.getInt("id_status")),sala.busca(rs.getInt("id_sala")),rs.getString("nota_fiscal"),entidade.busca(rs.getInt("id_entidade")), rs.getBoolean(("kit"))));
        }
        pst.close();
        return listaPat;
     }
      public List<PatrimonioM> buscaPatrimonio100(String codigo, int inicio) throws SQLException{
        String aux = "%"+codigo+"%";
        List<PatrimonioM> listaPat = new ArrayList<PatrimonioM>();
        sql = "select * from Patrimonio where codigo like ? limit ?,100";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, aux);
        pst.setInt(2, inicio);
        ResultSet rs = pst.executeQuery();
        SubTipoDAO subtipo = new SubTipoDAO();
        GrauConservacaoDAO grau = new GrauConservacaoDAO();
        StatusDAO status = new StatusDAO();
        SalaDAO sala = new SalaDAO();
        OrgaoDAO entidade = new OrgaoDAO();
        while(rs.next()){
           listaPat.add(new PatrimonioM(rs.getInt("id"),
                   rs.getString("descricao"),
                   rs.getString("codigo"),
                   subtipo.busca(rs.getInt("id_subtipo")),grau.busca(rs.getInt("id_grau_conservacao")),status.busca(rs.getInt("id_status")),sala.busca(rs.getInt("id_sala")),rs.getString("nota_fiscal"),entidade.busca(rs.getInt("id_entidade")), rs.getBoolean(("kit"))));
        }
        pst.close();
        return listaPat;
     }
     
     public List<PatrimonioM> buscaDescricao(String comparador) throws SQLException{
        comparador = "%"+comparador+"%";
        List<PatrimonioM> listaPat = new ArrayList<PatrimonioM>();
        sql = "select * from patrimonio where descricao like ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, comparador);
        ResultSet rs = pst.executeQuery();
        SubTipoDAO subtipo = new SubTipoDAO();
        GrauConservacaoDAO grau = new GrauConservacaoDAO();
        StatusDAO status = new StatusDAO();
        SalaDAO sala = new SalaDAO();
        OrgaoDAO entidade = new OrgaoDAO();
        while(rs.next()){
           listaPat.add(new PatrimonioM(rs.getInt("id"),
                   rs.getString("descricao"),
                   rs.getString("codigo"),
                   subtipo.busca(rs.getInt("id_subtipo")),grau.busca(rs.getInt("id_grau_conservacao")),status.busca(rs.getInt("id_status")),sala.busca(rs.getInt("id_sala")),rs.getString("nota_fiscal"),entidade.busca(rs.getInt("id_entidade")), rs.getBoolean(("kit"))));
        }
        pst.close();
        return listaPat;
     }
     public List<PatrimonioM> buscaDescricao100(String comparador, int inicio) throws SQLException{
        comparador = "%"+comparador+"%";
        List<PatrimonioM> listaPat = new ArrayList<PatrimonioM>();
        sql = "select * from patrimonio where descricao like ? limit ?,100";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, comparador);
        pst.setInt(2, inicio);
        ResultSet rs = pst.executeQuery();
        SubTipoDAO subtipo = new SubTipoDAO();
        GrauConservacaoDAO grau = new GrauConservacaoDAO();
        StatusDAO status = new StatusDAO();
        SalaDAO sala = new SalaDAO();
        OrgaoDAO entidade = new OrgaoDAO();
        while(rs.next()){
           listaPat.add(new PatrimonioM(rs.getInt("id"),
                   rs.getString("descricao"),
                   rs.getString("codigo"),
                   subtipo.busca(rs.getInt("id_subtipo")),grau.busca(rs.getInt("id_grau_conservacao")),status.busca(rs.getInt("id_status")),sala.busca(rs.getInt("id_sala")),rs.getString("nota_fiscal"),entidade.busca(rs.getInt("id_entidade")), rs.getBoolean(("kit"))));
        }
        pst.close();
        return listaPat;
     }
     
     public List<PatrimonioM> buscaOrgao(int orgaoId) throws SQLException{
        List<PatrimonioM> listaPat = new ArrayList<PatrimonioM>();
        sql = "select * from patrimonio where id_entidade = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, orgaoId);
        ResultSet rs = pst.executeQuery();
        SubTipoDAO subtipo = new SubTipoDAO();
        GrauConservacaoDAO grau = new GrauConservacaoDAO();
        StatusDAO status = new StatusDAO();
        SalaDAO sala = new SalaDAO();
        OrgaoDAO entidade = new OrgaoDAO();
        while(rs.next()){
           listaPat.add(new PatrimonioM(rs.getInt("id"),
                   rs.getString("descricao"),
                   rs.getString("codigo"),
                   subtipo.busca(rs.getInt("id_subtipo")),
                   grau.busca(rs.getInt("id_grau_conservacao")),
                   status.busca(rs.getInt("id_status")),
                   sala.busca(rs.getInt("id_sala")),
                   rs.getString("nota_fiscal"),
                   entidade.busca(rs.getInt("id_entidade")), 
                   rs.getBoolean(("kit"))));
        }
        pst.close();
        return listaPat;
     }
     
     public List<PatrimonioM> buscaConservacao(int conservacaoId) throws SQLException{
        List<PatrimonioM> listaPat = new ArrayList<PatrimonioM>();
        sql = "select * from patrimonio where id_grau_conservacao = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, conservacaoId);
        ResultSet rs = pst.executeQuery();
        SubTipoDAO subtipo = new SubTipoDAO();
        GrauConservacaoDAO grau = new GrauConservacaoDAO();
        StatusDAO status = new StatusDAO();
        SalaDAO sala = new SalaDAO();
        OrgaoDAO entidade = new OrgaoDAO();
        while(rs.next()){
           listaPat.add(new PatrimonioM(rs.getInt("id"),
                   rs.getString("descricao"),
                   rs.getString("codigo"),
                   subtipo.busca(rs.getInt("id_subtipo")),grau.busca(rs.getInt("id_grau_conservacao")),status.busca(rs.getInt("id_status")),sala.busca(rs.getInt("id_sala")),rs.getString("nota_fiscal"),entidade.busca(rs.getInt("id_entidade")), rs.getBoolean(("kit"))));
        }
        pst.close();
        return listaPat;
     }
     
      public List<PatrimonioM> listaTodosDescricao() throws SQLException{
        List<PatrimonioM> listaPat = new ArrayList<PatrimonioM>();
        sql = "select count(*) quantidade, descricao from patrimonio group by descricao";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        SubTipoDAO subtipo = new SubTipoDAO();
        GrauConservacaoDAO grau = new GrauConservacaoDAO();
        StatusDAO status = new StatusDAO();
        SalaDAO sala = new SalaDAO();
        OrgaoDAO entidade = new OrgaoDAO();
        while(rs.next()){
           listaPat.add(new PatrimonioM(/*rs.getInt("id"),*/
                   rs.getString("descricao"),
                   /*rs.getString("codigo"),
                   subtipo.busca(rs.getInt("id_subtipo")),
                   grau.busca(rs.getInt("id_grau_conservacao")),
                   status.busca(rs.getInt("id_status")),
                   sala.busca(rs.getInt("id_sala")),
                   rs.getString("nota_fiscal"),
                   entidade.busca(rs.getInt("id_entidade")),
                   rs.getBoolean(("kit")),*/
                   rs.getInt("quantidade")
           ));
        }
        pst.close();
        return listaPat;
     }
      
      public List<PatrimonioM> buscaDescricaoGroup(String comparador) throws SQLException{
        String aux = "%"+comparador+"%";
        List<PatrimonioM> listaPat = new ArrayList<PatrimonioM>();
        sql = "select count(*) quantidade, descricao from patrimonio where descricao like ? group by descricao";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, aux);
        ResultSet rs = pst.executeQuery();
        SubTipoDAO subtipo = new SubTipoDAO();
        GrauConservacaoDAO grau = new GrauConservacaoDAO();
        StatusDAO status = new StatusDAO();
        SalaDAO sala = new SalaDAO();
        OrgaoDAO entidade = new OrgaoDAO();
        while(rs.next()){
           listaPat.add(new PatrimonioM(/*rs.getInt("id"),*/
                   rs.getString("descricao"),
                   /*rs.getString("codigo"),
                   subtipo.busca(rs.getInt("id_subtipo")),
                   grau.busca(rs.getInt("id_grau_conservacao")),
                   status.busca(rs.getInt("id_status")),
                   sala.busca(rs.getInt("id_sala")),
                   rs.getString("nota_fiscal"),
                   entidade.busca(rs.getInt("id_entidade")),
                   rs.getBoolean(("kit")),*/
                   rs.getInt("quantidade")
           ));
        }
        pst.close();
        return listaPat;
     }
     public List<PatrimonioM> listaTodosPorDescricao(String comparador) throws SQLException{
        List<PatrimonioM> listaPat = new ArrayList<PatrimonioM>();
        sql = "select * from patrimonio where descricao = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, comparador);
        ResultSet rs = pst.executeQuery();
        SubTipoDAO subtipo = new SubTipoDAO();
        GrauConservacaoDAO grau = new GrauConservacaoDAO();
        StatusDAO status = new StatusDAO();
        SalaDAO sala = new SalaDAO();
        OrgaoDAO entidade = new OrgaoDAO();
        while(rs.next()){
           listaPat.add(new PatrimonioM(rs.getInt("id"),
                   rs.getString("descricao"),
                   rs.getString("codigo"),
                   subtipo.busca(rs.getInt("id_subtipo")),
                   grau.busca(rs.getInt("id_grau_conservacao")),
                   status.busca(rs.getInt("id_status")),
                   sala.busca(rs.getInt("id_sala")),
                   rs.getString("nota_fiscal"),
                   entidade.busca(rs.getInt("id_entidade")),
                   rs.getBoolean(("kit"))));
        }
        pst.close();
        return listaPat;
     } 
     public List<PatrimonioM> listaSelecionados(int id_sala) throws SQLException{
       
         List<PatrimonioM> listaPatri = new ArrayList<PatrimonioM>();
        
        sql = "select * from patrimonio where id_sala = ? order by id_sala";
       
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id_sala);
        //pst.setInt(2, id_bloco);
        //pst.setInt(3, id_unidade);
        ResultSet rs = pst.executeQuery();
        //PisoDAO piso = new PisoDAO();
        GrauConservacaoDAO grau = new GrauConservacaoDAO();
        while(rs.next()){
           listaPatri.add(new PatrimonioM(rs.getInt("id"),
                   rs.getString("codigo"),
                   rs.getNString("descricao"),
                   grau.busca(rs.getInt("id_grau_conservacao"))));
        }
        pst.close();
        return listaPatri;
     }
     
     public void movimentar( List<PatrimonioM> lista, int id_sala) throws SQLException{
         for(int i = 0; i < lista.size(); i++){
             sql = "update Patrimonio set id_sala = ? where id = ?";
            pst = Conexao.getInstance().prepareStatement(sql);
            pst.setInt(1, id_sala);
            pst.setInt(2, lista.get(i).getId());
            pst.execute();
            pst.close();
         }
         
     }
     
     public List<PatrimonioM> buscaSubtipo(int comparador) throws SQLException{
        List<PatrimonioM> listaPat = new ArrayList<PatrimonioM>();
        sql = "select * from patrimonio where id_subtipo = ?";
        
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, comparador);
        ResultSet rs = pst.executeQuery();
        SubTipoDAO subtipo = new SubTipoDAO();
        GrauConservacaoDAO grau = new GrauConservacaoDAO();
        StatusDAO status = new StatusDAO();
        SalaDAO sala = new SalaDAO();
        OrgaoDAO entidade = new OrgaoDAO();
        while(rs.next()){
           listaPat.add(new PatrimonioM(
                   rs.getInt("id"),
                   rs.getString("descricao"),
                   rs.getString("codigo"),
                   subtipo.busca(rs.getInt("id_subtipo")),
                   grau.busca(rs.getInt("id_grau_conservacao")),
                   status.busca(rs.getInt("id_status")),
                   sala.busca(rs.getInt("id_sala")),
                   rs.getString("nota_fiscal"),
                   entidade.busca(rs.getInt("id_entidade")),
                   rs.getBoolean(("kit"))
           ));
        }
        pst.close();
        return listaPat;
     }
     
      public List<PatrimonioM> lista100(int id_inicio) throws SQLException{
        List<PatrimonioM> listaPat = new ArrayList<PatrimonioM>();
        sql = "select * from patrimonio limit ?,100";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id_inicio);
        ResultSet rs = pst.executeQuery();
        
        
        SubTipoDAO subtipo = new SubTipoDAO();
        GrauConservacaoDAO grau = new GrauConservacaoDAO();
        StatusDAO status = new StatusDAO();
        SalaDAO sala = new SalaDAO();
        OrgaoDAO entidade = new OrgaoDAO();
        
        while(rs.next()){
           listaPat.add(new PatrimonioM(rs.getInt("id"),
                   rs.getString("descricao"),
                   rs.getString("codigo"),
                   subtipo.busca(rs.getInt("id_subtipo")),
                   grau.busca(rs.getInt("id_grau_conservacao")),
                   status.busca(rs.getInt("id_status")),
                   sala.busca(rs.getInt("id_sala")),
                   rs.getString("nota_fiscal"),
                   entidade.busca(rs.getInt("id_entidade")),
                   rs.getBoolean("kit")));
        }
      
        
        pst.close();
        return listaPat;
    }
      
      public int quantidade() throws SQLException{     

        sql = "select count(*) quantidade from patrimonio";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        //PatrimonioM patrimonioM = new PatrimonioM(rs.getInt("quantidade"));
        int aux = 0;
        while(rs.next()){
            aux = rs.getInt("quantidade");
        }
        
        pst.close();
        
        return aux;
     }
      public int quantidadeDescricao(String comparador) throws SQLException{     
        String aux = "%"+comparador+"%";
        sql = "select count(*) quantidade from patrimonio where descricao like ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, aux);
        ResultSet rs = pst.executeQuery();

        //PatrimonioM patrimonioM = new PatrimonioM(rs.getInt("quantidade"));
        int quant = 0;
        while(rs.next()){
            quant = rs.getInt("quantidade");
        }
        
        pst.close();
        
        return quant;
     }
      public int quantidadeCodigo(String comparador) throws SQLException{     
        String aux = "%"+comparador+"%";
        sql = "select count(*) quantidade from patrimonio where codigo like ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, aux);
        ResultSet rs = pst.executeQuery();

        //PatrimonioM patrimonioM = new PatrimonioM(rs.getInt("quantidade"));
        int quant = 0;
        while(rs.next()){
            quant = rs.getInt("quantidade");
        }
        
        pst.close();
        
        return quant;
     }
      
      public List<PatrimonioM> listaPatrimonioEsperados(int id_sala) throws SQLException{
        List<PatrimonioM> listaPat = new ArrayList<PatrimonioM>();
        sql = "select * from Patrimonio where id_sala = ? and inventario = 0";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id_sala);
        ResultSet rs = pst.executeQuery();
        SubTipoDAO subtipo = new SubTipoDAO();
        GrauConservacaoDAO grau = new GrauConservacaoDAO();
        StatusDAO status = new StatusDAO();
        SalaDAO sala = new SalaDAO();
        OrgaoDAO entidade = new OrgaoDAO();
        while(rs.next()){
           listaPat.add(new PatrimonioM(rs.getInt("id"),
                   rs.getString("codigo"),
                   rs.getString("descricao") ));
        }
        pst.close();
        return listaPat;
    }
      
      public List<PatrimonioM> listaPatrimonioReais(int id_sala) throws SQLException{
        List<PatrimonioM> listaPat = new ArrayList<PatrimonioM>();
        sql = "select * from Patrimonio where id_sala = ? and inventario = 1";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id_sala);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
           listaPat.add(new PatrimonioM(rs.getInt("id"),
                   rs.getString("codigo"),
                   rs.getString("descricao") ));
        }
        pst.close();
        return listaPat;
    }
      
      public static void patrimonioInventariado(String codigo,int numeroSala) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "select id_sala, inventario from patrimonio where codigo = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, codigo);
        ResultSet rs = pst.executeQuery();
        int salaAtual = 0;
        int inventario = 0;
        while(rs.next()){
           salaAtual = rs.getInt("id_sala");
           inventario = rs.getInt("inventario");
        }
        if(numeroSala != salaAtual && inventario != 1){
            SalaDAO sala = new SalaDAO();
            if(JOptionPane.showConfirmDialog(null, "Patrimonio localizado em:"
                    + "\nUnidade: "+sala.busca(salaAtual).getPiso().getBloco().getUnidadeM().getNome()
                    + "\nBloco: "+sala.busca(salaAtual).getPiso().getBloco().getDescricao()
                    + "\nPiso: "+sala.busca(salaAtual).getPiso().getDescricao()
                    + "\nSala: "+sala.busca(salaAtual).getDescricao()+"\nDeseja Movimenta-lo?") == 0 ){
                sql = "update Patrimonio set inventario = 1, id_sala = ? where codigo = ? and inventario != 1" ;
                pst = Conexao.getInstance().prepareStatement(sql);
                pst.setInt(1, numeroSala);
                pst.setString(2, codigo);     
                pst.execute();
                pst.close();
            }
        }else {
            sql = "update Patrimonio set inventario = 1 where codigo = ? and inventario != 1" ;
            pst = Conexao.getInstance().prepareStatement(sql);
            pst.setString(1, codigo);     
            pst.execute();
            pst.close();
        }
        
      }
      
      public static void inventarioTaAqui(int id) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "update Patrimonio set inventario = 1 where id = ?" ;
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);     
        pst.execute();
        pst.close();
      }
      
      
      
}
