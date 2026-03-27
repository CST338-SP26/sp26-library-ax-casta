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
    public Code addBook(Book book){
        if (hasBook(book)){
            return Code.BOOK_ALREADY_CHECKED_OUT_ERROR;
        }
        else{
            books.add(book);
            return Code.SUCCESS;
        }
    }

    //REMOVE-BOOK
    public Code removeBook(Book book){
        if(!hasBook(book)){
            return Code.READER_DOESNT_HAVE_BOOK_ERROR;
        }
        boolean removeSuccess = books.remove(book);
        if (removeSuccess){
            return Code.SUCCESS;
        }
        else{
            return Code.READER_COULD_NOT_REMOVE_BOOK_ERROR;
        }
    }

    //HAS-BOOK
    public boolean hasBook(Book book){
        return books.contains(book);
    }

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
    public int getBookCount(){
        return books.size();
    }

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
