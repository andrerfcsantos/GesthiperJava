package gesthiper;

import dados.Cliente;
import dados.Compra;
import dados.Mes;
import dados.Produto;
import dados.TipoCompra;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public final class Leitura {
    
    private Leitura(){}
    
    private static final String fich_produtos_default[] = {
        "datasets/FichProdutos.txt",
        "FichProdutos.txt",
        "../datasets/FichProdutos.txt"
    };
    private static final String fich_clientes_default[] = {
        "datasets/FichClientes.txt",
        "FichClientes.txt",
        "../datasets/FichClientes.txt"
    };
    private static final String fich_compras_default[] = {
        "datasets/Compras.txt",
        "Compras.txt",
        "../datasets/Compras.txt"
    };
    
    public static void le_ficheiros(String fich_produtos, String fich_clientes, String fich_compras){
        File ficheiro_produtos, ficheiro_clientes, ficheiro_compras;
        
        ficheiro_produtos = new File(fich_produtos);
        if(!ficheiro_produtos.exists()) ficheiro_produtos = procura_ficheiro_produtos();
        le_ficheiro_produtos(ficheiro_produtos);
        
        ficheiro_clientes = new File(fich_clientes);
        if(!ficheiro_clientes.exists()) ficheiro_clientes = procura_ficheiro_clientes();
        le_ficheiro_clientes(ficheiro_clientes);
        
        ficheiro_compras = new File(fich_compras);
        if(!ficheiro_compras.exists()) ficheiro_compras = procura_ficheiro_compras();
        le_ficheiro_compras(ficheiro_compras);
    }
    
    public static void le_ficheiros(){
        File ficheiro_produtos, ficheiro_clientes, ficheiro_compras;
        
        ficheiro_produtos = procura_ficheiro_produtos();
        le_ficheiro_produtos(ficheiro_produtos);
        
        ficheiro_clientes = procura_ficheiro_clientes();
        le_ficheiro_clientes(ficheiro_clientes);
        
        ficheiro_compras = procura_ficheiro_compras();
        le_ficheiro_compras(ficheiro_compras);
    }
    
    public static void le_ficheiro_produtos(File ficheiro){
        try {
            int i=1;
            String linha;
            StringTokenizer st;
            ArrayList<Produto> produtos = new ArrayList<>();
            BufferedReader bin = new BufferedReader(new FileReader(ficheiro));
            
            while(bin.ready()){
               linha = bin.readLine();
               st = new StringTokenizer(linha, " \r\n");
               produtos.add(new Produto(st.nextToken()));
            }
            
            bin.close();
            
            for(Produto prod : produtos){
                System.out.print("Produto "+ (i++) + ": " +prod.getCodigoProduto()+"\n");
            }
            
        } catch (FileNotFoundException ex) {
            System.out.print("Ficheiro não encontrado.\n");
        } catch (IOException ex) {
            System.out.print("Erro ao ler ficheiro.\n");
        }
    }
    
    public static void le_ficheiro_clientes(File ficheiro) {
        try {
            int i = 1;
            String linha;
            StringTokenizer st;
            ArrayList<Cliente> clientes = new ArrayList<>();
            BufferedReader bin = new BufferedReader(new FileReader(ficheiro));

            while (bin.ready()) {
                linha = bin.readLine();
                st = new StringTokenizer(linha, " \r\n");
                clientes.add(new Cliente(st.nextToken()));
            }

            bin.close();

            for (Cliente cli : clientes) {
                System.out.print("Cliente " + (i++) + ": " + cli.getCodigoCliente() + "\n");
            }

        } catch (FileNotFoundException ex) {
            System.out.print("Ficheiro não encontrado.\n");
        } catch (IOException ex) {
            System.out.print("Erro ao ler ficheiro.\n");
        }
    }
    
    public static void le_ficheiro_compras(File ficheiro) {
        try {
            int i = 1;
            String linha;
            StringTokenizer st;
            ArrayList<Compra> compras = new ArrayList<>();
            Compra compra;
            BufferedReader bin = new BufferedReader(new FileReader(ficheiro));

            while (bin.ready()) {
                compra = new Compra();
                /*
                 WJ3256 4.72 2 N AF651 10
                 */
                linha = bin.readLine();
                st = new StringTokenizer(linha, " \r\n");
                compra.setProduto(new Produto(st.nextToken()));
                compra.setPreco(Double.parseDouble(st.nextToken()));
                compra.setQuantidade(Integer.parseInt(st.nextToken()));
                compra.setTipoCompra(st.nextToken().compareTo("N")==0 ? TipoCompra.NORMAL : TipoCompra.PROMOCAO);
                compra.setCliente(new Cliente(st.nextToken()));
                compra.setMes(Mes.numero_to_mes(Integer.parseInt(st.nextToken())));
                
                compras.add(compra);
            }

            bin.close();

            for (Compra c : compras) {
                System.out.print("" + (i++) + " => "+ c.toString()+"\n");
            }

        } catch (FileNotFoundException ex) {
            System.out.print("Ficheiro não encontrado.\n");
        } catch (IOException ex) {
            System.out.print("Erro ao ler ficheiro.\n");
        }
    }
    
    
    public static void teste_file() {
        File fich_prod=null;
        int i = 0;

        for (i = 0; i < fich_produtos_default.length; i++) {
            fich_prod = new File(fich_produtos_default[i]);
            System.out.print(fich_produtos_default[i] + " existe? " + fich_prod.exists() + "\n");
        }
    }
    
    
    public static File procura_ficheiro_produtos() {
        File fich_prod=null;
        boolean encontrado = false;
        for (int i = 0; i < fich_produtos_default.length && !encontrado; i++) {
            fich_prod = new File(fich_produtos_default[i]);
            if(fich_prod.exists()) encontrado = true;
        }
        
        return encontrado ? fich_prod : null;
    }
    
    public static File procura_ficheiro_clientes() {
        File fich_prod=null;
        boolean encontrado = false;
        for (int i = 0; i < fich_clientes_default.length && !encontrado; i++) {
            fich_prod = new File(fich_clientes_default[i]);
            if(fich_prod.exists()) encontrado = true;
        }
        
        return encontrado ? fich_prod : null;
    }
    
    public static File procura_ficheiro_compras() {
        File fich_prod=null;
        boolean encontrado = false;
        
        for (int i = 0; i < fich_compras_default.length && !encontrado; i++) {
            fich_prod = new File(fich_compras_default[i]);
            if(fich_prod.exists()) encontrado = true;
        }
        
        return encontrado ? fich_prod : null;
    }
}
