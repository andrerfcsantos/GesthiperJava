package lei.li3.g50.utilitarios;

import java.util.Objects;
import lei.li3.g50.modulos.dados.Cliente;

public class TriploClienteQtdCompradaDinheiro {
    private Cliente cliente;
    private int quantidadeComprada;
    private double totalDinheirGasto;
    
    private TriploClienteQtdCompradaDinheiro(){}
    
    public TriploClienteQtdCompradaDinheiro(Cliente cliente){
        this.cliente = cliente.clone();
        this.quantidadeComprada =0;
        this.totalDinheirGasto=0;
    }
    
    public TriploClienteQtdCompradaDinheiro(Cliente cliente, int qtdComprada, double dinheiro){
        this.cliente = cliente.clone();
        this.quantidadeComprada =qtdComprada;
        this.totalDinheirGasto=dinheiro;
    }
    
    public TriploClienteQtdCompradaDinheiro(TriploClienteQtdCompradaDinheiro triplo){
        this.cliente = triplo.cliente.clone();
        this.quantidadeComprada =triplo.getQuantidadeComprada();
        this.totalDinheirGasto=triplo.getTotalDinheirGasto();
    }
    
    
    /*
    GETTERS
    */
    public Cliente getCliente() {
        return cliente;
    }

    public int getQuantidadeComprada() {
        return quantidadeComprada;
    }
    
    public double getTotalDinheirGasto() {
        return totalDinheirGasto;
    }
    
    /*
    SETTERS
    */
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente.clone();
    }

    public void setQuantidadeComprada(int quantidadeComprada) {
        this.quantidadeComprada = quantidadeComprada;
    }
    
    public void addQuantidadeComprada(int valor) {
        this.quantidadeComprada += valor;
    }

    public void setTotalDinheirGasto(double totalDinheirGasto) {
        this.totalDinheirGasto = totalDinheirGasto;
    }
    
    public void addTotalDinheirGasto(double valor) {
        this.totalDinheirGasto += valor;
    }
    
    
    /*
    METODOS STANDARD
    */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.cliente);
        hash = 17 * hash + this.quantidadeComprada;
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.totalDinheirGasto) ^ (Double.doubleToLongBits(this.totalDinheirGasto) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TriploClienteQtdCompradaDinheiro other = (TriploClienteQtdCompradaDinheiro) obj;
        return this.cliente.equals(other.cliente)
                && this.quantidadeComprada == this.quantidadeComprada
                && this.totalDinheirGasto == this.totalDinheirGasto;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TriploClienteQtdCompradaDinheiro{");
        sb.append("cliente=").append(cliente);
        sb.append(", quantidadeComprada=").append(quantidadeComprada);
        sb.append(", totalDinheirGasto=").append(totalDinheirGasto);
        sb.append('}');
        return sb.toString();
    }
    
    
    
    
    
}
