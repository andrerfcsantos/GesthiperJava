package lei.li3.g50.gesthiper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import lei.li3.g50.modulos.dados.*;
import lei.li3.g50.utilitarios.Crono;
import lei.li3.g50.utilitarios.FiltraFicheirosPorExtensao;

public final class Leitura {

    private Leitura() {
    }

    final static String ANSI_CLEARSCREEN = "\u001b[2J";
    final static String ANSI_HOME = "\u001b[H";

    private static final String fich_produtos_default[] = {
        "datasets/FichProdutos.txt", "FichProdutos.txt",
        "../datasets/FichProdutos.txt"};
    private static final String fich_clientes_default[] = {
        "datasets/FichClientes.txt", "FichClientes.txt",
        "../datasets/FichClientes.txt"};
    private static final String fich_compras_default[] = {
        "datasets/Compras.txt", "Compras.txt", "../datasets/Compras.txt",
        "datasets/Compras1.txt", "Compras1.txt", "../datasets/Compras1.txt",
        "datasets/Compras3.txt", "Compras3.txt", "../datasets/Compras3.txt",
        "datasets/miniCompras.txt", "miniCompras.txt", "../datasets/miniCompras.txt"};

    public static void menuLeitura() {
        Scanner input = new Scanner(System.in);
        int escolha;
        System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
        System.out.print("=================================\n");
        System.out.print(" GESTHIPER >> Leitura Ficheiros   \n");
        System.out.print("=================================\n");
        System.out.print("   1) Ler ficheiro objecto       \n");
        System.out.print("   2) Ler ficheiros genericos    \n");
        System.out.print("   3) Procura automatica ficheiro objecto \n");
        System.out.print("   4) Procura automatica ficheiro generico \n");
        System.out.print("   5) Sair \n");
        System.out.print("=================================\n");
        System.out.print("Escolha uma opção: ");
        escolha = input.nextInt();

        switch (escolha) {
            case 1:
                leFicheiroObjectoDado();
                break;
            case 2:
                leFicheirosGenericosDados();
                break;
            case 3:
                leFicheiroObjectoPAutomatica();
                break;
            case 4:
                leFicheirosGenericosPAutomatica();
                break;
            case 5:
                System.exit(0);
            default:
                break;
        }

    }

    public static void leFicheirosGenericosPAutomatica() {
        int escolha;
        double tempo_leitura_produtos = -1;
        double tempo_leitura_clientes = -1;
        double tempo_leitura_compras = -1;
        String input;
        File ficheiro_clientes, ficheiro_produtos, ficheiro_compras;
        ArrayList<File> ficheiros_clientes = procura_ficheiros_clientes();
        ArrayList<File> ficheiros_produtos = procura_ficheiros_produtos();
        ArrayList<File> ficheiros_compras = procura_ficheiros_compras();
        Scanner sc = new Scanner(System.in);
        try {

            if (ficheiros_clientes.size() == 1) {
                System.out.print("" + ficheiros_clientes.size()
                        + " ficheiro de clientes encontrado: " + ficheiros_clientes.get(0).getPath() + "\n");
                ficheiro_clientes = ficheiros_clientes.get(0);
                Crono.start();
                le_ficheiro_clientes(ficheiro_clientes.getPath());
                tempo_leitura_clientes = Crono.stop();
            } else if (ficheiros_clientes.size() > 1) {
                System.out.print("" + ficheiros_clientes.size() + " ficheiros de clientes encontrados \n");
                mostraOpcoesFicheiros(ficheiros_clientes);
                System.out.print("Insira nº do ficheiro que quer ler: ");
                escolha = sc.nextInt();
                ficheiro_clientes = ficheiros_clientes.get(escolha - 1);
                Crono.start();
                le_ficheiro_clientes(ficheiro_clientes.getPath());
                tempo_leitura_clientes = Crono.stop();
            }

            if (ficheiros_produtos.size() == 1) {
                System.out.print("" + ficheiros_produtos.size()
                        + " ficheiro de produtos encontrado: " + ficheiros_produtos.get(0).getPath() + "\n");
                ficheiro_produtos = ficheiros_produtos.get(0);
                Crono.start();
                le_ficheiro_produtos(ficheiro_produtos.getPath());
                tempo_leitura_produtos = Crono.stop();
            } else if (ficheiros_produtos.size() > 1) {
                System.out.print("" + ficheiros_produtos.size()
                        + " ficheiros de produtos encontrados.\n");
                mostraOpcoesFicheiros(ficheiros_produtos);
                System.out.print("Insira nº do ficheiro: ");
                escolha = sc.nextInt();
                ficheiro_produtos = ficheiros_produtos.get(escolha - 1);
                Crono.start();
                le_ficheiro_produtos(ficheiro_produtos.getPath());
                tempo_leitura_produtos = Crono.stop();
            }

            if (ficheiros_compras.size() == 1) {
                System.out.print("" + ficheiros_compras.size()
                        + " ficheiro de compras encontrado: " + ficheiros_compras.get(0).getPath() + "\n");
                ficheiro_compras = ficheiros_compras.get(0);
                Crono.start();
                le_ficheiro_compras(ficheiro_compras.getPath());
                tempo_leitura_compras = Crono.stop();
            } else if (ficheiros_compras.size() > 1) {
                System.out.print("" + ficheiros_compras.size() + " ficheiros de compras encontrados \n");
                mostraOpcoesFicheiros(ficheiros_compras);
                System.out.print("Insira nº do ficheiro: ");
                escolha = sc.nextInt();
                ficheiro_compras = ficheiros_compras.get(escolha - 1);
                Crono.start();
                le_ficheiro_compras(ficheiro_compras.getPath());
                tempo_leitura_compras = Crono.stop();
            }
            
            System.out.print("=========================================\n");
            System.out.print("Tempo de leitura Clientes: " + tempo_leitura_clientes + " segs.\n");
            System.out.print("Tempo de leitura Produtos: " + tempo_leitura_produtos + " segs.\n");
            System.out.print("Tempo de leitura Compras: " + tempo_leitura_compras + " segs.\n");
            System.out.print("Tempo total leitura: "
                    + (tempo_leitura_clientes + tempo_leitura_produtos + tempo_leitura_compras) + " segs.\n");
            System.out.print("=========================================\n");
            
            System.out.print("Pressione qualquer tecla para continuar: ");
            input = sc.next();
            
            
            
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
        Hipermercado hiper = Gesthiper.getHipermercado();
        StringTokenizer st;
        BufferedReader bin;
        File ficheiro = new File(str_ficheiro_produtos);
        bin = new BufferedReader(new FileReader(ficheiro));

        while (bin.ready()) {
            linha = bin.readLine();
            st = new StringTokenizer(linha, " \r\n");
            hiper.regista_produto(new Produto(st.nextToken()));
        }

        bin.close();
    }

    public static void le_ficheiro_clientes(String str_ficheiro_clientes)
            throws FileNotFoundException, IOException {
        int i = 1;
        String linha;
        Hipermercado hiper = Gesthiper.getHipermercado();
        StringTokenizer st;
        BufferedReader bin;
        File ficheiro = new File(str_ficheiro_clientes);

        bin = new BufferedReader(new FileReader(ficheiro));

        while (bin.ready()) {
            linha = bin.readLine();
            st = new StringTokenizer(linha, " \r\n");
            hiper.regista_cliente(new Cliente(st.nextToken()));
        }

        bin.close();

    }

    public static void le_ficheiro_compras(String str_ficheiro_compras)
            throws FileNotFoundException, IOException {
        int compras_invalidas = 0;
        int i = 0;
        Hipermercado hiper = Gesthiper.getHipermercado();
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

            if (compraValida(compra)) {
                hiper.regista_compra(compra);
            } else {
                compras_invalidas++;
            }

        }
        //System.out.print(compras_invalidas);
        bin.close();
    }

