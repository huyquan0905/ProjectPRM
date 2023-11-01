package com.example.projectprm392.activities;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.cardview.widget.CardView;
import com.example.projectprm392.R;
import android.widget.Toast;
import com.example.projectprm392.activities.AdminAddItemActivity;
import com.example.projectprm392.activities.LoginActivity;

public class AdminDashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        // Lấy tham chiếu đến CardView "Add Item" bằng ID
        CardView addItemCardView = findViewById(R.id.addItemCardView);

        // Thêm sự kiện nghe cho CardView "Add Item"
        addItemCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi người dùng bấm vào CardView "Add Item"
                // Chuyển sang màn hình AdminAddItemActivity
                Intent intent = new Intent(AdminDashboardActivity.this, AdminAddItemActivity.class);
                startActivity(intent);
            }
        });

        // Lấy tham chiếu đến CardView "Log Out" bằng ID
        CardView logoutCardView = findViewById(R.id.logoutCardView);

        // Thêm sự kiện nghe cho CardView "Log Out"
        logoutCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi người dùng bấm vào CardView "Log Out"
                // Thực hiện đăng xuất và chuyển đến màn hình đăng nhập
                performLogout();
            }
        });
    }

    // Hàm thực hiện đăng xuất
    private void performLogout() {
        // Đặt code đăng xuất của bạn ở đây.
        // Ví dụ: Chuyển đến màn hình đăng nhập (LoginActivity)
        Intent intent = new Intent(AdminDashboardActivity.this, LoginActivity.class);
        startActivity(intent);
        finish(); // Kết thúc hoạt động hiện tại
        Toast.makeText(this, "Logout success!!!", Toast.LENGTH_SHORT).show();
    }
}
