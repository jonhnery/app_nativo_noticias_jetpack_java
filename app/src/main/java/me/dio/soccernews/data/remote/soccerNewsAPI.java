package me.dio.soccernews.data.remote;

import java.util.List;

import me.dio.soccernews.doMain.News;
import retrofit2.Call;
import retrofit2.http.GET;

public interface soccerNewsAPI {

    @GET("news.json")
    Call<List<News>> getNews();

}
