package lei.li3.g50.gesthiper;

public class Gesthiper {

	public static Hipermercado hipermercado;

	public static void main(String[] args) {
		hipermercado = new Hipermercado();
		Leitura.mostra_ficheiros();
                Leitura.menuLeitura();
                testes_aos_modulos();
	}
        

	public static Hipermercado getHipermercado() {
		return hipermercado;
	}

	public static void setHipermercado(Hipermercado novo_hiper) {
		hipermercado = novo_hiper;
	}

	public static void testes_aos_modulos() {
		System.out.print(""+hipermercado.getMod_cat_clientes().toString()+"\n");
		System.out.print(""+hipermercado.getMod_cat_produtos().toString()+"\n");
	}
}
