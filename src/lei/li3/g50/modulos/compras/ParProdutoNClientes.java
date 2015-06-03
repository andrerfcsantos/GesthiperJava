package lei.li3.g50.modulos.compras;

import java.util.Arrays;
import lei.li3.g50.excepcoes.ArrayNaoTem12Comprimento;
import lei.li3.g50.modulos.dados.Mes;
import lei.li3.g50.modulos.dados.Produto;

public class ParProdutoNClientes {

    private int numeroClientesPorMes[];
    private int numeroTotalClientes;

    /*
     CONSTRUCTORES
     */
    public ParProdutoNClientes() {
        this.numeroClientesPorMes = new int[12];
        this.numeroTotalClientes = 0;
    }


    public ParProdutoNClientes(Produto produto, int[] numeroClientesPorMes)
            throws ArrayNaoTem12Comprimento {

        this.numeroTotalClientes = 0;
        this.numeroClientesPorMes = new int[12];
        if (numeroClientesPorMes.length == 12) {
            for (int i = 0; i < 12; i++) {
                this.numeroClientesPorMes[i] = numeroClientesPorMes[i];
                this.numeroTotalClientes += numeroClientesPorMes[i];
            }
        } else {
            throw new ArrayNaoTem12Comprimento();
        }

    }

    public ParProdutoNClientes(ParProdutoNClientes par) {
        this.numeroTotalClientes = par.getNumeroTotalClientes();
        this.numeroClientesPorMes = new int[12];

        for (int i = 0; i < 12; i++) {
            this.numeroClientesPorMes[i] = par.getNumeroClientesMes(i);
        }
    }

    /*
     GETTERS
     */
  
    public int[] getNumeroClientesPorMes() {
        int temp[] = new int[12];
        for (int i = 0; i < 12; i++) {
            temp[i] = this.numeroClientesPorMes[i];
        }
        return temp;
    }

    public int getNumeroClientesMes(int indice) {
        return this.numeroClientesPorMes[indice];
    }

    public int getNumeroClientesMes(Mes mes) {
        return this.numeroClientesPorMes[mes.getIndiceArray()];
    }

    public int getNumeroClientesEntreMeses(Mes mes1, Mes mes2) {
        int resultado = 0;
        int indice_menor = Math.min(mes1.getIndiceArray(), mes2.getIndiceArray());
        int indice_maior = Math.max(mes1.getIndiceArray(), mes2.getIndiceArray());

        for (int i = indice_menor; i <= indice_maior; i++) {
            resultado += this.numeroClientesPorMes[i];
        }

        return resultado;
    }

    public int getNumeroTotalClientes() {
        return numeroTotalClientes;
    }

    /*
     SETTERS
     */
    

    public void setNumeroClientesMes(Mes mes, int numero_clientes) {
        int indice = mes.getIndiceArray();
        int valor_antigo = this.numeroClientesPorMes[indice];
        int diferenca = numero_clientes - valor_antigo;
        this.numeroClientesPorMes[indice] = numero_clientes;
        this.numeroTotalClientes += diferenca;
    }
    
    public void addNumeroClientesMes(Mes mes, int numero_clientes){
        this.numeroClientesPorMes[mes.getIndiceArray()] += numero_clientes;
        this.numeroTotalClientes += numero_clientes;
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


    /*
     METODOS STANDARD
     */
    
    @Override
    public ParProdutoNClientes clone(){
        return new ParProdutoNClientes(this);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Arrays.hashCode(this.numeroClientesPorMes);
        hash = 41 * hash + this.numeroTotalClientes;
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
        final ParProdutoNClientes other = (ParProdutoNClientes) obj;
        return (this.numeroTotalClientes == other.numeroTotalClientes)
                && Arrays.equals(this.numeroClientesPorMes, other.numeroClientesPorMes);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("ParProdutoNClientes{");;
        sb.append(", numeroTotalClientes=").append(numeroTotalClientes);
        sb.append('}');
        
        return sb.toString();
    }
    
}
