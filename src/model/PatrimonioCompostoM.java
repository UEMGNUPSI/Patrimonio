/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Marlon
 */
public class PatrimonioCompostoM {
    
    int id;
    String descricao;

   
    /*int id_grau_conservacao;
    int id_status;
    int id_patrimonio;
    */
    PatrimonioM patrimonio;
    GrauConservacaoM grau;
    StatusM status;
    boolean kit;
    
    public PatrimonioCompostoM(){
        
    }
    
    /*public PatrimonioCompostoM(int id, String descricao, PatrimonioM patrimonio) {
        this.id = id;
        this.descricao = descricao;
        this.id_grau_conservacao = patrimonio.getGrau_conservacao().getId();
        this.id_status = patrimonio.getStatus().getId();
        this.id_patrimonio = patrimonio.getId();
        this.patrimonio = patrimonio;
    }*/
        
     public PatrimonioCompostoM(int id, String descricao, GrauConservacaoM grau_conservacao, StatusM status, PatrimonioM patrimonio) {
        this.id = id;
        this.descricao = descricao;
        this.grau = grau_conservacao;
        this.status = status;
        this.patrimonio = patrimonio;
        
        
    }
     public PatrimonioCompostoM(int id, String descricao, GrauConservacaoM grau_conservacao, StatusM status) {
        this.id = id;
        this.descricao = descricao;
        this.grau = grau_conservacao;
        this.status = status;
        //this.patrimonio = patrimonio;
        
        
    }
      public PatrimonioCompostoM(int id, String descricao, GrauConservacaoM grau_conservacao) {
        this.id = id;
        this.descricao = descricao;
        this.grau = grau_conservacao;
    }
      public PatrimonioCompostoM(int id,boolean kit) {
        this.id = id;
        this.kit = kit;
        
    }
     public PatrimonioCompostoM(int id) {
        this.id = id;
      
  
    }
      public boolean isKit() {
        return kit;
    }

    public void setKit(boolean kit) {
        this.kit = kit;
    }
      
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public GrauConservacaoM getGrau() {
        return grau;
    }

    public void setGrau(GrauConservacaoM grau) {
        this.grau = grau;
    }

    public StatusM getStatus() {
        return status;
    }

    public void setStatus(StatusM status) {
        this.status = status;
    }
    
    public PatrimonioM getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(PatrimonioM patrimonio) {
        this.patrimonio = patrimonio;
    }
    
    /*public int getId_grau_conservacao() {
        return id_grau_conservacao;
    }

    public void setId_grau_conservacao(int id_grau_conservacao) {
        this.id_grau_conservacao = id_grau_conservacao;
    }

    public int getId_status() {
        return id_status;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    public int getId_patrimonio() {
        return id_patrimonio;
    }

    public void setId_patrimonio(int id_patrimonio) {
        this.id_patrimonio = id_patrimonio;
    }
    */
    
}
