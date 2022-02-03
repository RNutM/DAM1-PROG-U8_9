package gestionPedidosIntGraf;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Pedidos {
	/**
	 * Gestión pedidos interfaz gráfica - Pedidos
	 * 
	 * @author Robert G
	 * 
	 */
	private int Codigo;
	private String Estado;
	private int CodigoCliente;

	public Pedidos(int codigo, String estado, int codigoCliente) {
		super();
		Codigo = codigo;
		Estado = estado;
		CodigoCliente = codigoCliente;
	}

	public int getCodigo() {
		return Codigo;
	}

	public void setCodigo(int codigo) {
		Codigo = codigo;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public int getCodigoCliente() {
		return CodigoCliente;
	}

	public void setCodigoCliente(int codigoCliente) {
		CodigoCliente = codigoCliente;
	}

	@Override
	public String toString() {
		return "pedidos [Codigo=" + Codigo + ", Estado=" + Estado + ", CodigoCliente=" + CodigoCliente + "]";
	}

	public static void CrearFicheroPedido(ResultSet rset, ArrayList<Pedidos> listapedidos)
			throws SQLException, IOException {

		String directorio = "./ficheros/interfazGrafica/pedidos.csv";
		FileWriter fw = new FileWriter(directorio);
		PrintWriter fichero = new PrintWriter(fw);
		fichero.println("Codigo Producto;Estado;Codigo Cliente");
		while (rset.next()) {
			fichero.println(rset.getInt(1) + ";" + rset.getString(2) + ";" + rset.getInt(3));
			listapedidos.add(new Pedidos(rset.getInt(1), rset.getString(2), rset.getInt(3)));
		}
		fichero.flush();
		fichero.close();
	}

	public static void leerPedidostxt() throws IOException {

		String directorio = "./ficheros/interfazGrafica/pedidos.txt";
		FileReader f = new FileReader(directorio);
		Scanner entrada = new Scanner(f);
		String cadena = "";
		while (entrada.hasNext()) {// lee cadena
			cadena = entrada.nextLine();
			System.out.println(cadena);
		}
		entrada.close();
	}
}
