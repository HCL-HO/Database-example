package application.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Main;
import application.SQLiteDataBase.Investment;
import application.SQLiteDataBase.InvestmentTable;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class StockPorfolioController implements Initializable{
	@FXML
	private TableView<Investment> stockTable;
	@FXML
	private TableColumn<Investment, String> dateCol;
	@FXML
	private TableColumn<Investment, String> nameCol;
	@FXML
	private TableColumn<Investment, Number> numCol;
	@FXML
	private TableColumn<Investment, Number> bPriceCol;
	@FXML
	private TableColumn<Investment, String> sPriceCol;
	@FXML
	private TableColumn<Investment, Number> amountCol;
	@FXML
	private TableColumn<Investment, String> profitCol;
	@FXML
	private TableColumn<Investment, String> curPriceCol;
	@FXML
	private Button holdingOnlyBtn;
	private InvestmentTable investTable = new InvestmentTable();
	private Main main;
	public static boolean ListIsUpdated = false;
	// Event Listener on Button.onAction
	@FXML
	public void handleNew(ActionEvent event) {
		main.showEditItemDialog(true, 0,null, 0, 0, 0, null, null);
	}
	@FXML
	public void handleEdit(ActionEvent event) {
		Investment item = (Investment) stockTable.getSelectionModel().getSelectedItem();
		if(item!=null){
			int id = item.getId();
			String date = item.getDate();
			String name = item.getStockName();
			int num = item.getStockNum();
			int amount = item.getAmount();
			long bPrice = item.getBroughtPrice();
			String sPrice = item.getSoldPrice();
			
			main.showEditItemDialog(false,id, name, num, amount, bPrice, sPrice, date);
		} else {
			showNotSelectedError();
		}
	}
	@FXML
	public void handleDelete(ActionEvent e){
		Investment item = stockTable.getSelectionModel().getSelectedItem();
		investTable.deleteItem(item.getId());
		ListIsUpdated= true;
	}

	@FXML
	public void handleShowHolding(ActionEvent event) {
		if(InvestmentTable.showStillHoldingOnly ==false){
		InvestmentTable.showStillHoldingOnly = true;
		holdingOnlyBtn.setText("Show All");
		}else{
		InvestmentTable.showStillHoldingOnly = false;
		holdingOnlyBtn.setText("Holding Only");
		}
		ListIsUpdated = true;
	}
	@FXML
	public void handleShowGraphics(ActionEvent event) {
		main.showStat();
	}
	public void setMain(Main main) {
		this.main= main;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			stockTable.setItems(investTable.getInvestmentTable());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		new updateThread().start();
		dateCol.setCellValueFactory(cellData -> cellData.getValue().getDateProperty());
		nameCol.setCellValueFactory(cellData -> cellData.getValue().getStockNameProperty());
		numCol.setCellValueFactory(cellData -> cellData.getValue().getStockNumberProperty());
		bPriceCol.setCellValueFactory(cellData -> cellData.getValue().getBroughtPriceProperty());
		sPriceCol.setCellValueFactory(cellData -> cellData.getValue().getSoldPriceProeprty());
		amountCol.setCellValueFactory(cellData -> cellData.getValue().getAmountProperty());
		profitCol.setCellValueFactory(cellData -> cellData.getValue().getNetProfitProperty());
	}
	private void showNotSelectedError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setContentText("Please selected the item you want to edit");
		alert.show();
	}
	
	private class updateThread extends Thread{
		@Override 
		public void run(){
		while(true){
			if(ListIsUpdated == true){
			Platform.runLater(()->{
			try {
				stockTable.setItems(investTable.getInvestmentTable());
				System.out.println("updated");
				ListIsUpdated = false;
			} catch (Exception e) {
				ListIsUpdated = false;
				e.printStackTrace();
			}
				});
			}
		}
	}
}
}

