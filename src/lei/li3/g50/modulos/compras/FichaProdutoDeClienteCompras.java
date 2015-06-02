package lei.li3.g50.modulos.compras;

import java.util.Objects;
import lei.li3.g50.modulos.dados.Mes;
import lei.li3.g50.modulos.dados.Produto;
import lei.li3.g50.modulos.dados.TipoCompra;
import lei.li3.g50.utilitarios.Matriz_Int_12x2;

public class FichaProdutoDeClienteCompras {

    private Produto produto;
    private Matriz_Int_12x2 numUnidadesCompradasProdutoClientePorMes;
    private Matriz_Int_12x2 numComprasProdutoClientePorMes;

    /*
     CONSTRUCTORES
     */
    public FichaProdutoDeClienteCompras() {
        produto = new Produto();
        numUnidadesCompradasProdutoClientePorMes = new Matriz_Int_12x2();
        numComprasProdutoClientePorMes = new Matriz_Int_12x2();
    }

    public FichaProdutoDeClienteCompras(Produto produto) {
        this.produto = produto.clone();
        numUnidadesCompradasProdutoClientePorMes = new Matriz_Int_12x2();
        numComprasProdutoClientePorMes = new Matriz_Int_12x2();
    }

    public FichaProdutoDeClienteCompras(FichaProdutoDeClienteCompras ficha) {
        this.produto = ficha.getProduto();
        numUnidadesCompradasProdutoClientePorMes = ficha.getNumUnidadesCompradasProdutoClientePorMes();
        numComprasProdutoClientePorMes = ficha.getNumComprasProdutoClientePorMes();
    }

    /*
     GET'S
     */
    public Produto getProduto() {
        return produto.clone();
    }

    public Matriz_Int_12x2 getNumUnidadesCompradasProdutoClientePorMes() {
        return numUnidadesCompradasProdutoClientePorMes.clone();
    }

    public Matriz_Int_12x2 getNumComprasProdutoClientePorMes() {
        return numComprasProdutoClientePorMes.clone();
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
        return numUnidadesCompradasProdutoClientePorMes.getValorEntreMeses(mes2, mes2, tipo_compra);
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
        return numComprasProdutoClientePorMes.getValorEntreMeses(mes2, mes2, tipo_compra);
    }


    /*
     SET'S
     */
    public void setProduto(Produto produto) {
        this.produto = produto.clone();
    }

    public void setNumUnidadesCompradasProdutoClientePorMes(Matriz_Int_12x2 numUnidadesCompradasProdutoClientePorMes) {
        this.numUnidadesCompradasProdutoClientePorMes = numUnidadesCompradasProdutoClientePorMes.clone();
    }

    public void setNumComprasProdutoClientePorMes(Matriz_Int_12x2 numComprasProdutoClientePorMes) {
        this.numComprasProdutoClientePorMes = numComprasProdutoClientePorMes.clone();
    }
    
    public void setNumComprasProdutoClienteMes(Mes mes, TipoCompra tipo_compra, int valor) {
        this.numComprasProdutoClientePorMes.setValorMesTipoCompra(mes, tipo_compra, valor);
    }
    
    public void addNumComprasProdutoClienteMes(Mes mes, TipoCompra tipo_compra, int valor) {
        this.numComprasProdutoClientePorMes.addValorMesTipoCompra(mes, tipo_compra, valor);
    }
    
    public void setNumUnidadesCompradasProdutoClienteMes(Mes mes, TipoCompra tipo_compra, int valor) {
        this.numUnidadesCompradasProdutoClientePorMes.setValorMesTipoCompra(mes, tipo_compra, valor);
    }
    
    public void addNumUnidadesCompradasProdutoClienteMes(Mes mes, TipoCompra tipo_compra, int valor) {
        this.numUnidadesCompradasProdutoClientePorMes.addValorMesTipoCompra(mes, tipo_compra, valor);
    }
    
    
    /*
    METODOS STANDARD
    */
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.produto);
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
        return this.produto.equals(other.getProduto());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("FichaProdutoDeClienteCompras{");
        sb.append("Produto=").append(produto.toString());
        sb.append(", Total unidade compradas=").append(this.numUnidadesCompradasProdutoClientePorMes.getSomaTotal());
        sb.append(", Total compras=").append(this.numComprasProdutoClientePorMes.getSomaTotal());
        sb.append('}');

        return sb.toString();
    }
    
    
    @Override
    public FichaProdutoDeClienteCompras clone(){
        return new FichaProdutoDeClienteCompras(this);
    }
}
