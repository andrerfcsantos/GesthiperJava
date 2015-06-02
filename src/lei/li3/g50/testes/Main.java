package lei.li3.g50.testes;

import java.util.ArrayList;
import lei.li3.g50.excepcoes.PaginaImpossivelException;
import lei.li3.g50.utilitarios.Matriz_Int_12x2;
import lei.li3.g50.utilitarios.Paginador;

public class Main {

    public static void main(String[] args) {
        teste_paginador();
    }

    public static void teste_matrizes() {
        Matriz_Int_12x2 mat = new Matriz_Int_12x2();

        for (int i = 0; i < Matriz_Int_12x2.MAX_ROW; i++) {
            for (int j = 0; j < Matriz_Int_12x2.MAX_COL; j++) {
                mat.setIntAt(i, j, 1);

            }

        }

        System.out.println(mat.toString());
    }

    public static void teste_paginador() {
        /* Cria Array a ser paginado. */
        ArrayList<String> nomes = new ArrayList<>();
        /*Fazendo exemplo com 2 elementos po pagina tem-se:*/
        /*Pagina 1*/
        nomes.add("aaa");
        nomes.add("bbb");
        /*Pagina 2*/
        nomes.add("ccc");
        nomes.add("ddd");
        /*Pagina 3*/
        nomes.add("eee");

        try {
            /*Cria paginador sobre array de nomes, com 2 elementos por página,
            a começar na pagina 1*/
            Paginador<ArrayList<String>> pag = new Paginador(nomes, 2, 1);
            /*Pede para ir para a pagina 3*/
            pag.goto_pagina(3);
            /*Pede a posicao do array que corresponde ao inicio da pagina actual 
            (neste caso, pagina 3)*/
            int pos_inicial = pag.getPosInicial();
            
            /*Mostra todos os elementos da pagina.*/
            for (int i = 0; i < pag.getNumElemsPag(); i++) {
                System.out.print((pos_inicial + i) + ": " + nomes.get(pos_inicial + i)+ "\n");
            }
            System.out.print(pag.toString());
        } catch (PaginaImpossivelException ex) {
            System.out.print("Pag Impossivel\n");
        }
        
    }
}
