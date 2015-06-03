package lei.li3.g50.modulos.compras;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.TreeSet;
import lei.li3.g50.modulos.dados.*;
import lei.li3.g50.utilitarios.ComparadorFichaClientePorCodigo;
import lei.li3.g50.utilitarios.ComparadorParProdNClientesPorCodigoProduto;;

public class Compras {

    private int numeroComprasValorZero;
    private int numeroClientesPorMes[];
    private TreeSet<FichaClienteCompras> arvoreClientes;
    private TreeSet<ParProdutoNClientes> arvoreParesProdutoNClientes;

    public Compras() {
        this.numeroComprasValorZero = 0;
        numeroClientesPorMes = new int[12];
        arvoreClientes = new TreeSet<>(new ComparadorFichaClientePorCodigo());
        arvoreParesProdutoNClientes = new TreeSet<>(new ComparadorParProdNClientesPorCodigoProduto());
    }

    public Compras(Compras compras) {
        this.numeroComprasValorZero = compras.getNumeroComprasValorZero();
        for(int i=0;i<12;i++) this.numeroClientesPorMes[i] = compras.numeroClientesPorMes[i];
        arvoreClientes = compras.getArvoreClientes();
        arvoreParesProdutoNClientes = compras.getArvoreParesProdutoNClientes();
    }

    public void registaCliente(Cliente cliente) {
        this.arvoreClientes.add(new FichaClienteCompras(cliente));
    }

    public void registaProduto(Produto produto) {
        this.arvoreParesProdutoNClientes.add(new ParProdutoNClientes(produto));
    }

    public void registaCompra(Compra compra) {
            Mes mes = compra.getMes();
            FichaClienteCompras ficha_cliente = getFichaClienteNoClone(compra.getCliente());
            
            if(ficha_cliente.getNumComprasMes(mes, TipoCompra.AMBOS) == 0)
                numeroClientesPorMes[mes.getIndiceArray()]++;
            
            if(compra.getPreco()==0)
                this.numeroComprasValorZero++;
            
            ficha_cliente.regista_compra(compra);
    }

    /*
     GETTERS
     */
    public int getNumeroComprasValorZero() {
        return numeroComprasValorZero;
    }

    public int[] getNumeroClientesPorMes() {
        int resultado[] = new int[12];
        
        for(int i=0;i<12;i++) resultado[i] = this.numeroClientesPorMes[i];
        
        return resultado;
    }
    public int getNumeroClientesPorMes(Mes mes) {
        return this.numeroClientesPorMes[mes.getIndiceArray()];
    }

    public TreeSet<FichaClienteCompras> getArvoreClientes() {
        TreeSet<FichaClienteCompras> resultado = new TreeSet<>(new ComparadorFichaClientePorCodigo());

        for (FichaClienteCompras ficha : this.arvoreClientes) {
            resultado.add(ficha.clone());
        }
        return resultado;
    }

    public TreeSet<ParProdutoNClientes> getArvoreParesProdutoNClientes() {
        TreeSet<ParProdutoNClientes> resultado = new TreeSet<>(new ComparadorParProdNClientesPorCodigoProduto());

        for (ParProdutoNClientes ficha : this.arvoreParesProdutoNClientes) {
            resultado.add(ficha.clone());
        }
        return resultado;
    }

    public FichaClienteCompras getFichaCliente(Cliente cliente) {
        FichaClienteCompras ficha = null;
        FichaClienteCompras ficha_iterada;
        boolean encontrado = false;
        Iterator<FichaClienteCompras> it = this.arvoreClientes.iterator();

        while (it.hasNext() && !encontrado) {
            ficha_iterada = it.next();
            if (ficha_iterada.getCliente().equals(cliente)) {
                encontrado = true;
                ficha = ficha_iterada;
            }
        }

        return encontrado ? ficha.clone() : null;

    }
    private FichaClienteCompras getFichaClienteNoClone(Cliente cliente) {
        FichaClienteCompras ficha = null;
        FichaClienteCompras ficha_iterada;
        boolean encontrado = false;
        Iterator<FichaClienteCompras> it = this.arvoreClientes.iterator();

        while (it.hasNext() && !encontrado) {
            ficha_iterada = it.next();
            if (ficha_iterada.getCliente().equals(cliente)) {
                encontrado = true;
                ficha = ficha_iterada;
            }
        }

        return encontrado ? ficha : null;

    }

    /*
     METODOS STANDARD
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.numeroComprasValorZero;
        hash = 11 * hash + Objects.hashCode(this.numeroClientesPorMes);
        hash = 11 * hash + Objects.hashCode(this.arvoreClientes);
        hash = 11 * hash + Objects.hashCode(this.arvoreParesProdutoNClientes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            if (obj == null) {
                return false;
            }
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Compras other = (Compras) obj;
        return this.arvoreClientes.containsAll(other.arvoreClientes)
                && this.arvoreParesProdutoNClientes.containsAll(other.arvoreClientes)
                && Arrays.equals(this.numeroClientesPorMes, other.numeroClientesPorMes)
                && this.numeroComprasValorZero == other.numeroComprasValorZero;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Compras{");
        sb.append("numeroComprasValorZero=").append(numeroComprasValorZero);
        sb.append(", numeroClientesPorMes=").append(numeroClientesPorMes);
        sb.append(", Nº clientes=").append(arvoreClientes.size());
        sb.append('}');
        return sb.toString();
    }

    public Compras clone() {
        return new Compras(this);
    }

}
