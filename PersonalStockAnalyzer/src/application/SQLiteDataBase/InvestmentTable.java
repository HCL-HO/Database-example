package application.SQLiteDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InvestmentTable {

	public static Connection conn;
	public static String userName;
	private static final String InvestmentTableName = "Personal_Investment_Table_";
	public static boolean showStillHoldingOnly = false;
	public InvestmentTable(){
		conn= SqliteConnection.connector();
	}
	public void createNewInvestmentTable(String name) {
		try {
			Statement st = conn.createStatement();
			String sql = "CREATE TABLE "+InvestmentTableName+name
					+"(id integer primary key autoincrement,"
					+"date text not null,"
					+ "stock_name text not null," 
					+"stock_number integer not null,"
					+" brought_price long not null,"
					+" sold_price text not null,"
					+" amount integer not null,"
					+" net_profit text no null);";
			st.execute(sql);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void insertNewItem(String date, String number, String name, int amount, long bPrice,
			String sPrice) {
		try {
			String netProfit;
			PreparedStatement pst = conn.prepareStatement("INSERT INTO "+InvestmentTableName+userName
					+" values(?,?,?,?,?,?,?,?)");
			if(date.isEmpty()){
				Calendar cal = Calendar.getInstance();
				DateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
				date = formater.format(cal.getTime());
			}
			if(sPrice.isEmpty()){
				sPrice = "Holding";
				netProfit = "Holding";
			} else {
				long profit = (Long.parseLong(sPrice)-bPrice)*amount;
				netProfit = String.valueOf(profit);
			}
			pst.setString(2, date);
			pst.setString(3, name);
			if(!number.isEmpty()){
			pst.setInt(4, Integer.parseInt(number));}
			pst.setLong(5, bPrice);
			pst.setString(6, sPrice);
			pst.setInt(7, amount);
			pst.setString(8, netProfit);
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	public ObservableList<Investment> getInvestmentTable() throws SQLException {
		
		try {
			ObservableList<Investment> list = FXCollections.observableArrayList();
			Statement st = conn.createStatement();
			String sql = "SELECT * FROM "+InvestmentTableName+userName;
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()){
				Investment item = new Investment();
				int id = rs.getInt(1);
				String date = rs.getString(2);
				String name = rs.getString(3);
				int num = rs.getInt(4);
				long bPrice = rs.getLong(5);
				String sPrice = rs.getString(6);
				int amount = rs.getInt(7);
				String netProfit = rs.getString(8);
				
				item.setSoldPrice(sPrice);
				item.setId(id);
				item.setDate(date);
				item.setStockName(name);
				item.setStockNumber(num);
				item.setBroughtPrice(bPrice);
				item.setAmount(amount);
				item.setNetProfit(netProfit);
				//filtering stock still on hold
				if(showStillHoldingOnly){
					if(sPrice.contains("Holding")){
						list.add(item);
					} 
				}else{
					list.add(item);
				}
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}

	public void upDateInvestmentItem(int id, String date, String number, String name, String bPrice, String sPrice,
			String amount) {
		try {
			PreparedStatement pst = conn.prepareStatement("UPDATE "+InvestmentTableName+ userName
					+ " SET date = ?, stock_name=?, stock_number=?, brought_price=?, sold_price=?, amount=?, net_profit=?"
					+ "WHERE id="+ id);
			
			if(!date.isEmpty()){pst.setString(1, date);};
			pst.setString(2, name);
			pst.setInt(3, Integer.parseInt(number));
			pst.setLong(4, Long.parseLong(bPrice));
			pst.setInt(6, Integer.parseInt(amount));
			String netProfit;
			if(!sPrice.isEmpty()&& !sPrice.contains("Holding")){
				netProfit = String.valueOf((Long.parseLong(sPrice)-Long.parseLong(bPrice))*Integer.parseInt(amount));
			} else {netProfit = "Holding";}
			pst.setString(5, sPrice);
			pst.setString(7, netProfit);
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteItem(int id){
		try {
			Statement st = conn.createStatement();
			st.execute("DELETE FROM "+InvestmentTableName+userName+" WHERE id="+ id);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//MANAGING STATISTICS
	public List<String> maxNumberHolding(){
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT stock_nubmer FROM "+InvestmentTableName+userName);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
}
