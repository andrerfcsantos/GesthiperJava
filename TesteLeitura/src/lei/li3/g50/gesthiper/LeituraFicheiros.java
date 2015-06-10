package lei.li3.g50.gesthiper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.StringTokenizer;
import lei.li3.g50.modulos.dados.Cliente;
import lei.li3.g50.modulos.dados.Compra;
import lei.li3.g50.modulos.dados.Mes;
import lei.li3.g50.modulos.dados.Produto;
import lei.li3.g50.modulos.dados.TipoCompra;

public class LeituraFicheiros {
    
    public static void produtosBufferedReaderParse(String str_ficheiro_produtos)
            throws FileNotFoundException, IOException {
    	//FIXME var não usada
        int i = 1;
        String linha;
        StringTokenizer st;
        Produto produto;
        BufferedReader bin;
        File ficheiro = new File(str_ficheiro_produtos);

        bin = new BufferedReader(new FileReader(ficheiro));

        while (bin.ready()) {
            linha = bin.readLine();
            st = new StringTokenizer(linha, " \r\n");
            produto = new Produto(st.nextToken());
        }

        bin.close();
    }

    public static void clientesBufferedReaderParse(String str_ficheiro_clientes)
            throws FileNotFoundException, IOException {
        String linha;
        StringTokenizer st;
        Cliente cliente;
        BufferedReader bin;
        File ficheiro = new File(str_ficheiro_clientes);

        bin = new BufferedReader(new FileReader(ficheiro));

        while (bin.ready()) {
            linha = bin.readLine();
            st = new StringTokenizer(linha, " \r\n");
            cliente = new Cliente(st.nextToken());
        }

        bin.close();

    }

    public static void comprasBufferedReaderParse(String str_ficheiro_compras)
            throws FileNotFoundException, IOException {
        String linha;
        StringTokenizer st;
        Compra compra;
        BufferedReader bin;
        File ficheiro = new File(str_ficheiro_compras);

        bin = new BufferedReader(new FileReader(ficheiro));

        while (bin.ready()) {
            compra = new Compra();
            /*
             * Exemplo de linha: WJ3256 4.72 2 N AF651 10
             */
            linha = bin.readLine();
            st = new StringTokenizer(linha, " \r\n");
            compra.setProduto(new Produto(st.nextToken()));
            compra.setPreco(Double.parseDouble(st.nextToken()));
            compra.setQuantidade(Integer.parseInt(st.nextToken()));
            compra.setTipoCompra(st.nextToken().compareTo("N") == 0 ? TipoCompra.NORMAL
                    : TipoCompra.PROMOCAO);
            compra.setCliente(new Cliente(st.nextToken()));
            compra.setMes(Mes.numero_to_mes(Integer.parseInt(st.nextToken())));

        }
        bin.close();
    }
    
    public static void produtosBufferedReaderNoParse(String str_ficheiro_produtos)
            throws FileNotFoundException, IOException {
    	//FIXME var não usada
        int i = 1;
        String linha;
        StringTokenizer st;
        Produto produto;
        BufferedReader bin;
        File ficheiro = new File(str_ficheiro_produtos);



        bin = new BufferedReader(new FileReader(ficheiro));

        while (bin.ready()) {
            linha = bin.readLine();
        }

        bin.close();
    }

    public static void clientesBufferedReaderNoParse(String str_ficheiro_clientes)
            throws FileNotFoundException, IOException {
        String linha;
        StringTokenizer st;
        Cliente cliente;
        BufferedReader bin;
        File ficheiro = new File(str_ficheiro_clientes);


        bin = new BufferedReader(new FileReader(ficheiro));

        while (bin.ready()) {
            linha = bin.readLine();
        }

        bin.close();

    }

