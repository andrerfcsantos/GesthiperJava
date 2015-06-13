package lei.li3.g50.gesthiper;

import lei.li3.g50.modulos.dados.Hipermercado;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import lei.li3.g50.excepcoes.ClienteNaoExisteException;
import lei.li3.g50.excepcoes.ProdutoNaoExisteException;
import static lei.li3.g50.gesthiper.MenuQueries.MenuActual.*;
import lei.li3.g50.modulos.catalogos.CatalogoClientes;
import lei.li3.g50.modulos.catalogos.CatalogoProdutos;
import lei.li3.g50.modulos.compras.Compras;
import lei.li3.g50.modulos.contabilidade.Contabilidade;
import lei.li3.g50.modulos.dados.Cliente;
import lei.li3.g50.modulos.dados.Mes;
import lei.li3.g50.modulos.dados.Produto;
import lei.li3.g50.modulos.dados.TipoCompra;
import lei.li3.g50.utilitarios.Crono;
import lei.li3.g50.utilitarios.Matriz_Double_12x2;
import lei.li3.g50.utilitarios.Matriz_Int_12x2;
import lei.li3.g50.utilitarios.Paginador;
import lei.li3.g50.utilitarios.ParClienteProdutosDiferentes;
import lei.li3.g50.utilitarios.ParProdutoQuantidadeComprada;
import lei.li3.g50.utilitarios.TriploClienteQtdCompradaDinheiro;

public final class MenuQueries {

    final static String ANSI_CLEARSCREEN = "\u001b[2J";
    final static String ANSI_HOME = "\u001b[H";

    public static CatalogoClientes catalogoClientes;
    public static CatalogoProdutos catalogoProdutos;
    public static Contabilidade moduloContabilidade;
    public static Compras moduloCompras;

    public enum MenuActual {

        SAIR,
        MENU_QUERIES,
        QUERIE_01,
        QUERIE_02,
        QUERIE_03,
        QUERIE_04,
        QUERIE_05a,
        QUERIE_05b,
        QUERIE_06a,
        QUERIE_06b,
        QUERIE_07a,
        QUERIE_07b,
        QUERIE_08a,
        QUERIE_08b,
        QUERIE_09a,
        QUERIE_09b,
        QUERIE_09c,
        QUERIE_10a,
        QUERIE_10b,
        QUERIE_10c,
        QUERIE_11a,
        QUERIE_11b,
        QUERIE_11c,
        QUERIE_12a,
        QUERIE_12b,
        QUERIE_12c,
        QUERIE_13a,
        QUERIE_13b,
        QUERIE_14a,
        QUERIE_14b,
        QUERIE_15a,
        QUERIE_15b;
    }

    private MenuQueries() {
    }

    public static void menuPrincipal() {
        int escolha;
        Scanner input = new Scanner(System.in);
        String lixo;
        MenuActual estadoMenu = MENU_QUERIES;

        while (estadoMenu == MENU_QUERIES) {

            catalogoClientes = Gesthiper.getHipermercado().getCatalogoClientes();
            catalogoProdutos = Gesthiper.getHipermercado().getCatalogoProdutos();
            moduloContabilidade = Gesthiper.getHipermercado().getContabilidade();
            moduloCompras = Gesthiper.getHipermercado().getCompras();

            System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
            System.out.print("=============================\n");
            System.out.print("GESTHIPER >> Menu Queries\n");
            System.out.print("=============================\n");
            System.out.print("QUERIES:\n");
            System.out.print("  1) Estatísticas último ficheiro\n");
            System.out.print("  2) Dados gerais\n");
            System.out.print("  3) Produtos não comprados\n");
            System.out.print("  4) Clientes que não compraram\n");
            System.out.print("  5) Nº compras / clientes num mês\n");
            System.out.print("  6) Compras cliente num mês\n");
            System.out.print("  7) Compras / clientes /facturacao produto\n");
            System.out.print("  8) Compras produto N e P\n");
            System.out.print("  9) Produtos mais comprados cliente\n");
            System.out.print("  10) Produtos mais vendidos\n");
            System.out.print("  11) Clientes com mais produtos diferentes comprados\n");
            System.out.print("  12) Clientes que mais compraram produto\n");
            System.out.print("OPERAÇÕES:\n");
            System.out.print("  13) Guardar em ficheiro objecto\n");
            System.out.print("  14) Carregar ficheiro objecto\n");
            System.out.print("  15) Mudar ficheiro compras\n");
            System.out.print("=============================\n");
            System.out.print("0 - Sair \n");
            System.out.print("=============================\n");
            System.out.print("Escolha o nº opção: ");

            try {
                escolha = input.nextInt();
            } catch (InputMismatchException ex) {
                lixo = input.next();
                escolha = -1;
            }

            switch (escolha) {
                case 0:
                    estadoMenu = SAIR;
                    break;
                case 1:
                    estadoMenu = _01_estatisticasUltimoFicheiro();
                    break;
                case 2:
                    estadoMenu = _02_dadosGerais();
                    break;
                case 3:
                    estadoMenu = _03_produtosNuncaComprados();
                    break;
                case 4:
                    estadoMenu = _04_clientesQueNuncaCompraram();
                    break;
                case 5:
                    estadoMenu = _05_comprasEClientesNumMes();
                    break;
                case 6:
                    estadoMenu = _06_comprasClienteMeses();
                    break;
                case 7:
                    estadoMenu = _07_comprasProdutoTodosMeses();
                    break;
                case 8:
                    estadoMenu = _08_comprasProdutoModoNeP();
                    break;
                case 9:
                    estadoMenu = _09_produtosMaisCompradosCliente();
                    break;
                case 10:
                    estadoMenu = _10_produtosMaisVendidos();
                    break;
                case 11:
                    estadoMenu = _11_clientesComMaisProdutosDiferentesComprados();
                    break;
                case 12:
                    estadoMenu = _12_clientesQueMaisCompraramProduto();
                    break;
                case 13:
                    estadoMenu = _13_guardarEmFicheiroObjecto();
                    break;
                case 14:
                    estadoMenu = _14_carregarFicheiroObjecto();
                    break;
                case 15:
                    estadoMenu = _15_mudarFicheiroCompras();
                    break;
                default:
                    estadoMenu = MENU_QUERIES;
                    break;
            }

        }
        input.close();
    }

    /*
     Apresenta ao utilizador os dados referentes ao último ficheiro de compras lido,
     designadamente, nome dos ficheiros, número total de produtos, total de diferentes
     produtos comprados, total de não comprados, número total de clientes e total dos
     que realizaram compras, total de clientes que nada compraram, total de compras de
     valor total igual a 0 e facturação total.
     */
    public static MenuActual _01_estatisticasUltimoFicheiro() {
        MenuActual estadoMenu = QUERIE_01;
        int escolha_opcao;
        double tempo_querie;
        String lixo;
        Scanner input = new Scanner(System.in);
        Hipermercado hiper = Gesthiper.getHipermercado();

        while (estadoMenu == QUERIE_01) {
            Crono.start();
            int produtosNaoComprados = moduloCompras.getTotalProdutosNaoComprados();
            int clientesSemCompras = moduloCompras.getTotalClientesSemCompras();
            System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
            System.out.print("=================================================== \n");
            System.out.print("GESTHIPER >> QUERIE 1                               \n");
            System.out.print("Estatísticas últimos ficheiros lidos                \n");
            System.out.print("=================================================== \n");

            System.out.print("Ficheiro Produtos: " + hiper.getFicheiro_produtos().getPath() + "\n");
            System.out.print("Ficheiro Clientes: " + hiper.getFicheiro_clientes().getPath() + "\n");
            System.out.print("Ficheiro Compras: " + hiper.getFicheiro_compras().getPath() + "\n");
            System.out.print("Total Produtos: " + catalogoProdutos.getNumeroProdutosTotal() + "\n");
            System.out.print("Total Produtos Nao Comprados: " + produtosNaoComprados + "\n");
            System.out.print("Total Produtos Comprados: " + (catalogoProdutos.getNumeroProdutosTotal() - produtosNaoComprados) + "\n");
            System.out.print("Total Clientes: " + catalogoClientes.getNumeroClientesTotal() + "\n");
            System.out.print("Total Clientes Sem Compras: " + clientesSemCompras + "\n");
            System.out.print("Total Produtos Comprados: " + (catalogoClientes.getNumeroClientesTotal() - clientesSemCompras) + "\n");
            System.out.print("Compras de valor zero: " + moduloCompras.getNumeroComprasValorZero() + "\n");
            System.out.print("Facturação total: " + moduloContabilidade.getTotalFacturado() + "\n");
            tempo_querie = Crono.stop();
            System.out.print("--------------------------------------------------- \n");
            System.out.printf("Tempo querie: %.4f segundos.\n", tempo_querie);
            System.out.print("=================================================== \n");
            System.out.print(" 0 - Sair | 1 - Menu Principal   \n");
            System.out.print("=================================================== \n");
            System.out.print("Escolha opção: ");
            try {
                escolha_opcao = input.nextInt();
            } catch (InputMismatchException ex) {
                lixo = input.next();
                escolha_opcao = -1;
            }
            switch (escolha_opcao) {
                case 0:
                    estadoMenu = SAIR;
                    break;
                case 1:
                    estadoMenu = MENU_QUERIES;
                    break;
                default:
                    estadoMenu = QUERIE_01;
                    break;
            }
        }
        return estadoMenu;
    }

