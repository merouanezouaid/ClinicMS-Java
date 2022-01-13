package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class DocteurController implements Initializable{
	
	private Connection cn = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	
	private ObservableList<tableDocteur> dataDocteur;


    @FXML
    private TableColumn<?, ?> docid;

    @FXML
    private TableColumn<?, ?> docgrade;

    @FXML
    private TableColumn<?, ?> docdep;

    @FXML
    private TableColumn<?, ?> docprenom;

    @FXML
    private TableColumn<?, ?> docnom;

    @FXML
    private TableView<tableDocteur> tableDocteur;

    @FXML
    private TableColumn<?, ?> doctel;

    @FXML
    private TableColumn<?, ?> docdate;

    @FXML
    private TableColumn<?, ?> doccin;

    @FXML
    private TableColumn<?, ?> docemail;

    @FXML
    private TableColumn<?, ?> docspe;
    
    @FXML
    private TextField demailtext;

    @FXML
    private DatePicker tdatetext;

    @FXML
    private TextField didtext;
    
    @FXML
    private TextField dgradetext;

    @FXML
    private TextField ddeptext;


    @FXML
    private TextField dteltext;
    
    @FXML
    private TextField searchdoc;

    @FXML
    private TextField dprenomtext;

    @FXML
    private TextField dnomtext;

    @FXML
    private TextField dspetext;

    @FXML
    private TextField dcintext;
    
    
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	cn = SQLConnection.getDBConnection();
    	dataDocteur = FXCollections.observableArrayList();
    	tableDocteur.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> 
    	{
    	    if (newVal != null) {
    	        didtext.setText(newVal.getDocid()+"");
    	        dnomtext.setText(newVal.getDocnom());
    	        dprenomtext.setText(newVal.getDocprenom());
    	        dcintext.setText(newVal.getDoccin());
    	        demailtext.setText(newVal.getDocemail());
    	        dteltext.setText(newVal.getDoctel());
    	        dspetext.setText(newVal.getDocspe());
    	        ddeptext.setText(newVal.getDocdep());
    	        tdatetext.setValue(LocalDate.parse(newVal.getDocdate()));
    	        dgradetext.setText(newVal.getDocgrade());
    	    }
    	});
    	setCellTable();
    	loadDocteurs();	
    	
	}

	private void setCellTable () {
	     docid.setCellValueFactory (new PropertyValueFactory<> ("docid"));
	     doccin.setCellValueFactory (new PropertyValueFactory<>("doccin"));
	     docnom.setCellValueFactory (new PropertyValueFactory<> ("docnom"));
	     docprenom.setCellValueFactory (new PropertyValueFactory<> ("docprenom"));
	     doctel.setCellValueFactory (new PropertyValueFactory<> ("doctel"));
	     docemail.setCellValueFactory (new PropertyValueFactory<> ("docemail"));
	     docgrade.setCellValueFactory (new PropertyValueFactory<> ("docgrade"));
	     docdep.setCellValueFactory (new PropertyValueFactory<> ("docdep"));
	     docspe.setCellValueFactory (new PropertyValueFactory<> ("docspe"));
	     docdate.setCellValueFactory (new PropertyValueFactory<> ("docdate"));
	}
	
	
	private void loadDocteurs (){
		dataDocteur.clear();
	    try {
	        pst = cn.prepareStatement("Select * from Docteur");
	        rs = pst.executeQuery ();
	        while (rs.next())
	            dataDocteur.add(new tableDocteur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10), rs.getString(11)));
	    }
	    catch(Exception e) {
	    	System.out.print(e.getMessage());
	    }
	    
	    tableDocteur.setItems(dataDocteur);
	    
    	FilteredList<tableDocteur> fddoc = new FilteredList<>(dataDocteur, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        searchdoc.textProperty().addListener((observable, oldValue, newValue) -> {
            fddoc.setPredicate(tableDocteur -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name field in your object with filter.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(tableDocteur.getDocid()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                    // Filter matches first name.
                } 
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<tableDocteur> sortedDataDoc = new SortedList<>(fddoc);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedDataDoc.comparatorProperty().bind(tableDocteur.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        tableDocteur.setItems(sortedDataDoc);
}

	// delete docteur
	
	@FXML
    void supprimerDocteur(ActionEvent event) {
    	int index=tableDocteur.getSelectionModel().getSelectedIndex();
    	// System.out.println(index);
    	if(index<0) {
    		Alert erreur = new Alert(AlertType.ERROR);
    		erreur.setTitle("Erreur");
    		erreur.setHeaderText("Vous devez selectionner le docteur a supprimer.");
    		erreur.showAndWait();
    	}
    	else {
        	Alert dialog = new Alert(AlertType.CONFIRMATION);
    		dialog.setTitle("Supprimer Docteur");
    		dialog.setHeaderText("Merci de confimer");
    		dialog.setContentText("Voulez-vous supprimer ce Docteur?");
    		Optional<ButtonType> result = dialog.showAndWait();
    		
    		if(result.get()==ButtonType.OK) {
    	    	    	    		
    	    		tableDocteur p=tableDocteur.getSelectionModel().getSelectedItem();
    	    		delete(p.getDocid());
    	    		dataDocteur.remove(index);

    	    	    	    	
    		}
    	}

    }
    
    public void delete(int id) {
		try {
			Connection connection = SQLConnection.getDBConnection();
			String sql="DELETE FROM Docteur WHERE D_id = ?"; 
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1, id);
			System.out.println(id);
			
			ps.execute();
			
			System.out.println(id+" 1");

			
	    	searchdoc.setText("");
			loadDocteurs();



		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    
    //modifier Docteur
    
    @FXML
    void modifierDocteur(ActionEvent event) throws SQLException {
    	
    	if (dcintext.getText().isEmpty() | dnomtext.getText().isEmpty() | dprenomtext.getText().isEmpty()
    			| dteltext.getText().isEmpty() | demailtext.getText().isEmpty() |
    		dgradetext.getText().isEmpty() | dspetext.getText().isEmpty() |
    			 ddeptext.getText().isEmpty() | tdatetext.getValue().toString().isEmpty() ) {
    		Alert erreur = new Alert(AlertType.ERROR);
    		erreur.setTitle("Erreur");
    		erreur.setHeaderText("Vous devez remplir les champs.");
    		erreur.showAndWait();
    	}
    	else {
    		int docid = Integer.valueOf(didtext.getText());
        	String doccin = dcintext.getText();
        	String docnom = dnomtext.getText();
        	String docprenom = dprenomtext.getText();
        	String doctel = dteltext.getText();
        	String docemail = demailtext.getText();
        	String docgrade = dgradetext.getText();
        	String docspe = dspetext.getText();
        	String docdep = ddeptext.getText();
        	String docdate = tdatetext.getValue().toString();
        	if (checkDocteurCIN(docid ,doccin)== true) {
            	tableDocteur p=new tableDocteur(docid, doccin, docnom, docprenom,
            			doctel,docemail ,docgrade,docspe, docdep, docdate);
            	
            	edit(p);
        	}

    	}

    }
    
    
    public void edit(tableDocteur p) {
		try {
			
			Connection connection = SQLConnection.getDBConnection();
			
			String sql="UPDATE Docteur set D_Cin=? ,D_Nom=?,D_Prenom=?,D_Telephone=?,D_Email=?,Grade=? ,Departement=?, Specialite=?, DateEmbauche=? WHERE D_id = ?"; 
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, p.getDoccin());
			ps.setString(2, p.getDocnom());
			ps.setString(3, p.getDocprenom());
			ps.setString(4, p.getDoctel());
			ps.setString(5, p.getDocemail());
			ps.setString(6, p.getDocgrade());
			ps.setString(7, p.getDocdep());
			ps.setString(8, p.getDocspe());
			ps.setString(9, p.getDocdate());
			ps.setInt(10, p.getDocid());
			ps.execute();
			
	    	searchdoc.setText("");
			loadDocteurs();
			
		} catch (Exception e) {
    		Alert erreur = new Alert(AlertType.ERROR);
    		erreur.setTitle("Erreur");
    		erreur.setHeaderText("Veuillez verifier les informations saisies.");
    		erreur.showAndWait();
		}
	}
    
    
    public boolean checkDocteurCIN(int id, String cin) throws SQLException {
    	
        	int x = 0;
            pst = cn.prepareStatement("Select * from Docteur");
            rs = pst.executeQuery ();
            while (rs.next()) {
            	// System.out.println(rs.getString("cin").compareTo(cin));
            	 if(rs.getString("D_Cin").compareTo(cin) == 0) {
            		 if(rs.getInt("D_id") != id) {
                		 x = 1;
            		 }
            	 }
            }
            if (x == 1) {
        		Alert erreur = new Alert(AlertType.ERROR);
        		erreur.setTitle("Erreur");
        		erreur.setHeaderText("Ce docteur CIN existe deja.");
        		erreur.showAndWait();
        		return false;
            }
            
            return true;

    }
    
    public boolean checkDocteurCINajout(String cin) throws SQLException {
    	
    	int x = 0;
        pst = cn.prepareStatement("Select * from Docteur");
        rs = pst.executeQuery ();
        while (rs.next()) {
        	// System.out.println(rs.getString("cin").compareTo(cin));
        	 if(rs.getString("D_Cin").compareTo(cin) == 0) {
            		 x = 1;
        	 }
        }
        if (x == 1) {
    		Alert erreur = new Alert(AlertType.ERROR);
    		erreur.setTitle("Erreur");
    		erreur.setHeaderText("Ce docteur CIN existe deja.");
    		erreur.showAndWait();
    		return false;
        }
        
        return true;

}

    @FXML
    void ajouterDocteur(ActionEvent event) throws SQLException {
    	
    	if (dcintext.getText().isEmpty() | dnomtext.getText().isEmpty() | dprenomtext.getText().isEmpty()
    			| dteltext.getText().isEmpty() | demailtext.getText().isEmpty() |
    		dgradetext.getText().isEmpty() | dspetext.getText().isEmpty() |
    			 ddeptext.getText().isEmpty() | tdatetext.getValue() == null) {
    		Alert erreur = new Alert(AlertType.ERROR);
    		erreur.setTitle("Erreur");
    		erreur.setHeaderText("Vous devez remplir les champs.");
    		erreur.showAndWait();
    	}
    	else {
        	String doccin = dcintext.getText();
        	String docnom = dnomtext.getText();
        	String docprenom = dprenomtext.getText();
        	String doctel = dteltext.getText();
        	String docemail = demailtext.getText();
        	String docgrade = dgradetext.getText();
        	String docspe = dspetext.getText();
        	String docdep = ddeptext.getText();
        	String docdate = tdatetext.getValue().toString();
        	if (checkDocteurCINajout(doccin)== true) {
            	tableDocteur p=new tableDocteur(doccin, docnom, docprenom,
            			doctel,docemail ,docgrade,docspe, docdep, docdate);
            	
            	insert(p);
        	}


    	}

    }
    
    
    // ajout test
    
    public void insert(tableDocteur p) {
		try {
			
			Connection connection = SQLConnection.getDBConnection();
			
			String sql="INSERT INTO Docteur (D_Cin,D_Nom,D_Prenom,D_Telephone,D_Email,Grade, Departement, Specialite, DateEmbauche) VALUES (?,?,?,?,?,?,?,?,?)"; 
			
			
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, p.getDoccin());
			ps.setString(2, p.getDocnom());
			ps.setString(3, p.getDocprenom());
			ps.setString(4, p.getDoctel());
			ps.setString(5, p.getDocemail());
			ps.setString(6, p.getDocgrade());
			ps.setString(7, p.getDocdep());
			ps.setString(8, p.getDocspe());
			ps.setString(9, p.getDocdate());
			ps.execute();
			
    		Alert success = new Alert(AlertType.INFORMATION);
    		success.setTitle("Succes");
    		success.setHeaderText("Le Docteur est ajoute avec succes.");
    		success.showAndWait();
	    	
    		
    		searchdoc.setText("");
			loadDocteurs();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    

    
}
