
public class Main {

	public static void main(String[] args) {
		Matriz_Int_12x2 mat = new Matriz_Int_12x2();
		
		for (int i = 0; i < Matriz_Int_12x2.MAX_ROW; i++) {
			for (int j = 0; j < Matriz_Int_12x2.MAX_COL; j++) {
				mat.setIntAt(i, j, 1);
				
			}
			
		}
		
		System.out.println(mat.toString());

	}

}
