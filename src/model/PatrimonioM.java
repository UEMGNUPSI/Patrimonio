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
    private OrgaoM entidade;
    private Boolean kit;
    private Integer quantidade;

    public PatrimonioM() {
        
    }
    
    public PatrimonioM(Integer id, String descricao,String codigo,SubTipoM subTipo, GrauConservacaoM grau_conservacao, StatusM status, SalaM sala, String notaFiscal, OrgaoM entidade, Boolean kit) {        
        this.id = id;
        this.codigo = codigo;
        this.subTipo = subTipo;
        this.descricao = descricao;
        this.grau_conservacao = grau_conservacao;
        this.status = status;
        this.sala = sala;
        this.notaFiscal = notaFiscal;
        this.entidade = entidade;
        this.kit = kit;
    }
    public PatrimonioM(Integer id, String descricao,String codigo,SubTipoM subTipo, GrauConservacaoM grau_conservacao, StatusM status, SalaM sala, String notaFiscal, OrgaoM entidade, Boolean kit, Integer quantidade) {        
        this.id = id;
        this.codigo = codigo;
        this.subTipo = subTipo;
        this.descricao = descricao;
        this.grau_conservacao = grau_conservacao;
        this.status = status;
        this.sala = sala;
        this.notaFiscal = notaFiscal;
        this.entidade = entidade;
        this.kit = kit;
        this.quantidade = quantidade;
    }
    public PatrimonioM(String descricao, Integer quantidade){
        this.descricao = descricao;
        this.quantidade = quantidade;
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

    public OrgaoM getEntidade() {
        return entidade;
    }

    public void setEntidade(OrgaoM entidade) {
        this.entidade = entidade;
    }
   
    public Boolean getKit() {
        return kit;
    }

    public void setKit(Boolean kit) {
        this.kit = kit;
    }
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
