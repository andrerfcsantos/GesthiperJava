package lei.li3.g50.excepcoes;

import java.io.Serializable;

public class ProdutoNaoExisteException extends Exception implements Serializable{

    public ProdutoNaoExisteException() {
    }

    public ProdutoNaoExisteException(String msg) {
        super(msg);
    }
}
