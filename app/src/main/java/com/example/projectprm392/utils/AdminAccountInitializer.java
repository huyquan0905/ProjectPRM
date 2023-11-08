package com.example.projectprm392.utils;

import android.content.Context;

import com.example.projectprm392.dao.AccountDAO;
import com.example.projectprm392.Database.AccountDatabase;
import com.example.projectprm392.model.Account;

public class AdminAccountInitializer {
    public static void insertAdminAccount(Context context) {
        Account adminAccount = new Account("admin", "11111111");
        adminAccount.setRole("admin");

        AccountDAO accountDAO = AccountDatabase.getInstance(context).accountDAO();
        accountDAO.insertAdmin(adminAccount);
    }
}