    /*
     Apresenta em ecrã ao utilizador os números gerais respeitantes aos dados
     actuais já registados nas estruturas, designadamente:
     - Número total de compras por mês (não é a facturação);
     - Facturação total por mês (valor total das compras/vendas) e total global;
     - Número de distintos clientes que compraram em cada mês (não interessa
     quantas vezes o cliente comprou mas apenas quem de facto comprou);
     - Total de registos de compras inválidos (os registos completos, com os
     nomes e valores dos respectivos campos, ou seja, não é apenas a linha lida,
     deverão ser também guardados em ficheiro de texto dado pelo utilizador).
     */
    public static MenuActual _02_dadosGerais() {
        MenuActual estadoMenu = QUERIE_02;
        int escolha_opcao;
        Mes mes;
        String ficheiro;
        String lixo;
        Map<Mes, Integer> clientesDistintos;
        Matriz_Double_12x2 facturacao;
        Matriz_Int_12x2 compras;
        int totalClientesDistintos;
        double tempo_querie;
        Scanner input = new Scanner(System.in);
        Hipermercado hiper = Gesthiper.getHipermercado();

        while (estadoMenu == QUERIE_02) {
            Crono.start();
            compras = moduloContabilidade.getTotalComprasPorMes();
            facturacao = moduloContabilidade.getTotalFacturadoPorMes();
            clientesDistintos = moduloCompras.getNumeroClientesDistintosMeses();
            totalClientesDistintos = moduloCompras.getTotalClientesDistintos();
            tempo_querie = Crono.stop();
            System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
            System.out.print("=============================================================== \n");
            System.out.print("GESTHIPER >> QUERIE 2                                           \n");
            System.out.print("Dados globais                                                   \n");
            System.out.print("=============================================================== \n");

            System.out.print("------------------------------------------- \n");
            System.out.print("|     | Número  |              | Clientes  |\n");
            System.out.print("| Mes | Compras |  Facturacao  | Distintos |\n");
            System.out.print("------------------------------------------- \n");

            for (int i = 0; i < 12; i++) {
                mes = Mes.numero_to_mes(i + 1);

                System.out.printf("| %3s | %7d | %12.2f | %9d |\n", mes.getMes_abreviado(),
                        compras.getValorMesTipoCompra(mes, TipoCompra.AMBOS),
                        facturacao.getValorMesTipoCompra(mes, TipoCompra.AMBOS),
                        clientesDistintos.get(mes));
            }
            System.out.print("------------------------------------------- \n");
            System.out.printf("| Tot | %7d | %12.2f | %9d |\n",
                    compras.getSomaTotal(),
                    facturacao.getSomaTotal(),
                    moduloCompras.getTotalClientesDistintos());

            System.out.print("-------------------------------------------\n");
            System.out.print("Número compras inválidas: " + hiper.getNumeroComprasInvalidas() + "\n");
            System.out.printf("Tempo querie: %.4f segundos.\n", tempo_querie);
            System.out.print("=============================================================== \n");
            System.out.print(" 0 - Sair | 1 - Menu Principal | 2 - Guardar compras inválidas  \n");
            System.out.print("=============================================================== \n");
            System.out.print("Escolha opção: ");
            try {
                escolha_opcao = input.nextInt();
            } catch (InputMismatchException ex) {
                lixo = input.next();
                escolha_opcao = -1;
            }
            switch (escolha_opcao) {
                case 0:
                    estadoMenu = SAIR;
                    break;
                case 1:
                    estadoMenu = MENU_QUERIES;
                    break;
                case 2:
                    System.out.print("Indique o nome do ficheiro onde guardar: ");
                    ficheiro = input.next();
                     {
                        try {
                            hiper.guardaComprasInvalidas(ficheiro);
                            System.out.print("O ficheiro foi guardado com sucesso.\n");
                        } catch (FileNotFoundException ex) {
                            System.out.print("O ficheiro não pôde ser guardado.Excepcao FileNotFound.\n");
                        }
                    }

                    break;
                default:
                    estadoMenu = QUERIE_02;
                    break;
            }
        }
        return estadoMenu;
    }

    /*
     Lista ordenada com os códigos dos produtos nunca comprados e respectivo total;
     */
    public static MenuActual _03_produtosNuncaComprados() {
        MenuActual estadoMenu = QUERIE_03;
        Scanner input = new Scanner(System.in);
        Produto produto;
        String lixo;
        double tempo_querie;
        int numero_pagina = 1, num_elems_pag_actual, inicio_pagina, fim_pagina;
        int numero_resultados, total_paginas, escolha_pag, escolha_opcao_menu;

        Crono.start();
        List<Produto> listaProdutosSemCompras = moduloContabilidade.getProdutosNaoComprados();
        Paginador<Produto> paginador = new Paginador<>(listaProdutosSemCompras, 10, 1);
        tempo_querie = Crono.stop();

        numero_resultados = listaProdutosSemCompras.size();
        total_paginas = paginador.getNumPaginas();

        while (estadoMenu == QUERIE_03) {
            paginador.gotoPagina(numero_pagina);
            inicio_pagina = paginador.getPosInicialPagActual();
            num_elems_pag_actual = paginador.getNumElemsPagActual();
            fim_pagina = inicio_pagina + num_elems_pag_actual;
            System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
            System.out.print("==================================================== \n");
            System.out.print("GESTHIPER >> QUERIE 3            \n");
            System.out.print("Produtos não comprados                 \n");
            System.out.print("==================================================== \n");

            if (numero_resultados > 0) {
                System.out.printf("Pagina %2d/%d \n", numero_pagina, total_paginas);
                System.out.printf("--------------------\n");
                System.out.printf("|       |  Codigo   |\n");
                System.out.printf("|   #   |  Produto  |\n");
                System.out.printf("--------------------\n");
                for (int i = 0; i < num_elems_pag_actual; i++) {
                    produto = listaProdutosSemCompras.get(inicio_pagina + i);
                    System.out.printf("| %5d | %9s |\n", inicio_pagina + i + 1, produto.getCodigoProduto());
                }
                System.out.printf("--------------------\n");
                System.out.printf("A mostrar %d-%d de %d resultados.\n",
                        inicio_pagina + 1, fim_pagina, numero_resultados);
                System.out.printf("Tempo querie: %.4f segundos.\n", tempo_querie);
            } else {
                System.out.print("Não há resultados a mostrar.\n");
            }

            System.out.print("==================================================== \n");
            System.out.print("0 - Sair | 1 - Menu Principal   \n");
            System.out.print("[<<] 4   [<] 5  ###  6 [>]   7 [>>]  |   2 - Pag...  \n");
            System.out.print("==================================================== \n");
            System.out.print("Insira nº da opcao > ");
            try {
                escolha_opcao_menu = input.nextInt();
            } catch (InputMismatchException ex) {
                lixo = input.next();
                escolha_opcao_menu = -1;
            }

            switch (escolha_opcao_menu) {
                case 0:
                    estadoMenu = SAIR;
                    break;
                case 1:
                    estadoMenu = MENU_QUERIES;
                    break;
                case 2:
                    System.out.printf("Indique a pag para que quer ir: ");
                    escolha_pag = input.nextInt();
                    if (escolha_pag > 0 && escolha_pag <= total_paginas) {
                        numero_pagina = escolha_pag;
                    }
                    break;
                case 4:
                    numero_pagina = 1;
                    break;
                case 5:
                    if (numero_pagina > 1) {
                        numero_pagina--;
                    }
                    break;
                case 6:
                    if (numero_pagina < total_paginas) {
                        numero_pagina++;
                    }
                    break;
                case 7:
                    numero_pagina = total_paginas;
                    break;
                default:
                    estadoMenu = QUERIE_03;
            }
        }
        return estadoMenu;
    }

    /*
     Lista ordenada com os códigos dos clientes que nunca compraram e seu total;
     */
    public static MenuActual _04_clientesQueNuncaCompraram() {
        MenuActual estadoMenu = QUERIE_04;
        Scanner input = new Scanner(System.in);
        Cliente cliente;
        String lixo;
        double tempo_querie;
        int numero_pagina = 1, num_elems_pag_actual, inicio_pagina, fim_pagina;
        int numero_resultados, total_paginas, escolha_pag, escolha_opcao_menu;

        Crono.start();
        List<Cliente> listaClientesSemCompras = moduloCompras.getClientesSemCompras();
        Paginador<Cliente> paginador = new Paginador<>(listaClientesSemCompras, 10, 1);
        tempo_querie = Crono.stop();
        numero_resultados = listaClientesSemCompras.size();
        total_paginas = paginador.getNumPaginas();

        while (estadoMenu == QUERIE_04) {
            paginador.gotoPagina(numero_pagina);
            inicio_pagina = paginador.getPosInicialPagActual();
            num_elems_pag_actual = paginador.getNumElemsPagActual();
            fim_pagina = inicio_pagina + num_elems_pag_actual;
            System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
            System.out.print("==================================================== \n");
            System.out.print("GESTHIPER >> QUERIE 4            \n");
            System.out.print("Clientes sem compras                 \n");
            System.out.print("==================================================== \n");

            if (numero_resultados > 0) {
                System.out.printf("Pagina %2d/%d \n", numero_pagina, total_paginas);
                System.out.printf("--------------------\n");
                System.out.printf("|       |  Codigo   |\n");
                System.out.printf("|   #   |  Cliente  |\n");
                System.out.printf("--------------------\n");
                for (int i = 0; i < num_elems_pag_actual; i++) {
                    cliente = listaClientesSemCompras.get(inicio_pagina + i);
                    System.out.printf("| %5d | %9s |\n", inicio_pagina + i + 1, cliente.getCodigoCliente());
                }
                System.out.printf("--------------------\n");
                System.out.printf("A mostrar %d-%d de %d resultados.\n",
                        inicio_pagina + 1, fim_pagina, numero_resultados);
                System.out.printf("Tempo querie: %.4f segundos.\n", tempo_querie);
            } else {
                System.out.print("Não há resultados a mostrar.\n");
            }

            System.out.print("==================================================== \n");
            System.out.print("0 - Sair | 1 - Menu Principal   \n");
            System.out.print("[<<] 4   [<] 5  ###  6 [>]   7 [>>]  |   2 - Pag...  \n");
            System.out.print("==================================================== \n");
            System.out.print("Insira nº da opcao > ");
            try {
                escolha_opcao_menu = input.nextInt();
            } catch (InputMismatchException ex) {
                lixo = input.next();
                escolha_opcao_menu = -1;
            }

            switch (escolha_opcao_menu) {
                case 0:
                    estadoMenu = SAIR;
                    break;
                case 1:
                    estadoMenu = MENU_QUERIES;
                    break;
                case 2:
                    System.out.printf("Indique a pag para que quer ir: ");
                    escolha_pag = input.nextInt();
                    if (escolha_pag > 0 && escolha_pag <= total_paginas) {
                        numero_pagina = escolha_pag;
                    }
                    break;
                case 4:
                    numero_pagina = 1;
                    break;
                case 5:
                    if (numero_pagina > 1) {
                        numero_pagina--;
                    }
                    break;
                case 6:
                    if (numero_pagina < total_paginas) {
                        numero_pagina++;
                    }
                    break;
                case 7:
                    numero_pagina = total_paginas;
                    break;
                default:
                    estadoMenu = QUERIE_04;
            }
        }
        return estadoMenu;
    }

