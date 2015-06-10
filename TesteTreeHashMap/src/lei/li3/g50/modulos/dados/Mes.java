package lei.li3.g50.modulos.dados;

import java.io.Serializable;

public enum Mes implements Serializable {

    JANEIRO(1, "Jan", "Janeiro"),
    FEVEREIRO(2, "Fev", "Fevereiro"),
    MARCO(3, "Mar", "Março"),
    ABRIL(4, "Abr", "Abril"),
    MAIO(5, "Mai", "Maio"),
    JUNHO(6, "Jun", "Junho"),
    JULHO(7, "Jul", "Julho"),
    AGOSTO(8, "Ago", "Agosto"),
    SETEMBRO(9, "Set", "Setembro"),
    OUTUBRO(10, "Out", "Outubro"),
    NOVEMBRO(11, "Nov", "Novembro"),
    DEZEMBRO(12, "Dez", "Dezembro");

    private int num_mes;
    private String mes_abreviado;
    private String mes_extenso;

    private Mes(int num_mes, String mes_abreviado, String mes_extenso) {
        this.num_mes = num_mes;
        this.mes_abreviado = mes_abreviado;
        this.mes_extenso = mes_extenso;
    }

    public static Mes getJANEIRO() {
        return JANEIRO;
    }

    public static Mes getFEVEREIRO() {
        return FEVEREIRO;
    }

    public static Mes getMARCO() {
        return MARCO;
    }

    public static Mes getABRIL() {
        return ABRIL;
    }

    public static Mes getMAIO() {
        return MAIO;
    }

    public static Mes getJUNHO() {
        return JUNHO;
    }

    public static Mes getJULHO() {
        return JULHO;
    }

    public static Mes getAGOSTO() {
        return AGOSTO;
    }

    public static Mes getSETEMBRO() {
        return SETEMBRO;
    }

    public static Mes getOUTUBRO() {
        return OUTUBRO;
    }

    public static Mes getNOVEMBRO() {
        return NOVEMBRO;
    }

    public static Mes getDEZEMBRO() {
        return DEZEMBRO;
    }

    public int getNum_mes() {
        return num_mes;
    }

    public String getMes_abreviado() {
        return mes_abreviado;
    }

    public String getMes_extenso() {
        return mes_extenso;
    }
    
    public int getIndiceArray(){
        return this.num_mes-1;
    }
    
    public static Mes numero_to_mes(int n_mes) {
        Mes resultado;
        switch (n_mes) {
            case 1:
                resultado = JANEIRO;
                break;
            case 2:
                resultado = FEVEREIRO;
                break;
            case 3:
                resultado = MARCO;
                break;
            case 4:
                resultado = ABRIL;
                break;
            case 5:
                resultado = MAIO;
                break;
            case 6:
                resultado = JUNHO;
                break;
            case 7:
                resultado = JULHO;
                break;
            case 8:
                resultado = AGOSTO;
                break;
            case 9:
                resultado = SETEMBRO;
                break;
            case 10:
                resultado = OUTUBRO;
                break;
            case 11:
                resultado = NOVEMBRO;
                break;
            case 12:
                resultado = DEZEMBRO;
                break;
            default:
                resultado = JANEIRO;
        }
        return resultado;
    }

}
