package service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginService {
    @FXML
    private Label errorText;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    private GitHubService gitHubService;

    @FXML
    private void processLogin(ActionEvent event) {
        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            errorText.setText("Please enter username and password");
            return;
        }
        GitHubBuilder builder = new GitHubBuilder();
        System.out.println(builder.getService().getUserRepo(usernameField.getText()));

    }
}