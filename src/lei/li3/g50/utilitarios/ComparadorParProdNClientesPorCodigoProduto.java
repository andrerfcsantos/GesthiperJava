package lei.li3.g50.utilitarios;

import java.util.Comparator;
import lei.li3.g50.modulos.compras.ParProdutoNClientes;

public class ComparadorParProdNClientesPorCodigoProduto implements Comparator<ParProdutoNClientes> {

    @Override
    public int compare(ParProdutoNClientes p1, ParProdutoNClientes p2) {
        return p1.getProduto().getCodigoProduto().compareTo(p2.getProduto().getCodigoProduto());
    }

}
