package application;

import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class LaboController implements Initializable{

	
	private Connection cn = null;
	
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	private ObservableList<tableLabo> data;
	
    @FXML
    private TableColumn<?, ?> testprix;

    @FXML
    private TableView<tableLabo> tableLabo;

    @FXML
    private TableColumn<?, ?> testid;

    @FXML
    private TableColumn<?, ?> testname;

    @FXML
    private TableColumn<?, ?> testpid;

    @FXML
    private TextField test;

    @FXML
    private TextField prix;

    @FXML
    private TextField pid;

    
    
    @Override
    public void initialize (URL url, ResourceBundle rb) {
    	cn = SQLConnection.getDBConnection();
    	data = FXCollections.observableArrayList();
    	
    	tableLabo.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> 
    	{
    	    if (newVal != null) {
    	        pid.setText(newVal.getPid()+"");
    	        test.setText(newVal.getTestname()+"");
    	        prix.setText(newVal.getTestprix()+"");
    	    }
    	});
    	setCellTable();
    	loadDataFromDatabase();
    }

	private void setCellTable () {
	     testpid.setCellValueFactory (new PropertyValueFactory<>("pid"));
	     testname.setCellValueFactory (new PropertyValueFactory<> ("testname"));
	     testprix.setCellValueFactory (new PropertyValueFactory<> ("testprix"));
	}
	public void loadDataFromDatabase (){
		data.clear();
	    try {
	        pst = cn.prepareStatement("Select * from laboratoire");
	        rs = pst.executeQuery ();
	        while (rs.next())
	            data.add(new tableLabo(rs.getInt("pid"), rs.getString("testnom"), rs.getInt("testprix")));
	    }
	    catch(Exception e) {
	    	System.out.print(e.getMessage());
	    }
	    
	    tableLabo.setItems(data);
}
	
	// delete test
	
    @FXML
    void deleteLabo(ActionEvent event) {
    	int index=tableLabo.getSelectionModel().getSelectedIndex();
    	System.out.println(index);
    	if(index<0) {
    		Alert erreur = new Alert(AlertType.ERROR);
    		erreur.setTitle("Erreur");
    		erreur.setHeaderText("Vous devez selectionner le test.");
    		erreur.showAndWait();
    	}
    	else {
        	Alert dialog = new Alert(AlertType.CONFIRMATION);
    		dialog.setTitle("Supprimer Test");
    		dialog.setHeaderText("Merci de confimer");
    		dialog.setContentText("Voulez-vous supprimer ce Test?");
    		Optional<ButtonType> result = dialog.showAndWait();
    		
    		if(result.get()==ButtonType.OK) {
    	    	
    	    	if(index>=0) {
    	    		
    	    		tableLabo p=tableLabo.getSelectionModel().getSelectedItem();
    	    		delete(p.getPid());
    	    		data.remove(index);
    	    	}
    		}
    	}

    }
    
    public void delete(int id) {
		try {
			Connection connection = SQLConnection.getDBConnection();
			String sql="DELETE FROM laboratoire WHERE pid = ?"; 
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1, id);

			ps.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    

    @FXML
    void ajouterTest(ActionEvent event) {
    	
    	if (pid.getText().isEmpty() | test.getText().isEmpty() | prix.getText().isEmpty()) {
    		Alert erreur = new Alert(AlertType.ERROR);
    		erreur.setTitle("Erreur");
    		erreur.setHeaderText("Vous devez remplir les champs.");
    		erreur.showAndWait();
    	}
    	else {
        	int id =Integer.valueOf(pid.getText());
        	String testname = test.getText();
        	int testprix = Integer.valueOf(prix.getText());
        	if (checkPatientID(id)) {
            	tableLabo p=new tableLabo(id, testname, testprix);
            	
            	insert(p);
        	}

    	}

    }
    
    
    // ajout test
    
    public void insert(tableLabo p) {
		try {
			
			Connection connection = SQLConnection.getDBConnection();
			
			String sql="INSERT INTO laboratoire (pid, testnom, testprix) VALUES (?,?,?)"; 
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1, p.getPid());
			ps.setString(2, p.getTestname());
			ps.setInt(3, p.getTestprix());
			ps.execute();
			
			loadDataFromDatabase();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    
    public boolean checkPatientID(int id) {
        try {
        	boolean x = false;
            pst = cn.prepareStatement("Select * from laboratoire");
            rs = pst.executeQuery ();
            while (rs.next()) {
            	 if(rs.getInt("pid") == id) {
            		 x = true;
            	 }
            }
            if (!x) {
        		Alert erreur = new Alert(AlertType.ERROR);
        		erreur.setTitle("Erreur");
        		erreur.setHeaderText("Ce patient ID n'existe pas.");
        		erreur.showAndWait();
        		return false;
            }
            return true;
              
        }
        catch(Exception e) {
        	System.out.print(e.getMessage());
        	return false;
        }
    }

    
}
