package lei.li3.g50.modulos.contabilidade;

import java.io.Serializable;

import lei.li3.g50.modulos.dados.Mes;
import lei.li3.g50.modulos.dados.TipoCompra;
import lei.li3.g50.utilitarios.Matriz_Double_12x2;
import lei.li3.g50.utilitarios.Matriz_Int_12x2;

public class FichaProdutoContabilidade implements Serializable {

	/**
	 * Classe Ficha de Produto da contabilidade
	 */
	private static final long serialVersionUID = -3086668118672328393L;
	private Matriz_Int_12x2 numUnidadesProdutoVendidasPorMes;
	private Matriz_Int_12x2 numComprasProdutoPorMes;
	private Matriz_Double_12x2 facturacaoProdutoPorMes;

	/**
	 * Construtor vazio
	 */
	public FichaProdutoContabilidade() {
		super();
		this.numUnidadesProdutoVendidasPorMes = new Matriz_Int_12x2();
		this.numComprasProdutoPorMes = new Matriz_Int_12x2();
		this.facturacaoProdutoPorMes = new Matriz_Double_12x2();
	}

	/**
	 * Construtor parametrizado
	 * @param numUnidadesProdutoVendidasPorMes
	 * @param numComprasProdutoPorMes
	 * @param facturacaoProdutoPorMes
	 */
	public FichaProdutoContabilidade(
			Matriz_Int_12x2 numUnidadesProdutoVendidasPorMes,
			Matriz_Int_12x2 numComprasProdutoPorMes,
			Matriz_Double_12x2 facturacaoProdutoPorMes) {
		super();
		this.numUnidadesProdutoVendidasPorMes = numUnidadesProdutoVendidasPorMes;
		this.numComprasProdutoPorMes = numComprasProdutoPorMes;
		this.facturacaoProdutoPorMes = facturacaoProdutoPorMes;
	}

	/**
	 * 
	 * Contrutor de cópia
	 * 
	 * @param a ficha de produto
	 */
	public FichaProdutoContabilidade(FichaProdutoContabilidade ficha) {
		super();
		this.numUnidadesProdutoVendidasPorMes = ficha
				.getMatrizNumUnidadesProdutoVendidasPorMes();
		this.numComprasProdutoPorMes = ficha.getMatrizNumComprasProdutoPorMes();
		this.facturacaoProdutoPorMes = ficha.getMatrizFacturacaoProdutoPorMes();
	}

	
	public Matriz_Int_12x2 getMatrizNumComprasProdutoPorMes() {
		return numComprasProdutoPorMes.clone();
	}

	
	public Matriz_Int_12x2 getMatrizNumUnidadesProdutoVendidasPorMes() {
		return numUnidadesProdutoVendidasPorMes.clone();
	}

	
	public Matriz_Double_12x2 getMatrizFacturacaoProdutoPorMes() {
		return facturacaoProdutoPorMes.clone();
	}

	
	public void setMatrizNumUnidadesProdutoVendidasPorMes(
			Matriz_Int_12x2 numUnidadesProdutoVendidasPorMes) {
		this.numUnidadesProdutoVendidasPorMes = numUnidadesProdutoVendidasPorMes
				.clone();
	}

	
	public void setMatrizNumComprasProdutoPorMes(
			Matriz_Int_12x2 numComprasProdutoPorMes) {
		this.numComprasProdutoPorMes = numComprasProdutoPorMes.clone();
	}

	
	public void setMatrizFacturacaoProdutoPorMes(
			Matriz_Double_12x2 facturacaoProdutoPorMes) {
		this.facturacaoProdutoPorMes = facturacaoProdutoPorMes.clone();
	}

	/**
	 * Método que retorna o total de unidades vendidas
	 * 
	 * @return total de unidades vendidas
	 */
	public int getNumUnidadesProdutoVendidas() {

		return numUnidadesProdutoVendidasPorMes.getSomaTotal();
	}

