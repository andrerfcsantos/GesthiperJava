package lei.li3.g50.modulos.dados.contabilidade;

import lei.li3.g50.dados.enumerados.TipoCompra;

public class ContabilidadeProduto {
	
	
	private String codProduto;
	private int quantidade;
	private double preco;
	private TipoCompra tipo;
	
	
	public ContabilidadeProduto() {
		super();
		this.codProduto = "";
		this.quantidade = 0;
		this.preco = 0.0;
		this.tipo = TipoCompra.NORMAL;
	}
	
	public ContabilidadeProduto(String codProduto, int quantidade, double preco,
			TipoCompra tipo) {
		super();
		this.codProduto = codProduto;
		this.quantidade = quantidade;
		this.preco = preco;
		this.tipo = tipo;
	}
	
	public ContabilidadeProduto(ContabilidadeProduto cproduto) {
		super();
		this.codProduto = cproduto.getCodProduto();
		this.quantidade = cproduto.getQuantidade();
		this.preco = cproduto.getPreco();
		this.tipo = cproduto.getTipo();
	}
	

	/**
	 * @return the codProduto
	 */
	public String getCodProduto() {
		return codProduto;
	}

	/**
	 * @param codProduto the codProduto to set
	 */
	public void setCodProduto(String codProduto) {
		this.codProduto = codProduto;
	}

	/**
	 * @return the quantidade
	 */
	public int getQuantidade() {
		return quantidade;
	}

	/**
	 * @param quantidade the quantidade to set
	 */
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * @return the preco
	 */
	public double getPreco() {
		return preco;
	}

	/**
	 * @param preco the preco to set
	 */
	public void setPreco(double preco) {
		this.preco = preco;
	}

	/**
	 * @return the tipo
	 */
	public TipoCompra getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(TipoCompra tipo) {
		this.tipo = tipo;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codProduto == null) ? 0 : codProduto.hashCode());
		long temp;
		temp = Double.doubleToLongBits(preco);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + quantidade;
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		ContabilidadeProduto other = (ContabilidadeProduto) obj;
		if (codProduto == null) {
			if (other.getCodProduto() != null) {
				return false;
			}
		} else if (!codProduto.equals(other.getCodProduto())) {
			return false;
		}
		if (Double.doubleToLongBits(preco) != Double
				.doubleToLongBits(other.getPreco())) {
			return false;
		}
		if (quantidade != other.getQuantidade()) {
			return false;
		}
		if (tipo != other.getTipo()) {
			return false;
		}
		return true;
	}
	
	
	@Override
	public ContabilidadeProduto clone(){
		
		return new ContabilidadeProduto(this);
	}


	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(" ºººººººººº Produto  ºººººººººººº \n");
		sb.append("Cód. Produto . :"+this.codProduto+"\n");
		sb.append("Quantidade ... :"+quantidade+"\n");
		sb.append("Preço ........ :"+preco+"\n");
		sb.append("Tipo de compra :"+tipo+"\n");
		
		return sb.toString();
	}
	
	
	
	
	
	
	
	

}
