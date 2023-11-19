package controller;

import au.edu.uts.ap.javafx.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import model.Library;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.Book;
import model.Patron;

public class PlaceHoldController extends Controller<Library>{
    //Set Feidls 
    @FXML private TextField patronTf;
    @FXML private ListView<Book> booksLv;
    @FXML private Button patronBtn;
    @FXML private Button holdBtn;
    @FXML private Text holdTxt;
      
    @FXML
    public void initialize() {
        //Set Button Disable 
        patronBtn.setDisable(true);
        holdBtn.setDisable(true);
        
        patronTf.textProperty().addListener((o, oldText, newText) -> patronBtn.setDisable(getID() == null)); //Disable if their text or not     
        booksLv.getSelectionModel().selectedItemProperty().addListener((observable, oldSubject, newSubject) -> holdBtn.setDisable(newSubject == null)); //Display if the selection is made or not
        
        displayBooks(getLibrary().getCatalogue().getAllBooks());
    }

    public final Library getLibrary() { return model; } //Get Library 
    
    @FXML private Integer getID () {return Integer.parseInt(patronTf.getText()); } //Get ID
    @FXML private Book getSelectedBook() {return booksLv.getSelectionModel().getSelectedItem(); } //Get Selcted Book 
    @FXML private void displayBooks(ObservableList<Book> books) { booksLv.setItems(books); }  //Display Book 
    @FXML private void setText(String text) {holdTxt.setText(text);} //Display Message 
    
    
    @FXML 
    private void handlePatron (ActionEvent event) { //Find Patron 
        Patron patron = getLibrary().getPatron(getID()); //Get Patron 
        
        if (patron != null) { //If not Null Display List of Books 
            setText(null);
            displayBooks(getLibrary().getCatalogue().getAllBooks());
        }
        else { //If null display message 
            setText("No Patron selected");
            displayBooks(getLibrary().getCatalogue().getAllBooks());
        }
    }
    
    @FXML 
    private void handleHold (ActionEvent event) { //Hold Method 
        Book book = getSelectedBook(); //Get Book 
        Patron patron = getLibrary().getPatron(getID()); //Get Patron 
        
        if (!book.isHeldBy(patron)) { //Check if patron has the book on hold or not 
            book.addHold(patron); //Add Hold 
            setText("Hold placed on " + book.getTitle() + " for " + patron.getName()); //Display Message 
        }
        else { //If it already Hold Display Message 
            setText(patron.getName() + " has already placed a hold on " + book.getTitle()); 
        }
    }
    
    @FXML 
    private void handleExit (ActionEvent event) throws Exception{ stage.close(); } //Exit Page 
}

