package lei.li3.g50.modulos.contabilidade;

import java.util.ArrayList;
import java.util.List;

public class ContabilidadeProdutoMes {

	List<ContabilidadeProduto> listaProdutos;

	public ContabilidadeProdutoMes() {
		super();
		this.listaProdutos = new ArrayList<ContabilidadeProduto>();
	}

	public ContabilidadeProdutoMes(List<ContabilidadeProduto> listaProdutos) {
		super();
		this.listaProdutos = new ArrayList<ContabilidadeProduto>();
		this.setListaProdutos(listaProdutos);
	}
	
	public ContabilidadeProdutoMes(ContabilidadeProdutoMes cproduto_mes) {
		super();
		this.listaProdutos = cproduto_mes.getListaProdutos();
	}

	/**
	 * @return the listaProdutos
	 */
	public List<ContabilidadeProduto> getListaProdutos() {
		ArrayList<ContabilidadeProduto> tmp = new ArrayList<ContabilidadeProduto>();

		for (ContabilidadeProduto contabilidadeProduto : this.listaProdutos) {
			tmp.add(contabilidadeProduto.clone());
		}

		return tmp;
	}

	/**
	 * @param listaProdutos
	 *            the listaProdutos to set
	 */
	public void setListaProdutos(List<ContabilidadeProduto> listaProdutos) {

		for (ContabilidadeProduto contabilidadeProduto : listaProdutos) {
			this.listaProdutos.add(contabilidadeProduto.clone());
		}

	}
	
	
	

	/**
	 * @return
	 */
	public int size() {
		return listaProdutos.size();
	}

	/**
	 * @return
	 */
	public boolean isEmpty() {
		return listaProdutos.isEmpty();
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.List#contains(java.lang.Object)
	 */
	public boolean contains(ContabilidadeProduto o) {
		return listaProdutos.contains(o);
	}

	/**
	 * @param e
	 * @return
	 
	 */
	public void add(ContabilidadeProduto e) {
		this.listaProdutos.add(e.clone());
	}

	/**
	 * @param o
	 * @return
	 */
	public void remove(ContabilidadeProduto o) {
		this.listaProdutos.remove(o);
	}

	
	/**
	 * 
	 */
	public void clear() {
		listaProdutos.clear();
	}

	/**
	 * @param index
	 * @return
	 */
	public ContabilidadeProduto get(int index) {
		return listaProdutos.get(index).clone();
	}

	/**
	 * @param index
	 * @param element
	 */
	public ContabilidadeProduto set(int index, ContabilidadeProduto element) {
		return listaProdutos.set(index, element.clone());
	}

	/**
	 * @param index
	 * @param element
	 */
	public void add(int index, ContabilidadeProduto element) {
		listaProdutos.add(index, element.clone());
	}

	/**
	 * @param index
	 * @return
	 * @see java.util.List#remove(int)
	 */
	public ContabilidadeProduto remove(int index) {
		return listaProdutos.remove(index);
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	public int indexOf(Object o) {
		return listaProdutos.indexOf(o);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((listaProdutos == null) ? 0 : listaProdutos.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		ContabilidadeProdutoMes other = (ContabilidadeProdutoMes) obj;
		if (listaProdutos == null) {
			if (other.getListaProdutos() != null) {
				return false;
			}
		} else if (!listaProdutos.equals(other.getListaProdutos())) {
			return false;
		}
		return true;
	}
	
	@Override
	public ContabilidadeProdutoMes clone()  {
		
		return new ContabilidadeProdutoMes(this);
	}
	
	
	

}
