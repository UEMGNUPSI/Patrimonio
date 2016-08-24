/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.UsuarioM;

/**
 *
 * @author NUPSI-01
 */
public class UsuarioDAO {
    
    PreparedStatement pst;
    String sql;
    
    public UsuarioM busca(String user, String senha) throws SQLException{
           sql = "select * from Usuario where usuario = ? and senha = ?";
           pst = Conexao.getInstance().prepareStatement(sql);
           pst.setString(1, user);
           pst.setString(2, senha);
           UsuarioM usuario = null;
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               usuario = new UsuarioM(rs.getInt("id"),rs.getString("usuario"), rs.getString("senha"), rs.getBoolean("admin"));
            }
            pst.close();
            return usuario;
     }
    
}
