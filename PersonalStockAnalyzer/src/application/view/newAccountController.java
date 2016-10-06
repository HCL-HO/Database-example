package application.view;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

import java.sql.SQLException;

import application.Main;
import application.SQLiteDataBase.AccountTable;
import javafx.event.ActionEvent;

public class newAccountController {
	@FXML
	private TextField nameT;
	@FXML
	private TextField passT1;
	@FXML
	private TextField passT2;
	private Main main;
	
	@FXML
	public void handleConfirm(ActionEvent event) throws SQLException {
		String name = nameT.getText();
		String password = passT1.getText();
		String password2 = passT2.getText();
		if(name != null && password!=null && password2!=null && (password.equals(password2))){
			boolean isSuccess = AccountTable.addNewAccount(name, password);
			if(isSuccess){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Account Registered");
				alert.setContentText("Registered successfully");
				alert.show();
				backToMainPage(event);
			} else {
				showUnsccessfulAlert(name);
			}
		} else {
			showWrongEntryError();
		}
	}

	@FXML
	public void handleCancel(ActionEvent event) {
		backToMainPage(event);
	}
	public void setMain(Main main){
		this.main = main;
	}
	private void showWrongEntryError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("PLease check your inputs");
		alert.setContentText("Account name cannot be null"+"\n"
							+"Password cannot be null"+"\n"
							+"Must Enter the same Password"+"\n");
		alert.show();
	}
	private void showUnsccessfulAlert(String name) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Please Try Other Names");
		alert.setContentText("Account name has been used");
		alert.show();		
	}

	private void backToMainPage(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();	
	}

}
