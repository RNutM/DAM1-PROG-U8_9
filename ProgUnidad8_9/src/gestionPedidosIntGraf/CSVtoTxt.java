package gestionPedidosIntGraf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVtoTxt {
	/**
	 * Gestión pedidos interfaz gráfica - Convertir CSV a TXT
	 * 
	 * @author Robert G
	 * 
	 */

	// Método para convertir un archivo CSV a TXT
	public static void convert1() throws IOException {

		String file = "./ficheros/interfazGrafica/pedidos.csv", txtFile = "./ficheros/interfazGrafica/pedidoscsv.txt";
		BufferedReader leer = null;// Para leer el fichero CSV
		BufferedWriter escribir = null;// Para escribir en un fichero txt
		String palabras = "";
		String separador = ";";// Aqui puedes usar el separador que tenga tu CSV
		int cont = 0;

		try {
			leer = new BufferedReader(new FileReader(file));
			escribir = new BufferedWriter(new FileWriter(txtFile));

			while ((palabras = leer.readLine()) != null) {
				String[] codigo = palabras.split(separador);

				for (int i = 0; i < palabras.length(); i++) {

				}
				if (cont == 0) {

					escribir.write(codigo[0]);
					escribir.write(" ");

					escribir.write("   " + codigo[1]);
					escribir.write(" ");

					escribir.write("   " + codigo[2]);
					escribir.write(" ");
					escribir.write("\n");

					escribir.write("-----------------------------------------------------------");
					escribir.write("\n");
					System.out.println("********************************");
					System.out.println("Archivo convertido correctamente");
					System.out.println("********************************");
				} else {
					escribir.write("     " + codigo[0]);
					escribir.write("  ");

					escribir.write("         	 " + codigo[1]);
					escribir.write("  ");

					escribir.write("      	" + codigo[2]);
					escribir.write("  ");
					escribir.write("\n");
				}
				cont++;

				// Con estas lineas lo mostraría en consola
				// System.out.println("");
				// System.out.println(" " + codigo[0] + "\t\t" + codigo[1] + "\t" + codigo[2] );
			} // Fin del while
		} // Fin del try

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		finally {

			if (leer != null) {
				try {
					leer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} // Fin if leer

			leer.close();
			escribir.close();

		} // Fin del finally
	}// Fin del método

	// Método para convertir un archivo CSV a TXT
	public static void convert2() throws IOException {

		String file = "./ficheros/interfazGrafica/detalle.csv", txtFile = "./ficheros/interfazGrafica/detallecsv.txt";
		BufferedReader leer = null;// Para leer el fichero CSV
		BufferedWriter escribir = null;// Para escribir en un fichero txt
		String palabras = "";
		String separador = ";";// Aqui puedes usar el separador que tenga tu CSV
		int cont = 0;

		try {

			leer = new BufferedReader(new FileReader(file));
			escribir = new BufferedWriter(new FileWriter(txtFile));

			while ((palabras = leer.readLine()) != null) {
				String[] codigo = palabras.split(separador);
				for (int i = 0; i < palabras.length(); i++) {

				}
				if (cont == 0) {

					escribir.write(codigo[0]);
					escribir.write(" ");

					escribir.write("   " + codigo[1]);
					escribir.write(" ");

					escribir.write("   " + codigo[2]);
					escribir.write(" ");

					escribir.write("   " + codigo[3]);
					escribir.write(" ");

					escribir.write("   " + codigo[4]);
					escribir.write(" ");
					escribir.write("\n");

					escribir.write("---------------------------------------------------------------"
							+ "----------------------------------------------");
					escribir.write("\n");
					System.out.println("********************************");
					System.out.println("Archivo convertido correctamente");
					System.out.println("********************************");
				} else {
					escribir.write("      " + codigo[0]);
					escribir.write("  ");

					escribir.write("        	    " + codigo[1]);
					escribir.write("  ");

					escribir.write("                    " + codigo[2]);
					escribir.write("  ");

					escribir.write("         	" + codigo[3]);
					escribir.write("  ");

					escribir.write("         	" + codigo[4]);
					escribir.write("  ");
					escribir.write("\n");
				}
				cont++;

				// Con estas lineas lo mostraría en consola
				// System.out.println("");
				// System.out.println(" " + codigo[0] + "\t\t" + codigo[1] + "\t" + codigo[2] );

			} // Fin del while

		} // Fin del try

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			if (leer != null) {
				try {
					leer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} // Fin if leer

			leer.close();
			escribir.close();

		} // Fin del finally

	}// Fin del método

}// Fin de clase