package Controllers;

import Model.Book;
import Model.Student;
import Service.BookService;
import Service.StudentService;
import app.SessionManager;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
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
    private Label studentCountLabel;
    @FXML
    private Label nr;



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

    }
    private void loadBookData() throws SQLException {
        List<Book> books = bookService.getAllBooks();
        Books.getItems().setAll(books);
    }

    private void loadStudentData() throws SQLException {
        List<Student> students = studentService.getStudentsWithBooks();
        int studentCount = students.size();
        studentCountLabel.setText(" " + studentCount);
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
