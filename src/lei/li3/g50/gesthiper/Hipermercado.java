package lei.li3.g50.gesthiper;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static lei.li3.g50.gesthiper.Leitura.le_ficheiro_compras;
import lei.li3.g50.modulos.catalogos.*;
import lei.li3.g50.modulos.compras.*;
import lei.li3.g50.modulos.contabilidade.*;
import lei.li3.g50.modulos.dados.*;

public class Hipermercado implements Serializable {
    
    ArrayList<String> comprasInvalidas;
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

    public File getFicheiro_clientes() {
        return ficheiro_clientes;
    }

    public File getFicheiro_produtos() {
        return ficheiro_produtos;
    }

    public File getFicheiro_compras() {
        return ficheiro_compras;
    }
    
    public int getNumeroComprasInvalidas(){
        return this.comprasInvalidas.size();
    }

    /*
     SETTERS
     */
    public void setFicheiro_clientes(File ficheiro_clientes) {
        this.ficheiro_clientes = ficheiro_clientes;
    }

    public void setFicheiro_produtos(File ficheiro_produtos) {
        this.ficheiro_produtos = ficheiro_produtos;
    }

    public void setFicheiro_compras(File ficheiro_compras) {
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
    
    public void addCompraInvalida(String linhaInvalida){
        this.comprasInvalidas.add(linhaInvalida);
    }
    
    public void mudaFicheiroCompras(String pathNovoFicheiro){
        this.moduloCompras = new Compras();
        this.moduloContabilidade = new Contabilidade();
        try {
            le_ficheiro_compras(pathNovoFicheiro);
        } catch (IOException ex) {
            Logger.getLogger(Hipermercado.class.getName()).log(Level.SEVERE, null, ex);
        }
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

}
