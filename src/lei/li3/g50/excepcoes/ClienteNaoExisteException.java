package lei.li3.g50.excepcoes;

import java.io.Serializable;

public class ClienteNaoExisteException extends Exception implements Serializable {

    private static final long serialVersionUID = 5086976191514780368L;

    public ClienteNaoExisteException() {
    }

    public ClienteNaoExisteException(String msg) {
        super(msg);
    }
}
