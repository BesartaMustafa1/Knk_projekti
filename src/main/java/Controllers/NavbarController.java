package Controllers;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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
    void handleChangeLanguage(ActionEvent event) {
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
