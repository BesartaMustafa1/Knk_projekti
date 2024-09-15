package Controllers;

import Model.Book;
import Model.Student;
import Service.BookService;
import Service.StudentService;
import app.SessionManager;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class HomeController {

    @FXML
    private BarChart BookChart;

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
        setTranslations();
        // Setup Book TableView
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        autorColumn.setCellValueFactory(new PropertyValueFactory<>("autor"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        // Setup Student TableView
        studentIDColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        bookIDColumn.setCellValueFactory(new PropertyValueFactory<>("bookID"));

        try {
            loadBookData();
            loadStudentData();
            loadBookChart();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void setTranslations() {
        Locale currentLocale = SessionManager.getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("translations.content", currentLocale);

        bookIdColumn.setText(bundle.getString("bookID"));
        bookNameColumn.setText(bundle.getString("bookName"));
        autorColumn.setText(bundle.getString("autor"));
        quantityColumn.setText(bundle.getString("quantity"));

        studentIDColumn.setText(bundle.getString("studentID"));
        bookIDColumn.setText(bundle.getString("bookID"));

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

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Numri i Librave");
        for (Book book : books) {
            XYChart.Data<String, Number> data = new XYChart.Data<>(book.getBookName(), book.getQuantity());
            series.getData().add(data);
        }

        BookChart.getData().clear();
        BookChart.getData().add(series);
    }
}
