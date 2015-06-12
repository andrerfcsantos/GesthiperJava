package lei.li3.g50.gesthiper;

import lei.li3.g50.modulos.dados.Hipermercado;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import lei.li3.g50.modulos.dados.*;
import lei.li3.g50.utilitarios.Crono;
import lei.li3.g50.utilitarios.FiltraFicheirosPorExtensao;

public final class MenuLeitura {

    private MenuLeitura() {
    }

    final static String ANSI_CLEARSCREEN = "\u001b[2J";
    final static String ANSI_HOME = "\u001b[H";

    private static final String fich_produtos_default[] = {
        "datasets/FichProdutos.txt", "FichProdutos.txt", "../datasets/FichProdutos.txt"
    };

    private static final String fich_clientes_default[] = {
        "datasets/FichClientes.txt", "FichClientes.txt", "../datasets/FichClientes.txt"
    };

    private static final String fich_compras_default[] = {
        "datasets/Compras.txt", "Compras.txt", "../datasets/Compras.txt",
        "datasets/Compras1.txt", "Compras1.txt", "../datasets/Compras1.txt",
        "datasets/Compras3.txt", "Compras3.txt", "../datasets/Compras3.txt",
        "datasets/miniCompras.txt", "miniCompras.txt", "../datasets/miniCompras.txt"
    };

    public static void menuLeitura() {
        int escolha;
        String lixo;
        boolean leituraCorrecta = false;
        Scanner input = new Scanner(System.in);

        while (!leituraCorrecta) {

            System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
            System.out.print("============================================\n");
            System.out.print(" GESTHIPER >> Leitura Ficheiros             \n");
            System.out.print("============================================\n");
            System.out.print("   1) Ler ficheiro objecto                  \n");
            System.out.print("   2) Ler ficheiros genericos               \n");
            System.out.print("   3) Procura automatica ficheiro objecto   \n");
            System.out.print("   4) Procura automatica ficheiro generico  \n");
            System.out.print("--------------------------------------------\n");
            System.out.print(" 0 - Sair                                   \n");
            System.out.print("============================================\n");
            System.out.print("Escolha uma opção: ");

            try {
                escolha = input.nextInt();
                switch (escolha) {
                    case 0:
                        System.exit(0);
                        break;
                    case 1:
                        opcao_FicheiroObjectoDado();
                        leituraCorrecta = true;
                        break;
                    case 2:
                        opcao_FicheirosGenericosDados();
                        leituraCorrecta = true;
                        break;
                    case 3:
                        opcao_FicheiroObjectoPAutomatica();
                        leituraCorrecta = true;
                        break;
                    case 4:
                        opcao_FicheirosGenericosPAutomatica();
                        leituraCorrecta = true;
                        break;
                    default:
                        break;
                }

            } catch (IOException | ClassNotFoundException ex) {

            } catch (InputMismatchException ex) {
                lixo = input.next();
            }
        }

        

    }

