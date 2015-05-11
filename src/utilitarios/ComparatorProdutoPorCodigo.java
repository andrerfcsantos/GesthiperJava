package utilitarios;

import dados.Produto;
import java.util.Comparator;

public class ComparatorProdutoPorCodigo implements Comparator<Produto> {

    @Override
    public int compare(Produto p1, Produto p2) {
        return p1.getCodigoProduto().compareTo(p2.getCodigoProduto());
    }

}
