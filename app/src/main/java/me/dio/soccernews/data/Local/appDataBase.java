package me.dio.soccernews.data.Local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import me.dio.soccernews.doMain.News;

@Database(entities = {News.class}, version = 1)
public abstract class appDataBase extends RoomDatabase {
    public abstract newsDao newsDao();
}
