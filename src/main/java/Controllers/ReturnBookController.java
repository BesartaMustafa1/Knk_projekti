package Controllers;

import Model.ReturnBook;
import Model.filter.ReturnBookFilter;
import Repository.ReturnBookRepository;
import Service.ReturnBookService;
import app.SessionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
        setTranslations();
        studentColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        bookColumn.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        loadReturnBookData();
    }
    private void setTranslations() {
        Locale currentLocale = SessionManager.getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("translations.content", currentLocale);

        studentColumn.setText(bundle.getString("studentID"));
        bookColumn.setText(bundle.getString("bookID"));
    }

    @FXML
    void clear(ActionEvent event) {
        studentID.clear();
        bookID.clear();
    }

    @FXML
    void filter(ActionEvent event) {
            int studentId = studentID.getText().isEmpty() ? 0 : Integer.parseInt(studentID.getText().trim());
            int bookId = bookID.getText().isEmpty() ? 0 : Integer.parseInt(bookID.getText().trim());

            ReturnBookFilter filter = new ReturnBookFilter(studentId, bookId);

            try {
                List<ReturnBook> filteredReturnBooks = returnBookService.filterReturnBook(filter);
                returnBookList.setAll(filteredReturnBooks);
                tableView.setItems(returnBookList);

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Gabim gjatë filtrimit të të dhënave.");
            }
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