	/**
	 * Este método retorna o número de de unidades de produto dado um mês.
	 * 
	 * @see lei.li3.g50.modulos.dados.Mes
	 * 
	 * @param O mês a procurar
	 * @return o total de unidades vendidas no mês dado
	 */
	public int getNumUnidadesProdutoVendidasPorMes(Mes mes) {
		return numUnidadesProdutoVendidasPorMes.getValorMesTipoCompra(mes,
				TipoCompra.AMBOS);
	}

	/**
	 * Método que recebe um intervalo meses e o tipo de compra, e devolve o
	 * total de unidades de produto vendidas nesse intervalo
	 * @see lei.li3.g50.modulos.dados.Mes
	 * @see lei.li3.g50.modulos.dados.TipoCompra
	 * @param mes_inf
	 * @param mes_sup
	 * @param tipo_compra
	 * @return
	 */
	public int getNumUnidadesProdutoVendidasPorIntervaloDeMeses(Mes mes1,
			Mes mes2, TipoCompra tipo_compra) {
		return numUnidadesProdutoVendidasPorMes.getValorEntreMeses(mes1, mes2,
				tipo_compra);
	}

	// numComprasProdutoPorMes;

	/**
	 * Método de retorna o total comprado do produto 
	 * 
	 * @return total comprado do produto
	 */
	public int getNumComprasProduto() {
		return numComprasProdutoPorMes.getSomaTotal();
	}

	/**
	 * @param mes
	 * @param tipo_compra
	 * @return
	 */
	public int getNumComprasProdutoPorMes(Mes mes) {
		return numComprasProdutoPorMes.getValorMesTipoCompra(mes,
				TipoCompra.AMBOS);
	}

	/**
	 * Método que recebe um intervalo meses e o tipo de compra, e devolve o
	 * total de unidades de produto vendidas nesse intervalo
	 * @see lei.li3.g50.modulos.dados.Mes
	 * @see lei.li3.g50.modulos.dados.TipoCompra
	 * @param mes_inf
	 * @param mes_sup
	 * @param tipo_compra
	 * @return total de unidades de produto compradas nesse intervalo
	 */
	public int getNumComprasProdutoPorIntervaloDeMeses(Mes mes1, Mes mes2,
			TipoCompra tipo_compra) {
		return numComprasProdutoPorMes.getValorEntreMeses(mes1, mes2,
				tipo_compra);
	}

	/**
	 * 
	 * @return o total faturado do produto
	 */
	public double getFacturacaoProduto() {
		return facturacaoProdutoPorMes.getSomaTotal();
	}

	/**
	 * Este método retorna o total faturado de um produto dado um mês.
	 * 
	 * @see lei.li3.g50.modulos.dados.Mes
	 * 
	 * @param O mês a procurar
	 * @return o total faturado no mês dado
	 */
	public double getFacturacaoProdutoPorMes(Mes mes) {
		return facturacaoProdutoPorMes.getValorMesTipoCompra(mes,
				TipoCompra.AMBOS);
	}

	/**
	 * Método que recebe um intervalo meses e o tipo de compra, e devolve o
	 * total faturado nesse intervalo
	 * @see lei.li3.g50.modulos.dados.Mes
	 * @see lei.li3.g50.modulos.dados.TipoCompra
	 * @param mes_inf
	 * @param mes_sup
	 * @param tipo_compra
	 * @return total faturado nesse intervalo no intervalo dado
	 */
	public double getFacturacaoProdutoPorIntervaloDeMeses(Mes mes1, Mes mes2,
			TipoCompra tipo_compra) {
		return facturacaoProdutoPorMes.getValorEntreMeses(mes1, mes2,
				tipo_compra);
	}

	/**
	 * 
	 * @param mes
	 * @param tipo_compra
	 * @param valor
	 * 
	 */
	public void setNumUnidadesProdutoVendidasPorMesPorTipoCompra(Mes mes,
			TipoCompra tipo_compra, int valor) {
		this.numUnidadesProdutoVendidasPorMes.setValorMesTipoCompra(mes,
				tipo_compra, valor);
	}

