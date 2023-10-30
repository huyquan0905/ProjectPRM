package com.example.projectprm392;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectprm392.Database.AccountDatabase;

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
        String strPasswrod = edtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(strUsername) || TextUtils.isEmpty(strPasswrod)){
            return;
        }
        Account account = new Account(strUsername,strPasswrod);

        if (isAccountExist(account)){
            Toast.makeText(this, "Login successfully.", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "Account does not exist!", Toast.LENGTH_SHORT).show();
    }

    private boolean isAccountExist(Account account) {
        List<Account> list = AccountDatabase.getInstance(this).accountDAO().checkAccountLogin(account.getUsername(),account.getPassword());
        return list != null && !list.isEmpty();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindingView();
        bindingAction();
    }
}