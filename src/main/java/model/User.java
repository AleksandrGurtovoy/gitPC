package model;

import lombok.ToString;

@ToString
public class User {

    private
    String login;

    private
    String avatar_url;

    private
    String name;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getAvatarUrl() {
        return avatar_url;
    }

    public void setAvatarUrl(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
