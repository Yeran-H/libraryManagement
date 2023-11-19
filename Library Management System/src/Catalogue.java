
import java.util.ArrayList;
import java.util.List;

public class Catalogue {

    private Library library; //Library Class
    private ArrayList<Book> booksOnShelf = new ArrayList<Book>(); //List of books on shelf
    private ArrayList<Book> booksOnLoan= new ArrayList<Book>(); //List of books on Loan 
    private ArrayList<Genre> genres= new ArrayList<Genre>(); // List of genres
    private ArrayList<Author> authors= new ArrayList<Author>(); // List of Authors
    // write your solution below
    
    public Catalogue (Library library) {
        this.library = library; //Setting Library 
    }

    public void use() { //Menu
        char choice ;
        while((choice = readChoice()) != 'R') {
            switch(choice) {
                case '1': allBooks(); break; //Display all Books
                case '2': allAvailableBooks(); break; //Display Available Books
                case '3': allGenres(); break; //Display Genres
                case '4': allBooksGenres(); break; //Display all the books in the Genre
                case '5': allAuthors(); break; //Display Author
                case '6': allBooksAuthor(); break; // Display all the books in Author
                case '7': borrow(); break; // Borrow Book
                case '8': returnBook(); break; // Return Book 
                case '9': hold(); break; // Hold Book 
                default: help(); break;
            }
        }
    }

    private char readChoice() { //Read Choice for Menu
        System.out.println("Welcome to the Catalogue! Please make a selection from the menu:");
        System.out.println("1. Display all books.");
        System.out.println("2. Display all available books.");
        System.out.println("3. Display all genres.");
        System.out.println("4. Display books in a genre.");
        System.out.println("5. Display all authors.");
        System.out.println("6. Display all books by an author.");
        System.out.println("7. Borrow a book.");
        System.out.println("8. Return a book.");
        System.out.println("9. Place a hold.");
        System.out.println("R. Return to previous menu.");
        System.out.print("Enter a choice: ");
        return In.nextChar();
    }

    private void allBooks() { //Display all Books
        System.out.println("");
        System.out.println("The Library has the following books:");
        for (Book book : booksOnShelf) {
                System.out.println(book);
        }
        
        for (Book book : booksOnLoan) {
                System.out.println(book);
        }
        System.out.println("");
    }

    private void allAvailableBooks() { // Display all Available Books
        System.out.println("");
        System.out.println("The following books are on the shelf:");
        for (Book book : booksOnShelf) {
                System.out.println(book);
        }
        System.out.println("");
    }

    private void allGenres() { //Display all Genres
        System.out.println("");
        System.out.println("The Library has books in the following genres:");
        for (Genre genre : genres) {
                System.out.println(genre);
        }
        System.out.println("");
    }
    
    private void allAuthors() { //Display Authors
        System.out.println("");
        System.out.println("The following authors have books in this Library:");
        for (Author author : authors) {
                System.out.println(author);
        }
        System.out.println("");
    }

    public void addBook(String title, String author, String genre) { //Adding Book 
        //Setting Vaules
        boolean foundAuthor = false;
        boolean foundGenre = false;
        Author author1 = new Author(author);
        Genre genre1 = new Genre(genre);
        
        for (Author orginalAuthor : authors) { //Checking if Author already Exist 
            if(orginalAuthor.exist(author)) {
                foundAuthor = true;
                author1 = orginalAuthor;
                break;
            }
        }
        
        if (!foundAuthor) { //If they dont exist make a new author
            authors.add(new Author(author));
        }
        
        
        for (Genre orginalGenre : genres) { //Checking if Genre already Exist 
            if(orginalGenre.exist(genre)) {
                foundGenre = true;
                genre1 = orginalGenre;
                break;
            }
        }
        
        if (!foundGenre) { //If they dont exist make a new Genre
            genres.add(new Genre(genre));
        }
        
        booksOnShelf.add(new Book(title, author1, genre1)); //Adding Book
    }


    private void borrow() { //Borrow Book 
        System.out.println("");
        ArrayList <Patron> patrons2 = library.patrons; // Setting up Patron from Library 
        System.out.print("Enter a valid patron ID: ");
        int ID = In.nextInt();
        boolean found = false;
        
        for (Patron patron : patrons2) { //Finding Patron
            if (patron.exist(ID)) {
                System.out.print("Enter the title of the book you wish to borrow: ");
                String title = In.nextLine();
                for (Book book : booksOnShelf) { //finding Book
                    if (book.existTitle(title)) { //Check if Book Exist 
                        if (book.onHold(patron) || book.emptyHold()) { //Check is Patron is first in line for Book 
                            patron.addBook(book);
                            book.removeHold(); //Remove patron from hold
                            System.out.println("Book loaned.");

                            booksOnLoan.add(book);
                            booksOnShelf.remove(book);
                            found = true;
                            break;
                        }
                    }
                }
            }
        }
        
        if (!found) { //If could not find book display unable
            System.out.println("That book is not available or doesn't exist.");
        }
        System.out.println("");
    }

