package Controllers;

import Model.ReturnBook;
import Repository.ReturnBookRepository;
import Service.ReturnBookService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReturnBookController implements Initializable {

    @FXML
    private TableColumn<ReturnBook, Integer> bookColumn;

    @FXML
    private TextField bookID;

    @FXML
    private TableColumn<ReturnBook, Integer> studentColumn;

    @FXML
    private TextField studentID;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnFilter;

    @FXML
    private TableView<ReturnBook> tableView;

    private ObservableList<ReturnBook> returnBookList = FXCollections.observableArrayList();

    private final ReturnBookService returnBookService;

    public ReturnBookController() {
        ReturnBookRepository returnBookRepository = new ReturnBookRepository();
        this.returnBookService = new ReturnBookService(returnBookRepository);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        studentColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        bookColumn.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        loadReturnBookData();
    }

    @FXML
    void clear(ActionEvent event) {
        studentID.clear();
        bookID.clear();
    }

    @FXML
    void filter(ActionEvent event) {
        String studentIdText = studentID.getText().trim();
        String bookIdText = bookID.getText().trim();

        ObservableList<ReturnBook> filteredList = FXCollections.observableArrayList();

        for (ReturnBook rb : returnBookList) {
            boolean matchesStudentID = studentIdText.isEmpty() || String.valueOf(rb.getStudentID()).equals(studentIdText);
            boolean matchesBookID = bookIdText.isEmpty() || String.valueOf(rb.getBookID()).equals(bookIdText);

            if (matchesStudentID && matchesBookID) {
                filteredList.add(rb);
            }
        }

        tableView.setItems(filteredList);
    }

    @FXML
    void returnBook(ActionEvent event) {
        ReturnBook selectedReturnBook = tableView.getSelectionModel().getSelectedItem();

        if (selectedReturnBook != null) {
            int studentId = selectedReturnBook.getStudentID();
            int bookId = selectedReturnBook.getBookID();

            try {
                returnBookService.returnBook(studentId, bookId);
                loadReturnBookData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Ju lutem selektoni një rresht në tabelë.");
        }
    }

    private void loadReturnBookData() {
        try {
            returnBookList.clear();
            returnBookList.addAll(returnBookService.getAllReturnBooks());
            tableView.setItems(returnBookList);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Dështoi ngarkimi i të dhënave nga baza e të dhënave.");
        }
    }
}
