package lei.li3.g50.modulos.dados;

import java.io.Serializable;


public class Compra implements Serializable {
	public static final int COMPRA_INVALIDA = -1;

	private Cliente cliente;
	private Produto produto;
	private double preco;
	private int quantidade;
	private Mes mes;
	private TipoCompra tipo_compra;

	public Compra() {
		this.cliente = null;
		this.produto = null;
		this.preco = COMPRA_INVALIDA;
		this.quantidade = COMPRA_INVALIDA;
		this.mes = null;
		this.tipo_compra = null;
	}

	public Compra(Cliente cliente, Produto produto, double preco,
			int quantidade, Mes mes, TipoCompra tipo_compra) {
		this.cliente = cliente;
		this.produto = produto;
		this.preco = preco;
		this.quantidade = quantidade;
		this.mes = mes;
		this.tipo_compra = tipo_compra;
	}

	public Compra(Compra compra) {
		this.cliente = compra.getCliente();
		this.produto = compra.getProduto();
		this.preco = compra.getPreco();
		this.quantidade = compra.getQuantidade();
		this.mes = compra.getMes();
		this.tipo_compra = compra.getTipoCompra();
	}

	/*
	 * GETTERS e SETTERS
	 */
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Mes getMes() {
		return mes;
	}

	public void setMes(Mes mes) {
		this.mes = mes;
	}

	public TipoCompra getTipoCompra() {
		return tipo_compra;
	}

	public void setTipoCompra(TipoCompra tipo_compra) {
		this.tipo_compra = tipo_compra;
	}

	/*
	 * METODOS COMPRA
	 */

	public boolean equals(Compra compra) {
		return this.cliente.equals(compra.getCliente())
				&& this.produto.equals(compra.getProduto())
				&& (this.preco == compra.getPreco())
				&& (this.quantidade == compra.getQuantidade())
				&& (this.mes == compra.getMes())
				&& (this.tipo_compra == compra.getTipoCompra());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Compra compra = (Compra) obj;
		return this.equals(compra);
	}

	@Override
	public Compra clone() {
		return new Compra(this);
	}

	@Override
	public String toString() {
		StringBuilder st = new StringBuilder();
		st.append("Compra{");
		st.append("cliente=").append(cliente);
		st.append(", produto=").append(produto);
		st.append(", preco=").append(preco);
		st.append(", quantidade=").append(quantidade);
		st.append(", mes=").append(mes);
		st.append(", tipo_compra=").append(tipo_compra);
		st.append('}');
		return st.toString();
	}

	boolean is_compra_valida() {
		return this.cliente != null && this.produto != null
				&& this.preco != COMPRA_INVALIDA && this.preco >= 0
				&& this.quantidade != COMPRA_INVALIDA && this.quantidade >= 0
				&& this.mes != null && this.tipo_compra != null;
	}
}
