package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.PatrimonioCompostoM;
import model.PatrimonioM;


public class PatrimonioCompostoDAO {
    PreparedStatement pst;
    String sql;
    
    //lista os itens de um patrimonio especificado 
    //usado para alterar o KIT
    public static List<PatrimonioCompostoM> listaTodosExistentes(PatrimonioM patrimonio) throws SQLException{
        PreparedStatement pst;
        String sql;
        List<PatrimonioCompostoM> listaComposto = new ArrayList<PatrimonioCompostoM>();
        sql = "select * from Patrimonio_composto where id_patrimonio = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, patrimonio.getId());
        ResultSet rs = pst.executeQuery();
        GrauConservacaoDAO grau = new GrauConservacaoDAO();
        StatusDAO status = new StatusDAO();
        while(rs.next()){
           
           listaComposto.add(new PatrimonioCompostoM(rs.getInt("id"), rs.getString("descricao"), grau.busca(rs.getInt("id_grau_conservacao")), status.busca(rs.getInt("id_status")), patrimonio ));
        }
        pst.close();
        return listaComposto;
    }
    
    public void salvar(PatrimonioCompostoM patrimonioComposto) throws SQLException{
        sql = "insert into Patrimonio_composto values(?, ?, ?, ?, ?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, 0);
        pst.setString(2, patrimonioComposto.getDescricao());
        pst.setInt(3, patrimonioComposto.getGrau().getId());
        pst.setInt(4, patrimonioComposto.getStatus().getId());
        pst.setInt(5, patrimonioComposto.getPatrimonio().getId());
        pst.execute();
        pst.close();
    }
    
    public void alterar(PatrimonioCompostoM patrimonioComposto) throws SQLException{
         sql = "update Patrimonio_composto set descricao = ?, id_grau_conservacao = ?, id_status = ? where id = ?";
         pst = Conexao.getInstance().prepareStatement(sql);
         pst.setString(1, patrimonioComposto.getDescricao());
         pst.setInt(2, patrimonioComposto.getGrau().getId());
         pst.setInt(3, patrimonioComposto.getStatus().getId());
         pst.setInt(4, patrimonioComposto.getId());
         pst.execute();
         pst.close();
     }
    
    public static void excluir(PatrimonioCompostoM patrimonioComposto) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "delete from Patrimonio_composto where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, patrimonioComposto.getId());
        pst.execute();
        pst.close();
    }
    
    public PatrimonioCompostoM buscaDescricao(String descricao) throws SQLException{
        sql = "select * from Patrimonio_composto where descricao = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, descricao);
        PatrimonioCompostoM patrimonioComposto = null;
        ResultSet rs = pst.executeQuery();
        GrauConservacaoDAO grau = new GrauConservacaoDAO();
        StatusDAO status = new StatusDAO();
        PatrimonioDAO pat = new PatrimonioDAO();
        while(rs.next()){
            patrimonioComposto = new PatrimonioCompostoM(rs.getInt("id"), rs.getString("descricao"), grau.busca(rs.getInt("id_grau_conservacao")),status.busca(rs.getInt("id_status")), pat.busca(rs.getInt("id_patrimonio")) );
        }
        pst.close();
        return patrimonioComposto;
     }
    
    public static void salvarBaixado(PatrimonioCompostoM patrimonioComposto, int id_patrimonio) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "insert into Patrimonio_composto_baixado values(?, ?, ?, ?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, 0);
        pst.setString(2, patrimonioComposto.getDescricao());
        pst.setInt(3, patrimonioComposto.getGrau().getId());
        pst.setInt(4, id_patrimonio);
        pst.execute();
        pst.close();
    }
    public PatrimonioCompostoM buscaIDbaixado(int id) throws SQLException{
        sql = "select * from patrimonio_baixado where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        PatrimonioCompostoM patrimonioComposto = null;
        int aux = 0;
        ResultSet rs = pst.executeQuery();        
        while(rs.next()){
            //aux = rs.getInt("id");
           patrimonioComposto = new PatrimonioCompostoM(rs.getInt("id"),rs.getBoolean("kit"));
        }
        pst.close();
        return patrimonioComposto;
     }
    
    public List<PatrimonioCompostoM> listaTodosCompostoBaixados(int id) throws SQLException{
        
        //PreparedStatement pst;
        //String sql;
        List<PatrimonioCompostoM> listaComposto = new ArrayList<>();
        sql = "select * from patrimonio_composto_baixado where id_patrimonio = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        
        ResultSet rs = pst.executeQuery();
        GrauConservacaoDAO grau = new GrauConservacaoDAO();
        StatusDAO status = new StatusDAO();
        while(rs.next()){
           
           listaComposto.add(new PatrimonioCompostoM(rs.getInt("id"), rs.getString("descricao"), grau.busca(rs.getInt("id_grau_conservacao"))));
        }
        pst.close();
        
        return listaComposto;
    }
    
    
}
