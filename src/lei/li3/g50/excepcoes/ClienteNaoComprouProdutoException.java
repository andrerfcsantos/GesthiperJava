package lei.li3.g50.excepcoes;

import java.io.Serializable;

public class ClienteNaoComprouProdutoException extends Exception implements Serializable{

    public ClienteNaoComprouProdutoException() {
    }

    public ClienteNaoComprouProdutoException(String msg) {
        super(msg);
    }
}
