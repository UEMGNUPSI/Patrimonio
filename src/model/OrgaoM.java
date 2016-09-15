/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author nupsi-02
 */
public class OrgaoM {
    private Integer id;
    private String nome;
    private String cnpj;
    private String contato;

    public OrgaoM() {
    }

    public OrgaoM(Integer id, String nome, String cnpj, String contato) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.contato = contato;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
    
    
}
