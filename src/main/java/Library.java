import Utilities.Code;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Library {
    public static final int LENDING_LIMIT = 5;
    private HashMap<Book, Integer> books;
    private static int libraryCard;
    private String name;
    private List<Reader> readers;
    private HashMap<String, Shelf> shelves;

    public Library(String name) {
        this.name = name;
    }

    public Code addBook(Book book){return null;}
    public Code addBookToShelf(Book book, Shelf shelf){return null;}
    public Code addReader(Reader reader){return null;}
    public Code addShelf(Shelf shelf){return null;}
    public Code addShelf(String string){return null;}
    public static LocalDate convertDate(String string, Code code){return null;}
    public Code checkOutBook(Reader reader, Book book){return null;}
    public static int convertInt(String string, Code code){return 0;}
    private Code errorCode(int integer){return null;}
    public Book getBookByISBN(String string){return null;}
    static public int getLibraryCardNumber(){return 0;}
    public String getName(){return name;}
    public Reader getReaderByCard(int integer){return null;}
    public Shelf getShelf(String string){return null;}
    public Shelf getShelf(Integer integer){return null;}
    public Code init(String string){return null;}
    private Code initBooks(int integer, Scanner scanner){return null;}
    private Code initReader(int integer, Scanner scanner){return null;}
    private Code initShelves(int integer, Scanner scanner){return null;}
    public int listBooks(){return 0;}
    public int listReaders(){return 0;}
    public int listReaders(boolean bool){return 0;}
    public int listShelves(boolean bool){return 0;}
    public int listShelves(){return 0;}
    public Code removeReader(Reader reader){return null;}
    public Code returnBook(Reader reader, Book book){return null;}
    public Code returnBook(Book book){return null;}

}
