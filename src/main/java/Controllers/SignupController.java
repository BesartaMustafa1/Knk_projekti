package Controllers;

import Model.dto.UserDto;
import Service.UserService;
import app.Navigator;
import app.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class SignupController {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSignup;

    @FXML
    private PasswordField pswCPassword;

    @FXML
    private PasswordField pswPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtName;
    @FXML
    public void initialize() {
        txtName.setOnKeyPressed(this::handleKeyPressed);
        txtLastName.setOnKeyPressed(this::handleKeyPressed);
        txtEmail.setOnKeyPressed(this::handleKeyPressed);
        pswPassword.setOnKeyPressed(this::handleKeyPressed);
        pswCPassword.setOnKeyPressed(this::handleKeyPressed);
    }

    @FXML
    private void handleKeyPressed(KeyEvent ke) {
        if (ke.getCode() == KeyCode.ENTER) {
            ActionEvent actionEvent = new ActionEvent(ke.getSource(), null);
            HandelSignup(actionEvent);
        }
    }

    @FXML
    void HandelSignup(ActionEvent event) {
        if (SessionManager.isLoggedIn()) {
            System.out.println("Already logged in. No need to sign up.");
            Navigator.navigate(event, Navigator.HOME_PAGE);
            return;
        }
        String firstName = txtName.getText();
        String lastName = txtLastName.getText();
        String email = txtEmail.getText();
        String password = pswPassword.getText();
        String confirmPassword = pswCPassword.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Registration Failed", "All fields are required.");
            return;
        }


        UserDto userData = new UserDto(firstName, lastName, email, password, confirmPassword);
        try {
            boolean success = UserService.signUp(userData);
            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Registration Successful!", "Welcome " + firstName);
                Navigator.navigate(event, Navigator.HOME_PAGE);
            } else {
                showAlert(Alert.AlertType.ERROR, "Registration Failed", "An error occurred while creating the account.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Registration Failed", "A database error occurred.");
        }
    }

    @FXML
    void HandleCancel(ActionEvent event) {
        txtName.clear();
        txtLastName.clear();
        txtEmail.clear();
        pswPassword.clear();
        pswCPassword.clear();
    }

    @FXML
    void HandleLogin(MouseEvent event) {
        showAlert(Alert.AlertType.INFORMATION, "Navigate", "Navigating to Sign In page...");
        Navigator.navigate(event, Navigator.LOGIN_PAGE);
    }


    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

//    private boolean signUp(String firstName, String lastName, String email, String password) {
//        return true;
//    }
}
