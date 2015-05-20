package lei.li3.g50.modulos.dados.catalogo.produtos;

public class Produto {
    private String codigo_produto;
    
    /*
    CONSTRUCTORES
    */
    
    public Produto(){
        codigo_produto = "";
    }
    
    public Produto(String cod_produto){
        codigo_produto = cod_produto;
    }
    
    public Produto(Produto produto){
        codigo_produto = produto.getCodigoProduto();
    }
    
    /*
    SETTERS E GETTERS
    */
    public String getCodigoProduto() {
        return codigo_produto;
    }

    public void setCodigoProduto(String codigo_produto) {
        this.codigo_produto = codigo_produto;
    }

    /*
    METODOS PRODUTO
    */
    
        public boolean equals(Produto produto){
        return this.codigo_produto.equals(produto.getCodigoProduto());
    }
    
    
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;
        Produto produto = (Produto) obj;
        return this.equals(produto);
    }
    
    @Override
    public Produto clone(){
        return new Produto(this);
    }
    
    @Override
    public String toString() {
        return "Produto{" + "codigo_produto=" + codigo_produto + '}';
    }

}