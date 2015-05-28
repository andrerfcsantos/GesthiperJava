package lei.li3.g50.modulos.dados;

public class Cliente implements Comparable<Cliente>{
    private String codigo_cliente;
    
    /*
    CONSTRUCTORES
    */
    public Cliente(){
        codigo_cliente = "";
    }
    
    public Cliente(String cod_cliente){
        codigo_cliente = cod_cliente;
    }
    
    public Cliente(Cliente cliente){
        codigo_cliente = cliente.getCodigoCliente();
    }
    
    /*
    SETTERS E GETTERS
    */
    
    public String getCodigoCliente() {
        return codigo_cliente;
    }

    public void setCodigoCliente(String codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }
    
    /*
    METODOS CLIENTE
    */
    
    public boolean equals(Cliente cliente){
        return this.codigo_cliente.equals(cliente.getCodigoCliente());
    }
    
    
    
    
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;
        Cliente cliente = (Cliente) obj;
        return this.equals(cliente);
    }
    
   
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigo_cliente == null) ? 0 : codigo_cliente.hashCode());
		return result;
	}

	@Override
    public Cliente clone(){
        return new Cliente(this);
    }
    
    @Override
    public String toString() {
        return "Cliente{" + "codigo_cliente=" + codigo_cliente + '}';
    }

    @Override
    public int compareTo(Cliente c) {
        return this.codigo_cliente.compareTo(c.getCodigoCliente());
    }

	
    
}