	/**
	 * 
	 * @param mes
	 * @param tipo_compra
	 * @param valor
	 * 
	 */
	public void addNumUnidadesProdutoVendidasPorMesPorTipoCompra(Mes mes,
			TipoCompra tipo_compra, int valor) {
		this.numUnidadesProdutoVendidasPorMes.addValorMesTipoCompra(mes,
				tipo_compra, valor);
	}

	// numComprasProdutoPorMes;

	/**
	 * 
	 * @param mes
	 * @param tipo_compra
	 * @param valor
	 */
	public void setnumComprasProdutoPorMesPorTipoCompra(Mes mes,
			TipoCompra tipo_compra, int valor) {
		this.numComprasProdutoPorMes.setValorMesTipoCompra(mes, tipo_compra,
				valor);
	}

	/**
	 * 
	 * @param mes
	 * @param tipo_compra
	 * @param valor
	 */
	public void addnumComprasProdutoPorMesPorTipoCompra(Mes mes,
			TipoCompra tipo_compra, int valor) {
		this.numComprasProdutoPorMes.addValorMesTipoCompra(mes, tipo_compra,
				valor);
	}

	/**
	 * 
	 * @param mes
	 * @param tipo_compra
	 * @param valor
	 */
	public void setfacturacaoProdutoPorMesPorTipoCompra(Mes mes,
			TipoCompra tipo_compra, int valor) {
		this.facturacaoProdutoPorMes.setValorMesTipoCompra(mes, tipo_compra,
				valor);
	}

	/**
	 * 
	 * @param mes
	 * @param tipo_compra
	 * @param valor
	 */
	public void addfacturacaoProdutoPorMesPorTipoCompra(Mes mes,
			TipoCompra tipo_compra, int valor) {
		this.facturacaoProdutoPorMes.addValorMesTipoCompra(mes, tipo_compra,
				valor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((facturacaoProdutoPorMes == null) ? 0
						: facturacaoProdutoPorMes.hashCode());
		result = prime
				* result
				+ ((numComprasProdutoPorMes == null) ? 0
						: numComprasProdutoPorMes.hashCode());
		result = prime
				* result
				+ ((numUnidadesProdutoVendidasPorMes == null) ? 0
						: numUnidadesProdutoVendidasPorMes.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
		FichaProdutoContabilidade other = (FichaProdutoContabilidade) obj;
		if (facturacaoProdutoPorMes == null) {
			if (other.facturacaoProdutoPorMes != null) {
				return false;
			}
		} else if (!facturacaoProdutoPorMes.equals(other
				.getMatrizFacturacaoProdutoPorMes())) {
			return false;
		}
		if (numComprasProdutoPorMes == null) {
			if (other.numComprasProdutoPorMes != null) {
				return false;
			}
		} else if (!numComprasProdutoPorMes.equals(other
				.getMatrizNumComprasProdutoPorMes())) {
			return false;
		}
		if (numUnidadesProdutoVendidasPorMes == null) {
			if (other.getMatrizNumUnidadesProdutoVendidasPorMes() != null) {
				return false;
			}
		} else if (!numUnidadesProdutoVendidasPorMes.equals(other
				.getMatrizNumUnidadesProdutoVendidasPorMes())) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append("FichaProdutoContabilidade [numUnidadesProdutoVendidasPorMes="
				+ numUnidadesProdutoVendidasPorMes
				+ ", numComprasProdutoPorMes="
				+ numComprasProdutoPorMes
				+ ", facturacaoProdutoPorMes=" + facturacaoProdutoPorMes + "]");
		return sb.toString();
	}

	@Override
	public FichaProdutoContabilidade clone() {

		return new FichaProdutoContabilidade(this);
	}
}
