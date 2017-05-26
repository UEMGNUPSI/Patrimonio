package model;


public class BlocoM {
    private Integer id;
    private String descricao;
    private UnidadeM unidade;
    
    public BlocoM(){
        
    }
    
    public BlocoM(Integer id, String descricao,UnidadeM unidade){
        this.id = id;
        this.descricao = descricao;
        this.unidade = unidade;
        
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

    public UnidadeM getUnidadeM() {
        return unidade;
    }

    public void setUnidadeM(UnidadeM unidade) {
        this.unidade = unidade;
    }
     
    
    
    
}
