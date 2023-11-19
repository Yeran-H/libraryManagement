
import java.util.ArrayList;
import java.util.List;

public class Library {

    private Catalogue catalogue; //Catalogue class
    public ArrayList<Patron> patrons = new ArrayList<Patron>(); //Patrons list
    // write your solution below
    public static void main(String[] args) {
        new Library().use(); // Main method
    }
    
    public Library() {
        catalogue = new Catalogue(this); //create Catalogue
    }

    
    
    
    private void use() { // Library Menu 
        char choice;
        while((choice = readChoice("Library")) != 'X') {
            switch(choice) {
                case '1': catalogue.use(); break; //Open Catalogue
                case '2': veiwPatron(); break; // View Patron
                case '3': favourite(); break; //View Favourite 
                case '4': admin(); break; //Open Admin
                default: help("Library"); break;
            }
        }
    }

    private char readChoice(String type) { //Read choice for menu 
        if (type.equals("Library")) { // Read choice for Library Menu
            System.out.println("Welcome to the Library! Please make a selection from the menu:");
            System.out.println("1. Explore the catalogue.");
            System.out.println("2. View your patron record.");
            System.out.println("3. Show you favourite books.");
            System.out.println("4. Enter Admin Mode.");
            System.out.println("X. Exit the system.");
            System.out.print("Enter a choice: ");
        }
        else { //Read Choice for Admin
            System.out.println("Welcome to the administration menu:");
            System.out.println("1. Add a patron.");
            System.out.println("2. Remove a patron.");
            System.out.println("3. Add a book to the catalogue.");
            System.out.println("4. Remove a book from the catalogue.");
            System.out.println("R. Return to the previous menu.");
            System.out.print("Enter a choice: ");
        }
        return In.nextChar();
    }

    private void admin() { //Admin Menu
        char choice;
        while((choice = readChoice("Admin")) != 'R') {
            switch(choice) {
                case '1': addPatron(); break; //Add Patron
                case '2': removePatron(); break; //remove Patron
                case '3': addBook(); break; //Add book
                case '4': removeBook(); break; //Remove Book
                default: help("Admin"); break;
            }
        } 
    }

    private void veiwPatron() { //View Patron
        //Get Details
        System.out.println("");
        System.out.print("Enter a patron ID: ");
        int ID = In.nextInt();
        boolean found = false;
        
        for (Patron patron : patrons) { //Look Through each Patron
            if (patron.exist(ID)) { // Check if their is a Patron and Display 
                found = true; 
                System.out.println(patron);
                patron.borrow();
                System.out.println(patron.name + "'s borrowing history:");  
                patron.borrowHistory();
                break;
            }
        }
                
        
        if (!found) { //If faslse Display no Patron Found
            System.out.println("That patron does not exist.");
        }
        System.out.println("");
    }

    
    
    private void addPatron() { //Adding a Patron
        //Get Details
        System.out.println("");
        System.out.println("Adding a new patron.");
        System.out.print("Enter a new ID: ");
        int ID = In.nextInt();
        System.out.print("Enter the patron's name: ");
        String name = In.nextLine();
        
        patrons.add(new Patron(ID, name)); // Add new Patron
        
        System.out.println("Patron added."); //Display
        System.out.println("");
    }

    private void removePatron() { //Remove Patron
        //Get Details
        System.out.println("");
        System.out.println("Removing a patron.");
        System.out.print("Enter a patron ID: ");
        int ID = In.nextInt();
        boolean found = false;
        
        for (Patron patron : patrons) { // Look through each patron
            if (patron.exist(ID)) {
                //remove book from loan
                patron.removeBooks(catalogue); //Remove Book from Patron      

                patrons.remove(patron); //Remove Patron
                System.out.println("Patron removed.");
                found = true;
                break;
            }
        }
        
        if (!found) { //If faslse Display no Patron Found 
            System.out.println("That patron does not exist.");
        }
        System.out.println("");
    }

    private void addBook() { //Add Book
        //Get Details
        System.out.println("");
        System.out.println("Adding a new book.");
        System.out.print("Enter the title of the book: ");
        String title = In.nextLine();
        System.out.print("Enter the author's name: ");
        String author = In.nextLine();
        System.out.print("Enter the genre: ");
        String genre = In.nextLine();
        
        catalogue.addBook(title, author, genre.toLowerCase()); //Add
        System.out.println("Added " + title + " to catalogue.");
        System.out.println("");
    }

    private void removeBook() { //Remove Book 
        System.out.println("");
        System.out.println("Removing a book.");
        System.out.print("Enter the title of the book: ");
        String title = In.nextLine();
        System.out.print("Enter the author's name: ");
        String author = In.nextLine();
        
        if (!catalogue.findBook(title)) { //Find book and return boolean if removed or not
            System.out.println("No such book found.");
        }
        System.out.println("");
    }

    private void help(String choice) {
        if (choice.equals("Library")) {
            System.out.println("Please enter a number between 1 and 4, or press X to exit.");
        }
        else if (choice.equals("Admin")) {
            System.out.println("Please enter a number between 1 and 4 or press R to return to the previous menu.");
        }
    }

    private void favourite() { //Fvaourite
        System.out.println("");
        System.out.print("Enter a patron ID: ");
        int ID = In.nextInt();
        
        for (Patron patron : patrons) { //Check Through Patron
            if (patron.exist(ID)) {
                System.out.println(patron.name + "'s favourite books are:");
                patron.favourite();
            }
        }
        System.out.println("");
    }
}
