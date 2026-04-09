import Utilities.Code;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @version  1.3.1.
 */

class LibraryTest {

    Library csumb = null;

    String library00 = "Library00.csv";
    String library01 = "Library01.csv";
    String badBooks0 = "badBooks0.csv";
    String badBooks1 = "badBooks1.csv";
    String badShelves0 = "badShelves0.csv";
    String badShelves1 = "badShelves1.csv";
    String badReader0 = "badReader0.csv";
    String badReader1 = "badReader1.csv";

    @BeforeEach
    void setUp() {
        csumb = new Library("CSUMB");
    }

    @AfterEach
    void tearDown() {
        csumb = null;
    }

    @Test
    void init_test() {
        //Bad file
        assertEquals(Code.FILE_NOT_FOUND_ERROR, csumb.init("nope.csv"));
        assertEquals(Code.BOOK_COUNT_ERROR, csumb.init(badBooks0));
        assertEquals(Code.BOOK_RECORD_COUNT_ERROR, csumb.init(badBooks1) );
        assertEquals(Code.SHELF_COUNT_ERROR,csumb.init(badShelves0));
        assertEquals(Code.SHELF_NUMBER_PARSE_ERROR,csumb.init(badShelves1));
    }

    @Test
    void init_goodFile_test() {
        Code result = csumb.init(library00);
        assertEquals(Code.SUCCESS, result);
    }

    @Test
    void addBook() {
    }

    @Test
    void returnBook() {
        csumb.init(library00);
        Reader reader = csumb.getReaderByCard(1);
        Book book = csumb.getBookByISBN("42-w-87");
        csumb.checkOutBook(reader, book);
        Code result = csumb.returnBook(reader, book);
        assertNotNull(result);
    }

    @Test
    void testReturnBook() {
    }

    @Test
    void listBooks() {
        csumb.init(library00);
        int total = csumb.listBooks();
        assertTrue(total > 0);
    }

    @Test
    void checkOutBook() {
        csumb.init(library00);
        Reader reader = csumb.getReaderByCard(1);
        Book book = csumb.getBookByISBN("42-w-87");
        Code result = csumb.checkOutBook(reader, book);
        assertNotNull(result);
    }

    @Test
    void getBookByISBN() {
        csumb.init(library00);
        Book book = csumb.getBookByISBN("e1337");
        assertNotNull(book);
        Book missing = csumb.getBookByISBN("fake");
        assertNull(missing);
    }

    @Test
    void listShelves() {
    }

    @Test
    void addShelf() {
        Code result = csumb.addShelf("Fantasy");
        assertEquals(Code.SUCCESS, result);
        Shelf shelf = csumb.getShelf("Fantasy");
        assertNotNull(shelf);
    }

    @Test
    void testAddShelf() {
    }

    @Test
    void getShelf() {
    }

    @Test
    void testGetShelf() {
        csumb.init(library00);
        Shelf shelf = csumb.getShelf(1);
        assertNotNull(shelf);
        Shelf missing = csumb.getShelf(999);
        assertNull(missing);
    }

    @Test
    void listReaders() {
    }

    @Test
    void testListReaders() {
    }

    @Test
    void getReaderByCard() {
    }

    @Test
    void addReader() {
        Reader reader = new Reader(100, "Alex", "123");
        Code result = csumb.addReader(reader);
        assertEquals(Code.SUCCESS, result);
        Reader found = csumb.getReaderByCard(100);
        assertNotNull(found);
    }

    @Test
    void removeReader() {
        Reader reader = new Reader(101, "Test", "123");
        csumb.addReader(reader);
        Code result = csumb.removeReader(reader);
        assertEquals(Code.SUCCESS, result);
    }

    @Test
    void convertInt() {
        int value = Library.convertInt("10", Code.BOOK_COUNT_ERROR);
        assertEquals(10, value);
        int error = Library.convertInt("bad", Code.BOOK_COUNT_ERROR);
        assertTrue(error < 0);
    }

    @Test
    void convertDate() {
        LocalDate date = Library.convertDate("2020-10-10", Code.DATE_CONVERSION_ERROR);
        assertNotNull(date);
        LocalDate badDate = Library.convertDate("bad-date", Code.DATE_CONVERSION_ERROR);
        assertNotNull(badDate);
    }

    @Test
    void getLibraryCardNumber() {
        int num = Library.getLibraryCardNumber();
        assertTrue(num > 0);
    }
}
