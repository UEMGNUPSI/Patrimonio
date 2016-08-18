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
public class PatrimonioM {
    private Integer id;
    private String codigo;
    private SubTipoM subTipo;
    private String descricao;
    private GrauConservacaoM grau_conservacao;
    private StatusM status;
    private SalaM sala;
    private String notaFiscal;
    private EntidadeM entidade;

    public PatrimonioM() {
        
    }

    public PatrimonioM(Integer id, String codigo,String descricao,SubTipoM subTipo, GrauConservacaoM grau_conservacao, StatusM status, SalaM sala, String notaFiscal, EntidadeM entidade) {        this.id = id;
        this.codigo = codigo;
        this.subTipo = subTipo;
        this.descricao = descricao;
        this.grau_conservacao = grau_conservacao;
        this.status = status;
        this.sala = sala;
        this.notaFiscal = notaFiscal;
        this.entidade = entidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public SubTipoM getSubTipo() {
        return subTipo;
    }

    public void setSubTipo(SubTipoM subTipo) {
        this.subTipo = subTipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public GrauConservacaoM getGrau_conservacao() {
        return grau_conservacao;
    }

    public void setGrau_conservacao(GrauConservacaoM grau_conservacao) {
        this.grau_conservacao = grau_conservacao;
    }

    public StatusM getStatus() {
        return status;
    }

    public void setStatus(StatusM status) {
        this.status = status;
    }

    public SalaM getSala() {
        return sala;
    }

    public void setSala(SalaM sala) {
        this.sala = sala;
    }

    public String getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(String notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public EntidadeM getEntidade() {
        return entidade;
    }

    public void setEntidade(EntidadeM entidade) {
        this.entidade = entidade;
    }
   
    
}
