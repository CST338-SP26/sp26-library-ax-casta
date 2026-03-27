import Utilities.Code;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Author: Alexander Castaneda
 * Title: Project_01 Part 2 - Reader
 * Date: 3/26/2026
 */

public class Reader {
    //CONSTANTS
    public static final int CARD_NUMBER_ = 0;
    public static final int NAME_ = 1;
    public static final int PHONE_ = 2;
    public static final int BOOK_COUNT_ = 3;
    public static final int BOOK_START_ = 4;

    //VARIABLES
    private int cardNumber;
    private String name;
    private String phone;
    private List<Book> books;

    //CONSTRUCTOR
    public Reader(int cardNumber, String name, String phone) {
        this.cardNumber = cardNumber;
        this.name = name;
        this.phone = phone;
        this.books = new ArrayList<>();
    }

    //ADD-BOOK
    //public Code addBook(Book book){}
    //REMOVE-BOOK
    //public Code removeBook(Book book){}
    //HAS-BOOK
    //public boolean hasBook(Book book){}

    //TO-STRING
    @Override
    public String toString(){return "java";}

    //EQUALS
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return cardNumber == reader.cardNumber && Objects.equals(name, reader.name) && Objects.equals(phone, reader.phone);
    }

    //HASHCODE
    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, name, phone);
    }

    //GETTERS AND SETTERS
    //getBookCount()

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
