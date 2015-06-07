package lei.li3.g50.excepcoes;

public class ProdutoNaoExisteException extends Exception {

    public ProdutoNaoExisteException() {
    }

    public ProdutoNaoExisteException(String msg) {
        super(msg);
    }
}
