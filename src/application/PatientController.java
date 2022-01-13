package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.transformation.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class PatientController implements Initializable {

	
	private Connection cn = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	private ObservableList<tablePatient> dataPatient;
	private ObservableList<tableAnt> dataAnt;
	private ObservableList<tableRDV> dataRDV;



    @FXML
    private RadioButton medical;
    @FXML
    private RadioButton familial;
    @FXML
    private RadioButton chirurgical;

    @FXML
    private DatePicker daterdvtext;
    
    @FXML
    private TextField heurerdvtext;
    
    @FXML
    private TextField motifrdvtext;
    
    @FXML
    private TextField comtext;
    
    @FXML
    private TextField anttext;
    
    @FXML
    private TextField searchpatient;

    @FXML
    private TextField searchant;
    
    @FXML
    private TextField searchrdv;
    
    @FXML
    private TableColumn<?, ?> motifrdv;

    @FXML
    private TableColumn<?, ?> pidrdv;
    
    @FXML
    private TableColumn<?, ?> daterdv;

    @FXML
    private TableColumn<?, ?> heurerdv;

    @FXML
    private TableView<tableRDV> tableRDV;

    @FXML
    private TableColumn<?, ?> com;

    @FXML
    private TableColumn<?, ?> ant;
    
    @FXML
    private TableColumn<?, ?> pidant;
    

    @FXML
    private TableColumn<?, ?> typeant;
    
    @FXML
    private TextField pteltext;

    @FXML
    private TextField pmuttext;

    @FXML
    private TextArea padrtext;

    @FXML
    private TextField pdatetext;
    
    @FXML
    private TextField pidtext2;

    @FXML
    private TextField pidtext3;
    
    @FXML
    private TextField pnomtext;

    @FXML
    private TextField psexetext;

    @FXML
    private TextField pidtext;

    @FXML
    private TextField pprenomtext;

    @FXML
    private TextField psittext;

    @FXML
    private TextField pcintext;
    
    @FXML
    private TableColumn<?, ?> pnais;

    @FXML
    private TableView<tablePatient> tablePatient;
    
    @FXML
    private TableView<tableAnt> tableAnt;

    @FXML
    private TableColumn<?, ?> pprenom;

    @FXML
    private TableColumn<?, ?> pnom;

    @FXML
    private TableColumn<?, ?> pmut;

    @FXML
    private TableColumn<?, ?> pid;

    @FXML
    private TableColumn<?, ?> psit;

    @FXML
    private TableColumn<?, ?> padr;

    @FXML
    private TableColumn<?, ?> pcin;

    @FXML
    private TableColumn<?, ?> psexe;

    @FXML
    private TableColumn<?, ?> ptel;
    
    @FXML
    private TableColumn<?, ?> pcre;

    @FXML
    private TableColumn<?, ?> pdoc;
    
    public String radioLabel;

    @Override
    public void initialize (URL url, ResourceBundle rb) {
    	cn = SQLConnection.getDBConnection();
    	dataPatient = FXCollections.observableArrayList();
    	dataAnt = FXCollections.observableArrayList();
    	dataRDV = FXCollections.observableArrayList();
    	addTextLimiter(motifrdvtext, 10);
    	addTextLimiter(heurerdvtext, 5);
    	// fill the fields with data
    	tablePatient.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> 
    	{
    	    if (newVal != null) {
    	        pidtext.setText(newVal.getIdP()+"");
    	        pnomtext.setText(newVal.getNomP());
    	        pprenomtext.setText(newVal.getPrenomP());
    	        psexetext.setText(newVal.getSexeP());
    	        pcintext.setText(newVal.getCINP());
    	        pdatetext.setText(newVal.getDateNP());
    	        pteltext.setText(newVal.getTelephoneP());
    	        psittext.setText(newVal.getSituationP());
    	        pmuttext.setText(newVal.getMutuelleP());
    	        padrtext.setText(newVal.getAdresseP());
    	    }
    	});
    	
    	tableAnt.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> 
    	{
    	    if (newVal != null) {
    	        pidtext2.setText(newVal.getPid()+"");
    	    }
    	});
    	
    	@SuppressWarnings("unused")
		ToggleGroup tgl = new ToggleGroup();
    	
    	tableRDV.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> 
    	{
    	    if (newVal != null) {
    	        pidtext3.setText(newVal.getPidrdv()+"");
    	    }
    	});
    	
    	
    	familial.setOnAction(e ->{
    		radioLabel = familial.getText();
    		System.out.println(radioLabel);
    	});
    	
    	medical.setOnAction(e ->{
    		radioLabel = medical.getText();
    		System.out.println(radioLabel);

    	});
    	
    	chirurgical.setOnAction(e ->{
    		radioLabel = chirurgical.getText();
    		System.out.println(radioLabel);

    	});
    	
    	setCellTable();
    	loadPatients();
    	loadAntecedents();
    	loadRDV();   	      
    }

	private void setCellTable () {
	     pid.setCellValueFactory (new PropertyValueFactory<> ("idP"));
	     pnom.setCellValueFactory (new PropertyValueFactory<>("nomP"));
	     pprenom.setCellValueFactory (new PropertyValueFactory<> ("PrenomP"));
	     psexe.setCellValueFactory (new PropertyValueFactory<> ("SexeP"));
	     pcin.setCellValueFactory (new PropertyValueFactory<> ("CINP"));
	     pnais.setCellValueFactory (new PropertyValueFactory<> ("dateNP"));
	     ptel.setCellValueFactory (new PropertyValueFactory<> ("TelephoneP"));
	     psit.setCellValueFactory (new PropertyValueFactory<> ("SituationP"));
	     pmut.setCellValueFactory (new PropertyValueFactory<> ("MutuelleP"));
	     padr.setCellValueFactory (new PropertyValueFactory<> ("AdresseP"));
	     pcre.setCellValueFactory (new PropertyValueFactory<> ("DateCreationP"));
	     pdoc.setCellValueFactory (new PropertyValueFactory<> ("DocteurID"));
	     pidant.setCellValueFactory (new PropertyValueFactory<> ("pid"));
	     typeant.setCellValueFactory (new PropertyValueFactory<> ("typeant"));
	     ant.setCellValueFactory (new PropertyValueFactory<> ("ant"));
	     com.setCellValueFactory (new PropertyValueFactory<> ("comm"));
	     pidrdv.setCellValueFactory (new PropertyValueFactory<> ("pidrdv"));
	     daterdv.setCellValueFactory (new PropertyValueFactory<> ("daterdv"));
	     heurerdv.setCellValueFactory (new PropertyValueFactory<> ("heurerdv"));
	     motifrdv.setCellValueFactory (new PropertyValueFactory<> ("motifrdv"));

	}
	
	private void loadPatients (){
		dataPatient.clear();
	    try {
	        pst = cn.prepareStatement("Select * from Patient");
	        rs = pst.executeQuery ();
	        while (rs.next())
	            dataPatient.add(new tablePatient(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10), rs.getString(11), rs.getInt(12)));
	    }
	    catch(Exception e) {
	    	System.out.print(e.getMessage());
	    }
	    
	    tablePatient.setItems(dataPatient);
	    
	   	FilteredList<tablePatient> fdp = new FilteredList<>(dataPatient, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        searchpatient.textProperty().addListener((observable, oldValue, newValue) -> {
            fdp.setPredicate(tablePatient -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name field in your object with filter.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(tablePatient.getCINP()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                    // Filter matches first name.
                } 
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<tablePatient> sortedDataP = new SortedList<>(fdp);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedDataP.comparatorProperty().bind(tablePatient.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        tablePatient.setItems(sortedDataP);
    	
}
	
	
	public void loadAntecedents() {
		dataAnt.clear();
	    try {
	        pst = cn.prepareStatement("Select * from Antecedents");
	        rs = pst.executeQuery ();
	        while (rs.next())
	            dataAnt.add(new tableAnt(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5)));
	    }
	    catch(Exception e) {
	    	System.out.print(e.getMessage());
	    }
	    
	    tableAnt.setItems(dataAnt);
	    
        //search antecedents
        FilteredList<tableAnt> fdant = new FilteredList<>(dataAnt, p -> true);

        searchant.textProperty().addListener((observable, oldValue, newValue) -> {
            fdant.setPredicate(tableAnt -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(tableAnt.getPid()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } 
                return false;
            });
        });

        SortedList<tableAnt> sortedDataAnt = new SortedList<>(fdant);

        sortedDataAnt.comparatorProperty().bind(tableAnt.comparatorProperty());
        tableAnt.setItems(sortedDataAnt);
	}
	
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	
	public void loadRDV() {
		dataRDV.clear();
	    try {
	        pst = cn.prepareStatement("Select * from RDV");
	        rs = pst.executeQuery ();
	        while (rs.next())
	            dataRDV.add(new tableRDV(rs.getInt(2), rs.getString(3), rs.getString(4).substring(0, 5), rs.getString(5)));
	    }
	    catch(Exception e) {
	    	e.printStackTrace();
	    }
	    
	    tableRDV.setItems(dataRDV);
	    
        //search RDV
        FilteredList<tableRDV> fdrdv = new FilteredList<>(dataRDV, p -> true);

        searchrdv.textProperty().addListener((observable, oldValue, newValue) -> {
            fdrdv.setPredicate(tableRDV -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(tableRDV.getPidrdv()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } 
                return false;
            });
        });

        SortedList<tableRDV> sortedDatardv = new SortedList<>(fdrdv);

        sortedDatardv.comparatorProperty().bind(tableRDV.comparatorProperty());
        tableRDV.setItems(sortedDatardv);

	}
	
	// Delete patient
	
    @FXML
    void deletePatient(ActionEvent event) {
    	int index=tablePatient.getSelectionModel().getSelectedIndex();
    	System.out.println(index);
    	if(index<0) {
    		Alert erreur = new Alert(AlertType.ERROR);
    		erreur.setTitle("Erreur");
    		erreur.setHeaderText("Vous devez selectionner le patient a supprimer.");
    		erreur.showAndWait();
    	}
    	else {
        	Alert dialog = new Alert(AlertType.CONFIRMATION);
    		dialog.setTitle("Supprimer Patient");
    		dialog.setHeaderText("Merci de confimer");
    		dialog.setContentText("Voulez-vous supprimer ce patient?");
    		Optional<ButtonType> result = dialog.showAndWait();
    		
    		if(result.get()==ButtonType.OK) {
    	    	
    	    	if(index>=0) {
    	    		
    	    		tablePatient p=tablePatient.getSelectionModel().getSelectedItem();
    	    		delete(p.idP);
    	    		dataPatient.remove(index);
    	    		searchpatient.setText("");
    	    	}
    		}
    	}

    }
    
    public void delete(int id) {
		try {
			Connection connection = SQLConnection.getDBConnection();
			String sql="DELETE FROM Patient WHERE ID = ?"; 
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1, id);

			ps.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    
    
   // modifier patient
    
    
    @FXML
    void modifierPatient(ActionEvent event) throws SQLException {
    	
    	if (pnomtext.getText().isEmpty() | pprenomtext.getText().isEmpty() | pcintext.getText().isEmpty()
    			| psexetext.getText().isEmpty() | pteltext.getText().isEmpty() |
    		 pdatetext.getText().isEmpty() || psittext.getText().isEmpty() |
    			 pmuttext.getText().isEmpty() | padrtext.getText().isEmpty() ) {
    		Alert erreur = new Alert(AlertType.ERROR);
    		erreur.setTitle("Erreur");
    		erreur.setHeaderText("Vous devez remplir les champs.");
    		erreur.showAndWait();
    	}
    	else {
        	int id =Integer.valueOf(pidtext.getText());
        	String patientnom = pnomtext.getText();
        	String patientprenom = pprenomtext.getText();
        	String patientsexe = psexetext.getText();
        	String patientcin = pcintext.getText();
        	String patientdate = pdatetext.getText();
        	String patienttel = pteltext.getText();
        	String patientsit = psittext.getText();
        	String patientmut = pmuttext.getText();
        	String patientadr = padrtext.getText();
        	if (checkPatientCIN(id, patientcin)== true) {
            	tablePatient p=new tablePatient(id, patientnom, patientprenom,patientsexe,
            			patientcin,patientdate,patienttel,patientsit,patientmut,patientadr, "0", 0 );
            	
            	edit(p);
        	}

    	}

    }
    
    
    public void edit(tablePatient p) {
		try {
			
			Connection connection = SQLConnection.getDBConnection();
			
			String sql="UPDATE Patient set nom=? ,prenom=?,sexe=?,cin=?,datenaissance=?,telephone=?,situationfamiliale=? ,mutuelle=?, adresse=?  WHERE ID = ?"; 
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, p.getNomP());
			ps.setString(2, p.getPrenomP());
			ps.setString(3, p.getSexeP());
			ps.setString(4, p.getCINP());
			ps.setString(5, p.getDateNP());
			ps.setString(6, p.getTelephoneP());
			ps.setString(7, p.getSituationP());
			ps.setString(8, p.getMutuelleP());
			ps.setString(9, p.getAdresseP());
			ps.setInt(10, p.getIdP());
			ps.execute();
			
			searchpatient.setText("");
			loadPatients();
			
		} catch (Exception e) {
    		Alert erreur = new Alert(AlertType.ERROR);
    		erreur.setTitle("Erreur");
    		erreur.setHeaderText("Veuillez verifier les informations saisies.");
    		erreur.showAndWait();
		}
	}
    
    
    public boolean checkPatientCIN(int id, String cin) throws SQLException {
    	
        	int x = 0;
            pst = cn.prepareStatement("Select * from Patient");
            rs = pst.executeQuery ();
            while (rs.next()) {
            	// System.out.println(rs.getString("cin").compareTo(cin));
            	 if(rs.getString("cin").compareTo(cin) == 0) {
            		 if(rs.getInt("ID") != id) {
                		 x = 1;
            		 }
            	 }
            }
            if (x == 1) {
        		Alert erreur = new Alert(AlertType.ERROR);
        		erreur.setTitle("Erreur");
        		erreur.setHeaderText("Ce patient CIN existe deja.");
        		erreur.showAndWait();
        		return false;
            }
            
            return true;

    }
    
    @FXML
    void ajouterAnt(ActionEvent event) {
    	
    	if (pidtext2.getText().isEmpty() | radioLabel.isEmpty() | anttext.getText().isEmpty()| comtext.getText().isEmpty()) {
    		Alert erreur = new Alert(AlertType.ERROR);
    		erreur.setTitle("Erreur");
    		erreur.setHeaderText("Vous devez remplir les champs.");
    		erreur.showAndWait();
    	}
    	else {
        	int id =Integer.valueOf(pidtext2.getText());
        	String ant = anttext.getText();
        	String com = comtext.getText();

        	if (checkPatientID(id)) {
            	tableAnt p=new tableAnt(id, radioLabel, ant, com);
            	
            	insertAnt(p);
        	}

    	}

    }
    
    
    // ajout test
    
    public void insertAnt(tableAnt p) {
		try {
			
			Connection connection = SQLConnection.getDBConnection();
			
			String sql="INSERT INTO Antecedents (patientID, typeAntecedents, antecedents, commentaire) VALUES (?,?,?,?)"; 
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1, p.getPid());
			ps.setString(2, p.getTypeant());
			ps.setString(3, p.getAnt());
			ps.setString(4, p.getComm());

			ps.execute();
			
			loadAntecedents();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    
    public boolean checkPatientID(int id) {
        try {
        	boolean x = false;
            pst = cn.prepareStatement("Select * from Patient");
            rs = pst.executeQuery ();
            while (rs.next()) {
            	 if(rs.getInt("ID") == id) {
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
    
    
    // limit characters in textfield
    
    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }
   
    @FXML
    public void ajouterRDV() throws SQLException {
       	if (pidtext3.getText().isEmpty() | daterdvtext.getValue() == null | heurerdvtext.getText().isEmpty()| motifrdvtext.getText().isEmpty()) {
    		Alert erreur = new Alert(AlertType.ERROR);
    		erreur.setTitle("Erreur");
    		erreur.setHeaderText("Vous devez remplir les champs.");
    		erreur.showAndWait();
    	}
    	else {
        	int id =Integer.valueOf(pidtext3.getText());
        	String date = daterdvtext.getValue().toString();
        	String heure = heurerdvtext.getText();
        	String motif = motifrdvtext.getText();

        	
        	if (checkPatientID(id)) {
        		if (checkTime(date, heure)) {
                	tableRDV p=new tableRDV(id, date, heure, motif);
                	insertRDV(p);
        		}

        	}

    	}

    }
    
    
    // ajout test
    
    public void insertRDV(tableRDV p) {
		try {
			
			Connection connection = SQLConnection.getDBConnection();
			
			String sql="INSERT INTO RDV (patientID, dateRDV, heureRDV, motif) VALUES (?,?,?,?)"; 
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1, p.getPidrdv());
			ps.setString(2, p.getDaterdv());
			ps.setString(3, p.getHeurerdv().substring(0, 5));
			ps.setString(4, p.getMotifrdv());

			ps.execute();
			
			loadRDV();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
        
    public boolean checkTime(String date, String heure) throws SQLException {
    	
    	int x = 0;
        pst = cn.prepareStatement("Select * from RDV");
        rs = pst.executeQuery ();
        while (rs.next()) {
        	System.out.println(rs.getString("dateRDV").compareTo(date));
        	System.out.println(rs.getString("heureRDV").compareTo(heure));

        	 if(rs.getString("dateRDV").compareTo(date) == 0) {
        		 if(rs.getString("heureRDV").compareTo(heure) == 11) {
            		 x = 1;
        		 }
        	 }
        }
        if (x == 1) {
    		Alert erreur = new Alert(AlertType.ERROR);
    		erreur.setTitle("Erreur");
    		erreur.setHeaderText("Un rendez-vous est deja réservé.");
    		erreur.setContentText("Veillez choisir une autre date et heure.");
    		erreur.showAndWait();
    		return false;
        }
        
        return true;

}    

}
