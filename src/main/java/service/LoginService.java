package service;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Base64;

public class LoginService {
    @FXML
    private Label errorText;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private void processLogin(ActionEvent event) throws IOException {
        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            errorText.setText("Please enter username and password");
            return;
        }
        Response<User> response = getUserResponse();
        if (response.isSuccessful()) {
            User user = response.body();
            try {
                Main.getInstance().gotoMain(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Platform.runLater(() -> errorText.setText("User not found"));
        }

    }

    private Response<User> getUserResponse() throws IOException {
        GitHubBuilder builder = new GitHubBuilder();
        String credential = usernameField.getText() + ":" + passwordField.getText();
        String basic = "Basic " + Base64.getEncoder().encodeToString(credential.getBytes());

        Call<User> userCall = builder.getService().getUser(basic);
        return userCall.execute();
    }


}