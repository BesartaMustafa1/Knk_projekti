package Controllers;

import Model.Book;
import Service.BookService;
import app.SessionManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class BookController implements Initializable {

    @FXML
    private TextField txtBookID;
    @FXML
    private TextField txtBookName;
    @FXML
    private TextField txtAutor;
    @FXML
    private TextField txtQuantity;
    @FXML
    private TableView<Book> tableView;
    @FXML
    private TableColumn<Book, Integer> bookIDColumn;

    @FXML
    private TableColumn<Book, String> bookNameColumn;

    @FXML
    private TableColumn<Book, String> autorColumn;

    @FXML
    private TableColumn<Book, Integer> quantityColumn;
    @FXML
    private Button addBookButton;
    @FXML
    private Button deleteBookButton;
    @FXML
    private Button updateBookButton;

    private final BookService bookService = new BookService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize table columns
        setTranslations();
        bookIDColumn.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        autorColumn.setCellValueFactory(new PropertyValueFactory<>("autor"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        // Load initial data
        loadBooks();

    }
    private void setTranslations() {
        Locale currentLocale = SessionManager.getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("translations.content", currentLocale);

        bookIDColumn.setText(bundle.getString("bookID"));
        bookNameColumn.setText(bundle.getString("bookName"));
        autorColumn.setText(bundle.getString("autor"));
        quantityColumn.setText(bundle.getString("quantity"));


    }
    public void refreshTable() {
        loadBooks();  // Rifreskon tabelën e librave
    }

    @FXML
    private void AddBook(ActionEvent event) {
        String bookIdStr = txtBookID.getText();
        String bookName = txtBookName.getText();
        String author = txtAutor.getText();
        String quantityStr = txtQuantity.getText();

        try {
            int bookId = Integer.parseInt(bookIdStr);
            int quantity = Integer.parseInt(quantityStr);
            Book newBook = new Book(bookId, bookName, author, quantity);

            bookService.create(newBook);
            loadBooks();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Book added successfully.");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter valid numbers for Book ID and Quantity.");
        }
    }

    @FXML
    private void DeleteBook(ActionEvent event) {
        Book selectedBook = tableView.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirm Deletion");
            confirmationAlert.setHeaderText("Delete Book");
            confirmationAlert.setContentText("Are you sure you want to delete this book?");

            Optional<ButtonType> response = confirmationAlert.showAndWait();
            if (response.isPresent() && response.get() == ButtonType.OK) {
                bookService.deleteBook(selectedBook.getBookID());
                loadBooks();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Book deleted successfully.");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a book to delete.");
        }
    }

    @FXML
    private void UpdateBook(ActionEvent event) {
        String bookIdStr = txtBookID.getText();
        String bookName = txtBookName.getText();
        String author = txtAutor.getText();
        String quantityStr = txtQuantity.getText();

        try {
            int bookId = Integer.parseInt(bookIdStr);
            int quantity = Integer.parseInt(quantityStr);
            Book updatedBook = new Book(bookId, bookName, author, quantity);

            bookService.updateBook(updatedBook);
            loadBooks();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Book updated successfully.");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter valid numbers for Book ID and Quantity.");
        }
    }

    private void loadBooks() {
        try {
            ObservableList<Book> books = bookService.getAllBooks();
            tableView.setItems(books);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error loading books: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
