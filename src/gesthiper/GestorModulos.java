package gesthiper;

import modulos.*;

public class GestorModulos {

    private CatalogoClientes mod_cat_clientes;
    private CatalogoProdutos mod_cat_produtos;
    private Contabilidade mod_contabilidade;
    private Compras mod_compras;

    public GestorModulos() {
        mod_cat_clientes = new CatalogoClientes();
        mod_cat_produtos = new CatalogoProdutos();
        mod_contabilidade = new Contabilidade();
        mod_compras = new Compras();
    }
    
}
