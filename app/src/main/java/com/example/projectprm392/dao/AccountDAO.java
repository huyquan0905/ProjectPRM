package com.example.projectprm392.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.projectprm392.model.Account;
import java.util.List;

@Dao
public interface AccountDAO {
    @Insert
    void signUp(Account account);
    @Query("SELECT * FROM Account WHERE Username = :username AND Password = :password")
    List<Account> checkAccountLogin (String username, String password);
    @Query("SELECT * FROM Account WHERE Username = :username")
    List<Account> checkAccountSignUp (String username);

    @Insert
    void insertAdmin(Account account);

    List<Account> getAllAccounts();
}
