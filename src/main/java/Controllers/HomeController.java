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


}
