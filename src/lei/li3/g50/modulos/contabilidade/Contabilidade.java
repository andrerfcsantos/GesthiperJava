package lei.li3.g50.modulos.contabilidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.TreeSet;

import lei.li3.g50.excepcoes.ProdutoNaoExisteException;
import lei.li3.g50.modulos.dados.Compra;
import lei.li3.g50.modulos.dados.Mes;
import lei.li3.g50.modulos.dados.Produto;
import lei.li3.g50.modulos.dados.TipoCompra;
import lei.li3.g50.utilitarios.ComparatorParProdutoQuantidadeComprada;
import lei.li3.g50.utilitarios.Matriz_Double_12x2;
import lei.li3.g50.utilitarios.Matriz_Int_12x2;
import lei.li3.g50.utilitarios.ParProdutoQuantidadeComprada;

public class Contabilidade implements Serializable {

    private static final long serialVersionUID = -6294510456940432396L;
    private static final int MAX_MONTH = 12;
    private Matriz_Int_12x2 totalComprasPorMes;
    private Matriz_Int_12x2 totalUnidadesVendidasPorMes;
    private Matriz_Double_12x2 totalFacturadoPorMes;
    TreeMap<Produto, FichaProdutoContabilidade> arvoreProdutos;

    /*
     CONSTRUCTORES
     */
    public Contabilidade() {
        super();
        this.totalComprasPorMes = new Matriz_Int_12x2();
        this.totalUnidadesVendidasPorMes = new Matriz_Int_12x2();
        this.totalFacturadoPorMes = new Matriz_Double_12x2();
        this.arvoreProdutos = new TreeMap<>();
    }

    public Contabilidade(Matriz_Int_12x2 totalComprasPorMes,
            Matriz_Int_12x2 totalUnidadesVendidasPorMes, Matriz_Double_12x2 totalFacturadoPorMes,
            TreeMap<Produto, FichaProdutoContabilidade> arvoreProdutos,
            int numeroClientesDistintosPorMes[], int numeroProdutosDistintosPorMes[]) {

        super();
        this.arvoreProdutos = new TreeMap<>();
        this.totalComprasPorMes = totalComprasPorMes.clone();
        this.totalUnidadesVendidasPorMes = totalUnidadesVendidasPorMes.clone();
        this.totalFacturadoPorMes = totalFacturadoPorMes.clone();

        for (Map.Entry<Produto, FichaProdutoContabilidade> tuplo : arvoreProdutos.entrySet()) {
            this.arvoreProdutos.put(tuplo.getKey().clone(), tuplo.getValue().clone());
        }

    }

    public Contabilidade(Contabilidade contabilidade) {
        super();
        this.totalComprasPorMes = contabilidade.getTotalComprasPorMes();
        this.totalUnidadesVendidasPorMes = contabilidade.getTotalUnidadesVendidasPorMes();
        this.totalFacturadoPorMes = contabilidade.getTotalFacturadoPorMes();
        this.arvoreProdutos = contabilidade.getArvoreProdutos();
    }

    /*
     REGISTOS
     */
    public void registaProduto(Produto produto) {
        this.arvoreProdutos.put(produto.clone(), new FichaProdutoContabilidade());
    }

    public void registaCompra(Compra compra) {
        Mes mes = compra.getMes();
        Produto produto = compra.getProduto();
        TipoCompra tipo_compra = compra.getTipoCompra();
        int qt = compra.getQuantidade();
        double preco = compra.getPreco();
        FichaProdutoContabilidade ficha_produto;

        this.totalComprasPorMes.addValorMesTipoCompra(mes, tipo_compra, 1);
        this.totalUnidadesVendidasPorMes.addValorMesTipoCompra(mes, tipo_compra, qt);
        this.totalFacturadoPorMes.addValorMesTipoCompra(mes, tipo_compra, preco * (qt + 0.0));

        try {
            ficha_produto = getFichaProdutoNoClone(produto);
        } catch (ProdutoNaoExisteException e) {
            ficha_produto = new FichaProdutoContabilidade();
            this.arvoreProdutos.put(produto.clone(), ficha_produto);
        }

        ficha_produto.addNumComprasProdutoPorMesPorTipoCompra(mes, tipo_compra, 1);
        ficha_produto.addNumUnidadesProdutoVendidasPorMesPorTipoCompra(mes, tipo_compra, qt);
        ficha_produto.addFacturacaoProdutoPorMesPorTipoCompra(mes, tipo_compra, preco * (qt + 0.0));

    }


