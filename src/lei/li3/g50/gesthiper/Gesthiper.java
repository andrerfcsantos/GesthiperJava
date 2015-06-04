package lei.li3.g50.gesthiper;

import lei.li3.g50.modulos.dados.Cliente;

public class Gesthiper {

	public static Hipermercado hipermercado;

	public static void main(String[] args) {
		hipermercado = new Hipermercado();
		Leitura.mostra_ficheiros();
                Leitura.menuLeitura();
                testes_aos_modulos();
                Interface.menuQueries();
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
		System.out.print(""+hipermercado.getMod_compras().toString()+"\n");
                System.out.print(" Compras do cliente CW786: "+hipermercado.getMod_compras().getTotalComprasCliente(new Cliente("CW786"))+"\n");
	}
}
