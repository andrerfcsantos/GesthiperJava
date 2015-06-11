package lei.li3.g50.gesthiper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import lei.li3.g50.modulos.catalogos.*;
import lei.li3.g50.modulos.compras.*;
import lei.li3.g50.modulos.contabilidade.*;
import lei.li3.g50.modulos.dados.*;

public class Hipermercado implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -9073031387966524796L;
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

    public ArrayList<String> getComprasInvalidas() {
        return comprasInvalidas;
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

    public void setComprasInvalidas(ArrayList<String> comprasInvalidas) {
        this.comprasInvalidas = comprasInvalidas;
    }
    
    public void addCompraInvalida(String linhaInvalida) {
        this.comprasInvalidas.add(linhaInvalida);
    }
    
    
    public void mudaFicheiroCompras(String pathNovoFicheiro) throws IOException {
        CatalogoClientes backup_catalogo_clientes = this.moduloCatalogoClientes;
        CatalogoProdutos backup_catalogo_produtos = this.moduloCatalogoProdutos;
        Compras backup_compras = this.moduloCompras;
        Contabilidade backup_contabilidade = this.moduloContabilidade;
        ArrayList<String> backup_compras_invalidas = this.comprasInvalidas;
        this.moduloCatalogoClientes = new CatalogoClientes();
        this.moduloCatalogoProdutos = new CatalogoProdutos();
        this.moduloContabilidade = new Contabilidade();
        this.moduloCompras = new Compras();
        this.comprasInvalidas = new ArrayList<>();

        try {
            LeituraFicheiros.le_ficheiro_clientes(this.ficheiro_clientes.getPath());
            LeituraFicheiros.le_ficheiro_produtos(this.ficheiro_produtos.getPath());
            LeituraFicheiros.le_ficheiro_compras(pathNovoFicheiro);
        } catch (IOException e) {
            this.moduloCatalogoClientes = backup_catalogo_clientes;
            this.moduloCatalogoProdutos = backup_catalogo_produtos;
            this.moduloContabilidade = backup_contabilidade;
            this.moduloCompras = backup_compras;
            this.comprasInvalidas = backup_compras_invalidas;
            throw e;
        }
    }

    public void guardaComprasInvalidas(String ficheiro) throws FileNotFoundException{
        PrintWriter pw = new PrintWriter(ficheiro);
        for(String linha : this.comprasInvalidas)
            pw.print(linha+"\n");
        
        pw.flush();
        pw.close();
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
