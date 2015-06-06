package lei.li3.g50.modulos.contabilidade;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import lei.li3.g50.modulos.dados.Compra;
import lei.li3.g50.modulos.dados.Produto;
import lei.li3.g50.utilitarios.Matriz_Double_12x2;
import lei.li3.g50.utilitarios.Matriz_Int_12x2;


public class Contabilidade {
    
    private Matriz_Int_12x2 totalComprasPorMes;
    private Matriz_Int_12x2 totalUnidadesVendidasPorMes;
    private Matriz_Double_12x2 totalFacturadoPorMes;
    TreeMap<Produto, FichaProdutoContabilidade> arvoreProdutos;
    
    
    public Contabilidade(){}
    
    public void registaProduto(Produto produto){}
    
    public void registaCompra(Compra compra){}
    
    
    public List<Produto> produtosMaisVendidos(int topN){
        ArrayList<Produto> lista_produtos = new ArrayList<>();
        lista_produtos.add(new Produto("FOO"));
        lista_produtos.add(new Produto("BAR"));
        return lista_produtos;
    }
}
