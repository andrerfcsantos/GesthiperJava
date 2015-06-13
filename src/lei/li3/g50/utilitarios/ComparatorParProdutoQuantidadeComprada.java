package lei.li3.g50.utilitarios;

import java.io.Serializable;
import java.util.Comparator;

public class ComparatorParProdutoQuantidadeComprada implements Comparator<ParProdutoQuantidadeComprada>, Serializable {

    private static final long serialVersionUID = -2610068152791665204L;

    @Override
    public int compare(ParProdutoQuantidadeComprada p1, ParProdutoQuantidadeComprada p2) {

        if (p1.getQuantidadeComprada() < p2.getQuantidadeComprada()) {
            return 1;
        }

        if (p1.getQuantidadeComprada() > p2.getQuantidadeComprada()) {
            return -1;
        }
        /*
         Neste ponto o número de compras é o mesmo, ordenar por produto.
         */
        return p1.getProduto().getCodigoProduto().compareTo(p2.getProduto().getCodigoProduto());

    }

}
