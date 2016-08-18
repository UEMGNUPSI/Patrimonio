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
public class PisoM {
    private Integer id;
    private String descricao;
    private BlocoM bloco;

    public PisoM(){
        
    }
    
    public PisoM(Integer id, String descricao, BlocoM bloco){
        this.id = id;
        this.descricao = descricao;
        this.bloco = bloco;
    }
   
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BlocoM getBloco() {
        return bloco;
    }

    public void setBloco(BlocoM bloco) {
        this.bloco = bloco;
    }
    
    
}
