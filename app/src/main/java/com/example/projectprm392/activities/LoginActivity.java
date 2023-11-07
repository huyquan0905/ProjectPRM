package com.example.projectprm392.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectprm392.Database.AccountDatabase;
import com.example.projectprm392.R;
import com.example.projectprm392.model.Account;
import com.example.projectprm392.utils.AdminAccountInitializer;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;
    private TextView tvSignup;

    private void bindingView(){
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvSignup = findViewById(R.id.tvSignup);
    }
    private void bindingAction(){
        btnLogin.setOnClickListener(this::onBtnLoginClick);
        tvSignup.setOnClickListener(this::onTvSignupClick);
    }

    private void onTvSignupClick(View view) {
        startActivity(new Intent(LoginActivity.this, SignupActivity.class));
    }


    private void onBtnLoginClick(View view) {
        String strUsername = edtUsername.getText().toString().trim();
        String strPassword = edtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(strUsername) || TextUtils.isEmpty(strPassword)) {
            return;
        }

        Account account = new Account(strUsername, strPassword);


        // Kiểm tra xem tài khoản có tồn tại và lấy thông tin của tài khoản
        List<Account> accounts = AccountDatabase.getInstance(this).accountDAO().checkAccountLogin(account.getUsername(), account.getPassword());

        boolean loggedIn = false;

        for (Account loggedInAccount : accounts) {
            if ("admin".equals(loggedInAccount.getRole())) {
                // Xử lý khi người dùng đăng nhập với vai trò "admin"
                Toast.makeText(this, "Admin login successfully.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, AdminDashboardActivity.class);
                startActivity(intent);
                loggedIn = true;
                break; // Kết thúc vòng lặp nếu là admin
            } else if ("users".equals(loggedInAccount.getRole())) {
                // Xử lý khi người dùng đăng nhập với vai trò "user"
                Toast.makeText(this, "User login successfully.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, ListItem.class);
                startActivity(intent);
                loggedIn = true;
                break; // Kết thúc vòng lặp nếu là user
            }
            else{
                // Xử lý khi người dùng đăng nhập với vai trò "user"
                Toast.makeText(this, "User login successfully.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, ListItem.class);
                startActivity(intent);
                loggedIn = true;
                break; // Kết thúc vòng lặp nếu là user
            }
        }

        if (!loggedIn) {
            // Xử lý khi không có tài khoản phù hợp
            Toast.makeText(this, "Account does not exist or role is not defined!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isAccountExist(Account account) {
        List<Account> list = AccountDatabase.getInstance(this).accountDAO().checkAccountLogin(account.getUsername(),account.getPassword());
        return list != null && !list.isEmpty();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AdminAccountInitializer.insertAdminAccount(this);
        bindingView();
        bindingAction();
    }

}