    /*
     Dado um mês válido, determinar o número total de compras e o total de clientes
     distintos que as realizaram;
     */
    public static MenuActual _05_comprasEClientesNumMes() {
        MenuActual estadoMenu = QUERIE_05a;
        int escolha_mes, escolha_opcao;
        String lixo;
        String linha_user;
        String srt_numero;
        Mes mes_escolhido;
        double tempo_querie;

        while (estadoMenu == QUERIE_05a) {
            Scanner input = new Scanner(System.in);
            System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
            System.out.print("========================================================== \n");
            System.out.print("GESTHIPER >> QUERIE 5                                      \n");
            System.out.print("Nº de compras e Clientes num mês                           \n");
            System.out.print("========================================================== \n");
            System.out.print("q - sair | b - voltar                                      \n");
            System.out.print("---------------------------------------------------------- \n");
            System.out.print("Indique o nº do mês: ");
            try {
                linha_user = input.nextLine();
                escolha_mes = 1;
                if (linha_user.compareToIgnoreCase("q") == 0) {
                    estadoMenu = SAIR;
                }
                if (linha_user.compareToIgnoreCase("b") == 0) {
                    estadoMenu = MENU_QUERIES;
                }
                if (estadoMenu == QUERIE_05a) {
                    escolha_mes = Integer.parseInt(linha_user);
                    estadoMenu = QUERIE_05b;
                }
            } catch (NoSuchElementException ex) {
                lixo = input.next();
                escolha_mes = 1;
            } catch (NumberFormatException e2) {
                estadoMenu = QUERIE_05a;
                escolha_mes = 1;
            }

            while (estadoMenu == QUERIE_05b) {
                System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
                System.out.print("========================================================== \n");
                System.out.print("GESTHIPER >> QUERIE 5            \n");
                System.out.print("Nº de compras e Clientes num mês                \n");
                System.out.print("========================================================== \n");

                if (escolha_mes > 0 && escolha_mes <= 12) {
                    mes_escolhido = Mes.numero_to_mes(escolha_mes);
                    Crono.start();
                    System.out.print("Mês: " + mes_escolhido.getMes_extenso() + "\n");
                    System.out.print("Nº clientes distintos: " + moduloCompras.getNumeroClientesDistintosMes(mes_escolhido) + "\n");
                    System.out.print("Nº compras: " + moduloContabilidade.getTotalComprasPorMes(mes_escolhido) + "\n");
                    tempo_querie = Crono.stop();
                    System.out.print("-------------------------------------------\n");
                    System.out.printf("Tempo querie: %.4f segundos.\n", tempo_querie);
                } else {
                    System.out.print("Mês inserido é inválido\n");
                }

                System.out.print("========================================================== \n");
                System.out.print(" 0 - Sair | 1 - Menu Principal | 2 - Procurar outro mês    \n");
                System.out.print("========================================================== \n");
                System.out.print("Escolha opção: ");
                try {
                    escolha_opcao = input.nextInt();
                } catch (InputMismatchException ex) {
                    lixo = input.next();
                    escolha_opcao = -1;
                }
                switch (escolha_opcao) {
                    case 0:
                        estadoMenu = SAIR;
                        break;
                    case 1:
                        estadoMenu = MENU_QUERIES;
                        break;
                    case 2:
                        estadoMenu = QUERIE_05a;
                        break;
                    default:
                        estadoMenu = QUERIE_05b;
                        break;
                }
            }
        }
        return estadoMenu;
    }

    /*
     Dado um código de cliente, determinar, para cada mês, quantas compras fez,
     quantos produtos distintos comprou e quanto gastou. Apresentar também o total
     anual facturado ao cliente;
     */
    public static MenuActual _06_comprasClienteMeses() {
        MenuActual estadoMenu = QUERIE_06a;
        String cliente_inserido;
        Mes mes;
        Cliente cliente;
        String lixo;
        int escolha_opcao;
        double tempo_querie;
        Matriz_Int_12x2 numeroCompras;
        Matriz_Double_12x2 dinheiroGasto;
        Map<Mes, Integer> numeroProdsDistintosPorMes;

        while (estadoMenu == QUERIE_06a) {
            Scanner input = new Scanner(System.in);
            System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
            System.out.print("============================================================ \n");
            System.out.print("GESTHIPER >> QUERIE 6            \n");
            System.out.print("Compras de cliente            \n");
            System.out.print("============================================================ \n");
            System.out.print("q - sair | b - voltar                                        \n");
            System.out.print("------------------------------------------------------------ \n");
            System.out.print("Indique o cliente que quer procurar: ");

            try {
                cliente_inserido = input.nextLine();
                if (cliente_inserido.compareToIgnoreCase("q") == 0) {
                    estadoMenu = SAIR;
                } else if (cliente_inserido.compareToIgnoreCase("b") == 0) {
                    estadoMenu = MENU_QUERIES;
                } else {
                    estadoMenu = QUERIE_06b;
                }
            } catch (NoSuchElementException ex) {
                lixo = input.next();
                cliente_inserido = "";
                estadoMenu = QUERIE_06a;
            }

            while (estadoMenu == QUERIE_06b) {

                System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
                System.out.print("============================================================ \n");
                System.out.print("GESTHIPER >> QUERIE 6            \n");
                System.out.print("Compras de cliente            \n");
                System.out.print("============================================================ \n");

                cliente = new Cliente(cliente_inserido);

                try {
                    Crono.start();
                    numeroCompras = moduloCompras.getNumeroComprasClienteMeses(cliente);
                    dinheiroGasto = moduloCompras.getDinheiroGastoClienteMeses(cliente);
                    numeroProdsDistintosPorMes = moduloCompras.getNumeroProdutosDisntintosPorMesCliente(cliente);
                    tempo_querie = Crono.stop();
                    System.out.print("Código Cliente: " + cliente.getCodigoCliente() + "\n");
                    System.out.print("---------------------------------------\n");
                    System.out.print("|     | Número  | Produtos  |         |\n");
                    System.out.print("| Mes | Compras | Distintos | € Gasto |\n");
                    System.out.print("---------------------------------------\n");

                    for (int i = 0; i < 12; i++) {
                        mes = Mes.numero_to_mes(i + 1);

                        System.out.printf("| %3s | %7d | %9d | %7.2f |\n", mes.getMes_abreviado(),
                                numeroCompras.getValorMesTipoCompra(mes, TipoCompra.AMBOS),
                                numeroProdsDistintosPorMes.get(mes),
                                dinheiroGasto.getValorMesTipoCompra(mes, TipoCompra.AMBOS));
                    }

                    System.out.print("---------------------------------------\n");
                    System.out.printf("| Tot | %7d | %9d | %7.2f |\n",
                            numeroCompras.getSomaTotal(),
                            moduloCompras.getNumeroProdutosDistintosCliente(cliente),
                            dinheiroGasto.getSomaTotal());
                    System.out.print("---------------------------------------\n");
                    System.out.printf("Tempo querie: %.4f segundos.\n", tempo_querie);

                } catch (ClienteNaoExisteException ex) {
                    System.out.print("O cliente " + cliente_inserido + " não existe.\n");
                }

                System.out.print("============================================================ \n");
                System.out.print(" 0 - Sair | 1 - Menu Principal | 2 - Procurar outro cliente  \n");
                System.out.print("============================================================ \n");
                System.out.print("Escolha opção: ");
                try {
                    escolha_opcao = input.nextInt();
                } catch (InputMismatchException ex) {
                    lixo = input.next();
                    escolha_opcao = -1;
                }
                switch (escolha_opcao) {
                    case 0:
                        estadoMenu = SAIR;
                        break;
                    case 1:
                        estadoMenu = MENU_QUERIES;
                        break;
                    case 2:
                        estadoMenu = QUERIE_06a;
                        break;
                    default:
                        estadoMenu = QUERIE_06b;
                        break;
                }
            }
        }
        return estadoMenu;
    }

