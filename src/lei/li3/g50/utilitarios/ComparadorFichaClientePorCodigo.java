package lei.li3.g50.utilitarios;

import java.util.Comparator;
import lei.li3.g50.modulos.compras.FichaClienteCompras;

public class ComparadorFichaClientePorCodigo implements Comparator<FichaClienteCompras> {

    @Override
    public int compare(FichaClienteCompras f1, FichaClienteCompras f2) {
        return f1.getCliente().getCodigoCliente().compareTo(f2.getCliente().getCodigoCliente());
    }

}
