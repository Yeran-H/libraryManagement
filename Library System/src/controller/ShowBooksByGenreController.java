package controller;

import au.edu.uts.ap.javafx.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.*;
import javafx.scene.control.*;
import model.Book;
import model.Catalogue;
import model.Genre;

public class ShowBooksByGenreController extends Controller<Catalogue>{
    //Set Fields 
    @FXML private ListView<Book> booksLv;
    @FXML private ListView<Genre> genresLv;
    
    @FXML
    public void initialize() {}

    public final Catalogue getCatalogue() { return model; } //Get Catalogue 
    
    private void setSelectedBook(ObservableList<Book> books) { booksLv.setItems(books); } //Set Books in Genre 
    
    private Genre getSelectedGenre() { return genresLv.getSelectionModel().getSelectedItem(); } //Return the Types Genre
    
    @FXML 
    private void handleDisplay (ActionEvent event) { //Display Books in Genre
        ObservableList<Book> books = getCatalogue().getBooksInGenre(getSelectedGenre()); //Get Book in Genre
        setSelectedBook(books); //Display Books
    }
    
    @FXML 
    private void handleExit (ActionEvent event) throws Exception{ stage.close(); } //Exit Page 
}
