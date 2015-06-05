package lei.li3.g50.utilitarios;

import java.util.Objects;
import lei.li3.g50.modulos.dados.Cliente;

public class ParClienteProdutosDiferentes {

    private Cliente cliente;
    private int produtos_diferentes;

    private ParClienteProdutosDiferentes() {
    }

    public ParClienteProdutosDiferentes(Cliente cliente) {
        this.cliente = cliente.clone();
        this.produtos_diferentes = 0;
    }

    public ParClienteProdutosDiferentes(Cliente cliente, int produtos) {
        this.cliente = cliente.clone();
        this.produtos_diferentes = produtos;
    }

    public ParClienteProdutosDiferentes(ParClienteProdutosDiferentes par) {
        this.cliente = par.cliente.clone();
        this.produtos_diferentes = par.produtos_diferentes;
    }

    /*
     GETTERS
     */
    public Cliente getCliente() {
        return cliente;
    }

    public int getProdutosDiferentes() {
        return produtos_diferentes;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente.clone();
    }

    /*
     SETTERS
     */
    public void setProdutosDiferentes(int produtos_diferentes) {
        this.produtos_diferentes = produtos_diferentes;
    }

    public void addProdutosDiferentes(int valor) {
        this.produtos_diferentes += valor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.cliente);
        hash = 29 * hash + this.produtos_diferentes;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ParClienteProdutosDiferentes other = (ParClienteProdutosDiferentes) obj;
        return this.cliente.equals(other.cliente)
                && this.produtos_diferentes == other.produtos_diferentes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("ParClienteProdutosDiferentes{");
        sb.append("cliente=").append(cliente);
        sb.append(", produtos_diferentes=").append(produtos_diferentes);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public ParClienteProdutosDiferentes clone() {
        return new ParClienteProdutosDiferentes(this);
    }

}
