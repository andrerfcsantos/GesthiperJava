package lei.li3.g50.utilitarios;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import lei.li3.g50.excepcoes.PaginaImpossivelException;

public final class Paginador<E> implements Serializable {


	private static final long serialVersionUID = -5051604213886807288L;
	public static final int ELEMENTOS_POR_PAG_DEFAULT = 10;
    public static final int PAGINA_IMPOSSIVEL = -1;

    private List<E> lista;
    private int numPaginaActual;
    private int posInicialPagActual;
    private int numElemsPagActual;
    private int numElemsPorPag;

    /*
     CONSTRUCTORES
     */
    private Paginador() {
    }

    public Paginador(List<E> lista) {
        if (lista == null) {
            throw new NullPointerException("Tentativa de inicializar paginador com array nulo.");
        }
        this.lista = lista;
        this.numElemsPorPag = ELEMENTOS_POR_PAG_DEFAULT;
        if (this.paginaExiste(1)) {
            this.gotoPagina(1);
        }else{
            throw new PaginaImpossivelException();
        }
    }

    public Paginador(List<E> lista, int num_elemetos_por_pag, int pag) {
        if (lista == null) {
            throw new NullPointerException("Tentativa de inicializar paginador com array nulo.");
        }
        this.lista = lista;
        this.numElemsPorPag = (num_elemetos_por_pag > 0) ? num_elemetos_por_pag : ELEMENTOS_POR_PAG_DEFAULT;
        if (this.paginaExiste(pag)) {
            this.gotoPagina(pag);
        }else{
            throw new PaginaImpossivelException();
        }
    }

    public Paginador(Paginador pag) {
        this.lista = pag.getLista();
        this.numPaginaActual = pag.getNumPaginaActual();
        this.posInicialPagActual = pag.getPosInicialPagActual();
        this.numElemsPagActual = pag.getNumElemsPagActual();
        this.numElemsPorPag = pag.getNumElemsPorPag();
    }

    /*
     GETTERS
     */

    public List<E> getLista() {
        return lista;
    }

    public int getNumPaginaActual() {
        return numPaginaActual;
    }

    public int getPosInicialPagActual() {
        return posInicialPagActual;
    }

    public int getNumElemsPagActual() {
        return numElemsPagActual;
    }
    
    public int getNumElems(){
        return lista.size();
    }

    public int getNumElemsPorPag() {
        return numElemsPorPag;
    }
    
    public int getNumPaginas() {
        int num_elems = this.lista.size();
        int elems_por_pag = this.numElemsPorPag;
        return ((num_elems % elems_por_pag) == 0) ? num_elems / elems_por_pag : (num_elems / elems_por_pag) + 1;
    }

    /*
     SETTERS
     */
    public void setLista(List<E> lista) {
        this.lista = lista;
        if (this.paginaExiste(this.numPaginaActual)) {
            this.gotoPagina(this.numPaginaActual);
        }
    }

    public void setNumElemsPorPag(int elems_por_pag) {
        int nova_pagina;
        this.numElemsPorPag = elems_por_pag;
        nova_pagina = paginaDoIndice(this.posInicialPagActual);
        if (this.paginaExiste(nova_pagina)) {
            this.gotoPagina(nova_pagina);
        }
    }

    /*
     METODOS INSTANCIA
     */
    public void gotoPagina(int numero_pagina) {
        int comeco_pag = this.numElemsPorPag * (numero_pagina - 1);
        int diferenca = this.lista.size() - comeco_pag;

        if (diferenca > 0 && numero_pagina > 0) {
            this.posInicialPagActual = comeco_pag;
            this.numElemsPagActual = (diferenca > this.numElemsPorPag) ? this.numElemsPorPag : diferenca;
            this.numPaginaActual = numero_pagina;
        }
    }

    public boolean paginaExiste() {
        return paginaExiste(this.numPaginaActual);
    }

    public boolean paginaExiste(int num_pag) {
        int comeco_pag = this.numElemsPorPag * (num_pag - 1);
        int diferenca = this.lista.size() - comeco_pag;

        return diferenca > 0 && num_pag > 0;
    }

    private int paginaDoIndice(int indice) {
        double pag = indice / this.numElemsPorPag;
        return (int) Math.floor(pag) + 1;
    }

    /*
     METODOS STANDARD
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.lista);
        hash = 37 * hash + this.numPaginaActual;
        hash = 37 * hash + this.posInicialPagActual;
        hash = 37 * hash + this.numElemsPagActual;
        hash = 37 * hash + this.numElemsPorPag;
        return hash;
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
        final Paginador<?> other = (Paginador<?>) obj;
        return this.lista == other.getLista()
                && this.numElemsPorPag == other.getNumElemsPorPag()
                && this.numElemsPagActual == other.getNumElemsPagActual()
                && this.numPaginaActual == other.getNumPaginaActual()
                && this.posInicialPagActual == other.getPosInicialPagActual();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Paginador{" + "tamanho lista=").append(lista.size()).append(", num_pag=").append(numPaginaActual).append(", posicao_inicial=").append(posInicialPagActual).append(", num_elems_pag=").append(numElemsPagActual).append(", elems_por_pag=").append(numElemsPorPag).append('}');
        return sb.toString();
    }

    @Override
    public Paginador clone() {
        return new Paginador(this);
    }

}
