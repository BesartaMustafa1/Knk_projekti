package Service;

import Model.ReturnBook;
import Repository.ReturnBookRepository;

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

    public void returnBook(int studentID, int bookID) throws SQLException {
        returnBookRepository.updateBookQuantity(bookID);
        returnBookRepository.deleteStudent(studentID);
    }
}
