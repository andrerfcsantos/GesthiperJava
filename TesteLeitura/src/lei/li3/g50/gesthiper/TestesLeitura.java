package lei.li3.g50.gesthiper;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lei.li3.g50.utilitarios.Crono;

public class TestesLeitura {


	public static void main(String[] args) {
            try {
                double var;
                double comprasBRNoParse, comprasBRParse;
                double compras1BRNoParse, compras1BRParse;
                double compras3BRNoParse, compras3BRParse;
                double comprasSCNoParse, comprasSCParse;
                double compras1SCNoParse, compras1SCParse;
                double compras3SCNoParse, compras3SCParse;
                
                
                /*
                Compras.txt
                */
                Crono.start();
                LeituraFicheiros.comprasBufferedReaderNoParse("datasets/Compras.txt");
                var = Crono.stop();
                System.out.printf("Compras Buffered Reader No Parse: %f\n", var);
                
                Crono.start();
                LeituraFicheiros.comprasBufferedReaderParse("datasets/Compras.txt");
                var = Crono.stop();
                System.out.printf("Compras Buffered Reader Parse: %f\n", var);
                
                Crono.start();
                LeituraFicheiros.comprasScannerNoParse("datasets/Compras.txt");
                var = Crono.stop();
                System.out.printf("Compras Scanner No Parse: %f\n", var);
                
                Crono.start();
                LeituraFicheiros.comprasScannerParse("datasets/Compras.txt");
                var = Crono.stop();
                System.out.printf("Compras Scanner Parse: %f\n", var);
                
                /*
                Compras1.txt
                */
                
                Crono.start();
                LeituraFicheiros.comprasBufferedReaderNoParse("datasets/Compras1.txt");
                var = Crono.stop();
                System.out.printf("Compras1 Buffered Reader No Parse: %f\n", var);
                
                Crono.start();
                LeituraFicheiros.comprasBufferedReaderParse("datasets/Compras1.txt");
                var = Crono.stop();
                System.out.printf("Compras1 Buffered Reader Parse: %f\n", var);
                
                Crono.start();
                LeituraFicheiros.comprasScannerNoParse("datasets/Compras1.txt");
                var = Crono.stop();
                System.out.printf("Compras1 Scanner No Parse: %f\n", var);
                
                Crono.start();
                LeituraFicheiros.comprasScannerParse("datasets/Compras1.txt");
                var = Crono.stop();
                System.out.printf("Compras1 Scanner Parse: %f\n", var);
                
                /*
                Compras3.txt
                */
                Crono.start();
                LeituraFicheiros.comprasBufferedReaderNoParse("datasets/Compras3.txt");
                var = Crono.stop();
                System.out.printf("Compras3 Buffered Reader No Parse: %f\n", var);
                
                Crono.start();
                LeituraFicheiros.comprasBufferedReaderParse("datasets/Compras3.txt");
                var = Crono.stop();
                System.out.printf("Compras3 Buffered Reader Parse: %f\n", var);
                
                Crono.start();
                LeituraFicheiros.comprasScannerNoParse("datasets/Compras3.txt");
                var = Crono.stop();
                System.out.printf("Compras3 Scanner No Parse: %f\n", var);
                
                Crono.start();
                LeituraFicheiros.comprasScannerParse("datasets/Compras3.txt");
                var = Crono.stop();
                System.out.printf("Compras3 Scanner Parse: %f\n", var);
                
                
                
                
                
                
                
                
                
                
            } catch (IOException ex) {
                Logger.getLogger(TestesLeitura.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            
	}
        
}
