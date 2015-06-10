package lei.li3.g50.excepcoes;

import java.io.Serializable;

public class PaginaImpossivelException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 708661924780368L;

    public PaginaImpossivelException() {
    }

    public PaginaImpossivelException(String msg) {
        super(msg);
    }
}
