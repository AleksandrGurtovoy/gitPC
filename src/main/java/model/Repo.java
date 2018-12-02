package model;

;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.ToString;

@ToString
public class Repo {

    private
    String name;

    private
    String description;

    private
    Long stargazers_count;

    private
    Long watchers_count;

    private
    Long forks_count;

    private
    String clone_url;

    public Repo() {

    }

    public Repo(String name, String description, Long stargazers_count, Long watchers_count, Long forks_count) {
        this.name = name;
        this.description = description;
        this.stargazers_count = stargazers_count;
        this.watchers_count = watchers_count;
        this.forks_count = forks_count;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StringProperty nameProperty() {
        return new SimpleStringProperty(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(Long stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public Long getWatchers_count() {
        return watchers_count;
    }

    public void setWatchers_count(Long watchers_count) {
        this.watchers_count = watchers_count;
    }

    public Long getForks_count() {
        return forks_count;
    }

    public void setForks_count(Long forks_count) {
        this.forks_count = forks_count;
    }

    public String getClone_url() {
        return clone_url;
    }

    public void setClone_url(String clone_url) {
        this.clone_url = clone_url;
    }

    public StringProperty cloneUrlProperty() {
        return new SimpleStringProperty(clone_url);
    }
}
