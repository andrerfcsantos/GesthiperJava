package lei.li3.g50.modulos.compras;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.TreeSet;

import lei.li3.g50.excepcoes.ClienteNaoComprouProdutoException;
import lei.li3.g50.excepcoes.ClienteNaoExisteException;
import lei.li3.g50.excepcoes.ProdutoNaoExisteException;
import lei.li3.g50.modulos.dados.Cliente;
import lei.li3.g50.modulos.dados.Compra;
import lei.li3.g50.modulos.dados.Mes;
import lei.li3.g50.modulos.dados.Produto;
import lei.li3.g50.modulos.dados.TipoCompra;
import lei.li3.g50.utilitarios.ComparatorParClienteProdutosDiferentes;
import lei.li3.g50.utilitarios.ComparatorTriploClienteQtdCompradaDinheiro;
import lei.li3.g50.utilitarios.Matriz_Double_12x2;
import lei.li3.g50.utilitarios.Matriz_Int_12x2;
import lei.li3.g50.utilitarios.ParClienteProdutosDiferentes;
import lei.li3.g50.utilitarios.ParProdutoQuantidadeComprada;
import lei.li3.g50.utilitarios.TriploClienteQtdCompradaDinheiro;

public class Compras implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7175792608614138915L;
	private int numeroComprasValorZero;
    private int numeroClientesDistintosPorMes[];
    private int numeroTotalClientesDistintos;
    private TreeMap<Cliente, FichaClienteCompras> arvoreClientes;
    private TreeMap<Produto, ParProdutoNClientes> arvoreParesProdutoNClientes;

    /*
     CONSTRUCTORES
     */
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

    /*
     REGISTOS
     */
    public void registaCliente(Cliente cliente) {
        this.arvoreClientes.put(cliente.clone(), new FichaClienteCompras());
    }

    public void registaProduto(Produto produto) {
        this.arvoreParesProdutoNClientes.put(produto.clone(), new ParProdutoNClientes());
    }

    public void registaCompra(Compra compra) {
        Mes mes = compra.getMes();
        Produto produto = compra.getProduto();
        FichaClienteCompras ficha_cliente;

        try {
            ficha_cliente = getFichaClienteNoClone(compra.getCliente());
        } catch (ClienteNaoExisteException ex) {
            ficha_cliente = new FichaClienteCompras();
            this.arvoreClientes.put(compra.getCliente().clone(), ficha_cliente);
        }

        if (ficha_cliente.getNumComprasMes(mes, TipoCompra.AMBOS) == 0) {
            numeroClientesDistintosPorMes[mes.getIndiceArray()]++;
        }

        if (ficha_cliente.getTotalCompras() == 0) {
            numeroTotalClientesDistintos++;
        }

        ParProdutoNClientes par_prod_nclis;

        try {
            par_prod_nclis = this.getParProdutoNClientesNoClone(produto);
        } catch (ProdutoNaoExisteException ex) {
            par_prod_nclis = new ParProdutoNClientes();
            this.arvoreParesProdutoNClientes.put(compra.getProduto().clone(), par_prod_nclis);
        }

        try {
            Matriz_Int_12x2 comprasMeses = ficha_cliente.getNumComprasProdutoMeses(produto);

            if (comprasMeses.getValorMesTipoCompra(mes, TipoCompra.AMBOS) == 0) {
                par_prod_nclis.addNumeroClientesMes(mes, 1);
            }

            if (comprasMeses.getSomaTotal() == 0) {
                par_prod_nclis.addNumeroTotalClientesDistintos(1);
            }

        } catch (ClienteNaoComprouProdutoException ex) {
            par_prod_nclis.addNumeroClientesMes(mes, 1);
            par_prod_nclis.addNumeroTotalClientesDistintos(1);
        }

        if (compra.getPreco() == 0) {
            this.numeroComprasValorZero++;
        }

        ficha_cliente.regista_compra(compra);
    }

    /*
     GETTERS ESPECIFICOS CLASSE
     */
    /*COMPRAS*/
    public int getNumeroComprasValorZero() {
        return numeroComprasValorZero;
    }

    /*CLIENTES DISTINTOS*/
    public int getTotalClientesDistintos() {
        return this.numeroTotalClientesDistintos;
    }

    public int getNumeroClientesDistintosMes(Mes mes) {
        return this.numeroClientesDistintosPorMes[mes.getIndiceArray()];
    }

    public int[] getNumeroClientesDistintosMesesAsArray() {
        int resultado[] = new int[12];

        for (int i = 0; i < 12; i++) {
            resultado[i] = this.numeroClientesDistintosPorMes[i];
        }

        return resultado;
    }

    public Map<Mes, Integer> getNumeroClientesDistintosMeses() {
        Mes mes;
        TreeMap<Mes, Integer> resultado = new TreeMap<>();

        for (int i = 0; i < 12; i++) {
            mes = Mes.numero_to_mes(i + 1);
            resultado.put(mes, this.numeroClientesDistintosPorMes[i]);
        }

        return resultado;
    }

    /*GETTERS CLASSES INFERIORES*/
    /*CLASSE FICHA CLIENTE*/
    private FichaClienteCompras getFichaClienteNoClone(Cliente cliente) throws ClienteNaoExisteException {
        FichaClienteCompras ficha;
        
        if(this.arvoreClientes.containsKey(cliente)){
            ficha = this.arvoreClientes.get(cliente);
        }else{
            throw new ClienteNaoExisteException();
        }
        
        return ficha;
    }

    public int getNumeroProdutosDistintosCliente(Cliente cliente) throws ClienteNaoExisteException {
        return this.getFichaClienteNoClone(cliente).getNumeroProdutosDistintos();
    }

    public Matriz_Int_12x2 getNumeroUnidadesCompradasClienteMeses(Cliente cliente) throws ClienteNaoExisteException {
        return this.getFichaClienteNoClone(cliente).getNumUnidadesCompradasClientePorMes();
    }

    public Matriz_Int_12x2 getNumeroComprasClienteMeses(Cliente cliente) throws ClienteNaoExisteException {
        return this.getFichaClienteNoClone(cliente).getNumComprasClientePorMes();
    }

    public Matriz_Double_12x2 getDinheiroGastoClienteMeses(Cliente cliente) throws ClienteNaoExisteException {
        return this.getFichaClienteNoClone(cliente).getDinheiroGastoClientePorMes();
    }

    public int getNumeroUnidadesCompradasClienteMes(Cliente cliente, Mes mes) throws ClienteNaoExisteException {
        return this.getFichaClienteNoClone(cliente).getNumUnidadesCompradasMes(mes, TipoCompra.AMBOS);
    }

    public int getNumeroComprasClienteMes(Cliente cliente, Mes mes) throws ClienteNaoExisteException {
        return this.getFichaClienteNoClone(cliente).getNumComprasMes(mes, TipoCompra.AMBOS);
    }

    public double getDinheiroGastoClienteMes(Cliente cliente, Mes mes) throws ClienteNaoExisteException {
        return this.getFichaClienteNoClone(cliente).getDinheiroGastoClientePorMes(mes, TipoCompra.AMBOS);
    }

    public int getTotalUnidadesCompradasCliente(Cliente cliente) throws ClienteNaoExisteException {
        return this.getFichaClienteNoClone(cliente).getTotalUnidadesCompradas();
    }

    public int getTotalComprasCliente(Cliente cliente) throws ClienteNaoExisteException {
        return this.getFichaClienteNoClone(cliente).getTotalCompras();
    }

    public double getTotalDinheiroGasto(Cliente cliente) throws ClienteNaoExisteException {
        return this.getFichaClienteNoClone(cliente).getTotalDinheiroGasto();
    }

    /*CLASSE ParProdutoNClientes*/
    private ParProdutoNClientes getParProdutoNClientesNoClone(Produto produto) throws ProdutoNaoExisteException {
        ParProdutoNClientes resultado;
        
        if(this.arvoreParesProdutoNClientes.containsKey(produto)){
            resultado = this.arvoreParesProdutoNClientes.get(produto);
        }else{
            throw new ProdutoNaoExisteException();
        }
        
        return resultado;
    }

    public int getTotalClientesDistintosProduto(Produto produto) throws ProdutoNaoExisteException {
        return this.getParProdutoNClientesNoClone(produto).getNumeroTotalClientesDisntintos();
    }

    public Map<Mes, Integer> getClientesDistintosProdutoMeses(Produto produto) throws ProdutoNaoExisteException {
        return this.getParProdutoNClientesNoClone(produto).getNumeroClientesDistintosPorMes();
    }

    public int getClientesDistintosProdutoMes(Produto produto, Mes mes) throws ProdutoNaoExisteException {
        return this.getParProdutoNClientesNoClone(produto).getNumeroClientesDisntintosMes(mes);
    }

    /*CLASSE FichaProdutoDeClienteCompras (ASSOCIACAO PRODUTO-CLIENTE)*/
    public Matriz_Int_12x2 getComprasProdutoClienteMeses(Produto produto, Cliente cliente) throws ClienteNaoExisteException, ClienteNaoComprouProdutoException {
        return this.getFichaClienteNoClone(cliente).getNumComprasProdutoMeses(produto);
    }

    public Matriz_Int_12x2 getUnidadesCompradasProdutoClienteMeses(Produto produto, Cliente cliente) throws ClienteNaoExisteException, ClienteNaoComprouProdutoException {
        return this.getFichaClienteNoClone(cliente).getNumUnidadesCompradasProdutoMeses(produto);
    }
    
    public int getTotalComprasProdutoCliente(Produto produto, Cliente cliente) throws ClienteNaoExisteException, ClienteNaoComprouProdutoException{
        return this.getFichaClienteNoClone(cliente).getNumTotalUnidadesCompradasProduto(produto);
    }
    public int getTotalUnidadesCompradasProdutoCliente(Produto produto, Cliente cliente) throws ClienteNaoExisteException, ClienteNaoComprouProdutoException{
        return this.getFichaClienteNoClone(cliente).getNumTotalUnidadesCompradasProduto(produto);
    }
    
    public double getTotalDinheiroGastoProdutoCliente(Produto produto, Cliente cliente) throws ClienteNaoExisteException, ClienteNaoComprouProdutoException {
        return this.getFichaClienteNoClone(cliente).getDinheiroGastoProduto(produto);
    }

    /*RESULTADOS QUERIES*/
    public int getTotalClientesSemCompras() {
        int resultado = 0;

        for (Map.Entry<Cliente, FichaClienteCompras> entrada : this.arvoreClientes.entrySet()) {
            if (entrada.getValue().getTotalCompras() == 0) {
                resultado++;
            }
        }

        return resultado;
    }

    public int getTotalProdutosNaoComprados() {
        int resultado = 0;
        for (Map.Entry<Produto, ParProdutoNClientes> entrada : this.arvoreParesProdutoNClientes.entrySet()) {
            if (entrada.getValue().getNumeroTotalClientesDisntintos() == 0) {
                resultado++;
            }
        }
        return resultado;
    }

    public Map<Mes, Integer> getNumeroProdutosDisntintosPorMesCliente(Cliente cliente) throws ClienteNaoExisteException {
        return this.getFichaClienteNoClone(cliente).getNumeroProdutosDistintosPorMes();
    }

    public List<Produto> getProdutosNaoComprados() {
        ArrayList<Produto> lista_produtos = new ArrayList<>();

        for (Map.Entry<Produto, ParProdutoNClientes> entrada : this.arvoreParesProdutoNClientes.entrySet()) {
            if (entrada.getValue().getNumeroTotalClientesDisntintos() == 0) {
                lista_produtos.add(entrada.getKey().clone());
            }
        }
        return lista_produtos;
    }

    public List<TriploClienteQtdCompradaDinheiro> getTriplosClienteQtdCompradaDinheiro(Produto produto, int topN) {
        int unidadesCompradas;
        double dinheiroGasto;
        TreeSet<TriploClienteQtdCompradaDinheiro> triplos = new TreeSet<>(new ComparatorTriploClienteQtdCompradaDinheiro());
        ArrayList<TriploClienteQtdCompradaDinheiro> lista_triplos = new ArrayList<>();
        TriploClienteQtdCompradaDinheiro novo_triplo;

        for (Map.Entry<Cliente, FichaClienteCompras> entrada : this.arvoreClientes.entrySet()) {
            try {
                unidadesCompradas = this.getTotalComprasProdutoCliente(produto, entrada.getKey());
            } catch (ClienteNaoComprouProdutoException | ClienteNaoExisteException ex) {
                unidadesCompradas = 0;
            }
            
            try {
                dinheiroGasto = this.getTotalDinheiroGastoProdutoCliente(produto, entrada.getKey());
            } catch (ClienteNaoComprouProdutoException | ClienteNaoExisteException ex) {
                dinheiroGasto = 0;
            }
            
            novo_triplo = new TriploClienteQtdCompradaDinheiro(entrada.getKey().clone(),
                                                                unidadesCompradas,
                                                                dinheiroGasto);

            triplos.add(novo_triplo);
        }

        for (TriploClienteQtdCompradaDinheiro triplo : triplos) {
            lista_triplos.add(triplo);
        }

        return lista_triplos.subList(0, (topN > lista_triplos.size()) ? lista_triplos.size() :topN);

    }

    public List<ParClienteProdutosDiferentes> getParesClienteProdutosDiferentes(int n) {
        TreeSet<ParClienteProdutosDiferentes> pares = new TreeSet<>(new ComparatorParClienteProdutosDiferentes());
        ArrayList<ParClienteProdutosDiferentes> lista = new ArrayList<>();
        ParClienteProdutosDiferentes novo_par;

        for (Map.Entry<Cliente, FichaClienteCompras> entrada : this.arvoreClientes.entrySet()) {
            novo_par = new ParClienteProdutosDiferentes(entrada.getKey(), entrada.getValue().getNumeroProdutosDistintos());
            pares.add(novo_par);
        }

        for (ParClienteProdutosDiferentes par : pares) {
            lista.add(par);
        }

        if (n > arvoreClientes.size()) {
            n = arvoreClientes.size();
        }

        return lista.subList(0, n);

    }

    public List<ParProdutoQuantidadeComprada> getParesProdutoNumComprasCliente(Cliente cliente) throws ClienteNaoExisteException {
        return this.getFichaClienteNoClone(cliente).getParesProdutoQuantidadeComprada();
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
          return true;
        }
        if (obj == null) {
            return false;
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
