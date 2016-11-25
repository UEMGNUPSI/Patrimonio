/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.HistoricoAcaoM;
import model.UsuarioM;

/**
 *
 * @author NUPSI-03
 */
public class HistoricoAcaoDAO {
    
    PreparedStatement pst = null;
    String sql = null;

    public List<HistoricoAcaoM> listaTodos() throws SQLException{
        List<HistoricoAcaoM> retBusca = new ArrayList<>();
        sql = "select * from HistoricoAcoes";
        pst = Conexao.getInstance().prepareStatement(sql);
   
        UsuarioDAO user = new UsuarioDAO();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            
           retBusca.add(new HistoricoAcaoM(rs.getString("tipoObjeto"),
                   rs.getDate("dataAcao"),
                   user.UsuarioMById(rs.getInt("id_usuario")),
                   rs.getString("acao") ));
        }
        pst.close();
        return retBusca;
    }
    
    
    public List<HistoricoAcaoM> lista100(int ultimo) throws SQLException{
        List<HistoricoAcaoM> listaHistorico = new ArrayList<>();
        sql = "select * from HistoricoAcoes limit ?,100";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, ultimo);
        UsuarioDAO user = new UsuarioDAO();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            
           listaHistorico.add(new HistoricoAcaoM(rs.getString("tipoObjeto"),
                   rs.getDate("dataAcao"),
                   user.UsuarioMById(rs.getInt("id_usuario")),
                   rs.getString("acao") ));
        }
        pst.close();
        return listaHistorico;
    }
    
    public int contaTodos() throws SQLException{           
        sql = "select count(*) quantidade from HistoricoAcoes";
        pst = Conexao.getInstance().prepareStatement(sql);       
        ResultSet rs = pst.executeQuery();
        int quant = 0;
        while(rs.next()){
            quant = rs.getInt("quantidade");
        }        
        pst.close();        
        return quant;
    }
    
    public static void salvar(HistoricoAcaoM historico) throws SQLException{
        PreparedStatement pst;
        String sql;
      
        sql = "insert into HistoricoAcoes values(?,?,?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, 0);
        pst.setInt(2, historico.getIdObjeto());
        pst.setString(3, historico.getTipoObjeto());
        pst.setString(4, historico.getAcao());
        pst.setDate(5, historico.getDataAcao());
        pst.setInt(6, historico.getUsuario().getId());
        pst.execute();
        pst.close();
  
    }  
    
    public List<HistoricoAcaoM> buscaConcatenada(HistoricoAcaoM infoFiltro, Date inicio, Date fim, int qnt, int comb) throws SQLException{
        List<HistoricoAcaoM> retBusca = new ArrayList<>();
        String aux;
        String aux2;
        aux = "select * from HistoricoAcoes where ";
        
        //somente por 1 filtro
        if (qnt == 1){
            
            //por usuario
            if (comb == 2){
                sql = aux + "id_usuario = ?";
                pst = Conexao.getInstance().prepareStatement(sql);
                pst.setInt(1, infoFiltro.getUsuario().getId());
            }
            
            //por periodo
            if (comb == 3){
                sql = aux + "dataAcao between ? and ?";
                pst = Conexao.getInstance().prepareStatement(sql);
                pst.setDate(1, inicio);
                pst.setDate(2, fim);               
            }
            
            //por acao
            if (comb == 5){
                aux2 = "%" + infoFiltro.getAcao() + "%";
                sql = aux + "acao like ?";
                pst = Conexao.getInstance().prepareStatement(sql);
                pst.setString(1, aux2);  
            }
            
            if (comb == 7){
                aux2 = "%" + infoFiltro.getTipoObjeto() + "%";
                sql = aux + "tipoObjeto like ?";
                pst = Conexao.getInstance().prepareStatement(sql);
                pst.setString(1, aux2);   
            }
        }

        //com somente 2 filtros de busca
        if(qnt == 2){
            
            //combinacao = 5 significa usuario e periodo
            if(comb == 5){
                sql = aux + "dataAcao between ? and ? and id_usuario = ?";
                pst = Conexao.getInstance().prepareStatement(sql);
                pst.setDate(1, inicio);
                pst.setDate(2, fim);
                pst.setInt(3, infoFiltro.getUsuario().getId());
            }
            
            //combinacao = 7 significa usuario e acao
            if (comb == 7){
                aux2 = "%" + infoFiltro.getAcao() + "%";
                sql = aux + "id_usuario = ? and acao like ?";
                pst = Conexao.getInstance().prepareStatement(sql);
                pst.setInt(1, infoFiltro.getUsuario().getId());
                pst.setString(2, aux2);  
            }
            
            //combicanao = 9 usuario e descricao (tipo de objeto Sala, Piso... etc)
            if (comb == 9){
                aux2 = "%" + infoFiltro.getTipoObjeto() + "%";
                sql = aux + "id_usuario = ? and tipoObjeto like ?";
                pst = Conexao.getInstance().prepareStatement(sql);
                pst.setInt(1, infoFiltro.getUsuario().getId());
                pst.setString(2, aux2);
            }
            
            //combinacao = 8 Periodo e acao
            if (comb == 8){
                aux2 = "%" + infoFiltro.getAcao() + "%";
                sql = aux + "dataAcao between ? and ? and acao like ?";
                pst = Conexao.getInstance().prepareStatement(sql);
                pst.setDate(1, inicio);
                pst.setDate(2, fim);
                pst.setString(3, aux2);   
            }
            
            //combinacao = 10 Periodo e descricao
            if (comb == 10){
                aux2 = "%" + infoFiltro.getTipoObjeto() + "%";
                sql = aux + "dataAcao between ? and ? and tipoObjeto like ?";
                pst = Conexao.getInstance().prepareStatement(sql);
                pst.setDate(1, inicio);
                pst.setDate(2, fim);
                pst.setString(3, aux2);   
            }
            
            //com = 12 Acao e Descrição
            if (comb == 12){
                String aux3;
                aux2 = "%" + infoFiltro.getAcao() + "%";
                aux3 = "%" + infoFiltro.getTipoObjeto() + "%";
                sql = aux + "acao like ? and tipoObjeto like ?";
                pst = Conexao.getInstance().prepareStatement(sql);
                pst.setString(1, aux2);
                pst.setString(2, aux3);
            }
        }
        
        //com combinação de 3 filtros
        if (qnt == 3){
            
            //combinacao = 10 usuario, periodo e acao
            if (comb == 10){
                aux2 = "%" + infoFiltro.getAcao() + "%";
                sql = aux + "id_usuario = ? and dataAcao between ? and ? and acao like ?";
                pst = Conexao.getInstance().prepareStatement(sql);
                pst.setInt(1, infoFiltro.getUsuario().getId());
                pst.setDate(2, inicio);
                pst.setDate(3, fim);
                pst.setString(4, aux2);

            }
            
            //combinacao 12 usaurio, periodo e descricao
            if (comb == 12){
                aux2 = "%" + infoFiltro.getTipoObjeto() + "%";
                sql = aux + "id_usuario = ? and dataAcao between ? and ? and tipoObjeto like ?";
                pst = Conexao.getInstance().prepareStatement(sql);
                pst.setInt(1, infoFiltro.getUsuario().getId());
                pst.setDate(2, inicio);
                pst.setDate(3, fim);
                pst.setString(4, aux2);
            }
            
            //combinacao 14 usuario, acao e descricao
            if (comb == 14){
                String aux3;
                aux2 = "%" + infoFiltro.getAcao() + "%";
                aux3 = "%" + infoFiltro.getTipoObjeto() + "%";
                sql = aux + "id_usuario = ? and acao like ? and tipoObjeto like ?";
                pst = Conexao.getInstance().prepareStatement(sql);
                pst.setInt(1, infoFiltro.getUsuario().getId());
                pst.setString(2, aux2);
                pst.setString(3, aux3);
                
            }
            
            //combinacao 15 periodo, acao e descricao
            if (comb == 15){
                String aux3;
                aux2 = "%" + infoFiltro.getAcao() + "%";
                aux3 = "%" + infoFiltro.getTipoObjeto() + "%";
                sql = aux + "dataAcao between ? and ? and acao like ? and tipoObjeto like ?";
                pst = Conexao.getInstance().prepareStatement(sql);
                pst.setDate(1, inicio);
                pst.setDate(2, fim);
                pst.setString(3, aux2);
                pst.setString(4, aux3);
            }
            
        }
        
        if (qnt == 4){
            String aux3;
            aux2 = "%" + infoFiltro.getAcao() + "%";
            aux3 = "%" + infoFiltro.getTipoObjeto() + "%";
            sql = aux + "id_usuario = ? and dataAcao between ? and ? and acao like ? and tipoObjeto like ?";
            pst = Conexao.getInstance().prepareStatement(sql);
            pst.setInt(1, infoFiltro.getUsuario().getId());
            pst.setDate(2, inicio);
            pst.setDate(3, fim);
            pst.setString(4, aux2);
            pst.setString(5, aux3); 
        }

        UsuarioDAO user = new UsuarioDAO();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            
           retBusca.add(new HistoricoAcaoM(rs.getString("tipoObjeto"),
                   rs.getDate("dataAcao"),
                   user.UsuarioMById(rs.getInt("id_usuario")),
                   rs.getString("acao") ));
        }
        pst.close();
        
        return retBusca;
    }
    
}
