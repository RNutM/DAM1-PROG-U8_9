package Varios;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateWebsite {
	/**
	 * Acceso a BD, creando una página web con datos
	 * 
	 * @author Robert G
	 * 
	 */
	private static Statement stmt = null;

	public void conectar() {

		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "jardineria",
					"jardineria");
			// driver@machineName:port:SID , userid, password
			stmt = conn.createStatement();

		} catch (Exception ex) {

			System.out.println("Ha habido un fallo en la conexion a la BD : " + ex.getMessage());
		}
	}

	public static void main(String[] args) throws IOException, SQLException {

		CreateWebsite web = new CreateWebsite();
		web.conectar();

		/*
		 * Escribir en el fichero, poniendo true incrementa lo escrito si lo quito borra lo
		 * anterior
		 */
		String directorio = "./ficheros/tablas.html";
		FileWriter fw = new FileWriter(directorio, false);

		PrintWriter salida = new PrintWriter(fw);
		salida.println("<!DOCTYPE html>\r\n" + "<html lang=\"es-ES\">\r\n" + "<head>\r\n"
				+ "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">"
				+ "<title>Tablas</title>\r\n" + "</head>\r\n" + "<body>\r\n" + "	<h1>Base de datos</h1>");

		salida.println("<table border='1px' class='table table-striped'>");

		// Aqui construyo la tabla

		ResultSet rset = stmt
				.executeQuery("select CodigoCliente,NombreCliente,ApellidoContacto,Telefono from clientes");

		salida.println("<h1>Clientes Empresa</h1>");
		salida.println("<tr class=\"table-success\"><th>" + "Codigo" + "</th><th>" + "Nombre" + "</th><th>" + "Apellido"
				+ "</th><th>" + "Teléfono" + "</th></tr>");
		while (rset.next()) {

			salida.println("<tr><td class=\"table-info\">" + rset.getString(1) + "</td><td>" + rset.getString(2)
					+ "</td><td>" + rset.getString(3) + "<td>" + rset.getString(4) + "</td></tr>");
		}

		salida.println("</table>");

		salida.println("</body></html>");

		salida.close();
		System.out
				.println("Página web creada, ve al directorio " + directorio + " y ejecuta el archivo html para verla");
	}
}
