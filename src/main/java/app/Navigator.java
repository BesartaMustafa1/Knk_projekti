package app;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class Navigator {
    public static final String SIGNUP_PAGE = "Signup.fxml";
    public static final String LOGIN_PAGE = "Login.fxml";

    private static final Map<String, Object> params = new HashMap<>();
    private static ResourceBundle bundle = ResourceBundle.getBundle("translations.content", Locale.getDefault());
    public interface ParametrizedController {
        void setParams(Object params);
    }

    public static void navigate(Stage stage, String page) {
        Pane formPane = loadPane(page);
        if (formPane != null) {
            Scene newScene = new Scene(formPane);
            stage.setScene(newScene);
            stage.show();
        }
    }

    public static void navigate(Event event, String page) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        navigate(stage, page);
    }

    public static void navigate(ActionEvent ae, String page) {
        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        navigate(stage, page);
    }

    public static void navigate(ActionEvent ae, String page, Object param) {
        params.put(page, param);
        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(page));
            Parent parent = loader.load();
            Object controller = loader.getController();
            if (controller instanceof ParametrizedController) {
                ((ParametrizedController) controller).setParams(param);
            }
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void navigate(Pane pane, String form) {
        Pane formPane = loadPane(form);
        if (formPane != null) {
            pane.getChildren().clear();
            pane.getChildren().add(formPane);
        }
    }
    public static void changeLanguage() {
        Locale defaultLocale = Locale.getDefault();
        if (defaultLocale.getLanguage().equals("en")) {
            Locale.setDefault(new Locale("sq"));
        } else {
            Locale.setDefault(Locale.ENGLISH);
        }
        bundle = ResourceBundle.getBundle("translations.content", Locale.getDefault());
    }
    public static Pane loadPane(String form) {
        try {
            FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(form));
            Parent formPane = loader.load();

            Object controller = loader.getController();
            if (controller instanceof ParametrizedController && params.containsKey(form)) {
                ((ParametrizedController) controller).setParams(params.get(form));
            }

            return (Pane) formPane;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }
}
