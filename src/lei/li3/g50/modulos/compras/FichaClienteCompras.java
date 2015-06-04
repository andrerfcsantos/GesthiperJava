package lei.li3.g50.modulos.compras;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import lei.li3.g50.modulos.dados.*;
import lei.li3.g50.utilitarios.Matriz_Double_12x2;
import lei.li3.g50.utilitarios.Matriz_Int_12x2;

public class FichaClienteCompras {

    private Matriz_Int_12x2 numUnidadesCompradasClientePorMes;
    private Matriz_Int_12x2 numComprasClientePorMes;
    private Matriz_Double_12x2 dinheiroGastoClientePorMes;
    private TreeMap<Produto, FichaProdutoDeClienteCompras> produtosCliente;

    /*
     CONSTRUCTORES
     */
    public FichaClienteCompras() {
        numUnidadesCompradasClientePorMes = new Matriz_Int_12x2();
        numComprasClientePorMes = new Matriz_Int_12x2();
        dinheiroGastoClientePorMes = new Matriz_Double_12x2();
        produtosCliente = new TreeMap<>();
    }

    public FichaClienteCompras(FichaClienteCompras ficha) {
        numUnidadesCompradasClientePorMes = ficha.getNumUnidadesCompradasClientePorMes();
        numComprasClientePorMes = ficha.getNumComprasClientePorMes();
        dinheiroGastoClientePorMes = ficha.getDinheiroGastoClientePorMes();

        for (Map.Entry<Produto, FichaProdutoDeClienteCompras> entrada : ficha.produtosCliente.entrySet()) {
            this.produtosCliente.put(entrada.getKey().clone(), entrada.getValue().clone());
        }
    }

    public void regista_compra(Compra compra) {
        FichaProdutoDeClienteCompras ficha_produto;
        int unidades_compradas = compra.getQuantidade();
        double preco = compra.getPreco();
        Mes mes = compra.getMes();
        TipoCompra tipo_compra = compra.getTipoCompra();

        this.numUnidadesCompradasClientePorMes.addValorMesTipoCompra(mes, tipo_compra, unidades_compradas);
        this.numComprasClientePorMes.addValorMesTipoCompra(mes, tipo_compra, 1);
        this.dinheiroGastoClientePorMes.addValorMesTipoCompra(mes, tipo_compra, preco * (unidades_compradas + 0.0));

        ficha_produto = this.getFichaProdutoNoClone(compra.getProduto());

        if (ficha_produto == null) {
            ficha_produto = new FichaProdutoDeClienteCompras();
            this.produtosCliente.put(compra.getProduto().clone(),ficha_produto);
        }
        
        ficha_produto.addNumComprasProdutoClienteMes(mes, tipo_compra, 1);
        ficha_produto.addNumUnidadesCompradasProdutoClienteMes(mes, tipo_compra, unidades_compradas);
    }
    /*
     GETTERS
     */


    public FichaProdutoDeClienteCompras getFichaProduto(Produto produto) {
        return this.produtosCliente.get(produto).clone();
    }

    private FichaProdutoDeClienteCompras getFichaProdutoNoClone(Produto produto) {
        return this.produtosCliente.get(produto);
    }
    
    public boolean clienteComprouProduto(Produto produto){
        return this.produtosCliente.containsKey(produto);
    }
    
    /* Nº UNIDADES */
    public Matriz_Int_12x2 getNumUnidadesCompradasClientePorMes() {
        return numUnidadesCompradasClientePorMes.clone();
    }

    public int getNumUnidadesCompradasMes(Mes mes, TipoCompra tipo_compra) {
        return this.numUnidadesCompradasClientePorMes.getValorMesTipoCompra(mes, tipo_compra);
    }

    public int getNumUnidadesCompradasMeses(Mes mes1, Mes mes2, TipoCompra tipo_compra) {
        return this.numUnidadesCompradasClientePorMes.getValorEntreMeses(mes1, mes2, tipo_compra);
    }
    
    public int getTotalUnidadesCompradas(){
        return this.numUnidadesCompradasClientePorMes.getSomaTotal();
    }

    /* Nº COMPRAS */
    public Matriz_Int_12x2 getNumComprasClientePorMes() {
        return numComprasClientePorMes.clone();
    }

    public int getNumComprasMes(Mes mes, TipoCompra tipo_compra) {
        return this.numComprasClientePorMes.getValorMesTipoCompra(mes, tipo_compra);
    }

    public int getNumComprasMeses(Mes mes1, Mes mes2, TipoCompra tipo_compra) {
        return this.numComprasClientePorMes.getValorEntreMeses(mes1, mes2, tipo_compra);
    }
    
    public int getTotalCompras(){
        return this.numComprasClientePorMes.getSomaTotal();
    }

