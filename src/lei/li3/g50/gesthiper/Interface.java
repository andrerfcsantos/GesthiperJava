package lei.li3.g50.gesthiper;

import java.util.Scanner;
import static lei.li3.g50.gesthiper.Interface.MenuActual.*;

public final class Interface {

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

        while (estadoMenu == MENU_QUERIES) {
            System.out.print("-----------------------------\n");
            System.out.print("GESTHIPER >> Menu Queries\n");
            System.out.print("-----------------------------\n");
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
            System.out.print("-----------------------------\n");
            System.out.print("0 - Sair \n");
            System.out.print("-----------------------------\n");
            System.out.print("Escolha o nº opção: ");
            escolha = input.nextInt();

            switch (escolha) {
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
                    estadoMenu = SAIR;
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
        System.out.print("Querie ainda nao implementada\n");
        return MENU_QUERIES;
    }

    /*
     Dado um mês válido, determinar o número total de compras e o total de clientes
     distintos que as realizaram;
     */
    public static MenuActual _05_comprasEClientesNumMes() {
        System.out.print("Querie ainda nao implementada\n");
        return MENU_QUERIES;
    }

    /*
     Dado um código de cliente, determinar, para cada mês, quantas compras fez,
     quantos produtos distintos comprou e quanto gastou. Apresentar também o total
     anual facturado ao cliente;
     */
    public static MenuActual _06_comprasClienteMeses() {
        System.out.print("Querie ainda nao implementada\n");
        return MENU_QUERIES;
    }

    /*
     Dado o código de um produto existente, determinar, mês a mês, quantas vezes foi
     comprado, por quantos clientes diferentes e o total facturado;
     */
    public static MenuActual _07_comprasProdutoTodosMeses() {
        System.out.print("Querie ainda nao implementada\n");
        return MENU_QUERIES;
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
        System.out.print("Querie ainda nao implementada\n");
        return MENU_QUERIES;
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
