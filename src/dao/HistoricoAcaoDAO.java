/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
    
    public List<HistoricoAcaoM> listaTodos() throws SQLException{
        List<HistoricoAcaoM> listaHistorico = new ArrayList<HistoricoAcaoM>();
        String sql = "select * from HistoricoAcoes";
        PreparedStatement pst = Conexao.getInstance().prepareStatement(sql);
        UsuarioDAO user = new UsuarioDAO();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            
           listaHistorico.add(new HistoricoAcaoM(rs.getString("tipoObjeto"), rs.getDate("dataAcao"), user.UsuarioMById(rs.getInt("id_usuario")), rs.getString("acao") ));
        }
        pst.close();
        return listaHistorico;
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
    
    public List<HistoricoAcaoM> busca(HistoricoAcaoM historico, int qnt, int combCampos) throws SQLException{
        PreparedStatement pst = null;
        String sql = null;
        List<HistoricoAcaoM> retornoHistorico = new ArrayList<HistoricoAcaoM>();     
        //Qnt é para verificar quantos campos foram preenchidos nos filtros.
        //combCampos é uma combinação matematica para saber quais campos foram preenchidos
        //campo1 = 2, campo2 = 3, campo3 = 5, campo4 = 7
    
        if (qnt == 1){
            //se somente 1 campo tiver sido preenchido no filtro
            if (combCampos == 2){
                sql.concat(" tipoObjeto = ?");
                pst = Conexao.getInstance().prepareStatement(sql);
                pst.setString(1, historico.getTipoObjeto().toString());
            }
            if (combCampos == 3){
                sql = "select * from HistoricoAcoes where dataAcao between ? and ?";
                pst = Conexao.getInstance().prepareStatement(sql);
                pst.setDate(1, historico.getPeriodoInicio());
                pst.setDate(2, historico.getPeriodoFim());
            }
            if (combCampos == 5){
                sql = "select * from HistoricoAcoes where id_usuario = ?";
                pst = Conexao.getInstance().prepareStatement(sql);
                pst.setInt(1, historico.getUsuario().getId());
            }
            if (combCampos == 7){
                sql = "select * from HistoricoAcoes where acao = ?";
                pst = Conexao.getInstance().prepareStatement(sql);
                pst.setString(1, historico.getAcao().toString());
            }
        }
        if (qnt == 2){
            //Se somente dois campos tivem sido preenchidos
            if (combCampos == 5){
                //se a combinacao resultante for 5 a unica possibilidade sao
                //filtro de tipo e data
                sql.concat(" tipoObjeto = ? and dataAcao = ?");
                pst = Conexao.getInstance().prepareStatement(sql);
                pst.setString(1, historico.getTipoObjeto().toString());
                pst.setDate(2, historico.getDataAcao());
            }
            if (combCampos == 7){
                //se for 7 a unica possibilidade eh
                //tipo e usuario
                sql.concat(" tipoObjeto = ? and usuario = ?");
                pst = Conexao.getInstance().prepareStatement(sql);
                pst.setString(1, historico.getTipoObjeto().toString());
                pst.setInt(2, historico.getUsuario().getId());
            }
            if (combCampos == 9){
                //se for 9 a unica possibilidade eh
                //objeto e acao
                sql.concat(" tipoObjeto = ? and acao = ?");
                pst = Conexao.getInstance().prepareStatement(sql);
                pst.setString(1, historico.getTipoObjeto().toString());
                pst.setString(2, historico.getAcao().toString());
            }
            if (combCampos == 8){
                //se a combinacao for 8 a unica possibilidade eh
                //data e usuario
                sql.concat(" dataAcao = ? and usuario = ?");
                pst = Conexao.getInstance().prepareStatement(sql);
                pst.setDate(1, historico.getDataAcao());;
                pst.setInt(2, historico.getUsuario().getId());
            }
            if (combCampos == 10){
                //se a combinacao for 8 a unica possibilidade eh
                //data e acao
                sql.concat(" dataAcao = ? and acao = ?");
                pst = Conexao.getInstance().prepareStatement(sql);
                pst.setDate(1, historico.getDataAcao());
                pst.setString(2, historico.getAcao().toString());
            }
            if (combCampos == 12){
                //se a combinacao for 8 a unica possibilidade eh
                //usario e acao
                sql.concat(" usuario = ? and acao = ?");
                pst = Conexao.getInstance().prepareStatement(sql);
                pst.setInt(1, historico.getUsuario().getId());
                pst.setString(2, historico.getAcao().toString());
            }
               
        }
        
        if (qnt == 3){
            //Se somente 3 campos tivem sido preenchidos
            if (combCampos == 10){
                   //se a combinacao for 10 a unica possibilidade eh
                   //objeto, data e usuario
                   sql.concat(" tipoObjeto = ? and dataAcao = ? and usuario = ?");
                   pst = Conexao.getInstance().prepareStatement(sql);
                   pst.setString(1, historico.getTipoObjeto().toString());
                   pst.setDate(2, historico.getDataAcao());
                   pst.setInt(3, historico.getUsuario().getId());
            }
            if (combCampos == 12){
                   //se a combinacao for 12 a unica possibilidade eh
                   //objeto, data e acao
                   sql.concat(" tipoObjeto = ? and dataAcao = ? and acao = ?");
                   pst = Conexao.getInstance().prepareStatement(sql);
                   pst.setString(1, historico.getTipoObjeto().toString());
                   pst.setDate(2, historico.getDataAcao());
                   pst.setString(3, historico.getAcao().toString());
            }
            if (combCampos == 14){
                   //se a combinacao for 14 a unica possibilidade eh
                   //objeto, usuario e acao
                   sql.concat(" tipoObjeto = ? and usuario = ? and acao = ?");
                   pst = Conexao.getInstance().prepareStatement(sql);
                   pst.setString(1, historico.getTipoObjeto().toString());
                   pst.setInt(2, historico.getUsuario().getId());
                   pst.setString(3, historico.getAcao().toString());
            }
            if (combCampos == 15){
                   //se a combinacao for 14 a unica possibilidade eh
                   //data, usuario e acao
                   sql.concat(" data = ? and usuario = ? and acao = ?");
                   pst = Conexao.getInstance().prepareStatement(sql);
                   pst.setDate(1, historico.getDataAcao());
                   pst.setInt(2, historico.getUsuario().getId());
                   pst.setString(3, historico.getAcao().toString());
            }
               
        }
           
        if(qnt == 4){
            //se os 4 campos estiverem preenchidos
            sql.concat(" objeto = ? and data = ? and usuario = ? and acao = ?");
            pst = Conexao.getInstance().prepareStatement(sql);
            pst.setString(1, historico.getTipoObjeto().toString());
            pst.setString(2, historico.getDataAcao().toString());
            pst.setInt(3, historico.getUsuario().getId());
            pst.setString(4, historico.getAcao().toString());
        }
        
        ResultSet rs = pst.executeQuery();
        UsuarioDAO user = new UsuarioDAO();
        while(rs.next()){
            
            retornoHistorico.add(new HistoricoAcaoM(rs.getString("tipoObjeto"), rs.getDate("dataAcao"), user.UsuarioMById(rs.getInt("id_usuario")), rs.getString("acao")));
        }
        pst.close();
        return retornoHistorico;
     }
    
    
    
}
