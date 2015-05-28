package lei.li3.g50.gesthiper;

import lei.li3.g50.modulos.catalogos.*;
import lei.li3.g50.modulos.compras.*;
import lei.li3.g50.modulos.contabilidade.*;
import lei.li3.g50.modulos.dados.*;

public class Hipermercado {

	private CatalogoClientes mod_cat_clientes;
	private CatalogoProdutos mod_cat_produtos;
	private Contabilidade mod_contabilidade;
	private Compras mod_compras;

	public Hipermercado() {
		mod_cat_clientes = new CatalogoClientes();
		mod_cat_produtos = new CatalogoProdutos();
		mod_contabilidade = new Contabilidade();
		mod_compras = new Compras();
	}

	public CatalogoClientes getMod_cat_clientes() {
		return mod_cat_clientes;
	}

	public CatalogoProdutos getMod_cat_produtos() {
		return mod_cat_produtos;
	}

	public Contabilidade getMod_contabilidade() {
		return mod_contabilidade;
	}

	public Compras getMod_compras() {
		return mod_compras;
	}

	public void regista_produto(Produto produto) {
		mod_cat_produtos.insere_produto(produto);
	}

	public void regista_cliente(Cliente cliente) {
                mod_cat_clientes.insere_cliente(cliente);
	}

	public void regista_compra(Compra compra) {

	}
}
