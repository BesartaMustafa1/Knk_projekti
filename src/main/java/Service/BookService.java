package Service;

import Model.Book;
import Model.dto.BookDto;
import Repository.BookRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class BookService {
    private final BookRepository bookRepository = new BookRepository();

    public int create(Book bookData) {
        return bookRepository.create(bookData);
    }

    public boolean updateBook(Book book) {
        BookDto bookDto = new BookDto(
                book.getBookID(),
                book.getBookName(),
                book.getAutor(),
                book.getQuantity()
        );
        return bookRepository.update(bookDto);
    }

    public ObservableList<Book> getAllBooks() throws SQLException {
        List<Book> books = bookRepository.getAllBooks();
        return FXCollections.observableArrayList(books);
    }

    public boolean deleteBook(int bookId) {
        return bookRepository.deleteBook(bookId);
    }


    public Book getBookById(int bookId) {
        return bookRepository.getBookById(bookId);
    }
    public boolean decreaseQuantity(int bookId, int amount) {
        Book book = bookRepository.getBookById(bookId);
        if (book != null && book.getQuantity() >= amount) {
            book.setQuantity(book.getQuantity() - amount);
            BookDto bookDto = new BookDto(
                    book.getBookID(),
                    book.getBookName(),
                    book.getAutor(),
                    book.getQuantity()
            );
            return bookRepository.update(bookDto);
        }
        return false; // Nese libri nuk ekziston ose nuk ka sasi të mjaftueshme
    }

}
