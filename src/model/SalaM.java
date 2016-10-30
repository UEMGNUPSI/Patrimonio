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
public class SalaM {
    private Integer id;
    private String descricao;
    private PisoM piso;
    private Integer inventario;
    
    public SalaM(){
        
    }
    
    public SalaM(Integer id, String descricao, PisoM piso){
        this.id = id;
        this.descricao = descricao;
        this.piso = piso;
    }

    public SalaM(Integer id, String descricao, PisoM piso, Integer inventario) {
        this.id = id;
        this.descricao = descricao;
        this.piso = piso;
        this.inventario = inventario;
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

    public PisoM getPiso() {
        return piso;
    }

    public void setPiso(PisoM piso) {
        this.piso = piso;
    }

    public Integer getInventario() {
        return inventario;
    }

    public void setInventario(Integer inventario) {
        this.inventario = inventario;
    }
    
    
    
    
    
    
}
