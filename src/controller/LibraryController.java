package controller;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import model.Library;
import javafx.event.*;
import javafx.stage.*;

public class LibraryController extends Controller<Library> {
    @FXML
    public void initialize() {}

    public final Library getLibrary() { return model; } //Getting the library class
    
    @FXML 
    private void handleCatalogue (ActionEvent event) throws Exception{
        ViewLoader.showStage(getLibrary(), "/view/catalogue.fxml", "Catalogue", new Stage()); //Opening the catalogue FXML
    }
    
    @FXML 
    private void handleAdministration (ActionEvent event) throws Exception{
        ViewLoader.showStage(getLibrary(), "/view/admin.fxml", "Administration Menu", new Stage()); //Openning the Admin FXML
    }
    
    @FXML 
    private void handRecord (ActionEvent event) throws Exception{
        ViewLoader.showStage(getLibrary(), "/view/record.fxml", "Patron Record", new Stage()); //Opening the Patron Record FXML
    }
    
    @FXML 
    private void handleFavourite (ActionEvent event) throws Exception{
        ViewLoader.showStage(getLibrary(), "/view/favourites.fxml", "Favourites", new Stage()); //Opening the Patron Favourities FXML
    }
    
    @FXML 
    private void handleExit (ActionEvent event){ System.exit(0); }//Closing the Application
}
