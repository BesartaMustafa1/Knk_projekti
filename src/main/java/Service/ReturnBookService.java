package Service;

import Model.ReturnBook;
import Model.filter.ReturnBookFilter;
import Repository.ReturnBookRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReturnBookService {
    private ReturnBookRepository returnBookRepository;

    public ReturnBookService(ReturnBookRepository returnBookRepository) {
        this.returnBookRepository = returnBookRepository;
    }

    public List<ReturnBook> getAllReturnBooks() throws SQLException {
        return returnBookRepository.getAllReturnBooks();
    }
    public List<ReturnBook> filterReturnBook(ReturnBookFilter filter) throws SQLException {
        try (Connection conn = Database.DatabaseUtil.getConnection()) {
            return ReturnBookRepository.filterReturnBook(conn, filter);
        }
    }
    public void returnBook(int studentID, int bookID) throws SQLException {
        returnBookRepository.updateBookQuantity(bookID);
        returnBookRepository.deleteStudent(studentID);
    }
}