    public static void opcao_FicheirosGenericosPAutomatica() throws FileNotFoundException, IOException {
        int escolha;
        String input;
        double tempo_leitura_produtos = -1, tempo_leitura_clientes = -1, tempo_leitura_compras = -1;
        File ficheiro_clientes, ficheiro_produtos, ficheiro_compras;
        ArrayList<File> ficheiros_clientes = procura_ficheiros_clientes();
        ArrayList<File> ficheiros_produtos = procura_ficheiros_produtos();
        ArrayList<File> ficheiros_compras = procura_ficheiros_compras();
        Scanner sc = new Scanner(System.in);

        /*
         PEDIDO FICHEIRO CLIENTES
         */
        if (ficheiros_clientes.size() >= 1) {

            if (ficheiros_clientes.size() == 1) {
                System.out.print("" + ficheiros_clientes.size()
                        + " ficheiro de clientes encontrado: " + ficheiros_clientes.get(0).getPath() + "\n");
                ficheiro_clientes = ficheiros_clientes.get(0);
            } else {
                System.out.print("" + ficheiros_clientes.size() + " ficheiros de clientes encontrados \n");
                mostraOpcoesFicheiros(ficheiros_clientes);
                System.out.print("Insira nº do ficheiro que quer ler: ");
                escolha = sc.nextInt();
                ficheiro_clientes = ficheiros_clientes.get(escolha - 1);
            }

        } else {
            System.out.print("Não foi encontrado nenhum ficheiro de clientes.\n");
            throw new FileNotFoundException();
        }

        /*
         PEDIDO FICHEIRO PRODUTOS
         */
        if (ficheiros_produtos.size() >= 1) {

            if (ficheiros_produtos.size() == 1) {
                System.out.print("" + ficheiros_produtos.size() + " ficheiro de produtos encontrado: "
                        + ficheiros_produtos.get(0).getPath() + "\n");
                ficheiro_produtos = ficheiros_produtos.get(0);
            } else {
                System.out.print("" + ficheiros_produtos.size() + " ficheiros de produtos encontrados.\n");
                mostraOpcoesFicheiros(ficheiros_produtos);
                System.out.print("Insira nº do ficheiro: ");
                escolha = sc.nextInt();
                ficheiro_produtos = ficheiros_produtos.get(escolha - 1);
            }

        } else {
            System.out.print("Não foi encontrado nenhum ficheiro de produtos.\n");
            throw new FileNotFoundException();
        }

        /*
         PEDIDO FICHEIRO COMPRAS
         */
        if (ficheiros_compras.size() >= 1) {

            if (ficheiros_compras.size() == 1) {
                System.out.print("" + ficheiros_compras.size()
                        + " ficheiro de compras encontrado: " + ficheiros_compras.get(0).getPath() + "\n");
                ficheiro_compras = ficheiros_compras.get(0);

            } else {
                System.out.print("" + ficheiros_compras.size() + " ficheiros de compras encontrados \n");
                mostraOpcoesFicheiros(ficheiros_compras);
                System.out.print("Insira nº do ficheiro: ");
                escolha = sc.nextInt();
                ficheiro_compras = ficheiros_compras.get(escolha - 1);
            }

        } else {
            System.out.print("Não foi encontrado nenhum ficheiro de compras.\n");
            throw new FileNotFoundException();
        }

        System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
        System.out.print("============================================\n");
        System.out.print(" GESTHIPER >> Leitura Ficheiros             \n");
        System.out.print("============================================\n");

        /*
         LEITURA FICHEIRO CLIENTES
         */
        Crono.start();
        try {
            System.out.print("A ler " + ficheiro_clientes.getPath() + "...\n");
            LeituraFicheiros.le_ficheiro_clientes(ficheiro_clientes.getPath());
            System.out.print("Ficheiro " + ficheiro_clientes.getPath() + " lido com sucesso.\n");
        } catch (IOException ex) {
            System.out.print("Erro ao ler ficheiro de clientes. Excepção IOException.\n");
            System.out.print("A abortar leitura dos restantes ficheiros...\n");
            throw ex;
        }
        tempo_leitura_clientes = Crono.stop();

        /*
         LEITURA FICHEIRO PRODUTOS
         */
        Crono.start();
        try {
            System.out.print("A ler " + ficheiro_produtos.getPath() + "...\n");
            LeituraFicheiros.le_ficheiro_produtos(ficheiro_produtos.getPath());
            System.out.print("Ficheiro " + ficheiro_produtos.getPath() + " lido com sucesso.\n");
        } catch (IOException ex) {
            System.out.print("Erro ao ler ficheiro de produtos. Excepção IOException.\n");
            System.out.print("A abortar leitura dos restantes ficheiros...\n");
            throw ex;
        }
        tempo_leitura_produtos = Crono.stop();

        /*
         LEITURA FICHEIRO COMPRAS
         */
        Crono.start();
        try {
            System.out.print("A ler " + ficheiro_compras.getPath() + "...\n");
            LeituraFicheiros.le_ficheiro_compras(ficheiro_compras.getPath());
            System.out.print("Ficheiro " + ficheiro_compras.getPath() + " lido com sucesso.\n");
        } catch (IOException ex) {
            System.out.print("Erro ao ler ficheiro de compras. Excepção IOException.\n");
            System.out.print("A abortar leitura...\n");
        }
        tempo_leitura_compras = Crono.stop();

        System.out.print("=========================================\n");
        System.out.print("Tempo de leitura Clientes: " + tempo_leitura_clientes + " segs.\n");
        System.out.print("Tempo de leitura Produtos: " + tempo_leitura_produtos + " segs.\n");
        System.out.print("Tempo de leitura Compras: " + tempo_leitura_compras + " segs.\n");
        System.out.print("Tempo total leitura: "
                + (tempo_leitura_clientes + tempo_leitura_produtos + tempo_leitura_compras) + " segs.\n");
        System.out.print("=========================================\n");

        System.out.print("Pressione qualquer tecla para continuar: ");
        input = sc.next();

    }

