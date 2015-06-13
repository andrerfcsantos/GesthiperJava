package lei.li3.g50.utilitarios;

import java.io.Serializable;
import java.util.Comparator;

public class ComparatorTriploClienteQtdCompradaDinheiro implements Comparator<TriploClienteQtdCompradaDinheiro>, Serializable {

    private static final long serialVersionUID = 1580155894893516193L;

    @Override
    public int compare(TriploClienteQtdCompradaDinheiro p1, TriploClienteQtdCompradaDinheiro p2) {

        if (p1.getQuantidadeComprada() < p2.getQuantidadeComprada()) {
            return 1;
        }

        if (p1.getQuantidadeComprada() > p2.getQuantidadeComprada()) {
            return -1;
        }
        /*
         Neste ponto o número de compras é o mesmo, ordenar por cliente.
         */
        return p1.getCliente().getCodigoCliente().compareTo(p2.getCliente().getCodigoCliente());

    }

}
