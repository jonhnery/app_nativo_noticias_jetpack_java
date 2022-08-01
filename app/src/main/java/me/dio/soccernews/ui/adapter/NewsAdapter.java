package me.dio.soccernews.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import me.dio.soccernews.R;
import me.dio.soccernews.databinding.MeusItemsBinding;
import me.dio.soccernews.doMain.News;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private final List<News> news;
    private final FavoriteListener favoriteListener;

    public NewsAdapter(List<News> news, FavoriteListener favoriteListener) {
        this.news = news;
        this.favoriteListener = favoriteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MeusItemsBinding binding = MeusItemsBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        News news = this.news.get(position);
        holder.binding.tvtitle.setText(news.title);
        holder.binding.tvdescription.setText(news.description);
        Picasso.get().load(news.image).fit().into(holder.binding.ivThummail);
            holder.binding.btopenlink.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(news.link));
            context.startActivity(i);
        });
        // Implementação de compartilhar
        holder.binding.ivshare.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, news.title);
            context.startActivity(Intent.createChooser(i, "Share"));
        });
        //Implementação de favoritar - o evento sera intasiado pelo fragment
        holder.binding.ivfavorite.setOnClickListener(view -> {
            news.favorite = !news.favorite;
            this.favoriteListener.onFavorite(news);
            notifyItemChanged(position);
        });
            int favoriteColor = news.favorite ? R.color.favorite_active : R.color.favorite_inactive;
            holder.binding.ivfavorite.setColorFilter(context.getResources().getColor(favoriteColor));


    }

    @Override
    public int getItemCount() {
        return this.news.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final MeusItemsBinding binding;

        public ViewHolder(MeusItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface FavoriteListener {
        void onFavorite(News news);
    }
}
