package lei.li3.g50.gesthiper;

import lei.li3.g50.modulos.dados.Hipermercado;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.StringTokenizer;
import static lei.li3.g50.gesthiper.MenuLeitura.compraValida;
import lei.li3.g50.modulos.dados.Cliente;
import lei.li3.g50.modulos.dados.Compra;
import lei.li3.g50.modulos.dados.Mes;
import lei.li3.g50.modulos.dados.Produto;
import lei.li3.g50.modulos.dados.TipoCompra;

public class LeituraFicheiros {
    
    public static void le_ficheiro_produtos(String str_ficheiro_produtos)
            throws FileNotFoundException, IOException {
    	//FIXME var não usada
        int i = 1;
        String linha;
        Hipermercado hiper = Gesthiper.getHipermercado();
        StringTokenizer st;
        BufferedReader bin;
        File ficheiro = new File(str_ficheiro_produtos);

        if (ficheiro.exists()) {
            hiper.setFicheiro_produtos(ficheiro);
        }

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
    	//FIXME var não usada
        int i = 1;
        String linha;
        Hipermercado hiper = Gesthiper.getHipermercado();
        StringTokenizer st;
        BufferedReader bin;
        File ficheiro = new File(str_ficheiro_clientes);

        if (ficheiro.exists()) {
            hiper.setFicheiro_clientes(ficheiro);
        }

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
        Hipermercado hiper = Gesthiper.getHipermercado();
        String linha;
        StringTokenizer st;
        Compra compra;
        BufferedReader bin;
        File ficheiro = new File(str_ficheiro_compras);

        if (ficheiro.exists()) {
            hiper.setFicheiro_compras(ficheiro);
        }

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
                hiper.addCompraInvalida(linha);
            }

        }
        bin.close();
    }
    
        public static void le_ficheiro_compras_cc(String str_ficheiro_compras)
            throws FileNotFoundException, IOException {
        Hipermercado hiper = Gesthiper.getHipermercado();
        String linha;
        String token;
        StringTokenizer st;
        Compra compra;
        BufferedReader bin;
        Produto produto;
        double preco;
        int quantidade;
        TipoCompra tipo_compra;
        Cliente cliente;
        Mes mes;
        File ficheiro = new File(str_ficheiro_compras);

        if (ficheiro.exists()) {
            hiper.setFicheiro_compras(ficheiro);
        }

        bin = new BufferedReader(new FileReader(ficheiro));

        while (bin.ready()) {
            compra = new Compra();
            /*
             * Exemplo de linha: WJ3256 4.72 2 N AF651 10
             */
            linha = bin.readLine();
            st = new StringTokenizer(linha, " \r\n");
            produto = new Produto(st.nextToken());
            preco = Double.parseDouble(st.nextToken());
            quantidade = Integer.parseInt(st.nextToken());
            
            token = st.nextToken();
            if(token.compareTo("N") ==0) tipo_compra = TipoCompra.NORMAL;
            else tipo_compra = TipoCompra.PROMOCAO;
            
            cliente = new Cliente(st.nextToken());
            mes = Mes.numero_to_mes(Integer.parseInt(st.nextToken()));
            
            compra = new Compra(cliente, produto, preco, quantidade, mes, tipo_compra);
            
            if (compraValida(compra)) {
                hiper.regista_compra(compra);
            } else {
                hiper.addCompraInvalida(linha);
            }

        }
        bin.close();
    }


    public static void le_ficheiro_objecto(String str_ficheiro_objecto) throws IOException, ClassNotFoundException {
        ObjectInputStream fich_obj = new ObjectInputStream(new FileInputStream(str_ficheiro_objecto));
        Gesthiper.setHipermercado((Hipermercado) fich_obj.readObject());
        fich_obj.close();
    }

    public static void guarda_ficheiro_objecto(String ficheiro) throws FileNotFoundException, IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ficheiro));
        out.writeObject(Gesthiper.getHipermercado());
        out.flush();
        out.close();
    }
}
