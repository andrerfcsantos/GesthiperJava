package lei.li3.g50.excepcoes;

import java.io.Serializable;

public class MatrizNao12x2 extends Exception implements Serializable {

    
    private static final long serialVersionUID = -7371032171565933618L;

    public MatrizNao12x2() {
    }

    public MatrizNao12x2(String msg) {
        super(msg);
    }
}
