package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import application.Main;
import application.SQLiteDataBase.AccountTable;
import application.SQLiteDataBase.InvestmentTable;
import javafx.event.ActionEvent;
import javafx.scene.Node;
public class mainController {
	private Main main = new Main();
	@FXML
	private TextField nameT;
	@FXML
	private TextField passT;

	@FXML
	public void handleLogIn(ActionEvent event) {
		if(AccountTable.validateAccount(nameT.getText(), passT.getText())){
			((Node)event.getSource()).getScene().getWindow().hide();
			InvestmentTable.userName = nameT.getText();
		main.showInvestment();
		} else {
			showUnsuccessfulAlert(nameT.getText());
		}
	}
	@FXML
	public void handleNew(ActionEvent event) {
		main.showNewAccountPage();
	}
	public void setMain(Main main){
		this.main = main;
	}
	private void showUnsuccessfulAlert(String text) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Login Failed");
		alert.setContentText("Cannot found your account");
		alert.show();
	}
}
