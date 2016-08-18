/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author nupsi-01
 */
public class SubTipoM {
    private Integer id;
    private String descricao;
    private TipoM tipo;

    public SubTipoM() {
    }

    public SubTipoM(Integer id, String descricao, TipoM tipoM) {
        this.id = id;
        this.descricao = descricao;
        tipo = tipoM;
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

    public TipoM getTipo() {
        return tipo;
    }

    public void setTipo(TipoM tipo) {
        this.tipo = tipo;
    }
    
    
    
}
