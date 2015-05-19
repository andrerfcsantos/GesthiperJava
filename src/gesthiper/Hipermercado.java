package gesthiper;

import dados.*;
import modulos.*;

public final class Hipermercado {

    public static CatalogoClientes mod_cat_clientes;
    public static CatalogoProdutos mod_cat_produtos;
    public static Contabilidade mod_contabilidade;
    public static Compras mod_compras;

    private Hipermercado(){}
    
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
