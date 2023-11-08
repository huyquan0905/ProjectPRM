package com.example.projectprm392.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.projectprm392.dao.HistoryDAO;
import com.example.projectprm392.model.OrderHistory;

@Database(entities = {OrderHistory.class}, version = 1, exportSchema = false)
public abstract class HistoryDatabase extends RoomDatabase {

    private static HistoryDatabase instance;

    public static synchronized HistoryDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            HistoryDatabase.class, "history_order_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract HistoryDAO historyOrderDAO();
}