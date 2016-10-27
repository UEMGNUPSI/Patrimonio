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
   private UsuarioM usario;
   
   public HistoricoAcaoM(){
       
   }
   
   public HistoricoAcaoM(Integer idObjeto, String tipoObjeto, String acao, String dataAcao, UsuarioM usario) {
        this.idObjeto = idObjeto;
        this.tipoObjeto = tipoObjeto;
        this.acao = acao;
        this.dataAcao = dataAcao;
        this.usario = usario;
    }
   
    public HistoricoAcaoM(String tipoObjeto, String dataAcao, UsuarioM usario) { 
        this.tipoObjeto = tipoObjeto;
        this.dataAcao = dataAcao;
        this.usario = usario;
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
        return usario;
    }

    public void setUsario(UsuarioM usario) {
        this.usario = usario;
    }

    
   
}
