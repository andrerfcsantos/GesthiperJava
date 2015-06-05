package lei.li3.g50.modulos.catalogos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

import lei.li3.g50.modulos.dados.Cliente;
import lei.li3.g50.utilitarios.ComparatorClientePorCodigo;

public class CatalogoClientes implements Serializable {

    private ArrayList<TreeSet<Cliente>> catalogo;

    /*
     CONSTRUCTORES
     */
    public CatalogoClientes() {
        TreeSet<Cliente> arvore;
        catalogo = new ArrayList<>(27);

        for (int i = 0; i < 27; i++) {
            arvore = new TreeSet<>(new ComparatorClientePorCodigo());
            catalogo.add(i, arvore);
        }

    }

    public CatalogoClientes(Collection<Cliente> coleccao) {
        this();
        int i, indice;
        for (Cliente cliente : coleccao) {
            indice = calcula_indice(cliente.getCodigoCliente().charAt(0));
            catalogo.get(indice).add(cliente.clone());
        }

    }

    public CatalogoClientes(CatalogoClientes cat) {
        this();
        int i, indice;
        for (i = 0; i < cat.catalogo.size(); i++) {
            for (Cliente cliente : cat.catalogo.get(i)) {
                indice = calcula_indice(cliente.getCodigoCliente().charAt(0));
                catalogo.get(indice).add(cliente.clone());
            }
        }

    }


    /*
     GETTERS
     */
    public List<Cliente> getClientes() {
        ArrayList<Cliente> resultado = new ArrayList<>();

        for (TreeSet<Cliente> arvore_clientes : this.catalogo) {
            for (Cliente cliente : arvore_clientes) {
                resultado.add(cliente.clone());
            }
        }

        return (List<Cliente>) resultado;
    }

    public List<Cliente> getClientesComecadosPorLetra(char letra) {
        ArrayList<Cliente> resultado = new ArrayList<>();
        int indice = calcula_indice(letra);

        for (Cliente cliente : this.catalogo.get(indice)) {
            resultado.add(cliente.clone());
        }

        return (List<Cliente>) resultado;
    }

    public int getNumeroClientesTotal() {
        int total = 0;

        for (TreeSet<Cliente> arvore_letra : catalogo) {
            total += arvore_letra.size();
        }

        return total;
    }

    public int getNumeroClientesLetra(char l) {
        int indice = calcula_indice(l);
        return catalogo.get(indice).size();
    }

    /*
     METODOS INSTANCIA
     */
    public void insere_cliente(Cliente cliente) {
        int indice = calcula_indice(cliente.getCodigoCliente().charAt(0));
        catalogo.get(indice).add(cliente.clone());
    }

    public void remove_cliente(Cliente cliente) {
        int indice = calcula_indice(cliente.getCodigoCliente().charAt(0));
        catalogo.get(indice).remove(cliente);
    }

    public boolean existeCliente(Cliente cliente) {
        int indice = calcula_indice(cliente.getCodigoCliente().charAt(0));
        return catalogo.get(indice).contains(cliente);
    }

    private static int calcula_indice(char l) {
        char letra = Character.toUpperCase(l);
        int res = Character.isAlphabetic(letra) ? letra - 'A' : 26;
        return res;
    }

    private boolean equalsCatalogos(CatalogoClientes cat) {
        int i;

        /*
         Tem o mesmo numero de arvores?
         */
        if (this.catalogo.size() != cat.catalogo.size()) {
            return false;
        }

        /*
         Temdo o mesmo número de arvores, as arvores têm o mesmo tamanho?
         */
        for (i = 0; i < this.catalogo.size(); i++) {
            if (this.catalogo.get(i).size() == cat.catalogo.get(i).size()) {
                return false;
            }
        }

        /*
         Os elementos em cada um dos TreeSet sao os mesmos?
         */
        for (i = 0; i < this.catalogo.size(); i++) {
            if (!this.catalogo.get(i).containsAll(cat.catalogo.get(i))) {
                return false;
            }
        }

        return true;
    }

    /*
     METODOS STANDARD
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.catalogo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CatalogoClientes other = (CatalogoClientes) obj;

        return this.equalsCatalogos(other);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("CatalogoClientes {");

        for (char i = 'A'; i <= 'Z'; i++) {
            sb.append(i).append(": ").append(this.getNumeroClientesLetra(i));
            sb.append(" ");
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public CatalogoClientes clone() {
        return new CatalogoClientes(this);
    }

}