    /* GETTERS CLASSE SUPERIOR*/
    public TreeMap<Produto, FichaProdutoContabilidade> getArvoreProdutos() {
        TreeMap<Produto, FichaProdutoContabilidade> tmp = new TreeMap<>();

        for (Map.Entry<Produto, FichaProdutoContabilidade> tuplo : this.arvoreProdutos.entrySet()) {
            tmp.put(tuplo.getKey().clone(), tuplo.getValue().clone());
        }

        return tmp;
    }

    /* NUMERO COMPRAS */
    public Matriz_Int_12x2 getTotalComprasPorMes() {
        return totalComprasPorMes.clone();
    }

    public int getTotalCompras() {
        return totalComprasPorMes.getSomaTotal();
    }

    public int getTotalComprasPorMes(Mes mes) {
        return totalComprasPorMes.getValorMesTipoCompra(mes, TipoCompra.AMBOS);
    }

    /* UNIDADES VENDIDAS */
    public Matriz_Int_12x2 getTotalUnidadesVendidasPorMes() {
        return totalUnidadesVendidasPorMes.clone();
    }

    public int getTotalUnidadesVendidas() {
        return totalUnidadesVendidasPorMes.getSomaTotal();
    }

    public int getTotalUnidadesVendidasPorMes(Mes mes) {
        return totalUnidadesVendidasPorMes.getValorMesTipoCompra(mes, TipoCompra.AMBOS);
    }

    /* FACTURCAO */
    public Matriz_Double_12x2 getTotalFacturadoPorMes() {
        return totalFacturadoPorMes.clone();
    }

    public double getTotalFacturado() {
        return totalFacturadoPorMes.getSomaTotal();
    }

    public double getTotalFacturadoPorMes(Mes mes) {
        return totalFacturadoPorMes.getValorMesTipoCompra(mes, TipoCompra.AMBOS);
    }

    /* GETTERS CLASSES INFERIORES*/
    private FichaProdutoContabilidade getFichaProdutoNoClone(Produto produto) throws ProdutoNaoExisteException {
        FichaProdutoContabilidade ficha;

        if (this.arvoreProdutos.containsKey(produto)) {
            ficha = this.arvoreProdutos.get(produto);
        } else {
            throw new ProdutoNaoExisteException();
        }

        return ficha;
    }

    /* NUMERO COMPRAS */
    public Matriz_Int_12x2 getComprasProdutoMeses(Produto produto) throws ProdutoNaoExisteException {
        return this.getFichaProdutoNoClone(produto).getMatrizNumComprasProdutoPorMes();
    }
    public int getNumeroTotalComprasProduto(Produto produto) throws ProdutoNaoExisteException {
        return this.getFichaProdutoNoClone(produto).getNumComprasProduto();
    }

    public int getNumeroComprasProdutoMes(Produto produto, Mes mes) throws ProdutoNaoExisteException {
        return this.getFichaProdutoNoClone(produto).getNumComprasProdutoMes(mes);
    }

    public int getTotalComprasProdutoIntervaloDeMeses(Produto produto, Mes mes_inf, Mes mes_sup, TipoCompra tipo_compra) throws ProdutoNaoExisteException {
        return this.getFichaProdutoNoClone(produto).getNumComprasProdutoIntervaloDeMeses(mes_inf, mes_sup, tipo_compra);
    }

    /* UNIDADES COMPRADAS*/
    public Matriz_Int_12x2 getUnidadesProdutoVendidasMeses(Produto produto) throws ProdutoNaoExisteException {
        return this.getFichaProdutoNoClone(produto).getMatrizNumUnidadesProdutoVendidasPorMes();
    }
    public int getTotalUnidadesProdutoVendidas(Produto produto) throws ProdutoNaoExisteException {
        return this.getFichaProdutoNoClone(produto).getNumUnidadesProdutoVendidas();
    }

    public int getNumUnidadesProdutoVendidasMes(Produto produto, Mes mes) throws ProdutoNaoExisteException {
        return this.getFichaProdutoNoClone(produto).getNumUnidadesProdutoVendidasMes(mes);
    }

