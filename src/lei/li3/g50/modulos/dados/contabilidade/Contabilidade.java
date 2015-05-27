package lei.li3.g50.modulos.dados.contabilidade;

import java.util.HashMap;
import java.util.Map;

import lei.li3.g50.dados.enumerados.Mes;

public class Contabilidade {

	Map<Mes, ContabilidadeProdutoMes> contabilidade;

	public Contabilidade() {
		super();
		this.contabilidade = new HashMap<Mes, ContabilidadeProdutoMes>();
	}

	public Contabilidade(Map<Mes, ContabilidadeProdutoMes> contabilidade) {
		super();
		this.contabilidade = new HashMap<Mes, ContabilidadeProdutoMes>();
		this.setContabilidade(contabilidade);
	}

	public Contabilidade(Contabilidade contabilidade) {
		super();
		this.contabilidade = contabilidade.getContabilidade();
	}

	/**
	 * @return the contabilidade
	 */
	public Map<Mes, ContabilidadeProdutoMes> getContabilidade() {
		HashMap<Mes, ContabilidadeProdutoMes> tmp = new HashMap<Mes, ContabilidadeProdutoMes>();

		for (Map.Entry<Mes, ContabilidadeProdutoMes> entry : this.contabilidade
				.entrySet()) {
			tmp.put(entry.getKey(), entry.getValue().clone());

		}

		return tmp;
	}

	/**
	 * @param contabilidade
	 *            the contabilidade to set
	 */
	public void setContabilidade(Map<Mes, ContabilidadeProdutoMes> contabilidade) {
		for (Map.Entry<Mes, ContabilidadeProdutoMes> entry : contabilidade
				.entrySet()) {
			this.contabilidade.put(entry.getKey(), entry.getValue().clone());

		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((contabilidade == null) ? 0 : contabilidade.hashCode());
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
		Contabilidade other = (Contabilidade) obj;
		if (contabilidade == null) {
			if (other.getContabilidade() != null) {
				return false;
			}
		} else if (!contabilidade.equals(other.getContabilidade())) {
			return false;
		}
		return true;
	}

	public Contabilidade clone() {

		return new Contabilidade(this);

	}

}
