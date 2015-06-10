package lei.li3.g50.modulos.contabilidade;

import java.io.Serializable;
import java.util.Objects;

import lei.li3.g50.modulos.dados.Mes;
import lei.li3.g50.modulos.dados.TipoCompra;
import lei.li3.g50.utilitarios.Matriz_Double_12x2;
import lei.li3.g50.utilitarios.Matriz_Int_12x2;

public class FichaProdutoContabilidade implements Serializable {

    private static final long serialVersionUID = -3086668118672328393L;
    private Matriz_Int_12x2 numUnidadesProdutoVendidasPorMes;
    private Matriz_Int_12x2 numComprasProdutoPorMes;
    private Matriz_Double_12x2 facturacaoProdutoPorMes;

    /* CONSTRUCTORES */
    public FichaProdutoContabilidade() {
        super();
        this.numUnidadesProdutoVendidasPorMes = new Matriz_Int_12x2();
        this.numComprasProdutoPorMes = new Matriz_Int_12x2();
        this.facturacaoProdutoPorMes = new Matriz_Double_12x2();
    }

    public FichaProdutoContabilidade(Matriz_Int_12x2 numUnidadesProdutoVendidasPorMes,
            Matriz_Int_12x2 numComprasProdutoPorMes, Matriz_Double_12x2 facturacaoProdutoPorMes) {
        super();
        this.numUnidadesProdutoVendidasPorMes = numUnidadesProdutoVendidasPorMes;
        this.numComprasProdutoPorMes = numComprasProdutoPorMes;
        this.facturacaoProdutoPorMes = facturacaoProdutoPorMes;
    }

    public FichaProdutoContabilidade(FichaProdutoContabilidade ficha) {
        super();
        this.numUnidadesProdutoVendidasPorMes = ficha.getMatrizNumUnidadesProdutoVendidasPorMes();
        this.numComprasProdutoPorMes = ficha.getMatrizNumComprasProdutoPorMes();
        this.facturacaoProdutoPorMes = ficha.getMatrizFacturacaoProdutoPorMes();
    }

    /* GETTERS */
    /* NUMERO COMPRAS*/
    public Matriz_Int_12x2 getMatrizNumComprasProdutoPorMes() {
        return numComprasProdutoPorMes.clone();
    }

    public int getNumComprasProduto() {
        return numComprasProdutoPorMes.getSomaTotal();
    }

    public int getNumComprasProdutoMes(Mes mes) {
        return numComprasProdutoPorMes.getValorMesTipoCompra(mes, TipoCompra.AMBOS);
    }

    public int getNumComprasProdutoPorMesPorTipoCompra(Mes mes, TipoCompra tipo_compra) {
        return this.numComprasProdutoPorMes.getValorMesTipoCompra(mes, tipo_compra);
    }

    public int getNumComprasProdutoIntervaloDeMeses(Mes mes1, Mes mes2, TipoCompra tipo_compra) {
        return numComprasProdutoPorMes.getValorEntreMeses(mes1, mes2, tipo_compra);
    }

    /* UNIDADES VENDIDAS*/
    public Matriz_Int_12x2 getMatrizNumUnidadesProdutoVendidasPorMes() {
        return numUnidadesProdutoVendidasPorMes.clone();
    }

    public int getNumUnidadesProdutoVendidas() {
        return numUnidadesProdutoVendidasPorMes.getSomaTotal();
    }

    public int getNumUnidadesProdutoVendidasMes(Mes mes) {
        return numUnidadesProdutoVendidasPorMes.getValorMesTipoCompra(mes, TipoCompra.AMBOS);
    }

    public int getNumUnidadesProdutoVendidasPorMesPorTipoCompra(Mes mes, TipoCompra tipo_compra) {
        return this.numUnidadesProdutoVendidasPorMes.getValorMesTipoCompra(mes, tipo_compra);
    }

    public int getNumUnidadesProdutoVendidasIntervaloDeMeses(Mes mes1, Mes mes2, TipoCompra tipo_compra) {
        return numUnidadesProdutoVendidasPorMes.getValorEntreMeses(mes1, mes2, tipo_compra);
    }

    /* FACTURACAO*/
    public Matriz_Double_12x2 getMatrizFacturacaoProdutoPorMes() {
        return facturacaoProdutoPorMes.clone();
    }

