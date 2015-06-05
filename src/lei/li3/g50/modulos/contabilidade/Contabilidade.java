package lei.li3.g50.modulos.contabilidade;

import java.util.TreeMap;
import lei.li3.g50.modulos.dados.Produto;
import lei.li3.g50.utilitarios.Matriz_Double_12x2;
import lei.li3.g50.utilitarios.Matriz_Int_12x2;


public class Contabilidade {
    
    private Matriz_Int_12x2 totalComprasPorMes;
    private Matriz_Int_12x2 totalUnidadesVendidasPorMes;
    private Matriz_Double_12x2 totalFacturadoPorMes;
    TreeMap<Produto, FichaProdutoContabilidade> arvoreProdutos;
    
    
    public Contabilidade(){}
    
    

}
