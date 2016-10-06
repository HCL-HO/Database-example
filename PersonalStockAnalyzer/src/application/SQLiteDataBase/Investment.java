package application.SQLiteDataBase;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Investment {
	private StringProperty date;
	private IntegerProperty stockNumber;
	private StringProperty stockName;
	private LongProperty broughtPrice;
	private StringProperty soldPrice;
	private IntegerProperty amount;
	private StringProperty netProfit;
	private int id;
	
	public Investment(){
	this.date = new SimpleStringProperty();
	this.stockName = new SimpleStringProperty();
	this.stockNumber = new SimpleIntegerProperty();
	this.broughtPrice = new SimpleLongProperty();
	this.soldPrice = new SimpleStringProperty();
	this.amount = new SimpleIntegerProperty();
	this.netProfit = new SimpleStringProperty();
}

	public StringProperty getDateProperty() {
		return date;
	}
	public String getDate(){
		return date.get();
	}
	public void setDate(String date) {
		this.date.set(date);
	}

	public IntegerProperty getStockNumberProperty() {
		return stockNumber;
	}
	public int getStockNum(){
		return stockNumber.get();
	}
	public void setStockNumber(int stockNumber) {
		this.stockNumber.set(stockNumber);;
	}

	public StringProperty getStockNameProperty() {
		return stockName;
	}
	public String getStockName(){
		return stockName.get();
	}
	public void setStockName(String stockName) {
		this.stockName.set(stockName);;
	}

	public LongProperty getBroughtPriceProperty() {
		return broughtPrice;
	}
	public Long getBroughtPrice(){
		return broughtPrice.get();
	}
	public void setBroughtPrice(long broughtPrice) {
		this.broughtPrice.set(broughtPrice);;
	}

	public StringProperty getSoldPriceProeprty() {
		return soldPrice;
	}
	public String getSoldPrice(){
		return soldPrice.get();
	}
	public void setSoldPrice(String soldPrice) {
		this.soldPrice.set(soldPrice);;
	}

	public IntegerProperty getAmountProperty() {
		return amount;
	}
	public int getAmount(){
		return amount.get();
	}
	public void setAmount(int amount) {
		this.amount.set(amount);;
	}

	public StringProperty getNetProfitProperty() {
		return netProfit;
	}
	public String getNetProfit(){
		return netProfit.get();
	}
	public void setNetProfit(String netProfit2) {
		this.netProfit.set(netProfit2);;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
