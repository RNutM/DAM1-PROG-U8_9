package Varios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Access {
	/**
	 * Acceso a BD probando consultas
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

	public static void main(String[] args) throws SQLException {

		DB_Access db = new DB_Access();

		db.conectar();

		System.out.println("Primera consulta");
		System.out.println("****************");

		ResultSet rset = stmt.executeQuery("select CodigoCliente,NombreCliente from clientes");
		while (rset.next())
			System.out.println("Codigo: " + rset.getString(1) + "||" + "Nombre: " + rset.getString(2));
		System.out.println();
		System.out.println("Segunda consulta");
		System.out.println("****************");

		ResultSet rset2 = stmt.executeQuery("select Ciudad,Telefono from oficinas where pais ='EEUU'");
		while (rset2.next())

			System.out.println("Ciudad: " + rset2.getString(1) + "||" + "Telefono: " + rset2.getString(2));
		System.out.println();
		System.out.println("Tercera consulta");
		System.out.println("****************");

		ResultSet rset3 = stmt.executeQuery(
				"select Nombre, Apellido1||' '||Apellido2 as Apellidos, Email from empleados where codigojefe like '3'");
		while (rset3.next())

			System.out.println("Nombre: " + rset3.getString(1) + "||" + "Apellidos: " + rset3.getString(2) + "||"
					+ "Email: " + rset3.getString(3));
		System.out.println();
		System.out.println("Cuarta consulta");
		System.out.println("****************");

		ResultSet rset4 = stmt.executeQuery("select nombrecliente,pais from clientes where pais in('España','Spain') ");
		while (rset4.next())

			System.out.println("Nombre: " + rset4.getString(1) + "||" + "País: " + rset4.getString(2));

		stmt.close();
	}
}
