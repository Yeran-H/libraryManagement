package controller;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import model.Library;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class AddPatronController extends Controller<Library>{
    //Set the textfields and text
    @FXML private TextField nameTf;
    @FXML private TextField idTf;
    @FXML private Text trueTxt;
    
    @FXML
    public void initialize() {}

    public final Library getLibrary() { return model; } //get the Library 
    
    private final int getID() { return Integer.parseInt(idTf.getText()); } //Get the ID
    private final String getName() { return nameTf.getText(); } //Get the Name
      
    @FXML 
    private void handleAdd (ActionEvent event) { //Add Patron 
        if (getLibrary().getPatron(getID()) == null) { //Check if Patron is already in the system if not add a new patron 
            getLibrary().addPatron(getID(),getName());
            trueTxt.setText("Patron added.");
        }
        else { //If it is display a message 
            trueTxt.setText("Patron already exists!");
        }
    }
    
    @FXML 
    private void handleExit (ActionEvent event) throws Exception{ stage.close(); } //Exit the Page
}
