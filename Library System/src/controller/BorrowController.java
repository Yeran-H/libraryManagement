package controller;

import au.edu.uts.ap.javafx.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import model.Library;
import javafx.event.*;
import javafx.scene.control.*;
import model.Book;
import model.Patron;

public class BorrowController extends Controller<Library>{
    //Set Field 
    @FXML private TextField patronTf;
    @FXML private ListView<Book> booksLv;
    @FXML private Button patronBtn;
    @FXML private Button borrowBtn;    
    
    @FXML
    public void initialize() {
        //Set Button Disable 
        patronBtn.setDisable(true);
        borrowBtn.setDisable(true);
        
        patronTf.textProperty().addListener((o, oldText, newText) -> patronBtn.setDisable(getID() == null)); //Disable if their text or not         
        booksLv.getSelectionModel().selectedItemProperty().addListener((observable, oldSubject, newSubject) -> borrowBtn.setDisable(newSubject == null)); //Display if the selection is made or not
    }

    public final Library getLibrary() { return model; } //Get Library
    
    @FXML private Integer getID () {return Integer.parseInt(patronTf.getText()); } //Get ID
    @FXML private Book getSelectedBook() {return booksLv.getSelectionModel().getSelectedItem(); } //Get Selected Book 
    @FXML private void displayBooks(ObservableList<Book> books) { booksLv.setItems(books); }  //Display Book 
    
    
    @FXML 
    private void handlePatron (ActionEvent event) { //Get Patron 
        Patron patron = getLibrary().getPatron(getID()); //get Patron based of ID
        
        if (patron != null) { //If exist display book 
            displayBooks(getLibrary().getCatalogue().getBorrowableBooks(patron)); //Display Book 
        }
        else {
            displayBooks(null);
        }
    } 
    
    @FXML 
    private void handleBook (ActionEvent event) { //Borrow Selected Book 
        Book book = getSelectedBook(); //get Book 
        Patron patron = getLibrary().getPatron(getID()); //Get Patron 
        getLibrary().getCatalogue().loanBookToPatron(book, patron); //Borrow Book 
        displayBooks(getLibrary().getCatalogue().getBorrowableBooks(patron)); //Display Updated List 
    } 
    
    @FXML 
    private void handleExit (ActionEvent event) throws Exception{ stage.close(); } //Exit Page 
}

