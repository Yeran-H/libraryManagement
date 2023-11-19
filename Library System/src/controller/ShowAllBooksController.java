package controller;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import model.Catalogue;
import javafx.event.*;

public class ShowAllBooksController extends Controller<Catalogue>{
    @FXML
    public void initialize() {}

    public final Catalogue getCatalogue() { return model; } //Get Catalogue  
    
    @FXML 
    private void handleExit (ActionEvent event) throws Exception{ stage.close(); } //Exit Page 
}