    public static void comprasBufferedReaderNoParse(String str_ficheiro_compras)
            throws FileNotFoundException, IOException {
        String linha;
        StringTokenizer st;
        Compra compra;
        BufferedReader bin;
        File ficheiro = new File(str_ficheiro_compras);

        bin = new BufferedReader(new FileReader(ficheiro));

        while (bin.ready()) {
            compra = new Compra();
            /*
             * Exemplo de linha: WJ3256 4.72 2 N AF651 10
             */
            linha = bin.readLine();
        }
        bin.close();
    }
    
    public static void produtosScannerParse(String str_ficheiro_produtos)
            throws FileNotFoundException, IOException {
    	//FIXME var não usada
        int i = 1;
        String linha;
        StringTokenizer st;
        Produto produto;
        Scanner bin;
        File ficheiro = new File(str_ficheiro_produtos);

        bin = new Scanner(new FileReader(ficheiro));

        while (bin.hasNextLine()) {
            linha = bin.nextLine();
            st = new StringTokenizer(linha, " \r\n");
            produto = new Produto(st.nextToken());
        }

        bin.close();
    }

    public static void clientesScannerParse(String str_ficheiro_clientes)
            throws FileNotFoundException, IOException {
        String linha;
        StringTokenizer st;
        Cliente cliente;
        Scanner bin;
        File ficheiro = new File(str_ficheiro_clientes);


        bin = new Scanner(new FileReader(ficheiro));

        while (bin.hasNextLine()) {
            linha = bin.nextLine();
            st = new StringTokenizer(linha, " \r\n");
            cliente = new Cliente(st.nextToken());
        }

        bin.close();

    }

    public static void comprasScannerParse(String str_ficheiro_compras)
            throws FileNotFoundException, IOException {
        String linha;
        StringTokenizer st;
        Compra compra;
        Scanner bin;
        File ficheiro = new File(str_ficheiro_compras);


        bin = new Scanner(new FileReader(ficheiro));

        while (bin.hasNextLine()) {
            compra = new Compra();
            /*
             * Exemplo de linha: WJ3256 4.72 2 N AF651 10
             */
            linha = bin.nextLine();
            st = new StringTokenizer(linha, " \r\n");
            compra.setProduto(new Produto(st.nextToken()));
            compra.setPreco(Double.parseDouble(st.nextToken()));
            compra.setQuantidade(Integer.parseInt(st.nextToken()));
            compra.setTipoCompra(st.nextToken().compareTo("N") == 0 ? TipoCompra.NORMAL
                    : TipoCompra.PROMOCAO);
            compra.setCliente(new Cliente(st.nextToken()));
            compra.setMes(Mes.numero_to_mes(Integer.parseInt(st.nextToken())));

        }
        bin.close();
    }
    
    public static void produtosScannerNoParse(String str_ficheiro_produtos)
            throws FileNotFoundException, IOException {
    	//FIXME var não usada
        int i = 1;
        String linha;
        StringTokenizer st;
        Produto produto;
        Scanner bin;
        File ficheiro = new File(str_ficheiro_produtos);

        bin = new Scanner(new FileReader(ficheiro));

        while (bin.hasNextLine()) {
            linha = bin.nextLine();
        }

        bin.close();
    }

    public static void clientesScannerNoParse(String str_ficheiro_clientes)
            throws FileNotFoundException, IOException {
        String linha;
        StringTokenizer st;
        Cliente cliente;
        Scanner bin;
        File ficheiro = new File(str_ficheiro_clientes);

        bin = new Scanner(new FileReader(ficheiro));

        while (bin.hasNextLine()) {
            linha = bin.nextLine();
        }

        bin.close();

    }

    public static void comprasScannerNoParse(String str_ficheiro_compras)
            throws FileNotFoundException, IOException {
        String linha;
        StringTokenizer st;
        Compra compra;
        Scanner bin;
        File ficheiro = new File(str_ficheiro_compras);


        bin = new Scanner(new FileReader(ficheiro));

        while (bin.hasNextLine()) {
            compra = new Compra();
            /*
             * Exemplo de linha: WJ3256 4.72 2 N AF651 10
             */
            linha = bin.nextLine();
        }
        bin.close();
    }
    
}
