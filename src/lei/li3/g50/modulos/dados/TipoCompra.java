package lei.li3.g50.modulos.dados;

public enum TipoCompra {
    NORMAL('N', "N", "Normal", "Norm."),
    PROMOCAO('P', "P", "Promoção", "Promo.");
    
    char letra_representativa;
    String representacao_ficheiro;
    String representacao_capitalizada;
    String abreviatura;
    
    private TipoCompra(char letra_representativa, 
                        String representacao_ficheiro, 
                        String representacao_capitalizada, 
                        String abreviatura) {
        this.letra_representativa = letra_representativa;
        this.representacao_ficheiro = representacao_ficheiro;
        this.representacao_capitalizada = representacao_capitalizada;
        this.abreviatura = abreviatura;
    }

    public static TipoCompra getNORMAL() {
        return NORMAL;
    }

    public static TipoCompra getPROMOCAO() {
        return PROMOCAO;
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
