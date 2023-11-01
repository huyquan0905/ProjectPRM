package com.example.projectprm392.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.projectprm392.dao.ItemDAO;
import com.example.projectprm392.model.Item;

@Database(entities = {Item.class}, version = 1, exportSchema = false)
public abstract class ItemDatabase extends RoomDatabase {

    private static ItemDatabase instance;

    public static synchronized ItemDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            ItemDatabase.class, "item_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
    public abstract ItemDAO itemDAO();
}
