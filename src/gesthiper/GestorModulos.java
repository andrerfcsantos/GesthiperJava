package gesthiper;

import dados.Produto;
import modulos.*;

public final class GestorModulos {

    public static CatalogoClientes mod_cat_clientes;
    public static CatalogoProdutos mod_cat_produtos;
    public static Contabilidade mod_contabilidade;
    public static Compras mod_compras;

    private GestorModulos(){}
    
     public static void inicializaModulos(){
        mod_cat_clientes = new CatalogoClientes();
        mod_cat_produtos =  new CatalogoProdutos();
        mod_contabilidade = new Contabilidade();
        mod_compras = new Compras();
    }
     
     public static void regista_produto(Produto produto){
         mod_cat_produtos.insere_produto(produto);
     }
}
