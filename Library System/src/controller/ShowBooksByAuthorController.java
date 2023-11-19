package controller;

import au.edu.uts.ap.javafx.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import model.Catalogue;
import javafx.event.*;
import javafx.scene.control.*;
import model.Book;
import model.Author;

public class ShowBooksByAuthorController extends Controller<Catalogue>{
    //Set Fields 
    @FXML private ListView<Book> booksLv;
    @FXML private ListView<Author> authorsLv;
    
    @FXML
    public void initialize() {}

    public final Catalogue getCatalogue() { return model; } //Get Catalogue 
    
    private void setSelectedBook(ObservableList<Book> books) { booksLv.setItems(books); } //Display Books 
    
    private Author getSelectedAuthor() { return authorsLv.getSelectionModel().getSelectedItem(); } //Get Selected Author
    
    @FXML 
    private void handleDisplay (ActionEvent event) { //Display Books Based of Author
        ObservableList<Book> books = getCatalogue().getBooksByAuthor(getSelectedAuthor()); //Get Author and then get list of books 
        setSelectedBook(books); //Display Books 
    }   
    
    @FXML 
    private void handleExit (ActionEvent event) throws Exception{ stage.close(); } //Exit Page 
}
