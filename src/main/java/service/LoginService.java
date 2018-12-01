package service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.User;
import retrofit2.Call;
import retrofit2.Callback;
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


    private MainWindowService mainWindowService = new MainWindowService();

    @FXML
    private void processLogin(ActionEvent event) {
        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            errorText.setText("Please enter username and password");
            return;
        }
        GitHubBuilder builder = new GitHubBuilder();
        String credential = usernameField.getText() + ":" + passwordField.getText();
        String basic = "Basic " + Base64.getEncoder().encodeToString(credential.getBytes());

        Call<User> userCall = builder.getService().getUser(basic);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                try {
                    mainWindowService.mainAction(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                errorText.setText("User not found");
            }
        });

    }


}