    public int getNumUnidadesProdutoVendidasPorIntervaloDeMeses(Produto produto, Mes mes_inf, Mes mes_sup, TipoCompra tipo_compra) throws ProdutoNaoExisteException {
        return this.getFichaProdutoNoClone(produto).getNumUnidadesProdutoVendidasIntervaloDeMeses(mes_inf, mes_sup, tipo_compra);
    }

    /* FACTURACAO */
    public Matriz_Double_12x2 getFacturacaoProdutoMeses(Produto produto) throws ProdutoNaoExisteException {
        return this.getFichaProdutoNoClone(produto).getMatrizFacturacaoProdutoPorMes();
    }
    public double getTotalFacturacaoProduto(Produto produto) throws ProdutoNaoExisteException {
        return this.getFichaProdutoNoClone(produto).getFacturacaoProduto();
    }

    public double getFacturacaoProdutoMes(Produto produto, Mes mes) throws ProdutoNaoExisteException {
        return this.getFichaProdutoNoClone(produto).getFacturacaoProdutoMes(mes);
    }

    public double getFacturacaoProdutoPorIntervaloDeMeses(Produto produto, Mes mes_inf, Mes mes_sup, TipoCompra tipo_compra) throws ProdutoNaoExisteException {
        return this.getFichaProdutoNoClone(produto).getFacturacaoProdutoIntervaloDeMeses(mes_inf, mes_sup, tipo_compra);
    }

    /* SETTERS CLASSE SUPERIOR*/
    /*NUMERO COMPRAS*/
    public void setTotalComprasPorMes(Matriz_Int_12x2 totalComprasPorMes) {
        this.totalComprasPorMes = totalComprasPorMes.clone();
    }

    public void setTotalComprasPorMesPorTipoCompra(Mes mes, TipoCompra tipo_compra, int valor) {
        this.totalComprasPorMes.setValorMesTipoCompra(mes, tipo_compra, valor);
    }

    public void addTotalComprasPorMesPorTipoCompra(Mes mes, TipoCompra tipo_compra, int valor) {
        this.totalComprasPorMes.addValorMesTipoCompra(mes, tipo_compra, valor);
    }

    /* UNIDADES VENDIDAS */
    public void setTotalUnidadesVendidasPorMes(Matriz_Int_12x2 totalUnidadesVendidasPorMes) {
        this.totalUnidadesVendidasPorMes = totalUnidadesVendidasPorMes.clone();
    }

    public void setNumUnidadesVendidasPorMesPorTipoCompra(Mes mes, TipoCompra tipo_compra, int valor) {
        this.totalUnidadesVendidasPorMes.setValorMesTipoCompra(mes, tipo_compra, valor);
    }

    public void addNumUnidadesVendidasPorMesPorTipoCompra(Mes mes, TipoCompra tipo_compra, int valor) {
        this.totalUnidadesVendidasPorMes.addValorMesTipoCompra(mes, tipo_compra, valor);
    }

    /* FACTURCAO */
    public void setTotalFacturadoPorMes(Matriz_Double_12x2 totalFacturadoPorMes) {
        this.totalFacturadoPorMes = totalFacturadoPorMes.clone();
    }

    public void setTotalFacturadoPorMesPorTipoCompra(Mes mes, TipoCompra tipo_compra, int valor) {
        this.totalFacturadoPorMes.setValorMesTipoCompra(mes, tipo_compra, valor);
    }

    public void addTotalFacturadoPorMesPorTipoCompra(Mes mes, TipoCompra tipo_compra, int valor) {
        this.totalFacturadoPorMes.addValorMesTipoCompra(mes, tipo_compra, valor);
    }


    /* SETTERS CLASSES INFERIORES*/
    /* UNIDADES VENDIDAS */
    public void setNumUnidadesProdutoVendidasPorMesPorTipoCompra(Produto produto, Mes mes, TipoCompra tipo_compra, int valor) throws ProdutoNaoExisteException {
        this.getFichaProdutoNoClone(produto).addNumUnidadesProdutoVendidasPorMesPorTipoCompra(mes, tipo_compra, valor);
    }

