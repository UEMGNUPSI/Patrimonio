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

import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


public class Conexao implements Serializable {

    private static Conexao conexao = null;
    private static Connection connection;
    private String usuario;
    private String senha;
    private String url;

    private Conexao() {
        
        try {
            String currentDir = System.getProperty("user.dir");
            Path c = Paths.get(currentDir + "/conexao.txt");
            List<String> texto = Files.readAllLines(c, Charset.defaultCharset());

            int tamanho0 = texto.get(0).length();
            int tamanho1 = texto.get(1).length();
            int tamanho2 = texto.get(2).length();
            int i0 = texto.get(0).indexOf('"');
            int i1 = texto.get(1).indexOf('"');
            int i2 = texto.get(2).indexOf('"');
            String r0 = texto.get(0).substring(i0 + 1, tamanho0);
            String r1 = texto.get(1).substring(i1 + 1, tamanho1);
            String r2 = texto.get(2).substring(i2 + 1, tamanho2);
            int i02 = r0.indexOf('"');
            int i12 = r1.indexOf('"');
            int i22 = r2.indexOf('"');
            usuario = r0.substring(0, i02);
            senha = r1.substring(0, i12);
            url = r2.substring(0, i22);

        } catch (Exception erro) {
            erro.printStackTrace();
        }
    
        // Defina aqui o nome do seu banco de dados
        

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
        }
    }

    public static Connection getInstance() {
        if (conexao == null) {
            synchronized (Conexao.class) {
                conexao = new Conexao();
            }
        }
        return connection;
    }

    public static void closeInstance() throws SQLException {
        if (conexao != null) {
            connection.close();
        }
    }

    public static void setAutoCommit(boolean vlr) throws SQLException {
        connection.setAutoCommit(vlr);
    }

    public static void commit() throws SQLException {
        connection.commit();
    }

    public static void rollback() throws SQLException {
        connection.rollback();
    }
}

