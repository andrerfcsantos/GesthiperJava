package lei.li3.g50.gesthiper;

public final class Interface {

    private Interface() {
    }
    
    public static void menuQueries(){
        
        System.out.print("-----------------------------\n");
        System.out.print("------ Menu queries ---------\n");
        System.out.print("-----------------------------\n");
        System.out.print(" 1) Estatísticas último ficheiro\n");
        System.out.print(" 2) Dados gerais\n");
        System.out.print(" 3) Produtos não comprados\n");
        System.out.print(" 4) Clientes que não compraram\n");
        System.out.print(" 5) Nº compras / clientes num mês\n");
        System.out.print(" 6) Compras cliente num mês\n");
        System.out.print(" 7) Compras / clientes /facturacao produto\n");
        System.out.print(" 8) Compras produto N e P\n");
        System.out.print(" 9) Produtos mais comprados cliente\n");
        System.out.print(" 10) Produtos mais vendidos\n");
        System.out.print(" 11) Clientes com mais produtos diferentes comprados\n");
        System.out.print(" 12) Clientes que mais compraram produto\n");
        System.out.print("-----------------------------\n");
        System.out.print("0 - Sair \n");
        System.out.print("-----------------------------\n");
        System.out.print("Escolha o nº opção: \n");
        
        
    }
    
    /*
     Apresenta ao utilizador os dados referentes ao último ficheiro de compras lido,
     designadamente, nome dos ficheiros, número total de produtos, total de diferentes
     produtos comprados, total de não comprados, número total de clientes e total dos
     que realizaram compras, total de clientes que nada compraram, total de compras de
     valor total igual a 0 e facturação total.
     */
    
    public static void _01_estatisticasUltimoFicheiro(){
        
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
    public static void _02_dadosGerais(){
        
    }
    
    /*
    Lista ordenada com os códigos dos produtos nunca comprados e respectivo total;
    */
    public static void _03_produtosNuncaComprados(){
        
    }
    
    /*
    Lista ordenada com os códigos dos clientes que nunca compraram e seu total;
    */
    public static void _04_clientesQueNuncaCompraram(){
        
    }
    
    /*
     Dado um mês válido, determinar o número total de compras e o total de clientes
     distintos que as realizaram;
     */
    public static void _05_comprasEClientesNumMes(){
        
    }
    
    /*
     Dado um código de cliente, determinar, para cada mês, quantas compras fez,
     quantos produtos distintos comprou e quanto gastou. Apresentar também o total
     anual facturado ao cliente;
     */
    public static void _06_comprasClienteNumMes(){
        
    }
    
    /*
    Dado o código de um produto existente, determinar, mês a mês, quantas vezes foi
comprado, por quantos clientes diferentes e o total facturado;
    */
    public static void _07_comprasProdutoTodosMeses(){
        
    }
    
    /*
    Dado o código de um produto existente, determinar, mês a mês, quantas vezes foi
comprado em modo N e em modo P e respectivas facturações;
    */
    public static void _08_comprasProdutoModoNeP(){
        
    }
    
    /*
    Dado o código de um cliente determinar a lista de códigos de produtos que mais
comprou (e quantos), ordenada por ordem decrescente de quantidade e, para
quantidades iguais, por ordem alfabética dos códigos;
    */
    public static void _09_produtosMaisCompradosCliente(){
        
    }
    
    /*
    Determinar o conjunto dos X produtos mais vendidos em todo o ano (em número de
unidades vendidas) indicando o número total de distintos clientes que o
compraram (X é um inteiro dado pelo utilizador);
    */
    public static void _10_produtosMaisVendidos(){
        
    }
    
    /*
    Determinar os X clientes que compraram um maior número de diferentes produtos,
indicando quantos, sendo o critério de ordenação igual a 7;
    */
    public static void _11_clientesComMaisProdutosDiferentesComprados(){
        
    }
    
    /*
    Dado o código de um produto, determinar o conjunto dos X clientes que mais o
compraram e qual o valor gasto (ordenação cf. 7);
    */
    public static void _12_clientesQueMaisCompraramProduto(){
        
    }

    
    
    
    
}
