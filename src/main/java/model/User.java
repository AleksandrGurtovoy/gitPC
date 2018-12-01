package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class User {

    private @Getter
    @Setter
    String login;

    private @Getter
    @Setter
    Integer id;

    private @Getter
    @Setter
    String avatarUrl;

    private @Getter
    @Setter
    String gravatarId;

    private @Getter
    @Setter
    String url;

    private @Getter
    @Setter
    String htmlUrl;
}
