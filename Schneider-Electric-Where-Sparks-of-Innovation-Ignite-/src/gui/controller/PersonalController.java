package gui.controller;

import be.Personal;
import gui.helperclases.ShowImageClass;
import gui.model.PersonalModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
public class PersonalController extends SubViewController {
    private static final Logger LOGGER = Logger.getLogger(PersonalController.class.getName());
    public MFXTextField txtSearch;
    @FXML private Label lblProfileName;
    @FXML private ImageView imgLogo;
    @FXML private ImageView imgProfilePicture;
    @FXML private MFXButton btnHome, btnPersonalInfo, btnSecurity, btnPeopleAndSharing;
    @FXML private BorderPane mainPane;
    @FXML private FlowPane flowPaneInformation;

    private ShowImageClass showImageClass = new ShowImageClass();
    private int textFieldCount = 0;
    private Personal operator;
    private PersonalModel personalModel;

    public void initialize() {
        super.initialize();
        try {
            personalModel = new PersonalModel();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Initialization failed: " + e.getMessage(), e);
        }
    }

    public void setOperator(Personal operator) {
        this.operator = operator;
        lblProfileName.setText(operator.getUsername());
        updateProfileImage(operator.getPicture());
    }

    private void updateProfileImage(String imagePath) {
        if (imagePath != null && !imagePath.isEmpty()) {
            imgProfilePicture.setImage(showImageClass.showImage(imagePath));
        } else {
            imgProfilePicture.setImage(showImageClass.showImage("profileimages/image.png"));
        }
    }

    @FXML
    private void handleHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainView.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading MainView.fxml", e);
        }
    }
    private Path generateUniqueFilePath(Path directory, String fileName) {
        String baseFileName = fileName;
        String extension = "";
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex != -1) {
            baseFileName = fileName.substring(0, dotIndex);
            extension = fileName.substring(dotIndex);
        }

        Path filePath = directory.resolve(fileName);
        int count = 1;
        while (Files.exists(filePath)) {
            filePath = directory.resolve(baseFileName + "_" + count + extension);
            count++;
        }
        return filePath;
    }

    private TextField addComponentsToGridPane(String labelText) {
        Label label = new Label(labelText);
        TextField textField = new TextField();
        GridPane gridpane = new GridPane();
        gridpane.add(label, 0, textFieldCount);
        gridpane.add(textField, 1, textFieldCount++);
        flowPaneInformation.getChildren().add(gridpane);
        return textField;
    }

    @FXML
    private void handleSecurity(ActionEvent event) {
        flowPaneInformation.getChildren().clear();
        TextField currentPassword = addComponentsToGridPane("Current Password:");
        TextField newPassword = addComponentsToGridPane("New Password:");
        Button changePassword = addButtonToFlowPane("Change Password");
        changePassword.setOnAction(e -> processPasswordChange(currentPassword, newPassword));
    }

    private Button addButtonToFlowPane(String buttonText) {
        Button button = new Button(buttonText);
        flowPaneInformation.getChildren().add(button);
        return button;
    }

    private void processPasswordChange(TextField currentPassword, TextField newPassword) {
        if (currentPassword.getText().equals(operator.getPassword())) {
            operator.setPassword(newPassword.getText());
            try {
                personalModel.updatePersonal(operator);
            } catch (Exception e) {  // General Exception handling if no specific exceptions are expected
                LOGGER.log(Level.SEVERE, "Database update failed for password change.", e);
            }
        } else {
            currentPassword.clear();
            newPassword.clear();
            currentPassword.setPromptText("Incorrect Password");
        }
    }

    private void alertUser(String message, Exception ex) {
        LOGGER.log(Level.SEVERE, message, ex);
        Alert alert = new Alert(AlertType.ERROR, message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML
    private void openCalculator() {
        if (mainController != null) {
            mainController.setView("/fxml/Calculator.fxml");
        }
    }

    public void setup() {
    }

    @FXML
    private void handleLogo(MouseEvent event) {
        System.out.println("Logo was clicked!");
    }
    public void handlePersonalInfo(ActionEvent event) {
    }

    public void handlePeopleAndSharing(ActionEvent event) {
    }

    public void handleDragDrop(DragEvent dragEvent) {
    }

    public void handleDragOver(DragEvent dragEvent) {
    }

    public void handleProfilePicture(MouseEvent mouseEvent) {
    }
}
