package lei.li3.g50.excepcoes;

import java.io.Serializable;

public class ProdutoNaoExisteException extends Exception implements Serializable {

    
    private static final long serialVersionUID = 7689782863553043152L;

    public ProdutoNaoExisteException() {
    }

    public ProdutoNaoExisteException(String msg) {
        super(msg);
    }
}
