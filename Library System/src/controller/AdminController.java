package controller;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import model.Library;
import javafx.event.*;
import javafx.stage.*;

public class AdminController extends Controller<Library>{
    @FXML
    public void initialize() {}

    public final Library getLibrary() { return model; } //Get Library
    
    @FXML 
    private void handleAddPatron (ActionEvent event) throws Exception{
        ViewLoader.showStage(getLibrary(), "/view/addPatron.fxml", "Add Patron", new Stage()); //Display Add Patron Page
    }
    
    @FXML 
    private void handleRemovePatron (ActionEvent event) throws Exception{
        ViewLoader.showStage(getLibrary(), "/view/removePatron.fxml", "Remove Patron", new Stage()); //Display Remove Patron Page 
    }
    
    @FXML 
    private void handleAddBook (ActionEvent event) throws Exception{
        ViewLoader.showStage(getLibrary().getCatalogue(), "/view/addBook.fxml", "Add Book", new Stage()); //Display Add Book Page
    }
    
    @FXML 
    private void handleRemoveBook (ActionEvent event) throws Exception{
        ViewLoader.showStage(getLibrary().getCatalogue(), "/view/removeBook.fxml", "Remove Book", new Stage()); //Display Remove Book Page
    }
    
    @FXML 
    private void handleExit (ActionEvent event) throws Exception{ stage.close(); } //Close Page
}