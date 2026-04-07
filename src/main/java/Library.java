import Utilities.Code;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Library {
    //CONSTANTS
    public static final int LENDING_LIMIT = 5;

    //VARIABLES
    private HashMap<Book, Integer> books;
    private static int libraryCard;
    private String name;
    private List<Reader> readers;
    private HashMap<String, Shelf> shelves;

    //CONSTRUCTOR
    public Library(String name) {
        this.name = name;
        this.books = new HashMap<>();
        this.readers = new ArrayList<>();
        this.shelves = new HashMap<>();
    }

    //CONVERTER METHODS
    //CONVERT-INT
    public static int convertInt(String string, Code code){
        try {
            int convertedInt = Integer.parseInt(string);
            return convertedInt;
        }
        catch(NumberFormatException e){
            if(code == Code.BOOK_COUNT_ERROR){
                System.out.println("Value which caused the error: " + string);
                System.out.println("Error message: " + code.getMessage());
                System.out.println("Error: Could not read number of books");
                return code.getCode();
            }
            else if(code == Code.PAGE_COUNT_ERROR){
                System.out.println("Value which caused the error: " + string);
                System.out.println("Error message: " + code.getMessage());
                System.out.println("Error: could not parse page count");
                return code.getCode();
            }
            else if(code == Code.DATE_CONVERSION_ERROR){
                System.out.println("Value which caused the error: " + string);
                System.out.println("Error message: " + code.getMessage());
                System.out.println("Error: Could not parse date component");
                return code.getCode();
            }
            else{
                System.out.println("Value which caused the error: " + string);
                System.out.println("Error message: " + code.getMessage());
                System.out.println("Error: Unknown conversion error");
                return code.getCode();
            }
        }
    }
    //CONVERT-DATE
    public static LocalDate convertDate(String string, Code code){
        if(string.equals("0000")) {
            return LocalDate.of(1970, 1, 1);
        }
        //splits the string by "-" from format "yyyy-mm-dd"
        String[] datePieces = string.split("-");

        //checks if the string was not broken up into 3 pieces
        if(datePieces.length != 3){
            System.out.println("ERROR: date conversion error, could not parse " + string);
            System.out.println("Using default date (01-jan-1970)");
            return LocalDate.of(1970, 1, 1);
        }
        //converts pieces into integers
        int year = convertInt(datePieces[0], code);
        int month = convertInt(datePieces[1], code);
        int day = convertInt(datePieces[2], code);

        //checks if pieces are less than 0
        if (year < 0 || month < 0 || day < 0){
            if (year < 0) {
                System.out.println("Error converting date: Year " + year);
            }
            if (month < 0) {
                System.out.println("Error converting date: Month " + month);
            }
            if (day < 0) {
                System.out.println("Error converting date: Day " + day);
            }
            System.out.println("Using default date (01-jan-1970)");
            return LocalDate.of(1970, 1, 1);
        }
        else{
            return LocalDate.of(year, month, day);
        }
    }


    //BOOK METHODS
    public Code addBook(Book book){return null;}
    public Code addBookToShelf(Book book, Shelf shelf){return null;}
    public int listBooks(){return 0;}
    public Code checkOutBook(Reader reader, Book book){return null;}
    public Book getBookByISBN(String string){return null;}
    public Code returnBook(Reader reader, Book book){return null;}
    public Code returnBook(Book book){return null;}

    //READER METHODS
    public Code addReader(Reader reader){return null;}
    public int listReaders(){return 0;}
    public int listReaders(boolean bool){return 0;}
    public Reader getReaderByCard(int integer){return null;}
    public Code removeReader(Reader reader){return null;}

    //SHELF METHODS
    public Code addShelf(Shelf shelf){return null;}
    public Code addShelf(String string){return null;}
    public Shelf getShelf(String string){return null;}
    public Shelf getShelf(Integer integer){return null;}
    public int listShelves(boolean bool){return 0;}
    public int listShelves(){return 0;}

    //INIT METHODS
    public Code init(String string){return null;}
    private Code initBooks(int integer, Scanner scanner){return null;}
    private Code initReader(int integer, Scanner scanner){return null;}
    private Code initShelves(int integer, Scanner scanner){return null;}

    //METHODS
    private Code errorCode(int integer){return null;}
    static public int getLibraryCardNumber(){return 0;}
    public String getName(){return name;}

}
