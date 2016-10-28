/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author NUPSI-03
 */
public class HistoricoAcaoM {
    
   private Integer id;
   private Integer idObjeto;
   private String tipoObjeto;
   private String acao;
   private String dataAcao;
   private UsuarioM usuario;
   
   public HistoricoAcaoM(){
       
   }
   
   public HistoricoAcaoM(Integer idObjeto, String tipoObjeto, String acao, String dataAcao, UsuarioM usuario) {
        this.idObjeto = idObjeto;
        this.tipoObjeto = tipoObjeto;
        this.acao = acao;
        this.dataAcao = dataAcao;
        this.usuario = usuario;
   }
   
   public HistoricoAcaoM(String tipoObjeto, String dataAcao, UsuarioM usuario, String acao) { 
        this.tipoObjeto = tipoObjeto;
        this.dataAcao = dataAcao;
        this.usuario = usuario;
        this.acao = acao;
   }
    
   public HistoricoAcaoM(String tipoObjeto, String dataAcao, UsuarioM usuario) { 
        this.tipoObjeto = tipoObjeto;
        this.dataAcao = dataAcao;
        this.usuario = usuario;
    }
    
    public HistoricoAcaoM(String tipoObjeto, String dataAcao) { 
        this.tipoObjeto = tipoObjeto;
        this.dataAcao = dataAcao;
    }
    
    public HistoricoAcaoM(String tipoObjeto) { 
        this.tipoObjeto = tipoObjeto;
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

    public String getDataAcao() {
        return dataAcao;
    }

    public void setDataAcao(String dataAcao) {
        this.dataAcao = dataAcao;
    }

    public UsuarioM getUsario() {
        return usuario;
    }

    public void setUsario(UsuarioM usario) {
        this.usuario = usario;
    }

    
   
}
