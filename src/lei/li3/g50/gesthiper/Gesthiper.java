package lei.li3.g50.gesthiper;

public class Gesthiper {

	public static Hipermercado hipermercado;

	public static void main(String[] args) {
		hipermercado = new Hipermercado();
		Leitura.mostra_ficheiros();
	}

	public static Hipermercado getHipermercado() {
		return hipermercado;
	}

	public static void setHipermercado(Hipermercado novo_hiper) {
		hipermercado = novo_hiper;
	}

	public static void testes_aos_modulos() {
		System.out.print("Numero de produtos registados: "
				+ hipermercado.getMod_cat_produtos().getNumeroProdutosTotal()
				+ "\n");

		for (char letra = 'A'; letra <= 'Z'; letra++) {
			System.out.print(""
					+ letra
					+ " "
					+ hipermercado.getMod_cat_produtos()
							.getNumeroProdutosLetra(letra) + "\n");
		}

	}
}
