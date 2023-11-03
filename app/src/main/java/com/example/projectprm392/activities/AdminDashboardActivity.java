package com.example.projectprm392.activities;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.cardview.widget.CardView;
import com.example.projectprm392.R;
import android.widget.Toast;
import com.example.projectprm392.activities.AdminAddItemActivity;
import com.example.projectprm392.activities.AdminGetAllActivity;
import com.example.projectprm392.activities.LoginActivity;

public class AdminDashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        CardView addItemCardView = findViewById(R.id.addItemCardView);

        addItemCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboardActivity.this, AdminAddItemActivity.class);
                startActivity(intent);

            }
        });

        CardView getAllCardView = findViewById(R.id.getItemCardView);
        getAllCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboardActivity.this, AdminGetAllActivity.class);
                startActivity(intent);
            }
        });

        CardView logoutCardView = findViewById(R.id.logoutCardView);

        logoutCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogout();
            }
        });
    }

    // Hàm thực hiện đăng xuất
    private void performLogout() {
        Intent intent = new Intent(AdminDashboardActivity.this, LoginActivity.class);
        startActivity(intent);
        finish(); // Kết thúc hoạt động hiện tại
        Toast.makeText(this, "Logout success!!!", Toast.LENGTH_SHORT).show();
    }
}
