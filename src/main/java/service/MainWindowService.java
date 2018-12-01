package service;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Repo;
import model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class MainWindowService extends Application {
    @FXML
    private Label loginLabel;
    @FXML
    private Label nickNameLabel;
    @FXML
    private TableView tableRepos;

    private Pane root;
    private Stage primaryStage;


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("gitPC");

        initRootLayout();
    }

    public void mainAction(User user) {
        Platform.runLater(() -> {
            try {
                loginLabel.setText(user.getLogin());
                nickNameLabel.setText(user.getName());
                GitHubBuilder builder = new GitHubBuilder();
                Call<List<Repo>> repoCall = builder.getService().getUserRepo(user.getLogin());
                repoCall.enqueue(new Callback<List<Repo>>() {

                    @Override
                    public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                        List<Repo> repos = response.body();
                        tableRepos.setItems(getRepoData(repos));
                    }

                    @Override
                    public void onFailure(Call<List<Repo>> call, Throwable t) {
                        System.out.println("Fail");
                    }
                });
                start(Main.getInstance().getStage());

            } catch (Exception e) {
                e.printStackTrace();
            }
            initRootLayout();
        });
    }


    private void initRootLayout() {
        try {
            root = FXMLLoader.load(getClass().getResource("/view/mainWindow.fxml"));

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ObservableList<Repo> getRepoData(List<Repo> repos) {
        ObservableList<Repo> repoData = FXCollections.observableArrayList();
        for (Repo repo : repos) {
            repoData.add(new Repo(repo.getName(), repo.getDescription(),
                    repo.getStargazers_count(), repo.getWatchers_count(), repo.getForks_count()));
        }
        return repoData;
    }


}
