package com.example.projectprm392.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.projectprm392.dao.OrderDAO;
import com.example.projectprm392.model.Order;

@Database(entities = {Order.class}, version = 1, exportSchema = false)
public abstract class OrderDatabase extends RoomDatabase {

    private static OrderDatabase instance;

    public static synchronized OrderDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            OrderDatabase.class, "order_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract OrderDAO orderDAO();
}