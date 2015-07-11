/**
 * 
 */
package lei.li3.g50.utilitarios;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import lei.li3.g50.modulos.catalogos.CatalogoClientes;
import lei.li3.g50.modulos.catalogos.CatalogoProdutos;
import lei.li3.g50.modulos.compras.Compras;
import lei.li3.g50.modulos.contabilidade.Contabilidade;
import lei.li3.g50.modulos.dados.Hipermercado;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * @author bpereira
 *
 */
public class HipermercadoKryoSerializer extends Serializer<Hipermercado> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.esotericsoftware.kryo.Serializer#read(com.esotericsoftware.kryo.Kryo,
	 * com.esotericsoftware.kryo.io.Input, java.lang.Class)
	 */
	@Override
	public Hipermercado read(Kryo kryo, Input input,
			Class<Hipermercado> hipermercado) {
		//
		// kryo.register(Collection.class, 0);
		// kryo.register(CatalogoClientes.class, 1);
		// kryo.register(CatalogoProdutos.class, 2);
		// kryo.register(Contabilidade.class, 4);
		// kryo.register(Compras.class, 5);
		ArrayList<String> comprasInvalidas = kryo.readObject(input,
				ArrayList.class);

		CatalogoClientes moduloCatalogoClientes = kryo.readObject(input,
				CatalogoClientes.class);
		CatalogoProdutos moduloCatalogoProdutos = kryo.readObject(input,
				CatalogoProdutos.class);
		Contabilidade moduloContabilidade = kryo.readObject(input,
				Contabilidade.class);
		Compras moduloCompras = kryo.readObject(input, Compras.class);
		String ficheiro_clientes = kryo.readObject(input, String.class);
		String ficheiro_produtos = kryo.readObject(input, String.class);
		String ficheiro_compras = kryo.readObject(input, String.class);
		Hipermercado hiper = new Hipermercado(comprasInvalidas,
				moduloCatalogoClientes, moduloCatalogoProdutos,
				moduloContabilidade, moduloCompras);

		hiper.setFicheiroClientes(new File(ficheiro_compras));
		hiper.setFicheiroProdutos(new File(ficheiro_produtos));
		hiper.setFicheiroCompras(new File(ficheiro_compras));

		return hiper;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.esotericsoftware.kryo.Serializer#write(com.esotericsoftware.kryo.
	 * Kryo, com.esotericsoftware.kryo.io.Output, java.lang.Object)
	 */
	@Override
	public void write(Kryo kryo, Output output, Hipermercado hipermercado) {

		kryo.register(Collection.class, 1);
		kryo.register(CatalogoClientes.class, 2);
		kryo.register(CatalogoProdutos.class, 3);
		kryo.register(Contabilidade.class, 4);
		kryo.register(Compras.class, 5);
		kryo.register(String.class, 6);
		kryo.register(String.class, 7);
		kryo.register(String.class, 8);

		kryo.writeObject(output, hipermercado.getComprasInvalidas(),
				kryo.getSerializer(Collection.class));
		kryo.writeObject(output, hipermercado.getCatalogoClientes(),
				kryo.getSerializer(CatalogoClientes.class));
		kryo.writeObject(output, hipermercado.getCatalogoProdutos(),
				kryo.getSerializer(CatalogoProdutos.class));
		kryo.writeObject(output, hipermercado.getContabilidade(),
				kryo.getSerializer(Contabilidade.class));
		kryo.writeObject(output, hipermercado.getCompras(),
				kryo.getSerializer(Compras.class));

		kryo.writeObject(output, hipermercado.getFicheiroClientes().getPath(),
				kryo.getSerializer(String.class));
		kryo.writeObject(output, hipermercado.getFicheiroProdutos().getPath(),
				kryo.getSerializer(String.class));
		kryo.writeObject(output, hipermercado.getFicheiroCompras().getPath(),
				kryo.getSerializer(String.class));

	}

}
