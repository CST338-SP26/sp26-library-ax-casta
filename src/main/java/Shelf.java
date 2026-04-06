import Utilities.Code;

import java.util.HashMap;
import java.util.Objects;

/**
 * Author: Alexander Castaneda
 * Title: Project_01 Part 3 - Shelf
 * Date: 4/5/2026
 */

public class Shelf {
    //CONSTANTS
    public static final int SHELF_NUMBER_ = 0;
    public static final int SUBJECT_ = 1;

    //VARIABLES
    private HashMap<Book, Integer> books;
    private int shelfNumber;
    private String subject;

    //CONSTRUCTORS
    /**
     * Creates a Shelf with a shelf number and subject
     *
     * @param shelfNumber the shelf number
     * @param subject the book subject
     */
    public Shelf(int shelfNumber, String subject) {
        this.subject = subject;
        this.shelfNumber = shelfNumber;
        this.books = new HashMap<>();
    }
    /**
     * Creates an empty shelf
     */
    public Shelf() {
        this.books = new HashMap<>();
    }

    //ADD-BOOK
    /**
     * Adds book to the shelf if the subject matches the shelf.
     * If book already exists, increments count.
     *
     * @param book the book being added to the shelf
     * @return Code.SUCCESS if the book is added, or Code.SHELF_SUBJECT_MISMATCH_ERROR if subject does not match
     */
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

    //REMOVE-BOOK
    /**
     * removes a book from the shelf if the book is on the shelf
     *
     * @param book the book being removed
     * @return Code.BOOK_NOT_IN_INVENTORY_ERROR if book is not on the shelf or if there are no copies of the book, or Code.SUCCESS if book is removed.
     */
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

    //GET-BOOK-COUNT
    /**
     * returns number of copies of a book on shelf.
     *
     * @param book the book to check
     * @return the number of copies, or -1 if the book does not exist
     */
    public int getBookCount(Book book){
        //checks if hashmap does not contain book
        //returns book
        return books.getOrDefault(book, -1);
    }

    //LIST-BOOKS
    /**
     * Returns a formatted list of the books on shelf.
     *
     * @return a string listing all books and their counts on the shelf
     */
    public String listBooks(){
        int bookNum = 0;
        StringBuilder bookList = new StringBuilder();
        //builds bookNum
        for(Integer count : books.values()){
            bookNum += count;
        }
        //builds bookList
        for(Book book : books.keySet()){
            bookList.append("\n").append(book.toString()).append(" ").append(books.get(book));
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
    /**
     * Compares this shelf to another object.
     *
     * @param o the object being compared
     * @return true if the shelves have the same shelf number and subject
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Shelf shelf = (Shelf) o;
        return shelfNumber == shelf.shelfNumber && Objects.equals(subject, shelf.subject);
    }

    //HASHCODE
    /**
     * Returns a hash code for this shelf.
     *
     * @return a hash code based on shelf number and subject
     */
    @Override
    public int hashCode() {
        return Objects.hash(shelfNumber, subject);
    }

    //TO-STRING
    /**
     * Returns a string for the shelf.
     *
     * @return the shelf number and subject as [NUMBER] : [SUBJECT]
     */
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
