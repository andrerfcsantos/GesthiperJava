package lei.li3.g50.utilitarios;

import java.io.Serializable;
import java.util.Objects;
import lei.li3.g50.modulos.dados.Produto;

public class ParProdutoQuantidadeComprada implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7078413263130812242L;
	private Produto produto;
    private int quantidade;

    public ParProdutoQuantidadeComprada() {
      this.produto = new Produto();
      this.quantidade = 0;
      
      
    }

    public ParProdutoQuantidadeComprada(Produto produto) {
        this.produto = produto.clone();
        this.quantidade = 0;
    }
    
    public ParProdutoQuantidadeComprada(Produto produto, int numero_compras) {
        this.produto = produto.clone();
        this.quantidade = numero_compras;
    }
    
    public ParProdutoQuantidadeComprada(ParProdutoQuantidadeComprada par) {
        this.produto = par.produto.clone();
        this.quantidade = par.quantidade;
    }

    /*
     GETTERS
     */
    public Produto getProduto() {
        return produto;
    }

    public int getQuantidadeComprada() {
        return quantidade;
    }

    /*
     SETTERS
     */
    public void setProduto(Produto produto) {
        this.produto = produto.clone();
    }

    public void setQuantidadeComprada(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public void addQuantidadeComprada(int quantidade){
        this.quantidade+=quantidade;
    }
    

    /*
     METODOS STANDARD
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.produto);
        hash = 97 * hash + this.quantidade;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final ParProdutoQuantidadeComprada other = (ParProdutoQuantidadeComprada) obj;
        return this.produto.equals(other.produto) 
                && this.quantidade==other.quantidade;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
         sb.append("ParProdutoQuantidadeComprada{");
         sb.append("produto=").append(produto);
         sb.append(", quantidade=").append(quantidade);
         sb.append('}');
        
        return sb.toString();
    }
    
    @Override
    public ParProdutoQuantidadeComprada clone(){
        return new ParProdutoQuantidadeComprada(this);
    }



}
