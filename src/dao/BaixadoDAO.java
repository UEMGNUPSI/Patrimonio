package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.BaixadoM;
import model.BlocoM;
import model.PatrimonioM;


public class BaixadoDAO {
    PreparedStatement pst;
    String sql;
    
    public BaixadoM busca(int id) throws SQLException{
        sql = "select * from patrimonio_baixado where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        BaixadoM baixado = null;
        SubTipoDAO subtipo = new SubTipoDAO();
        GrauConservacaoDAO grau = new GrauConservacaoDAO();
        StatusDAO status = new StatusDAO();
        SalaDAO sala = new SalaDAO();
        OrgaoDAO entidade = new OrgaoDAO();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
           baixado = new BaixadoM(rs.getInt("id"), 
                   rs.getString("descricao"),
                   rs.getString("codigo"),
                   subtipo.busca(rs.getInt("id_subtipo")),
                   grau.busca(rs.getInt("id_grau_conservacao")),        
                   rs.getString("nota_fiscal"),
                   entidade.busca(rs.getInt("id_entidade")),
                   rs.getBoolean("kit"));
        }
        pst.close();
        return baixado;
    }
    public BaixadoM busca100(int id, int inicio) throws SQLException{
        sql = "select * from patrimonio_baixado where id = ? limit ?,100";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        pst.setInt(2, inicio);
        BaixadoM baixado = null;
        SubTipoDAO subtipo = new SubTipoDAO();
        GrauConservacaoDAO grau = new GrauConservacaoDAO();
        StatusDAO status = new StatusDAO();
        SalaDAO sala = new SalaDAO();
        OrgaoDAO entidade = new OrgaoDAO();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
           baixado = new BaixadoM(rs.getInt("id"), 
                   rs.getString("descricao"),
                   rs.getString("codigo"),
                   subtipo.busca(rs.getInt("id_subtipo")),
                   grau.busca(rs.getInt("id_grau_conservacao")),        
                   rs.getString("nota_fiscal"),
                   entidade.busca(rs.getInt("id_entidade")),
                   rs.getBoolean("kit"));
        }
        pst.close();
        return baixado;
    }
    public List<BaixadoM> lista100(int id_inicio) throws SQLException{
        List<BaixadoM> listBai = new ArrayList<>();
        sql = "select * from patrimonio_baixado limit ?,100";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id_inicio);
        ResultSet rs = pst.executeQuery();
        
        
        SubTipoDAO subtipo = new SubTipoDAO();
        GrauConservacaoDAO grau = new GrauConservacaoDAO();
        StatusDAO status = new StatusDAO();
        SalaDAO sala = new SalaDAO();
        OrgaoDAO entidade = new OrgaoDAO();
       
        while(rs.next()){
          listBai.add( new BaixadoM(rs.getInt("id"), 
                   rs.getString("descricao"),
                   rs.getString("codigo"),
                   subtipo.busca(rs.getInt("id_subtipo")),
                   grau.busca(rs.getInt("id_grau_conservacao")),        
                   rs.getString("nota_fiscal"),
                   entidade.busca(rs.getInt("id_entidade")),
                   rs.getBoolean("kit")));
        }
      
        
        pst.close();
        return listBai;
    }
    public List<BaixadoM> buscaPatrimonio100(String codigo, int inicio) throws SQLException{
        String aux = "%"+codigo+"%";
        List<BaixadoM> listBai = new ArrayList<>();
        sql = "select * from patrimonio_baixado where codigo like ? limit ?,100";
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
           listBai.add( new BaixadoM(rs.getInt("id"), 
                   rs.getString("descricao"),
                   rs.getString("codigo"),
                   subtipo.busca(rs.getInt("id_subtipo")),
                   grau.busca(rs.getInt("id_grau_conservacao")),        
                   rs.getString("nota_fiscal"),
                   entidade.busca(rs.getInt("id_entidade")),
                   rs.getBoolean("kit")));
        }
        pst.close();
        return listBai;
     }
    public int quantidadeCodigo(String comparador) throws SQLException{     
        String aux = "%"+comparador+"%";
        sql = "select count(*) quantidade from patrimonio_baixado where codigo like ?";
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
    public List<BaixadoM> buscaDescricao100(String comparador, int inicio) throws SQLException{
        comparador = "%"+comparador+"%";
        List<BaixadoM> listabai = new ArrayList<>();
        sql = "select * from patrimonio_baixado where descricao like ? limit ?,100";
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
           listabai.add( new BaixadoM(rs.getInt("id"), 
                   rs.getString("descricao"),
                   rs.getString("codigo"),
                   subtipo.busca(rs.getInt("id_subtipo")),
                   grau.busca(rs.getInt("id_grau_conservacao")),        
                   rs.getString("nota_fiscal"),
                   entidade.busca(rs.getInt("id_entidade")),
                   rs.getBoolean("kit")));
        }
        pst.close();
        return listabai;
     }
    public int quantidadeDescricao(String comparador) throws SQLException{     
        String aux = "%"+comparador+"%";
        sql = "select count(*) quantidade from patrimonio_baixado where descricao like ?";
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
    public int quantidade() throws SQLException{     

        sql = "select count(*) quantidade from patrimonio_baixado";
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
    public List<BaixadoM> buscaOrgao100(int orgaoId,int inicio) throws SQLException{
        List<BaixadoM> listaBai = new ArrayList<>();
        sql = "select * from patrimonio_baixado where id_entidade = ? limit ?,100";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, orgaoId);
        pst.setInt(2, inicio);
        ResultSet rs = pst.executeQuery();
        SubTipoDAO subtipo = new SubTipoDAO();
        GrauConservacaoDAO grau = new GrauConservacaoDAO();
        StatusDAO status = new StatusDAO();
        SalaDAO sala = new SalaDAO();
        OrgaoDAO entidade = new OrgaoDAO();
        while(rs.next()){
           listaBai.add( new BaixadoM(rs.getInt("id"), 
                   rs.getString("descricao"),
                   rs.getString("codigo"),
                   subtipo.busca(rs.getInt("id_subtipo")),
                   grau.busca(rs.getInt("id_grau_conservacao")),        
                   rs.getString("nota_fiscal"),
                   entidade.busca(rs.getInt("id_entidade")),
                   rs.getBoolean("kit")));
        }
        pst.close();
        return listaBai;
     }
    public int quantidadeOrgao(int comparador) throws SQLException{     
        String aux = "%"+comparador+"%";
        sql = "select count(*) quantidade from patrimonio_baixado where id_entidade = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        //pst.setString(1, aux);
        pst.setInt(1,comparador);
        ResultSet rs = pst.executeQuery();

        //PatrimonioM patrimonioM = new PatrimonioM(rs.getInt("quantidade"));
        int quant = 0;
        while(rs.next()){
            quant = rs.getInt("quantidade");
        }
        
        pst.close();
        
        return quant;
     }
     public List<BaixadoM> buscaConservacao100(int conservacaoId, int inicio) throws SQLException{
        List<BaixadoM> listaBai = new ArrayList<>();
        sql = "select * from patrimonio_baixado where id_grau_conservacao = ? limit ?,100";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, conservacaoId);
        pst.setInt(2, inicio);
        ResultSet rs = pst.executeQuery();
        SubTipoDAO subtipo = new SubTipoDAO();
        GrauConservacaoDAO grau = new GrauConservacaoDAO();
        StatusDAO status = new StatusDAO();
        SalaDAO sala = new SalaDAO();
        OrgaoDAO entidade = new OrgaoDAO();
        while(rs.next()){
           listaBai.add( new BaixadoM(rs.getInt("id"), 
                   rs.getString("descricao"),
                   rs.getString("codigo"),
                   subtipo.busca(rs.getInt("id_subtipo")),
                   grau.busca(rs.getInt("id_grau_conservacao")),        
                   rs.getString("nota_fiscal"),
                   entidade.busca(rs.getInt("id_entidade")),
                   rs.getBoolean("kit")));
        }
        pst.close();
        return listaBai;
     }
     public int quantidadeConservacao(int comparador) throws SQLException{     
        String aux = "%"+comparador+"%";
        sql = "select count(*) quantidade from patrimonio_baixado where id_grau_conservacao = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        //pst.setString(1, aux);
        pst.setInt(1,comparador);
        ResultSet rs = pst.executeQuery();

        //PatrimonioM patrimonioM = new PatrimonioM(rs.getInt("quantidade"));
        int quant = 0;
        while(rs.next()){
            quant = rs.getInt("quantidade");
        }
        
        pst.close();
        
        return quant;
     }
     public List<BaixadoM> buscaSubtipo100(int comparador, int inicio) throws SQLException{
        List<BaixadoM> listaBai = new ArrayList<>();
        sql = "select * from patrimonio_baixado where id_subtipo = ? limit ?,100";
        
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, comparador);
        pst.setInt(2, inicio);
        ResultSet rs = pst.executeQuery();
        SubTipoDAO subtipo = new SubTipoDAO();
        GrauConservacaoDAO grau = new GrauConservacaoDAO();
        StatusDAO status = new StatusDAO();
        SalaDAO sala = new SalaDAO();
        OrgaoDAO entidade = new OrgaoDAO();
        while(rs.next()){
           listaBai.add( new BaixadoM(rs.getInt("id"), 
                   rs.getString("descricao"),
                   rs.getString("codigo"),
                   subtipo.busca(rs.getInt("id_subtipo")),
                   grau.busca(rs.getInt("id_grau_conservacao")),        
                   rs.getString("nota_fiscal"),
                   entidade.busca(rs.getInt("id_entidade")),
                   rs.getBoolean("kit")));
           
        }
        pst.close();
        return listaBai;
     }
      public int quantidadeSubtipo(int comparador) throws SQLException{     
        String aux = "%"+comparador+"%";
        sql = "select count(*) quantidade from patrimonio_baixado where id_subtipo = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        //pst.setString(1, aux);
        pst.setInt(1,comparador);
        ResultSet rs = pst.executeQuery();

        //PatrimonioM patrimonioM = new PatrimonioM(rs.getInt("quantidade"));
        int quant = 0;
        while(rs.next()){
            quant = rs.getInt("quantidade");
        }
        
        pst.close();
        
        return quant;
     }
     
}
