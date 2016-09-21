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
    int id_grau_conservacao;
    int id_status;
    int id_patrimonio;
    PatrimonioM patrimonio;

    public PatrimonioCompostoM(){
        
    }
    
    public PatrimonioCompostoM(int id, String descricao, PatrimonioM patrimonio) {
        this.id = id;
        this.descricao = descricao;
        this.id_grau_conservacao = patrimonio.getGrau_conservacao().getId();
        this.id_status = patrimonio.getStatus().getId();
        this.id_patrimonio = patrimonio.getId();
        this.patrimonio = patrimonio;
    }
    
     public PatrimonioCompostoM(int id, String descricao, int id_grau_conservacao, int id_status, int id_patrimonio) {
        this.id = id;
        this.descricao = descricao;
        this.id_grau_conservacao = id_grau_conservacao;
        this.id_status = id_status;
        this.id_patrimonio = id_patrimonio;
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

    public int getId_grau_conservacao() {
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
    
    public PatrimonioM getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(PatrimonioM patrimonio) {
        this.patrimonio = patrimonio;
    }
    
}
