package excepcoes;

public class PaginaImpossivelException extends Exception {

    public PaginaImpossivelException() {
    }
    
    public PaginaImpossivelException(String msg) {
        super(msg);
    }
}
