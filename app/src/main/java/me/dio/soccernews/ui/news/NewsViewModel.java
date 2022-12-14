package me.dio.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import me.dio.soccernews.data.remote.soccerNewsAPI;
import me.dio.soccernews.doMain.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsViewModel extends ViewModel {

    public enum State {
        DOING, DONE, ERROR;
    }

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();
    private final MutableLiveData<State> state = new MutableLiveData<State>();
    private final soccerNewsAPI api;


    public NewsViewModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jonhnery.github.io/api_nativo_noticias_jetpack_java/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(soccerNewsAPI.class);


        this.findNews();
    }

    private void findNews() {
        state.setValue(State.DOING);
        api.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()) {
                    news.setValue(response.body());
                    state.setValue(State.ERROR);

                } else {
                    //TODO Pensar em uma estratégia de tratamento de erros.
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                //TODO Pensar em uma estratégia de tratamento de erros.
            }
        });
    }

    public LiveData<List<News>> getNews() {
        return this.news;
    }
    public MutableLiveData<State> getState() { return this.state; }

}