package lei.li3.g50.modulos.contabilidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import lei.li3.g50.excepcoes.ProdutoNaoExisteException;
import lei.li3.g50.modulos.dados.Compra;
import lei.li3.g50.modulos.dados.Mes;
import lei.li3.g50.modulos.dados.Produto;
import lei.li3.g50.modulos.dados.TipoCompra;
import lei.li3.g50.utilitarios.ComparatorParProdutoQuantidadeComprada;
import lei.li3.g50.utilitarios.Matriz_Double_12x2;
import lei.li3.g50.utilitarios.Matriz_Int_12x2;
import lei.li3.g50.utilitarios.ParProdutoQuantidadeComprada;

public class Contabilidade implements Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = -6294510456940432396L;
  private static final int MAX_MONTH= 12;
  private Matriz_Int_12x2 totalComprasPorMes;
  private Matriz_Int_12x2 totalUnidadesVendidasPorMes;
  private Matriz_Double_12x2 totalFacturadoPorMes;
  private int numeroProdutosDistintosPorMes[];
  TreeMap<Produto, FichaProdutoContabilidade> arvoreProdutos;


  /**
   * 
   */
  public Contabilidade() {
    super();
    this.totalComprasPorMes = new Matriz_Int_12x2();
    this.totalUnidadesVendidasPorMes = new Matriz_Int_12x2();
    this.totalFacturadoPorMes = new Matriz_Double_12x2();
    this.arvoreProdutos = new TreeMap<>();
    this.numeroProdutosDistintosPorMes = new int[12];

  }

  /**
   * 
   * @param totalComprasPorMes
   * @param totalUnidadesVendidasPorMes
   * @param totalFacturadoPorMes
   * @param arvoreProdutos
   */
  public Contabilidade(Matriz_Int_12x2 totalComprasPorMes,
      Matriz_Int_12x2 totalUnidadesVendidasPorMes, Matriz_Double_12x2 totalFacturadoPorMes,
      TreeMap<Produto, FichaProdutoContabilidade> arvoreProdutos,
      int numeroClientesDistintosPorMes[], int numeroProdutosDistintosPorMes[]) {
    super();
    this.totalComprasPorMes = totalComprasPorMes.clone();
    this.totalUnidadesVendidasPorMes = totalUnidadesVendidasPorMes.clone();
    this.totalFacturadoPorMes = totalFacturadoPorMes.clone();
    for (int i = 0; i < MAX_MONTH; i++) {
      this.numeroProdutosDistintosPorMes[i] = numeroProdutosDistintosPorMes[i];
    }

    this.arvoreProdutos = new TreeMap<Produto, FichaProdutoContabilidade>();

    for (Map.Entry<Produto, FichaProdutoContabilidade> tuplo : arvoreProdutos.entrySet()) {

      this.arvoreProdutos.put(tuplo.getKey().clone(), tuplo.getValue().clone());

    }

  }

  /**
   * 
   * @param contabilidade
   */
  public Contabilidade(Contabilidade contabilidade) {
    super();
    this.totalComprasPorMes = contabilidade.getTotalComprasPorMes();
    this.totalUnidadesVendidasPorMes = contabilidade.getTotalUnidadesVendidasPorMes();
    this.totalFacturadoPorMes = contabilidade.getTotalFacturadoPorMes();
    this.arvoreProdutos = contabilidade.getArvoreProdutos();
    this.numeroProdutosDistintosPorMes = contabilidade.getNumeroProdutosDistintosPorMes();
  }

  /**
   * 
   * @param produto
   */
  public void registaProduto(Produto produto) {

    this.arvoreProdutos.put(produto.clone(), new FichaProdutoContabilidade());

  }



  public void registaCompra(Compra compra) {

    Mes mes = compra.getMes();
    Produto produto = compra.getProduto();
    TipoCompra tipo_compra = compra.getTipoCompra();
    int qt = compra.getQuantidade();
    double preco = compra.getPreco();
    FichaProdutoContabilidade ficha_produto;

    try {
      ficha_produto = getFichaProdutoNoClone(produto);
    } catch (ProdutoNaoExisteException e) {
      ficha_produto = new FichaProdutoContabilidade();
      this.arvoreProdutos.put(produto.clone(), ficha_produto);
    }



    if (ficha_produto.getNumComprasProdutoPorMesPorTipoCompra(mes, tipo_compra) == 0) {
      this.totalComprasPorMes.addValorMesTipoCompra(mes, tipo_compra, 1);
      ficha_produto.addNumComprasProdutoPorMesPorTipoCompra(mes, tipo_compra, 1);

    }

    if (ficha_produto.getNumUnidadesProdutoVendidasPorMesPorTipoCompra(mes, tipo_compra) == 0) {
      this.totalUnidadesVendidasPorMes.addValorMesTipoCompra(mes, tipo_compra, qt);
      ficha_produto.addNumUnidadesProdutoVendidasPorMesPorTipoCompra(mes, tipo_compra, qt);

    }

    if (ficha_produto.getFacturacaoProdutoPorMesPorTipoCompra(mes, tipo_compra) == 0) {
      this.totalFacturadoPorMes.addValorMesTipoCompra(mes, tipo_compra, preco * (qt + 0.0));
      ficha_produto.addFacturacaoProdutoPorMesPorTipoCompra(mes, tipo_compra, preco * (qt + 0.0));

    }

   
    if (this.getTotalComprasPorMes(mes) == 0) {
      numeroProdutosDistintosPorMes[mes.getIndiceArray()]++;

    }

  }

  public List<Produto> produtosMaisVendidos(int topN) {
    ArrayList<Produto> lista_produtos = new ArrayList<>();
    lista_produtos.add(new Produto("FOO"));
    lista_produtos.add(new Produto("BAR"));
    return lista_produtos;
  }

  /*
   * GETTERS
   */

  public Matriz_Int_12x2 getTotalComprasPorMes() {
    return totalComprasPorMes.clone();
  }

  public Matriz_Int_12x2 getTotalUnidadesVendidasPorMes() {
    return totalUnidadesVendidasPorMes.clone();
  }

  public Matriz_Double_12x2 getTotalFacturadoPorMes() {
    return totalFacturadoPorMes.clone();
  }

  public int[] getNumeroProdutosDistintosPorMes() {
    int tmp[] = new int[MAX_MONTH];
    for (int i = 0; i < MAX_MONTH; i++) {
      tmp[i] = this.numeroProdutosDistintosPorMes[i];

    }
    return tmp;
  }

 

  /*
   * SETTERS
   */


  public void setNumeroProdutosDistintosPorMes(int[] numeroProdutosDistintosPorMes) {

    for (int i = 0; i < MAX_MONTH; i++) {
      this.numeroProdutosDistintosPorMes[i] = numeroProdutosDistintosPorMes[i];

    }

  }

  

  public void setTotalComprasPorMes(Matriz_Int_12x2 totalComprasPorMes) {
    this.totalComprasPorMes = totalComprasPorMes.clone();
  }


  public void setTotalUnidadesVendidasPorMes(Matriz_Int_12x2 totalUnidadesVendidasPorMes) {
    this.totalUnidadesVendidasPorMes = totalUnidadesVendidasPorMes.clone();
  }

  public void setTotalFacturadoPorMes(Matriz_Double_12x2 totalFacturadoPorMes) {
    this.totalFacturadoPorMes = totalFacturadoPorMes.clone();
  }



  /**
   * Querie 3
   */

  TreeSet<String> getSetProdutoNComprados() {

    TreeSet<String> tmp = new TreeSet<>();

    for (Map.Entry<Produto, FichaProdutoContabilidade> par : this.arvoreProdutos.entrySet()) {

      if (par.getValue().getNumComprasProduto() == 0) {
        tmp.add(par.getKey().getCodigoProduto());

      }
    }

    return tmp;
  }


  /*
   * Querie 5
   * 
   */

  int getNumProdutosDistintosNoMes(Mes mes) {

    return this.numeroProdutosDistintosPorMes[mes.getNum_mes() - 1];
  }


  Map<Mes, Integer> getMapNumeroProdutosDistintosPorMes() {

    TreeMap<Mes, Integer> resultado = new TreeMap<>();

    for (int i = 0; i < MAX_MONTH; i++) {
      Mes mes = Mes.numero_to_mes(i + 1);
      resultado.put(mes, this.numeroProdutosDistintosPorMes[i]);

    }
    return resultado;


  }


  /*
   * Querie 10
   */
  
  public List<ParProdutoQuantidadeComprada> getParesrodutoQuantidadeCompradaDiferentes(int X) {
  
  TreeSet<ParProdutoQuantidadeComprada> pares = new TreeSet<>(new ComparatorParProdutoQuantidadeComprada());
  ArrayList<ParProdutoQuantidadeComprada> lista = new ArrayList<>();
  ParProdutoQuantidadeComprada novo_par;

  for (Map.Entry<Produto, FichaProdutoContabilidade> entrada : this.arvoreProdutos.entrySet()) {
      novo_par = new ParProdutoQuantidadeComprada(entrada.getKey(), entrada.getValue().getNumUnidadesProdutoVendidas());
      pares.add(novo_par);
  }
         

    for (ParProdutoQuantidadeComprada par : pares) {
      lista.add(par);
  }

  if (X > arvoreProdutos.size()) {
      X = arvoreProdutos.size();
  }
  
  return lista.subList(0, X);
}

  /*
   * CONTABILIDADE AUX
   */

  /**
   * 
   * @return
   */
  public int getTotalCompras() {
    return totalComprasPorMes.getSomaTotal();
  }

  /**
   * 
   * @param produto
   * @param mes
   * @param tipo_compra
   * @param valor
   */
  public void setTotalComprasPorMesPorTipoCompra(Produto produto, Mes mes, TipoCompra tipo_compra,
      int valor) {
    this.totalComprasPorMes.setValorMesTipoCompra(mes, tipo_compra, valor);

  }

  /**
   * 
   * @param produto
   * @param mes
   * @param tipo_compra
   * @param valor
   */
  public void addTotalComprasPorMesPorTipoCompra(Produto produto, Mes mes, TipoCompra tipo_compra,
      int valor) {
    this.totalComprasPorMes.addValorMesTipoCompra(mes, tipo_compra, valor);

  }

  /**
   * 
   * @return
   */
  public int getTotalUnidadesVendidas() {
    return totalUnidadesVendidasPorMes.getSomaTotal();
  }

  /**
   * 
   * @param produto
   * @param mes
   * @param tipo_compra
   * @param valor
   */
  public void setNumUnidadesVendidasPorMesPorTipoCompra(Produto produto, Mes mes,
      TipoCompra tipo_compra, int valor) {
    this.totalUnidadesVendidasPorMes.setValorMesTipoCompra(mes, tipo_compra, valor);

  }

  /**
   * 
   * @param produto
   * @param mes
   * @param tipo_compra
   * @param valor
   */
  public void addNumUnidadesVendidasPorMesPorTipoCompra(Produto produto, Mes mes,
      TipoCompra tipo_compra, int valor) {
    this.totalUnidadesVendidasPorMes.addValorMesTipoCompra(mes, tipo_compra, valor);

  }


  /**
   * 
   * @return
   */
  public double getTotalFacturado() {
    return totalFacturadoPorMes.getSomaTotal();
  }

  /**
   * 
   * @param produto
   * @param mes
   * @param tipo_compra
   * @param valor
   */
  public void setTotalFacturadoPorMesPorTipoCompra(Produto produto, Mes mes,
      TipoCompra tipo_compra, int valor) {
    this.totalFacturadoPorMes.setValorMesTipoCompra(mes, tipo_compra, valor);

  }

  /**
   * 
   * @param produto
   * @param mes
   * @param tipo_compra
   * @param valor
   */
  public void addTotalFacturadoPorMesPorTipoCompra(Produto produto, Mes mes,
      TipoCompra tipo_compra, int valor) {
    this.totalFacturadoPorMes.addValorMesTipoCompra(mes, tipo_compra, valor);

  }


  /**
   * 
   * @param mes
   * @return
   */
  public int getTotalComprasPorMes(Mes mes) {
    return totalComprasPorMes.getValorMesTipoCompra(mes, TipoCompra.AMBOS);
  }

  /**
   * 
   * @param mes
   * @return
   */
  public int getTotalUnidadesVendidasPorMes(Mes mes) {
    return totalUnidadesVendidasPorMes.getValorMesTipoCompra(mes, TipoCompra.AMBOS);
  }

  /**
   * 
   * @param mes
   * @return
   */
  public double getTotalFacturadoPorMes(Mes mes) {
    return totalFacturadoPorMes.getValorMesTipoCompra(mes, TipoCompra.AMBOS);
  }

  /**
   * 
   * @return
   */
  public TreeMap<Produto, FichaProdutoContabilidade> getArvoreProdutos() {

    TreeMap<Produto, FichaProdutoContabilidade> tmp =
        new TreeMap<Produto, FichaProdutoContabilidade>();

    for (Map.Entry<Produto, FichaProdutoContabilidade> tuplo : this.arvoreProdutos.entrySet()) {

      tmp.put(tuplo.getKey().clone(), tuplo.getValue().clone());

    }

    return tmp;
  }

  /**
   * 
   * @param arvoreProdutos
   */
  public void setArvoreProdutos(TreeMap<Produto, FichaProdutoContabilidade> arvoreProdutos) {

    for (Map.Entry<Produto, FichaProdutoContabilidade> tuplo : arvoreProdutos.entrySet()) {

      this.arvoreProdutos.put(tuplo.getKey().clone(), tuplo.getValue().clone());

    }
  }

  // //////////////////////////////////////////////////////////////////////////


  /**
   * 
   */
  private FichaProdutoContabilidade getFichaProdutoNoClone(Produto produto)
      throws ProdutoNaoExisteException {
    FichaProdutoContabilidade ficha;

    if (this.arvoreProdutos.containsKey(produto)) {
      ficha = this.arvoreProdutos.get(produto);
    } else {
      throw new ProdutoNaoExisteException();
    }

    return ficha;
  }

  /**
   * 
   * @param produto
   * @param mes
   * @return
   * @throws ProdutoNaoExisteException
   */
  public int getNumeroUnidadesCompradasProdutoMes(Produto produto, Mes mes)
      throws ProdutoNaoExisteException {
    return this.getFichaProdutoNoClone(produto).getNumComprasProdutoPorMes(mes);
  }

  /**
   * 
   * @param produto
   * @return
   * @throws ProdutoNaoExisteException
   */
  public int getNumeroTotalUnidadesCompradasProdutoMes(Produto produto)
      throws ProdutoNaoExisteException {
    return this.getFichaProdutoNoClone(produto).getNumComprasProduto();
  }

  /**
   * 
   * @param produto
   * @param mes_inf
   * @param mes_sup
   * @param tipo_compra
   * @return
   * @throws ProdutoNaoExisteException
   */
  public int getNumeroTotalUnidadesCompradasProdutoPorIntervaloDeMeses(Produto produto,
      Mes mes_inf, Mes mes_sup, TipoCompra tipo_compra) throws ProdutoNaoExisteException {
    return this.getFichaProdutoNoClone(produto).getNumUnidadesProdutoVendidasPorIntervaloDeMeses(
        mes_inf, mes_sup, tipo_compra);
  }

  /**
   * 
   * @param produto
   * @param mes
   * @return
   * @throws ProdutoNaoExisteException
   */
  public int getNumUnidadesProdutoVendidasPorMes(Produto produto, Mes mes)
      throws ProdutoNaoExisteException {
    return this.getFichaProdutoNoClone(produto).getNumUnidadesProdutoVendidasPorMes(mes);
  }

  /**
   * 
   * @param produto
   * @return
   * @throws ProdutoNaoExisteException
   */
  public int getNumeroTotalUnidadesProdutoVendidasPorMes(Produto produto)
      throws ProdutoNaoExisteException {
    return this.getFichaProdutoNoClone(produto).getNumUnidadesProdutoVendidas();
  }

  /**
   * 
   * @param produto
   * @param mes_inf
   * @param mes_sup
   * @param tipo_compra
   * @return
   * @throws ProdutoNaoExisteException
   */
  public int getNumUnidadesProdutoVendidasPorIntervaloDeMeses(Produto produto, Mes mes_inf,
      Mes mes_sup, TipoCompra tipo_compra) throws ProdutoNaoExisteException {
    return this.getFichaProdutoNoClone(produto).getNumUnidadesProdutoVendidasPorIntervaloDeMeses(
        mes_inf, mes_sup, tipo_compra);
  }

  /**
   * 
   * @param produto
   * @param mes
   * @return
   * @throws ProdutoNaoExisteException
   */
  public double getFacturacaoProdutoPorMes(Produto produto, Mes mes)
      throws ProdutoNaoExisteException {
    return this.getFichaProdutoNoClone(produto).getFacturacaoProdutoPorMes(mes);
  }

  /**
   * 
   * @param produto
   * @return
   * @throws ProdutoNaoExisteException
   */
  public double getFacturacaoProdutoPorMes(Produto produto) throws ProdutoNaoExisteException {
    return this.getFichaProdutoNoClone(produto).getFacturacaoProduto();
  }

  /**
   * 
   * @param produto
   * @param mes_inf
   * @param mes_sup
   * @param tipo_compra
   * @return
   * @throws ProdutoNaoExisteException
   */
  public double getFacturacaoProdutoPorIntervaloDeMeses(Produto produto, Mes mes_inf, Mes mes_sup,
      TipoCompra tipo_compra) throws ProdutoNaoExisteException {
    return this.getFichaProdutoNoClone(produto).getFacturacaoProdutoPorIntervaloDeMeses(mes_inf,
        mes_sup, tipo_compra);
  }

  /**
   * 
   * @param produto
   * @param mes
   * @param tipo_compra
   * @param valor
   * @throws ProdutoNaoExisteException
   */
  public void setNumUnidadesProdutoVendidasPorMesPorTipoCompra(Produto produto, Mes mes,
      TipoCompra tipo_compra, int valor) throws ProdutoNaoExisteException {
    this.getFichaProdutoNoClone(produto).setFacturacaoProdutoPorMesPorTipoCompra(mes, tipo_compra,
        valor);

  }

  /**
   * 
   * @param produto
   * @param mes
   * @param tipo_compra
   * @param valor
   * @throws ProdutoNaoExisteException
   */
  public void addNumUnidadesProdutoVendidasPorMesPorTipoCompra(Produto produto, Mes mes,
      TipoCompra tipo_compra, int valor) throws ProdutoNaoExisteException {
    this.getFichaProdutoNoClone(produto).addFacturacaoProdutoPorMesPorTipoCompra(mes, tipo_compra,
        valor);
  }

  /**
   * 
   * @param produto
   * @param mes
   * @param tipo_compra
   * @param valor
   * @throws ProdutoNaoExisteException
   */
  public void setNumComprasProdutoPorMesPorTipoCompra(Produto produto, Mes mes,
      TipoCompra tipo_compra, int valor) throws ProdutoNaoExisteException {
    this.getFichaProdutoNoClone(produto).setNumComprasProdutoPorMesPorTipoCompra(mes, tipo_compra,
        valor);
  }

  /**
   * 
   * @param produto
   * @param mes
   * @param tipo_compra
   * @param valor
   * @throws ProdutoNaoExisteException
   */
  public void addNumComprasProdutoPorMesPorTipoCompra(Produto produto, Mes mes,
      TipoCompra tipo_compra, int valor) throws ProdutoNaoExisteException {
    this.getFichaProdutoNoClone(produto).addNumComprasProdutoPorMesPorTipoCompra(mes, tipo_compra,
        valor);
  }

  /**
   * 
   * @param produto
   * @param mes
   * @param tipo_compra
   * @param valor
   * @throws ProdutoNaoExisteException
   */
  public void setFacturacaoProdutoPorMesPorTipoCompra(Produto produto, Mes mes,
      TipoCompra tipo_compra, int valor) throws ProdutoNaoExisteException {
    this.getFichaProdutoNoClone(produto).setFacturacaoProdutoPorMesPorTipoCompra(mes, tipo_compra,
        valor);
  }

  /**
   * 
   * @param produto
   * @param mes
   * @param tipo_compra
   * @param valor
   * @throws ProdutoNaoExisteException
   */
  public void addFacturacaoProdutoPorMesPorTipoCompra(Produto produto, Mes mes,
      TipoCompra tipo_compra, int valor) throws ProdutoNaoExisteException {
    this.getFichaProdutoNoClone(produto).addFacturacaoProdutoPorMesPorTipoCompra(mes, tipo_compra,
        valor);
  }



  // //////////////////////////////////////////////////////////////////////////////
  
  

