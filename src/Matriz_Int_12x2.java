import java.util.Arrays;
import lei.li3.g50.excepcoes.MatrizNao12x2;

public class Matriz_Int_12x2 {

    public static int MAX_ROW = 12;
    public static int MAX_COL = 2;

    private int[][] matriz = new int[MAX_ROW][MAX_COL];

    /*
     CONSTRUCTORES
     */
    public Matriz_Int_12x2() {
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                this.matriz[i][j] = 0;
            }
        }
    }

    public Matriz_Int_12x2(int[][] matriz) throws MatrizNao12x2 {
        super();

        /*
         NOTA: Como tinhas, this.matriz[i][j] = this.getMatriz() não faz o que queres.
         Provavelmente querias ter dito this.setMatriz(matriz), mas isso é unsafe,
         uma vez que o método pode ser reescrito por herança. O melhor mesmo é repetir codigo aqui,
         para garantir que o codigo não é alterado externamente.
         */
        if (matriz[0].length != 2 || matriz.length != 12) {
            throw new MatrizNao12x2("A matriz não tem o tamanho certo (12x2)");
        } else {
            for (int i = 0; i < MAX_ROW; i++) {
                for (int j = 0; j < MAX_COL; j++) {
                    this.matriz[i][j] = matriz[i][j];
                }
            }
        }
    }

    public Matriz_Int_12x2(Matriz_Int_12x2 matriz) {
        super();
        this.matriz = matriz.getMatriz();
    }

    /*
     GETS
     */
    public int[][] getMatriz() {
        int[][] tmp = new int[MAX_ROW][MAX_COL];

        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                tmp[i][j] = matriz[i][j];
            }
        }
        return tmp;
    }

    public int getIntAt(int row, int col) {
        return this.matriz[row][col];
    }

    /*
     SETS
     */
    public void setMatriz(int[][] matriz) {
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                this.matriz[i][j] = matriz[i][j];
            }
        }
    }

    public void setIntAt(int row, int col, int value) {
        this.matriz[row][col] = value;
    }

    /*
     METODOS STANDARD
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(matriz);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Matriz_Int_12x2 other = (Matriz_Int_12x2) obj;
        return Arrays.deepEquals(matriz, other.getMatriz());
    }

    @Override
    public Matriz_Int_12x2 clone() {
        return new Matriz_Int_12x2(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        sb.append("{ ");
        sb.append("{ ");
        sb2.append("  { ");
        for (int i = 0; i < MAX_ROW - 1; i++) {
            sb.append(matriz[i][0]).append(", ");
            sb2.append(matriz[i][1]).append(", ");
        }

        sb.append(matriz[MAX_ROW - 1][0]).append(" ");
        sb2.append(matriz[MAX_ROW - 1][1]).append(" ");

        sb2.append("}");

        sb.append("},\n");
        sb.append(sb2.toString());
        sb.append(" }\n");
        return sb.toString();
    }

}
