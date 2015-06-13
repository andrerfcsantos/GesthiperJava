package lei.li3.g50.modulos.dados;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import lei.li3.g50.modulos.catalogos.*;
import lei.li3.g50.modulos.compras.*;
import lei.li3.g50.modulos.contabilidade.*;

public class Hipermercado implements Serializable {

    private static final long serialVersionUID = -9073031387966524796L;
    private ArrayList<String> comprasInvalidas;
    private File ficheiro_clientes;
    private File ficheiro_produtos;
    private File ficheiro_compras;
    private CatalogoClientes moduloCatalogoClientes;
    private CatalogoProdutos moduloCatalogoProdutos;
    private Contabilidade moduloContabilidade;
    private Compras moduloCompras;

    public Hipermercado() {
        comprasInvalidas = new ArrayList<>();
        moduloCatalogoClientes = new CatalogoClientes();
        moduloCatalogoProdutos = new CatalogoProdutos();
        moduloContabilidade = new Contabilidade();
        moduloCompras = new Compras();
    }
    
    	public Hipermercado(ArrayList<String> comprasInvalidas,
			CatalogoClientes moduloCatalogoClientes,
			CatalogoProdutos moduloCatalogoProdutos,
			Contabilidade moduloContabilidade, Compras moduloCompras) {
		super();
		this.comprasInvalidas = comprasInvalidas;
		this.moduloCatalogoClientes = moduloCatalogoClientes;
		this.moduloCatalogoProdutos = moduloCatalogoProdutos;
		this.moduloContabilidade = moduloContabilidade;
		this.moduloCompras = moduloCompras;
		
	}

	
	public Hipermercado(ArrayList<String> comprasInvalidas,
			CatalogoClientes moduloCatalogoClientes,
			CatalogoProdutos moduloCatalogoProdutos,
			Contabilidade moduloContabilidade, Compras moduloCompras,
			File ficheiro_clientes, File ficheiro_produtos,
			File ficheiro_compras) {
		super();
		this.comprasInvalidas = comprasInvalidas;
		this.moduloCatalogoClientes = moduloCatalogoClientes;
		this.moduloCatalogoProdutos = moduloCatalogoProdutos;
		this.moduloContabilidade = moduloContabilidade;
		this.moduloCompras = moduloCompras;
		this.ficheiro_clientes = ficheiro_clientes;
		this.ficheiro_produtos = ficheiro_produtos;
		this.ficheiro_compras= ficheiro_compras;

	}
    
    public Hipermercado(Hipermercado hiper) {
        comprasInvalidas = hiper.getComprasInvalidas();
        moduloCatalogoClientes = hiper.getCatalogoClientes();
        moduloCatalogoProdutos = hiper.getCatalogoProdutos();
        moduloContabilidade = hiper.getContabilidade();
        moduloCompras = hiper.getCompras();
        ficheiro_clientes = hiper.getFicheiroClientes();
        ficheiro_produtos = hiper.getFicheiroProdutos();
        ficheiro_compras = hiper.getFicheiroCompras();
    }

    /*
     GETTERS
     */
    public CatalogoClientes getCatalogoClientes() {
        return moduloCatalogoClientes;
    }

    public CatalogoProdutos getCatalogoProdutos() {
        return moduloCatalogoProdutos;
    }

    public Contabilidade getContabilidade() {
        return moduloContabilidade;
    }

    public Compras getCompras() {
        return moduloCompras;
    }

    public File getFicheiroClientes() {
        return ficheiro_clientes;
    }

    public File getFicheiroProdutos() {
        return ficheiro_produtos;
    }

    public File getFicheiroCompras() {
        return ficheiro_compras;
    }

    public int getNumeroComprasInvalidas() {
        return this.comprasInvalidas.size();
    }

    public ArrayList<String> getComprasInvalidas() {
        return comprasInvalidas;
    }

    /*
     SETTERS
     */
    public void setFicheiroClientes(File ficheiro_clientes) {
        this.ficheiro_clientes = ficheiro_clientes;
    }

    public void setFicheiroProdutos(File ficheiro_produtos) {
        this.ficheiro_produtos = ficheiro_produtos;
    }

    public void setFicheiroCompras(File ficheiro_compras) {
        this.ficheiro_compras = ficheiro_compras;
    }

    public void setModuloCatalogoClientes(CatalogoClientes moduloCatalogoClientes) {
        this.moduloCatalogoClientes = moduloCatalogoClientes;
    }

    public void setModuloCatalogoProdutos(CatalogoProdutos moduloCatalogoProdutos) {
        this.moduloCatalogoProdutos = moduloCatalogoProdutos;
    }

    public void setModuloContabilidade(Contabilidade moduloContabilidade) {
        this.moduloContabilidade = moduloContabilidade;
    }

    public void setModuloCompras(Compras moduloCompras) {
        this.moduloCompras = moduloCompras;
    }

    public void setComprasInvalidas(ArrayList<String> comprasInvalidas) {
        this.comprasInvalidas = comprasInvalidas;
    }

    public void addCompraInvalida(String linhaInvalida) {
        this.comprasInvalidas.add(linhaInvalida);
    }

    /*
     OPERAÇÕES SOBRE HIPERMERCADO
     */
    public void regista_produto(Produto produto) {
        moduloCatalogoProdutos.insereProduto(produto);
        moduloCompras.registaProduto(produto);
        moduloContabilidade.registaProduto(produto);
    }

    public void regista_cliente(Cliente cliente) {
        moduloCatalogoClientes.insereCliente(cliente);
        moduloCompras.registaCliente(cliente);
    }

    public void regista_compra(Compra compra) {
        moduloCompras.registaCompra(compra);
        moduloContabilidade.registaCompra(compra);
    }

    public void guardaComprasInvalidas(String ficheiro) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(ficheiro);
        for (String linha : this.comprasInvalidas) {
            pw.print(linha + "\n");
        }

        pw.flush();
        pw.close();
    }
    
    /* METODOS STANDARD */

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.comprasInvalidas);
        hash = 97 * hash + Objects.hashCode(this.ficheiro_clientes);
        hash = 97 * hash + Objects.hashCode(this.ficheiro_produtos);
        hash = 97 * hash + Objects.hashCode(this.ficheiro_compras);
        hash = 97 * hash + Objects.hashCode(this.moduloCatalogoClientes);
        hash = 97 * hash + Objects.hashCode(this.moduloCatalogoProdutos);
        hash = 97 * hash + Objects.hashCode(this.moduloContabilidade);
        hash = 97 * hash + Objects.hashCode(this.moduloCompras);
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
        final Hipermercado other = (Hipermercado) obj;
        
        return this.ficheiro_clientes.equals(other.ficheiro_clientes)
                && this.ficheiro_produtos.equals(other.ficheiro_produtos)
                && this.ficheiro_compras.equals(other.ficheiro_compras)
                && this.comprasInvalidas.containsAll(other.comprasInvalidas)
                && this.moduloCatalogoClientes.equals(other.moduloCatalogoClientes)
                && this.moduloCatalogoProdutos.equals(other.moduloCatalogoProdutos)
                && this.moduloContabilidade.equals(other.moduloContabilidade)
                && this.moduloCompras.equals(other.moduloCompras);
    }
    
    
    
    
    
}
