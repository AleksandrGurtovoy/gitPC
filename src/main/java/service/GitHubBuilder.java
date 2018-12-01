package service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubBuilder {

    public static final String BASE_URL = "https://api.github.com";

    private Retrofit retrofit;

    public GitHubBuilder() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public service.GitHubService getService() {
        return retrofit.create(service.GitHubService.class);
    }
}
