package com.example.dogapp.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DogBreed.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContactDao contactDao();

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context contenxt){
        if (instance == null){
            instance = Room.databaseBuilder(contenxt,
                    AppDatabase.class, "ContactAppMobile").build();
        }
        return instance;
    }
}
