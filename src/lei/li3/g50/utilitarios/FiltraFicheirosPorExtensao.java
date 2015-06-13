package lei.li3.g50.utilitarios;

import java.io.File;
import java.io.FilenameFilter;

public class FiltraFicheirosPorExtensao implements FilenameFilter {

    private String extensao;

    public FiltraFicheirosPorExtensao(String extensao) {
        this.extensao = extensao;
    }
    
    @Override
    public boolean accept(File file, String string) {
        return string.endsWith(extensao);
    }

}
