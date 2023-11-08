package com.example.projectprm392.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.projectprm392.model.OrderHistory;

import java.util.List;

@Dao
public interface HistoryDAO {
    @Insert
    long insert(OrderHistory order);

    @Query("SELECT * FROM history_orders WHERE userId = :userId")
    List<OrderHistory> getHistoryOrdersForUser(int userId);

    @Query("DELETE FROM history_orders WHERE userId = :userId")
    void deleteHistoryOrdersForUser(int userId);
}
