package lei.li3.g50.gesthiper;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import static lei.li3.g50.gesthiper.Interface.MenuActual.*;
import lei.li3.g50.modulos.catalogos.CatalogoClientes;
import lei.li3.g50.modulos.catalogos.CatalogoProdutos;
import lei.li3.g50.modulos.compras.Compras;
import lei.li3.g50.modulos.compras.FichaClienteCompras;
import lei.li3.g50.modulos.contabilidade.Contabilidade;
import lei.li3.g50.modulos.dados.Cliente;
import lei.li3.g50.modulos.dados.Mes;
import lei.li3.g50.modulos.dados.Produto;
import lei.li3.g50.modulos.dados.TipoCompra;
import lei.li3.g50.utilitarios.Paginador;
import lei.li3.g50.utilitarios.ParProdutoQuantidadeComprada;

public final class Interface {

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
        QUERIE_05,
        QUERIE_06,
        QUERIE_07,
        QUERIE_08,
        QUERIE_09,
        QUERIE_10,
        QUERIE_11,
        QUERIE_12,
        QUERIE_13,
        QUERIE_14,
        QUERIE_15;
    }

    private Interface() {
    }

    public static void menuQueries() {
        int escolha;
        Scanner input = new Scanner(System.in);
        MenuActual estadoMenu = MENU_QUERIES;

        catalogoClientes = Gesthiper.getHipermercado().getCatalogoClientes();
        catalogoProdutos = Gesthiper.getHipermercado().getCatalogoProdutos();
        moduloContabilidade = Gesthiper.getHipermercado().getContabilidade();
        moduloCompras = Gesthiper.getHipermercado().getCompras();

        while (estadoMenu == MENU_QUERIES) {
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
            escolha = input.nextInt();

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

    }

    /*
     Apresenta ao utilizador os dados referentes ao último ficheiro de compras lido,
     designadamente, nome dos ficheiros, número total de produtos, total de diferentes
     produtos comprados, total de não comprados, número total de clientes e total dos
     que realizaram compras, total de clientes que nada compraram, total de compras de
     valor total igual a 0 e facturação total.
     */
    public static MenuActual _01_estatisticasUltimoFicheiro() {
        System.out.print("Querie ainda nao implementada\n");
        return MENU_QUERIES;
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
        System.out.print("Querie ainda nao implementada\n");
        return MENU_QUERIES;
    }

    /*
     Lista ordenada com os códigos dos produtos nunca comprados e respectivo total;
     */
    public static MenuActual _03_produtosNuncaComprados() {
        System.out.print("Querie ainda nao implementada\n");
        return MENU_QUERIES;
    }

    /*
     Lista ordenada com os códigos dos clientes que nunca compraram e seu total;
     */
    public static MenuActual _04_clientesQueNuncaCompraram() {
        MenuActual estadoMenu = QUERIE_04;
        Scanner input = new Scanner(System.in);
        Cliente cliente;
        int numero_pagina = 1, num_elems_pag_actual, inicio_pagina, fim_pagina;
        int numero_resultados, total_paginas, escolha_pag, escolha_opcao_menu;

        List<Cliente> listaClientesSemCompras = moduloCompras.getClientesSemCompras();
        Paginador<List<Cliente>> paginador = new Paginador<>(listaClientesSemCompras, 10, 1);

        numero_resultados = listaClientesSemCompras.size();
        total_paginas = paginador.getNumPaginas();

        while (estadoMenu == QUERIE_04) {
            paginador.gotoPagina(numero_pagina);
            inicio_pagina = paginador.getPosInicialPagActual();
            num_elems_pag_actual = paginador.getNumElemsPagActual();
            fim_pagina = inicio_pagina + num_elems_pag_actual;
            System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
            System.out.print("================================================= \n");
            System.out.print("GESTHIPER >> QUERIE 4            \n");
            System.out.print("Clientes sem compras                 \n");
            System.out.print("================================================= \n");

            if (numero_resultados > 0) {
                System.out.printf("Pagina %2d/%d \n", numero_pagina, total_paginas);
                System.out.printf("--------------------\n");
                System.out.printf("|   #   |  Codigo  |\n");
                System.out.printf("--------------------\n");
                for (int i = 0; i < num_elems_pag_actual; i++) {
                    cliente = listaClientesSemCompras.get(inicio_pagina + i);
                    System.out.printf("| %5d | %8s |\n", inicio_pagina + i + 1, cliente.getCodigoCliente());
                }
                System.out.printf("--------------------\n");
            } else {
                System.out.print("Não há resultados a mostrar.\n");
            }

            System.out.print("==================================================== \n");
            System.out.print("0 - Sair | 1 - Menu Principal   \n");
            System.out.print("[<<] 4   [<] 5  ###  6 [>]   7 [>>]  |   2 - Pag...  \n");
            System.out.print("==================================================== \n");
            System.out.print("Insira nº da opcao > ");
            escolha_opcao_menu = input.nextInt();

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
                    estadoMenu = MENU_QUERIES;
            }
        }
        return estadoMenu;
    }

    /*
     Dado um mês válido, determinar o número total de compras e o total de clientes
     distintos que as realizaram;
     */
    public static MenuActual _05_comprasEClientesNumMes() {
        MenuActual estadoMenu = QUERIE_05;
        int escolha_mes, escolha_opcao;
        Mes mes_escolhido;
        Scanner input = new Scanner(System.in);

        while (estadoMenu == QUERIE_05) {
            System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
            System.out.print("================================================= \n");
            System.out.print("GESTHIPER >> QUERIE 5            \n");
            System.out.print("Nº de compras e Clientes num mês                \n");
            System.out.print("================================================= \n");
            System.out.print("Indique o nº do mês: ");
            escolha_mes = input.nextInt();

            System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
            System.out.print("================================================= \n");
            System.out.print("GESTHIPER >> QUERIE 5            \n");
            System.out.print("Nº de compras e Clientes num mês                \n");
            System.out.print("================================================= \n");

            if (escolha_mes > 0 && escolha_mes <= 12) {
                mes_escolhido = Mes.numero_to_mes(escolha_mes);

                System.out.print("Mês: " + mes_escolhido.getMes_extenso() + "\n");
                System.out.print("Nº clientes distintos: " + moduloCompras.getNumeroClientesDistintosMes(mes_escolhido) + "\n");
                System.out.print("Nº compras: [Contabilidade]\n");
            } else {
                System.out.print("Mês inserido é inválido\n");
            }

            System.out.print("================================================= \n");
            System.out.print(" 0 - Sair | 1 - Menu Principal | 2 - Procurar outro mês    \n");
            System.out.print("==================================================== \n");
            System.out.print("Escolha opção: ");
            escolha_opcao = input.nextInt();
            switch (escolha_opcao) {
                case 0:
                    estadoMenu = SAIR;
                    break;
                case 1:
                    estadoMenu = MENU_QUERIES;
                    break;
                case 2:
                    estadoMenu = QUERIE_05;
                    break;
                default:
                    estadoMenu = MENU_QUERIES;
                    break;
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
        MenuActual estadoMenu = QUERIE_06;
        String cliente_inserido;
        Mes mes;
        Cliente cliente;
        int escolha_opcao;
        int total_compras_mes, total_compras;
        double total_gasto_mes, total_gasto;
        Map<Mes, Integer> numeroProdsDistintosPorMes;
        Scanner input = new Scanner(System.in);

        while (estadoMenu == QUERIE_06) {
            total_compras = 0;
            total_gasto = 0;

            System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
            System.out.print("================================================= \n");
            System.out.print("GESTHIPER >> QUERIE 6            \n");
            System.out.print("Compras de cliente            \n");
            System.out.print("================================================= \n");
            System.out.print("Indique o cliente que quer procurar: ");
            cliente_inserido = input.next();

            System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
            System.out.print("================================================= \n");
            System.out.print("GESTHIPER >> QUERIE 6            \n");
            System.out.print("Compras de cliente            \n");
            System.out.print("================================================= \n");

            cliente = new Cliente(cliente_inserido);
            if (catalogoClientes.existeCliente(cliente)) {

                numeroProdsDistintosPorMes = moduloCompras.getNumeroProdutosDisntintosPorMesCliente(cliente);

                System.out.print("CLIENTE:" + cliente.getCodigoCliente() + "\n");
                System.out.print("---------------------------------------\n");
                System.out.print("|     | Número  | Produtos  |         |\n");
                System.out.print("| Mes | Compras | Distintos | € Gasto |\n");
                System.out.print("---------------------------------------\n");

                for (int i = 0; i < 12; i++) {
                    mes = Mes.numero_to_mes(i + 1);
                    total_compras_mes = moduloCompras.getNumComprasClienteMes(cliente, mes);
                    total_compras += total_compras_mes;

                    total_gasto_mes = moduloCompras.getDinheiroGastoClienteMes(cliente, mes);
                    total_gasto += total_gasto_mes;

                    System.out.printf("| %3s | %7d | %9d | %7.2f |\n", mes.getMes_abreviado(),
                            total_compras_mes,
                            numeroProdsDistintosPorMes.get(mes),
                            total_gasto_mes);
                }

                System.out.print("---------------------------------------\n");
                System.out.printf("| Tot | %7d | %9d | %7.2f |\n",
                        total_compras,
                        moduloCompras.getNumeroProdutosDistintosCliente(cliente),
                        total_gasto);
                System.out.print("---------------------------------------\n");

            } else {
                System.out.print("O cliente " + cliente_inserido + " não existe.\n");
            }

            System.out.print("============================================================ \n");
            System.out.print(" 0 - Sair | 1 - Menu Principal | 2 - Procurar outro cliente  \n");
            System.out.print("============================================================ \n");
            System.out.print("Escolha opção: ");
            escolha_opcao = input.nextInt();
            switch (escolha_opcao) {
                case 0:
                    estadoMenu = SAIR;
                    break;
                case 1:
                    estadoMenu = MENU_QUERIES;
                    break;
                case 2:
                    estadoMenu = QUERIE_06;
                    break;
                default:
                    estadoMenu = MENU_QUERIES;
                    break;
            }
        }

        return estadoMenu;
    }

    /*
     Dado o código de um produto existente, determinar, mês a mês, quantas vezes foi
     comprado, por quantos clientes diferentes e o total facturado;
     */
    public static MenuActual _07_comprasProdutoTodosMeses() {
        MenuActual estadoMenu = QUERIE_07;
        String produto_inserido;
        int total_compras_mes, total_compras;
        double total_gasto_mes, total_gasto;
        Mes mes;
        Produto produto;
        int escolha_opcao;
        Scanner input = new Scanner(System.in);

        while (estadoMenu == QUERIE_07) {
            total_compras = 0;
            total_gasto = 0;
            System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
            System.out.print("================================================= \n");
            System.out.print("GESTHIPER >> QUERIE 7            \n");
            System.out.print("Compras de produto            \n");
            System.out.print("================================================= \n");
            System.out.print("Indique o produto que quer procurar: ");
            produto_inserido = input.next();

            System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
            System.out.print("================================================= \n");
            System.out.print("GESTHIPER >> QUERIE 7            \n");
            System.out.print("Compras de produto            \n");
            System.out.print("================================================= \n");

            produto = new Produto(produto_inserido);
            if (catalogoProdutos.existeProduto(produto)) {

                System.out.print("PRODUTO:" + produto.getCodigoProduto() + "\n");
                System.out.print("-------------------------------------------\n");
                System.out.print("|     | Número  | Clientes  |             |\n");
                System.out.print("| Mes | Compras | Distintos | € Facturado |\n");
                System.out.print("-------------------------------------------\n");

                for (int i = 0; i < 12; i++) {
                    mes = Mes.numero_to_mes(i + 1);

                    System.out.printf("| %3s | %7d | %9d | %7.2f |\n", mes.getMes_abreviado(),
                            -1,
                            moduloCompras.getTotalClientesDistintosProdutoMes(produto, mes),
                            -1.0);
                }

                System.out.print("---------------------------------------\n");
                System.out.printf("| Tot | %7d | %9d | %7.2f |\n",
                        -1,
                        moduloCompras.getTotalClientesDistintosProduto(produto),
                        -1.0);
                System.out.print("---------------------------------------\n");

            } else {
                System.out.print("O produto " + produto_inserido + " não existe.\n");
            }

            System.out.print("============================================================ \n");
            System.out.print(" 0 - Sair | 1 - Menu Principal | 2 - Procurar outro produto  \n");
            System.out.print("============================================================ \n");
            System.out.print("Escolha opção: ");
            escolha_opcao = input.nextInt();
            switch (escolha_opcao) {
                case 0:
                    estadoMenu = SAIR;
                    break;
                case 1:
                    estadoMenu = MENU_QUERIES;
                    break;
                case 2:
                    estadoMenu = QUERIE_07;
                    break;
                default:
                    estadoMenu = MENU_QUERIES;
                    break;
            }
        }

        return estadoMenu;
    }

    /*
     Dado o código de um produto existente, determinar, mês a mês, quantas vezes foi
     comprado em modo N e em modo P e respectivas facturações;
     */
    public static MenuActual _08_comprasProdutoModoNeP() {
        System.out.print("Querie ainda nao implementada\n");
        return MENU_QUERIES;
    }

    /*
     Dado o código de um cliente determinar a lista de códigos de produtos que mais
     comprou (e quantos), ordenada por ordem decrescente de quantidade e, para
     quantidades iguais, por ordem alfabética dos códigos;
     */
    public static MenuActual _09_produtosMaisCompradosCliente() {
        MenuActual estadoMenu = QUERIE_09;
        Scanner input = new Scanner(System.in);
        Cliente cliente;
        String cliente_lido;
        ParProdutoQuantidadeComprada par;
        int numero_pagina = 1, num_elems_pag_actual, inicio_pagina, fim_pagina;
        int numero_resultados, total_paginas, escolha_pag, escolha_opcao_menu;
        
        System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
        System.out.print("================================================= \n");
        System.out.print("GESTHIPER >> QUERIE 9            \n");
        System.out.print("Produtos mais comprados cliente                 \n");
        System.out.print("================================================= \n");
        System.out.print("Indique o cliente que pretende procurar: ");
        cliente_lido = input.next();

        cliente = new Cliente(cliente_lido);

        while (estadoMenu == QUERIE_09) {
            System.out.print(ANSI_CLEARSCREEN + ANSI_HOME);
            System.out.print("================================================= \n");
            System.out.print("GESTHIPER >> QUERIE 9            \n");
            System.out.print("Produtos mais comprados cliente                 \n");
            System.out.print("================================================= \n");
            
            if (catalogoClientes.existeCliente(cliente)) {
                List<ParProdutoQuantidadeComprada> lista_pares = moduloCompras.getParesProdutoNumComprasCliente(cliente);
                Paginador<List<ParProdutoQuantidadeComprada>> paginador = new Paginador<>(lista_pares, 10, 1);

                numero_resultados = lista_pares.size();
                total_paginas = paginador.getNumPaginas();
                
                paginador.gotoPagina(numero_pagina);
                inicio_pagina = paginador.getPosInicialPagActual();
                num_elems_pag_actual = paginador.getNumElemsPagActual();
                fim_pagina = inicio_pagina + num_elems_pag_actual;

                if (numero_resultados > 0) {
                    System.out.printf("Pagina %2d/%d \n", numero_pagina, total_paginas);
                    System.out.printf("---------------------------------\n");
                    System.out.printf("|   #   |  Codigo  | Quantidade |\n");
                    System.out.printf("---------------------------------\n");
                    for (int i = 0; i < num_elems_pag_actual; i++) {
                        par = lista_pares.get(inicio_pagina + i);
                        System.out.printf("| %5d | %8s | %10d |\n", 
                                inicio_pagina + i + 1, par.getProduto().getCodigoProduto(), par.getQuantidadeComprada());
                    }
                    System.out.printf("---------------------------------\n");
                } else {
                    System.out.print("Não há resultados a mostrar.\n");
                }

                System.out.print("==================================================== \n");
                System.out.print("0 - Sair | 1 - Menu Principal  | 3 - Procurar outro cliente \n");
                System.out.print("[<<] 4   [<] 5  ###  6 [>]   7 [>>]  |   2 - Pag...  \n");
                System.out.print("==================================================== \n");
                System.out.print("Insira nº da opcao > ");
                escolha_opcao_menu = input.nextInt();

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
                        estadoMenu = QUERIE_09;
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
                        estadoMenu = MENU_QUERIES;
                }
            }else{
                System.out.print("O cliente " + cliente_lido + " não existe.\n");
                System.out.print("==================================================== \n");
                System.out.print("0 - Sair | 1 - Menu Principal  | 2 - Procurar outro cliente \n");
                System.out.print("==================================================== \n");
                System.out.print("Insira nº da opcao > ");
                escolha_opcao_menu = input.nextInt();

                switch (escolha_opcao_menu) {
                    case 0:
                        estadoMenu = SAIR;
                        break;
                    case 1:
                        estadoMenu = MENU_QUERIES;
                        break;
                    case 2:
                        estadoMenu = QUERIE_09;
                        break;
                    default:
                        estadoMenu = MENU_QUERIES;
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
        System.out.print("Querie ainda nao implementada\n");
        return MENU_QUERIES;
    }

    /*
     Determinar os X clientes que compraram um maior número de diferentes produtos,
     indicando quantos, sendo o critério de ordenação igual a 7;
     */
    public static MenuActual _11_clientesComMaisProdutosDiferentesComprados() {
        System.out.print("Querie ainda nao implementada\n");
        return MENU_QUERIES;
    }

    /*
     Dado o código de um produto, determinar o conjunto dos X clientes que mais o
     compraram e qual o valor gasto (ordenação cf. 7);
     */
    public static MenuActual _12_clientesQueMaisCompraramProduto() {
        System.out.print("Querie ainda nao implementada\n");
        return MENU_QUERIES;
    }

    /*
     A qualquer momento deverá estar disponível uma opção que permita ao utilizador
     gravar toda a estrutura de dados de forma persistente usando ObjectStreams, criando
     por omissão o ficheiro hipermercado.obj ou um outro se indicado pelo utilizador.
     */
    public static MenuActual _13_guardarEmFicheiroObjecto() {
        System.out.print("Querie ainda nao implementada\n");
        return MENU_QUERIES;
    }

    /*
     A qualquer momento também deverá o utilizador poder carregar os dados a partir de
     uma ObjectStream de nome dado, repopulando assim toda a informação da estrutura
     de dados até então existente em memória.
     */
    public static MenuActual _14_carregarFicheiroObjecto() {
        System.out.print("Querie ainda nao implementada\n");
        return MENU_QUERIES;
    }

    /*
     O programa deverá também poder ler a qualquer momento um outro qualquer ficheiro
     de texto contendo as informações referentes às compras registadas (cf. Compras1.txt,
     Compras3.txt).
     */
    public static MenuActual _15_mudarFicheiroCompras() {
        System.out.print("Querie ainda nao implementada\n");
        return MENU_QUERIES;
    }

}
