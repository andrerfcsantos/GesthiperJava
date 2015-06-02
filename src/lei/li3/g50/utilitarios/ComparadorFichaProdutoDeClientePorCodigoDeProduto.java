package lei.li3.g50.utilitarios;

import java.util.Comparator;
import lei.li3.g50.modulos.compras.FichaProdutoDeClienteCompras;

public class ComparadorFichaProdutoDeClientePorCodigoDeProduto
                    implements Comparator<FichaProdutoDeClienteCompras>{

    @Override
    public int compare(FichaProdutoDeClienteCompras f1, FichaProdutoDeClienteCompras f2) {
        return f1.getProduto().getCodigoProduto().compareTo(f2.getProduto().getCodigoProduto());
    }

}
