package service;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseButton;
import model.Repo;
import model.User;
import retrofit2.Call;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainWindowService implements Initializable {
    @FXML
    private Label loginLabel;
    @FXML
    private Label nickNameLabel;
    @FXML
    private TableView<Repo> tableRepos;
    @FXML
    private ImageView photo;
    @FXML
    private TableColumn<Integer, Integer> numberColumn;
    @FXML
    private TableColumn<Repo, String> nameColumn;
    @FXML
    private TableColumn<Repo, String> deskColumn;
    @FXML
    private TableColumn<Repo, Long> watchedColumn;
    @FXML
    private TableColumn<Repo, Long> starsColumn;
    @FXML
    private TableColumn<Repo, Long> forksColumn;

    private List<String> urls = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User user = Main.getInstance().getUser();
        setUserData(user);
        setRightClickAction();
        List<Repo> repos = getUserRepo(user);
        if (Objects.nonNull(repos)) {
            setUrlsToList(repos);
            setColumnData(getRepoData(repos));
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

    private void setColumnData(ObservableList<Repo> repoData) {
        final int[] number = {0};
        tableRepos.getItems().addAll(repoData);

        nameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        deskColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDescription()));
        watchedColumn.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getWatchers_count()));
        starsColumn.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getStargazers_count()));
        forksColumn.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getForks_count()));
        numberColumn.setCellValueFactory(c -> new SimpleObjectProperty<>(number[0]++));
    }

    private void setUserData(User user) {
        if (Objects.nonNull(user.getLogin())) {
            loginLabel.setText(user.getLogin());
        } else {
            loginLabel.setText("Login is not present");
        }
        if (Objects.nonNull(user.getName())) {
            nickNameLabel.setText(user.getName());
        } else {
            nickNameLabel.setText("Username is not present");
        }
        if (Objects.nonNull(user.getAvatarUrl())) {
            photo.setImage(new Image(user.getAvatarUrl()));
        } else {
            photo.setImage(new Image("/media/pepe.jpg"));
        }
    }

    private List<Repo> getUserRepo(User user) {
        GitHubBuilder builder = new GitHubBuilder();
        Call<List<Repo>> repoCall = builder.getService().getUserRepo(user.getLogin());
        List<Repo> repos = null;
        try {
            repos = repoCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repos;
    }

    @FXML
    private void processExit(ActionEvent event) {
        Main.getInstance().gotoLogin();
    }

    private void setRightClickAction() {
        tableRepos.getSelectionModel().setCellSelectionEnabled(true);
        tableRepos.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                String url = new String();
                ObservableList<TablePosition> positionList = tableRepos.getSelectionModel().getSelectedCells();

                for (TablePosition position : positionList) {
                    url = urls.get(position.getRow());
                    System.out.println(url);
                }

                final ClipboardContent clipboardContent = new ClipboardContent();
                clipboardContent.putString(url);
                Clipboard.getSystemClipboard().setContent(clipboardContent);
            }

        });
    }

    private void setUrlsToList(List<Repo> repos) {
        for (Repo r : repos) {
            urls.add(r.getClone_url());
        }
    }

}
