package model;


public class BaixadoM {
    private Integer id;
    private String descricao;
    private String codigo;
    private SubTipoM subTipo;
    private GrauConservacaoM grau_conservacao;
    private String notaFiscal;
    private OrgaoM entidade;
    private Boolean kit;

    public BaixadoM(Integer id, String descricao, String codigo, SubTipoM subTipo, GrauConservacaoM grau_conservacao, String notaFiscal, OrgaoM entidade, Boolean kit) {
        this.id = id;
        this.descricao = descricao;
        this.codigo = codigo;
        this.subTipo = subTipo;
        this.grau_conservacao = grau_conservacao;
        this.notaFiscal = notaFiscal;
        this.entidade = entidade;
        this.kit = kit;
    }

    public BaixadoM() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public GrauConservacaoM getGrau_conservacao() {
        return grau_conservacao;
    }

    public void setGrau_conservacao(GrauConservacaoM grau_conservacao) {
        this.grau_conservacao = grau_conservacao;
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
    
}
