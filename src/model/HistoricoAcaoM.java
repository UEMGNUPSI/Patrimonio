/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author NUPSI-03
 */
public class HistoricoAcaoM {
    
    private Integer id;
    private Integer idObjeto;
    private String tipoObjeto;
    private String acao;
    private Date dataAcao;
    private UsuarioM usuario;
     
    public HistoricoAcaoM(){
       
    }
   
    public HistoricoAcaoM(Integer id, Integer idObjeto, String tipoObjeto, String acao, Date dataAcao, UsuarioM usuario) {
        this.id = id;
        this.idObjeto = idObjeto;
        this.tipoObjeto = tipoObjeto;
        this.acao = acao;
        this.dataAcao = dataAcao;
        this.usuario = usuario;
    }
    
    
    public HistoricoAcaoM(String tipoObjeto, Date dataAcao, UsuarioM usuario, String acao) {
        this.tipoObjeto = tipoObjeto;
        this.acao = acao;
        this.dataAcao = dataAcao;
        this.usuario = usuario;
    }
 
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(Integer idObjeto) {
        this.idObjeto = idObjeto;
    }

    public String getTipoObjeto() {
        return tipoObjeto;
    }

    public void setTipoObjeto(String tipoObjeto) {
        this.tipoObjeto = tipoObjeto;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public UsuarioM getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioM usuario) {
        this.usuario = usuario;
    }
    
    public Date getDataAcao() {
        return dataAcao;
    }

    public void setDataAcao(Date dataAcao) {
        this.dataAcao = dataAcao;
    }
   
}
