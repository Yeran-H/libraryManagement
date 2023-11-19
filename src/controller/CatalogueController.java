package controller;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import javafx.event.*;
import javafx.stage.*;
import model.Library;

public class CatalogueController extends Controller<Library>{
    @FXML
    public void initialize() {}

    public final Library getLibrary() { return model;} //Get Library
    
    @FXML 
    private void handleAll (ActionEvent event) throws Exception{ //Display All Books Page
        ViewLoader.showStage(getLibrary().getCatalogue(), "/view/showAllBooks.fxml", "Complete Catlogue", new Stage());
    }
    
    @FXML 
    private void handleAvailable (ActionEvent event) throws Exception{ //Display All Available Books Page
        ViewLoader.showStage(getLibrary().getCatalogue(), "/view/showAvailableBooks.fxml", "Available Books", new Stage());
    }
    
    @FXML  
    private void handleGenre (ActionEvent event) throws Exception{ //Display Genre Page 
        ViewLoader.showStage(getLibrary().getCatalogue(), "/view/showBooksByGenre.fxml", "Browse by Genre", new Stage());
    }
    
    @FXML 
    private void handleAuthor (ActionEvent event) throws Exception{ //Display Author Page 
        ViewLoader.showStage(getLibrary().getCatalogue(), "/view/showBooksByAuthor.fxml", "Browse by Author", new Stage());
    }
    
    @FXML 
    private void handleBorrow (ActionEvent event) throws Exception{ //Display Borrow Page 
        ViewLoader.showStage(getLibrary(), "/view/borrow.fxml", "Borrow a Book", new Stage());
    }
    
    @FXML 
    private void handleReturn (ActionEvent event) throws Exception{ //Display Return Page 
        ViewLoader.showStage(getLibrary(), "/view/return.fxml", "Return a Book", new Stage());
    }
    
    @FXML 
    private void handleHold (ActionEvent event) throws Exception{ //Display Hold Page 
        ViewLoader.showStage(getLibrary(), "/view/placeHold.fxml", "Place a Hold", new Stage());
    }
    
    @FXML 
    private void handleExit (ActionEvent event) throws Exception{ stage.close(); } //Exit Page 
}
