
public class Genre{

    private String name; //Setting Name 
    // write your solution below

    public Genre(String name) { //Constructor for Name 
        this.name = name.toLowerCase();
    }

    @Override
    public String toString() { //String 
        return name;
    }

    public boolean exist(String name) { //Checking if Genre Exist 
        return this.name.equals(name);
    }
}
