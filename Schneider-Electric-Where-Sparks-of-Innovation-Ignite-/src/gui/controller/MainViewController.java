package gui.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class MainViewController {
    public Label welcomeLabel;
    private StackPane contentArea;

    public void setView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Node view = loader.load();
            contentArea.getChildren().setAll(view);

            Object controller = loader.getController();
            if (controller instanceof SubViewController) {
                ((SubViewController) controller).setMainController(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
