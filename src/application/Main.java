package application;
	
// import java.sql.Connection;
// import java.sql.PreparedStatement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
	        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginpage.fxml"));
	        Scene scene = new Scene(fxmlLoader.load());
	        primaryStage.setTitle("Gestion de Cabinet Medical");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	        primaryStage.setResizable(false);
	        //Connection cn = SQLConnection.getDBConnection();
			//String sql="INSERT INTO Patient (nom, prenom) VALUES ('Mohamed', 'Oualid')"; 
			//PreparedStatement ps=cn.prepareStatement(sql);

			//ps.execute();
	        
	        primaryStage.setOnCloseRequest(event -> {
				event.consume();
				logout(primaryStage);	
			});
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void logout(Stage stage){	
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You're about to logout!");
		alert.setContentText("Do you want to save before exiting?");
		
		if (alert.showAndWait().get() == ButtonType.OK){
			System.out.println("You successfully logged out");
			stage.close();
		} 
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
