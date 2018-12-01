package service;

import model.Repo;
import model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

import java.util.List;

public interface GitHubService {
    @GET("/users/{username}/repos")
    Call<List<Repo>> getUserRepo(@Path("username")String username);

    @GET("user")
    Call<User> getUser(@Header("Authorization") String authorization);

}
