package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Controller {

    @FXML
    void seConnecter(ActionEvent even) throws Exception {
    	System.out.println("it works!");
        ((Node)even.getSource()).getScene().getWindow().hide();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("dash.fxml"));
        primaryStage.setTitle("Hospital Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();

    }
    
    Stage stage;
	
	public void logout(ActionEvent event) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setHeaderText("You're about to close your program!");
		alert.setContentText("Are you sure you want to quit your application?");
		
		if(alert.showAndWait().get() == ButtonType.OK){
			stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			System.out.println("You successfully logged out!");
			stage.close();
		}
		
	}
}