    /*
     Dado o código de um produto existente, determinar, mês a mês, quantas vezes foi
     comprado, por quantos clientes diferentes e o total facturado;
     */
    public static MenuActual _07_comprasProdutoTodosMeses() {
        MenuActual estadoMenu = QUERIE_07a;
        String produto_inserido;
        Mes mes;
        String lixo;
        Produto produto;
        Map<Mes, Integer> clientesDistintosProduto;
        Matriz_Double_12x2 facturacaoProduto;
        Matriz_Int_12x2 comprasProduto;
        int escolha_opcao;
        double tempo_querie;

        while (estadoMenu == QUERIE_07a) {
            Scanner input = new Scanner(System.in);
            System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
            System.out.print("============================================================ \n");
            System.out.print("GESTHIPER >> QUERIE 7            \n");
            System.out.print("Compras de produto            \n");
            System.out.print("============================================================ \n");
            System.out.print("q - sair | b - voltar                                        \n");
            System.out.print("------------------------------------------------------------ \n");
            System.out.print("Indique o produto que quer procurar: ");

            try {
                produto_inserido = input.nextLine();
                if (produto_inserido.compareToIgnoreCase("q") == 0) {
                    estadoMenu = SAIR;
                } else if (produto_inserido.compareToIgnoreCase("b") == 0) {
                    estadoMenu = MENU_QUERIES;
                } else {
                    estadoMenu = QUERIE_07b;
                }
            } catch (NoSuchElementException ex) {
                lixo = input.next();
                produto_inserido = "";
                estadoMenu = QUERIE_07a;
            }

            while (estadoMenu == QUERIE_07b) {
                System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
                System.out.print("============================================================ \n");
                System.out.print("GESTHIPER >> QUERIE 7            \n");
                System.out.print("Compras de produto            \n");
                System.out.print("============================================================ \n");

                produto = new Produto(produto_inserido);

                try {
                    Crono.start();
                    clientesDistintosProduto = moduloCompras.getClientesDistintosProdutoMeses(produto);
                    facturacaoProduto = moduloContabilidade.getFacturacaoProdutoMeses(produto);
                    comprasProduto = moduloContabilidade.getComprasProdutoMeses(produto);
                    tempo_querie = Crono.stop();

                    System.out.print("Código Produto: " + produto.getCodigoProduto() + "\n");
                    System.out.print("-------------------------------------------\n");
                    System.out.print("|     | Número  | Clientes  |             |\n");
                    System.out.print("| Mes | Compras | Distintos | € Facturado |\n");
                    System.out.print("-------------------------------------------\n");

                    for (int i = 0; i < 12; i++) {
                        mes = Mes.numero_to_mes(i + 1);

                        System.out.printf("| %3s | %7d | %9d | %11.2f |\n", mes.getMes_abreviado(),
                                comprasProduto.getValorMesTipoCompra(mes, TipoCompra.AMBOS),
                                clientesDistintosProduto.get(mes),
                                facturacaoProduto.getValorMesTipoCompra(mes, TipoCompra.AMBOS));
                    }

                    System.out.print("-------------------------------------------\n");
                    System.out.printf("| Tot | %7d | %9d | %11.2f |\n",
                            comprasProduto.getSomaTotal(),
                            moduloCompras.getTotalClientesDistintosProduto(produto),
                            facturacaoProduto.getSomaTotal());
                    System.out.print("-------------------------------------------\n");
                    System.out.printf("Tempo querie: %.4f segundos.\n", tempo_querie);

                } catch (ProdutoNaoExisteException ex) {
                    System.out.print("O produto " + produto_inserido + " não existe.\n");
                }

                System.out.print("============================================================ \n");
                System.out.print(" 0 - Sair | 1 - Menu Principal | 2 - Procurar outro produto  \n");
                System.out.print("============================================================ \n");
                System.out.print("Escolha opção: ");
                try {
                    escolha_opcao = input.nextInt();
                } catch (InputMismatchException ex) {
                    lixo = input.next();
                    escolha_opcao = -1;
                }
                switch (escolha_opcao) {
                    case 0:
                        estadoMenu = SAIR;
                        break;
                    case 1:
                        estadoMenu = MENU_QUERIES;
                        break;
                    case 2:
                        estadoMenu = QUERIE_07a;
                        break;
                    default:
                        estadoMenu = QUERIE_07b;
                        break;
                }
            }
        }
        return estadoMenu;
    }

    /*
     Dado o código de um produto existente, determinar, mês a mês, quantas vezes foi
     comprado em modo N e em modo P e respectivas facturações;
     */
    public static MenuActual _08_comprasProdutoModoNeP() {
        MenuActual estadoMenu = QUERIE_08a;
        String produto_inserido;
        Mes mes;
        Produto produto;
        String lixo;
        int escolha_opcao;
        double tempo_querie;
        Matriz_Double_12x2 facturacaoProduto;
        Matriz_Int_12x2 comprasProduto;

        while (estadoMenu == QUERIE_08a) {
            Scanner input = new Scanner(System.in);
            System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
            System.out.print("============================================================ \n");
            System.out.print("GESTHIPER >> QUERIE 8                             \n");
            System.out.print("Compras / Facturação de produto                   \n");
            System.out.print("============================================================ \n");
            System.out.print("q - sair | b - voltar                                        \n");
            System.out.print("------------------------------------------------------------ \n");
            System.out.print("Indique o produto que quer procurar: ");

            try {
                produto_inserido = input.nextLine();
                if (produto_inserido.compareToIgnoreCase("q") == 0) {
                    estadoMenu = SAIR;
                } else if (produto_inserido.compareToIgnoreCase("b") == 0) {
                    estadoMenu = MENU_QUERIES;
                } else {
                    estadoMenu = QUERIE_08b;
                }
            } catch (NoSuchElementException ex) {
                lixo = input.next();
                produto_inserido = "";
                estadoMenu = QUERIE_08a;
            }

            while (estadoMenu == QUERIE_08b) {
                System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
                System.out.print("============================================================ \n");
                System.out.print("GESTHIPER >> QUERIE 8                             \n");
                System.out.print("Compras / Facturação de produto                   \n");
                System.out.print("============================================================ \n");

                produto = new Produto(produto_inserido);
                try {
                    Crono.start();
                    facturacaoProduto = moduloContabilidade.getFacturacaoProdutoMeses(produto);
                    comprasProduto = moduloContabilidade.getComprasProdutoMeses(produto);
                    tempo_querie = Crono.stop();
                    System.out.print("Código Produto: " + produto.getCodigoProduto() + "\n");
                    System.out.print("-------------------------------------------------------------------------- \n");
                    System.out.print("|     |       Número Compras     ||          Facturacao                  ||\n");
                    System.out.print("| Mes | Normal |  Promo  | Total ||   Normal   |   Promo   |    Total    ||\n");
                    System.out.print("-------------------------------------------------------------------------- \n");

                    for (int i = 0; i < 12; i++) {
                        mes = Mes.numero_to_mes(i + 1);

                        System.out.printf("| %3s | %6d | %7d | %5d || %10.2f | %9.2f | %11.2f ||\n", mes.getMes_abreviado(),
                                comprasProduto.getValorMesTipoCompra(mes, TipoCompra.NORMAL), comprasProduto.getValorMesTipoCompra(mes, TipoCompra.PROMOCAO), comprasProduto.getValorMesTipoCompra(mes, TipoCompra.AMBOS),
                                facturacaoProduto.getValorMesTipoCompra(mes, TipoCompra.NORMAL), facturacaoProduto.getValorMesTipoCompra(mes, TipoCompra.PROMOCAO), facturacaoProduto.getValorMesTipoCompra(mes, TipoCompra.AMBOS));
                    }

                    System.out.print("-------------------------------------------------------------------------- \n");
                    System.out.printf("| Tot | %6d | %7d | %5d || %10.2f | %9.2f | %11.2f ||\n",
                            comprasProduto.getValorEntreMeses(Mes.JANEIRO, Mes.DEZEMBRO, TipoCompra.NORMAL), comprasProduto.getValorEntreMeses(Mes.JANEIRO, Mes.DEZEMBRO, TipoCompra.PROMOCAO), comprasProduto.getValorEntreMeses(Mes.JANEIRO, Mes.DEZEMBRO, TipoCompra.AMBOS),
                            facturacaoProduto.getValorEntreMeses(Mes.JANEIRO, Mes.DEZEMBRO, TipoCompra.NORMAL), facturacaoProduto.getValorEntreMeses(Mes.JANEIRO, Mes.DEZEMBRO, TipoCompra.PROMOCAO), facturacaoProduto.getValorEntreMeses(Mes.JANEIRO, Mes.DEZEMBRO, TipoCompra.AMBOS));
                    System.out.print("-------------------------------------------------------------------------- \n");
                    System.out.printf("Tempo querie: %.6f segundos.\n", tempo_querie);
                } catch (ProdutoNaoExisteException ex) {
                    System.out.print("O produto " + produto_inserido + " não existe.\n");
                }

                System.out.print("============================================================ \n");
                System.out.print(" 0 - Sair | 1 - Menu Principal | 2 - Procurar outro produto  \n");
                System.out.print("============================================================ \n");
                System.out.print("Escolha opção: ");
                try {
                    escolha_opcao = input.nextInt();
                } catch (InputMismatchException ex) {
                    lixo = input.next();
                    escolha_opcao = -1;
                }
                switch (escolha_opcao) {
                    case 0:
                        estadoMenu = SAIR;
                        break;
                    case 1:
                        estadoMenu = MENU_QUERIES;
                        break;
                    case 2:
                        estadoMenu = QUERIE_08a;
                        break;
                    default:
                        estadoMenu = QUERIE_08b;
                        break;
                }
            }
        }
        return estadoMenu;
    }

