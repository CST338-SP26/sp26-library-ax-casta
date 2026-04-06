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

    //ADDBOOK
    public Code addBook(Book book){
        //checks if hashmap contains book
        if (books.containsKey(book)){
            //increments books hashmap
            books.put(book, books.get(book) + 1);
            System.out.println(book.toString() + " added to shelf " + this.toString());
            return Code.SUCCESS;
        }
        //checks if book equals this subject
        else if(book.getSubject().equals(this.subject)){
            books.put(book, 1);
            System.out.println(book.toString() + " added to shelf " + this.toString());
            return Code.SUCCESS;
        }
        //error
        else{
            return Code.SHELF_SUBJECT_MISMATCH_ERROR;
        }
    }

    //REMOVEBOOK
    public Code removeBook(Book book){
        //checks if hashmap does not contain book
        if (!books.containsKey(book)){
            System.out.println(book.getTitle() + " is not on shelf " + this.subject);
            return Code.BOOK_NOT_IN_INVENTORY_ERROR;
        }
        //checks if book count is 0
        else if(getBookCount(book) == 0){
            System.out.println("No copies of " + book.getTitle() + " remain on shelf " + this.subject);
            return Code.BOOK_NOT_IN_INVENTORY_ERROR;
        }
        //successful book removal
        else{
            books.put(book, books.get(book) - 1);
            System.out.println(book.getTitle() + " successfully removed from shelf " + this.subject);
            return Code.SUCCESS;
        }
    }

    //GETBOOKCOUNT
    public int getBookCount(Book book){
        //checks if hashmap does not contain book
        if (!books.containsKey(book)) {
            return -1;
        }
        //returns book
        else{
            return books.get(book);
        }
    }

    //LISTBOOKS
    public String listBooks(){
        int bookNum = 0;
        String bookList = "";
        //builds bookNum
        for(Integer count : books.values()){
            bookNum += count;
        }
        //builds bookList
        for(Book book : books.keySet()){
            bookList = bookList + "\n" + book.toString() + " " + books.get(book);
        }
        //case if bookNum is 1
        if (bookNum == 1){
            return bookNum + " book on shelf: " + this.toString() + bookList;
        }
        //case if bookNum is not 1
        else{
            return bookNum + " books on shelf: " + this.toString() + bookList;
        }
    }

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
