package controller;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import model.Catalogue;
import javafx.event.*;
import javafx.scene.control.*;
import model.Book;

public class RemoveBookController extends Controller<Catalogue>{
    @FXML private ListView<Book> booksLv; //List View of Books
       
    @FXML
    public void initialize() {}

    public final Catalogue getCatalogue() { return model; } //Get Catalogue 
    
    private Book getSelectedBook() { return booksLv.getSelectionModel().getSelectedItem(); } //Get the Selected Book 
    
    @FXML 
    private void handleRemove (ActionEvent event) { getCatalogue().removeBook(getSelectedBook()); }  //Removing the Selected Book 
    
    @FXML 
    private void handleExit (ActionEvent event) throws Exception{ stage.close(); } //Exit Page 
}
