package me.dio.soccernews.ui.news;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import me.dio.soccernews.doMain.News;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news ;

    public NewsViewModel() {
        this.news = new MutableLiveData<>();

        //TODO Remover Mock de Notícias
       List<News> news = new ArrayList<>();
       news.add(new News("Bahia vai para cima","Finally, make sure to make the behavior is accessible by setting an AccessibilityDelegate on the card. The following shows an example of allowing the user to move the card to two different positions on the screen."));
       news.add(new News("Bahia joga no sábado","Finally, make sure to make the behavior is accessible by setting an AccessibilityDelegate on the card. The following shows an example of allowing the user to move the card to two different positions on the screen."));
       news.add(new News("Vitória quer revanche","Finally, make sure to make the behavior is accessible by setting an AccessibilityDelegate on the card. The following shows an example of allowing the user to move the card to two different positions on the screen."));

       this.news.setValue(news);


    }

    public MutableLiveData<List<News>> getNews() {
        return this.news;
    }
}