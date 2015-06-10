package lei.li3.g50.gesthiper;

import lei.li3.g50.excepcoes.ClienteNaoExisteException;
import lei.li3.g50.modulos.dados.Cliente;

public class TestesTreeHashMap {

	public static Hipermercado hipermercado;

	public static void main(String[] args) {
                System.out.printf("TreeHashMaps Test\n");
		hipermercado = new Hipermercado();
                MenuLeitura.menuLeitura();
	}
        

	public static Hipermercado getHipermercado() {
		return hipermercado;
	}

	public static void setHipermercado(Hipermercado novo_hiper) {
		hipermercado = novo_hiper;
	}

	public static void testes_aos_modulos() {
		System.out.print(""+hipermercado.getCatalogoClientes().toString()+"\n");
		System.out.print(""+hipermercado.getCatalogoProdutos().toString()+"\n");
		System.out.print(""+hipermercado.getContabilidade().toString()+"\n");
		System.out.print(""+hipermercado.getCompras().toString()+"\n");
            try {
                System.out.print(" Compras do cliente CW786: "+hipermercado.getCompras().getTotalComprasCliente(new Cliente("CW786"))+"\n");
            } catch (ClienteNaoExisteException ex) {
                System.out.print("O cliente nao existe\n");
            }
	}
}
