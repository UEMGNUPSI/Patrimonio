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
import model.UsuarioM;

/**
 *
 * @author NUPSI-01
 */
public class UsuarioDAO {
    
    PreparedStatement pst;
    String sql;
    public UsuarioM valida(String user, String senha) throws SQLException{
           sql = "select * from Usuario where usuario = ? and senha = ?";
           pst = Conexao.getInstance().prepareStatement(sql);
           pst.setString(1, user);
           pst.setString(2, senha);
           UsuarioM usuario = null;
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               usuario = new UsuarioM(rs.getInt("id"),rs.getString("usuario"), rs.getString("senha"), rs.getBoolean("admin"),
                       rs.getString("nome"), rs.getString("masp"), rs.getString("contato"));
            }
            pst.close();
            return usuario;
    }
    
    public UsuarioM buscaNome(String nome) throws SQLException{
        sql = "select * from Usuario where nome = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, nome);
        UsuarioM user = null;
        BlocoDAO bloco = new BlocoDAO();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            user = new UsuarioM(rs.getInt("id"),rs.getString("usuario"), rs.getString("senha"), rs.getBoolean("admin"),
                       rs.getString("nome"), rs.getString("masp"), rs.getString("contato"));
           
        }
        pst.close();
        return user;
    }
    
    public List<UsuarioM> listaTodos() throws SQLException{
        List<UsuarioM> listaUser = new ArrayList<UsuarioM>();
        sql = "select * from Usuario";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
           listaUser.add(new UsuarioM(rs.getInt("id"),rs.getString("usuario"), rs.getBoolean("admin"),
                   rs.getString("nome"), rs.getString("masp"), rs.getString("contato")));
        }
        pst.close();
        return listaUser;
    }
       public void salvar(UsuarioM usuario) throws SQLException{
        sql = "insert into Usuario values(?,?,?,?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, 0);
        pst.setString(2, usuario.getUsuario());
        pst.setString(3, usuario.getSenha());
        pst.setBoolean(4,usuario.isAdmin());
        pst.setString(5, usuario.getNome());
        pst.setString(6, usuario.getMasp());
        pst.setString(7, usuario.getContato());
        pst.execute();
        pst.close();
    }   
        public void excluir(UsuarioM usuario) throws SQLException{
        sql = "delete from Usuario where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, usuario.getId());
        pst.execute();
        pst.close();
    }
        public void alterar(UsuarioM usuario) throws SQLException{
         sql = "update Usuario set usuario = ?, senha = ?, admin = ?, masp = ?, nome = ?, contato = ? where id = ?";
         pst = Conexao.getInstance().prepareStatement(sql);
         pst.setString(1, usuario.getUsuario());
         pst.setString(2, usuario.getSenha());
         pst.setBoolean(3, usuario.isAdmin());
         pst.setString(4, usuario.getMasp());
         pst.setString(5, usuario.getNome());
         pst.setString(6, usuario.getContato());
         pst.setInt(7, usuario.getId());
         pst.execute();
         pst.close();
     } 
        public UsuarioM UsuarioMById(int id) throws SQLException {
        UsuarioM usuario = null;
        sql = "Select * from usuario where id=?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        pst.executeQuery();
        ResultSet rs = pst.getResultSet();
        while(rs.next()){
               usuario = new UsuarioM(rs.getInt("id"),rs.getString("usuario"), rs.getString("senha"), rs.getBoolean("admin"),
                       rs.getString("nome"), rs.getString("masp"), rs.getString("contato"));
        }
        pst.close();
        return usuario;
    }
}
