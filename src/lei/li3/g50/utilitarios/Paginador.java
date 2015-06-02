package lei.li3.g50.utilitarios;

import java.util.List;
import java.util.Objects;
import lei.li3.g50.excepcoes.PaginaImpossivelException;

public final class Paginador<E extends List<?>> {

    public static final int ELEMENTOS_POR_PAG_DEFAULT = 10;
    public static final int PAGINA_IMPOSSIVEL = -1;

    private E lista;
    private int num_pag;
    private int posicao_inicial;
    private int num_elems_pag;
    private int elems_por_pag;

    /*
     CONSTRUCTORES
     */
    private Paginador() {}

    public Paginador(List<?> lista) throws PaginaImpossivelException {
        if (lista == null) {
            throw new NullPointerException("Tentativa de inicializar paginador com array nulo.");
        }
        this.lista = (E) lista;
        this.elems_por_pag = ELEMENTOS_POR_PAG_DEFAULT;
        if (this.pagina_existe(1)) {
            this.goto_pagina(1);
        }
    }

    public Paginador(List<?> lista, int num_elemetos_por_pag, int pag) throws PaginaImpossivelException {
        if (lista == null) {
            throw new NullPointerException("Tentativa de inicializar paginador com array nulo.");
        }
        this.lista = (E) lista;
        this.elems_por_pag = (num_elemetos_por_pag > 0) ? num_elemetos_por_pag : ELEMENTOS_POR_PAG_DEFAULT;
        if (this.pagina_existe(pag)) {
            this.goto_pagina(pag);
        }
    }

    public Paginador(Paginador pag) {
        this.lista = (E) pag.getLista();
        this.num_pag = pag.getNumPag();
        this.posicao_inicial = pag.getPosInicial();
        this.num_elems_pag = pag.getNumElemsPag();
        this.elems_por_pag = pag.getElemsPorPag();
    }

    /*
     GETTERS
     */
    public static int getELEMENTOS_POR_PAG_DEFAULT() {
        return ELEMENTOS_POR_PAG_DEFAULT;
    }

    public static int getPAGINA_IMPOSSIVEL() {
        return PAGINA_IMPOSSIVEL;
    }

    public E getLista() {
        return lista;
    }

    public int getNumPag() {
        return num_pag;
    }

    public int getPosInicial() {
        return posicao_inicial;
    }

    public int getNumElemsPag() {
        return num_elems_pag;
    }

    public int getElemsPorPag() {
        return elems_por_pag;
    }

    /*
     SETTERS
     */
    public void setLista(E lista) {
        int nova_pagina = paginaDeIndice(this.posicao_inicial);
        this.lista = lista;
        try {
            this.goto_pagina(nova_pagina);
        } catch (PaginaImpossivelException ex) {
            
        }
    }

    public void setElemsPorPag(int elems_por_pag) {
        int nova_pagina;
        this.elems_por_pag = elems_por_pag;
        nova_pagina = paginaDeIndice(this.posicao_inicial);

        try {
            this.goto_pagina(nova_pagina);
        } catch (PaginaImpossivelException ex) {

        }
    }

    /*
     METODOS INSTANCIA
     */
    public void goto_pagina(int numero_pagina) throws PaginaImpossivelException {
        int comeco_pag = this.elems_por_pag * (numero_pagina - 1);
        int diferenca = this.lista.size() - comeco_pag;

        if (diferenca > 0 && numero_pagina > 0) {
            this.posicao_inicial = comeco_pag;
            this.num_elems_pag = (diferenca > this.elems_por_pag) ? this.elems_por_pag : diferenca;
            this.num_pag = numero_pagina;
        } else {
            throw new PaginaImpossivelException();
        }
    }

    public boolean pagina_existe() {
        return pagina_existe(this.num_pag);
    }

    public boolean pagina_existe(int num_pag) {
        int comeco_pag = this.elems_por_pag * (num_pag - 1);
        int diferenca = this.lista.size() - comeco_pag;

        return diferenca > 0 && num_pag > 0;
    }

    private int paginaDeIndice(int indice) {
        double pag = indice / this.elems_por_pag;
        return (int) Math.floor(pag) + 1;
    }
    
    
    /*
    METODOS STANDARD
    */

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.lista);
        hash = 37 * hash + this.num_pag;
        hash = 37 * hash + this.posicao_inicial;
        hash = 37 * hash + this.num_elems_pag;
        hash = 37 * hash + this.elems_por_pag;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Paginador<?> other = (Paginador<?>) obj;
        return this.lista == other.getLista()
                && this.elems_por_pag == other.getElemsPorPag()
                && this.num_elems_pag == other.getNumElemsPag()
                && this.num_pag == other.getNumPag()
                && this.posicao_inicial == other.getPosInicial();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Paginador{" + "tamanho lista=").append(lista.size()).append(", num_pag=").append(num_pag).append(", posicao_inicial=").append(posicao_inicial).append(", num_elems_pag=").append(num_elems_pag).append(", elems_por_pag=").append(elems_por_pag).append('}');
        return sb.toString();
    }
    
    @Override
    public Paginador clone(){
        return new Paginador(this);
    }
    
    
}
