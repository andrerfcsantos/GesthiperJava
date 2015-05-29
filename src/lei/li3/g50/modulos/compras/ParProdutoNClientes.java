package lei.li3.g50.modulos.compras;

import lei.li3.g50.excepcoes.ArrayNaoTem12Comprimento;
import lei.li3.g50.modulos.dados.Mes;
import lei.li3.g50.modulos.dados.Produto;

public class ParProdutoNClientes implements Comparable<ParProdutoNClientes> {

    private Produto produto;
    private int numeroClientesPorMes[];
    private int numeroTotalClientes;

    public ParProdutoNClientes() {
        this.produto = new Produto();
        this.numeroClientesPorMes = new int[12];
        this.numeroTotalClientes = 0;
    }

    public ParProdutoNClientes(Produto produto) {
        this.produto = produto.clone();
        this.numeroClientesPorMes = new int[12];
        this.numeroTotalClientes = 0;
    }

    public ParProdutoNClientes(Produto produto, int[] numeroClientesPorMes)
            throws ArrayNaoTem12Comprimento {

        this.produto = produto.clone();
        this.numeroTotalClientes = 0;
        this.numeroClientesPorMes = numeroClientesPorMes;
        if (numeroClientesPorMes.length == 12) {
            for (int i = 0; i < 12; i++) {
                this.numeroClientesPorMes[i] = numeroClientesPorMes[i];
                this.numeroTotalClientes += numeroClientesPorMes[i];
            }
        } else {
            throw new ArrayNaoTem12Comprimento();
        }

    }

    public Produto getProduto() {
        return produto.clone();
    }

    public void setProduto(Produto produto) {
        this.produto = produto.clone();
    }

    public int[] getNumeroClientesPorMes() {
        return numeroClientesPorMes;
    }
    
    public void setNumeroClientesMes(Mes mes, int numero_clientes){
        int valor_antigo = this.numeroClientesPorMes[mes.mesToIndex()];
        int diferenca = numero_clientes - valor_antigo;
        this.numeroTotalClientes+=diferenca;
    }
    
    public void setNumeroClientesPorMes(int[] numeroClientesPorMes) 
            throws ArrayNaoTem12Comprimento {
        
        this.numeroTotalClientes = 0;
        if (numeroClientesPorMes.length == 12) {
            for (int i = 0; i < 12; i++) {
                this.numeroClientesPorMes[i] = numeroClientesPorMes[i];
                this.numeroTotalClientes += numeroClientesPorMes[i];
            }
        } else {
            throw new ArrayNaoTem12Comprimento();
        }
    }

    public int getNumeroTotalClientes() {
        return numeroTotalClientes;
    }


    @Override
    public int compareTo(ParProdutoNClientes t) {
        return this.produto.getCodigoProduto().compareTo(t.getProduto().getCodigoProduto());
    }
}
