/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author NUPSI-01
 */
public class UsuarioM {
    private Integer id;
    private String usuario;
    private String senha;
    private boolean admin;
    private String nome;
    private String masp;   
    private String contato;

    
    
    public UsuarioM(){
        
    }
    public UsuarioM(Integer id, String usuario, String senha,boolean admin,String nome,String masp, String contato){
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
        this.admin = admin;
        this.nome = nome;
        this.masp = masp;
        this.contato = contato;
    }
     public UsuarioM(Integer id, String usuario,boolean admin,String nome,String masp, String contato){
        this.id = id;
        this.usuario = usuario;
        this.admin = admin;
        this.nome = nome;
        this.masp = masp;
        this.contato = contato;
    }
     

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
    
    
    
    
}
