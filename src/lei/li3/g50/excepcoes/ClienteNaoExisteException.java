package lei.li3.g50.excepcoes;

import java.io.Serializable;

public class ClienteNaoExisteException extends Exception implements Serializable {

    public ClienteNaoExisteException() {
    }

    public ClienteNaoExisteException(String msg) {
        super(msg);
    }
}