    /*
     Dado o código de um cliente determinar a lista de códigos de produtos que mais
     comprou (e quantos), ordenada por ordem decrescente de quantidade e, para
     quantidades iguais, por ordem alfabética dos códigos;
     */
    public static MenuActual _09_produtosMaisCompradosCliente() {
        MenuActual estadoMenu = QUERIE_09a;

        Cliente cliente;
        String cliente_lido;
        ParProdutoQuantidadeComprada par;
        String lixo;
        double tempo_querie;
        int numero_pagina = 1, num_elems_pag_actual, inicio_pagina, fim_pagina;
        int numero_resultados, total_paginas, escolha_pag, escolha_opcao_menu;

        while (estadoMenu == QUERIE_09a) {
            Scanner input = new Scanner(System.in);
            System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
            System.out.print("================================================= \n");
            System.out.print("GESTHIPER >> QUERIE 9            \n");
            System.out.print("Produtos mais comprados cliente                   \n");
            System.out.print("================================================= \n");
            System.out.print("q - sair | b - voltar                             \n");
            System.out.print("------------------------------------------------- \n");
            System.out.print("Indique o cliente que pretende procurar: ");
            try {
                cliente_lido = input.nextLine();
                if (cliente_lido.compareToIgnoreCase("q") == 0) {
                    estadoMenu = SAIR;
                } else if (cliente_lido.compareToIgnoreCase("b") == 0) {
                    estadoMenu = MENU_QUERIES;
                } else {
                    estadoMenu = QUERIE_09b;
                }
            } catch (NoSuchElementException ex) {
                lixo = input.next();
                cliente_lido = "";
                estadoMenu = QUERIE_09a;
            }

            cliente = new Cliente(cliente_lido);
            while (estadoMenu == QUERIE_09b) {
                try {
                    Crono.start();
                    List<ParProdutoQuantidadeComprada> lista_pares = moduloCompras.getParesProdutoNumComprasCliente(cliente);
                    Paginador<ParProdutoQuantidadeComprada> paginador = new Paginador<>(lista_pares, 10, 1);
                    tempo_querie = Crono.stop();
                    numero_resultados = lista_pares.size();
                    total_paginas = paginador.getNumPaginas();
                    estadoMenu = QUERIE_09c;
                    while (estadoMenu == QUERIE_09c) {
                        paginador.gotoPagina(numero_pagina);
                        inicio_pagina = paginador.getPosInicialPagActual();
                        num_elems_pag_actual = paginador.getNumElemsPagActual();
                        fim_pagina = inicio_pagina + num_elems_pag_actual;

                        System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
                        System.out.print("============================================================= \n");
                        System.out.print("GESTHIPER >> QUERIE 9            \n");
                        System.out.print("Produtos mais comprados cliente                 \n");
                        System.out.print("============================================================= \n");

                        if (numero_resultados > 0) {
                            System.out.print("Código Cliente: " + cliente.getCodigoCliente() + "\n");
                            System.out.printf("Pagina %2d/%d \n", numero_pagina, total_paginas);
                            System.out.printf("----------------------------------\n");
                            System.out.printf("|       |  Codigo   |            |\n");
                            System.out.printf("|   #   |  Produto  | Quantidade |\n");
                            System.out.printf("----------------------------------\n");
                            for (int i = 0; i < num_elems_pag_actual; i++) {
                                par = lista_pares.get(inicio_pagina + i);
                                System.out.printf("| %5d | %9s | %10d |\n",
                                        inicio_pagina + i + 1, par.getProduto().getCodigoProduto(), par.getQuantidadeComprada());
                            }
                            System.out.printf("----------------------------------\n");
                            System.out.printf("A mostrar %d-%d de %d resultados.\n",
                                    inicio_pagina + 1, fim_pagina, numero_resultados);
                            System.out.printf("Tempo querie: %.4f segundos.\n", tempo_querie);
                        } else {
                            System.out.print("Não há resultados a mostrar.\n");
                        }

                        System.out.print("============================================================= \n");
                        System.out.print("0 - Sair | 1 - Menu Principal  | 3 - Procurar outro cliente   \n");
                        System.out.print("[<<] 4   [<] 5  ###  6 [>]   7 [>>]  |   2 - Pag..            \n");
                        System.out.print("============================================================= \n");
                        System.out.print("Insira nº da opcao > ");
                        try {
                            escolha_opcao_menu = input.nextInt();
                        } catch (InputMismatchException ex) {
                            lixo = input.next();
                            escolha_opcao_menu = -1;
                        }

                        switch (escolha_opcao_menu) {
                            case 0:
                                estadoMenu = SAIR;
                                break;
                            case 1:
                                estadoMenu = MENU_QUERIES;
                                break;
                            case 2:
                                System.out.printf("Indique a pag para que quer ir: ");
                                escolha_pag = input.nextInt();
                                if (escolha_pag > 0 && escolha_pag <= total_paginas) {
                                    numero_pagina = escolha_pag;
                                }
                                break;
                            case 3:
                                estadoMenu = QUERIE_09a;
                                break;
                            case 4:
                                numero_pagina = 1;
                                break;
                            case 5:
                                if (numero_pagina > 1) {
                                    numero_pagina--;
                                }
                                break;
                            case 6:
                                if (numero_pagina < total_paginas) {
                                    numero_pagina++;
                                }
                                break;
                            case 7:
                                numero_pagina = total_paginas;
                                break;
                            default:
                                estadoMenu = QUERIE_09c;
                        }
                    }
                } catch (ClienteNaoExisteException ex) {
                    System.out.print("O cliente " + cliente_lido + " não existe.\n");
                    System.out.print("==================================================== \n");
                    System.out.print("0 - Sair | 1 - Menu Principal  | 2 - Procurar outro cliente \n");
                    System.out.print("==================================================== \n");
                    System.out.print("Insira nº da opcao > ");
                    try {
                        escolha_opcao_menu = input.nextInt();
                    } catch (InputMismatchException ex1) {
                        lixo = input.next();
                        escolha_opcao_menu = -1;
                    }

                    switch (escolha_opcao_menu) {
                        case 0:
                            estadoMenu = SAIR;
                            break;
                        case 1:
                            estadoMenu = MENU_QUERIES;
                            break;
                        case 2:
                            estadoMenu = QUERIE_09a;
                            break;
                        default:
                            estadoMenu = QUERIE_09a;
                    }
                }
            }
        }
        return estadoMenu;
    }

