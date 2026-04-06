import Utilities.Code;

import java.util.HashMap;
import java.util.Objects;

public class Shelf {
    //CONSTANTS
    public static final int SHELF_NUMBER_ = 0;
    public static final int SUBJECT_ = 1;

    //VARIABLES
    private HashMap<Book, Integer> books;
    private int shelfNumber;
    private String subject;

    //CONSTRUCTORS
    public Shelf(int shelfNumber, String subject) {
        this.subject = subject;
        this.shelfNumber = shelfNumber;
        this.books = new HashMap<>();
    }
    public Shelf() {
    }

    //METHODS
    public Code addBook(Book book){
        if (books.containsKey(book)){
            books.put(book, books.get(book) + 1);
            return Code.SUCCESS;
        }
        else{
            if(book.getSubject().equals(this.subject)){
                books.put(book, 1);
                return Code.SUCCESS;
            }
            else{
                return Code.SHELF_SUBJECT_MISMATCH_ERROR;
            }
        }
    }
    public int getBookCount(Book book){return 0;}
    public Code removeBook(Book book){return null;}
    public String listBooks(){return "";}

    //EQUALS
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Shelf shelf = (Shelf) o;
        return shelfNumber == shelf.shelfNumber && Objects.equals(subject, shelf.subject);
    }

    //HASHCODE
    @Override
    public int hashCode() {
        return Objects.hash(shelfNumber, subject);
    }

    //TO-STRING
    @Override
    public String toString(){
        return shelfNumber + " : " + subject;
    }

    //GETTERS AND SETTERS
    public HashMap<Book, Integer> getBooks() {
        return books;
    }

    public void setBooks(HashMap<Book, Integer> books) {
        this.books = books;
    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
