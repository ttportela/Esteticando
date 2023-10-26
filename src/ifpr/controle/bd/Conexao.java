package ifpr.controle.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private static Conexao instancia = null;
	
	private Connection con;
	
	private Conexao() {
		con = conectar();
	}
	
	public static Conexao getInstancia() {
		if (instancia == null) {
			instancia = new Conexao();
		}
		return instancia;
	}
	
	private String url = "jdbc:postgresql://localhost:5432/estetica";
	private String user = "postgres";
	private String pass = "postgres";
	
	private Connection conectar() {
		try {
			return DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Connection getCon() {
		return con;
	}

}
