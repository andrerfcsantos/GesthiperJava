package lei.li3.g50.modulos.contabilidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import lei.li3.g50.modulos.dados.Compra;
import lei.li3.g50.modulos.dados.Produto;
import lei.li3.g50.utilitarios.Matriz_Double_12x2;
import lei.li3.g50.utilitarios.Matriz_Int_12x2;

public class Contabilidade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6294510456940432396L;
	private Matriz_Int_12x2 totalComprasPorMes;
	private Matriz_Int_12x2 totalUnidadesVendidasPorMes;
	private Matriz_Double_12x2 totalFacturadoPorMes;
	TreeMap<Produto, FichaProdutoContabilidade> arvoreProdutos;

	public Contabilidade() {
		super();
		this.totalComprasPorMes = new Matriz_Int_12x2();
		this.totalUnidadesVendidasPorMes = new Matriz_Int_12x2();
		this.totalFacturadoPorMes = new Matriz_Double_12x2();
		this.arvoreProdutos = new TreeMap<>();
	}

	public Contabilidade(Matriz_Int_12x2 totalComprasPorMes,
			Matriz_Int_12x2 totalUnidadesVendidasPorMes,
			Matriz_Double_12x2 totalFacturadoPorMes,
			TreeMap<Produto, FichaProdutoContabilidade> arvoreProdutos) {
		super();
		this.totalComprasPorMes = totalComprasPorMes.clone();
		this.totalUnidadesVendidasPorMes = totalUnidadesVendidasPorMes.clone();
		this.totalFacturadoPorMes = totalFacturadoPorMes.clone();

		this.arvoreProdutos = new TreeMap<Produto, FichaProdutoContabilidade>();

		for (Map.Entry<Produto, FichaProdutoContabilidade> tuplo : arvoreProdutos
				.entrySet()) {

			this.arvoreProdutos.put(tuplo.getKey().clone(), tuplo.getValue()
					.clone());

		}

	}

	public Contabilidade(Contabilidade contabilidade) {
		super();
		this.totalComprasPorMes = contabilidade.getTotalComprasPorMes();
		this.totalUnidadesVendidasPorMes = contabilidade
				.getTotalUnidadesVendidasPorMes();
		this.totalFacturadoPorMes = contabilidade.getTotalFacturadoPorMes();
		this.arvoreProdutos = contabilidade.getArvoreProdutos();
	}

	public void registaProduto(Produto produto) {
	}

	public void registaCompra(Compra compra) {
	}

	public List<Produto> produtosMaisVendidos(int topN) {
		ArrayList<Produto> lista_produtos = new ArrayList<>();
		lista_produtos.add(new Produto("FOO"));
		lista_produtos.add(new Produto("BAR"));
		return lista_produtos;
	}

	public Matriz_Int_12x2 getTotalComprasPorMes() {
		return totalComprasPorMes.clone();
	}

	public Matriz_Int_12x2 getTotalUnidadesVendidasPorMes() {
		return totalUnidadesVendidasPorMes.clone();
	}

	public Matriz_Double_12x2 getTotalFacturadoPorMes() {
		return totalFacturadoPorMes.clone();
	}

	public void setTotalComprasPorMes(Matriz_Int_12x2 totalComprasPorMes) {
		this.totalComprasPorMes = totalComprasPorMes.clone();
	}

	public void setTotalUnidadesVendidasPorMes(
			Matriz_Int_12x2 totalUnidadesVendidasPorMes) {
		this.totalUnidadesVendidasPorMes = totalUnidadesVendidasPorMes.clone();
	}

	public void setTotalFacturadoPorMes(Matriz_Double_12x2 totalFacturadoPorMes) {
		this.totalFacturadoPorMes = totalFacturadoPorMes.clone();
	}

	public TreeMap<Produto, FichaProdutoContabilidade> getArvoreProdutos() {

		TreeMap<Produto, FichaProdutoContabilidade> tmp = new TreeMap<Produto, FichaProdutoContabilidade>();

		for (Map.Entry<Produto, FichaProdutoContabilidade> tuplo : this.arvoreProdutos
				.entrySet()) {

			tmp.put(tuplo.getKey().clone(), tuplo.getValue().clone());

		}

		return tmp;
	}

	public void setArvoreProdutos(
			TreeMap<Produto, FichaProdutoContabilidade> arvoreProdutos) {

		for (Map.Entry<Produto, FichaProdutoContabilidade> tuplo : arvoreProdutos
				.entrySet()) {

			this.arvoreProdutos.put(tuplo.getKey().clone(), tuplo.getValue()
					.clone());

		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((arvoreProdutos == null) ? 0 : arvoreProdutos.hashCode());
		result = prime
				* result
				+ ((totalComprasPorMes == null) ? 0 : totalComprasPorMes
						.hashCode());
		result = prime
				* result
				+ ((totalFacturadoPorMes == null) ? 0 : totalFacturadoPorMes
						.hashCode());
		result = prime
				* result
				+ ((totalUnidadesVendidasPorMes == null)
						? 0
						: totalUnidadesVendidasPorMes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contabilidade other = (Contabilidade) obj;
		if (arvoreProdutos == null) {
			if (other.getArvoreProdutos() != null)
				return false;
		} else if (!arvoreProdutos.equals(other.getArvoreProdutos()))
			return false;
		if (totalComprasPorMes == null) {
			if (other.getTotalComprasPorMes() != null)
				return false;
		} else if (!totalComprasPorMes.equals(other.getTotalComprasPorMes()))
			return false;
		if (totalFacturadoPorMes == null) {
			if (other.getTotalFacturadoPorMes() != null)
				return false;
		} else if (!totalFacturadoPorMes
				.equals(other.getTotalFacturadoPorMes()))
			return false;
		if (totalUnidadesVendidasPorMes == null) {
			if (other.getTotalUnidadesVendidasPorMes() != null)
				return false;
		} else if (!totalUnidadesVendidasPorMes.equals(other
				.getTotalUnidadesVendidasPorMes()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Contabilidade [totalComprasPorMes=" + totalComprasPorMes
				+ ", totalUnidadesVendidasPorMes="
				+ totalUnidadesVendidasPorMes + ", totalFacturadoPorMes="
				+ totalFacturadoPorMes + ", arvoreProdutos=" + arvoreProdutos
				+ "]");
		return sb.toString();
	}

}
