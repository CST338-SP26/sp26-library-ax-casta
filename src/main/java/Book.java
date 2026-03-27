import java.time.LocalDate;
import java.util.Objects;

/**
 * Author: Alexander Castaneda
 * Title: Project_01 Part 1 - Book
 * Date: 3/25/2026
 */

public class Book {
    //CONSTANTS
    public static final int ISBN_ = 0;
    public static final int TITLE_ = 1;
    public static final int SUBJECT_ = 2;
    public static final int PAGE_COUNT_ = 3;
    public static final int AUTHOR_ = 4;
    public static final int DUE_DATE_ = 5;

    //VARIABLES
    private String author;
    private LocalDate dueDate;
    private String isbn;
    private int pageCount;
    private String subject;
    private String title;

    //CONSTRUCTOR
    public Book(String isbn, String title, String subject, int pageCount, String author, LocalDate dueDate) {
        this.isbn = isbn;
        this.title = title;
        this.subject = subject;
        this.pageCount = pageCount;
        this.author = author;
        this.dueDate = dueDate;
    }

    //EQUALS
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return pageCount == book.pageCount && Objects.equals(author, book.author) && Objects.equals(isbn, book.isbn) && Objects.equals(subject, book.subject) && Objects.equals(title, book.title);
    }

    //HASHCODE
    @Override
    public int hashCode() {
        return Objects.hash(author, isbn, pageCount, subject, title);
    }

    //TOSTRING
    @Override
    public String toString() {
        //formats in the order of [title] by [author] ISBN: [isbn]
        return title + " by " + author + " ISBN: " + isbn;
    }

    //GETTERS AND SETTERS
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getISBN() {
        return isbn;
    }

    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}