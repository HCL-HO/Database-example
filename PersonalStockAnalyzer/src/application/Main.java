package application;
	
import java.io.IOException;

import application.SQLiteDataBase.AccountTable;
import application.view.InvestmentItemEditController;
import application.view.StockPorfolioController;
import application.view.mainController;
import application.view.newAccountController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("view/main.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			new AccountTable();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void showMain(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/main.fxml"));
			Parent root = loader.load();
			
			mainController control = loader.getController();
			control.setMain(this);
			
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showNewAccountPage() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/newAccount.fxml"));
			Parent root = loader.load();
			
			newAccountController control = loader.getController();
			control.setMain(this);
			
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}

	public void showInvestment() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/StockPorfolio.fxml"));
			Parent root = loader.load();
			
			StockPorfolioController control = loader.getController();
			control.setMain(this);
			
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showEditItemDialog(boolean isNew, int id, String name, int num, int amount, long bPrice, String sPrice, String date) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/InvestmentItemEdit.fxml"));
			Parent root = loader.load();
			
			InvestmentItemEditController control = loader.getController();
			if(!isNew){
				control.setItemInfo(id, date, name, num, amount, bPrice, sPrice);
			}
			control.setMain(this, isNew, name);
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void showStat(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/statistics.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
