package Controllers;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class NavbarController {

    @FXML
    void handleChangeLanguage(ActionEvent event) {

    }

    @FXML
    void sendHome(MouseEvent event) {
        Navigator.navigate(event,Navigator.HOME_PAGE);
    }


}
