package lei.li3.g50.gesthiper;

import java.io.Serializable;
import lei.li3.g50.modulos.catalogos.*;
import lei.li3.g50.modulos.compras.*;
import lei.li3.g50.modulos.contabilidade.*;
import lei.li3.g50.modulos.dados.*;

public class Hipermercado implements Serializable  {

	private CatalogoClientes moduloCatalogoClientes;
	private CatalogoProdutos moduloCatalogoProdutos;
	private Contabilidade moduloContabilidade;
	private Compras moduloCompras;

	public Hipermercado() {
		moduloCatalogoClientes = new CatalogoClientes();
		moduloCatalogoProdutos = new CatalogoProdutos();
		moduloContabilidade = new Contabilidade();
		moduloCompras = new Compras();
	}
        
        /*
        GET'S DOS MODULOS
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
        
        
        /*
        OPERAÇÕES SOBRE HIPERMERCADO
        */
	public void regista_produto(Produto produto) {
		moduloCatalogoProdutos.insere_produto(produto);
                moduloCompras.registaProduto(produto);
	}

	public void regista_cliente(Cliente cliente) {
                moduloCatalogoClientes.insere_cliente(cliente);
                moduloCompras.registaCliente(cliente);
	}

	public void regista_compra(Compra compra) {
                moduloCompras.registaCompra(compra);
	}
        
        
}
