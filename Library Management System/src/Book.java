
import java.util.ArrayList;
import java.util.List;

public class Book {

    public String title; //Setting Title which can be used in other classes
    private Author author; //Setting author
    private Genre genre; //Setting genre
    private ArrayList<Patron> holds = new ArrayList<Patron>(); // List of Patron on Hold 
    // write your solution below

    public Book(String title, Author author, Genre genre) { //Consturctor
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    @Override
    public String toString() { //String
        return author + ", " + title;
    }

    public boolean existTitle(String title) { //Checking if Exist
        return this.title.equals(title);
    } 

    public boolean existGenre(String word) { //Check if genre is the same 
        return genre.toString().equals(word);
    }

    public boolean existAuthor(String word) { //Check if author is the same
        return author.toString().equals(word);
    }

    public boolean isHold(Patron patron) { //Check if patron in hold 
        for (Patron hold : holds) {
            if (hold.exist(patron.ID)) {
                return true;
            } 
        }

        return false;
    }

    public void addHold(Patron patron) { //Add patron to hold 
        holds.add(patron);
    }

    public boolean onHold(Patron patron) { //Display first in hold list 
        if (holds.isEmpty()) {
            return true;
        }
        return patron.equals(holds.get(0));
    }

    public void removeHold() { //Remove patron from hold 
        if (!holds.isEmpty()) {
            holds.remove(0);
        }
    }

    public boolean emptyHold() { //Check if hold is empty 
        return holds.isEmpty();
    }
}
