package lei.li3.g50.utilitarios;

import java.util.Comparator;

import lei.li3.g50.modulos.dados.Cliente;

public class ComparatorClientePorCodigo implements Comparator<Cliente> {

    @Override
    public int compare(Cliente c1, Cliente c2) {
        return c1.getCodigoCliente().compareTo(c2.getCodigoCliente());
    }

}
