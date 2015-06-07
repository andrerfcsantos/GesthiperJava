package lei.li3.g50.modulos.contabilidade;

import java.io.Serializable;
import lei.li3.g50.utilitarios.Matriz_Double_12x2;
import lei.li3.g50.utilitarios.Matriz_Int_12x2;

public class FichaProdutoContabilidade implements Serializable{

    private Matriz_Int_12x2 numUnidadesProdutoVendidasPorMes;
    private Matriz_Int_12x2 numComprasProdutoPorMes;
    private Matriz_Double_12x2 facturacaoProdutoPorMes;
    
    public FichaProdutoContabilidade(){}
    
    
    
}
