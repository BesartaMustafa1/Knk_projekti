package Controllers;

import Model.User;
import Model.dto.LoginUserDto;
import Repository.UserRepository;
import Service.UserService;
import app.Navigator;
import app.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.*;

public class LoginController {

    @FXML
    private Label Password;

    @FXML
    private Button btnCancle;

    @FXML
    private Button btnLogin;

    @FXML
    private Label login;

    @FXML
    private PasswordField pswPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private Label username;

    @FXML
    void CancleBtn(ActionEvent event) {
        Navigator.navigate(event, Navigator.LOGIN_PAGE);

    }

    @FXML
    void LoginBtn(ActionEvent event) throws SQLException {
//        if (txtEmail.getText().isBlank()==false && pswPassword.getText().isBlank()==false){
//            validateLogin();
//        }else {
//            //
//        }
        LoginUserDto loginUserData = new LoginUserDto(txtEmail.getText(), pswPassword.getText());
        boolean isLogin = UserService.login(loginUserData);

        System.out.println("Login attempt: " + isLogin);

        if (isLogin) {
            User loggedInUser = UserRepository.getByEmail(txtEmail.getText());
            SessionManager.setUser(loggedInUser);

            String lastPage = SessionManager.getLastAttemptedPage();
            if (lastPage != null) {
                Navigator.navigate(event, lastPage);
                SessionManager.setLastAttemptedPage(null);
            } else {
                System.out.println("Success");
            }
            System.out.println("Log In Successful.");
        } else {
            System.out.println("Log In Failed");
        }
    }

    @FXML
    void Signinlink(MouseEvent event) {
        Navigator.navigate(event, Navigator.SIGNUP_PAGE);
    }

//    public void validateLogin() {
//          DatabaseUtil connection = new DatabaseUtil();
//        Connection connectionDB = connection.getConnection();
//        String getUserQuery = "SELECT * FROM User WHERE Email = ?";
//
//        try {
//            // Krijimi i PreparedStatement për të marrë përdoruesin nga databaza
//            PreparedStatement preparedStatement = connectionDB.prepareStatement(getUserQuery);
//            preparedStatement.setString(1, txtEmail.getText());
//
//            // Ekzekutimi i query-t
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                String salt = resultSet.getString("Salt");
//                String storedHash = resultSet.getString("passwordHash");
//                String enteredPassword = pswPassword.getText();
//
//                // Kriptimi i fjalëkalimit të futur me salt-in e ruajtur
//                String enteredHash = PasswordHasher.generateSaltedHash(enteredPassword, salt);
//
//                if (enteredHash.equals(storedHash)) {
//                    System.out.println("Sukses");
//                    // Këtu mund të shtoni logjikën për të naviguar në faqen tjetër ose për të shfaqur një mesazh suksesi
//                } else {
//                    System.out.println("Fjalëkalimi i gabuar!");
//                }
//            } else {
//                System.out.println("Përdoruesi nuk u gjet!");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}


