package application.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Node;
import application.Main;
import application.SQLiteDataBase.InvestmentTable;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

public class InvestmentItemEditController implements Initializable{
	@FXML
	private TextField dateT;
	@FXML
	private TextField numT;
	@FXML
	private TextField nameT;
	@FXML
	private TextField bPriceT;
	@FXML
	private TextField sPriceT;
	@FXML
	private TextField amountT;

	@FXML
	private Label titleLB;
	private boolean isNew;
	private Main main;
	private String date;
	private int id;
	private String name; 
	private int number;
	private int amount;
	private long bPrice;
	private String sPrice;
	private InvestmentTable iTable = new InvestmentTable();
	@FXML
	public void handleConfirm(ActionEvent event) {
		String number = numT.getText();
		String name = nameT.getText();
		String amount = amountT.getText();
		String bPrice = bPriceT.getText();
		String sPrice = sPriceT.getText();
		String date = dateT.getText();
		if((name==null && number==null) || amount == null || bPrice == null){
				showErrorMessage();
		} else {
			if(isNew){
			iTable.insertNewItem(date, number, name, Integer.parseInt(amount), Long.parseLong(bPrice), sPrice);
			StockPorfolioController.ListIsUpdated = true;
			((Node)event.getSource()).getScene().getWindow().hide();
			} else{
				((Node)event.getSource()).getScene().getWindow().hide();
				iTable.upDateInvestmentItem(id, date, number, name, bPrice, sPrice, amount);
				StockPorfolioController.ListIsUpdated = true;
			}
		}
	}

	@FXML
	public void handleCanlcel(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	public void setMain(Main main, boolean isNew, String name){
		this.main = main;
		this.isNew = isNew;
		System.out.println(isNew);
		if(isNew){titleLB.setText("New Investment");}
		else{titleLB.setText("Editing "+ name);
		dateT.setText(date);
		nameT.setText(name);
		numT.setText(String.valueOf((number)));
		amountT.setText(String.valueOf(amount));
		bPriceT.setText(String.valueOf(bPrice));
		sPriceT.setText(sPrice);}
	}
	public void setItemInfo(int id, String date, String name, int num, int amount, long bPrice, String sPrice){
		this.id = id;
		this.name = name;
		this.number = num;
		this.amount = amount;
		this.bPrice = bPrice;
		this.sPrice = sPrice;
		this.date=date;
	}
	private void showErrorMessage() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setTitle("Please check the following fields");
		alert.setContentText("Must complete either Name/Number"+"\n"
							+"Must complete Amount"+"\n"
							+"Must complete Brought Price");
		alert.show();
	}
}
