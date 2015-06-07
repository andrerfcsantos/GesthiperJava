package lei.li3.g50.excepcoes;

public class ClienteNaoExisteException extends Exception {

    public ClienteNaoExisteException() {
    }

    public ClienteNaoExisteException(String msg) {
        super(msg);
    }
}