    public void addNumUnidadesProdutoVendidasPorMesPorTipoCompra(Produto produto, Mes mes, TipoCompra tipo_compra, int valor) throws ProdutoNaoExisteException {
        this.getFichaProdutoNoClone(produto).addNumUnidadesProdutoVendidasPorMesPorTipoCompra(mes, tipo_compra, valor);
    }

    /*NUMERO COMPRAS*/
    public void setNumComprasProdutoPorMesPorTipoCompra(Produto produto, Mes mes, TipoCompra tipo_compra, int valor) throws ProdutoNaoExisteException {
        this.getFichaProdutoNoClone(produto).setNumComprasProdutoPorMesPorTipoCompra(mes, tipo_compra, valor);
    }

    public void addNumComprasProdutoPorMesPorTipoCompra(Produto produto, Mes mes, TipoCompra tipo_compra, int valor) throws ProdutoNaoExisteException {
        this.getFichaProdutoNoClone(produto).addNumComprasProdutoPorMesPorTipoCompra(mes, tipo_compra, valor);
    }

    /* FACTURCAO */
    public void setFacturacaoProdutoPorMesPorTipoCompra(Produto produto, Mes mes, TipoCompra tipo_compra, int valor) throws ProdutoNaoExisteException {
        this.getFichaProdutoNoClone(produto).setFacturacaoProdutoPorMesPorTipoCompra(mes, tipo_compra, valor);
    }

    public void addFacturacaoProdutoPorMesPorTipoCompra(Produto produto, Mes mes, TipoCompra tipo_compra, int valor) throws ProdutoNaoExisteException {
        this.getFichaProdutoNoClone(produto).addFacturacaoProdutoPorMesPorTipoCompra(mes, tipo_compra, valor);
    }

    /*
     QUERIES
     */
    public List<Produto> getProdutosNaoComprados() {
        ArrayList<Produto> tmp = new ArrayList<>();

        for (Map.Entry<Produto, FichaProdutoContabilidade> par : this.arvoreProdutos.entrySet()) {
            if (par.getValue().getNumComprasProduto() == 0) {
                tmp.add(par.getKey().clone());
            }
        }

        return tmp;
    }

    public List<ParProdutoQuantidadeComprada> getProdutosMaisVendidos(int X) {

        TreeSet<ParProdutoQuantidadeComprada> pares = new TreeSet<>(new ComparatorParProdutoQuantidadeComprada());
        ArrayList<ParProdutoQuantidadeComprada> lista = new ArrayList<>();
        ParProdutoQuantidadeComprada novo_par;

        for (Map.Entry<Produto, FichaProdutoContabilidade> entrada : this.arvoreProdutos.entrySet()) {
            novo_par = new ParProdutoQuantidadeComprada(entrada.getKey().clone(), entrada.getValue().getNumUnidadesProdutoVendidas());
            pares.add(novo_par);
        }

        for (ParProdutoQuantidadeComprada par : pares) {
            lista.add(par);
        }

        if (X > arvoreProdutos.size()) {
            X = arvoreProdutos.size();
        }

        return lista.subList(0, X);
    }

    /* METODOS STANDARD */
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

        Contabilidade other = (Contabilidade) obj;
        return this.totalComprasPorMes.equals(other.totalComprasPorMes)
                && this.totalFacturadoPorMes.equals(other.totalFacturadoPorMes)
                && this.totalUnidadesVendidasPorMes.equals(other.totalUnidadesVendidasPorMes)
                && this.arvoreProdutos.size() == other.arvoreProdutos.size();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.totalComprasPorMes);
        hash = 13 * hash + Objects.hashCode(this.totalUnidadesVendidasPorMes);
        hash = 13 * hash + Objects.hashCode(this.totalFacturadoPorMes);
        hash = 13 * hash + Objects.hashCode(this.arvoreProdutos);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Contabilidade {");
        sb.append("Total Compras=").append(totalComprasPorMes.getSomaTotal());
        sb.append(", totalUnidadesVendidasPorMes=").append(totalUnidadesVendidasPorMes.getSomaTotal());
        sb.append(", totalFacturadoPorMes=").append(totalFacturadoPorMes.getSomaTotal());
        sb.append(", Total Produtos=").append(arvoreProdutos.size());
        sb.append("}");
        return sb.toString();
    }
}
