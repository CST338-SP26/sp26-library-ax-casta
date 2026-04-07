import Utilities.Code;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Author: Alexander Castaneda
 * Title: Project_01 Part 4 - Library
 * Date: 4/6/2026
 */

public class Library {
    //CONSTANTS
    public static final int LENDING_LIMIT = 5;

    //VARIABLES
    private final HashMap<Book, Integer> books;
    private static int libraryCard;
    private final String name;
    private final List<Reader> readers;
    private final HashMap<String, Shelf> shelves;

    //CONSTRUCTOR
    public Library(String name) {
        this.name = name;
        this.books = new HashMap<>();
        this.readers = new ArrayList<>();
        this.shelves = new HashMap<>();
    }

    //CONVERTER METHODS
    //CONVERT-INT
    public static int convertInt(String recordCountString, Code code){
        try {
            return Integer.parseInt(recordCountString);
        }
        catch(NumberFormatException e){
            if(code == Code.BOOK_COUNT_ERROR){
                System.out.println("Value which caused the error: " + recordCountString);
                System.out.println("Error message: " + code.getMessage());
                System.out.println("Error: Could not read number of books");
                return code.getCode();
            }
            else if(code == Code.PAGE_COUNT_ERROR){
                System.out.println("Value which caused the error: " + recordCountString);
                System.out.println("Error message: " + code.getMessage());
                System.out.println("Error: could not parse page count");
                return code.getCode();
            }
            else if(code == Code.DATE_CONVERSION_ERROR){
                System.out.println("Value which caused the error: " + recordCountString);
                System.out.println("Error message: " + code.getMessage());
                System.out.println("Error: Could not parse date component");
                return code.getCode();
            }
            else{
                System.out.println("Value which caused the error: " + recordCountString);
                System.out.println("Error message: " + code.getMessage());
                System.out.println("Error: Unknown conversion error");
                return code.getCode();
            }
        }
    }
    //CONVERT-DATE
    public static LocalDate convertDate(String date, Code errorCode){
        if(date.equals("0000")) {
            return LocalDate.of(1970, 1, 1);
        }
        //splits the string by "-" from format "yyyy-mm-dd"
        String[] datePieces = date.split("-");

        //checks if the string was not broken up into 3 pieces
        if(datePieces.length != 3){
            System.out.println("ERROR: date conversion error, could not parse " + date);
            System.out.println("Using default date (01-jan-1970)");
            return LocalDate.of(1970, 1, 1);
        }
        //converts pieces into integers
        int year = convertInt(datePieces[0], errorCode);
        int month = convertInt(datePieces[1], errorCode);
        int day = convertInt(datePieces[2], errorCode);

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
    //ADD-BOOK
    public Code addBook(Book newBook){
        if(books.containsKey(newBook)){
            int count = books.get(newBook) + 1;
            books.put(newBook, count);
            System.out.println(count + " copies of " + newBook + " in the stacks");
        }
        else{
            books.put(newBook, 1);
            System.out.println(newBook + " added to the stacks.");
        }
        Shelf shelf = getShelf(newBook.getSubject());
        if(shelf != null){
            shelf.addBook(newBook);
            return Code.SUCCESS;
        }
        else{
            System.out.println("No shelf for " + newBook.getSubject() + " books");
            return Code.SHELF_EXISTS_ERROR;
        }
    }

    //CHECK-OUT-BOOK
    public Code checkOutBook(Reader reader, Book book){
        if(reader == null){
            System.out.println("Reader doesn't have an account here");
            return Code.READER_NOT_IN_LIBRARY_ERROR;
        }
        if(!readers.contains(reader)){
            System.out.println(reader.getName() + " doesn't have an account here");
            return Code.READER_NOT_IN_LIBRARY_ERROR;
        }
        if(reader.getBooks().size() >= LENDING_LIMIT){
            System.out.println(reader.getName() + " has reached the lending limit, (" + LENDING_LIMIT + ")");
            return Code.BOOK_LIMIT_REACHED_ERROR;
        }
        if(!books.containsKey(book)){
            System.out.println("ERROR: could not find " + book);
            return Code.BOOK_NOT_IN_INVENTORY_ERROR;
        }

        Shelf shelf = getShelf(book.getSubject());

        if(shelf == null){
            System.out.println("no shelf for " + book.getSubject() + " books!");
            return Code.SHELF_EXISTS_ERROR;
        }
        if(shelf.getBookCount(book) < 1){
            System.out.println("ERROR: no copies of " + book + " remain");
            return Code.BOOK_NOT_IN_INVENTORY_ERROR;
        }
        Code addCode = reader.addBook(book);
        if(addCode != Code.SUCCESS){
            System.out.println("Couldn't checkout " + book);
            return addCode;
        }

        Code removeCode = shelf.removeBook(book);
        if(removeCode == Code.SUCCESS){
            System.out.println(book + " checked out successfully");
        }
        return removeCode;
    }

    //RETURN-BOOK
    public Code returnBook(Reader reader, Book book){
        if(!reader.hasBook(book)){
            System.out.println(reader.getName() + " doesn't have " + book + " checked out");
            return Code.READER_DOESNT_HAVE_BOOK_ERROR;
        }
        if(!books.containsKey(book)){
            return Code.BOOK_NOT_IN_INVENTORY_ERROR;
        }

        System.out.println(reader.getName() + " is returning " + book);

        Code code = reader.removeBook(book);
        if(code == Code.SUCCESS){
            return returnBook(book);
        }
        else{
            System.out.println("Could not return " + book);
            return code;
        }
    }

    //ADD-BOOK-TO-SHELF
    public Code addBookToShelf(Book book, Shelf shelf){
        Code returnCode = returnBook(book);
        if(returnCode == Code.SUCCESS){
            return Code.SUCCESS;
        }
        if(!shelf.getSubject().equalsIgnoreCase(book.getSubject())){
            return Code.SHELF_SUBJECT_MISMATCH_ERROR;
        }
        Code code = shelf.addBook(book);
        if(code == Code.SUCCESS){
            System.out.println(book + " added to shelf");
            return Code.SUCCESS;
        }
        else{
            System.out.println("Could not add " + book + " to shelf");
            return code;
        }
    }

    //LIST-BOOKS
    public int listBooks(){
        int total = 0;

        for(Book book : books.keySet()){
            int count = books.get(book);
            total += count;
            System.out.println(count + " copies of " + book);
        }
        return total;
    }

    //GET-BOOK-BY-ISBN
    public Book getBookByISBN(String string){
        for(Book book : books.keySet()){
            if(book.getISBN().equals(string)){
                return book;
            }
        }
        System.out.println("ERROR: Could not find a book with isbn: " + string);
        return null;
    }

    //RETURN-BOOK
    public Code returnBook(Book book){
        Shelf shelf = getShelf(book.getSubject());

        if(shelf == null){
            System.out.println("No shelf for " + book);
            return Code.SHELF_EXISTS_ERROR;
        }
        return shelf.addBook(book);
    }

    //READER METHODS
    //ADD-READER
    public Code addReader(Reader reader){
        if(readers.contains(reader)){
            System.out.println(reader.getName() + " already has an account!");
            return Code.READER_ALREADY_EXISTS_ERROR;
        }

        for(Reader r : readers){
            if(r.getCardNumber() == reader.getCardNumber()){
                System.out.println(r.getName() + " and " + reader.getName() + " have the same card number!");
                return Code.READER_CARD_NUMBER_ERROR;
            }
        }

        readers.add(reader);
        System.out.println(reader.getName() + " added to the library!");

        if(reader.getCardNumber() > libraryCard){
            libraryCard = reader.getCardNumber();
        }
        return Code.SUCCESS;
    }

    //REMOVE-READER
    public Code removeReader(Reader reader){
        if(!readers.contains(reader)){
            System.out.println(reader + " is not part of this Library");
            return Code.READER_NOT_IN_LIBRARY_ERROR;
        }

        if(reader.getBooks().size() > 0){
            System.out.println(reader.getName() + " must return all books!");
            return Code.READER_STILL_HAS_BOOKS_ERROR;
        }
        readers.remove(reader);
        return Code.SUCCESS;
    }

    //LIST-READERS
    public int listReaders(boolean showBooks){
        if(!showBooks){
            return listReaders();
        }

        for(Reader reader : readers){
            System.out.println(reader.getName() + "(#" + reader.getCardNumber() + ")  has the following books:  ");
            System.out.println(reader.getBooks());
        }
        return readers.size();
    }

    //LIST-READERS
    public int listReaders(){
        for(Reader reader : readers){
            System.out.println(reader);
        }
        return readers.size();
    }

    //GET-READER-BY-CARD
    public Reader getReaderByCard(int cardNumber){
        for(Reader reader : readers){
            if(reader.getCardNumber() == cardNumber){
                return reader;
            }
        }
        System.out.println("Could not find a reader with card #" + cardNumber);
        return null;
    }

    //SHELF METHODS
    //ADD-SHELF
    public Code addShelf(Shelf shelf){
        //checks for duplicates
        if(shelves.containsKey(shelf.getSubject())){
            System.out.println("ERROR: Shelf already exists " + shelf);
            return Code.SHELF_EXISTS_ERROR;
        }
        //assign next shelf number and add to hashmap
        shelf.setShelfNumber(shelves.size() + 1);
        shelves.put(shelf.getSubject(), shelf);

        //add existing matching books
        for(Book book : books.keySet()){
            if(book.getSubject().equalsIgnoreCase(shelf.getSubject())){
                int count = books.get(book);
                for(int i = 0; i < count; i++){
                    shelf.addBook(book);
                }
            }
        }
        return Code.SUCCESS;
    }

    //ADD-SHELF
    public Code addShelf(String shelfSubject){
        Shelf newShelf = new Shelf(shelves.size() + 1, shelfSubject);
        return addShelf(newShelf);
    }

    //LIST-SHELVES
    public int listShelves(boolean showBooks){
        for(Shelf shelf : shelves.values()){
            if(showBooks){
                System.out.println(shelf.listBooks());
            }
            else{
                System.out.println(shelf);
            }
        }
        return shelves.size();
    }

    //LIST-SHELVES
    public int listShelves(){
        return listShelves(false);
    }

    //GET-SHELF
    public Shelf getShelf(String subject){
        if(shelves.containsKey(subject)){
            return shelves.get(subject);
        }
        System.out.println("No shelf for " + subject + " books");
        return null;
    }

    //GET-SHELF
    public Shelf getShelf(Integer shelfNumber){
        for(Shelf shelf : shelves.values()){
            if(shelf.getShelfNumber() == shelfNumber){
                return shelf;
            }
        }
        System.out.println("No shelf number " + shelfNumber + " found");
        return null;
    }

    //INIT METHODS
    //INIT
    public Code init(String filename){
        try{
            Scanner scan = new Scanner(new File(filename));

            int bookCount = convertInt(scan.nextLine(), Code.BOOK_COUNT_ERROR);
            if(bookCount < 0){
                return errorCode(bookCount);
            }

            Code bookCode = initBooks(bookCount, scan);
            if (bookCode != Code.SUCCESS){
                return bookCode;
            }
            listBooks();

            int shelfCount = convertInt(scan.nextLine(), Code.SHELF_COUNT_ERROR);
            if (shelfCount < 0){
                return errorCode(shelfCount);
            }

            Code shelfCode = initShelves(shelfCount, scan);
            if (shelfCode != Code.SUCCESS){
                return shelfCode;
            }
            listShelves(true);

            int readerCount = convertInt(scan.nextLine(), Code.READER_COUNT_ERROR);
            if (readerCount < 0){
                return errorCode(readerCount);
            }

            Code readerCode = initReader(readerCount, scan);
            if(readerCode != Code.SUCCESS){
                return readerCode;
            }
            listReaders(true);

            scan.close();
            return Code.SUCCESS;
        }
        catch (FileNotFoundException e){
            return Code.FILE_NOT_FOUND_ERROR;
        }
    }

    //INIT-BOOKS
    private Code initBooks(int bookCount, Scanner scan){
        if(bookCount < 1){
            return Code.LIBRARY_ERROR;
        }
        for(int i = 0;i < bookCount;i++){
            String line = scan.nextLine();
            String[] bookPieces = line.split(",");

            if(bookPieces.length <= Book.DUE_DATE_) {
                return Code.BOOK_RECORD_COUNT_ERROR;
            }
            String isbn = bookPieces[Book.ISBN_];
            String title = bookPieces[Book.TITLE_];
            String subject = bookPieces[Book.SUBJECT_];
            String pageCountString = bookPieces[Book.PAGE_COUNT_];
            String author = bookPieces[Book.AUTHOR_];
            String dateString = bookPieces[Book.DUE_DATE_];

            int pageCountInt = convertInt(pageCountString, Code.PAGE_COUNT_ERROR);
            if(pageCountInt <= 0){
                return Code.PAGE_COUNT_ERROR;
            }
            LocalDate dateLocalDate = convertDate(dateString, Code.DATE_CONVERSION_ERROR);
            if(dateLocalDate == null){
                return Code.DATE_CONVERSION_ERROR;
            }
            Book newBook = new Book(isbn, title, subject, pageCountInt, author, dateLocalDate);
            addBook(newBook);
        }
        return Code.SUCCESS;
    }
    //INIT-READER
    private Code initReader(int readerCount, Scanner scan){
        if(readerCount <= 0){
            return Code.READER_COUNT_ERROR;
        }
        for(int i = 0; i < readerCount; i++) {
            String line = scan.nextLine();
            String[] tokens = line.split(",");

            int cardNumber = convertInt(tokens[Reader.CARD_NUMBER_], Code.READER_COUNT_ERROR);
            String name = tokens[Reader.NAME_];
            String phone = tokens[Reader.PHONE_];

            Reader reader = new Reader(cardNumber, name, phone);
            this.addReader(reader);

            int bookCount = convertInt(tokens[Reader.BOOK_COUNT_], Code.READER_COUNT_ERROR);
            for (int j = 0; j < bookCount; j++) {
                int index = Reader.BOOK_START_ + (j * 2);
                String isbn = tokens[index];
                Book book = getBookByISBN(isbn);
                if (book == null) {
                    System.out.println("ERROR");
                    continue;
                }
                LocalDate dueDate = convertDate(tokens[index + 1], Code.DATE_CONVERSION_ERROR);
                book.setDueDate(dueDate);
                checkOutBook(reader, book);
            }
        }
        return Code.SUCCESS;
    }

    //INIT-SHELVES
    private Code initShelves(int shelfCount, Scanner scan){
        if(shelfCount < 1){
            return Code.SHELF_COUNT_ERROR;
        }
        for(int i = 0;i < shelfCount;i++){
            String line = scan.nextLine();
            String[] shelfPieces = line.split(",");
            if(shelfPieces.length < 2){
                return Code.SHELF_NUMBER_PARSE_ERROR;
            }
            String shelfNumberString = shelfPieces[Shelf.SHELF_NUMBER_];
            String subject = shelfPieces[Shelf.SUBJECT_];

            int shelfNumberInt = convertInt(shelfNumberString, Code.SHELF_NUMBER_PARSE_ERROR);
            if(shelfNumberInt < 0){
                return Code.SHELF_NUMBER_PARSE_ERROR;
            }
            Shelf newShelf = new Shelf(shelfNumberInt, subject);
            addShelf(newShelf);
        }
        if(shelves.size() == shelfCount) {
            return Code.SUCCESS;
        }
        else{
            System.out.println("Number of shelves doesn't match expected");
            return Code.SHELF_NUMBER_PARSE_ERROR;
        }
    }

    //METHODS
    //ERROR-CODE
    private Code errorCode(int codeNumber) {
        for (Code code : Code.values()) {
            if (code.getCode() == codeNumber) {
                return code;
            }
        }
        return Code.UNKNOWN_ERROR;
    }
    //GET-LIBRARY-CARD-NUMBER
    public static int getLibraryCardNumber() {
        return libraryCard + 1;
    }
    //GET-NAME
    public String getName(){
        return name;
    }
}