    public double getFacturacaoProduto() {
        return facturacaoProdutoPorMes.getSomaTotal();
    }

    public double getFacturacaoProdutoMes(Mes mes) {
        return facturacaoProdutoPorMes.getValorMesTipoCompra(mes, TipoCompra.AMBOS);
    }

    public double getFacturacaoProdutoPorMesPorTipoCompra(Mes mes, TipoCompra tipo_compra) {
        return this.facturacaoProdutoPorMes.getValorMesTipoCompra(mes, tipo_compra);
    }

    public double getFacturacaoProdutoIntervaloDeMeses(Mes mes1, Mes mes2, TipoCompra tipo_compra) {
        return facturacaoProdutoPorMes.getValorEntreMeses(mes1, mes2, tipo_compra);
    }

    /* SETTERS */
    /* UNIDADES VENDIDAS */
    public void setMatrizNumUnidadesProdutoVendidasPorMes(Matriz_Int_12x2 numUnidadesProdutoVendidasPorMes) {
        this.numUnidadesProdutoVendidasPorMes = numUnidadesProdutoVendidasPorMes.clone();
    }

    public void setNumUnidadesProdutoVendidasPorMesPorTipoCompra(Mes mes, TipoCompra tipo_compra, int valor) {
        this.numUnidadesProdutoVendidasPorMes.setValorMesTipoCompra(mes, tipo_compra, valor);
    }

    public void addNumUnidadesProdutoVendidasPorMesPorTipoCompra(Mes mes, TipoCompra tipo_compra, int valor) {
        this.numUnidadesProdutoVendidasPorMes.addValorMesTipoCompra(mes, tipo_compra, valor);
    }

    /* COMPRAS */
    public void setNumComprasProdutoPorMesPorTipoCompra(Mes mes, TipoCompra tipo_compra, int valor) {
        this.numComprasProdutoPorMes.setValorMesTipoCompra(mes, tipo_compra, valor);
    }

    public void addNumComprasProdutoPorMesPorTipoCompra(Mes mes, TipoCompra tipo_compra, int valor) {
        this.numComprasProdutoPorMes.addValorMesTipoCompra(mes, tipo_compra, valor);
    }

    public void setMatrizNumComprasProdutoPorMes(Matriz_Int_12x2 numComprasProdutoPorMes) {
        this.numComprasProdutoPorMes = numComprasProdutoPorMes.clone();
    }

    /* FACTURACAO */
    public void setMatrizFacturacaoProdutoPorMes(Matriz_Double_12x2 facturacaoProdutoPorMes) {
        this.facturacaoProdutoPorMes = facturacaoProdutoPorMes.clone();
    }

    public void setFacturacaoProdutoPorMesPorTipoCompra(Mes mes, TipoCompra tipo_compra, double valor) {
        this.facturacaoProdutoPorMes.setValorMesTipoCompra(mes, tipo_compra, valor);
    }

    public void addFacturacaoProdutoPorMesPorTipoCompra(Mes mes, TipoCompra tipo_compra, double valor) {
        this.facturacaoProdutoPorMes.addValorMesTipoCompra(mes, tipo_compra, valor);
    }

    /*
     METODOS STANDARD
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.numUnidadesProdutoVendidasPorMes);
        hash = 37 * hash + Objects.hashCode(this.numComprasProdutoPorMes);
        hash = 37 * hash + Objects.hashCode(this.facturacaoProdutoPorMes);
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
        FichaProdutoContabilidade other = (FichaProdutoContabilidade) obj;
        return this.numUnidadesProdutoVendidasPorMes.equals(other.numUnidadesProdutoVendidasPorMes)
                && this.numComprasProdutoPorMes.equals(other.numComprasProdutoPorMes)
                && this.facturacaoProdutoPorMes.equals(other.facturacaoProdutoPorMes);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("FichaProdutoContabilidade {");
        sb.append("Unidades Vendidas=").append(numUnidadesProdutoVendidasPorMes.getSomaTotal());
        sb.append(", Nº Compras=").append(numComprasProdutoPorMes.getSomaTotal());
        sb.append(", Total Facturado=").append(facturacaoProdutoPorMes.getSomaTotal());
        sb.append("]");
        return sb.toString();
    }

    @Override
    public FichaProdutoContabilidade clone() {
        return new FichaProdutoContabilidade(this);
    }

}
