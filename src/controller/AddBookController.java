package controller;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import model.Catalogue;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class AddBookController extends Controller<Catalogue>{
    //Set the Fields
    @FXML private Text addTxt;
    @FXML private TextField titleTf;
    @FXML private TextField authorTf;
    @FXML private TextField genreTf;
    
    @FXML
    public void initialize() {}

    public final Catalogue getCatalogue() { return model; } //Get the Catalogue
    
    private final String getTitle() { return titleTf.getText(); } //Get Title
    private final String getAuthor() { return authorTf.getText(); } //Get Author
    private final String getGenre() { return genreTf.getText(); }   //Get Genre
        
    
    @FXML 
    private void handleAdd (ActionEvent event) { //Add Book 
        if (!getCatalogue().hasBook(getTitle(), getAuthor())) { //Check if it already Exist 
            getCatalogue().addBook(getTitle(), getAuthor(), getGenre()); //Add Book 
            addTxt.setText("Book added to Catalogue.");
        }
        else { //If it already do display message 
            addTxt.setText("That book is already in the Catalogue.");
        }
    }
    
    @FXML 
    private void handleExit (ActionEvent event) throws Exception{ stage.close(); } //Exit Page 
}

