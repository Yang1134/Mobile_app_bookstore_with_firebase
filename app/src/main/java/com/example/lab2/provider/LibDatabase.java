package com.example.lab2.provider;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Book.class}, version = 1)
public abstract class LibDatabase extends RoomDatabase {

    public static final String ITEM_DATABASE_NAME = "item_database";

    public abstract BookDao bookDao();

    private static volatile LibDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static LibDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LibDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    LibDatabase.class, ITEM_DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
