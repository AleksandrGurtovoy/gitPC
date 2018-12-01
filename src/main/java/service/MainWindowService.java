package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Repo;
import model.User;
import retrofit2.Call;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainWindowService implements Initializable {
    @FXML
    private Label loginLabel;
    @FXML
    private Label nickNameLabel;
    @FXML
    private TableView tableRepos;
    @FXML
    private ImageView photo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User user = Main.getInstance().getUser();
        loginLabel.setText(user.getLogin());
        nickNameLabel.setText(user.getName());
        photo.setImage(new Image(user.getAvatarUrl()));
        GitHubBuilder builder = new GitHubBuilder();
        Call<List<Repo>> repoCall = builder.getService().getUserRepo(user.getLogin());
        List<Repo> repos = null;
        try {
            repos = repoCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Objects.nonNull(repos)) {
            for (Repo r : repos) {
                System.out.println(r.toString());
            }
            tableRepos.setItems(getRepoData(repos));
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
