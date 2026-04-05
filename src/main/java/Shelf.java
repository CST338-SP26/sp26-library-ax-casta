import Utilities.Code;

import java.util.HashMap;
import java.util.Objects;

public class Shelf {
    public static final int SHELF_NUMBER_ = 0;
    public static final int SUBJECT_ = 1;
    private HashMap<Book, Integer> books;
    private int shelfNumber;
    private String subject;

    public Code addBook(Book book){return null;}
    public int getBookCount(Book book){return 0;}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Shelf shelf = (Shelf) o;
        return shelfNumber == shelf.shelfNumber && Objects.equals(subject, shelf.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shelfNumber, subject);
    }

    @Override
    public String toString(){
        return shelfNumber + " : " + subject;
    }

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
