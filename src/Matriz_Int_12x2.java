import java.util.Arrays;

public class Matriz_Int_12x2 {

	public static int MAX_ROW = 12;
	public static int MAX_COL = 2;

	int[][] matriz = new int[MAX_ROW][MAX_COL];

	public Matriz_Int_12x2() {
		for (int i = 0; i < MAX_ROW; i++) {
			for (int j = 0; j < MAX_COL; j++) {
				this.matriz[i][j] = 0;

			}

		}
	}

	public Matriz_Int_12x2(int[][] matriz) {
		super();
		this.matriz = this.getMatriz();
	}

	public Matriz_Int_12x2(Matriz_Int_12x2 matriz) {
		super();
		this.matriz = matriz.getMatriz();
	}

	/**
	 * @return the matriz
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

	/**
	 * @param matriz
	 *            the matriz to set
	 */
	public void setMatriz(int[][] matriz) {
		for (int i = 0; i < MAX_ROW; i++) {
			for (int j = 0; j < MAX_COL; j++) {
				this.matriz[i][j] = matriz[i][j];

			}

		}

	}

	public int getIntAt(int row, int col) {

		return this.matriz[row][col];

	}

	public void setIntAt(int row, int col, int value) {

		this.matriz[row][col] = value;

	}

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
		if (!Arrays.deepEquals(matriz, other.getMatriz())) {
			return false;
		}
		return true;
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

			sb.append(matriz[i][0] + ", ");
			sb2.append(matriz[i][1] + ", ");
		}

		sb.append(matriz[MAX_ROW - 1][0] + " ");
		sb2.append(matriz[MAX_ROW - 1][1] + " ");

		sb2.append("}");

		sb.append("},\n");
		sb.append(sb2.toString());
		sb.append(" }\n");
		return sb.toString();
	}

}
