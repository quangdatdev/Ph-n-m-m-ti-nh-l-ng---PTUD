package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	private static ConnectDB instance = new ConnectDB();
	private Connection con;

	private ConnectDB() {
		String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=QLLuongSPC;user=sa;password=sapassword";
		try {
			con = DriverManager.getConnection(dbURL);
			con.setAutoCommit(false);
			System.out.println("da ket noi csdl");
		} catch (SQLException e) {
			System.out.println("ket noi khong thanh cong");
			e.printStackTrace();
		}
	}

	public synchronized static ConnectDB getDataBase() {
		if (instance == null)
			instance = new ConnectDB();
		return instance;
	}

	public Connection getConnection() {
		return con;
	}

	public void disconnect() throws SQLException {
		con.close();
	}

	public static void main(String[] args) {
		Connection con = ConnectDB.getDataBase().getConnection();

	}
}
