package lei.li3.g50.gesthiper;

import lei.li3.g50.modulos.dados.Hipermercado;
import java.io.IOException;
import java.util.ArrayList;
import lei.li3.g50.excepcoes.ClienteNaoExisteException;
import lei.li3.g50.modulos.catalogos.CatalogoClientes;
import lei.li3.g50.modulos.catalogos.CatalogoProdutos;
import lei.li3.g50.modulos.compras.Compras;
import lei.li3.g50.modulos.contabilidade.Contabilidade;
import lei.li3.g50.modulos.dados.Cliente;

public class Gesthiper {

    public static Hipermercado hipermercado;

    public static void main(String[] args) {
        hipermercado = new Hipermercado();
        MenuLeitura.menuLeitura();
        MenuQueries.menuPrincipal();
    }

    public static Hipermercado getHipermercado() {
        return hipermercado;
    }

    public static void setHipermercado(Hipermercado novo_hiper) {
        hipermercado = novo_hiper;
    }

    public static void mudaFicheiroCompras(String pathNovoFicheiro) throws IOException {
        CatalogoClientes backup_catalogo_clientes = hipermercado.getCatalogoClientes();
        CatalogoProdutos backup_catalogo_produtos = hipermercado.getCatalogoProdutos();
        Compras backup_compras = hipermercado.getCompras();
        Contabilidade backup_contabilidade = hipermercado.getContabilidade();
        ArrayList<String> backup_compras_invalidas = hipermercado.getComprasInvalidas();

        hipermercado.setModuloCatalogoClientes(new CatalogoClientes());
        hipermercado.setModuloCatalogoProdutos(new CatalogoProdutos());
        hipermercado.setModuloContabilidade(new Contabilidade());
        hipermercado.setModuloCompras(new Compras());
        hipermercado.setComprasInvalidas(new ArrayList<>());

        try {
            LeituraFicheiros.le_ficheiro_clientes(hipermercado.getFicheiro_clientes().getPath());
            LeituraFicheiros.le_ficheiro_produtos(hipermercado.getFicheiro_produtos().getPath());
            LeituraFicheiros.le_ficheiro_compras(pathNovoFicheiro);
        } catch (IOException e) {
            hipermercado.setModuloCatalogoClientes(backup_catalogo_clientes);
            hipermercado.setModuloCatalogoProdutos(backup_catalogo_produtos);
            hipermercado.setModuloContabilidade(backup_contabilidade);
            hipermercado.setModuloCompras(backup_compras);
            hipermercado.setComprasInvalidas(backup_compras_invalidas);
            throw e;
        }

    }

    public static void testes_aos_modulos() {
        System.out.print("" + hipermercado.getCatalogoClientes().toString() + "\n");
        System.out.print("" + hipermercado.getCatalogoProdutos().toString() + "\n");
        System.out.print("" + hipermercado.getContabilidade().toString() + "\n");
        System.out.print("" + hipermercado.getCompras().toString() + "\n");
        try {
            System.out.print(" Compras do cliente CW786: " + hipermercado.getCompras().getTotalComprasCliente(new Cliente("CW786")) + "\n");
        } catch (ClienteNaoExisteException ex) {
            System.out.print("O cliente nao existe\n");
        }
    }
}
