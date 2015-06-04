package lei.li3.g50.modulos.compras;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import lei.li3.g50.modulos.dados.*;

public class Compras {

    private int numeroComprasValorZero;
    private int numeroClientesDistintosPorMes[];
    private int numeroTotalClientesDistintos;
    private TreeMap<Cliente, FichaClienteCompras> arvoreClientes;
    private TreeMap<Produto, ParProdutoNClientes> arvoreParesProdutoNClientes;

    public Compras() {
        this.numeroComprasValorZero = 0;
        numeroTotalClientesDistintos = 0;
        numeroClientesDistintosPorMes = new int[12];
        arvoreClientes = new TreeMap<>();
        arvoreParesProdutoNClientes = new TreeMap<>();
    }

    public Compras(Compras compras) {
        numeroTotalClientesDistintos = compras.getTotalClientesDistintos();
        this.numeroComprasValorZero = compras.getNumeroComprasValorZero();
        for (int i = 0; i < 12; i++) {
            this.numeroClientesDistintosPorMes[i] = compras.numeroClientesDistintosPorMes[i];
        }

        for (Map.Entry<Cliente, FichaClienteCompras> entrada : compras.arvoreClientes.entrySet()) {
            this.arvoreClientes.put(entrada.getKey().clone(), entrada.getValue().clone());
        }

        for (Map.Entry<Produto, ParProdutoNClientes> entrada : compras.arvoreParesProdutoNClientes.entrySet()) {
            this.arvoreParesProdutoNClientes.put(entrada.getKey().clone(), entrada.getValue().clone());
        }

    }

    public void registaCliente(Cliente cliente) {
        this.arvoreClientes.put(cliente.clone(), new FichaClienteCompras());
    }

    public void registaProduto(Produto produto) {
        this.arvoreParesProdutoNClientes.put(produto.clone(), new ParProdutoNClientes());
    }

    public void registaCompra(Compra compra) {
        Mes mes = compra.getMes();
        Produto produto = compra.getProduto();
        FichaClienteCompras ficha_cliente = getFichaClienteNoClone(compra.getCliente());

        if (ficha_cliente.getNumComprasMes(mes, TipoCompra.AMBOS) == 0) {
            numeroClientesDistintosPorMes[mes.getIndiceArray()]++;
        }

        if (ficha_cliente.getTotalCompras() == 0) {
            numeroTotalClientesDistintos++;
        }

        ParProdutoNClientes par_prod_nclis = this.getParProdutoNClientesNoClone(produto);

        if (ficha_cliente.clienteComprouProduto(produto)) {

            FichaProdutoDeClienteCompras ficha_produto = ficha_cliente.getFichaProduto(produto);

            if (ficha_produto.getNumComprasProdutoClienteMes(mes) == 0) {
                par_prod_nclis.addNumeroClientesMes(mes, 1);
            }

            if (ficha_produto.getNumComprasProdutoCliente() == 0) {
                par_prod_nclis.addNumeroTotalClientesDistintos(1);
            }

        } else {
            par_prod_nclis.addNumeroClientesMes(mes, 1);
            par_prod_nclis.addNumeroTotalClientesDistintos(1);
        }


        if (compra.getPreco() == 0) {
            this.numeroComprasValorZero++;
        }

        ficha_cliente.regista_compra(compra);
    }

    /*
     GETTERS
     */
    public int getNumeroComprasValorZero() {
        return numeroComprasValorZero;
    }

    public int getNumeroClientesDistintosMes(Mes mes) {
        return this.numeroClientesDistintosPorMes[mes.getIndiceArray()];
    }

    public int getTotalClientesDistintos() {
        return this.numeroTotalClientesDistintos;
    }

    public int getTotalComprasCliente(Cliente cliente) {
        return this.getFichaClienteNoClone(cliente).getTotalCompras();
    }

    public FichaClienteCompras getFichaCliente(Cliente cliente) {
        return this.arvoreClientes.get(cliente).clone();
    }

    private FichaClienteCompras getFichaClienteNoClone(Cliente cliente) {
        return this.arvoreClientes.get(cliente);
    }

    public ParProdutoNClientes getParProdutoNClientes(Produto produto) {
        return this.arvoreParesProdutoNClientes.get(produto).clone();
    }

    public ParProdutoNClientes getParProdutoNClientesNoClone(Produto produto) {
        return this.arvoreParesProdutoNClientes.get(produto);
    }

    public List<Cliente> getClientesSemCompras() {
        ArrayList<Cliente> lista_clientes = new ArrayList<>(this.arvoreClientes.size() / 10);

        for (Map.Entry<Cliente, FichaClienteCompras> entrada : this.arvoreClientes.entrySet()) {
            if (entrada.getValue().getTotalCompras() == 0) {
                lista_clientes.add(entrada.getKey().clone());
            }
        }

        return (List<Cliente>) lista_clientes;
    }

    public List<Produto> getProdutosSemCompras() {
        ArrayList<Produto> lista_produtos = new ArrayList<>(this.arvoreClientes.size() / 10);

        for (Map.Entry<Produto, ParProdutoNClientes> entrada : this.arvoreParesProdutoNClientes.entrySet()) {
            if (entrada.getValue().getNumeroTotalClientesDisntintos() == 0) {
                lista_produtos.add(entrada.getKey().clone());
            }
        }

        return (List<Produto>) lista_produtos;
    }

    /*
     METODOS STANDARD
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.numeroComprasValorZero;
        hash = 11 * hash + Objects.hashCode(this.numeroClientesDistintosPorMes);
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
        return this.arvoreClientes.size() == other.arvoreClientes.size()
                && this.arvoreParesProdutoNClientes.size() == other.arvoreParesProdutoNClientes.size()
                && Arrays.equals(this.numeroClientesDistintosPorMes, other.numeroClientesDistintosPorMes)
                && this.numeroComprasValorZero == other.numeroComprasValorZero;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Compras{");
        sb.append("Compras valor zero=").append(numeroComprasValorZero);
        sb.append(" Nº Clientes distintos=").append(arvoreClientes.size());
        sb.append('}');
        return sb.toString();
    }

    public Compras clone() {
        return new Compras(this);
    }

}
