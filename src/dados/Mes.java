package dados;

public enum Mes {
    JANEIRO     (1, "Jan"),
    FEVEREIRO   (2, "Fev"),
    MARCO       (3, "Mar"),
    ABRIL       (4, "Abr"),
    MAIO        (5, "Mai"),
    JUNHO       (6, "Jun"),
    JULHO       (7, "Jul"),
    AGOSTO      (8, "Ago"),
    SETEMBRO    (9, "Set"),
    OUTUBRO     (10, "Out"),
    NOVEMBRO    (11, "Nov"),
    DEZEMBRO    (12, "Dez");
    
    private int num_mes;
    private String mes_abreviado;
    
    public static Mes numero_to_mes(int n_mes){
        Mes resultado;
        switch(n_mes){
            case 1: resultado = JANEIRO; break;
            case 2: resultado = FEVEREIRO; break;
            case 3: resultado = MARCO; break;
            case 4: resultado = ABRIL; break;
            case 5: resultado = MAIO; break;
            case 6: resultado = JUNHO; break;
            case 7: resultado = JULHO; break;
            case 8: resultado = AGOSTO; break;
            case 9: resultado = SETEMBRO; break;
            case 10: resultado = OUTUBRO; break;
            case 11: resultado = NOVEMBRO; break;
            case 12: resultado = DEZEMBRO; break;
            default: resultado = JANEIRO;
        }
        return resultado;
    }
    
    private Mes(int n_mes, String abreviatura){
        num_mes = n_mes;
        mes_abreviado = abreviatura;
    }
    
    public int getNumMes(){
        return this.num_mes;
    }
    
    public String getAbreviatura(){
        return this.mes_abreviado;
    }
    
}
