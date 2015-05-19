package modulos;

import dados.Produto;
import java.util.ArrayList;
import java.util.TreeSet;
import utilitarios.ComparatorProdutoPorCodigo;

public class CatalogoProdutos {
    
    private ArrayList<TreeSet<Produto>> catalogo;
    
    
    public CatalogoProdutos(){
        TreeSet<Produto> arvore;
        catalogo = new ArrayList<>(27);
        
        for(int i=0;i<27;i++){
            arvore = new TreeSet<>(new ComparatorProdutoPorCodigo());
            catalogo.add(i, arvore);
        }
        
    }
    
    public void insere_produto(Produto produto){
        int indice = calcula_indice(produto.getCodigoProduto().charAt(0));
        catalogo.get(indice).add(produto.clone());
    }
    
    public void remove_produto(Produto produto){
        int indice = calcula_indice(produto.getCodigoProduto().charAt(0));
        catalogo.get(indice).remove(produto);
    }
    
    public boolean existeProduto(Produto produto){
        int indice = calcula_indice(produto.getCodigoProduto().charAt(0));
        return catalogo.get(indice).contains(produto);
    }
    
    public int getNumeroProdutosTotal(){
        int total = 0;
        
        for(TreeSet<Produto> arvore_letra : catalogo){
            total += arvore_letra.size();
        }
        
        return total;
    }
    
    public int getNumeroProdutosLetra(char l){
        int indice = calcula_indice(l);
        return catalogo.get(indice).size();
    }
    
    private int calcula_indice(char l) {
        char letra = Character.toUpperCase(l);
        int res = Character.isAlphabetic(letra) ? letra - 'A' : 26;
        return res;
    }

}