    public static void opcao_FicheirosGenericosDados() throws FileNotFoundException, IOException {
        String nome_ficheiro_produtos;
        String nome_ficheiro_clientes;
        String nome_ficheiro_compras;

        Scanner input = new Scanner(System.in);

        /*
         FICHEIRO PRODUTOS
         */
        System.out.print("Indique o nome do ficheiro de produtos que quer ler: ");
        nome_ficheiro_produtos = input.nextLine();

        try {
            LeituraFicheiros.le_ficheiro_produtos(nome_ficheiro_produtos);
        } catch (FileNotFoundException ex) {
            System.out.printf("Ficheiro de produtos nao encontrado. Excepção FileNotFoundException\n");
            throw ex;
        } catch (IOException ex) {
            System.out.printf("Erro ao abrir o ficheiro de produtos. Execpção IOException\n");
            throw ex;
        }

        /*
         FICHEIRO CLIENTES
         */
        System.out.print("Indique o nome do ficheiro de clientes que quer ler: ");
        nome_ficheiro_clientes = input.nextLine();

        try {
            LeituraFicheiros.le_ficheiro_clientes(nome_ficheiro_clientes);
        } catch (FileNotFoundException ex) {
            System.out.printf("Ficheiro de clientes nao encontrado. Excepção FileNotFoundException\n");
            throw ex;
        } catch (IOException ex) {
            System.out.printf("Erro ao abrir o ficheiro de clientes. Execpção IOException\n");
            throw ex;
        }


        /*
         FICHEIRO COMPRAS
         */
        System.out.print("Indique o nome do ficheiro de compras que quer ler: ");
        nome_ficheiro_compras = input.nextLine();
        try {
            LeituraFicheiros.le_ficheiro_compras(nome_ficheiro_compras);
        } catch (FileNotFoundException ex) {
            System.out.printf("Ficheiro de compras nao encontrado. Excepção FileNotFoundException\n");
            throw ex;
        } catch (IOException ex) {
            System.out.printf("Erro ao abrir o ficheiro de compras. Execpção IOException\n");
            throw ex;
        }

    }

    public static void opcao_FicheiroObjectoDado() throws IOException, ClassNotFoundException {
        String nome_ficheiro;
        Scanner input = new Scanner(System.in);
        System.out.print("Indique o nome do ficheiro objecto que quer ler (incluindo extensao):");
        nome_ficheiro = input.nextLine();

        try {
            LeituraFicheiros.le_ficheiro_objecto(nome_ficheiro);
        } catch (IOException ex) {
            System.out.print("Erro ao ler de ficheiro objecto.\n");
            throw ex;
        } catch (ClassNotFoundException ex) {
            System.out.print("Classe nao encontrada.\n");
            throw ex;
        }
    }

    public static void opcao_FicheiroObjectoPAutomatica() throws IOException, ClassNotFoundException {
        int escolha;
        String input;
        double tempo_leitura;
        Scanner sc = new Scanner(System.in);
        File directoria_actual = new File(".");
        File[] ficheiros_obj = directoria_actual.listFiles(new FiltraFicheirosPorExtensao("obj"));
        List<File> arrayFichObjs;
        File ficheiro_obj;

        if (ficheiros_obj.length >= 1) {
            arrayFichObjs = Arrays.asList(ficheiros_obj);
            if (arrayFichObjs.size() == 1) {
                System.out.print("" + arrayFichObjs.size()
                        + " ficheiro de clientes encontrado: " + arrayFichObjs.get(0).getPath() + "\n");
                ficheiro_obj = arrayFichObjs.get(0);

            } else {
                System.out.print("" + arrayFichObjs.size() + " ficheiros de clientes encontrados \n");
                mostraOpcoesFicheiros(arrayFichObjs);
                System.out.print("Insira nº do ficheiro que quer ler: ");
                escolha = sc.nextInt();
                ficheiro_obj = arrayFichObjs.get(escolha - 1);
            }

            try {
                Crono.start();
                LeituraFicheiros.le_ficheiro_objecto(ficheiro_obj.getPath());
                tempo_leitura = Crono.stop();
            } catch (IOException ex) {
                System.out.print("O ficheiro " + ficheiro_obj.getPath() + " não foi lido. Excepção de IO.\n");
                throw ex;
            } catch (ClassNotFoundException ex) {
                System.out.print("O ficheiro " + ficheiro_obj.getPath() + " não foi lido. Excepção ClassNotFound.\n");
                throw ex;
            }

        } else {
            System.out.print("Não foi encontrado nenhum ficheiro terminado por .obj\n");
            throw new FileNotFoundException();
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

    public static void mostraOpcoesFicheiros(List<File> lista) {
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
