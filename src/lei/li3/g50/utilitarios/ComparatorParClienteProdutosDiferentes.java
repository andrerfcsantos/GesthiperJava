package lei.li3.g50.utilitarios;

import java.io.Serializable;
import java.util.Comparator;


public class ComparatorParClienteProdutosDiferentes implements Comparator<ParClienteProdutosDiferentes>, Serializable{

    @Override
    public int compare(ParClienteProdutosDiferentes p1, ParClienteProdutosDiferentes p2) {

        if (p1.getProdutosDiferentes() < p2.getProdutosDiferentes()) {
            return 1;
        }

        if (p1.getProdutosDiferentes() > p2.getProdutosDiferentes()) {
            return -1;
        }
        /*
         Neste ponto o número de compras é o mesmo, ordenar por cliente.
         */
        return p1.getCliente().getCodigoCliente().compareTo(p2.getCliente().getCodigoCliente());
    }

    
}