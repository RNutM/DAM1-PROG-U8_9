package varios;

import java.io.FileWriter;
import java.io.IOException;

public class CrearCSV {
	/**
	 * Crear archivo CSV
	 * 
	 * @author Robert G
	 * 
	 */
	public static void main(String[] args) {
		final String nombreDeArchivo = "./ficheros/pedidos.csv";
		crearArchivoCSV(nombreDeArchivo);
	}

	private static void crearArchivoCSV(String nombreDeArchivo) {
		// Esto tambien puede ser "\t" para el delimitador de pestañas
		crearArchivoCSV(nombreDeArchivo, ";");
	}

	private static void crearArchivoCSV(String file, String delim) {
		final String NEXT_LINE = "\n";
		try {
			FileWriter fw = new FileWriter(file);

			fw.append("Código Pedido").append(delim);
			fw.append("Estado Pedido").append(delim);
			fw.append("Código Cliente").append(delim);
			fw.append(NEXT_LINE);
			// fw.append("123").append(NEXT_LINE);

			fw.flush();
			fw.close();
		} catch (IOException e) {
			// Error al crear el archivo, por ejemplo, el archivo
			// está actualmente abierto.
			e.printStackTrace();
		}
	}
}