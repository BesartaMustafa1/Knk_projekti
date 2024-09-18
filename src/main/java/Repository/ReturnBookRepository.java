package Repository;

import Model.ReturnBook;
import Model.filter.ReturnBookFilter;
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
        String query = "SELECT StudentID, BookID FROM Student WHERE BookID IS NOT NULL";
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
    public static List<ReturnBook> filterReturnBook(Connection conn, ReturnBookFilter filter) throws SQLException {
        List<ReturnBook> returnBooks = new ArrayList<>();
        String query = "SELECT * FROM ReturnBook WHERE StudentID = ? AND BookID = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, filter.getStudentId());
            pst.setInt(2, filter.getBookId());

            // Ekzekuto query-n dhe përpuno rezultatin
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    ReturnBook returnBook = getFromResultSet(rs);
                    if (returnBook != null) {
                        returnBooks.add(returnBook);
                    }
                }
            }
        }
        return returnBooks;
    }

    public static ReturnBook getFromResultSet(ResultSet rs){
        try{
            return new ReturnBook(rs.getInt("StudentID"),
                    rs.getInt("BookID"));
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
