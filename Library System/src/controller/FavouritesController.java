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

public class FavouritesController extends Controller<Library> {
    //Set Fields 
    @FXML private Button showBtn;
    @FXML private TextField patronTf;
    @FXML private ListView<Book> booksLv;
    @FXML private Text selectedTxt;
    
    @FXML
    public void initialize() {
        showBtn.setDisable(true);  //Set Button Disable    
        patronTf.textProperty().addListener((o, oldText, newText) -> showBtn.setDisable(getID() == null)); //Disable if their text or not 
    }

    public final Library getLibrary() { return model; } //Get Library 
    
    @FXML private Integer getID () {return Integer.parseInt(patronTf.getText()); } //Get ID 
    @FXML private void setID (String text) {selectedTxt.setText(text);} //Display Patron 
    @FXML private void displayBooks(ObservableList<Book> books) { booksLv.setItems(books); } //Display Books 
    
    @FXML 
    private void handleFavourities (ActionEvent event) { //Get Faviourites 
        Patron patron = getLibrary().getPatron(getID()); //Get Paton 
        
        if (patron != null) { //If Patron Their display Faviourties List 
            displayBooks(patron.favourites());
            setID(patron.getName() + "'s favourite books:");
        }
        else { //Not Display Message 
            setID("No Patron Selected");
            displayBooks(null);
        }
    }
    
    @FXML 
    private void handExit (ActionEvent event) throws Exception{ stage.close(); } //Exit Page 
}

