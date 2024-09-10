package Controllers;

import Model.Book;
import Model.Student;
import Service.BookService;
import Service.StudentService;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.List;

public class HomeController {

    @FXML
    private PieChart BookChart;

    @FXML
    private TableView<Book> Books;

    @FXML
    private TableView<Student> Students;

    @FXML
    private VBox slider;

    @FXML
    private TableColumn<Book, Integer> bookIdColumn;

    @FXML
    private TableColumn<Book, String> bookNameColumn;

    @FXML
    private TableColumn<Book, String> autorColumn;

    @FXML
    private TableColumn<Book, Integer> quantityColumn;

    @FXML
    private TableColumn<Student, Integer> studentIDColumn;

    @FXML
    private TableColumn<Student, String> nameColumn;

    @FXML
    private TableColumn<Student, String> emailColumn;

    @FXML
    private TableColumn<Student, Integer> bookIDColumn;

    @FXML
    private TableColumn<Student, String> departmentColumn;

    private BookService bookService;
    private StudentService studentService;

    public HomeController() {
        bookService = new BookService();
        studentService = new StudentService();
    }

    @FXML
    private void initialize() {
        // Setup Book TableView
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        autorColumn.setCellValueFactory(new PropertyValueFactory<>("autor"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        // Setup Student TableView
        studentIDColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        bookIDColumn.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));

        try {
            loadBookData();
            loadStudentData();
            loadBookChart();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadBookData() throws SQLException {
        List<Book> books = bookService.getAllBooks();
        Books.getItems().setAll(books);
    }

    private void loadStudentData() throws SQLException {
        List<Student> students = studentService.getAllStudents();
        Students.getItems().setAll(students);
    }

    private void loadBookChart() throws SQLException {
        List<Book> books = bookService.getAllBooks();
        PieChart.Data[] data = books.stream()
                .map(book -> new PieChart.Data(book.getBookName(), book.getQuantity()))
                .toArray(PieChart.Data[]::new);
        BookChart.getData().setAll(data);
    }
}
