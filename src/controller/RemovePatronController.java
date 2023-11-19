package controller;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import model.Library;
import javafx.event.*;
import javafx.scene.control.*;
import model.Patron;

public class RemovePatronController extends Controller<Library>{
    @FXML private ListView<Patron> patronsLv; //Setting List of patron 
    
    @FXML
    public void initialize() {}

    public final Library getLibrary() { return model; } //Get Library
    
    private Patron getSelectedPatron() { return patronsLv.getSelectionModel().getSelectedItem(); } //Get the Selected Patron 
    
    @FXML 
    private void handleRemove (ActionEvent event) { getLibrary().removePatron(getSelectedPatron()); } //Remove the Selected Patron 
    
    @FXML 
    private void handleExit (ActionEvent event) throws Exception{ stage.close();} //Exit Page 
}
