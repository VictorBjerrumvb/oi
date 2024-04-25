package gui.controller;

import be.Personal;
import bll.PersonalManager;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent; // Correct import for JavaFX
import javafx.scene.control.Alert;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginViewController {

    @FXML public ImageView background; // Background image for the login screen
    @FXML private MFXPasswordField txtFieldPassword; // Password field for input
    @FXML private MFXTextField txtFieldUsername; // Username field for input
    @FXML private MFXButton btnLogin; // Login button
    @FXML private MFXButton btnlogin1;
    private PersonalManager personalManager = new PersonalManager(); // Manager to handle login logic
    private static final Logger LOGGER = Logger.getLogger(LoginViewController.class.getName()); // Logger for logging errors

    /**
     * Handle login button action.
     * @param actionEvent The event triggered by clicking the login button.
     */
    @FXML
    private void handleLogin(ActionEvent actionEvent) {
        btnLogin.setDisable(true); // Disable the login button to prevent multiple clicks
        validateCredentials(txtFieldUsername.getText(), txtFieldPassword.getText()); // Validate credentials
    }

    /**
     * Validates the credentials provided by the user.
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     */
    private void validateCredentials(String username, String password) {
        new Thread(() -> { // Create a new thread to handle the validation process
            try {
                Personal personalLogged = personalManager.validatePersonal(username, password); // Validate the user
                if (personalLogged != null) {
                    loadUserSpecificView(personalLogged); // Load user-specific view if validation is successful
                } else {
                    updateUIOnError("Incorrect Password or Username"); // Update UI to show error message
                }
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Login failed", e); // Log the exception if login fails
                updateUIOnError("Login failed. Please try again."); // Show error message on UI
            } finally {
                Platform.runLater(() -> btnLogin.setDisable(false)); // Re-enable the login button on the UI thread
            }
        }).start();
    }

    /**
     * Loads the user-specific view based on the role of the user.
     * @param personal The personal object containing user data and roles.
     */
    private void loadUserSpecificView(Personal personal) {
        Platform.runLater(() -> {
            try {
                Parent root = loadFXMLForRole(personal.getRoleId()); // Load the appropriate FXML based on the user role
                showStage(root, resolveTitleForRole(personal.getRoleId())); // Show the stage with the loaded view
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Failed to load user interface", e); // Log the exception if loading fails
                showErrorDialog("Loading Error", "Failed to load user interface. Please contact support."); // Show error dialog
            }
        });
    }

    /**
     * Loads the FXML file for the given role ID.
     * @param roleId The role ID of the user.
     * @return The loaded parent node.
     * @throws IOException If loading the FXML file fails.
     */
    private Parent loadFXMLForRole(int roleId) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(getFXMLFileForRole(roleId)));
        return loader.load();
    }

    /**
     * Resolves the window title based on the user's role.
     * @param roleId The role ID of the user.
     * @return The title for the window.
     */
    private String resolveTitleForRole(int roleId) {
        return roleId == 1 ? "Main Page" : "Coordinator Page";
    }

    /**
     * Returns the FXML file path based on the user's role.
     * @param roleId The role ID of the user.
     * @return The file path of the FXML file.
     */
    private String getFXMLFileForRole(int roleId) {
        return roleId == 1 ? "/fxml/PersonalView.fxml" : "/fxml/MainView.fxml";
    }

    /**
     * Shows the stage with the given root node and title.
     * @param root The root node to set in the scene.
     * @param title The title for the stage.
     */
    private void showStage(Parent root, String title) {
        Stage currentStage = (Stage) btnLogin.getScene().getWindow(); // Get the current stage
        Stage newStage = new Stage();
        newStage.setTitle(title); // Set the title
        newStage.setScene(new Scene(root)); // Set the scene
        newStage.setMaximized(currentStage.isMaximized()); // Maximize if the current stage is maximized
        newStage.show(); // Show the stage
        currentStage.hide(); // Hide the current stage
    }

    /**
     * Updates the UI to show an error message.
     * @param message The error message to display.
     */
    private void updateUIOnError(String message) {
        Platform.runLater(() -> clearFieldsWithPrompt(message)); // Update the UI on the JavaFX application thread
    }

    /**
     * Displays an error dialog with the specified title and content.
     * @param title The title of the dialog.
     * @param content The content of the dialog.
     */
    private void showErrorDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Clears the fields and sets a prompt in the username field.
     * @param prompt The prompt to set in the username field.
     */
    private void clearFieldsWithPrompt(String prompt) {
        txtFieldPassword.clear();
        txtFieldUsername.setPromptText(prompt);
    }

    @FXML
    private void handleForgotPassword(ActionEvent actionEvent) {
        // Future implementation of forgot password functionality
    }

    @FXML
    private void handleLogo(MouseEvent event) {
        System.out.println("Logo clicked!"); // Log when the logo is clicked
    }
}
