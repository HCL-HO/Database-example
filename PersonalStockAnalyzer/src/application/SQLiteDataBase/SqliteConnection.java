package application.SQLiteDataBase;

import java.sql.*;
public class SqliteConnection {

	public static Connection connector(){
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:InvestmentDb.sqlite");
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();			
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	

}
