package application.SQLiteDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountTable {
public static Connection conn;
	public AccountTable(){
		conn = SqliteConnection.connector();
		if(conn == null){System.exit(1);}
		createTable();
	}
	private void createTable() {
		try {
			Statement state= conn.createStatement();
			ResultSet rs = state.executeQuery("SELECT name FROM sqlite_master where type='table' AND name ='UserTable'");
			if(rs.next()){
			rs.close();
			return;}
			// Create table if not exists
			String createSQL = "CREATE TABLE UserTable(id integer primary key autoincrement, "
					+ "userName text not null unique, password text not null);";
			state.executeUpdate(createSQL);
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Table already exists");
		}
	}
	public static boolean addNewAccount(String name, String password) throws SQLException{
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery("SELECT * FROM UserTable WHERE userName ="+"'"+name+"'");
			if(rs.next()){
				System.out.println(rs.next());
				rs.close();
				state.close();
				return false;
			} 
			rs.close();
			state.close();
			System.out.println(password);
			PreparedStatement prep = conn.prepareStatement("INSERT INTO UserTable values(?,?,?);");
			prep.setString(2, name);
			prep.setString(3, password);
			prep.execute();
			new InvestmentTable().createNewInvestmentTable(name);
			return true;
	}
	public static boolean validateAccount(String name , String password){
		try {
			Statement state = conn.createStatement();
			String sql ="SELECT * FROM UserTable WHERE userName= "+"'"+name+"'"
						+ "AND password= "+"'"+password+"'";
			ResultSet rs = state.executeQuery(sql);
			if(rs.next()){
				conn.close();
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
