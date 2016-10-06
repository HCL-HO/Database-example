package application.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.Node;

public class statisticsController implements Initializable{
	@FXML
	private Label maxValueHold;
	@FXML
	private Label numOfHolding;
	@FXML
	private Label maxValueHolding;
	@FXML
	private Label netProfit;
	@FXML
	private Label maxGain;
	@FXML
	private Label maxLoss;

	@FXML
	public void onBack(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}
