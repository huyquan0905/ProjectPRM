package com.example.projectprm392.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projectprm392.model.Item;

import java.util.List;

@Dao
public interface ItemDAO {
    @Insert
    void insertItem(Item item);

    @Update
    void updateItem(Item item);

    @Delete
    void deleteItem(Item item);

    @Query("SELECT * FROM Item")
    List<Item> getAllItems();

    @Query("SELECT * FROM Item WHERE itemId = :id")
    Item getItemById(int id);
}
