
import java.util.ArrayList;
import java.util.List;

public class Patron {

    public int ID; //Creating ID which can be access in other classes
    public String name; //Creating name which can be access in other classes
    public ArrayList<Book> currentlyBorrowed = new ArrayList<Book>(); //List of the current book borrowed
    private ArrayList<Book> borrowingHistory = new ArrayList<Book>(); // List of book borrowed
    // write your solution below

    public Patron(int ID, String name) { //Constructor for ID and Name
        this.ID = ID;
        this.name = name;
    }

    public boolean exist(int ID) { //Checking if Patron exist
        return this.ID == ID;
    }

    public void borrow() { //Display list of current books borrow
        System.out.println("Books currently borrowed by " + name + ":");
        for (Book book : currentlyBorrowed) {
            System.out.println(book);
        }
    }

    public void borrowHistory() { //Display list of books borrow
        for (Book book : borrowingHistory) {
            System.out.println(book);
        }
    }
  
    @Override
    public String toString() { //String 
        return ID + " " + name;
    }

    public void addBook(Book book) { //Adding book to Patron 
        currentlyBorrowed.add(book);
        borrowingHistory.add(book);
    }

    public void removeBook(Book book) { //Removing book from Patron
        currentlyBorrowed.remove(book);
    }

    public void removeBooks(Catalogue catalogue) { //Remove all Patron book from loan 
        for (int i = 0; i < currentlyBorrowed.size(); i++) {
            catalogue.removeBook(currentlyBorrowed.get(i).title); // Remove That book from Loan 
        }

        //Adding book back to the shelf
        for (Book book : currentlyBorrowed) {
            catalogue.addBook(book);
        }
    }     

    public void favourite() { //Find favourite 
        ArrayList <Book> listBook = new ArrayList<Book>(); //List of the book mention once
        
        for (Book book : borrowingHistory) { //Get the list of books but mention it once 
            boolean found = false;
            for (int i = 0; i < listBook.size(); i++) {
                if (listBook.get(i).equals(book)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                listBook.add(book);
            }
        }
        
        int[] count = new int[listBook.size()]; //How many times it is repeated 
        
        
        for (int i = 0; i < listBook.size(); i++) { //Count how many times it is repeated 
            for (Book book : borrowingHistory) {
                if (listBook.get(i).equals(book)) {
                    count[i]++;
                }
            }
        }
        
        int first = 0;
        int firstCount = 0;
        int second = 0;
        int secondCount = 0;
        int thrid = 0;
        int thirdCount = 0;
        
        for (int i = 0; i < count.length; i++) { //Find the first highest 
            if (count[i] > firstCount) {
                firstCount = count[i];
                first = i;
            }
        }
        
        for (int i = 0; i < count.length; i++) { //Find second highest 
            if (count[i] > secondCount && i != first) {
                secondCount = count[i];
                second = i;
            }
        }       
        
        for (int i = 0; i < count.length; i++) { //Find third highest 
            if (count[i] > thirdCount && i != first && i != second) {
                thirdCount = count[i];
                thrid = i;
            }
        }
        
        System.out.println(listBook.get(first).toString()); //Display First 
        
        if (secondCount != 0) { //Display Second
            System.out.println(listBook.get(second).toString());
        }
        
        if (thirdCount != 0) { //Display Third 
            System.out.println(listBook.get(thrid).toString());
        }
    }
}
