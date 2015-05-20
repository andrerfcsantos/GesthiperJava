package modulos;

import java.util.ArrayList;
import java.util.TreeSet;

import utilitarios.ComparatorClientePorCodigo;
import dados.Cliente;

public class CatalogoClientes {

	private ArrayList<TreeSet<Cliente>> catalogo;

	public CatalogoClientes() {
		TreeSet<Cliente> arvore;
		catalogo = new ArrayList<>(27);

		for (int i = 0; i < 27; i++) {
			arvore = new TreeSet<>(new ComparatorClientePorCodigo());
			catalogo.add(i, arvore);
		}

	}

	public void insere_produto(Cliente cliente) {
		int indice = calcula_indice(cliente.getCodigoCliente().charAt(0));
		catalogo.get(indice).add(cliente.clone());
	}

	public void remove_produto(Cliente cliente) {
		int indice = calcula_indice(cliente.getCodigoCliente().charAt(0));
		catalogo.get(indice).remove(cliente);
	}

	public boolean existeProduto(Cliente cliente) {
		int indice = calcula_indice(cliente.getCodigoCliente().charAt(0));
		return catalogo.get(indice).contains(cliente);
	}

	public int getNumeroProdutosTotal() {
		int total = 0;

		for (TreeSet<Cliente> arvore_letra : catalogo) {
			total += arvore_letra.size();	
		}

		return total;
	}

	public int getNumeroProdutosLetra(char l) {
		int indice = calcula_indice(l);
		return catalogo.get(indice).size();
	}

	private int calcula_indice(char l) {
		char letra = Character.toUpperCase(l);
		int res = Character.isAlphabetic(letra) ? letra - 'A' : 26;
		return res;
	}

}
