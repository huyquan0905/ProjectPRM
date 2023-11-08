package com.example.projectprm392.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.projectprm392.model.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);
}
