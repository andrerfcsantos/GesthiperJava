package lei.li3.g50.excepcoes;

import java.io.Serializable;

public class ArrayNaoTem12Comprimento extends Exception implements Serializable {

    private static final long serialVersionUID = 5578652986798133004L;

    public ArrayNaoTem12Comprimento() {
    }

    public ArrayNaoTem12Comprimento(String msg) {
        super(msg);
    }
}
