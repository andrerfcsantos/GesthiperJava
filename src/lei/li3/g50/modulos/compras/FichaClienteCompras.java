package lei.li3.g50.modulos.compras;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;
import lei.li3.g50.modulos.dados.Cliente;
import lei.li3.g50.modulos.dados.Mes;
import lei.li3.g50.modulos.dados.Produto;
import lei.li3.g50.modulos.dados.TipoCompra;
import lei.li3.g50.utilitarios.ComparadorFichaProdutoDeClientePorCodigoDeProduto;
import lei.li3.g50.utilitarios.Matriz_Double_12x2;
import lei.li3.g50.utilitarios.Matriz_Int_12x2;

public class FichaClienteCompras {

    Cliente cliente;
    Matriz_Int_12x2 numUnidadesCompradasClientePorMes;
    Matriz_Int_12x2 numComprasClientePorMes;
    Matriz_Double_12x2 dinheiroGastoClientePorMes;
    TreeSet<FichaProdutoDeClienteCompras> produtosCliente;

    /*
     CONSTRUCTORES
     */
    public FichaClienteCompras() {
        cliente = new Cliente();
        numUnidadesCompradasClientePorMes = new Matriz_Int_12x2();
        numComprasClientePorMes = new Matriz_Int_12x2();
        dinheiroGastoClientePorMes = new Matriz_Double_12x2();
        produtosCliente = new TreeSet<>(new ComparadorFichaProdutoDeClientePorCodigoDeProduto());
    }

    public FichaClienteCompras(Cliente cliente) {
        this.cliente = cliente.clone();
        numUnidadesCompradasClientePorMes = new Matriz_Int_12x2();
        numComprasClientePorMes = new Matriz_Int_12x2();
        dinheiroGastoClientePorMes = new Matriz_Double_12x2();
        produtosCliente = new TreeSet<>(new ComparadorFichaProdutoDeClientePorCodigoDeProduto());
    }

    public FichaClienteCompras(FichaClienteCompras ficha) {
        this.cliente = ficha.getCliente();
        numUnidadesCompradasClientePorMes = ficha.getNumUnidadesCompradasClientePorMes();
        numComprasClientePorMes = ficha.getNumComprasClientePorMes();
        dinheiroGastoClientePorMes = ficha.getDinheiroGastoClientePorMes();

        for (FichaProdutoDeClienteCompras fichaProdCli : ficha.produtosCliente) {
            this.produtosCliente.add(fichaProdCli.clone());
        }
    }

    /*
     GETTERS
     */
    public Cliente getCliente() {
        return cliente.clone();
    }
    
    public FichaProdutoDeClienteCompras getFichaProduto(Produto produto){
        FichaProdutoDeClienteCompras ficha = null;
        FichaProdutoDeClienteCompras ficha_iterada;
        boolean encontrado=false;
        Iterator<FichaProdutoDeClienteCompras> it = this.produtosCliente.iterator();
        
        while(it.hasNext() && !encontrado){
            ficha_iterada = it.next();
            if(ficha_iterada.getProduto().equals(produto)){
                encontrado = true;
                ficha = ficha_iterada;
            }
        }
        
        return encontrado ? ficha.clone() : null;
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
    
    public List<FichaProdutoDeClienteCompras> getProdutosCliente() {
        ArrayList<FichaProdutoDeClienteCompras> resultado = new ArrayList<>();
        for (FichaProdutoDeClienteCompras ficha : this.produtosCliente) {
            resultado.add(ficha.clone());
        }
        return (List<FichaProdutoDeClienteCompras>) resultado;
    }

    /*
     SETTERS
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente.clone();
    }
    
    
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

    public void setProdutosCliente(TreeSet<FichaProdutoDeClienteCompras> produtosCliente) {
        this.produtosCliente = new TreeSet<>();
        for(FichaProdutoDeClienteCompras fichaProdCliente : produtosCliente){
            this.produtosCliente.add(fichaProdCliente.clone());
        }
    }

    
    /*
    METODOS INSTANCIA
    */
    
    public boolean produtoExiste(Produto produto){
        FichaProdutoDeClienteCompras produtoToFicha = new FichaProdutoDeClienteCompras(produto);
        return this.produtosCliente.contains(produtoToFicha);
    }
    
    /*
     METODOS STANDARD
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.cliente);
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
        return this.cliente.equals(other.getCliente())
                && this.dinheiroGastoClientePorMes.equals(other.getDinheiroGastoClientePorMes())
                && this.numComprasClientePorMes.equals(other.getNumComprasClientePorMes())
                && this.numUnidadesCompradasClientePorMes.equals(other.getNumUnidadesCompradasClientePorMes())
                && this.produtosCliente.containsAll(other.produtosCliente);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FichaClienteCompras{");
        sb.append("cliente=").append(cliente);
        sb.append(", Total unidades compras=").append(numUnidadesCompradasClientePorMes.getSomaTotal());
        sb.append(", Total Compras=").append(numComprasClientePorMes.getSomaTotal());
        sb.append(", € Gasto=").append(dinheiroGastoClientePorMes.getSomaTotal());
        sb.append(", Nº Produtos Cliente=").append(produtosCliente.size());
        sb.append('}');

        return sb.toString();
    }
    
    @Override
    public FichaClienteCompras clone(){
        return new FichaClienteCompras(this);
    }

}
