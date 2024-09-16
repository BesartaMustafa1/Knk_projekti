package app;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Navigator.navigate(stage, Navigator.SIGNUP_PAGE);
        stage.setTitle("Menaxhimi i Biblotekes");
    }
}

