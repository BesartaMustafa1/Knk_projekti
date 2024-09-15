package Controllers;

import app.Navigator;
import app.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.Locale;
import java.util.ResourceBundle;

public class NavbarController  {
    @FXML
    private Button btnLanguage;

    @FXML
    private Label Book;

    @FXML
    private Label Home;

    @FXML
    private Label ReturnBook;

    @FXML
    private Label Students;
    @FXML
    public void initialize() {
        Locale currentLocale = SessionManager.getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("translations.content", currentLocale);
        Home.setText(bundle.getString("home"));
        Book.setText(bundle.getString("book"));
        Students.setText(bundle.getString("students"));
        ReturnBook.setText(bundle.getString("returnbook"));
        btnLanguage.setText(bundle.getString("language"));
    }

    @FXML
    void handleChangeLanguage(ActionEvent event) {
        Locale currentLocale = SessionManager.getLocale();

        if (currentLocale.equals(new Locale("en", "US"))) {
            currentLocale = new Locale("sq", "AL");
        } else {
            currentLocale = new Locale("en", "US");
        }

        SessionManager.setLocale(currentLocale);

        ResourceBundle bundle = ResourceBundle.getBundle("translations.content", currentLocale);
        Home.setText(bundle.getString("home"));
        Book.setText(bundle.getString("book"));
        Students.setText(bundle.getString("students"));
        ReturnBook.setText(bundle.getString("returnbook"));
        btnLanguage.setText(bundle.getString("language"));
    }
    @FXML
    void sendHome(MouseEvent event) {
        Navigator.navigate(event,Navigator.HOME_PAGE);
    }

    @FXML
    void sendToBooks(MouseEvent event) {
        Navigator.navigate(event,Navigator.BOOK_PAGE);
    }

    @FXML
    void sendToStudents(MouseEvent event) {
        Navigator.navigate(event,Navigator.STUDENT_PAGE);
    }

    @FXML
    void sendToReturnBook(MouseEvent event) { Navigator.navigate(event,Navigator.RETURNBOOK_PAGE);}
}
