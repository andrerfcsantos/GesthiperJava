package gesthiper;

import java.util.Scanner;

public final class Interface {
    
    private Interface(){};
    
    public static void leitura(){
        Scanner input = new Scanner(System.in);
        int escolha;
        
        System.out.print("=================================");
        System.out.print(" GESTHIPER >> Leitura Ficheiros   ");
        System.out.print("=================================");
        System.out.print("   1) Ler ficheiro objecto       ");
        System.out.print("   2) Ler ficheiros genericos    ");
        System.out.print("   3) Procura automatica ficheiro objecto ");
        System.out.print("   4) Procura automatica ficheiro generico ");
        System.out.print("=================================");
        System.out.print("Escolha uma opção: ");
        escolha = input.nextInt();
        
        switch(escolha){
            case 1 : opcao_le_ficheiro_objecto(); break;
            case 2 : opcao_le_ficheiros_genericos(); break;
            case 3 : Leitura.le_ficheiro_objecto();break;
            case 4 : Leitura.le_ficheiros(); break;
            default: break;
        }
        
    }
    
    public static void opcao_le_ficheiro_objecto() {
        String nome_ficheiro;
        Scanner input = new Scanner(System.in);
        System.out.print("Indique o nome do ficheiro objecto que quer ler (incluindo extensao):");
        nome_ficheiro = input.nextLine();

        Leitura.le_ficheiro_objecto(nome_ficheiro);
    }
    
    
    public static void opcao_le_ficheiros_genericos(){
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
        
        Leitura.le_ficheiros(nome_ficheiro_produtos, nome_ficheiro_clientes, nome_ficheiro_compras);
    }
    
}
