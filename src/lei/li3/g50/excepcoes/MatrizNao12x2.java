package lei.li3.g50.excepcoes;

import java.io.Serializable;

public class MatrizNao12x2 extends Exception implements Serializable  {

    public MatrizNao12x2() {
    }

    public MatrizNao12x2(String msg) {
        super(msg);
    }
}
