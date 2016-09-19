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
import model.PatrimonioM;

public class PatrimonioDAO {
    PreparedStatement pst;
    String sql;
    
     
    public int salvar(PatrimonioM patrimonio) throws SQLException{
        int auxID = 0;
        
        sql = "insert into Patrimonio values(?,?,?,?,?,?,?,?,?,?)";
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
         sql = "update Patrimonio set descricao = ?, codigo = ?, nota_fiscal = ? where id = ?" ;
         pst = Conexao.getInstance().prepareStatement(sql);
         pst.setString(1, patrimonio.getDescricao());
         pst.setString(2, patrimonio.getCodigo());
         pst.setString(3, patrimonio.getNotaFiscal());
         pst.setInt(4, patrimonio.getId());
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
}
