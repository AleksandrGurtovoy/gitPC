package service;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;

public class Main extends Application {
    private static Main instance;

    private Stage primaryStage;
    private BorderPane rootLayout;

    private User user;

    public Main(){
        instance = this;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("gitPC");
        gotoLogin();
        primaryStage.show();
    }

    public void gotoLogin(){
        this.user = new User();
        try {
            replaceSceneContent("/view/login.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gotoMain(User user) throws Exception {
        this.user = user;
        replaceSceneContent("/view/mainWindow.fxml");
    }

    private void replaceSceneContent(String fxml) throws Exception {
        Parent page =  FXMLLoader.load(Main.class.getResource(fxml), null, new JavaFXBuilderFactory());
        Scene scene = new Scene(page);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();
    }

    public static Main getInstance() {
        return instance;
    }

    public User getUser() {
        return user;
    }


    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