    /* € GASTO */
    public Matriz_Double_12x2 getDinheiroGastoClientePorMes() {
        return dinheiroGastoClientePorMes.clone();
    }

    public double getDinheiroGastoClientePorMes(Mes mes, TipoCompra tipo_compra) {
        return dinheiroGastoClientePorMes.getValorMesTipoCompra(mes, tipo_compra);
    }

    public double getDinheiroGastoClienteMeses(Mes mes1, Mes mes2, TipoCompra tipo_compra) {
        return dinheiroGastoClientePorMes.getValorEntreMeses(mes1, mes2, tipo_compra);
    }
    
    public double getTotalDinheiroGasto(){
        return this.dinheiroGastoClientePorMes.getSomaTotal();
    }
    
    public List<Produto> getProdutosCliente() {
        ArrayList<Produto> resultado = new ArrayList<>();
        for (Produto produto : this.produtosCliente.keySet()) {
            resultado.add(produto.clone());
        }
        return (List<Produto>) resultado;
    }

    /*
     SETTERS
     */
    
    /* Nº UNIDADES */
    public void setNumUnidadesCompradasClientePorMes(Matriz_Int_12x2 numUnidadesCompradasClientePorMes) {
        this.numUnidadesCompradasClientePorMes = numUnidadesCompradasClientePorMes.clone();
    }

    public void setNumUnidadesCompradasClienteMes(Mes mes, TipoCompra tipo_compra, int valor) {
        this.numUnidadesCompradasClientePorMes.setValorMesTipoCompra(mes, tipo_compra, valor);
    }

    public void addNumUnidadesCompradasClienteMes(Mes mes, TipoCompra tipo_compra, int valor) {
        this.numUnidadesCompradasClientePorMes.addValorMesTipoCompra(mes, tipo_compra, valor);
    }

    /* Nº COMPRAS */
    public void setNumComprasClientePorMes(Matriz_Int_12x2 numComprasClientePorMes) {
        this.numComprasClientePorMes = numComprasClientePorMes.clone();
    }

    public void setNumComprasClienteMes(Mes mes, TipoCompra tipo_compra, int valor) {
        this.numComprasClientePorMes.setValorMesTipoCompra(mes, tipo_compra, valor);
    }

    public void addNumComprasClienteMes(Mes mes, TipoCompra tipo_compra, int valor) {
        this.numComprasClientePorMes.addValorMesTipoCompra(mes, tipo_compra, valor);
    }

    /* € GASTO */
    public void setDinheiroGastoClientePorMes(Matriz_Double_12x2 dinheiroGastoClientePorMes) {
        this.dinheiroGastoClientePorMes = dinheiroGastoClientePorMes.clone();
    }

    public void setDinheiroGastoClienteMes(Mes mes, TipoCompra tipo_compra, double valor) {
        this.dinheiroGastoClientePorMes.setValorMesTipoCompra(mes, tipo_compra, valor);
    }

    public void addDinheiroGastoClienteMes(Mes mes, TipoCompra tipo_compra, double valor) {
        this.dinheiroGastoClientePorMes.addValorMesTipoCompra(mes, tipo_compra, valor);
    }


    /*
     METODOS INSTANCIA
     */
    public boolean produtoExiste(Produto produto) {
        return this.produtosCliente.containsKey(produto);
    }
    
    
    /*
     METODOS STANDARD
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.numUnidadesCompradasClientePorMes);
        hash = 53 * hash + Objects.hashCode(this.numComprasClientePorMes);
        hash = 53 * hash + Objects.hashCode(this.dinheiroGastoClientePorMes);
        hash = 53 * hash + Objects.hashCode(this.produtosCliente);
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
        final FichaClienteCompras other = (FichaClienteCompras) obj;
        return this.dinheiroGastoClientePorMes.equals(other.getDinheiroGastoClientePorMes())
                && this.numComprasClientePorMes.equals(other.getNumComprasClientePorMes())
                && this.numUnidadesCompradasClientePorMes.equals(other.getNumUnidadesCompradasClientePorMes())
                && this.produtosCliente.size() == other.produtosCliente.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FichaClienteCompras{");
        sb.append(", Total unidades compras=").append(numUnidadesCompradasClientePorMes.getSomaTotal());
        sb.append(", Total Compras=").append(numComprasClientePorMes.getSomaTotal());
        sb.append(", € Gasto=").append(dinheiroGastoClientePorMes.getSomaTotal());
        sb.append(", Nº Produtos Cliente=").append(produtosCliente.size());
        sb.append('}');

        return sb.toString();
    }

    @Override
    public FichaClienteCompras clone() {
        return new FichaClienteCompras(this);
    }

}
