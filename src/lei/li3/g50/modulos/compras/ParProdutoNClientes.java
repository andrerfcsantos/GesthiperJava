package lei.li3.g50.modulos.compras;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import lei.li3.g50.excepcoes.ArrayNaoTem12Comprimento;
import lei.li3.g50.modulos.dados.Mes;

public class ParProdutoNClientes implements Serializable  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3271844417478630684L;
	private int[] numeroClientesDistintosPorMes;
    private int numeroTotalClientesDistintos;

    /*
     CONSTRUCTORES
     */
    public ParProdutoNClientes() {
        this.numeroClientesDistintosPorMes = new int[12];
        this.numeroTotalClientesDistintos = 0;
    }
    
    public ParProdutoNClientes(int clientesDistintos[], int totalClientesDistintos) throws ArrayNaoTem12Comprimento {
        if(clientesDistintos.length!=12)
            throw new ArrayNaoTem12Comprimento();
        for(int i=0;i<12;i++) this.numeroClientesDistintosPorMes[i] = clientesDistintos[i];
        this.numeroTotalClientesDistintos = totalClientesDistintos;
    }


    public ParProdutoNClientes(ParProdutoNClientes par) {
        this.numeroTotalClientesDistintos = par.getNumeroTotalClientesDisntintos();
        this.numeroClientesDistintosPorMes = new int[12];
        for (int i = 0; i < 12; i++) {
            this.numeroClientesDistintosPorMes[i] = par.getNumeroClientesDisntintosMes(i);
        }
    }

    /*
     GETTERS
     */
  
    public int[] getNumeroClientesDistintosPorMesAsArray() {
        int temp[] = new int[12];
        for (int i = 0; i < 12; i++) {
            temp[i] = this.numeroClientesDistintosPorMes[i];
        }
        return temp;
    }
    
    public Map<Mes,Integer> getNumeroClientesDistintosPorMes(){
        Mes mes;
        TreeMap<Mes,Integer> mapMeses = new TreeMap<>();
        
        for(int i=0;i<12;i++){
            mes = Mes.numero_to_mes(i+1);
            mapMeses.put(mes, this.numeroClientesDistintosPorMes[i]);
        }
            
        return mapMeses;
    }
    
    public int getNumeroClientesDisntintosMes(int indice) {
        return this.numeroClientesDistintosPorMes[indice];
    }

    public int getNumeroClientesDisntintosMes(Mes mes) {
        return this.numeroClientesDistintosPorMes[mes.getIndiceArray()];
    }

    public int getNumeroTotalClientesDisntintos() {
        return numeroTotalClientesDistintos;
    }

    /*
     SETTERS
     */
    
    
    public void setNumeroTotalClientesDistintos(int valor){
        this.numeroTotalClientesDistintos = valor;
    }
    public void addNumeroTotalClientesDistintos(int valor){
        this.numeroTotalClientesDistintos += valor;
    }
    
    public void setNumeroClientesMes(Mes mes, int numero_clientes) {
        this.numeroClientesDistintosPorMes[mes.getIndiceArray()] = numero_clientes;
    }
    
    public void addNumeroClientesMes(Mes mes, int numero_clientes){
        this.numeroClientesDistintosPorMes[mes.getIndiceArray()] += numero_clientes;
    }
    
    public void setNumeroClientesPorMes(int[] numeroClientesPorMes)
            throws ArrayNaoTem12Comprimento {

        if (numeroClientesPorMes.length != 12) {
            throw new ArrayNaoTem12Comprimento();
        }
        
        for (int i = 0; i < 12; i++) {
            this.numeroClientesDistintosPorMes[i] = numeroClientesPorMes[i];
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
        hash = 41 * hash + Arrays.hashCode(this.numeroClientesDistintosPorMes);
        hash = 41 * hash + this.numeroTotalClientesDistintos;
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
        return (this.numeroTotalClientesDistintos == other.numeroTotalClientesDistintos)
                && Arrays.equals(this.numeroClientesDistintosPorMes, other.numeroClientesDistintosPorMes);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("ParProdutoNClientes{");
        sb.append("numeroTotalClientesDistintos=").append(numeroTotalClientesDistintos);
        sb.append('}');
        
        return sb.toString();
    }
    
}