    private void returnBook() { //Return Book 
        System.out.println("");
        ArrayList <Patron> patrons2 = library.patrons; //Setting up Patron from Library 
        System.out.print("Enter a valid patron ID: ");
        int ID = In.nextInt(); //Get ID
        
        for (Patron patron : patrons2) { //Go through each Patron 
            if (patron.exist(ID)) {
                System.out.println(patron.name + " has the following books:");

                patron.borrow(); // Display books Borrow
                
                System.out.print("Enter the title of the book you wish to return: "); //Get title
                String title = In.nextLine();
                
                for (Book book : patron.currentlyBorrowed) { //Go through all book in Patron
                    if (book.existTitle(title)) { //Find Book 
                        patron.removeBook(book); //Remove book from Patron
                        System.out.println(title + " has been returned.");

                        removeBook(book.title); //Remove book from loan
                        addBook(book); //Add book to shelf
                        break;
                    }
                }
            }
        }
        System.out.println("");
    }

    public void addBook(Book book) { //Add book to shelf
        booksOnShelf.add(book);
    }

    public void removeBook(String title) { //Remove Book from Loan 
        for (Book book : booksOnLoan) {
            if (book.existTitle(title)) {
                booksOnLoan.remove(book);
                break;
            }
        }
    }

    public Boolean findBook(String title) { //Find and Remove book from Shelf
        for (Book book : booksOnShelf) { //Go through all Books 
            if (book.existTitle(title)) { //Find Book 
                System.out.println(book + " removed from catalogue.");
                booksOnShelf.remove(book); //Remove Book
                return true;
            }
        }
        return false;
    }

    private void allBooksGenres() { //Find books in that Genre
        System.out.println("");
        System.out.print("Enter a genre: ");
        String word = In.nextLine();
        System.out.println("The library has the following books in that genre:");
        
        for (Book book : booksOnShelf) { //Go through all Books 
            if (book.existGenre(word)) { //Find Book 
                System.out.println(book.toString()); //Display Book
            }
        }
        
        for (Book book : booksOnLoan) { //Go through all books 
            if (book.existGenre(word)) { //Find Book 
                System.out.println(book.toString()); //Display Book 
            }
        }
        System.out.println("");
    }

    private void allBooksAuthor() { //Display book from the author 
        System.out.println("");
        System.out.print("Enter the name of an author: ");
        String word = In.nextLine();
        System.out.println("The library has the following books by that author:");
        
        for (Book book : booksOnShelf) { //Go through all Books 
            if (book.existAuthor(word)) { //Find Book 
                System.out.println(book.toString()); //Display Book
            }
        }
        
        for (Book book : booksOnLoan) { //Go through all books 
            if (book.existAuthor(word)) { //Find Book 
                System.out.println(book.toString()); //Display Book 
            }
        }
        System.out.println("");
    }

    private void help() {
        System.out.println("Please enter a number between 1 and 9 or press R to return to the previous menu.");
    }

    private void hold() {
        System.out.println("");
        System.out.print("Enter a patron ID: ");
        int ID = In.nextInt();
        System.out.print("Enter book title: ");
        String title = In.nextLine();
        ArrayList <Patron> patrons2 = library.patrons; //Setting up Patron from Library 
        
        for (Patron patron : patrons2) { //Go through each patron 
            if (patron.exist(ID)) { //Check if exist 
                for (Book book : booksOnLoan) { //Check book on loan 
                    if (book.existTitle(title)) { //Check if Title exist 
                        if (!book.isHold(patron)) { //Check is patron already hold it 
                            book.addHold(patron); //Add on hold 
                            System.out.println("Book held.");
                            break;
                        }
                        break;
                    }
                }
                
                for (Book book : booksOnShelf) { //Check book on Shelf 
                    if (book.existTitle(title)) { //Check if Title exist 
                        if (!book.isHold(patron)) { //Check is patron already hold it 
                            book.addHold(patron); //Add on hold 
                            System.out.println("Book held.");
                            break;
                        }
                        break;
                    }
                }
            }
        }
        System.out.println("");
    }
}
