package Controllers;

import Model.Book;
import Model.Student;
import Service.BookService;
import Service.StudentService;
import app.SessionManager;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class StudentController implements Initializable {
    @FXML
    private Button Add;

    @FXML
    private Button Clear;

    @FXML
    private Button Delete;

    @FXML
    private Button Update;
    @FXML
    private TextField txtBookID;

    @FXML
    private TextField txtDep;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtStudentID;

    @FXML
    private TableView<Student> tableView;

    @FXML
    private TableColumn<Student, Integer> bookIDColumn;

    @FXML
    private TableColumn<Student, String> departmentColumn;

    @FXML
    private TableColumn<Student, String > emailColumn;

    @FXML
    private TableColumn<Student, String> nameColumn;

    @FXML
    private TableColumn<Student, Integer> studentIDColumn;
    private final StudentService studentService = new StudentService();
    private final BookService bookService = new BookService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTranslations();
        studentIDColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        bookIDColumn.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));

        loadStudents();
    }
    private void setTranslations() {
        Locale currentLocale = SessionManager.getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("translations.content", currentLocale);

        studentIDColumn.setText(bundle.getString("studentID"));
        nameColumn.setText(bundle.getString("nameColumn"));
        emailColumn.setText(bundle.getString("emailColumn"));
        bookIDColumn.setText(bundle.getString("bookID"));
        departmentColumn.setText(bundle.getString("department"));
    }
    @FXML
    void AddSt(MouseEvent event) {
        String studentID = txtStudentID.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String bookID = txtBookID.getText();
        String dep = txtDep.getText();

        try {
            int bookId = Integer.parseInt(bookID);
            int studentId = Integer.parseInt(studentID);
            Book book = bookService.getBookById(bookId);
            if (book == null) {
                showAlert(Alert.AlertType.ERROR, "Book ID Error", "The Book ID does not exist.");
                return;
            }
            if (!bookService.decreaseQuantity(bookId, 1)) {
                showAlert(Alert.AlertType.ERROR, "Quantity Error", "Not enough quantity or book does not exist.");
                return;
            }

            Student newStudent = new Student(studentId, name, email, bookId, dep);
            studentService.create(newStudent);
            loadStudents();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Student added successfully.");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter valid numbers for Student ID and Book ID.");
        }
    }



    private void loadStudents() {
        try {
            ObservableList<Student> students = studentService.getAllStudents();
            tableView.setItems(students);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error loading books: " + e.getMessage());
        }
    }

    @FXML
    void ClearSt(MouseEvent event) {
        txtStudentID.clear();
        txtName.clear();
        txtEmail.clear();
        txtBookID.clear();
        txtDep.clear();
    }

    @FXML
    void DeleteSt(MouseEvent event) {
        Student selectedStudent = tableView.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirm Deletion");
            confirmationAlert.setHeaderText("Delete Student");
            confirmationAlert.setContentText("Are you sure you want to delete this student?");

            Optional<ButtonType> response = confirmationAlert.showAndWait();
            if (response.isPresent() && response.get() == ButtonType.OK) {
                studentService.delete(selectedStudent.getStudentID());
                loadStudents();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Student deleted successfully.");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a student to delete.");
        }
    }

    @FXML
    void UpdateSt(MouseEvent event) {
        String studentID= txtStudentID.getText();
        String name= txtName.getText();
        String email= txtEmail.getText();
        String bookID = txtBookID.getText();
        String dep= txtDep.getText();

        try {
            int bookId = Integer.parseInt(bookID);
            int studentId = Integer.parseInt(studentID);
            Student updateStudent = new Student(studentId,name,email,bookId,dep);

            studentService.updated(updateStudent);
            loadStudents();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Student updated successfully.");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter valid numbers for Book ID and Student ID.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
