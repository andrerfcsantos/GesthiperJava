package lei.li3.g50.excepcoes;

import java.io.Serializable;

public class PaginaImpossivelException extends Exception implements Serializable  {

    public PaginaImpossivelException() {
    }
    
    public PaginaImpossivelException(String msg) {
        super(msg);
    }
}
