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

public class RecordController extends Controller<Library>{
    //Set Fields 
    @FXML private Button showBtn;
    @FXML private ListView<Book> currentLv;
    @FXML private ListView<Book> historyLv;
    @FXML private TextField patronTf;
    @FXML private Text selectedTxt;
    
    @FXML
    public void initialize() {
        showBtn.setDisable(true);  //Set Button Disable    
        patronTf.textProperty().addListener((o, oldText, newText) -> showBtn.setDisable(newText == null)); //Disable if their text or not 
    }

    public final Library getLibrary() { return model; } //Get Library 
    
    @FXML private Integer getID () {return Integer.parseInt(patronTf.getText()); } //Get ID
    @FXML private void setID (String text) {selectedTxt.setText(text);} //Display Patron Name
    @FXML private void displayCurrentBooks(ObservableList<Book> books) { currentLv.setItems(books); }  //Display Current Books 
    @FXML private void displayHistoryBooks(ObservableList<Book> books) { historyLv.setItems(books); }  //Display History Books 
    
    @FXML 
    private void handleRecord (ActionEvent event) { //Get Patron to display Books 
        Patron patron = getLibrary().getPatron(getID()); //Get Patron 
        
        if (patron != null) { //If Their Display Books 
            displayCurrentBooks(patron.currentlyBorrowed());
            displayHistoryBooks(patron.borrowingHistory());
            setID(patron.toString());
        }
        else { //If Not Display message 
            setID("No Patron Selected");
            displayCurrentBooks(null);
            displayHistoryBooks(null);
        }
    }
    
    @FXML 
    private void handleExit (ActionEvent event) throws Exception{ stage.close(); } //Exit Page 
}

