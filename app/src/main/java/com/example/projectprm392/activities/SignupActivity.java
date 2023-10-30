package com.example.projectprm392.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectprm392.Database.AccountDatabase;
import com.example.projectprm392.R;
import com.example.projectprm392.model.Account;

import java.util.List;

public class SignupActivity extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnSignup;
    private void bindingView(){
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignup = findViewById(R.id.btnSinup);
    }
    private void bindingAction(){
        btnSignup.setOnClickListener(this::onBtnSignupClick);
    }
    private boolean isValidUsername(String username) {
        return username.length() >= 5 && !username.matches(".*[^a-zA-Z0-9].*");
    }

    private boolean isValidPassword(String password) {
        return password.length() == 8 && password.matches("^[0-9]+$");
    }
    private void onBtnSignupClick(View view) {
        String strUsername = edtUsername.getText().toString().trim();
        String strPassword = edtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(strUsername) || TextUtils.isEmpty(strPassword)){
            return;
        }
        if (!isValidUsername(strUsername)) {
            Toast.makeText(this, "Invalid username. Username must have at least 5 characters and no special characters.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidPassword(strPassword)) {
            Toast.makeText(this, "Invalid password. Password must be 8 digits.", Toast.LENGTH_SHORT).show();
            return;
        }
        Account account = new Account(strUsername,strPassword);

        if (isAccountExist(account)){
            Toast.makeText(this, "Account exist! Please enter another account.", Toast.LENGTH_SHORT).show();
            return;
        }

        AccountDatabase.getInstance(this).accountDAO().signUp(account);
        Toast.makeText(this, "Sign Up successfully!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
    }



    private boolean isAccountExist(Account account){
        List<Account> list = AccountDatabase.getInstance(this).accountDAO().checkAccountSignUp(account.getUsername());
        return list != null && !list.isEmpty();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        bindingView();
        bindingAction();
    }
}