    /*
     Determinar o conjunto dos X produtos mais vendidos em todo o ano (em número de
     unidades vendidas) indicando o número total de distintos clientes que o
     compraram (X é um inteiro dado pelo utilizador);
     */
    public static MenuActual _10_produtosMaisVendidos() {
        MenuActual estadoMenu = QUERIE_10a;

        ParProdutoQuantidadeComprada par;
        ParProdutoQuantidadeComprada produto;
        String lixo;
        String linha_user;
        double tempo_querie;
        double tempo_get_lista_produtos;
        double tempo_get_clientes_distintos;
        int topN;
        int numero_pagina, num_elems_pag_actual, inicio_pagina, fim_pagina;
        int numero_resultados, total_paginas, escolha_pag, escolha_opcao_menu;

        while (estadoMenu == QUERIE_10a) {
            Scanner input = new Scanner(System.in);
            numero_pagina = 1;
            System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
            System.out.print("================================================= \n");
            System.out.print("GESTHIPER >> QUERIE 10                            \n");
            System.out.print("N Produtos mais vendidos                          \n");
            System.out.print("================================================= \n");
            System.out.print("q - sair | b - voltar                             \n");
            System.out.print("------------------------------------------------- \n");
            System.out.print("Qual o top de produtos deseja ver (N)?: ");

            try {
                linha_user = input.nextLine();
                topN = 1;
                if (linha_user.compareToIgnoreCase("q") == 0) {
                    estadoMenu = SAIR;
                }
                if (linha_user.compareToIgnoreCase("b") == 0) {
                    estadoMenu = MENU_QUERIES;
                }
                if (estadoMenu == QUERIE_10a) {
                    topN = Integer.parseInt(linha_user);
                    estadoMenu = QUERIE_10b;
                }
            } catch (NoSuchElementException ex) {
                lixo = input.next();
                topN = -1;
            } catch (NumberFormatException e2) {
                estadoMenu = QUERIE_10a;
                topN = -1;
            }

            while (estadoMenu == QUERIE_10b) {
                if (topN > 0) {
                    Crono.start();
                    List<ParProdutoQuantidadeComprada> lista_produtos = moduloContabilidade.getProdutosMaisVendidos(topN);
                    tempo_get_lista_produtos = Crono.stop();

                    Crono.start();
                    Paginador<ParProdutoQuantidadeComprada> paginador = new Paginador<>(lista_produtos, 10, 1);
                    ArrayList<Integer> numeroClientes = new ArrayList<>();
                    for (ParProdutoQuantidadeComprada par_it : lista_produtos) {
                        try {
                            numeroClientes.add(moduloCompras.getTotalClientesDistintosProduto(par_it.getProduto()));
                        } catch (ProdutoNaoExisteException ex) {
                            numeroClientes.add(0);
                        }
                    }
                    tempo_get_clientes_distintos = Crono.stop();
                    estadoMenu = QUERIE_10c;
                    while (estadoMenu == QUERIE_10c) {
                        numero_resultados = lista_produtos.size();
                        total_paginas = paginador.getNumPaginas();

                        paginador.gotoPagina(numero_pagina);
                        inicio_pagina = paginador.getPosInicialPagActual();
                        num_elems_pag_actual = paginador.getNumElemsPagActual();
                        fim_pagina = inicio_pagina + num_elems_pag_actual;
                        System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
                        System.out.print("================================================= \n");
                        System.out.print("GESTHIPER >> QUERIE 10                            \n");
                        System.out.print("N Produtos mais vendidos                          \n");
                        System.out.print("================================================= \n");

                        if (numero_resultados > 0) {
                            System.out.printf("Pagina %2d/%d \n", numero_pagina, total_paginas);
                            System.out.printf("--------------------------------------------\n");
                            System.out.printf("|       |  Codigo   | Unidades | Clientes  |\n");
                            System.out.printf("|   #   |  Produto  | Vendidas | Distintos |\n");
                            System.out.printf("--------------------------------------------\n");
                            for (int i = 0; i < num_elems_pag_actual; i++) {
                                par = lista_produtos.get(inicio_pagina + i);
                                System.out.printf("| %5d | %9s | %8d | %9d |\n",
                                        inicio_pagina + i + 1,
                                        par.getProduto().getCodigoProduto(),
                                        par.getQuantidadeComprada(),
                                        numeroClientes.get(inicio_pagina + i));
                            }
                            System.out.printf("--------------------------------------------\n");
                            System.out.printf("A mostrar %d-%d de %d resultados.\n",
                                    inicio_pagina + 1, fim_pagina, numero_resultados);
                            System.out.printf("Tempo querie: %.4f segundos. (Produtos: %.4f, Clientes: %.4f)\n",
                                    tempo_get_clientes_distintos + tempo_get_lista_produtos,
                                    tempo_get_lista_produtos,
                                    tempo_get_clientes_distintos);
                        } else {
                            System.out.print("Não há resultados a mostrar.\n");
                        }

                        System.out.print("==================================================== \n");
                        System.out.print("0 - Sair | 1 - Menu Principal  | 3 - Escolher novo N \n");
                        System.out.print("[<<] 4   [<] 5  ###  6 [>]   7 [>>]  |   2 - Pag...  \n");
                        System.out.print("==================================================== \n");
                        System.out.print("Insira nº da opcao > ");
                        try {
                            escolha_opcao_menu = input.nextInt();
                        } catch (InputMismatchException ex) {
                            lixo = input.next();
                            escolha_opcao_menu = -1;
                        }

                        switch (escolha_opcao_menu) {
                            case 0:
                                estadoMenu = SAIR;
                                break;
                            case 1:
                                estadoMenu = MENU_QUERIES;
                                break;
                            case 2:
                                System.out.printf("Indique a pag para que quer ir: ");
                                escolha_pag = input.nextInt();
                                if (escolha_pag > 0 && escolha_pag <= total_paginas) {
                                    numero_pagina = escolha_pag;
                                }
                                break;
                            case 3:
                                estadoMenu = QUERIE_10a;
                                break;
                            case 4:
                                numero_pagina = 1;
                                break;
                            case 5:
                                if (numero_pagina > 1) {
                                    numero_pagina--;
                                }
                                break;
                            case 6:
                                if (numero_pagina < total_paginas) {
                                    numero_pagina++;
                                }
                                break;
                            case 7:
                                numero_pagina = total_paginas;
                                break;
                            default:
                                estadoMenu = QUERIE_10c;
                        }
                    }
                } else {
                    System.out.print("Não foi possível ler o número inserido correctamente.\n");
                    System.out.print("==================================================== \n");
                    System.out.print("0 - Sair | 1 - Menu Principal  | 2 - Escolher outro N \n");
                    System.out.print("==================================================== \n");
                    System.out.print("Insira nº da opcao > ");
                    try {
                        escolha_opcao_menu = input.nextInt();
                    } catch (InputMismatchException ex) {
                        lixo = input.next();
                        escolha_opcao_menu = -1;
                    }

                    switch (escolha_opcao_menu) {
                        case 0:
                            estadoMenu = SAIR;
                            break;
                        case 1:
                            estadoMenu = MENU_QUERIES;
                            break;
                        case 2:
                            estadoMenu = QUERIE_10a;
                            break;
                        default:
                            estadoMenu = QUERIE_10a;
                    }
                }
            }
        }
        return estadoMenu;
    }

    /*
     Determinar os X clientes que compraram um maior número de diferentes produtos,
     indicando quantos, sendo o critério de ordenação igual a 7;
     */
    public static MenuActual _11_clientesComMaisProdutosDiferentesComprados() {
        MenuActual estadoMenu = QUERIE_11a;

        ParClienteProdutosDiferentes par;
        String lixo;
        String linha_user;
        int topN;
        double tempo_querie;
        int numero_pagina, num_elems_pag_actual, inicio_pagina, fim_pagina;
        int numero_resultados, total_paginas, escolha_pag, escolha_opcao_menu;

        while (estadoMenu == QUERIE_11a) {
            Scanner input = new Scanner(System.in);
            numero_pagina = 1;
            System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
            System.out.print("==================================================== \n");
            System.out.print("GESTHIPER >> QUERIE 11            \n");
            System.out.print("N Clientes com mais produtos diferentes comprados    \n");
            System.out.print("==================================================== \n");
            System.out.print("q - sair | b - voltar                             \n");
            System.out.print("------------------------------------------------- \n");
            System.out.print("Qual o top de clientes que deseja ver (N)?: ");

            try {
                linha_user = input.nextLine();
                topN = 1;
                if (linha_user.compareToIgnoreCase("q") == 0) {
                    estadoMenu = SAIR;
                }
                if (linha_user.compareToIgnoreCase("b") == 0) {
                    estadoMenu = MENU_QUERIES;
                }
                if (estadoMenu == QUERIE_11a) {
                    topN = Integer.parseInt(linha_user);
                    estadoMenu = QUERIE_11b;
                }
            } catch (NoSuchElementException ex) {
                lixo = input.next();
                topN = -1;
            } catch (NumberFormatException e2) {
                estadoMenu = QUERIE_11a;
                topN = -1;
            }

            while (estadoMenu == QUERIE_11b) {
                if (topN > 0) {
                    Crono.start();
                    List<ParClienteProdutosDiferentes> lista_pares = moduloCompras.getParesClienteProdutosDiferentes(topN);
                    Paginador<ParClienteProdutosDiferentes> paginador = new Paginador<>(lista_pares, 10, 1);
                    tempo_querie = Crono.stop();
                    numero_resultados = lista_pares.size();
                    total_paginas = paginador.getNumPaginas();

                    estadoMenu = QUERIE_11c;

                    while (estadoMenu == QUERIE_11c) {

                        paginador.gotoPagina(numero_pagina);
                        inicio_pagina = paginador.getPosInicialPagActual();
                        num_elems_pag_actual = paginador.getNumElemsPagActual();
                        fim_pagina = inicio_pagina + num_elems_pag_actual;
                        System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
                        System.out.print("==================================================== \n");
                        System.out.print("GESTHIPER >> QUERIE 11            \n");
                        System.out.print("N Clientes com mais produtos diferentes comprados   \n");
                        System.out.print("==================================================== \n");

                        if (numero_resultados > 0) {
                            System.out.printf("Pagina %2d/%d \n", numero_pagina, total_paginas);
                            System.out.printf("---------------------------------\n");
                            System.out.printf("|       |  Codigo   | Produtos  |\n");
                            System.out.printf("|   #   |  Cliente  | Distintos |\n");
                            System.out.printf("---------------------------------\n");
                            for (int i = 0; i < num_elems_pag_actual; i++) {
                                par = lista_pares.get(inicio_pagina + i);
                                System.out.printf("| %5d | %9s | %9d |\n",
                                        inicio_pagina + i + 1, par.getCliente().getCodigoCliente(), par.getProdutosDiferentes());
                            }
                            System.out.printf("---------------------------------\n");
                            System.out.printf("A mostrar %d-%d de %d resultados.\n",
                                    inicio_pagina + 1, fim_pagina, numero_resultados);
                            System.out.printf("Tempo querie: %.4f segundos.\n", tempo_querie);
                        } else {
                            System.out.print("Não há resultados a mostrar.\n");
                        }

                        System.out.print("==================================================== \n");
                        System.out.print("0 - Sair | 1 - Menu Principal  | 3 - Escolher novo N \n");
                        System.out.print("[<<] 4   [<] 5  ###  6 [>]   7 [>>]  |   2 - Pag...  \n");
                        System.out.print("==================================================== \n");
                        System.out.print("Insira nº da opcao > ");
                        try {
                            escolha_opcao_menu = input.nextInt();
                        } catch (InputMismatchException ex) {
                            lixo = input.next();
                            escolha_opcao_menu = -1;
                        }

                        switch (escolha_opcao_menu) {
                            case 0:
                                estadoMenu = SAIR;
                                break;
                            case 1:
                                estadoMenu = MENU_QUERIES;
                                break;
                            case 2:
                                System.out.printf("Indique a pag para que quer ir: ");
                                escolha_pag = input.nextInt();
                                if (escolha_pag > 0 && escolha_pag <= total_paginas) {
                                    numero_pagina = escolha_pag;
                                }
                                break;
                            case 3:
                                estadoMenu = QUERIE_11a;
                                break;
                            case 4:
                                numero_pagina = 1;
                                break;
                            case 5:
                                if (numero_pagina > 1) {
                                    numero_pagina--;
                                }
                                break;
                            case 6:
                                if (numero_pagina < total_paginas) {
                                    numero_pagina++;
                                }
                                break;
                            case 7:
                                numero_pagina = total_paginas;
                                break;
                            default:
                                estadoMenu = QUERIE_11c;
                        }
                    }
                } else {
                    System.out.print("Não foi possível ler o número inserido correctamente.\n");
                    System.out.print("==================================================== \n");
                    System.out.print("0 - Sair | 1 - Menu Principal  | 2 - Escolher outro N \n");
                    System.out.print("==================================================== \n");
                    System.out.print("Insira nº da opcao > ");
                    try {
                        escolha_opcao_menu = input.nextInt();
                    } catch (InputMismatchException ex) {
                        lixo = input.next();
                        escolha_opcao_menu = -1;
                    }

                    switch (escolha_opcao_menu) {
                        case 0:
                            estadoMenu = SAIR;
                            break;
                        case 1:
                            estadoMenu = MENU_QUERIES;
                            break;
                        case 2:
                            estadoMenu = QUERIE_11a;
                            break;
                        default:
                            estadoMenu = QUERIE_11c;
                    }
                }
            }
        }
        return estadoMenu;
    }

