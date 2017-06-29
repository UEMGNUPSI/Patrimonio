package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.HistoricoAcaoM;


public class HistoricoAcaoDAO {
    
    PreparedStatement pst = null;
    String sql = null;
    int quantidade =0;
    
    public List<HistoricoAcaoM> lista100(int ultimo) throws SQLException{
        List<HistoricoAcaoM> listaHistorico = new ArrayList<>();
        sql = "select * from HistoricoAcoes limit ?, 100";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, ultimo);
        UsuarioDAO user = new UsuarioDAO();
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            listaHistorico.add(new HistoricoAcaoM(rs.getString("tipoObjeto"),
            rs.getDate("dataAcao"),
            user.UsuarioMById(rs.getInt("id_usuario")),
            rs.getString("acao"),
            rs.getString("codigo")));
     
        }
        pst.close();
        return listaHistorico;
    }
    
    public static void salvar(HistoricoAcaoM historico) throws SQLException{
        PreparedStatement pst;
        String sql;

        sql = "insert into HistoricoAcoes values(?,?,?,?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, 0);
        pst.setInt(2, historico.getIdObjeto());
        pst.setString(3, historico.getTipoObjeto());
        pst.setString(4, historico.getAcao());
        pst.setDate(5, historico.getDataAcao());
        pst.setInt(6, historico.getUsuario().getId());
        pst.setString(7, historico.getCodigo());
        pst.execute();
        pst.close();
  
    } 
    
    public List<HistoricoAcaoM> buscaNova100(HistoricoAcaoM filtros, Date inicio, Date fim, int limiteInicio) throws SQLException{
        List<HistoricoAcaoM> lista = new ArrayList<>();
        String auxAcao = "%"+filtros.getAcao()+"%";
        String auxTipoObjeto = "%"+filtros.getTipoObjeto()+"%";
        String auxCodigo = "%"+filtros.getCodigo()+"%";
        
        if (inicio == null){
            sql = "select * from HistoricoAcoes where id_usuario like ? and acao like ? and tipoObjeto like ? and codigo like ? limit ?, 100";
            pst = Conexao.getInstance().prepareStatement(sql);
            pst.setInt(1, filtros.getUsuario().getId());
            pst.setString(2, auxAcao );
            pst.setString(3, auxTipoObjeto);
            pst.setString(4, auxCodigo);
            pst.setInt(5, limiteInicio);
        }
        else if (filtros.getUsuario() == null){
            sql = "select * from HistoricoAcoes where dataAcao BETWEEN ? and ? and acao like ? and tipoObjeto like ? and codigo like ? limit ?, 100";
            pst = Conexao.getInstance().prepareStatement(sql);
            pst.setDate(1, inicio);
            pst.setDate(2, fim);
            pst.setString(3, auxAcao);
            pst.setString(4, auxTipoObjeto);
            pst.setString(5, auxCodigo);
            pst.setInt(6, limiteInicio);
            
        }else { 
            sql = "select * from HistoricoAcoes where id_usuario like ? and dataAcao BETWEEN ? and ? and acao like ? and tipoObjeto like ? and codigo like ? limit ?, 100";
            pst = Conexao.getInstance().prepareStatement(sql);
            pst.setInt(1, filtros.getUsuario().getId());
            pst.setDate(2, inicio);
            pst.setDate(3, fim);
            pst.setString(4, auxAcao);
            pst.setString(5, auxTipoObjeto);
            pst.setString(6, auxCodigo);
            pst.setInt(7, limiteInicio);
        }
        UsuarioDAO user = new UsuarioDAO();
        ResultSet rs = pst.executeQuery();
        
        
        while(rs.next()){
            lista.add(new HistoricoAcaoM(rs.getString("tipoObjeto"),
            rs.getDate("dataAcao"),
            user.UsuarioMById(rs.getInt("id_usuario")),
            rs.getString("acao"),
            rs.getString("codigo")));
            
          
        }
        return lista;
    }
    
    public int buscaQuantidade(HistoricoAcaoM filtros, Date inicio, Date fim, int limiteInicio) throws SQLException{
        String auxAcao = "%"+filtros.getAcao()+"%";
        String auxTipoObjeto = "%"+filtros.getTipoObjeto()+"%";
        String auxCodigo = "%"+filtros.getCodigo()+"%";
       
        if (inicio == null){
            sql = "select count(*) quant from HistoricoAcoes where id_usuario like ? and acao like ? and tipoObjeto like ? and codigo like ?";
            pst = Conexao.getInstance().prepareStatement(sql);
            pst.setInt(1, filtros.getUsuario().getId());
            pst.setString(2, auxAcao );
            pst.setString(3, auxTipoObjeto);
            pst.setString(4, auxCodigo);
        }
        else if (filtros.getUsuario() == null){
            sql = "select count(*) quant from HistoricoAcoes where dataAcao BETWEEN ? and ? and acao like ? and tipoObjeto like ? and codigo like ?";
            pst = Conexao.getInstance().prepareStatement(sql);
            pst.setDate(1, inicio);
            pst.setDate(2, fim);
            pst.setString(3, auxAcao);
            pst.setString(4, auxTipoObjeto);
            pst.setString(5, auxCodigo);
            
        }else { 
            sql = "select count(*) quant from HistoricoAcoes where id_usuario like ? and dataAcao BETWEEN ? and ? and acao like ? and tipoObjeto like ? and codigo like ?";
            pst = Conexao.getInstance().prepareStatement(sql);
            pst.setInt(1, filtros.getUsuario().getId());
            pst.setDate(2, inicio);
            pst.setDate(3, fim);
            pst.setString(4, auxAcao);
            pst.setString(5, auxTipoObjeto);
            pst.setString(6, auxCodigo);
        }
        UsuarioDAO user = new UsuarioDAO();
        ResultSet rs = pst.executeQuery();
        
        
        while(rs.next()){
            quantidade = rs.getInt("quant");
            
          
        }
        return quantidade;
    }
    
    public int quantidadeTodos() throws SQLException{
         sql = "select count(*) quant from HistoricoAcoes";
         pst = Conexao.getInstance().prepareStatement(sql);
         ResultSet rs = pst.executeQuery();
          while(rs.next()){
            quantidade = rs.getInt("quant");
          
        }
          
          return quantidade;
    }
    
    
    
}