//  @Override
//  public int hashCode() {
//    final int prime = 31;
//    int result = 1;
//    result = prime * result + ((arvoreProdutos == null) ? 0 : arvoreProdutos.hashCode());
//    result = prime * result + ((totalComprasPorMes == null) ? 0 : totalComprasPorMes.hashCode());
//    result =
//        prime * result + ((totalFacturadoPorMes == null) ? 0 : totalFacturadoPorMes.hashCode());
//    result =
//        prime * result
//            + ((totalUnidadesVendidasPorMes == null) ? 0 : totalUnidadesVendidasPorMes.hashCode());
//    return result;
//  }

//  @Override
//  public boolean equals(Object obj) {
//    if (this == obj)
//      return true;
//    if (obj == null)
//      return false;
//    if (getClass() != obj.getClass())
//      return false;
//    Contabilidade other = (Contabilidade) obj;
//    if (arvoreProdutos == null) {
//      if (other.getArvoreProdutos() != null)
//        return false;
//    } else if (!arvoreProdutos.equals(other.getArvoreProdutos()))
//      return false;
//    if (totalComprasPorMes == null) {
//      if (other.getTotalComprasPorMes() != null)
//        return false;
//    } else if (!totalComprasPorMes.equals(other.getTotalComprasPorMes()))
//      return false;
//    if (totalFacturadoPorMes == null) {
//      if (other.getTotalFacturadoPorMes() != null)
//        return false;
//    } else if (!totalFacturadoPorMes.equals(other.getTotalFacturadoPorMes()))
//      return false;
//    if (totalUnidadesVendidasPorMes == null) {
//      if (other.getTotalUnidadesVendidasPorMes() != null)
//        return false;
//    } else if (!totalUnidadesVendidasPorMes.equals(other.getTotalUnidadesVendidasPorMes()))
//      return false;
//    return true;
//  }

//  @Override
//  public String toString() {
//    StringBuilder sb = new StringBuilder();
//    sb.append("Contabilidade [totalComprasPorMes=" + totalComprasPorMes
//        + ", totalUnidadesVendidasPorMes=" + totalUnidadesVendidasPorMes
//        + ", totalFacturadoPorMes=" + totalFacturadoPorMes + ", arvoreProdutos=" + arvoreProdutos
//        + "]");
//    return sb.toString();
//  }

}
