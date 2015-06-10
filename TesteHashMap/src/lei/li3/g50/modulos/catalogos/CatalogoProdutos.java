package lei.li3.g50.modulos.catalogos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

import lei.li3.g50.modulos.dados.Produto;
import lei.li3.g50.utilitarios.ComparatorProdutoPorCodigo;

public class CatalogoProdutos implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1697318332425633837L;
	private ArrayList<TreeSet<Produto>> catalogo;

    /*
     CONSTRUCTORES
     */
    public CatalogoProdutos() {
        TreeSet<Produto> arvore;
        catalogo = new ArrayList<>(27);

        for (int i = 0; i < 27; i++) {
            arvore = new TreeSet<>(new ComparatorProdutoPorCodigo());
            catalogo.add(i, arvore);
        }

    }

    public CatalogoProdutos(Collection<Produto> coleccao) {
        this();
        
        //FIXME var não usada
        int i, indice;
        for (Produto produto : coleccao) {
            indice = calcula_indice(produto.getCodigoProduto().charAt(0));
            catalogo.get(indice).add(produto.clone());
        }

    }

    public CatalogoProdutos(CatalogoProdutos cat) {
        this();
        int i, indice;
        for (i = 0; i < cat.catalogo.size(); i++) {
            for (Produto produto : cat.catalogo.get(i)) {
                indice = calcula_indice(produto.getCodigoProduto().charAt(0));
                catalogo.get(indice).add(produto.clone());
            }
        }
    }

    /*
     GETTERS
     */
    public List<Produto> getProdutos() {
        ArrayList<Produto> resultado = new ArrayList<>();

        for (TreeSet<Produto> arvore_produtos : this.catalogo) {
            for (Produto produto : arvore_produtos) {
                resultado.add(produto.clone());
            }
        }

        return (List<Produto>) resultado;
    }

    public List<Produto> getProdutosComecadosPorLetra(char letra) {
        ArrayList<Produto> resultado = new ArrayList<>();
        int indice = calcula_indice(letra);

        for (Produto produto : this.catalogo.get(indice)) {
            resultado.add(produto.clone());
        }

        return (List<Produto>) resultado;
    }

    public int getNumeroProdutosTotal() {
        int total = 0;

        for (TreeSet<Produto> arvore_letra : catalogo) {
            total += arvore_letra.size();
        }

        return total;
    }

    public int getNumeroProdutosLetra(char l) {
        int indice = calcula_indice(l);
        return catalogo.get(indice).size();
    }

    /*
     METODOS INSTANCIA
     */
    public void insereProduto(Produto produto) {
        int indice = calcula_indice(produto.getCodigoProduto().charAt(0));
        catalogo.get(indice).add(produto.clone());
    }

    public void remove_produto(Produto produto) {
        int indice = calcula_indice(produto.getCodigoProduto().charAt(0));
        catalogo.get(indice).remove(produto);
    }

    public boolean existeProduto(Produto produto) {
        int indice = calcula_indice(produto.getCodigoProduto().charAt(0));
        return catalogo.get(indice).contains(produto);
    }

    private int calcula_indice(char l) {
        char letra = Character.toUpperCase(l);
        int res = Character.isAlphabetic(letra) ? letra - 'A' : 26;
        return res;
    }

    private boolean equalsCatalogos(CatalogoProdutos cat) {
        int i;

        /*
         Tem o mesmo numero de arvores?
         */
        if (this.catalogo.size() != cat.catalogo.size()) {
            return false;
        }

        /*
         Temdo o mesmo número de arvores, as arvores têm o mesmo tamanho?
         */
        for (i = 0; i < this.catalogo.size(); i++) {
            if (this.catalogo.get(i).size() == cat.catalogo.get(i).size()) {
                return false;
            }
        }

        /*
         Os elementos em cada um dos TreeSet sao os mesmos?
         */
        for (i = 0; i < this.catalogo.size(); i++) {
            if (!this.catalogo.get(i).containsAll(cat.catalogo.get(i))) {
                return false;
            }
        }

        return true;
    }

    /*
     METODOS STANDARD
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.catalogo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CatalogoProdutos other = (CatalogoProdutos) obj;
        return this.equalsCatalogos(other);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("CatalogoProdutos {");

        for (char i = 'A'; i <= 'Z'; i++) {
            sb.append(i).append(": ").append(this.getNumeroProdutosLetra(i));
            sb.append(" ");
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public CatalogoProdutos clone() {
        return new CatalogoProdutos(this);
    }
}
