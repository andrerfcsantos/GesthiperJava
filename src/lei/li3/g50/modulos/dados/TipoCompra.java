package lei.li3.g50.modulos.dados;

import java.io.Serializable;

public enum TipoCompra implements Serializable {

    NORMAL(0, 'N', "N", "Normal", "Norm."),
    PROMOCAO(1, 'P', "P", "Promoção", "Promo."),
    AMBOS(2, 'A', "A", "Ambos", "Amb.");

    private int indice;
    private char letra_representativa;
    private String representacao_ficheiro;
    private String representacao_capitalizada;
    private String abreviatura;

    private TipoCompra(int indice,
            char letra_representativa,
            String representacao_ficheiro,
            String representacao_capitalizada,
            String abreviatura) {
        this.indice = indice;
        this.letra_representativa = letra_representativa;
        this.representacao_ficheiro = representacao_ficheiro;
        this.representacao_capitalizada = representacao_capitalizada;
        this.abreviatura = abreviatura;
    }

    public int getIndiceArray() {
        return indice;
    }

    public char getLetra_representativa() {
        return letra_representativa;
    }

    public String getRepresentacao_ficheiro() {
        return representacao_ficheiro;
    }

    public String getRepresentacao_capitalizada() {
        return representacao_capitalizada;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

}