    /*
     Dado o código de um produto, determinar o conjunto dos X clientes que mais o
     compraram e qual o valor gasto (ordenação cf. 7);
     */
    public static MenuActual _12_clientesQueMaisCompraramProduto() {
        MenuActual estadoMenu = QUERIE_12a;
        Produto produto;
        String produto_inserido;
        Scanner input;
        TriploClienteQtdCompradaDinheiro triplo;
        String lixo;
        String linha_user;
        int topN;
        double tempo_querie;
        int numero_pagina, num_elems_pag_actual, inicio_pagina, fim_pagina;
        int numero_resultados, total_paginas, escolha_pag, escolha_opcao_menu;

        while (estadoMenu == QUERIE_12a) {
            input = new Scanner(System.in);
            numero_pagina = 1;
            System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
            System.out.print("================================================= \n");
            System.out.print("GESTHIPER >> QUERIE 12            \n");
            System.out.print("N clientes que mais compraram produto   \n");
            System.out.print("================================================= \n");
            System.out.print("q - sair | b - voltar                             \n");
            System.out.print("------------------------------------------------- \n");
            System.out.print("Qual o top de clientes que deseja ver (N)?: ");

            try {
                linha_user = input.nextLine();
                topN = 1;
                if (linha_user.compareToIgnoreCase("q") == 0) {
                    estadoMenu = SAIR;
                }
                if (linha_user.compareToIgnoreCase("b") == 0) {
                    estadoMenu = MENU_QUERIES;
                }
                if (estadoMenu == QUERIE_12a) {
                    topN = Integer.parseInt(linha_user);
                    estadoMenu = QUERIE_12b;
                }
            } catch (NoSuchElementException ex) {
                lixo = input.next();
                topN = -1;
            } catch (NumberFormatException e2) {
                estadoMenu = QUERIE_12a;
                topN = -1;
            }

            if (estadoMenu == QUERIE_12b) {
                input = new Scanner(System.in);
                System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
                System.out.print("================================================= \n");
                System.out.print("GESTHIPER >> QUERIE 12                            \n");
                System.out.print("N clientes que mais compraram produto             \n");
                System.out.print("================================================= \n");
                System.out.print("q - sair | b - voltar                             \n");
                System.out.print("------------------------------------------------- \n");
                System.out.print("Qual o produto que deseja procurar: ");
                try {
                    produto_inserido = input.nextLine();
                    if (produto_inserido.compareToIgnoreCase("q") == 0) {
                        estadoMenu = SAIR;
                    } else if (produto_inserido.compareToIgnoreCase("b") == 0) {
                        estadoMenu = MENU_QUERIES;
                    } else {
                        estadoMenu = QUERIE_12b;
                    }
                } catch (NoSuchElementException ex) {
                    lixo = input.next();
                    produto_inserido = "";
                    estadoMenu = QUERIE_12a;
                }

                System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
                System.out.print("================================================= \n");
                System.out.print("GESTHIPER >> QUERIE 12                            \n");
                System.out.print("N clientes que mais compraram produto             \n");
                System.out.print("================================================= \n");

                produto = new Produto(produto_inserido);

                while (estadoMenu == QUERIE_12b) {
                    if (topN > 0 && catalogoProdutos.existeProduto(produto)) {
                        Crono.start();
                        List<TriploClienteQtdCompradaDinheiro> lista_triplos = moduloCompras.getTriplosClienteQtdCompradaDinheiro(produto, topN);
                        Paginador<TriploClienteQtdCompradaDinheiro> paginador = new Paginador<>(lista_triplos, 10, 1);
                        tempo_querie = Crono.stop();

                        numero_resultados = lista_triplos.size();
                        total_paginas = paginador.getNumPaginas();

                        paginador.gotoPagina(numero_pagina);
                        inicio_pagina = paginador.getPosInicialPagActual();

                        estadoMenu = QUERIE_12c;

                        while (estadoMenu == QUERIE_12c) {
                            input = new Scanner(System.in);
                            System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
                            System.out.print("================================================================= \n");
                            System.out.print("GESTHIPER >> QUERIE 12            \n");
                            System.out.print("N clientes que mais compraram produto   \n");
                            System.out.print("================================================================= \n");

                            num_elems_pag_actual = paginador.getNumElemsPagActual();
                            fim_pagina = inicio_pagina + num_elems_pag_actual;

                            if (numero_resultados > 0) {
                                System.out.printf("Código Produto: %s\n", produto.getCodigoProduto());
                                System.out.printf("Pagina %2d/%d \n", numero_pagina, total_paginas);
                                System.out.printf("---------------------------------------------\n");
                                System.out.printf("|       |  Codigo   | Unidades  |           |\n");
                                System.out.printf("|   #   |  Cliente  | Compradas |  € Gasto  |\n");
                                System.out.printf("---------------------------------------------\n");
                                for (int i = 0; i < num_elems_pag_actual; i++) {
                                    triplo = lista_triplos.get(inicio_pagina + i);
                                    System.out.printf("| %5d | %9s | %9d | %9.2f |\n",
                                            inicio_pagina + i + 1, triplo.getCliente().getCodigoCliente(), triplo.getQuantidadeComprada(), triplo.getTotalDinheirGasto());
                                }
                                System.out.printf("---------------------------------------------\n");
                                System.out.printf("A mostrar %d-%d de %d resultados.\n",
                                        inicio_pagina + 1, fim_pagina, numero_resultados);
                                System.out.printf("Tempo querie: %.4f segundos.\n", tempo_querie);
                            } else {
                                System.out.print("Não há resultados a mostrar.\n");
                            }

                            System.out.print("================================================================= \n");
                            System.out.print("0 - Sair | 1 - Menu Principal  | 3 - Procurar outro produto ou N \n");
                            System.out.print("[<<] 4   [<] 5  ###  6 [>]   7 [>>]  |   2 - Pag...  \n");
                            System.out.print("================================================================= \n");
                            System.out.print("Insira nº da opcao > ");
                            try {
                                escolha_opcao_menu = input.nextInt();
                            } catch (InputMismatchException ex) {
                                lixo = input.next();
                                escolha_opcao_menu = -1;
                            }

                            switch (escolha_opcao_menu) {
                                case 0:
                                    estadoMenu = SAIR;
                                    break;
                                case 1:
                                    estadoMenu = MENU_QUERIES;
                                    break;
                                case 2:
                                    System.out.printf("Indique a pag para que quer ir: ");
                                    escolha_pag = input.nextInt();
                                    if (escolha_pag > 0 && escolha_pag <= total_paginas) {
                                        numero_pagina = escolha_pag;
                                    }
                                    break;
                                case 3:
                                    estadoMenu = QUERIE_12a;
                                    break;
                                case 4:
                                    numero_pagina = 1;
                                    break;
                                case 5:
                                    if (numero_pagina > 1) {
                                        numero_pagina--;
                                    }
                                    break;
                                case 6:
                                    if (numero_pagina < total_paginas) {
                                        numero_pagina++;
                                    }
                                    break;
                                case 7:
                                    numero_pagina = total_paginas;
                                    break;
                                default:
                                    estadoMenu = QUERIE_12c;
                            }

                        }
                    } else {
                        if (topN < 0) {
                            System.out.print("Não foi possível ler o número inserido correctamente.\n");
                        } else {
                            System.out.print("O produto não existe.\n");
                        }

                        System.out.print("================================================================= \n");
                        System.out.print("0 - Sair | 1 - Menu Principal  | 2 - Procurar outro produto ou N \n");
                        System.out.print("================================================================= \n");
                        System.out.print("Insira nº da opcao > ");
                        try {
                            escolha_opcao_menu = input.nextInt();
                        } catch (InputMismatchException ex) {
                            lixo = input.next();
                            escolha_opcao_menu = -1;
                        }

                        switch (escolha_opcao_menu) {
                            case 0:
                                estadoMenu = SAIR;
                                break;
                            case 1:
                                estadoMenu = MENU_QUERIES;
                                break;
                            case 2:
                                estadoMenu = QUERIE_12a;
                                break;
                            default:
                                estadoMenu = QUERIE_12a;
                        }
                    }

                }
            }
        }
        return estadoMenu;
    }
    /*
     A qualquer momento deverá estar disponível uma opção que permita ao utilizador
     gravar toda a estrutura de dados de forma persistente usando ObjectStreams, criando
     por omissão o ficheiro hipermercado.obj ou um outro se indicado pelo utilizador.
     */

