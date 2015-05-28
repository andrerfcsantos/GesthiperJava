package lei.li3.g50.utilitarios;

import java.util.Comparator;

import lei.li3.g50.modulos.dados.Produto;

public class ComparatorProdutoPorCodigo implements Comparator<Produto> {

    @Override
    public int compare(Produto p1, Produto p2) {
        return p1.getCodigoProduto().compareTo(p2.getCodigoProduto());
    }

}
