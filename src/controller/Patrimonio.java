/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.sql.SQLException;
import view.LoginView;

/**
 *
 * @author Leopoldo
 */
public class Patrimonio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
       new Patrimonio().executar();
    }
    
    public void executar(){
        LoginView login = new LoginView();
    }
    
    
    
}
