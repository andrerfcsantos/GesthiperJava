package lei.li3.g50.excepcoes;

import java.io.Serializable;

public class ClienteNaoComprouProdutoException extends Exception implements Serializable {

    private static final long serialVersionUID = -5079364783443532857L;

    public ClienteNaoComprouProdutoException() {
    }

    public ClienteNaoComprouProdutoException(String msg) {
        super(msg);
    }
}
