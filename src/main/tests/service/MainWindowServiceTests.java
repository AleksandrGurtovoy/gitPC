package service;

import javafx.collections.ObservableList;
import model.Repo;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MainWindowServiceTests {

    @Test
    public void getRepoData_success() {
        List<Repo> repoList = new ArrayList<>();
        repoList.add(new Repo("test", "test", 1L, 2L, 3L));
        repoList.add(new Repo("test1", "test2", 1L, 2L, 3L));
        MainWindowService service = new MainWindowService();
        ObservableList<Repo> repoData = service.getRepoData(repoList);
        Assert.assertEquals(repoList.get(0).getName(), repoData.get(0).getName());
    }

}
