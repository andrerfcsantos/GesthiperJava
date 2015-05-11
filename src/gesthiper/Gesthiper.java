package gesthiper;

public class Gesthiper {

    public static void main(String[] args) {
        GestorModulos.inicializaModulos();
        Leitura.le_ficheiros();
        testes_aos_modulos();
    }

    public static void testes_aos_modulos() {
        System.out.print("Numero de produtos registados: " + GestorModulos.mod_cat_produtos.getNumeroProdutosTotal()+ "\n");
        
        for(char letra = 'A';letra <= 'Z';letra++){
            System.out.print("" + letra + " " +GestorModulos.mod_cat_produtos.getNumeroProdutosLetra(letra)+"\n");
        }
    }
}
