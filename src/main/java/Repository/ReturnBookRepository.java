package Repository;

import Model.ReturnBook;
import Service.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReturnBookRepository {
    private Connection connection;

    public ReturnBookRepository() {
        this.connection = DBConnector.getConnection();
    }

    public List<ReturnBook> getAllReturnBooks() throws SQLException {
        List<ReturnBook> returnBooks = new ArrayList<>();
        String query = "SELECT BookID, StudentID FROM ReturnBook";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int bookID = rs.getInt("BookID");
                int studentID = rs.getInt("StudentID");
                returnBooks.add(new ReturnBook(studentID, bookID));
            }
        }
        return returnBooks;
    }

    public void deleteStudent(int studentID) throws SQLException {
        String query = "DELETE FROM Student WHERE StudentID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, studentID);
            pstmt.executeUpdate();
        }
    }

    public void updateBookQuantity(int bookID) throws SQLException {
        String query = "UPDATE Book SET Quantity = Quantity + 1 WHERE BookID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, bookID);
            pstmt.executeUpdate();
        }
    }
}
