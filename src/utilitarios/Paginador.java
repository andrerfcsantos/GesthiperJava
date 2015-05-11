package utilitarios;

import java.util.List;

public class Paginador<E extends List<?>> {
    public static final int ELEMENTOS_POR_PAG_DEFAULT = 10;
    public static final int PAGINA_IMPOSSIVEL = -1;
            
    private E lista;
    private int num_pag;
    private int posicao_inicial;
    private int num_elems_pag;
    private int elems_por_pag;
    
    
    private Paginador(){}
    
    public Paginador(List<?> lista){
        if(lista == null) throw new NullPointerException("Tentativa de inicializar paginador com array nulo.");
        this.lista=(E) lista;
        this.elems_por_pag = ELEMENTOS_POR_PAG_DEFAULT;
        this.goto_pagina(1);
    }
    
    public final void goto_pagina(int numero_pagina) {
        int comeco_pag = this.elems_por_pag * (numero_pagina - 1);
        int diferenca = this.lista.size() - comeco_pag;

        if (diferenca > 0 && numero_pagina > 0) {
            this.posicao_inicial = comeco_pag;
            this.num_elems_pag = (diferenca > this.elems_por_pag) ? this.elems_por_pag : diferenca;
            this.num_pag = numero_pagina;
        } else {
            this.posicao_inicial = PAGINA_IMPOSSIVEL;
            this.num_elems_pag = PAGINA_IMPOSSIVEL;
            this.num_pag = PAGINA_IMPOSSIVEL;
        }
    }
}
