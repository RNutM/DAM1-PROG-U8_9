package gestionPedidosIntGraf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccesoDatos {
	/**
	 * Gestión pedidos interfaz gráfica - Acceso a la Base de datos
	 * 
	 * @author Robert G
	 * 
	 */
	public static Connection conn;
	public static Statement stmt;
	public static ResultSet rset;

	public static Connection ConectarBD(String driver, String usuario, String pass) throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		conn = DriverManager.getConnection(driver, usuario, pass);
		return conn;
	}

	public static ResultSet ConsultaBD(String consulta) throws SQLException {
		stmt = ConectarBD("jdbc:oracle:thin:@localhost:1521:XE", "jardineria", "jardineria").createStatement();
		ResultSet rset = stmt.executeQuery(consulta);
		return rset;
	}

	public static void CerrarConexion() throws SQLException {
		stmt.close();
	}
}
