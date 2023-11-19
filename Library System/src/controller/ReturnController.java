package controller;

import au.edu.uts.ap.javafx.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import model.Library;
import javafx.event.*;
import javafx.scene.control.*;
import model.Book;
import model.Patron;

public class ReturnController extends Controller<Library>{
    //Set Fields 
    @FXML private TextField patronTf;
    @FXML private ListView<Book> booksLv;
    @FXML private Button patronBtn;
    @FXML private Button returnBtn;
    
    
    @FXML
    public void initialize() {
        //Set Button Disable 
        patronBtn.setDisable(true);
        returnBtn.setDisable(true);
        
        patronTf.textProperty().addListener((o, oldText, newText) -> patronBtn.setDisable(getID() == null)); //Disable if their text or not        
        booksLv.getSelectionModel().selectedItemProperty().addListener((observable, oldSubject, newSubject) -> returnBtn.setDisable(newSubject == null)); //Display if the selection is made or not
    }

    public final Library getLibrary() { return model; } //Get Library 
    
    @FXML private Integer getID () {return Integer.parseInt(patronTf.getText()); } //Get ID
    @FXML private Book getSelectedBook() {return booksLv.getSelectionModel().getSelectedItem(); } //Get Selected Book 
    @FXML private void displayBooks(ObservableList<Book> books) { booksLv.setItems(books); }  //Display Books 
    
    
    @FXML 
    private void handlePatron (ActionEvent event) { //Find Patron 
        Patron patron = getLibrary().getPatron(getID()); //Get Patron 
        
        if (patron != null) { //If not Empty Display Book 
            displayBooks(patron.currentlyBorrowed());
        }
        else {
            displayBooks(null);
        }
    }   
    
    @FXML 
    private void handleBook (ActionEvent event) { //Get Book to return 
        Book book = getSelectedBook(); //Get Book 
        Patron patron = getLibrary().getPatron(getID()); //Get Patron 
        getLibrary().getCatalogue().returnBookFromPatron(book, patron); //Return Book 
        displayBooks(patron.currentlyBorrowed()); //Display Updataed Books
    }
    
    @FXML 
    private void handleExit (ActionEvent event) throws Exception{ stage.close(); } //Exit Page 
}