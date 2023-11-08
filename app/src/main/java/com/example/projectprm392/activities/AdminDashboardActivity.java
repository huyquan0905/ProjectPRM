package com.example.projectprm392.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.projectprm392.R;

public class AdminDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        CardView addItemCardView = findViewById(R.id.addItem);
        CardView getAllCardView = findViewById(R.id.allItem);
        CardView logoutCardView = findViewById(R.id.logOut);
        CardView orderCardView = findViewById(R.id.allOrder);

        addItemCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboardActivity.this, AdminAddItemActivity.class);
                startActivity(intent);
            }
        });

        getAllCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboardActivity.this, AdminGetAllActivity.class);
                startActivity(intent);
            }
        });

        orderCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboardActivity.this, AdminOrderActivity.class);
                startActivity(intent);
            }
        });

        logoutCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogout();
            }
        });
    }

    // thực hiện đăng xuất
    private void performLogout() {
        Intent intent = new Intent(AdminDashboardActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
        Toast.makeText(this, "Đăng xuất thành công!!!", Toast.LENGTH_SHORT).show();
    }
}
