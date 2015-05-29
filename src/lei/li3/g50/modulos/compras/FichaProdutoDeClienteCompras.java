package lei.li3.g50.modulos.compras;

import java.util.Objects;
import lei.li3.g50.modulos.dados.Produto;
import lei.li3.g50.utilitarios.Matriz_Int_12x2;

public class FichaProdutoDeClienteCompras {

    private Produto produto;
    private Matriz_Int_12x2 numUnidadesCompradasProdutoClientePorMes;
    private Matriz_Int_12x2 numComprasProdutoClientePorMes;
    private int numUnidadesCompradasProdutoCliente;
    private int numComprasProdutoCliente;

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
        return numUnidadesCompradasProdutoCliente;
    }

    public int getNumComprasProdutoCliente() {
        return numComprasProdutoCliente;
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

    public void setNumUnidadesCompradasProdutoCliente(int numUnidadesCompradasProdutoCliente) {
        this.numUnidadesCompradasProdutoCliente = numUnidadesCompradasProdutoCliente;
    }

    public void setNumComprasProdutoCliente(int numComprasProdutoCliente) {
        this.numComprasProdutoCliente = numComprasProdutoCliente;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.produto);
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
        final FichaProdutoDeClienteCompras other = (FichaProdutoDeClienteCompras) obj;
        return this.produto.equals(other.getProduto());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("FichaProdutoDeClienteCompras{");
        sb.append("produto=");
        sb.append(produto.toString());
        sb.append(", numUnidadesCompradasProdutoCliente=");
        sb.append(numUnidadesCompradasProdutoCliente);
        sb.append(", numComprasProdutoCliente=");
        sb.append(numComprasProdutoCliente);
        sb.append('}');
        
        return sb.toString();
    }

}
