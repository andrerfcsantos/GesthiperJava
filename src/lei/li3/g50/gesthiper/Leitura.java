package lei.li3.g50.gesthiper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

import lei.li3.g50.modulos.dados.*;
import lei.li3.g50.utilitarios.FiltraFicheirosPorExtensao;

public final class Leitura {

    private Leitura() {
    }

    private static final String fich_produtos_default[] = {
        "datasets/FichProdutos.txt", "FichProdutos.txt",
        "../datasets/FichProdutos.txt"};
    private static final String fich_clientes_default[] = {
        "datasets/FichClientes.txt", "FichClientes.txt",
        "../datasets/FichClientes.txt"};
    private static final String fich_compras_default[] = {
        "datasets/Compras.txt", "Compras.txt", "../datasets/Compras.txt",
        "datasets/Compras1.txt", "Compras1.txt",
        "../datasets/Compras1.txt", "datasets/Compras3.txt",
        "Compras3.txt", "../datasets/Compras3.txt"};

    public static void le_ficheiros() {
        ArrayList<File> ficheiros_clientes = new ArrayList<>(
                fich_clientes_default.length);
        ArrayList<File> ficheiros_produtos = new ArrayList<>(
                fich_produtos_default.length);
        ArrayList<File> ficheiros_compras = new ArrayList<>(
                fich_compras_default.length);

        for (String file_path : fich_clientes_default) {
            File ficheiro = new File(file_path);
            if (ficheiro.exists()) {
                ficheiros_clientes.add(ficheiro);
            }
        }
        for (String file_path : fich_produtos_default) {
            File ficheiro = new File(file_path);
            if (ficheiro.exists()) {
                ficheiros_produtos.add(ficheiro);
            }
        }
        for (String file_path : fich_compras_default) {
            File ficheiro = new File(file_path);
            if (ficheiro.exists()) {
                ficheiros_compras.add(ficheiro);
            }
        }

        try {

            System.out.print("" + ficheiros_clientes.size()
                    + " ficheiro(s) de clientes encontrado(s) \n");
            System.out.print("A ler ficheiro de clientes: "
                    + ficheiros_clientes.get(0).getPath()+"\n");
            le_ficheiro_clientes(ficheiros_clientes.get(0).getPath());

            System.out.print("" + ficheiros_produtos.size()
                    + " ficheiro(s) de produtos encontrado(s) \n");
            System.out.print("A ler ficheiro de produtos: "
                    + ficheiros_produtos.get(0).getPath()+"\n");
            le_ficheiro_produtos(ficheiros_produtos.get(0).getPath());

            System.out.print("" + ficheiros_produtos.size()
                    + " ficheiro(s) de compras encontrado(s) \n");
            System.out.print("A ler ficheiro de compras: "
                    + ficheiros_compras.get(0).getPath()+"\n");
            le_ficheiro_compras(ficheiros_compras.get(0).getPath());

        } catch (FileNotFoundException ex) {
            System.out.printf("Um ou mais ficheiros não foram encontrados.\n");
        } catch (IOException ex) {
            System.out.printf("Erro de IO ao abrir um dos ficheiros.\n");
        }

    }

    public static void le_ficheiros(String str_fich_produtos,
            String str_fich_clientes, String str_fich_compras) {
        try {
            le_ficheiro_produtos(str_fich_produtos);
            le_ficheiro_clientes(str_fich_clientes);
            le_ficheiro_compras(str_fich_compras);
        } catch (FileNotFoundException ex) {
            System.out.printf("Um ou mais ficheiros não foram encontrados.\n");
        } catch (IOException ex) {
            System.out.printf("Erro de IO ao abrir um dos ficheiros.\n");
        }
    }

    public static void le_ficheiro_produtos(String str_ficheiro_produtos)
            throws FileNotFoundException, IOException {
        int i = 1;
        String linha;
        StringTokenizer st;
        BufferedReader bin;
        File ficheiro = new File(str_ficheiro_produtos);
        bin = new BufferedReader(new FileReader(ficheiro));

        while (bin.ready()) {
            linha = bin.readLine();
            st = new StringTokenizer(linha, " \r\n");
            Gesthiper.getHipermercado().regista_produto(
                    new Produto(st.nextToken()));
        }

        bin.close();
    }

    public static void le_ficheiro_clientes(String str_ficheiro_clientes)
            throws FileNotFoundException, IOException {
        int i = 1;
        String linha;
        StringTokenizer st;
        BufferedReader bin;
        File ficheiro = new File(str_ficheiro_clientes);

        bin = new BufferedReader(new FileReader(ficheiro));

        while (bin.ready()) {
            linha = bin.readLine();
            st = new StringTokenizer(linha, " \r\n");
            Gesthiper.getHipermercado().regista_cliente(
                    new Cliente(st.nextToken()));
        }

        bin.close();

    }

    public static void le_ficheiro_compras(String str_ficheiro_compras)
            throws FileNotFoundException, IOException {
        int i = 1;
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

    public static void le_ficheiro_objecto(String nome_ficheiro) {
        try {
            ObjectInputStream fich_obj = new ObjectInputStream(
                    new FileInputStream(nome_ficheiro));
            Gesthiper.setHipermercado((Hipermercado) fich_obj.readObject());
            fich_obj.close();
        } catch (IOException ex) {
            System.out.print("Erro ao ler de ficheiro objecto.\n");
        } catch (ClassNotFoundException ex) {
            System.out.print("Classe nao encontrada.\n");
        }
    }

    public static void le_ficheiro_objecto() {
        File directoria_actual = new File(".");
        File[] ficheiros_obj = directoria_actual
                .listFiles(new FiltraFicheirosPorExtensao("obj"));
        try {
            if (ficheiros_obj.length == 0) {
                throw new IOException();
            }
            ObjectInputStream fich_obj = new ObjectInputStream(
                    new FileInputStream(ficheiros_obj[0]));
            Gesthiper.setHipermercado((Hipermercado) fich_obj.readObject());
            fich_obj.close();
        } catch (IOException ex) {
            System.out.print("Erro ao ler de ficheiro objecto.\n");
        } catch (ClassNotFoundException ex) {
            System.out.print("Classe nao encontrada.\n");
        }
    }

    public static void mostra_ficheiros() {
        for (String file_path : fich_clientes_default) {
            File ficheiro = new File(file_path);
            if (ficheiro.exists()) {
                System.out.print("" + ficheiro.getPath() + " encontrado.\n");
            }
        }
        for (String file_path : fich_produtos_default) {
            File ficheiro = new File(file_path);
            if (ficheiro.exists()) {
                System.out.print("" + ficheiro.getPath() + " encontrado.\n");
            }
        }
        for (String file_path : fich_compras_default) {
            File ficheiro = new File(file_path);
            if (ficheiro.exists()) {
                System.out.print("" + ficheiro.getPath() + " encontrado.\n");
            }
        }

        System.out.print("Lista ficheiros na directoria: \n");
        File directoria_corrente = new File(".");
        for (File ficheiro : directoria_corrente.listFiles()) {
            System.out.print("" + ficheiro.getPath() + "\n");
        }
    }
}
