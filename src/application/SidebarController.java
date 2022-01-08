package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SidebarController implements Initializable{
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}
	
	@FXML
	private BorderPane bp;
	
	Stage stage;

    @FXML
    void ui1(ActionEvent event) {
    	loadUI("Laboratoire");
    }

    @FXML
    void ui2(ActionEvent event) {
    	loadUI("GestionPatients");
    }

    @FXML
    void ui3(ActionEvent event) {

    }

    @FXML
    void ui4(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {
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
    
    private void loadUI(String ui) {
    	Parent root = null;
    	try {
           root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
    	}
    	catch(IOException ex) {
    		System.out.println(ex.getMessage());
    	}
    	bp.setCenter(root);
    	
    }

}

