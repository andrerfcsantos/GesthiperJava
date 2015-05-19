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
    
    public Paginador(List<?> lista) {
        int tamanho;
        if (lista == null) {
            throw new NullPointerException("Tentativa de inicializar paginador com array nulo.");
        }
        tamanho =   this.lista.size();
        this.lista = (E) lista;
        this.elems_por_pag = ELEMENTOS_POR_PAG_DEFAULT;
        this.posicao_inicial = (tamanho > 0 ) ? 0 :PAGINA_IMPOSSIVEL;
        this.num_elems_pag = (tamanho > this.elems_por_pag) ? this.elems_por_pag : tamanho;
        this.num_pag = 1;
    }
    
    public Paginador(List<?> lista, int num_elemetos_por_pag, int pag){
        
    }
    
    public void goto_pagina(int numero_pagina) {
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
    
    public boolean pagina_existe(){
        return pagina_existe(this.num_pag);
    }
    
    public boolean pagina_existe(int num_pag){
        int comeco_pag = this.elems_por_pag * (num_pag - 1);
        int diferenca = this.lista.size() - comeco_pag;
        
        return diferenca > 0 && num_pag > 0;
    }
}
