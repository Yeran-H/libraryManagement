
public class Author {

    private String name; //Setting name 
    // write your solution below

    public Author(String name) { //Constructor 
        this.name = name;
    }

    @Override
    public String toString() { //String 
        return name;
    }

    public boolean exist(String author) { //Check if Exist
        return name.equals(author);
    }
}
