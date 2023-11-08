package com.example.projectprm392.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.projectprm392.model.Order;

import java.util.List;

@Dao
public interface OrderDAO {
    @Insert
    long insert(Order order);

    @Query("SELECT * FROM orders WHERE userId = :userId")
    List<Order> getOrdersForUser(int userId);

    @Query("DELETE FROM orders WHERE userId = :userId")
    void deleteOrdersForUser(int userId);
}