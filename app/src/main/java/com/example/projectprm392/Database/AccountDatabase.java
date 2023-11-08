package com.example.projectprm392.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.projectprm392.model.Account;
import com.example.projectprm392.dao.AccountDAO;

@Database(entities = {Account.class}, version = 2)
public abstract class AccountDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "account.db";
    private static AccountDatabase instance;

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE account ADD COLUMN Role TEXT");
        }
    };
    public static synchronized AccountDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AccountDatabase.class, DATABASE_NAME)
                    .addMigrations(MIGRATION_1_2)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract AccountDAO accountDAO();
}