    public static void leFicheirosGenericosDados() {
        String nome_ficheiro_produtos;
        String nome_ficheiro_clientes;
        String nome_ficheiro_compras;

        Scanner input = new Scanner(System.in);

        System.out.print("Indique o nome do ficheiro de produtos que quer ler: ");
        nome_ficheiro_produtos = input.nextLine();

        System.out.print("Indique o nome do ficheiro de clientes que quer ler: ");
        nome_ficheiro_clientes = input.nextLine();
        System.out.print("Indique o nome do ficheiro de compras que quer ler: ");
        nome_ficheiro_compras = input.nextLine();

        le_ficheiros(nome_ficheiro_produtos, nome_ficheiro_clientes, nome_ficheiro_compras);
    }

    public static void leFicheiroObjectoDado() {
        String nome_ficheiro;
        Scanner input = new Scanner(System.in);
        System.out.print("Indique o nome do ficheiro objecto que quer ler (incluindo extensao):");
        nome_ficheiro = input.nextLine();

        try {
            ObjectInputStream fich_obj = new ObjectInputStream(new FileInputStream(nome_ficheiro));
            Gesthiper.setHipermercado((Hipermercado) fich_obj.readObject());
            fich_obj.close();
        } catch (IOException ex) {
            System.out.print("Erro ao ler de ficheiro objecto.\n");
        } catch (ClassNotFoundException ex) {
            System.out.print("Classe nao encontrada.\n");
        }
    }

    public static void leFicheiroObjectoPAutomatica() {
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
        /*
         System.out.print("Lista ficheiros na directoria: \n");
         File directoria_corrente = new File(".");
         for (File ficheiro : directoria_corrente.listFiles()) {
         System.out.print("" + ficheiro.getPath() + "\n");
         }
         */
    }

    public static ArrayList<File> procura_ficheiros_clientes() {
        ArrayList<File> ficheiros_clientes = new ArrayList<>(fich_clientes_default.length);

        for (String file_path : fich_clientes_default) {
            File ficheiro = new File(file_path);
            if (ficheiro.exists()) {
                ficheiros_clientes.add(ficheiro);
            }
        }
        return ficheiros_clientes;
    }

    public static ArrayList<File> procura_ficheiros_produtos() {
        ArrayList<File> ficheiros_produtos = new ArrayList<>(fich_produtos_default.length);

        for (String file_path : fich_produtos_default) {
            File ficheiro = new File(file_path);
            if (ficheiro.exists()) {
                ficheiros_produtos.add(ficheiro);
            }
        }
        return ficheiros_produtos;
    }

    public static ArrayList<File> procura_ficheiros_compras() {
        ArrayList<File> ficheiros_compras = new ArrayList<>(fich_compras_default.length);
        for (String file_path : fich_compras_default) {
            File ficheiro = new File(file_path);
            if (ficheiro.exists()) {
                ficheiros_compras.add(ficheiro);
            }
        }
        return ficheiros_compras;
    }

    public static void mostraOpcoesFicheiros(ArrayList<File> lista) {
        int tamanho = lista.size();
        System.out.print("Escolha que ficheiro quer ler: \n");
        for (int i = 0; i < tamanho; i++) {
            System.out.print("   " + (i + 1) + ") " + lista.get(i).getPath() + "\n");
        }
    }

    public static boolean compraValida(Compra compra) {
        Hipermercado hiper = Gesthiper.getHipermercado();
        return hiper.getCatalogoClientes().existeCliente(compra.getCliente())
                && hiper.getCatalogoProdutos().existeProduto(compra.getProduto())
                && compra.getPreco() >= 0
                && compra.getQuantidade() >= 0;

    }
}
