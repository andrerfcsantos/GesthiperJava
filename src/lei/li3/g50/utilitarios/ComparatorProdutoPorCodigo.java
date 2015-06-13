package lei.li3.g50.utilitarios;

import java.io.Serializable;
import java.util.Comparator;

import lei.li3.g50.modulos.dados.Produto;

public class ComparatorProdutoPorCodigo implements Comparator<Produto>, Serializable {

    private static final long serialVersionUID = 4152785616288841208L;

    @Override
    public int compare(Produto p1, Produto p2) {
        return p1.getCodigoProduto().compareTo(p2.getCodigoProduto());
    }

}
