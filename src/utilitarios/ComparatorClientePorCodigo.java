package utilitarios;

import dados.Cliente;
import java.util.Comparator;

public class ComparatorClientePorCodigo implements Comparator<Cliente> {

    @Override
    public int compare(Cliente c1, Cliente c2) {
        return c1.getCodigoCliente().compareTo(c2.getCodigoCliente());
    }

}
