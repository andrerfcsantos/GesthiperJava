package lei.li3.g50.modulos.compras;

import java.io.Serializable;
import java.util.Objects;
import lei.li3.g50.modulos.dados.Mes;
import lei.li3.g50.modulos.dados.TipoCompra;
import lei.li3.g50.utilitarios.Matriz_Int_12x2;

public class FichaProdutoDeClienteCompras implements Serializable {

    
    private static final long serialVersionUID = 8460055101460936902L;
    private Matriz_Int_12x2 numUnidadesCompradasProdutoClientePorMes;
    private Matriz_Int_12x2 numComprasProdutoClientePorMes;
    private double totalGastoClienteProduto;

    /*
     CONSTRUCTORES
     */
    public FichaProdutoDeClienteCompras() {
        numUnidadesCompradasProdutoClientePorMes = new Matriz_Int_12x2();
        numComprasProdutoClientePorMes = new Matriz_Int_12x2();
        totalGastoClienteProduto = 0;
    }

    public FichaProdutoDeClienteCompras(Matriz_Int_12x2 unidades, Matriz_Int_12x2 compras, double total_gasto) {
        numUnidadesCompradasProdutoClientePorMes = unidades.clone();
        numComprasProdutoClientePorMes = compras.clone();
        totalGastoClienteProduto = total_gasto;
    }

    public FichaProdutoDeClienteCompras(FichaProdutoDeClienteCompras ficha) {
        numUnidadesCompradasProdutoClientePorMes = ficha.getNumUnidadesCompradasProdutoClientePorMes();
        numComprasProdutoClientePorMes = ficha.getNumComprasProdutoClientePorMes();
        totalGastoClienteProduto = ficha.getTotalGastoClienteProduto();
    }

    /*
     GET'S
     */
    public double getTotalGastoClienteProduto() {
        return totalGastoClienteProduto;
    }

    /* Nº UNIDADES COMPRADAS*/
    public Matriz_Int_12x2 getNumUnidadesCompradasProdutoClientePorMes() {
        return numUnidadesCompradasProdutoClientePorMes.clone();
    }

    public int getNumUnidadesCompradasProdutoCliente() {
        return numUnidadesCompradasProdutoClientePorMes.getSomaTotal();
    }

    public int getNumUnidadesCompradasProdutoClienteMes(Mes mes) {
        return numUnidadesCompradasProdutoClientePorMes.getValorMesTipoCompra(mes, TipoCompra.AMBOS);
    }

    public int getNumUnidadesCompradasProdutoClienteMes(Mes mes, TipoCompra tipo_compra) {
        return numUnidadesCompradasProdutoClientePorMes.getValorMesTipoCompra(mes, tipo_compra);
    }

    public int getNumUnidadesCompradasProdutoClienteMeses(Mes mes1, Mes mes2, TipoCompra tipo_compra) {
        return numUnidadesCompradasProdutoClientePorMes.getValorEntreMeses(mes1, mes2, tipo_compra);
    }

    /*Nº COMPRAS*/
    public Matriz_Int_12x2 getNumComprasProdutoClientePorMes() {
        return numComprasProdutoClientePorMes.clone();
    }

    public int getNumComprasProdutoCliente() {
        return this.numComprasProdutoClientePorMes.getSomaTotal();
    }

    public int getNumComprasProdutoClienteMes(Mes mes) {
        return numComprasProdutoClientePorMes.getValorMesTipoCompra(mes, TipoCompra.AMBOS);
    }

    public int getNumComprasProdutoClienteMes(Mes mes, TipoCompra tipo_compra) {
        return numComprasProdutoClientePorMes.getValorMesTipoCompra(mes, tipo_compra);
    }

    public int getNumComprasProdutoClienteMeses(Mes mes1, Mes mes2, TipoCompra tipo_compra) {
        return numComprasProdutoClientePorMes.getValorEntreMeses(mes1, mes2, tipo_compra);
    }


    /*
     SET'S
     */
    public void setTotalGastoClienteProduto(double totalGastoClienteProduto) {
        this.totalGastoClienteProduto = totalGastoClienteProduto;
    }

    public void addTotalGastoClienteProduto(double valor) {
        this.totalGastoClienteProduto += valor;
    }

    /* Nº UNIDADES COMPRADAS*/
    public void setNumUnidadesCompradasProdutoClientePorMes(Matriz_Int_12x2 numUnidadesCompradasProdutoClientePorMes) {
        this.numUnidadesCompradasProdutoClientePorMes = numUnidadesCompradasProdutoClientePorMes.clone();
    }

    public void setNumUnidadesCompradasProdutoClienteMes(Mes mes, TipoCompra tipo_compra, int valor) {
        this.numUnidadesCompradasProdutoClientePorMes.setValorMesTipoCompra(mes, tipo_compra, valor);
    }

    public void addNumUnidadesCompradasProdutoClienteMes(Mes mes, TipoCompra tipo_compra, int valor) {
        this.numUnidadesCompradasProdutoClientePorMes.addValorMesTipoCompra(mes, tipo_compra, valor);
    }

    /*Nº COMPRAS*/
    public void setNumComprasProdutoClientePorMes(Matriz_Int_12x2 numComprasProdutoClientePorMes) {
        this.numComprasProdutoClientePorMes = numComprasProdutoClientePorMes.clone();
    }

    public void setNumComprasProdutoClienteMes(Mes mes, TipoCompra tipo_compra, int valor) {
        this.numComprasProdutoClientePorMes.setValorMesTipoCompra(mes, tipo_compra, valor);
    }

    public void addNumComprasProdutoClienteMes(Mes mes, TipoCompra tipo_compra, int valor) {
        this.numComprasProdutoClientePorMes.addValorMesTipoCompra(mes, tipo_compra, valor);
    }

    /*
     METODOS STANDARD
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.numUnidadesCompradasProdutoClientePorMes);
        hash = 59 * hash + Objects.hashCode(this.numComprasProdutoClientePorMes);
        hash = 59 * hash + Objects.hashCode(this.totalGastoClienteProduto);
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
        final FichaProdutoDeClienteCompras other = (FichaProdutoDeClienteCompras) obj;
        return this.numComprasProdutoClientePorMes.equals(other.numComprasProdutoClientePorMes)
                && this.numUnidadesCompradasProdutoClientePorMes.equals(other.numUnidadesCompradasProdutoClientePorMes)
                && this.totalGastoClienteProduto == other.totalGastoClienteProduto;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("FichaProdutoDeClienteCompras{");
        sb.append(", Total unidade compradas=").append(this.numUnidadesCompradasProdutoClientePorMes.getSomaTotal());
        sb.append(", Total compras=").append(this.numComprasProdutoClientePorMes.getSomaTotal());
        sb.append(", Total Gasto=").append(this.totalGastoClienteProduto);
        sb.append('}');

        return sb.toString();
    }

    @Override
    public FichaProdutoDeClienteCompras clone() {
        return new FichaProdutoDeClienteCompras(this);
    }
}