    public static MenuActual _13_guardarEmFicheiroObjecto() {
        MenuActual estadoMenu = QUERIE_13a;
        int escolha_opcao;
        Mes mes;
        String ficheiro;
        String lixo;
        double tempo_querie;
        Scanner input;
        Hipermercado hiper = Gesthiper.getHipermercado();

        while (estadoMenu == QUERIE_13a) {
            input = new Scanner(System.in);
            System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
            System.out.print("============================================================= \n");
            System.out.print("GESTHIPER >> QUERIE 13                                        \n");
            System.out.print("Guardar hipermercado em ficheiro objecto                      \n");
            System.out.print("============================================================= \n");
            System.out.print("q - sair | b - voltar                                         \n");
            System.out.print("------------------------------------------------------------- \n");
            System.out.print("Indique o nome do ficheiro onde guardar: ");
            try {
                ficheiro = input.nextLine();
                if (ficheiro.compareToIgnoreCase("q") == 0) {
                    estadoMenu = SAIR;
                } else if (ficheiro.compareToIgnoreCase("b") == 0) {
                    estadoMenu = MENU_QUERIES;
                } else {
                    estadoMenu = QUERIE_13b;
                }
            } catch (NoSuchElementException ex) {
                lixo = input.next();
                ficheiro = "";
                estadoMenu = QUERIE_13a;
            }

            while (estadoMenu == QUERIE_13b) {
                input = new Scanner(System.in);
                System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
                System.out.print("============================================================= \n");
                System.out.print("GESTHIPER >> QUERIE 13            \n");
                System.out.print("Guardar hipermercado em ficheiro objecto               \n");
                System.out.print("============================================================= \n");

                try {
                    System.out.print("A guardar ficheiro...aguarde por favor.\n");
                    Crono.start();
                    LeituraFicheiros.guarda_ficheiro_objecto(ficheiro);
                    tempo_querie = Crono.stop();
                    System.out.print("Ficheiro guardado com sucesso.\n");
                    System.out.printf("Tempo querie: %.4f segundos.\n", tempo_querie);
                } catch (IOException ex) {
                    System.out.println("Erro ao guardar ficheiro");
                }

                System.out.print("============================================================= \n");
                System.out.print(" 0 - Sair | 1 - Menu Principal | 2 - Guardar noutro ficheiro  \n");
                System.out.print("============================================================= \n");
                System.out.print("Escolha opção: ");
                try {
                    escolha_opcao = input.nextInt();
                } catch (InputMismatchException ex) {
                    lixo = input.next();
                    escolha_opcao = -1;
                }
                switch (escolha_opcao) {
                    case 0:
                        estadoMenu = SAIR;
                        break;
                    case 1:
                        estadoMenu = MENU_QUERIES;
                        break;
                    case 2:
                        estadoMenu = QUERIE_13a;
                        break;
                    default:
                        estadoMenu = QUERIE_13a;
                        break;
                }
            }
        }
        return estadoMenu;
    }

    /*
     A qualquer momento também deverá o utilizador poder carregar os dados a partir de
     uma ObjectStream de nome dado, repopulando assim toda a informação da estrutura
     de dados até então existente em memória.
     */
    public static MenuActual _14_carregarFicheiroObjecto() {
        MenuActual estadoMenu = QUERIE_14a;
        int escolha_opcao;
        double tempo_querie;
        Mes mes;
        String lixo;
        String ficheiro;
        Scanner input;

        while (estadoMenu == QUERIE_14a) {
            input = new Scanner(System.in);
            System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
            System.out.print("================================================= \n");
            System.out.print("GESTHIPER >> QUERIE 14                            \n");
            System.out.print("Carregar hipermercado de ficheiro objecto         \n");
            System.out.print("================================================= \n");
            System.out.print("q - sair | b - voltar                             \n");
            System.out.print("------------------------------------------------- \n");
            System.out.print("Indique o nome do ficheiro: ");
            try {
                ficheiro = input.nextLine();
                if (ficheiro.compareToIgnoreCase("q") == 0) {
                    estadoMenu = SAIR;
                } else if (ficheiro.compareToIgnoreCase("b") == 0) {
                    estadoMenu = MENU_QUERIES;
                } else {
                    estadoMenu = QUERIE_14b;
                }
            } catch (NoSuchElementException ex) {
                lixo = input.next();
                ficheiro = "";
                estadoMenu = QUERIE_14a;
            }

            while (estadoMenu == QUERIE_14b) {
                input = new Scanner(System.in);
                System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
                System.out.print("================================================= \n");
                System.out.print("GESTHIPER >> QUERIE 14            \n");
                System.out.print("Carregar hipermercado de ficheiro objecto               \n");
                System.out.print("================================================= \n");

                try {
                    System.out.print("A ler ficheiro...aguarde por favor.\n");
                    Crono.start();
                    LeituraFicheiros.le_ficheiro_objecto(ficheiro);
                    tempo_querie = Crono.stop();
                    System.out.print("Ficheiro carregado com sucesso.\n");
                    System.out.printf("Tempo querie: %.4f segundos.\n", tempo_querie);
                } catch (IOException ex) {
                    System.out.println("Erro ao carregar ficheiro.\n");
                } catch (ClassNotFoundException ex) {
                    System.out.println("Classe não conhecida.\n");
                }

                System.out.print("================================================= \n");
                System.out.print(" 0 - Sair | 1 - Menu Principal | 2 - Guardar noutro ficheiro  \n");
                System.out.print("==================================================== \n");
                System.out.print("Escolha opção: ");
                try {
                    escolha_opcao = input.nextInt();
                } catch (InputMismatchException ex) {
                    lixo = input.next();
                    escolha_opcao = -1;
                }
                switch (escolha_opcao) {
                    case 0:
                        estadoMenu = SAIR;
                        break;
                    case 1:
                        estadoMenu = MENU_QUERIES;
                        break;
                    case 2:
                        estadoMenu = QUERIE_14a;
                        break;
                    default:
                        estadoMenu = QUERIE_14a;
                        break;
                }
            }
        }
        return estadoMenu;
    }

    /*
     O programa deverá também poder ler a qualquer momento um outro qualquer ficheiro
     de texto contendo as informações referentes às compras registadas (cf. Compras1.txt,
     Compras3.txt).
     */
    public static MenuActual _15_mudarFicheiroCompras() {
        MenuActual estadoMenu = QUERIE_15a;
        int escolha_opcao;
        Mes mes;
        double tempo_querie;
        String lixo;
        String ficheiro;
        Hipermercado hiper = Gesthiper.getHipermercado();
        Scanner input;

        while (estadoMenu == QUERIE_15a) {
            input = new Scanner(System.in);
            System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
            System.out.print("================================================= \n");
            System.out.print("GESTHIPER >> QUERIE 15                            \n");
            System.out.print("Mudar ficheiro de compras                         \n");
            System.out.print("================================================= \n");
            System.out.print("q - sair | b - voltar                             \n");
            System.out.print("------------------------------------------------- \n");
            System.out.print("Indique o nome do ficheiro: ");
            
            try {
                ficheiro = input.nextLine();
                if (ficheiro.compareToIgnoreCase("q") == 0) {
                    estadoMenu = SAIR;
                } else if (ficheiro.compareToIgnoreCase("b") == 0) {
                    estadoMenu = MENU_QUERIES;
                } else {
                    estadoMenu = QUERIE_15b;
                }
            } catch (NoSuchElementException ex) {
                lixo = input.next();
                ficheiro = "";
                estadoMenu = QUERIE_15a;
            }

            while (estadoMenu == QUERIE_15b) {
                input = new Scanner(System.in);
                System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
                System.out.print("============================================================ \n");
                System.out.print("GESTHIPER >> QUERIE 15                            \n");
                System.out.print("Mudar ficheiro de compras       \n");
                System.out.print("============================================================ \n");

                try {
                    System.out.print("A ler ficheiro...aguarde por favor.\n");
                    Crono.start();
                    Gesthiper.mudaFicheiroCompras(ficheiro);
                    tempo_querie = Crono.stop();
                    System.out.print("Novo ficheiro lido com sucesso.\n");
                    System.out.printf("Tempo querie: %.4f segundos.\n", tempo_querie);
                } catch (IOException ex) {
                    System.out.print("Erro ao mudar ficheiro compras.\n");
                }

                System.out.print("============================================================ \n");
                System.out.print(" 0 - Sair | 1 - Menu Principal | 2 - Escolher novo ficheiro  \n");
                System.out.print("============================================================ \n");
                System.out.print("Escolha opção: ");
                try {
                    escolha_opcao = input.nextInt();
                } catch (InputMismatchException ex) {
                    lixo = input.next();
                    escolha_opcao = -1;
                }
                switch (escolha_opcao) {
                    case 0:
                        estadoMenu = SAIR;
                        break;
                    case 1:
                        estadoMenu = MENU_QUERIES;
                        break;
                    case 2:
                        estadoMenu = QUERIE_15a;
                        break;
                    default:
                        estadoMenu = QUERIE_15a;
                        break;
                }
            }
        }
        return estadoMenu;
    }

}
