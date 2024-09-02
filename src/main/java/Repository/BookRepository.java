package Repository;

import Database.DatabaseUtil;
import Model.Book;
import Model.dto.BookDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    public int create(Book bookData) {
        String query = "INSERT INTO Book (BookID, BookName, Autor, Quantity) VALUES (?, ?, ?, ?)";
        int affectedRows = -1;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, bookData.getBookID()); // Vendos ID manualisht
            pst.setString(2, bookData.getBookName());
            pst.setString(3, bookData.getAutor());
            pst.setInt(4, bookData.getQuantity());
            affectedRows = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    public boolean update(BookDto bookData) {
        String query = "UPDATE Book SET BookName = ?, Autor = ?, Quantity = ? WHERE BookID = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, bookData.getBookName());
            pst.setString(2, bookData.getAutor());
            pst.setInt(3, bookData.getQuantity());
            pst.setInt(4, bookData.getBookID());
            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM Book";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Book book = getFromResultSet(rs);
                if (book != null) {
                    books.add(book);
                }
            }
        }
        return books;
    }

    private Book getFromResultSet(ResultSet rs) {
        try {
            int bookID = rs.getInt("BookID");
            String bookName = rs.getString("BookName");
            String autor = rs.getString("Autor");
            int quantity = rs.getInt("Quantity");

            return new Book(bookID, bookName, autor, quantity);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Book getBookById(int bookID) {
        String query = "SELECT * FROM Book WHERE BookID = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapToBookDto(rs);
            }
        } catch (SQLException e) {
            System.out.println("Database error when fetching book by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private static Book mapToBookDto(ResultSet rs) throws SQLException {
        int bookID = rs.getInt("BookID");
        String bookName = rs.getString("BookName");
        String autor = rs.getString("Autor");
        int quantity = rs.getInt("Quantity");

        return new Book(bookID, bookName, autor, quantity);
    }

    public boolean deleteBook(int bookID) {
        String query = "DELETE FROM Book WHERE BookID = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, bookID);
            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
